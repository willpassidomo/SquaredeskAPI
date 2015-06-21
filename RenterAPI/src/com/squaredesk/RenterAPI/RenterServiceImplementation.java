/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import com.squaredesk.common.Contact;
import com.squaredesk.common.Imagec;
import com.squaredesk.common.Name;
import com.squaredesk.common.PayPalAccount;
import com.squaredesk.common.Rateable;
import com.squaredesk.common.Rating;
import com.squaredesk.common.User;
import com.squaredesk.provider.CommonFeature;
import com.squaredesk.provider.Coordinate;
import com.squaredesk.provider.FacilityType;
import com.squaredesk.provider.IllegalCoordinateValue;
import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.PrivateFeature;
import com.squaredesk.provider.Rate;
import com.squaredesk.renter.QueryBuilder;
import com.squaredesk.renter.Renter;
import com.squaredesk.renter.ReservationObject;
import com.squaredesk.renter.Scheduler;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willpassidomo
 */
public class RenterServiceImplementation {
   
    RenterDTO updateRenter(String authToken, RenterDTO renterDTO) {
        Renter renter = Renter.getRenterByID(renterDTO.getRenterID());
        renter.setContact(this.contactFromDTO(renterDTO.getContact()));
        renter.setName(this.nameFromDTO(renterDTO.getName()));
        User user = Renter.getRenterByID(renterDTO.getRenterID()).getUser();
        renter.setPayPalAccount(this.paypalFromDTO(renterDTO.getPaypal(), user.getUserID()));
        renter.setProfilePicture(this.imageFromDTO(renterDTO.getImage()));
        return this.copyRenterToDTO(renter);
    }
    
    void DeleteRenter(String authToken, UUID renterID) {
        Renter renter = Renter.getRenterByID(renterID);
        User user = renter.getUser();
        user.deleteRenterProfile();
        renter.delete();
    }
    
    RenterDTO getRenter(String authToken, UUID renterID) {
        return this.copyRenterToDTO(Renter.getRenterByID(renterID));
    }
    
    List<RenterDTO> getRenterList(String authToken) {
        List<RenterDTO> renters = new ArrayList<RenterDTO>();
        for (Renter renter: Renter.getRenters()) {
            renters.add(this.copyRenterToDTO(renter));
        }
        return renters;
    }
    
    List<OfficeSpace> submitQuery(String authToken, UUID renterID, QueryDTO queryDTO) throws IllegalCoordinateValue {
        QueryBuilder query = Renter.getRenterByID(renterID).newQuery();
        if(queryDTO.getCommonFeatures()!= null) {
            for (CommonFeature cf: queryDTO.getCommonFeatures())
            query.addTCondition(cf);
        }
        if(queryDTO.getPrivateFeatures()!= null) {
            for (PrivateFeature pf: queryDTO.getPrivateFeatures())
            query.addTCondition(pf);
        }
        if(queryDTO.getFacilityType()!=null) {
            query.addTCondition(queryDTO.getFacilityType());
        }
        if(queryDTO.getCoordinates()!= null) {
            query.addTCondition(queryDTO.getCoordinates());
        }
        if(queryDTO.getMinimumRating()!= null) {
            query.addTCondition(queryDTO.getMinimumRating());
        }
        if(queryDTO.getStartDate() != null) {
            query.setStartDate(queryDTO.getStartDate());
        }
        if(queryDTO.getEndDate() != null) {
            query.setEndDate(queryDTO.getEndDate());
        }
        
        List<OfficeSpace> officeSpaces = new ArrayList<OfficeSpace>();
        for (OfficeSpace officeSpace: query.executeTQuery()) {
            officeSpaces.add(officeSpace);
        }
        return officeSpaces;
    }
    
    ReservationDTO makeReservation(String authToken, UUID RenterID, ReservationDTO reservation) {
        OfficeSpace officespace = OfficeSpace.getOfficeSpaceByID(reservation.getOfficeSpaceID());
        Renter renter = Renter.getRenterByID(RenterID);
        ReservationObject reservationObj = Scheduler.getInstance().makeReservation(officespace, renter, reservation.getStartDate(), reservation.getEndDate(), officespace.getRateByID(reservation.getRateID()));
        return this.copyReservationToDTO(reservationObj);
    }
    
    RenterDTO createRenter(String authToken, RenterDTO renter, UUID userID) {
        Name name = nameFromDTO(renter.getName());
        Contact contact = contactFromDTO(renter.getContact());
        Imagec image = imageFromDTO(renter.getImage());
        PayPalAccount paypal = paypalFromDTO(renter.getPaypal(), userID);
        Renter newRenter = Renter.newRenter(name, contact, image, paypal, User.getUserByID(userID));
        return this.copyRenterToDTO(newRenter);
    }
    
    RatingDTO submitRating(String authToken, RatingDTO rating) {
        Rateable rated = OfficeSpace.getOfficeSpaceByID(rating.getRated());
        System.out.println(rating.getRated());
        System.out.println("rated"+OfficeSpace.getOfficeSpaceByID(rating.getRated()));
        
        Rating newRating = Rating.newRating(rating.getStars(), rating.getComments(), Renter.getRenterByID(rating.getRater()), rated, ReservationObject.getReservationByID(rating.getReservationID()));
        rated.addRating(newRating);
        return copyRatingToDTO(newRating);
    }
        
    
    void removeRating(String authToken, UUID ratingID) {
        
    }
    
    List<FacilityType> getFacitliyTypes() {
        return FacilityType.getFacilityTypes();
    }
    
    List<CommonFeature> getCommonFeatures() {
        return CommonFeature.getCommonFeatures();
    }
    
    List<PrivateFeature> getPrivateFeatures() {
        return PrivateFeature.getPrivateFeatures();
    }
    
    RenterDTO copyRenterToDTO(Renter renter) {
        NameDTO name = copyNameToDTO(renter.getName());
        ImageDTO image = copyImageToDTO(renter.getProfilePicture());
        ContactDTO contact = copyContactToDTO(renter.getContact());
        PayPalDTO payPal = copyPayPalToDTO(renter.getPayPalAccount());
        return new RenterDTO(name,image,contact, copyPayPalToDTO(renter.getPayPalAccount()), renter.getGUID());
    }
    
   NameDTO copyNameToDTO(Name name) {
       NameDTO nameDTO = new NameDTO(name.getFirstName(),name.getLastName());
       if(name.getMiddle()!= null) {
           nameDTO.setMiddleName(name.getMiddle());
       }
       if(name.getPrefix() != null) {
           nameDTO.setPrefix(name.getPrefix());
       }
       return nameDTO;
   }
   
   ImageDTO copyImageToDTO(Imagec image) {
        try {
            ImageDTO imageDTO = new ImageDTO(image.getName(),image.getReferance(),image.getUri());
            return imageDTO;
        } catch (URISyntaxException ex) {
            Logger.getLogger(RenterServiceImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
   }
   
   ContactDTO copyContactToDTO(Contact contact) {
       ContactDTO contactDTO = new ContactDTO(contact.getCellPhone());
       if(contact.getHomePhone()!=null) {
           contactDTO.setHomePhone(contact.getHomePhone());
       }
       if(contact.getWorkPhone()!=null) {
           contactDTO.setWorkPhone(contact.getWorkPhone());
       }
       return contactDTO;
   }
   
   RatingDTO copyRatingToDTO(Rating rating) {
       RatingDTO ratin = new RatingDTO(rating.getStars(), rating.getComment(), rating.getObjectRated().getID(), rating.getReservationObject().getReservationID());
       ratin.setDateSubmitted(rating.getDateSubmitted());
       return ratin;
       }
   
   PayPalDTO copyPayPalToDTO(PayPalAccount ppa) {
       PayPalDTO ppDTO = new PayPalDTO(ppa.getAccountIDNumber(),ppa.getAccountEmailAddress());
       return ppDTO;
    }    
   
   ReservationDTO copyReservationToDTO(ReservationObject reservation) {
       ReservationDTO resDTO = new ReservationDTO(reservation.getReservationID(),reservation.getStartDate(),reservation.getEndDate(),reservation.getOfficeSpace().getGUID(),reservation.getDateMade(),reservation.getPaymentStatus(),reservation.getBalance(), reservation.getRenter().getGUID());
       return resDTO;
   }
   
   Name nameFromDTO(NameDTO named) {
       Name name = Name.newName(named.getFirstName(), named.getLastName());
       if(named.getMiddleName()!=null) {
           name.setMiddle(named.getMiddleName());
       }
       if(named.getPrefix()!=null) {
           name.setPrefix(named.getPrefix());
       }
       return name;
   }
   
   Contact contactFromDTO(ContactDTO contactD) {
       Contact contact = Contact.newContact(contactD.getCellPhone());
       if (contactD.getHomePhone()!=null) {
           contact.setHomePhone(contactD.getHomePhone());
       }
       if (contactD.getWorkPhone()!=null) {
           contact.setWorkPhone(contactD.getWorkPhone());
       }
       return contact;
   }
   
   PayPalAccount paypalFromDTO(PayPalDTO ppD, UUID userID) {
       User user = User.getUserByID(userID);
       PayPalAccount ppa = PayPalAccount.newPayPalAccount(ppD.getPpIDNumber(), ppD.getEmailAddress(), user);
       return ppa;
   }
   
   Imagec imageFromDTO(ImageDTO imageD) {
       return Imagec.newImage(imageD.getName(), imageD.getDescription(), imageD.getUri());
   }
       
       
    
    
    
    
    
    
    
    
    
    
    
    
    private RenterServiceImplementation() {
    }
    
    public static RenterServiceImplementation getInstance() {
        return RenterServiceImplementaitonHolder.INSTANCE;
    }
        
    private static class RenterServiceImplementaitonHolder {

        private static final RenterServiceImplementation INSTANCE = new RenterServiceImplementation();
    }
}

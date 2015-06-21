/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.renter;

import com.squaredesk.common.Contact;
import com.squaredesk.common.Imagec;
import com.squaredesk.common.Name;
import com.squaredesk.common.PayPalAccount;
import com.squaredesk.common.Rateable;
import com.squaredesk.common.Rater;
import com.squaredesk.common.Rating;
import com.squaredesk.common.User;
import com.squaredesk.provider.IllegalValueError;
import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.OfficeSpaceProvider;
import com.squaredesk.provider.Rate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willpassidomo
 */
public class Renter implements Rateable, Rater{
    private final UUID GUID;
    private User user;
    private Name name;
    private Contact contact;
    private Imagec profilePicture;
    private PayPalAccount payPalAccount;
    private Scheduler scheduler;
    private final List<ReservationObject> reservations;
    private final List<Rating> ratingsIRecieve;
    private final List<Rating> ratingsIGive;
    
    private static final List<Renter> renters = new ArrayList<Renter>();
    private static final Map<UUID, Renter> rentersByID = new HashMap<UUID, Renter>();
    
    private Renter (Name name, Contact contact, Imagec profilePicture, PayPalAccount payPalAccount, User user) {
        this.user = user;
        this.name = name;
        this.contact = contact;
        this.profilePicture = profilePicture;
        this.payPalAccount = payPalAccount;
        this.GUID = UUID.randomUUID();
        this.scheduler = Scheduler.getInstance();
        ratingsIRecieve = new ArrayList<Rating>();
        ratingsIGive = new ArrayList<Rating>();
        reservations = new ArrayList<ReservationObject>();
        rentersByID.put(GUID, this);
        Rateable.addToRegistry(this);
    }
    /**
     * public constructor for new Renter instances.
     * @param name a Name object identifying the renters legal name
     * @param contact a Contact object containing the contact information for the renter
     * @param profilePicture an Imagec object to be used as a publicly viewable picture of the renter
     * @return 
     */
    public static Renter newRenter(Name name, Contact contact, Imagec profilePicture, PayPalAccount payPalAccount, User user) {
        Renter newRenter = new Renter(name, contact, profilePicture, payPalAccount, user);
        renters.add(newRenter);
        return newRenter;
        
    }
    
    /**
     * calls Scheduler.makeReservation to check for availability and to create a ReservationObject
     * @param officeSpace the OfficeSpace to reserve
     * @param startDate the date which the reservation starts
     * @param endDate the date which the reservation ends
     * @param rentalRate the rate which the Renter will be charged for the duration of the reservation
     * @return the ReservationObject instance which acts as a "reciept" of the reservation. Reservation my also be canceled through the ReservationObject
     */
    
    public ReservationObject reserveOfficeSpace(OfficeSpace officeSpace, Date startDate, Date endDate, Rate rentalRate) {
        ReservationObject newReservation = null;
        if (officeSpace.getRates().contains(rentalRate)) {            
            newReservation = scheduler.makeReservation(officeSpace, this, startDate, endDate, rentalRate);
        }
        return newReservation;
    }

    /**
     * tests for equality amongst Renter objects based on GUID
     * @param o the Renter to be compared against
     * @return true if the Renter has the same GUID, false if the Renter has a different GUID
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Renter) {
            Renter r = (Renter) o;
            if(r.getGUID().equals(this.getGUID())) {
                return true;
            }
        }
        return false;
    }
    
    public static Renter getRenterByID(UUID renterID) {
        return rentersByID.get(renterID);
    }

    /**
     * @return the GUID
     */
    public UUID getGUID() {
        return GUID;
    }

    /**
     * @return the name
     */
    public Name getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * @return the ratings this Renter has submitted
     */
    public List<Rating> getRatingsISubmitted() {
        return ratingsIGive;
    }
    
    /**
     * 
     * @return the rating that have been submitted about this renter 
     */
    public List<Rating> getRatingsIRecieved() {
        return ratingsIRecieve;
    }

        /**
     * method called by cscie97.asn2.sharedesk.common.Rating in order to register Rating Object with the submiter's account
     * @param rating a rating object by the renter for an OfficeSpace or OfficeSpaceProvider
     */
    public void submitRating(Rating rating) {
        ratingsIGive.add(rating);
    }
 
     /**
     * method called by cscie97.asn2.sharedesk.common.Rating in order to register Rating Object with the submiter's account
     * @param rating a rating object by an OfficeSpaceProvider for the renter
     */
    
    public void recieveRating (Rating rating) {
        ratingsIRecieve.add(rating);
    }
    
    /**
     * @return the contact
     */
    public Contact getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    /**
     * @return the profilePicture
     */
    public Imagec getProfilePicture() {
        return profilePicture;
    }

    /**
     * @param profilePicture the profilePicture to set
     */
    public void setProfilePicture(Imagec profilePicture) {
        this.profilePicture = profilePicture;
    }

    /**
     * @return the reservations
     */
    public List<ReservationObject> getReservations() {
        return reservations;
    }
    
    /**
     * adds the reservation to the Renter's list of reservations
     * @param reservation the reservation to add
     */
    public void addReservation(ReservationObject reservation) {
        this.reservations.add(reservation);
    }

    /**
     * @return the renters
     */
    public static List<Renter> getRenters() {
        return renters;
    }

    /**
     * @return the payPalAccount
     */
    public PayPalAccount getPayPalAccount() {
        return payPalAccount;
    }

    /**
     * @param payPalAccount the payPalAccount to set
     */
    public void setPayPalAccount(PayPalAccount payPalAccount) {
        this.payPalAccount = payPalAccount;
    }

    public void addRating(Rating rating) {
        ratingsIGive.add(rating);
    }

    public void removeRatingIGave(Rating rating) {
        if (ratingsIGive.contains(rating)) {
            ratingsIGive.remove(rating);
        }
    }

    public double getAverageRating() {
        double sum = 0.0;
        int count = 0;
        for (Rating rating: ratingsIRecieve) {
            sum += rating.getStars();
            count++;
        }
        return sum / count;            
    }
    
    public QueryBuilder newQuery() {
        try {
            return QueryBuilder.newQueryBuilder(this);
        } catch (RenterDoesNotExistError ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public String toString() {
        return "Renter GUID: " +this.GUID +
                "\n\tname: " + this.name +
                "\n\tcontact: " +this.contact +
                "\n\tprofile picture" + this.profilePicture +
                "\n\tpaypalAccount" +this.payPalAccount + "\n";
    }

    @Override
    public void addRating(int stars, String comment, Rater rater, ReservationObject reservationObject) {
        this.ratingsIRecieve.add(Rating.newRating(stars, comment, rater, this, reservationObject));
    }

    @Override
    public void removeRating(Rating rating) {
        this.ratingsIGive.remove(rating);
    }

    @Override
    public Renter getByID(UUID id) {
        return Renter.getRenterByID(id);
    }
   
    @Override
    public UUID getID() {
        return this.GUID;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    public void delete() {
        renters.remove(this);
        rentersByID.remove(this.GUID);
    }
}

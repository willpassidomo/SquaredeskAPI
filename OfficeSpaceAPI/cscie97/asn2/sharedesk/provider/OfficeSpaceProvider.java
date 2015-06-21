/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author willpassidomo
 */
public class OfficeSpaceProvider implements Rateable {

    /**
     * @return the NextGUID
     */
    private int GUID;
    private Contact contact;
    private Name name;
    private PayPalAccount payPalAccount;
    private List<Rating> ratings = new ArrayList<Rating>();
    private Imagec picture;
    private List<OfficeSpace> officeSpaces= new ArrayList<OfficeSpace>();
    private static int NextGUID = 0;
    private static List<OfficeSpaceProvider> officeSpaceProviders = new ArrayList<OfficeSpaceProvider>();

    private OfficeSpaceProvider() {
        NextGUID++;
    }
    
     /**
     * in accordance with the Builder Pattern, this method returns an OfficeSpaceBuider
     * Object. the OfficeSpaceProviderBuilder object must use the 'set' methods to provide 
     * values for at least the minimally required fields. The minimally required fields 
     * are: name, payPalAccount, contact and 1 image
     * after the minimally required fields are satisfied, Client may instantiate an OfficeSpaceProvider object
     * by calling the build() method on the OfficeSpaceProviderBuilder Object
     * @return 
     */
    
    public static OfficeSpaceProviderBuilder newOfficeSpaceProvider() {
       return new OfficeSpaceProviderBuilder();
    }
    
        public static int getNextGUID() {
        return NextGUID;
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


    /**
     * returns a Collection containing all of the OfficeSpace Object's ratings
     * @return the ratings
     */
    public List<Rating> getRatings() {
        return ratings;
    }
    
    /**
     *  this method is for submitting a rating for an OfficeSpace by passing a Rating object
     * @param rating
     */
    public void addRating(Rating rating) {
        this.ratings.add(rating);
    }
    
    /**
     * this method is for submtting a rating for an OfficeSpace by providing
     * the neccessary parameters to instantiate a Rating Object, which is then
     * added to this OfficeSpace Object's ratings Collection
     * @param stars the number of 'stars', 1 - 5 which the user wishes to express
     * @param comment feedback from a User on the OfficeSpace
     * @param user  the User object which is submitting the 
     */
    
    public void addRating(int stars, String comment, User user) {
        try {
            Rating rating = Rating.newRating(stars, comment, user, this);
        } catch (IllegalValueError ex) {
            System.out.print("'stars' value must be between 1 and 5");
        }
        
    }
    
    /**
     * a method which will only be allowed to be accessed by administrators which allows
     * a review to be removed from an OfficeSpace object by passing its referance
     * @param rating referance to the rating which is to be removed
     */
    public void removeRating(Rating rating) {
        this.ratings.remove(rating);
    }
    
    /**
     * returns a straight average of the 'stars' given by each rating for
     * the OfficeSpace Object
     * @return a double which represents the average number of stars in the Ratings Collection
     */
    public double getAverageRating() {
        int sum = 0;
        int count = 0;
        for (Rating rating: this.ratings) {
            sum += rating.getStars();
            count++;
        }
        return sum / count;
    }
    
        public static Imagec addNewImage (String name, String description, String uri){   
        URI newUrl = null;
        try {
            newUrl = new URI(uri);
        } catch (URISyntaxException ex) {
            System.out.println("Improper URL formate");
        }
        Imagec image = Imagec.newImage(name, description, newUrl);
        return image;
    }
    /**
     * @return the GUID
     */
    public int getGUID() {
        return GUID;
    }



    /**
     * @return the picture
     */
    public Imagec getPicture() {
        return picture;
    }

    /**
     * @param picture the picture to set
     */
    public void setPicture(Imagec picture) {
        this.picture = picture;
    }
    
    public static Name newName (String first, String last) {
        Name name = Name.newName(first, last);
        return name;
    }
    
    public static Contact newContact (String phoneNumber) {
        Contact contact = Contact.newContact(phoneNumber);
        return contact;
    }
    
    public static PayPalAccount newPayPalAccount(String AccountIDNumber, String accountEmailAddress) {
        return PayPalAccount.newPayPalAccount(AccountIDNumber, accountEmailAddress);   
    }
    
    public static class OfficeSpaceProviderBuilder {
        private Contact contactBuild;
        private Name nameBuild;
        private PayPalAccount payPalAccountBuild;
        private Imagec pictureBuild;

        
        private OfficeSpaceProviderBuilder() {
            
        }
        /**
         * @return the contactBuild
         */
        public Contact getContactBuild() {
            return contactBuild;
        }

        /**
         * @param contactBuild the contactBuild to set
         */
        public void setContactBuild(Contact contactBuild) {
            this.contactBuild = contactBuild;
        }

        /**
         * @return the nameBuild
         */
        public Name getNameBuild() {
            return nameBuild;
        }

        /**
         * @param nameBuild the nameBuild to set
         */
        public void setNameBuild(Name nameBuild) {
            this.nameBuild = nameBuild;
        }

        /**
         * @return the payPalAccountBuild
         */
        public PayPalAccount getPayPalAccountBuild() {
            return payPalAccountBuild;
        }

        /**
         * @param payPalAccountBuild the payPalAccountBuild to set
         */
        public void setPayPalAccountBuild(PayPalAccount payPalAccountBuild) {
            this.payPalAccountBuild = payPalAccountBuild;
        }

        /**
         * @return the pictureBuild
         */
        public Imagec getPictureBuild() {
            return pictureBuild;
        }

        /**
         * @param pictureBuild the pictureBuild to set
         */
        public void setPictureBuild(Imagec pictureBuild) {
            this.pictureBuild = pictureBuild;
        }
        
        public OfficeSpaceProvider build() throws IncompleteInstantiationError {
            OfficeSpaceProvider newOfficeProvider;
            if (    this.contactBuild != null &&
                    this.nameBuild != null &&
                    this.payPalAccountBuild != null &&
                    this.pictureBuild != null) {
                newOfficeProvider = new OfficeSpaceProvider();
                newOfficeProvider.setContact(this.contactBuild);
                newOfficeProvider.setName(this.nameBuild);
                newOfficeProvider.setPayPalAccount(this.payPalAccountBuild);
                newOfficeProvider.setPicture(this.pictureBuild);
                officeSpaceProviders.add(newOfficeProvider);
            } 
            else {
                throw new IncompleteInstantiationError();
            }
            return newOfficeProvider;
                 
        }
        
    }
    

}

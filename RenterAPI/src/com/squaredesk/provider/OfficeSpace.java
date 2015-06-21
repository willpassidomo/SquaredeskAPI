

package com.squaredesk.provider;

import com.squaredesk.common.AverageStars;
import com.squaredesk.common.Imagec;
import com.squaredesk.common.Rateable;
import com.squaredesk.common.Rater;
import com.squaredesk.common.Rating;
import com.squaredesk.common.Searchable;
import com.squaredesk.common.User;
import com.squaredesk.renter.ReservationObject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *OfficeSpace Class represents the physical Office Space being offered by an Office Space Provider. 
 * Since there are a large number of fields, some required and some not, this class employs
 * the Builder patter with its nested Class OfficeSpaceBuilder. In order to instantiate
 * an instance of OfficeSpace, first instantiate an instance of OfficeSpaceBuilder and
 * use the 'setters' to provide values for the minimally required fields. the minimally 
 * required fields are: capacity, 1 Rate in rates, address, officeOwner, facilityType and 1 mage in images
 * after the minimally required fields are satisfied, Client may instantiate an OfficeSpace object
 * by calling the build() method on the OfficeSpaceBuilder Object
 * @author willpassidomo
 */
public class OfficeSpace implements Rateable, Comparable {


    private OfficeSpaceProvider officeOwner;
    private final UUID GUID;
    private Capacity capacity;
    private FacilityType facilityType;
    private List<Rate> rates;
    private List<PrivateFeature> privateFeatures;
    private List<CommonFeature> commonFeatures;
    private final List<Rating> ratingsIRecieve; 
    private Address address;
    private Coordinate coordinates;
    private List<Imagec> images;
    private static final List<OfficeSpace> officeSpaces = new ArrayList<OfficeSpace>();
    private static final Map<UUID, OfficeSpace> officeSpacesByID = new HashMap<UUID, OfficeSpace>();
    private final List<ReservationObject> reservations;
    private List<Searchable> tripleables;
    private AverageStars averageStars = new AverageStars(this);
    private Map<UUID, Rate> ratesByID = new HashMap<UUID, Rate>();
    
    private OfficeSpace () {
        this.GUID = UUID.randomUUID();
        rates = new ArrayList<Rate>();
        ratingsIRecieve = new ArrayList<Rating>(); 
        reservations = new ArrayList<ReservationObject>();
        privateFeatures = new ArrayList<PrivateFeature>();
        commonFeatures = new ArrayList<CommonFeature>();
        images = new ArrayList<Imagec>();
        tripleables = new ArrayList<Searchable>();
        officeSpacesByID.put(this.GUID, this);
        Rateable.addToRegistry(this);
    }
    
    /**
     * in accordance with the Builder Pattern, this method returns an OfficeSpaceBuider
     * Object. the OfficeSpaceBuilder object must use the 'set' methods to provide 
     * values for at least the minimally required fields. The minimally required fields 
     * are: capacity, 1 Rate in rates, address, officeOwner, facilityType and 1 mage in images
     * after the minimally required fields are satisfied, Client may instantiate an OfficeSpace object
     * by calling the build() method on the OfficeSpaceBuilder Object
     * @return 
     */
    
    public static OfficeSpaceBuilder newOfficeSpace (OfficeSpaceProvider officeSpaceProvider) {
        return new OfficeSpaceBuilder(officeSpaceProvider);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof OfficeSpace) {
            OfficeSpace os = (OfficeSpace) o;
            return os.GUID.equals(this.GUID);
        }
        return false;
    }
    
    @Override
    public UUID getID() {
        return this.GUID;
    }
    
    public static OfficeSpace getOfficeSpaceByID(UUID officeSpaceID) {
        return officeSpacesByID.get(officeSpaceID);
    }
    
    /**
     * returns the Capacity Object, which includes the fields for maximum number 
     * of people allowed in OfficeSpace, the total Square feet of the work area of 
     * OfficeSpace and the total number of work stations in OfficeSpace
     * @return the capacity
     */
    public Capacity getCapacity() {
        return capacity;
    }

    /**
     * sets the Capacity of the OfficeSpace by accepting a Capacity instance as a 
     * parameter
     * @param capacity the capacity to set
     */
    public void setCapacity(Capacity capacity) {
        this.capacity = capacity;
    }
    
    /**
     *initialized a new Capacity Object based on the parameters provided and sets the
     * OfficeSpace's capacity as the newly instantiated Capacity Object
     * @param sqFt the number of square feet available in the Office Space, must be a whole number
     * @param peopleLimit the maximum allowed people in the Office Space
     * @param workSpaces the number of work stations within the Office Space
     */
    public void setCapactiy(int sqFt, int peopleLimit, int workSpaces) {
        this.capacity = new Capacity(sqFt, peopleLimit, workSpaces);        
    }

    /**
     * returns the classification of the Facility. At the time of writing this document
     * the choices where Home or Garage
     * @return the facilityType
     */
    public FacilityType getFacilityType() {
        return facilityType;
    }

    /**
     * Use this method in combination with getFacilityTypes(). Assign getFacitiltyTypes() 
     * return List<FacilityType> to an identifier, then pass one of the FacilityType Objects in the array as a paramter in this function
     * sets the classification of the Facility. At the time of writing this, the
     * choices where Home of Garage, but more choices may be added by Users with adminitrative access.
     * 
     * 
     * @param facilityType the facilityType to set
     */
    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    /**
     * returns an instance of LinkedList containing  all of the possible FacilityTypes
     * @return a list of FacilityType objects
     */
    
    public List<FacilityType> getFacicilityTypes() {
        return FacilityType.getFacilityTypes();
    }
    
    /**
     * returns a List of the different (or single) Rates offered for this OfficeSpace
     * @return the rates
     */
    public List<Rate> getRates() {
        return rates;
    }

    /**
     * Allows you to set a Collection of rates for an OfficeSpace
     * @param rates the rates to set
     */
    public void setRates(List<Rate> rates) {
        for (Rate rate: rates) {
            ratesByID.put(rate.getRateID(), rate);
        }
        this.rates = rates;
    }
    
    /**
     *Takes a Rate Object as a parameter and adds it to the Collection of Rates for
     * the OfficeSpace object
     * @param rate
     */
    public void addRate(Rate rate) {
        ratesByID.put(rate.getRateID(), rate);
        this.rates.add(rate);
    }
    
    public Rate getRateByID(UUID rateID) {
        for (Rate rate: this.rates) {
            ratesByID.put(rate.getRateID(), rate);
        }
        return ratesByID.get(rateID);
    }
    
    /**
     *Removes the Rate which matches the passed parameter
     * @param rate a rate to remove
     */
    public void removeRate(Rate rate) {
        this.rates.remove(rate);
    }
    

    /**
     * returns a List of all approved PrivateFeatures
     * @return the privateFeatures
     */
    public List<PrivateFeature> getPrivateFeatures() {
        return privateFeatures;
    }

    /**
     * Allows the user to set the private Features with a Collection
     * @param privateFeatures the privateFeatures to set
     */
    public void setPrivateFeatures(List<PrivateFeature> privateFeatures) {
        this.privateFeatures = privateFeatures;
    }
    
    /**
     *use this method in combination with getPrivateFeature(). Assign getPrivateFeaturesTypes() 
     * return List<PrivateFeatures> to an identifier, then pass one of the PrivateFeature Objects in the array as a paramter in this function
     * 
     * @param privateFeature
     */
    public void addPrivateFeature(PrivateFeature privateFeature) {
        this.privateFeatures.add(privateFeature);
    }
    
    /**
     * use this method in combination with getPrivateFeature(). Assign getPrivateFeaturesTypes() 
     * return List<PrivateFeatures> to an identifier, then pass one of the PrivateFeature Objects in the array as a paramter in this function
     * 
     * removes the Private Feature which matches the one passed as a parameter
     * @param privateFeature the Private Feature object to remove
     */
    public void removePrivateFeature(PrivateFeature privateFeature) {
        this.privateFeatures.remove(privateFeature);
    }
    
    /**
     *  removes all Private Features from OfficeSpace's PrivateFeature Collection
     */
    public void removeAllPrivateFeatures() {
        for (PrivateFeature privateFeature: this.privateFeatures) {
            this.privateFeatures.remove(privateFeature);
        }
    }

    /**
     * returns a List<CommonFeature> of all the CommonFeature obects associated with this Office Space
     * this method is useful for obtaining referances to already instantiated CommonFeature objects
     * @return the commonFeatures
     */
    public List<CommonFeature> getCommonFeatures() {
        return commonFeatures;
    }


    /**
     *  Allows the user to set the Common Features with a Collection
     * @param commonFeatures the commonFeatures to set as the OfficeSpace Object's collection
     */
    public void setCommonFeatures(List<CommonFeature> commonFeatures) {
        this.commonFeatures = commonFeatures;
    }
    
    /**
     *use this method in combination with getCommonFeatures(). Assign getCommonFeaturesTypes() 
     * return List<CommonFeatures> to an identifier, then pass one of the CommonFeature Objects in the array as a paramter in this function
     * 
     * 
     * @param commonFeature the CommonFeature object to be added to the OfficeSpace Collection
     */
    public void addCommonFeature(CommonFeature commonFeature) {
        this.commonFeatures.add(commonFeature);
    }
    
    /**
     * use this method in combination with getCommonFeatures(). Assign getCommonFeaturesTypes() 
     * return List<CommonFeatures> to an identifier, then pass one of the CommonFeature Objects in the array as a paramter in this function
     * 
     * @param commonFeature
     */
    public void removeCommonFeature(CommonFeature commonFeature) {
        this.commonFeatures.remove(commonFeature);
    }
    
    /**
     *
     */
    public void removeAllCommonFeatures() {
        for (CommonFeature commonFeature:this.commonFeatures) {
            this.commonFeatures.remove(commonFeature);
        }
    }
    
    /**
     * Allows for the creation of a new Address object by returning a new
     * AddressBuilder object in accordance with the Builder Pattern. In order to
     * instantiate a new Address object, you must set the minimally required fields 
     * for the AddressBuilder using the "set" methods. Minimally required fields are;
     * street1, zip, countryCode, state, and city. You may set more fields using the 
     * "set" methods but you must do at least those 5. Once they are set, call the AddressBuilder's
     * .build() method which will return an Address object.
     * @return Address.AddressBuilder object which can return an Address Object with .build()
     */
    
    public Address.AddressBuilder newAddress() {
        return new Address.AddressBuilder();
    }

    /**
     * returns the Address object which contains the street address fields as well
     * as optionally coordinate fields and contact information
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * @return the GUID
     */
    public UUID getGUID() {
        return GUID;
    }

    /**
     * @return the image
     */
    public List<Imagec> getImage() {
        return getImages();
    }
    
    /**
     * @param images a List of the Images of the OfficeSpace
     */
    
    public void setImages (List<Imagec> images) {
        this.images = images;
    }
    
    /**
     *
     * @param image
     */
    public void addImage(Imagec image) {
        this.getImages().add(image);
    }
    
    /**
     *  checks to make sure there is more than 1 image left so that OfficeSpace
     * object does not fall below minimally requirements (must have at least one Image)
     * @param image image to be removed from OfficeSpace Object's images
     */
    public void removeImage(Imagec image) {
        if(this.getImages().size() > 1) {
            this.getImages().remove(image);
        }
    }


    /**
     * @return the officeOwner
     */
    public OfficeSpaceProvider getOfficeOwner() {
        return officeOwner;
    }

    /**
     * @param officeOwner the officeOwner to set
     */
    public void setOfficeOwner(OfficeSpaceProvider officeOwner) {
        this.officeOwner = officeOwner;
    }

    /**
     * returns a Collection containing all of the OfficeSpace Object's ratings
     * @return the ratings
     */
    public List<Rating> getRatingsIRecieved() {
        return ratingsIRecieve;
    }
    
    /**
     *  this method is for submitting a rating for an OfficeSpace by passing a Rating object
     * @param rating
     */
    public void addRatingForThisOfficeSpace(Rating rating) {
        this.ratingsIRecieve.add(rating);
    }
    
    /**
     * this method is for submtting a rating for an OfficeSpace by providing
     * the neccessary parameters to instantiate a Rating Object, which is then
     * added to this OfficeSpace Object's ratings Collection
     * @param stars the number of 'stars', 1 - 5 which the user wishes to express
     * @param comment feedback from a User on the OfficeSpace
     * @param rater the rater who is adding the ratting for this OfficeSpace
     * @param reservation the reservation object for confirming the Rater did experience the OfficeSpace
     */
    
    @Override
    public void addRating(int stars, String comment, Rater rater, ReservationObject reservation) {
        Rating rating = Rating.newRating(stars, comment, rater, this, reservation);
        ratingsIRecieve.add(rating);
        
    }
    
    public AverageStars getAverageStars() {
        return averageStars;
    }
    
    /**
     * returns a straight average of the 'stars' given by each rating for
     * the OfficeSpace Object
     * @return a double which represents the average number of stars in the Ratings Collection
     */
    public double getAverageRating() {
        double sum = 0.0;
        int count = 0;
        for (Rating rating: this.ratingsIRecieve) {
            sum += rating.getStars();
            count++;
        }
        return sum / count;
    }

    
     /**
     * a static method which acts as a database for all instantiated OfficeSpace Objects.
     * When a new OfficeSpace instance is created through the build() method on an
     * OfficeSpaceBuilder instance, it is added to this list
     * @return all instances of OfficeSpace
     */

    
    public static List<OfficeSpace> getOfficeSpaces() {
        return officeSpaces;
    }
    
    public static Capacity newCapacity(int sqFt, int peopleLimit, int workSpaces) {
        Capacity capacity = null;
        try {
            capacity = Capacity.newCapacity(sqFt, peopleLimit, workSpaces);
        } catch (IllegalValueError ex) {
            System.out.println("Illegal value");
        }
        return capacity;
        }
    
    public static Rate addNewRate (int period, double rate) {
        return Rate.newRate(period, rate);
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
     * @return the reservations
     */
    public List<ReservationObject> getReservations() {
        return reservations;
    }
    
    /**
     * adds reservation to list of reservation
     * @param reservation the reservation to add
     */
    public void addReservation(ReservationObject reservation) {
        this.reservations.add(reservation);
    }

    @Override
    public void addRating(Rating rating) {
        ratingsIRecieve.add(rating);
    }

    /**
     * @return the coordinates
     */
    public Coordinate getCoordinates() {
        return coordinates;
    }

    /**
     * @param coordinates the coordinates to set
     */
    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * @return the images
     */
    public List<Imagec> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "OfficeSpace GUID: "+this.GUID +
                "\n\tOwner: "+this.officeOwner.getName()+
                "\n\tfacility type: " +this.facilityType +
                "\n\tcapacity: "+this.capacity+
                "\n\tcoordinates: " +this.coordinates+
                "\n\taddress: \n"+this.address+
                "\n\trates: "+this.rates+
                "\n\tcommon features: " +this.commonFeatures+
                "\n\tprivate features: " +this.privateFeatures+"\n";
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof OfficeSpace) {
            OfficeSpace os = (OfficeSpace) o;
            return os.GUID.compareTo(this.GUID);
        }
        return 1;
    }

    /**
     *
     */
    public static class OfficeSpaceBuilder {
        private OfficeSpaceProvider builderOfficeOwner;
        private Capacity builderCapacity;
        private FacilityType builderFacilityType;
        private List<Rate> builderRates = new ArrayList<Rate>();
        private List<PrivateFeature> builderPrivateFeatures = new ArrayList<PrivateFeature>();
        private List<CommonFeature> builderCommonFeatures = new ArrayList<CommonFeature>();
        private Address builderAddress;
        private List<Imagec> builderImage = new ArrayList<Imagec>();
        private Coordinate builderCoordinates;
        
        /**
         * 
         */
        private OfficeSpaceBuilder(OfficeSpaceProvider officeSpaceProvider) {
            this.builderOfficeOwner = officeSpaceProvider;
        }

        /**
         * see OfficeSpace.getCapacity()
         * @return the builderCapacity
         */
        public Capacity getBuilderCapacity() {
            return builderCapacity;
        }

        /**
         * see OfficeSpace.setCapacity()
         * @param builderCapacity the builderCapacity to set
         */
        public void setBuilderCapacity(Capacity builderCapacity) {
            this.builderCapacity = builderCapacity;
        }
        
        public Coordinate getBuilderCoordinates() {
            return this.builderCoordinates;
        }
        
        public void setBuilderCoordinate(Coordinate coordinates) {
            this.builderCoordinates = coordinates;
        }

        /**
         * see OfficeSpace.getFacilityType()
         * @return the builderFacilityType
         */
        public FacilityType getBuilderFacilityType() {
            return builderFacilityType;
        }

        /**
         * see OfficeSpace.setFacilityType()
         * @param builderFacilityType the builderFacilityType to set
         */
        public void setBuilderFacilityType(FacilityType builderFacilityType) {
            this.builderFacilityType = builderFacilityType;
        }

        /**
         * see OfficeSpace.getRates()
         * @return the builderRates
         */
        public List<Rate> getBuilderRates() {
            return builderRates;
        }

        /**
         * see OfficeSpace.setRates()
         * @param builderRates the builderRates to set
         */
        public void setBuilderRates(List<Rate> builderRates) {
            this.builderRates = builderRates;
        }
        
        /**
         * see OfficeSpace.addRates();
         * @param rate
         */
        public void addBuilderRate(Rate rate) {
            this.builderRates.add(rate);
        }
        
        /**
         * see OfficeSpace.removeRates()
         * @param rate
         */
        public void removeBuilderRates(Rate rate) {
            this.builderRates.remove(rate);
        }
        
        /**
         * see OfficeSpace.removeAllRates()
         */
        public void removeAllBuilderRates() {
            for (Rate rate: this.builderRates) {
                this.builderRates.remove(rate);
            }
        }

        /**
         * see OfficeSpace.getPrivateFeatures()
         * @return the builderPrivateFeatures
         */
        public List<PrivateFeature> getBuilderPrivateFeatures() {
            return builderPrivateFeatures;
        }

        /**
         * see OfficeSpace.setPrivateFeatures()
         * @param builderPrivateFeatures the builderPrivateFeatures to set
         */
        public void setBuilderPrivateFeatures(List<PrivateFeature> builderPrivateFeatures) {
            this.builderPrivateFeatures = builderPrivateFeatures;
        }
        
        /**
         * see OfficeSpace.addPrivateFeature()
         * @param privateFeature
         */
        public void addBuilderPrivateFeature(PrivateFeature privateFeature) {
            this.builderPrivateFeatures.add(privateFeature);
        }
        
        /**
         * see OfficeSpace.removePrivateFeature()
         * @param privateFeature
         */
        public void removeBuilderPrivateFeature(PrivateFeature privateFeature) {
            this.builderPrivateFeatures.remove(privateFeature);
        }
        
        /**
         *see OfficeSpace.removeAllPrivateFeatures()
         */
        public void removeAllBuilderPrivateFeatures() {
            for (PrivateFeature privateFeature: this.builderPrivateFeatures) {
                this.builderPrivateFeatures.remove(privateFeature);
            }
        }

        /**
         * see OfficeSpace.getCommonFeatures()
         * @return the builderCommonFeatures
         */
        public List<CommonFeature> getBuilderCommonFeatures() {
            return builderCommonFeatures;
        }

        /**
         * see OfficeSpace.setCommonFeature()
         * @param builderCommonFeatures the builderCommonFeatures to set
         */
        public void setBuilderCommonFeatures(List<CommonFeature> builderCommonFeatures) {
            this.builderCommonFeatures = builderCommonFeatures;
        }

        /**
         *see OfficeSpace.addCommonFeature()
         * @param commonFeature
         */
        public void addBuilderCommonFeature(CommonFeature commonFeature) {
            this.builderCommonFeatures.add(commonFeature);
        }
        
        /**
         * see OfficeSpace.removeCommonFeature()
         * @param commonFeature
         */
        public void removeBuilderCommonFeature(CommonFeature commonFeature) {
            this.builderCommonFeatures.remove(commonFeature);
        }
        
        /**
         *see OfficeSpace.removeAllCommonFeatures()
         */
        public void removeAllBuilderCommonFeatures() {
            for (CommonFeature commonFeature: this.builderCommonFeatures) {
                this.builderCommonFeatures.remove(commonFeature);
            }
        }
        /**
         * see OfficeSpace.getAddress
         * @return the builderAddress
         */
        public Address getBuilderAddress() {
            return builderAddress;
        }

        /**
         * see OfficeSpace.setAddress
         * @param builderAddress the builderAddress to set
         */
        public void setBuilderAddress(Address builderAddress) {
            this.builderAddress = builderAddress;
        }

         /**
        * Allows for the creation of a new Address object by returning a new
        * AddressBuilder object in accordance with the Builder Pattern. In order to
        * instantiate a new Address object, you must set the minimally required fields 
        * for the AddressBuilder using the "set" methods. Minimally required fields are;
        * street1, zip, countryCode, state, and city. You may set more fields using the 
        * "set" methods but you must do at least those 5. Once they are set, call the AddressBuilder's
        * .build() method which will return an Address object.
        * @return Address.AddressBuilder object which can return an Address Object with .build()
        */
    
        public Address.AddressBuilder newAddress() {
            return new Address.AddressBuilder();
        }
           
        /**
         * see OfficeSpace.getImage()
         * @return the builderImage
         */
        public List<Imagec> getBuilderImage() {
            return builderImage;
        }

        /**
         * see OfficeSpace.setImage()
         * @param builderImage the builderImage to set
         */
        public void setBuilderImage(List<Imagec> builderImage) {
            this.builderImage = builderImage;
        }
        
        /**
         * see OfficeSpace.addImage()
         * @param image
         */
        public void addBuilderImage(Imagec image) {
            this.builderImage.add(image);
        }
        
        /**
         * see OfficeSpace.removeImage()
         * @param image
         */
        public void removeBuilderImage(Imagec image) {
            this.builderImage.remove(image);
        }
        
        /**
         * see OfficeSpace.removeAllImages()
         */
        public void removeAllBuilderImages() {
            for(Imagec image: this.builderImage) {
                this.builderImage.remove(image);
            }
        }

        /**
         * see OfficeSpace.getOfficeOwner()
         * @return the builderOfficeOwner
         */
        public OfficeSpaceProvider getBuilderOfficeOwner() {
            return builderOfficeOwner;
        }

        /**
         * see OfficeSpace.setOfficeOwner()
         * @param builderOfficeOwner the builderOfficeOwner to set
         */
        public void setBuilderOfficeOwner(OfficeSpaceProvider builderOfficeOwner)  {
            this.builderOfficeOwner = builderOfficeOwner;
        }
        
        /**
         *see OfficeSpace.newOfficeSpace();
         * @return
         * @throws IncompleteInstantiationError
         */
        public OfficeSpace build() throws IncompleteInstantiationError {
            OfficeSpace newOfficeSpace;
            if (    this.builderAddress != null &&
                    this.builderCoordinates != null &&
                    this.builderCapacity != null &&
                    this.builderFacilityType != null &&
                    !this.builderImage.isEmpty() &&
                    !this.builderRates.isEmpty() &&
                    this.builderOfficeOwner != null) 
            {
                newOfficeSpace = new OfficeSpace();
                newOfficeSpace.setAddress(this.builderAddress);
                newOfficeSpace.setCapacity(this.builderCapacity);
                newOfficeSpace.setCommonFeatures(this.builderCommonFeatures);
                newOfficeSpace.setCoordinates(this.builderCoordinates);
                newOfficeSpace.setFacilityType(this.builderFacilityType);
                newOfficeSpace.setOfficeOwner(this.builderOfficeOwner);
                newOfficeSpace.setPrivateFeatures(this.builderPrivateFeatures);
                newOfficeSpace.setRates(this.builderRates);
                
                
                getOfficeSpaces().add(newOfficeSpace);
            }
            else {
                throw new IncompleteInstantiationError();
            }
            return newOfficeSpace;
        }
        
    }
            
}

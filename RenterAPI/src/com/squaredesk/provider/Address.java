/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn3com.squaredesk.provider;

import com.squaredesk.common.Contact;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author willpassidomo
 */
public class Address {
    private static List<Address> addresses = new ArrayList<Address>();
    private Contact contact;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private int zip;
    private String countyCode;
    private Coordinate coordinate;
    private String poBox;
    private String aptNumber;

    private Address(String street1, String city, String state, int zip, String countryCode){
        this.street1 = street1;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.countyCode = countryCode;
    }
    
    private Address() {
        
    }
    
    public static Address newAddress(String street1, String city, String state, int zip, String countryCode) {
        Address address = new Address(street1, city, state, zip, countryCode);
        addresses.add(address);
        return address;
    }
    
    public AddressBuilder newAddressBuilder() {
        return new AddressBuilder();
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
     * @return the street1
     */
    public String getStreet1() {
        return street1;
    }

    /**
     * @param street1 the street1 to set
     */
    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    /**
     * @return the street2
     */
    public String getStreet2() {
        return street2;
    }

    /**
     * @param street2 the street2 to set
     */
    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the State
     */
    public String getState() {
        return state;
    }

    /**
     * @param State the State to set
     */
    public void setState(String State) {
        this.state = State;
    }

    /**
     * @return the zip
     */
    public int getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(int zip) {
        this.zip = zip;
    }

    /**
     * @return the countyCode
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * @param countyCode the countyCode to set
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate to set
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return the poBox
     */
    public String getPoBox() {
        return poBox;
    }

    /**
     * @param poBox the poBox to set
     */
    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    /**
     * @return the aptNumber
     */
    public String getAptNumber() {
        return aptNumber;
    }

    /**
     * @param aptNumber the aptNumber to set
     */
    public void setAptNumber(String aptNumber) {
        this.aptNumber = aptNumber;
    }
     
     @Override
     public String toString() {
         return  "\tstreet1:" + this.street1
                 + "\n\tcity: " + this.city 
                 + "\n\tState: " + this.state;
                 
     }

    public static class AddressBuilder {
        private Contact contact;
        private String street1;
        private String street2;
        private String city;
        private String State;
        private int zip;
        private String countyCode;
        private Coordinate coordinate;
        private String poBox;
        private String aptNumber;
        
        public AddressBuilder() {
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
         * @return the street1
         */
        public String getStreet1() {
            return street1;
        }

        /**
         * @param street1 the street1 to set
         */
        public void setStreet1(String street1) {
            this.street1 = street1;
        }

        /**
         * @return the street2
         */
        public String getStreet2() {
            return street2;
        }

        /**
         * @param street2 the street2 to set
         */
        public void setStreet2(String street2) {
            this.street2 = street2;
        }

        /**
         * @return the city
         */
        public String getCity() {
            return city;
        }

        /**
         * @param city the city to set
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         * @return the State
         */
        public String getState() {
            return State;
        }

        /**
         * @param State the State to set
         */
        public void setState(String State) {
            this.State = State;
        }

        /**
         * @return the zip
         */
        public int getZip() {
            return zip;
        }

        /**
         * @param zip the zip to set
         */
        public void setZip(int zip) {
            this.zip = zip;
        }

        /**
         * @return the countyCode
         */
        public String getCountyCode() {
            return countyCode;
        }

        /**
         * @param countyCode the countyCode to set
         */
        public void setCountyCode(String countyCode) {
            this.countyCode = countyCode;
        }

        /**
         * @return the coordinate
         */
        public Coordinate getCoordinate() {
            return coordinate;
        }

        /**
         * @param coordinate the coordinate to set
         */
        public void setCoordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
        }

        /**
         * @return the poBox
         */
        public String getPoBox() {
            return poBox;
        }

        /**
         * @param poBox the poBox to set
         */
        public void setPoBox(String poBox) {
            this.poBox = poBox;
        }

        /**
         * @return the aptNumber
         */
        public String getAptNumber() {
            return aptNumber;
        }

        /**
         * @param aptNumber the aptNumber to set
         */
        public void setAptNumber(String aptNumber) {
            this.aptNumber = aptNumber;
        }
        
        public Address build() throws IncompleteInstantiationError {
            Address newAddress;
            if (this.State != null &&
                    this.city != null &&
                    this.countyCode != null &&
                    this.street1 != null &&
                    this.zip < 100000 &&
                    this.zip > 10000) 
            {
                newAddress = new Address();
                newAddress.setAptNumber(this.aptNumber);
                newAddress.setCity(this.city);
                newAddress.setContact(this.contact);
                newAddress.setCoordinate(this.coordinate);
                newAddress.setCountyCode(this.countyCode);
                newAddress.setPoBox(this.poBox);
                newAddress.setState(this.State);
                newAddress.setStreet1(this.street1);
                newAddress.setStreet2(this.street2);                
            } else {
                throw new IncompleteInstantiationError();
            }
             return newAddress;       
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author willpassidomo
 */
class FacilityType implements Comparable {
    private static List<FacilityType> facilityTypes = new LinkedList<FacilityType>();
    private String facilityTypeIdentifier;

    /**
     * @return the facilityTypeIdentifier
     */
    private FacilityType(String facilityTypeIdentifier) {
        this.facilityTypeIdentifier = facilityTypeIdentifier;
    }
    
    /**
     * Factory Method for creating new instances of FacilitityType, takes only a String 
     * identifier as a parameter. Checks to see if instance with the same identifier 
     * is already present in facilityTypes then returns new FacilityType item
     * @param identifier
     * @return 
     */
    
    public static FacilityType newFacilityType(String identifier) {
        FacilityType newFType = new FacilityType(identifier);
        if (facilityTypes.contains(newFType)) {
        } else {
        facilityTypes.add(newFType);}
        return newFType;
    }
    
    /**
     * returns the identifier for the FacilityType Object
     * @return identifier for the FacilityType
     */
    
    public String getFacilityTypeIdentifier() {
        return facilityTypeIdentifier;
    }

    /**
     * @param facilityTypeIdentifier the facilityTypeIdentifier to set
     */
    public void setFacilityTypeIdentifier(String facilityTypeIdentifier) {
        this.facilityTypeIdentifier = facilityTypeIdentifier;
    }
    
    public static List<FacilityType> getFacilityTypes() {
        return facilityTypes;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof FacilityType) {
            FacilityType temp = (FacilityType) o;
            return this.facilityTypeIdentifier.compareTo(temp.facilityTypeIdentifier);
        } else {
            return -1;
        }    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof FacilityType) {
            FacilityType temp = (FacilityType) o;
            return this.facilityTypeIdentifier.equals(temp.facilityTypeIdentifier);
        } else {
            return false;
        }
    }
    
}

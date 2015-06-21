/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import cscie97.asn3.squaredesk.common.AverageStars;
import cscie97.asn3.squaredesk.provider.CommonFeature;
import cscie97.asn3.squaredesk.provider.Coordinate;
import cscie97.asn3.squaredesk.provider.FacilityType;
import cscie97.asn3.squaredesk.provider.PrivateFeature;
import cscie97.asn3.squaredesk.provider.Rate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class QueryDTO {
    private Coordinate coordinates;
    private FacilityType facilityType;
    private AverageStars minimumRating;
    private Date startDate;
    private Date endDate;
    private List<CommonFeature> commonFeatures;
    private List<PrivateFeature> privateFeatures;
    private UUID officeSpaceID;
    private List<Rate> rates;
 
    public QueryDTO () {
        commonFeatures = new ArrayList<CommonFeature>();
        privateFeatures = new ArrayList<PrivateFeature>();
        rates = new ArrayList<Rate>();
    }

    /**
     * @return the location
     */
    public Coordinate getCoordinates() {
        return coordinates;
    }

    /**
     * @return the facilityType
     */
    public FacilityType getFacilityType() {
        return facilityType;
    }

    /**
     * @return the minimumRating
     */
    public AverageStars getMinimumRating() {
        return minimumRating;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the commonFeatures
     */
    public List<CommonFeature> getCommonFeatures() {
        return commonFeatures;
    }

    /**
     * @return the privateFeatures
     */
    public List<PrivateFeature> getPrivateFeatures() {
        return privateFeatures;
    }

    /**
     * @return the officeSpaceID
     */
    public UUID getOfficeSpaceID() {
        return officeSpaceID;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Coordinate coordinate) {
        this.coordinates = coordinate;
    }

    /**
     * @param facilityType the facilityType to set
     */
    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    /**
     * @param minimumRating the minimumRating to set
     */
    public void setMinimumRating(AverageStars minimumRating) {
        this.minimumRating = minimumRating;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        if (Validate.checkDateOrder(startDate, this.endDate)){
            this.startDate = startDate;}
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        if (Validate.checkDateOrder(this.startDate, endDate)) {
            this.endDate = endDate;}
    }

    /**
     * @param commonFeatures the commonFeatures to set
     */
    public void setCommonFeatures(List<CommonFeature> commonFeatures) {
        this.commonFeatures = commonFeatures;
    }
    
    public void addCommonFeature(CommonFeature commonFeature) {
        commonFeatures.add(commonFeature);
    }
    
    public void removeCommonFeature(CommonFeature commonFeature) {
        commonFeatures.remove(commonFeature);
    }

    /**
     * @param privateFeatures the privateFeatures to set
     */
    public void setPrivateFeatures(List<PrivateFeature> privateFeatures) {
        this.privateFeatures = privateFeatures;
    }
    
    public void addPrivateFeature(PrivateFeature privateFeature) {
        this.privateFeatures.add(privateFeature);
    }
    
    public void removePrivateFeature(PrivateFeature privateFeature) {
        this.privateFeatures.remove(privateFeature);
    }

    /**
     * @return the rates
     */
    public List<Rate> getRates() {
        return rates;
    }
            
}

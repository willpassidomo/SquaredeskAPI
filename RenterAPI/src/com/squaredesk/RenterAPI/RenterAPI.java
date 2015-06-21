/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import com.squaredesk.provider.CommonFeature;
import com.squaredesk.provider.FacilityType;
import com.squaredesk.provider.IllegalCoordinateValue;
import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.PrivateFeature;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class RenterAPI {
    private RenterServiceImplementation rsi = RenterServiceImplementation.getInstance();
    
    public RenterDTO updateRenter(String authToken, RenterDTO renter) {
        return rsi.updateRenter(authToken, renter);
    }
    
    public void DeleteRenter(String authToken, UUID renterID) {
        rsi.DeleteRenter(authToken, renterID);
    }
    
    public RenterDTO getRenter(String authToken, UUID renterID) {
        return rsi.getRenter(authToken, renterID);
    }
    
    public List<RenterDTO> getRenterList(String authToken) {
        return rsi.getRenterList(authToken);
    }
    
    public List<OfficeSpace> submitQuery(String authToken, UUID renterID, QueryDTO query) throws IllegalCoordinateValue {
        return rsi.submitQuery(authToken, renterID, query);
    }
    
    public ReservationDTO makeReservation(String authToken, UUID RenterID, ReservationDTO reservation) {
        return rsi.makeReservation(authToken, RenterID, reservation);
    }
    
    public RenterDTO createRenter(String authToken, RenterDTO renter, UUID userID) {
        return rsi.createRenter(authToken, renter, userID);
    }
    
    public RatingDTO submitRating(String authToken, RatingDTO rating) {
        return rsi.submitRating(authToken, rating);
    }
    
    public void removeRating(String authToken, UUID ratingID) {
        rsi.removeRating(authToken, ratingID);
    }
    
    public List<FacilityType> getFacitliyTypes() {
        return rsi.getFacitliyTypes();
    }
    
    public List<CommonFeature> getCommonFeatures() {
        return rsi.getCommonFeatures();
    }
    
    public List<PrivateFeature> getPrivateFeatures() {
        return rsi.getPrivateFeatures();
    }
}

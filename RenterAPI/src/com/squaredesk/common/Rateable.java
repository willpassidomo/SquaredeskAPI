/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.common;

import com.squaredesk.common.Rating;
import com.squaredesk.provider.IllegalValueError;
import com.squaredesk.renter.ReservationObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public interface Rateable {
    static Map<UUID, Rateable> ratables = new HashMap<UUID, Rateable>();
       
    public List<Rating> getRatingsIRecieved();
    
    public void addRating(Rating rating);
    
    public void addRating(int stars, String comment, Rater rater, ReservationObject reservationObject);
    
    public double getAverageRating();
    
    public static void addToRegistry(Rateable rateable) {
        ratables.put(rateable.getID(), rateable);
    }
    
    public static Rateable newMinimumRatingCriteria(int rating) {
        return new Rateable() {

            @Override
            public List<Rating> getRatingsIRecieved() {
                return null;
            }

            @Override
            public void addRating(Rating rating) {
            }

            @Override
            public void addRating(int stars, String comment, Rater rater, ReservationObject reservationObject) {
            }

            @Override
            public double getAverageRating() {
                return (double) rating;
            }

            @Override
            public UUID getID() {
                return null;
            }
        };
    }
    
    public static Rateable getByID(UUID id) {
        return ratables.get(id);
    }
    
    public UUID getID();
    
}

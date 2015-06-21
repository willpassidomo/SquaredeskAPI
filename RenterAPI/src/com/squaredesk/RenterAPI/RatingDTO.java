/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class RatingDTO {
    private int stars;
    private String comments;
    private UUID rater;
    private UUID rated;
    private Date dateSubmitted;
    private UUID reservationID;
    
    
    
    public RatingDTO (int stars, String comments, UUID toBeRated, UUID reservationID) {
        this.setStars((int)Validate.testRange("stars", stars, 1, 5));
        this.setComments(comments);
        this.rated = toBeRated;
        this.reservationID = reservationID;
    }
    
    


    /**
     * @return the stars
     */
    public int getStars() {
        return stars;
    }

    /**
     * @param stars the stars to set
     */
    public void setStars(int stars) {
        this.stars = stars;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * @return the officeSpaceID
     */
    public UUID getRated() {
        return rated;
    }

    /**
     * @return the officeSpaceProviderID
     */
    public UUID getRater() {
        return rater;
    }
    
    public Date getDateSubmitted() {
        return this.dateSubmitted;
    }
    
    public UUID getReservationID() {
        return this.reservationID;
    }
    
    void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }
    
    void setReservationID(UUID reservationID) {
        this.reservationID = reservationID;
    }
    
}

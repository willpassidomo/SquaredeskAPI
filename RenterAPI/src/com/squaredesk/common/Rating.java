/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.common;

import com.squaredesk.provider.IllegalValueError;
import com.squaredesk.renter.ReservationObject;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class Rating{
    private Rater rater;
    private String comment;
    //must be between 1 and 5;
    private int stars;
    private Date dateSubmitted;
    private Rateable objectRated;
    private UUID ratingID;
    private ReservationObject reservationObject;
    
    private static Map<UUID, Rating> ratingsByID = new HashMap<UUID, Rating>();
    
    private Rating(int stars, String comment, Rater rater,Rateable objectRated, ReservationObject reservation) {
        dateSubmitted = new Date();
        this.stars = stars;
        this.comment = comment;
        this.rater = rater;
        this.objectRated = objectRated;
        this.ratingID = UUID.randomUUID();
        this.reservationObject = reservationObject;
        ratingsByID.put(ratingID, this);
    }
    
    public static Rating newRating(int stars, String comment, Rater rater, Rateable objectRated, ReservationObject reservation) {
        Validate1.testRange("stars", stars, 1, 5);
        Validate1.checkNotNull("rater", rater);
        Validate1.checkNotNull("objectReted", objectRated);
        Validate1.checkNotNull("reservation", reservation);
        return new Rating(stars, comment, rater,  objectRated, reservation);
    }

    public static Rating getRatingByID(UUID ratingID) {
        return ratingsByID.get(ratingID);
    }
    
    /**
     * @return the user
     */
    public Rater getRater() {
        return rater;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the dateSubmitted
     */
    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * @return the stars
     */
    public int getStars() {
        return stars;
    }

    /**
     * @return the objectRated
     */
    public Rateable getObjectRated() {
        return objectRated;
    }

    /**
     * @return the reservationObject
     */
    public ReservationObject getReservationObject() {
        return reservationObject;
    }

    /**
     * @return the ratingID
     */
    public UUID getRatingID() {
        return ratingID;
    }

}

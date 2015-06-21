/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

import java.util.Date;

/**
 *
 * @author willpassidomo
 */
class Rating {
    private User user;
    private String comment;
    //must be between 1 and 5;
    private int stars;
    private Date dateSubmitted;
    private Rateable objectRated;
    
    private Rating(int stars, String comment, User user,Rateable objectRated) {
        dateSubmitted = new Date();
        this.stars = stars;
        this.comment = comment;
        this.user = user;
        this.objectRated = objectRated;
    }
    
    public static Rating newRating(int stars, String comment, User user, Rateable objectRated) throws IllegalValueError {
        if (stars > 5 || stars < 1) {
        throw new IllegalValueError();
        } else {
        return new Rating(stars,comment,user,objectRated);
        }
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
     * @param dateSubmitted the dateSubmitted to set
     */
    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
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
     * @return the objectRated
     */
    public Rateable getObjectRated() {
        return objectRated;
    }

    /**
     * @param objectRated the objectRated to set
     */
    public void setObjectRated(Rateable objectRated) {
        this.objectRated = objectRated;
    }
}

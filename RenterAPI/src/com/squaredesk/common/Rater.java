/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.common;

import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public interface Rater {    
    public void removeRating(Rating rating);
    
    public Rater getByID(UUID id);
    
    public UUID getID();
}

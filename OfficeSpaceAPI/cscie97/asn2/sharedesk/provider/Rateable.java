/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

import cscie97.asn2.sharedesk.provider.Rating;
import java.util.List;

/**
 *
 * @author willpassidomo
 */
public interface Rateable {
       
    public List<Rating> getRatings();
    
    public void addRating(Rating rating);
    
    public void removeRating(Rating rating);
    
    public double getAverageRating();
}

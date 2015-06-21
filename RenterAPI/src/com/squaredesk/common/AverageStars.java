package com.squaredesk.common;

import com.squaredesk.provider.OfficeSpace;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author willpassidomo
 */
public class AverageStars implements Searchable {
        double averageStars;
        Rateable rateable;
        
        public AverageStars(Rateable rateable){
            this.rateable = rateable;
        }

        @Override
        public String getPredicate() {
            return "has_rating_of";
        }

        @Override
        public String getObject() {
            return rateable.getAverageRating()+"";
        }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.provider;

import com.squaredesk.common.Searchable;

/**
 *
 * @author willpassidomo
 */
    public class Coordinate implements Searchable, Comparable{
        private double xCord;
        private double yCord;
        
    private Coordinate(double xCord, double yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
        }
    
    public static Coordinate newCoordinate(double xCord, double yCord) throws IllegalCoordinateValue {
        Coordinate newCord = null;
        if(xCord > 180 || xCord < -180 || yCord > 90 || yCord < -90) {
            throw new IllegalCoordinateValue();
        } else {
            newCord = new Coordinate(xCord, yCord);
        }
        return newCord;
        
    }
    
    @Override
    public String toString() {
        return "["+this.xCord+","+this.yCord+"]";
    }

    @Override
    public String getPredicate() {
        return "has_coordinates_of";
    }

    @Override
    public String getObject() {
        String x = (int)xCord +"";
        String y = (int)yCord +"";
        return "["+x+", "+y+"]";
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Coordinate) {
            Coordinate cord = (Coordinate) o;
            if (cord.xCord > this.xCord) {
                return 1;
            }
            if (cord.xCord < this.xCord) {
                return -1;
            }
            if (cord.xCord == cord.yCord) {
                return 0;
            }
        }
        return 1;       
    }
}
    
    

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.provider;

/**
 *
 * @author willpassidomo
 */
public abstract class Feature {
    String name;
    
    public Feature(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.provider;

import com.squaredesk.common.Searchable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author willpassidomo
 */
public class PrivateFeature extends Feature implements Comparable, Searchable{
    private static List<PrivateFeature> privateFeatures = new ArrayList<PrivateFeature>();
    
    private PrivateFeature(String name) {
        super(name);
    }
    
    public static PrivateFeature newPrivateFeature(String name) {
        PrivateFeature newFeature = new PrivateFeature(name);
        privateFeatures.add(newFeature);
        return newFeature;
    }
    
    public static List<PrivateFeature> getPrivateFeatures() {
        return privateFeatures;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof PrivateFeature) {
            PrivateFeature cf = (PrivateFeature) o;
            return this.name.compareToIgnoreCase(cf.name);
        }
        return 1;
    }

    @Override
    public String getPredicate() {
        return "has_private_feature";
    }

    @Override
    public String getObject() {
        return this.name;
    }
    
    
}

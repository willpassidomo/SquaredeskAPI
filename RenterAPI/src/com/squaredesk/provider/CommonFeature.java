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
public class CommonFeature extends Feature implements Comparable, Searchable{
    private static List<CommonFeature> commonFeatures = new ArrayList<CommonFeature>();

    private CommonFeature(String name) {
        super(name);
    }
    
    public static CommonFeature newCommonFeature (String name) {
        CommonFeature newFeature = new CommonFeature(name);
        commonFeatures.add(newFeature);
        return newFeature;
    }
    
    public static List<CommonFeature> getCommonFeatures() {
        return commonFeatures;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof CommonFeature) {
            CommonFeature cf = (CommonFeature) o;
            return this.name.compareToIgnoreCase(cf.name);
        }
        return 1;
    }

    @Override
    public String getPredicate() {
        return "has_common_feature";
    }

    @Override
    public String getObject() {
        return this.name;
    }
    
    
    
    
}

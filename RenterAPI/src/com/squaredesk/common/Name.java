/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author willpassidomo
 */
public class Name {
    private String firstName;
    private String lastName;
    private String middle;
    private String prefix;
    private static List<Name> names = new ArrayList<Name>();

    /**
     * @return the firstName
     */
    private Name (String first, String last) {
        this.firstName = first;
        this.lastName = last;
    }
    
    public static Name newName (String first, String last) {
        Name name = new Name(first, last);
        names.add(name);
        return name;
    }
    
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the middle
     */
    public String getMiddle() {
        return middle;
    }

    /**
     * @param middle the middle to set
     */
    public void setMiddle(String middle) {
        this.middle = middle;
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix the prefix to set
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    
    @Override
    public String toString() {
        return this.firstName +" " + this.lastName;
    }
}

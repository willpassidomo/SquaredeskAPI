/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import java.util.Arrays;

/**
 *
 * @author willpassidomo
 */
public class NameDTO {
    private String firstName = "";
    private String lastName = "";
    private String middleName = "";
    private String prefix = "";
    
    public static final String LEGALPREFIX_Mr = "Mr.";
    public static final String LEGALPREFIX_Mrs = "Mrs.";
    public static final String LEGALPREFIX_Miss = "Miss";
    public static final String LEGALPREFIX_Sir = "Sir";
    public static final String LEGALPREFIX_Dr = "Dr.";
    
    private static final String[] legalPrefixs = {LEGALPREFIX_Mr, LEGALPREFIX_Mrs, LEGALPREFIX_Miss, LEGALPREFIX_Sir, LEGALPREFIX_Dr, ""};
    
    public NameDTO(String firstName, String lastName) {
        this.firstName = Validate.checkAllChars("firstName", firstName);
        this.lastName = Validate.checkAllChars("lastName", lastName);
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = Validate.checkAllChars("firstName", firstName);
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
        this.lastName = Validate.checkAllChars("lastName", lastName);
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = Validate.checkAllChars("middleName", middleName);
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
        boolean test = false;
        for (int i =0; i<legalPrefixs.length; i++) {
            if(prefix.trim().equals(legalPrefixs[i])) {
                test = true;
                this.prefix = prefix;
            }
        }
        if (!test){
            throw new IllegalArgumentException("PREFIX "+prefix+ " IS NOT A LEGAL PREFIX. legal prefixs \""+Arrays.toString(legalPrefixs)+"\"");
            
        }    
    }
    
    public static String[] getLegalPrefixs() {
        return legalPrefixs;
    }
    
    @Override 
    public String toString() {
        return this.prefix +" "+ this.firstName +" "+ this.middleName+ " "+this.lastName;
    }
    
}

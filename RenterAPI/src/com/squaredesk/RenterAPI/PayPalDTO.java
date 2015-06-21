/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

/**
 *
 * @author willpassidomo
 */
public class PayPalDTO {
    private String ppIDNumber;
    private String emailAddress;
    
    public PayPalDTO(String ppIDNumber, String emailAddress) {
        this.ppIDNumber = ppIDNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * @return the ppIDNumber
     */
    public String getPpIDNumber() {
        return ppIDNumber;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param ppIDNumber the ppIDNumber to set
     */
    public void setPpIDNumber(String ppIDNumber) {
        this.ppIDNumber = ppIDNumber;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return " account ID: " + this.ppIDNumber +
                "\n\t email: " +this.emailAddress;
    }
}

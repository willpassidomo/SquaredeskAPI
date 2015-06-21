/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.common;

import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.OfficeSpaceProvider;
import com.squaredesk.renter.ReservationObject;
import java.text.DecimalFormat;

/**
 *
 * @author willpassidomo
 */
public class PayPalAccount {
    private final User user;
    private String accountIDNumber;
    private String accountEmailAddress;
    
    /**
     * a dummy variable in place to the PayPalAPI
     */
    private double balance;

    private PayPalAccount (String accountIDNumber, String accountEmailAddress, User user) {
        this.accountEmailAddress = accountEmailAddress;
        this.accountIDNumber = accountIDNumber;
        this.user = user;
    }
    
    public static PayPalAccount newPayPalAccount(String accountIDNumber, String accountEmailAddress, User user) {
        PayPalAccount payPalAccount = new PayPalAccount(accountIDNumber, accountEmailAddress, user);
        return payPalAccount;
    }
    
    /**
     * @return the accountIDNumber
     */
    public String getAccountIDNumber() {
        return accountIDNumber;
    }

    /**
     * @param accountIDNumber the accountIDNumber to set
     */
    public void setAccountIDNumber(String accountIDNumber) {
        this.accountIDNumber = accountIDNumber;
    }

    /**
     * @return the accountEmailAddress
     */
    public String getAccountEmailAddress() {
        return accountEmailAddress;
    }

    /**
     * @param accountEmailAddress the accountEmailAddress to set
     */
    public void setAccountEmailAddress(String accountEmailAddress) {
        this.accountEmailAddress = accountEmailAddress;
    }
    
    public void addMoney(double amount) {
        this.balance += amount;
    }
    
    /**
     * removeMoney is used for making payments from Renter's to OfficeSpaceProviders for reserved office spaces, for refunding Renters from OfficeSpaceProviders, and for OfficeSpaceProviders to remove money (i.e cash out) from their PayPalAccount
     * If the User (regardless if they are using their Renter profile or OfficeSpaceProvider profile) has an OfficeSpaceProfile, they will not be allowed to withdraw from their account money which has been paid for reservations which may still be canceled (haven't started yet. This ensures that if a
     * Renter decides to cancel a reservation, they are gauranteed that the OfficeSpaceProvider will be able to cover it
     * @param amount
     * @return 
     */
    public boolean removeMoney(double amount) {
        double amountPending = 0.0;
        if (user.getOSproviderProfile() != null) {
            for (OfficeSpace officespace: user.getOSproviderProfile().getOfficeSpaces()) {
                for(ReservationObject reservation: officespace.getReservations()) {
                    amountPending += reservation.getPaid();
                }
            }
        }
        if (balance >= amount + amountPending) {
        this.balance -= amount;
        return true;
        }else{
            return false;
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public boolean makePayment(double amount, PayPalAccount paymentTo) {
        if (this.removeMoney(amount)) {
            paymentTo.addMoney(amount);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return " account ID: " + this.accountIDNumber +
                "\n\t email: " +this.accountEmailAddress +
                "\n\t user: " + this.user;
    }
}

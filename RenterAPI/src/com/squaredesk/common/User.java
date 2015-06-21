/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.common;

import com.squaredesk.provider.IncompleteInstantiationError;
import com.squaredesk.provider.OfficeSpaceProvider;
import com.squaredesk.provider.OfficeSpaceProvider.OfficeSpaceProviderBuilder;
import com.squaredesk.renter.Renter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willpassidomo
 */
public class User {
    private OfficeSpaceProvider OSproviderProfile;
    private Renter renterProfile ;
    private String userName;
    private UUID userID;
    
    private static List<User> users = new ArrayList<User>();
    private static List<String> userNames = new ArrayList<String>();
    private static Map<UUID, User> usersByID = new HashMap<UUID, User>();
    
    public User (String userName) throws NonUniqueUserNameError {
        if (userNames.contains(userName)) {
            throw new NonUniqueUserNameError();
        }
        userNames.add(userName);
        this.userName = userName;
        this.userID = UUID.randomUUID();
        users.add(this);
        usersByID.put(userID, this);
    }
    
    public Renter newRenterProfile (Name name, Contact contact, Imagec profilePicture, PayPalAccount payPalAccount) {
        if (renterProfile != null) {
            return renterProfile; 
            }
            Renter newRenter = Renter.newRenter(name, contact, profilePicture, payPalAccount, this);
            renterProfile = newRenter;
            return newRenter;
        }
                  
    
    
    public OfficeSpaceProvider newOSproviderProfile (Name name, Contact conact, Imagec profilePicture, PayPalAccount payPalAccount) {
        if (OSproviderProfile == null) {
            OfficeSpaceProviderBuilder newOSP = OfficeSpaceProvider.newOfficeSpaceProvider(this);
            newOSP.setNameBuild(name);
            newOSP.setContactBuild(conact);
            newOSP.setPayPalAccountBuild(payPalAccount);
            newOSP.setPictureBuild(profilePicture);
            try {
                OSproviderProfile = newOSP.build();
            } catch (IncompleteInstantiationError ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return OSproviderProfile;
    }
    
    //public OfficeSpaceProvider newOfficeSpaceProviderProfile()
    public OfficeSpaceProviderBuilder newOSproviderProfileBuilder() {
        if (OSproviderProfile == null) {
            return OfficeSpaceProvider.newOfficeSpaceProvider(this);
        }
        return null;
    }
    
    public OfficeSpaceProvider getOSproviderProfile() {
        return this.OSproviderProfile;
    }
    
    public void deleteOfficeSpaceProviderProfile() {
        OSproviderProfile = null;
    }
    
    public void deleteRenterProfile() {
        renterProfile = null;
    }
    
    public Renter getRenterProfile() {
        return this.renterProfile;
    }
    
    public PayPalAccount newPayPalAccount(String accountNumber, String email) {
        PayPalAccount newPPA = PayPalAccount.newPayPalAccount(accountNumber, email, this);
        return newPPA;
    }
    
    public static User getUserByID(UUID userID) {
        return usersByID.get(userID);
    }
    
    public UUID getUserID() {
        return this.userID;
    }
    
    @Override
    public String toString() {
        return this.userName;
    }
}

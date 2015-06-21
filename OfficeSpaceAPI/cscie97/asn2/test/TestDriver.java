/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.test;

import cscie97.asn2.sharedesk.provider.*;
import cscie97.asn2.sharedesk.provider.Address;
import cscie97.asn2.sharedesk.provider.Address.AddressBuilder;
import cscie97.asn2.sharedesk.provider.IncompleteInstantiationError;
import cscie97.asn2.sharedesk.provider.OfficeSpace;
import cscie97.asn2.sharedesk.provider.OfficeSpace.OfficeSpaceBuilder;
import cscie97.asn2.sharedesk.provider.OfficeSpaceProvider.OfficeSpaceProviderBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willpassidomo
 */
public class TestDriver {
    
    public static void main (String[] args) {
    
    OfficeSpaceProviderBuilder osProvB = OfficeSpaceProvider.newOfficeSpaceProvider();
    osProvB.setNameBuild(OfficeSpaceProvider.newName("will", "passidomo"));
    osProvB.setContactBuild(OfficeSpaceProvider.newContact("912-234-4324"));
    osProvB.setPayPalAccountBuild(OfficeSpaceProvider.newPayPalAccount("123456789", "fake@email.com"));
    osProvB.setPictureBuild(OfficeSpaceProvider.addNewImage("Me", "A crazy picture of me","http://img3.wikia.nocookie.net/__cb20100724183918/spongebob/images/3/33/Patrick_Star.svg" ));
    OfficeSpaceProvider officeSpaceProvider = null;
    try {
           officeSpaceProvider = osProvB.build();
        } catch (IncompleteInstantiationError ex) {
           System.out.println("Office Space Provider is missing requirements");
        }
    
    OfficeSpaceBuilder osb = OfficeSpace.newOfficeSpace();
    
    AddressBuilder adrsB = osb.newAddress();
    adrsB.setCity("Katonah");
    adrsB.setState("New York");
    adrsB.setStreet1("24 Millhurst rd");
    adrsB.setZip(10549);
    adrsB.setState("NY");
    adrsB.setCountyCode("USA");
    Address newAddress = null;
        try {
            newAddress = adrsB.build();
        } catch (IncompleteInstantiationError ex) {
            System.out.println("Address is missing requirements");
        }
    
    OfficeSpace.newFacilityType("Home");

    osb.addBuilderImage(OfficeSpace.addNewImage("Me", "A crazy picture of me","http://img3.wikia.nocookie.net/__cb20100724183918/spongebob/images/3/33/Patrick_Star.svg" ));
    osb.setBuilderAddress(newAddress);
    osb.setBuilderFacilityType(OfficeSpace.getFacilityTypes());
    osb.setBuilderOfficeOwner(officeSpaceProvider);
    osb.addBuilderRate(OfficeSpace.addNewRate(5, 30.99));
    osb.setBuilderCapacity(OfficeSpace.newCapacity(200, 2, 2));
    OfficeSpace testOfficeSpace = null;  
        try {
            testOfficeSpace = osb.build();
        } catch (IncompleteInstantiationError ex) {
            System.out.println("Office Space is missing requirements");
        }
            
    
    }
    
    
}

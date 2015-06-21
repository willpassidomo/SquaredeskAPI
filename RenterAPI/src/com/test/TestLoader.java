/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn3.test;

import cscie97.asn3.squaredesk.RenterAPI.ContactDTO;
import cscie97.asn3.squaredesk.RenterAPI.ImageDTO;
import cscie97.asn3.squaredesk.RenterAPI.NameDTO;
import cscie97.asn3.squaredesk.RenterAPI.PayPalDTO;
import cscie97.asn3.squaredesk.RenterAPI.RenterAPI;
import cscie97.asn3.squaredesk.common.Contact;
import cscie97.asn3.squaredesk.common.Imagec;
import cscie97.asn3.squaredesk.common.Name;
import cscie97.asn3.squaredesk.common.NonUniqueUserNameError;
import cscie97.asn3.squaredesk.common.PayPalAccount;
import cscie97.asn3.squaredesk.common.User;
import cscie97.asn3.squaredesk.provider.Address;
import cscie97.asn3.squaredesk.provider.Address.AddressBuilder;
import cscie97.asn3.squaredesk.provider.Capacity;
import cscie97.asn3.squaredesk.provider.CommonFeature;
import cscie97.asn3.squaredesk.provider.Coordinate;
import cscie97.asn3.squaredesk.provider.FacilityType;
import cscie97.asn3.squaredesk.provider.IllegalCoordinateValue;
import cscie97.asn3.squaredesk.provider.IllegalValueError;
import cscie97.asn3.squaredesk.provider.IncompleteInstantiationError;
import cscie97.asn3.squaredesk.provider.OfficeSpace;
import cscie97.asn3.squaredesk.provider.OfficeSpace.OfficeSpaceBuilder;
import cscie97.asn3.squaredesk.provider.OfficeSpaceProvider;
import cscie97.asn3.squaredesk.provider.OfficeSpaceProvider.OfficeSpaceProviderBuilder;
import cscie97.asn3.squaredesk.provider.PrivateFeature;
import cscie97.asn3.squaredesk.provider.Rate;
import cscie97.asn3.squaredesk.renter.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willpassidomo
 */
public class TestLoader {
    
PrivateFeature pf1 = PrivateFeature.newPrivateFeature("Couch");
PrivateFeature pf2 = PrivateFeature.newPrivateFeature("TV");
PrivateFeature pf3 = PrivateFeature.newPrivateFeature("Parking");

CommonFeature cf1 = CommonFeature.newCommonFeature("Air Conditioning");
CommonFeature cf2 = CommonFeature.newCommonFeature("Pool");
CommonFeature cf3 = CommonFeature.newCommonFeature("Yard");
CommonFeature cf4 = CommonFeature.newCommonFeature("WiFi");
CommonFeature cf5 = CommonFeature.newCommonFeature("Free Coffee");

FacilityType ft1 = FacilityType.newFacilityType("Home");
FacilityType ft2 = FacilityType.newFacilityType("Garage");
FacilityType ft3 = FacilityType.newFacilityType("Shack in the woods");

Address addy1 = null;
Address addy2 = null;
Address addy3 = null;
Address addy4 = null;

AddressBuilder adrsB;

User user1;
User user2;
User user3;
User user4;
User user5;

//Renter renter1 = null;
//Renter renter2 = null;
//Renter renter3 = null;

OfficeSpaceProvider OfficeSpaceProvider1 = null;
OfficeSpaceProvider OfficeSpaceProvider2 = null;
OfficeSpaceProvider OfficeSpaceProvider3 = null;
OfficeSpaceProvider OfficeSpaceProvider4 = null;

OfficeSpaceProviderBuilder officeSpaceProviderBuilder1;
OfficeSpaceProviderBuilder officeSpaceProviderBuilder2;
OfficeSpaceProviderBuilder officeSpaceProviderBuilder3;

OfficeSpaceBuilder officeSpaceBuilder1;
OfficeSpaceBuilder officeSpaceBuilder2;
OfficeSpaceBuilder officeSpaceBuilder3;
OfficeSpaceBuilder officeSpaceBuilder4;
OfficeSpaceBuilder officeSpaceBuilder5;
OfficeSpaceBuilder officeSpaceBuilder6;
OfficeSpaceBuilder officeSpaceBuilder7;
OfficeSpaceBuilder officeSpaceBuilder8;
OfficeSpaceBuilder officeSpaceBuilder9;
OfficeSpaceBuilder officeSpaceBuilder10;

             

    void initalizeVariables () throws URISyntaxException, IllegalCoordinateValue, IncompleteInstantiationError, IllegalValueError, NonUniqueUserNameError {
        user1 = new User("user1");
        user2 = new User("user2");
        user3 = new User("user3");
        user4 = new User("user4");
        user5 = new User("user5");
        officeSpaceProviderBuilder1 = user4.newOSproviderProfileBuilder();
        officeSpaceProviderBuilder1.setContactBuild(Contact.newContact("123-543-1234"));
        officeSpaceProviderBuilder1.setNameBuild(Name.newName("George", "Lopez"));
        officeSpaceProviderBuilder1.setPictureBuild(Imagec.newImage("George Lopez face", "This is a picture of my beautiful face", new URI("http://thenypost.files.wordpress.com/2013/08/george_lopez-300x300.jpg")));
        officeSpaceProviderBuilder1.setPayPalAccountBuild(PayPalAccount.newPayPalAccount("543756134", "GeorgeLopez@hotmail.net", officeSpaceProviderBuilder1.getUser()));
        OfficeSpaceProvider1 = officeSpaceProviderBuilder1.build();
        
        officeSpaceProviderBuilder2 = user5.newOSproviderProfileBuilder();
        officeSpaceProviderBuilder2.setNameBuild(OfficeSpaceProvider.newName("will", "passidomo"));
        officeSpaceProviderBuilder2.setContactBuild(OfficeSpaceProvider.newContact("912-234-4324"));
        officeSpaceProviderBuilder2.setPayPalAccountBuild(OfficeSpaceProvider.newPayPalAccount("123456789", "fake@email.com", officeSpaceProviderBuilder2.getUser()));
        officeSpaceProviderBuilder2.setPictureBuild(OfficeSpaceProvider.addNewImage("Me", "A crazy picture of me","http://img3.wikia.nocookie.net/__cb20100724183918/spongebob/images/3/33/Patrick_Star.svg" ));
        OfficeSpaceProvider2 = officeSpaceProviderBuilder2.build();

       
        
        officeSpaceProviderBuilder3 = user3.newOSproviderProfileBuilder();     
        officeSpaceProviderBuilder3.setNameBuild(Name.newName("John","Doe"));
        officeSpaceProviderBuilder3.setContactBuild(Contact.newContact("914-787-9919"));
        officeSpaceProviderBuilder3.setPictureBuild(Imagec.newImage("my picture", "the referance to this picture",new URI("http://kosmixmedia.com/static/1f40b993a4072fb3e70d547560245545.jpg")));
        officeSpaceProviderBuilder3.setPayPalAccountBuild(user3.newPayPalAccount("1234346525", "wpassidomo@fas.harvard.edu"));    
        OfficeSpaceProvider3 = officeSpaceProviderBuilder3.build();
       
        officeSpaceBuilder1 = OfficeSpaceProvider2.newOfficeSpaceBuilder();
        officeSpaceBuilder1.setBuilderAddress(Address.newAddress("355 Pearl st", "Burlington", "VT", 02321, "USA"));        
        officeSpaceBuilder1.addBuilderImage(OfficeSpace.addNewImage("Me", "A crazy picture of me","http://img3.wikia.nocookie.net/__cb20100724183918/spongebob/images/3/33/Patrick_Star.svg" ));
        officeSpaceBuilder1.setBuilderFacilityType(FacilityType.getFacilityTypes().get(1));
        officeSpaceBuilder1.addBuilderRate(OfficeSpace.addNewRate(5, 30.99));
        officeSpaceBuilder1.setBuilderCapacity(OfficeSpace.newCapacity(200, 2, 2));
        officeSpaceBuilder1.setBuilderCoordinate(Coordinate.newCoordinate(-34.1213, 55.33));
        officeSpaceBuilder1.addBuilderRate(Rate.newRate(3, 19.99));
        officeSpaceBuilder1.build();
        
        
        officeSpaceBuilder2  = OfficeSpaceProvider3.newOfficeSpaceBuilder();
        officeSpaceBuilder2.addBuilderCommonFeature(cf1);
        officeSpaceBuilder2.addBuilderCommonFeature(cf2);
        officeSpaceBuilder2.addBuilderCommonFeature(cf3);
        officeSpaceBuilder2.addBuilderCommonFeature(cf4);
        officeSpaceBuilder2.addBuilderCommonFeature(cf5);
        officeSpaceBuilder2.addBuilderPrivateFeature(pf1);
        officeSpaceBuilder2.addBuilderPrivateFeature(pf2);
        officeSpaceBuilder2.addBuilderPrivateFeature(pf3);
        officeSpaceBuilder2.setBuilderCoordinate(Coordinate.newCoordinate(43.8623, -29.232));
        
        
        adrsB = officeSpaceBuilder2.newAddress();
        adrsB.setCity("Katonah");
        adrsB.setState("New York");
        adrsB.setStreet1("24 Millhurst rd");
        adrsB.setZip(10549);
        adrsB.setState("NY");
        adrsB.setCountyCode("USA");
        addy2 = adrsB.build();
        
            
        officeSpaceBuilder2.setBuilderAddress(addy2);
        officeSpaceBuilder2.setBuilderCapacity(OfficeSpace.newCapacity(300, 3, 3));
        officeSpaceBuilder2.addBuilderRate(OfficeSpace.addNewRate(3, 50.49));
        officeSpaceBuilder2.setBuilderFacilityType(ft1);
        officeSpaceBuilder2.addBuilderImage(OfficeSpace.addNewImage("my house", "is a very very very fine house", "http://www.whitegadget.com/attachments/pc-wallpapers/85254d1320380902-house-house-wallpaper.jpg"));
        officeSpaceBuilder2.addBuilderRate(Rate.newRate(4,75.00));
        officeSpaceBuilder2.setBuilderCoordinate(Coordinate.newCoordinate(174.33, 10.2));
        officeSpaceBuilder2.build();
        
        
        officeSpaceBuilder3 = OfficeSpaceProvider2.newOfficeSpaceBuilder();
        officeSpaceBuilder3.setBuilderAddress(Address.newAddress("100 Vagabond rd", "Des Moins","IA", 25442, "USA"));
        officeSpaceBuilder3.setBuilderCapacity(OfficeSpace.newCapacity(100, 1, 1));
        officeSpaceBuilder3.setBuilderCoordinate(Coordinate.newCoordinate(54.325,-39.22));
        officeSpaceBuilder3.setBuilderFacilityType(ft2);
        officeSpaceBuilder3.addBuilderImage(Imagec.newImage("The office", "the garage space office", new URI("http://cdn.lightgalleries.net/4bd5ec0e35af0/images/garage-2.jpg")));
        officeSpaceBuilder3.addBuilderRate(Rate.newRate(7, 99.00));
        officeSpaceBuilder3.addBuilderPrivateFeature(pf2);
        officeSpaceBuilder3.addBuilderPrivateFeature(pf3);
        officeSpaceBuilder3.addBuilderCommonFeature(cf1);
        officeSpaceBuilder3.addBuilderCommonFeature(cf5);
        officeSpaceBuilder3.build();

        
        officeSpaceBuilder4 = OfficeSpaceProvider2.newOfficeSpaceBuilder();
        officeSpaceBuilder4.setBuilderAddress(Address.newAddress("1 washington sq", "Washington DC", "DC", 10101, "USA"));
        officeSpaceBuilder4.setBuilderCapacity(OfficeSpace.newCapacity(2500, 20, 25));
        officeSpaceBuilder4.setBuilderCoordinate(Coordinate.newCoordinate(32.234, 89.99));
        officeSpaceBuilder4.setBuilderFacilityType(ft1);
        officeSpaceBuilder4.addBuilderImage(Imagec.newImage("My shack", "had to rebuild in the 1800's due to fire", new URI("http://upload.wikimedia.org/wikipedia/commons/a/af/WhiteHouseSouthFacade.JPG")));
        officeSpaceBuilder4.addBuilderImage(Imagec.newImage("BackSide", "the view from behind", new URI("http://www.whitehouse.gov/sites/default/files/image/whitehouse_historypg.jpg")));
        officeSpaceBuilder4.addBuilderCommonFeature(cf1);
        officeSpaceBuilder4.addBuilderPrivateFeature(pf1);
        officeSpaceBuilder4.addBuilderPrivateFeature(pf3);
        officeSpaceBuilder4.addBuilderPrivateFeature(pf2);
        officeSpaceBuilder4.addBuilderRate(Rate.newRate(1, 200.0));
        officeSpaceBuilder4.build();
        
        officeSpaceBuilder5 = OfficeSpaceProvider1.newOfficeSpaceBuilder();
        officeSpaceBuilder5.setBuilderAddress(Address.newAddress("49 Capital st", "Moscow", null, 0, "RU"));
        officeSpaceBuilder5.setBuilderCapacity(Capacity.newCapacity(30000, 200, 450));
        officeSpaceBuilder5.setBuilderCoordinate(Coordinate.newCoordinate(-103.21, 80.343));
        officeSpaceBuilder5.setBuilderFacilityType(ft2);
        officeSpaceBuilder5.addBuilderImage(Imagec.newImage("Our Beautiful Office", "free common feature", new URI("http://theaviationist.com/wp-content/uploads/2013/09/PAK-FA-2.jpg")));
        officeSpaceBuilder5.addBuilderCommonFeature(cf4);
        officeSpaceBuilder5.addBuilderCommonFeature(cf5);
        officeSpaceBuilder5.addBuilderCommonFeature(cf3);
        officeSpaceBuilder5.addBuilderRate(Rate.newRate(3, 29.99));
        officeSpaceBuilder5.build();
        
        QueryBuilder.refreshIndexs();

    }
    
    
}

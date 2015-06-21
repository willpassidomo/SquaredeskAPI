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
import cscie97.asn3.squaredesk.RenterAPI.QueryDTO;
import cscie97.asn3.squaredesk.RenterAPI.RatingDTO;
import cscie97.asn3.squaredesk.RenterAPI.RenterAPI;
import cscie97.asn3.squaredesk.RenterAPI.RenterDTO;
import cscie97.asn3.squaredesk.RenterAPI.ReservationDTO;
import cscie97.asn3.squaredesk.common.Contact;
import cscie97.asn3.squaredesk.common.Imagec;
import cscie97.asn3.squaredesk.common.Name;
import cscie97.asn3.squaredesk.common.NonUniqueUserNameError;
import cscie97.asn3.squaredesk.common.PayPalAccount;
import cscie97.asn3.squaredesk.common.User;
import cscie97.asn3.squaredesk.provider.Coordinate;
import cscie97.asn3.squaredesk.provider.IllegalCoordinateValue;
import cscie97.asn3.squaredesk.provider.IllegalValueError;
import cscie97.asn3.squaredesk.provider.IncompleteInstantiationError;
import cscie97.asn3.squaredesk.provider.OfficeSpace;
import cscie97.asn3.squaredesk.provider.OfficeSpaceProvider;
import cscie97.asn3.squaredesk.renter.QueryBuilder;
import cscie97.asn3.squaredesk.renter.Renter;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willpassidomo
 */
public class TestDriver {
    
    public static void main (String[] args) throws IllegalCoordinateValue, URISyntaxException, NonUniqueUserNameError, ParseException {
        TestLoader test =  new TestLoader();
        try {
            test.initalizeVariables();
        } catch (Exception ex) {
            System.out.println("There was an exception Loading");
            ex.printStackTrace();
        }
        TestDriver testD = new TestDriver();
        testD.initalizeVariables();
        testD.runProgram();
    }
    
    User user10;
    User user11;
    User user12;
    
    RenterDTO renter1;
    RenterDTO renter2;
    RenterDTO renter3;
    
    RenterAPI renterAPI = new RenterAPI();
    
    String authToken = "";
    
    SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yy");

        void runProgram () throws IllegalCoordinateValue, ParseException {
        //step 1    
            renter1 = renterAPI.createRenter(authToken, renter1, user10.getUserID());
        //step2    
            renter2 = renterAPI.createRenter(authToken, renter2, user11.getUserID());
        //step3    
            renter3 = renterAPI.createRenter(authToken, renter3, user12.getUserID());
        // print step4    
            System.out.println("Renter List: " +renterAPI.getRenterList(authToken));
        //step5    
            renterAPI.DeleteRenter(authToken, renter3.getRenterID());
        //step6    
            renter2.getContact().setHomePhone("914-929-2432");
            renter2.getName().setPrefix(NameDTO.LEGALPREFIX_Dr);
            renter2.getImage().setName("silly picture");
            renter2.getPaypal().setEmailAddress("myaccount@gmail.com");
            renterAPI.updateRenter(authToken, renter2);
        //print to show 5&6    
            System.out.println("Renter List (after changes): " + renterAPI.getRenterList(authToken));
        //step 7
            System.out.println("renter1: " +renterAPI.getRenter(authToken, renter1.getRenterID()));
        //step 8
            System.out.println("Facility Types: " +renterAPI.getFacitliyTypes());
        //step 9
            System.out.println("Private Features: "+renterAPI.getPrivateFeatures());
        //step 10 
            System.out.println("Common Features: "+renterAPI.getCommonFeatures());
        //step 11R
            QueryDTO query = new QueryDTO();
            System.out.println("Blank query to return all OfficeSpaces: \n" +renterAPI.submitQuery(authToken, renter1.getRenterID(), query));
        //step 12
            QueryDTO query1 = new QueryDTO();
            query1.addCommonFeature(renterAPI.getCommonFeatures().get(4));
            query1.setFacilityType(renterAPI.getFacitliyTypes().get(1));
            query1.addPrivateFeature(renterAPI.getPrivateFeatures().get(1));
            List<OfficeSpace> results = renterAPI.submitQuery(authToken, renter2.getRenterID(), query1);
            System.out.println("Populated query results :\n" + results);
        //step 13
            ReservationDTO reservation = new ReservationDTO(results.get(0).getGUID(),sdf.parse("11/04/14"),sdf.parse("11/07/14"),results.get(0).getRates().get(0).getRateID());
            System.out.println(reservation.getRateID());
            reservation = renterAPI.makeReservation(authToken, renter1.getRenterID(), reservation);
            System.out.println("renter1's reservation: "+reservation);
        //step14
            ReservationDTO reservation1 = new ReservationDTO(results.get(0).getGUID(),sdf.parse("11/14/14"), sdf.parse("11/15/14"),results.get(0).getRates().get(0).getRateID());
            reservation1 = renterAPI.makeReservation(authToken, renter2.getRenterID(), reservation1);
            System.out.println("renter2's reservation: " +reservation1);
        //step15
            
            
        /**    
            System.out.println(OfficeSpaceProvider.getOfficeSpaceProviders());
        System.out.println(OfficeSpace.getOfficeSpaces());
        System.out.println(Renter.getRenters());
        QueryBuilder query1 = user2.getRenterProfile().newQuery();
        query1.addTCondition(ft2);
        query1.addTCondition(Coordinate.newCoordinate(-103.1, 80.1));
        //query1.addTCondition(cf4);
        //query1.addTCondition(cf1);
        QueryBuilder.refreshIndexs();
        System.out.println("\nRESULTS OF THE QUERY ARE:\n");
        System.out.println(query1.executeTQuery());
        * 
        * **/
    }   
    
    void initalizeVariables() throws URISyntaxException, NonUniqueUserNameError {
        
        user10 = new User("user10");
        user11 = new User("user11");
        user12 = new User("user12");
        
        NameDTO name1 = new NameDTO("Jonny", "AppleSeed");
        ContactDTO contact1 = new ContactDTO("234-677-0099");
        URI uri1 = new URI("http://static.asiawebdirect.com/m/phuket/portals/phuket-com/homepage/island/beaches_karon/TopPageContent/0/BigImage/karon-beach.jpg");
        ImageDTO image1 = new ImageDTO("At the beach", "this is me at the beach, not a great office", uri1);
        PayPalDTO payPal1 = new PayPalDTO("209582394", "google@harvard.edu");
        renter1 = new RenterDTO(name1, image1, contact1, payPal1);
                
        NameDTO name2 = new NameDTO("George", "Washington");
        ContactDTO contact2 = new ContactDTO("423-099-8403");
        URI uri2 = new URI("http://cp91279.biography.com/1000509261001/1000509261001_1895249413001_George-Washington-Washington-the-Farmer.jpg");
        ImageDTO image2 = new ImageDTO("My Favorite photo", "1770, back when I was young", uri2);
        PayPalDTO payPal2 = new PayPalDTO("894736974", "cherrychopa@aol.com");
        renter2 = new RenterDTO(name2, image2, contact2, payPal2);
        
        NameDTO name3 = new NameDTO("Evander", "Holyfield");
        ContactDTO contact3 = new ContactDTO("232-532-6573");
        URI uri3 = new URI("http://upload.wikimedia.org/wikipedia/commons/9/92/EHolyfield.jpg");
        ImageDTO image3 = new ImageDTO("Boo", "man dont I have an old looking face", uri3);
        PayPalDTO payPal3 = new PayPalDTO("982374590", "canthearuuu@hmail.com");
        renter3 = new RenterDTO(name3, image3, contact3, payPal3);
    }
    
}

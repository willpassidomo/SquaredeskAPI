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
import cscie97.asn3.squaredesk.RenterAPI.RenterDTO;
import cscie97.asn3.squaredesk.common.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author willpassidomo
 */
public class ErrorTest {
   
        public static void main (String[] args) throws URISyntaxException {
            ErrorTest test = new ErrorTest();
            test.runProgram();
        }
        
        private void runProgram() throws URISyntaxException {
        User user1;
        RenterDTO renter1;
        NameDTO name1 = null; 
        ImageDTO image1 = null;
        ContactDTO contact1 = null;
        PayPalDTO payPal1 = null;
        
        try {    
            name1 = new NameDTO("Jon7ny", "AppleSeed");
        } 
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        name1 = new NameDTO("Jonny", "AppleSeed");
        try {
            name1.setPrefix("king");
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            contact1 = new ContactDTO("2348-677-0099");
        }
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        URI uri1 = new URI("http://static.asiawebdirect.com/m/phuket/portals/phuket-com/homepage/island/beaches_karon/TopPageContent/0/BigImage/karon-beach.jpg");
        try {
            image1 = new ImageDTO("At the2 beach", "this is me at the beach, not a great office", uri1);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            payPal1 = new PayPalDTO("209582394", "google@harvard.edu");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            renter1 = new RenterDTO(name1, image1, contact1, payPal1);
        } 
        catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }
        }
}
        


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class RenterDTO {
    private NameDTO name;
    private ContactDTO contact;
    private ImageDTO image;
    private UUID renterID;
    private PayPalDTO paypal;
    
    public RenterDTO(NameDTO name, ImageDTO image, ContactDTO contact, PayPalDTO paypal) {
        this.name = (NameDTO)Validate.checkNotNull("name", name);
        this.contact = (ContactDTO)Validate.checkNotNull("contact", contact);
        this.image = (ImageDTO)Validate.checkNotNull("image", image);
        this.paypal = paypal;
    }
    
    RenterDTO(NameDTO name, ImageDTO image, ContactDTO contact, PayPalDTO paypal, UUID renterID) {
        this(name,image,contact, paypal);
        this.renterID = renterID;
    }

    /**
     * @return the name
     */
    public NameDTO getName() {
        return name;
    }

    /**
     * @return the contact
     */
    public ContactDTO getContact() {
        return contact;
    }

    /**
     * @return the image
     */
    public ImageDTO getImage() {
        return image;
    }

    /**
     * @param name the name to set
     */
    public void setName(NameDTO name) {
        this.name = (NameDTO)Validate.checkNotNull("name", name);
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(ContactDTO contact) {
        this.contact = (ContactDTO)Validate.checkNotNull("contact", contact);
    }

    /**
     * @param image the image to set
     */
    public void setImage(ImageDTO image) {
        this.image = (ImageDTO) Validate.checkNotNull("image", image);
    }

    /**
     * @return the renterID
     */
    public UUID getRenterID() {
        return renterID;
    }

    /**
     * @return the paypal
     */
    public PayPalDTO getPaypal() {
        return paypal;
    }

    /**
     * @param paypal the paypal to set
     */
    public void setPaypal(PayPalDTO paypal) {
        this.paypal = paypal;
    }
    
    @Override
    public String toString() {
        return "Renter GUID: " +this.renterID +
                "\n\tname: " + this.name +
                "\n\tcontact: " +this.contact +
                "\n\tprofile picture: " + this.image +
                "\n\tpaypalAccount: " +this.paypal + "\n";
    }
}

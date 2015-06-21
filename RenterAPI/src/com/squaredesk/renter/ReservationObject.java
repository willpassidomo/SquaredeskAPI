/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.renter;

import com.squaredesk.common.Validate1;
import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.OfficeSpaceProvider;
import com.squaredesk.provider.Rate;
import com.squaredesk.RenterAPI.Validate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * ReservationObject is an immutable class which act as a place holder or marker of a reservation. The reservation Objects are created solely through the scheduler but my cancel themselves
 * @author willpassidomo
 */
public class ReservationObject {
    public static final int PAID = 1;
    public static final int DUE = 2;
    public static final int PASTDUE = 3;

        
    private UUID reservationID;
    private Date startDate;
    private Date endDate;
    private Date dateMade;
    private OfficeSpace officeSpace;
    private OfficeSpaceProvider officeSpaceProvider;
    private Renter renter;
    private Rate rate;
    private int paymentStatus;
    private double balance;
    private double paid;
    
    static Scheduler scheduler = Scheduler.getInstance();
    private static List<ReservationObject> reservations = new ArrayList<ReservationObject>();
    private static Map<UUID, ReservationObject> reservationsByID = new HashMap<UUID, ReservationObject>();
    
    private ReservationObject(OfficeSpace officeSpace, Renter renter, Date startDate, Date endDate, Rate rate) {
        this.officeSpace = officeSpace;
        this.renter = renter;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
        this.dateMade = new Date();
        this.paymentStatus = DUE;
        this.balance = rate.getTotalCost();
        this.officeSpaceProvider = officeSpace.getOfficeOwner();
        this.reservationID = UUID.randomUUID();
        reservationsByID.put(reservationID, this);
    }
    
    /**
     * creates a new, immutable ReservationObject which acts as the notation of the reservation. ReservationObjects are registered with the Renter, the OfficeSpace, and the Scheduler
     * @param officeSpace the OfficeSpace to be reserved
     * @param renter the Renter reserving the OfficeSpace
     * @param startDate the date which the rental starts
     * @param endDate the date which the rental ends
     * @param rate the legal rate at which the renter will owe the OfficeSpaceProvider for the reservation period
     * @return the Reservation Object or null if the reservation cannot be made
     */
    
    static public ReservationObject newReservationObject(OfficeSpace officeSpace, Renter renter, Date startDate, Date endDate, Rate rate) {
            Validate1.checkDateOrder(startDate, endDate);
            ReservationObject newReservationObject = new ReservationObject(officeSpace, renter, startDate, endDate, rate);
            reservations.add(newReservationObject);
            officeSpace.addReservation(newReservationObject);
            renter.addReservation(newReservationObject);
        return newReservationObject;
    }
    
    /**
     * allows a Renter to cancel a reservation provided that the reservation has not yet started (time of method call is before ReservationOBject.startTime). 
     * If cancelReservation returns true, the amount previously paid towards the reservation by the Renter, if any will automatically be deducted from the OfficeSpaceProviders PayPalAccount and credited to the Renters PayPalAccount.
     * @return true if method call took place prior to startDate and is indeed canceled, false if method call occured after startData and is still active
     */
    
    public static ReservationObject getReservationByID(UUID reservationID) {
        return reservationsByID.get(reservationID);
    }
 
    public boolean cancelReservation() {
        Date currentTime = new Date();
        if (currentTime.before(this.getStartDate())) {
            getReservations().remove(this);
            scheduler.freeReservation(this);
            getRenter().getReservations().remove(this);
            getOfficeSpace().getReservations().remove(this);
            getOfficeSpaceProvider().getPayPalAccount().makePayment(getBalance(), getRenter().getPayPalAccount());
            return true;
        }
        return false;
    }
    
    /**
     * make a payment from the Renter's PayPalAccount to the OfficeSpaceProvider who is renting the OfficeSpace's PayPalAccount for the amount specified. this method will not allow the payment to ocure and will return false if the Renter does not have a high enough balance to cover the payment
     * @return true if the Renters payment goes through, false if it does not
     */
    
    boolean makePayment(double amount) {
        if (getRenter().getPayPalAccount().makePayment(amount, getOfficeSpaceProvider().getPayPalAccount())){
            paid += amount;
            balance = rate.getTotalCost() - paid;
            if (balance <= 0) {
                paymentStatus = ReservationObject.PAID;
            }
            return true;
        }
        return false;
        
    }
    
    /**
     * make a payment from the Renter's PayPalAccount to the OfficeSpaceProvider who is renting the OfficeSpace's PayPalAccount for the full balance. this method will not allow the payment to ocure and will return false if the Renter does not have a high enough balance to cover the payment
     * @return true if the Renters payment goes through, false if it does not
     */
    boolean makePayment() {
        if (getRenter().getPayPalAccount().makePayment(getBalance(), getOfficeSpaceProvider().getPayPalAccount())) {
            paid = rate.getTotalCost();
            balance = 0.0;
            paymentStatus = ReservationObject.PAID;
            return true;
        }
        return false;
    }
   
    /**
    @Override
    public boolean equals(Object o) {
        if (o instanceof ReservationObject) {
            ReservationObject ro = (ReservationObject) o;
            if (ro.officeSpace.equals(this.officeSpace)){
            if (ro.startDate.before(this.endDate) && ro.startDate.after(this.startDate)) {
                return true;
            } else { if(ro.endDate.after(this.startDate)&& ro.endDate.before(this.endDate)) {
                return true;
            }
            }
            }
        }
            return false;

    }
   **/

    /**
     * @return the reservationID
     */
    public UUID getReservationID() {
        return reservationID;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @return the dateMade
     */
    public Date getDateMade() {
        return dateMade;
    }

    /**
     * @return the officeSpace
     */
    public OfficeSpace getOfficeSpace() {
        return officeSpace;
    }

    /**
     * @return the officeSpaceProvider
     */
    public OfficeSpaceProvider getOfficeSpaceProvider() {
        return officeSpaceProvider;
    }

    /**
     * @return the renter
     */
    public Renter getRenter() {
        return renter;
    }

    /**
     * @return the rate
     */
    public Rate getRate() {
        return rate;
    }

    /**
     * @return the paymentStatus
     */
    public int getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @return the paid
     */
    public double getPaid() {
        return paid;
    }
    
    /**
     * @return the reservations
     */
    public static List<ReservationObject> getReservations() {
        return reservations;
    }
}

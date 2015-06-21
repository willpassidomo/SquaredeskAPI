/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.Rate;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class ReservationDTO {
    private UUID reservationID;
    private Date startDate;
    private Date endDate;
    private UUID officeSpaceID;
    private Date dateMake;
    private int paymentStatus;
    private double balance;
    private UUID rateID;
    private UUID renterID;

    public ReservationDTO (UUID officeSpaceID, Date startDate, Date endDate, UUID rateID) {
        this.officeSpaceID = officeSpaceID;
        if(Validate.checkDateOrder(startDate, endDate)) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rateID = rateID;
        }
    }   
    
    ReservationDTO (UUID reservationID, Date startDate, Date endDate, UUID officeSpaceID, Date dateMade, int paymentStatus, double balance, UUID renterID) {
        this.balance = balance;
        this.dateMake = dateMade;
        this.endDate = endDate;
        this.officeSpaceID = officeSpaceID;
        this.paymentStatus = paymentStatus;
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.renterID = renterID;
    }

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
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        if (Validate.checkDateOrder(startDate, this.endDate)) {
            this.startDate = startDate;
        }
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        if (Validate.checkDateOrder(this.startDate, endDate)) {
            this.endDate = endDate;
        }
    }

    /**
     * @return the officeSpaceID
     */
    public UUID getOfficeSpaceID() {
        return officeSpaceID;
    }

    /**
     * @param officeSpaceID the officeSpaceID to set
     */
    public void setOfficeSpaceID(UUID officeSpaceID) {
        this.officeSpaceID = officeSpaceID;
    }

    /**
     * @return the dateMake
     */
    public Date getDateMake() {
        return dateMake;
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
     * @return the rateID
     */
    public UUID getRateID() {
        return rateID;
    }

    /**
     * @param rateID the rateID to set
     */
    public void setRateID(UUID rateID) {
        this.rateID = rateID;
    }
    
    @Override
    public String toString() {
        return "Reservation GUID: " + this.reservationID+
                "\n\tstart date: " + this.startDate +
                "\n\tend date: " + this.endDate +
                "\n\toffice space ID: " + this.officeSpaceID +
                "\n\trenter ID: " + this.renterID +
                "\n\tdate made: " + this.dateMake;
    }
}

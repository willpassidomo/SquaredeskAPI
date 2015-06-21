/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.renter;

import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.OfficeSpaceProvider;
import com.squaredesk.provider.Rate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class Scheduler {
    
    private HashMap<OfficeSpace,ArrayList<ReservationObject>> reservationByOfficeSpace = new HashMap<OfficeSpace,ArrayList<ReservationObject>>();
        
    private Scheduler() {
    }
    
    public static Scheduler getInstance() {
        return SchedulerHolder.INSTANCE;
    }
    
    /**
     * checks if a reservation for a particular office space for the given time already exists. returns a boolean value
     * which represents that (true) no reservation exists for this office space during this time and a Renter would be able to book it, or (false)
     * a reservation has already been made for this time and it is unable to be booked.
     * @param officeSpace the OfficeSpace which is being checked for availability
     * @param startDate the startDate for the hypothetical reservation being checked
     * @param endDate the endDate for the hypothetical reservation being checked
     * @return 
     */
    
    public boolean isAvailable(OfficeSpace officeSpace, Date startDate, Date endDate) {
        if (!reservationByOfficeSpace.isEmpty()) {
        for(ReservationObject reservation :reservationByOfficeSpace.get(officeSpace)) {
            if (reservation.getStartDate().after(startDate) && reservation.getEndDate().before(endDate)){
                return false;
            } else { if (reservation.getEndDate().before(endDate) && reservation.getEndDate().after(startDate)) {
                return false;
            }
            }
            }
        }
        return true;
    }
    
    /**
     * creates and returns a ReservationObject after checking if the specified dates are available for the specified OfficeSpace. MakeReservation() is meant to be called by a Renter object
     * @param officeSpace the OfficeSpace to reserve
     * @param renter the Renter reserving the OfficeSpace
     * @param startDate the date which the reservation starts
     * @param endDate the date which the reservation ends
     * @param rate the rate which the Renter will be charged for the duration of the reservation
     * @return the ReservationObject instance which acts as a "reciept" of the reservation. Reservation my also be canceled through the ReservationObject
     */
    public ReservationObject makeReservation(OfficeSpace officeSpace, Renter renter, Date startDate, Date endDate, Rate rate) {
        ReservationObject newReservation = null;
        if (isAvailable(officeSpace, startDate, endDate)){
            newReservation = ReservationObject.newReservationObject(officeSpace, renter, startDate, endDate, rate);
            if(reservationByOfficeSpace.get(officeSpace) == null){
                 ArrayList<ReservationObject> reservationList = new ArrayList<ReservationObject>();
                reservationList.add(newReservation);
                reservationByOfficeSpace.put(officeSpace,reservationList);
            } else {
                reservationByOfficeSpace.get(officeSpace).add(newReservation);
            }               
            }
        return newReservation;
    }

    /**
     * called by reservation.cancelReservation(). removes the passed reservation from the reservations map, allowing the OfficeSpace previously booked for the time period to be booked again
     * @param reservation the reservation to remove
     */
    void freeReservation(ReservationObject reservation) {
        reservationByOfficeSpace.get(reservation.getOfficeSpace()).remove(reservation);
    }
    
    /**
     * returns a list of all reservations (ReservationObjects) for a given OfficeSpace. reservations include
     * those which have already happened, are currently happening, or and in the future
     * 
     * @param officeSpace the Office Space for which reservations will be returned
     * @return a list of reservations
     */
    
    public List<ReservationObject> getReservations(OfficeSpace officeSpace) {
        return reservationByOfficeSpace.get(officeSpace);
    }
    
    /**
     * returns a list of all reservations (ReservationObjects) for a given OfficeSpaceProvider. reservations include
     * those which have already happened, are currently happening, or and in the future
     * @param officeSpaceProvider the Provider for which reservations will be returned
     * @return a list of reservations
     */
    public List<ReservationObject> getReservations(OfficeSpaceProvider officeSpaceProvider) {
        List<ReservationObject> reservationsResult = new ArrayList<ReservationObject>();
        for (OfficeSpace officeSpace: officeSpaceProvider.getOfficeSpaces()) {
            for(ReservationObject reservations: getReservations(officeSpace)) {
                reservationsResult.add(reservations);
            }
        }
        return reservationsResult;
    }
    
    private static class SchedulerHolder {

        private static final Scheduler INSTANCE = new Scheduler();
    }
    
}

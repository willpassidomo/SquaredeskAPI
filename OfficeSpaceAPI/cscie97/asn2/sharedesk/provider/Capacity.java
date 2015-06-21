/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

/**
 *Capacity Class is used to encapsulate the description of the OfficeSpace in terms of it's Square Feet, maximum-allowed People and total number of work stations
 * @author willpassidomo
 */
class Capacity {
    private int sqFt;
    private int peopleLimit;
    private int workSpaces;

    /** 
     * public constructor for a Capacity Object
     * 
     * @param sqFt the total number of Square Feet of the OfficeSpace
     * @param peopleLimit the Maximum allowed people in the OfficeSpace
     * @param workSpaces the total number of workSpaces in the OfficeSpace, different from peopleLimit in that multiple people may work at one work space
     * @throws IllegalValueError 
     */
    
    public Capacity (int sqFt, int peopleLimit, int workSpaces) {
        this.sqFt = sqFt;
        this.peopleLimit = peopleLimit;
        this.workSpaces = workSpaces;
    }
    
    public static Capacity newCapacity (int sqFt, int peopleLimit, int workSpaces) throws IllegalValueError {
        Capacity capacity;
        if (sqFt > 0 && peopleLimit > 0 && workSpaces > 0 ) {
            capacity = new Capacity(sqFt, peopleLimit, workSpaces);
        } else {
            throw new IllegalValueError();
        }
        return capacity;
    }
    
    /**
     * returns the amount of sqFt for the Capcity Object
     * @return the sqFt value of the Capacity Object
     */
    
    public int getSqFt() {
        return sqFt;
    }

    /**
     * sets the amount of sqFt for the Capacity Object. For the sake of simplicity, the
     * sqFt must be a whole number
     * @param sqFt the sqFt to set
     */
    public void setSqFt(int sqFt) {
        this.sqFt = sqFt;
    }

    /**
     * returns the maximum amount of people the Office Space which owns the Capacity Object is suitable for
     * @return the peopleLimit
     */
    public int getPeopleLimit() {
        return peopleLimit;
    }

    /**
     * sets the maximum amount of people allowed in the given OfficeSpace with this Capacity Object
     * @param peopleLimit the peopleLimit to set
     */
    public void setPeopleLimit(int peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    /**
     * returns the total number of Work Spaces in the Office Space with the given Capacity Object, this number
     * should not be confused with PeopleLimit as multiple people may be able to work at one workstation
     * @return the workSpaces
     */
    public int getWorkSpaces() {
        return workSpaces;
    }

    /**
     * sets the total number of Work Spaces in the Office Space with the given Capacity Object, this number
     * should not be confused with PeopleLimit as multiple people may be able to work at one workstation
     *
     * @param workSpaces the workSpaces to set
     */
    public void setWorkSpaces(int workSpaces) {
        this.workSpaces = workSpaces;
    }
}

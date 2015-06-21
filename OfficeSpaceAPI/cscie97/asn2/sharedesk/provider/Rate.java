/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

/**
 *is a Class which encapsulates description of the rate being offered for a given OfficeSpace
 * the Rate consists of a Period which is an int representing the length in DAYS of the Rate and 
 * rate, which represents the cost in DOLLARS of a single day. Rate also has the method getTotalCost()
 * which returns the total cost of the stay over the full period of Period days at the daily rate
 * @author willpassidomo
 */
class Rate {
    private int period;
    private double rate;
    
    private Rate (int period, double rate) {
        this.period = period;
        this.rate = rate;
    }
    
    public static Rate newRate(int period, double rate) {
        return new Rate(period, rate);
    }
    
    /**
     * returns the value, in DOLLARS for the cost of the total amount of Period in DAYS given Rate in DOLLARS
     * @return 
     */
    public double getTotalCost() {
        return period * rate;
    }

    /**
     * returns the Period in DAYS for the Rate Object
     * @return the period
     */
    public int getPeriod() {
        return period;
    }

    /**
     * allows the user to set the Rate Object's period in DAYS
     * @param period the period to set
     */
    public void setPeriod(int days) {
        this.period = period;
    }

    /**
     * returns the period of the Rate Object in DAYS
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * sets the amount PER DAY in DOLLARS for the Rate Object
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

}

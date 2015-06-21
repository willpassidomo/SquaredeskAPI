/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

/**
 *
 * @author willpassidomo
 */
class PayPalAccount {
    private String accountIDNumber;
    private String accountEmailAddress;

    private PayPalAccount (String accountIDNumber, String accountEmailAddress) {
        this.accountEmailAddress = accountEmailAddress;
        this.accountIDNumber = accountIDNumber;
    }
    
    public static PayPalAccount newPayPalAccount(String accountIDNumber, String accountEmailAddress) {
        PayPalAccount payPalAccount = new PayPalAccount(accountIDNumber, accountEmailAddress);
        return payPalAccount;
    }
    
    /**
     * @return the accountIDNumber
     */
    public String getAccountIDNumber() {
        return accountIDNumber;
    }

    /**
     * @param accountIDNumber the accountIDNumber to set
     */
    public void setAccountIDNumber(String accountIDNumber) {
        this.accountIDNumber = accountIDNumber;
    }

    /**
     * @return the accountEmailAddress
     */
    public String getAccountEmailAddress() {
        return accountEmailAddress;
    }

    /**
     * @param accountEmailAddress the accountEmailAddress to set
     */
    public void setAccountEmailAddress(String accountEmailAddress) {
        this.accountEmailAddress = accountEmailAddress;
    }
}

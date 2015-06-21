/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

/**
 *
 * @author willpassidomo
 */
public class ContactDTO {
 
    private String homePhone;
    private String cellPhone;
    private String workPhone;
    
    
    public ContactDTO(String cellPhone) {
        this.cellPhone = Validate.makePhoneNumber(cellPhone);
    }
    
 
    /**
     * @return the homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * @param homePhone the homePhone to set
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = Validate.makePhoneNumber(homePhone);
    }

    /**
     * @return the cellPhone
     */
    public String getCellPhone() {
        return cellPhone;
    }

    /**
     * @param cellPhone the cellPhone to set
     */
    public void setCellPhone(String cellPhone) {
        this.cellPhone = Validate.makePhoneNumber(cellPhone);
    }

    /**
     * @return the workPhone
     */
    public String getWorkPhone() {
        return workPhone;
    }

    /**
     * @param workPhone the workPhone to set
     */
    public void setWorkPhone(String workPhone) {
        this.workPhone = Validate.makePhoneNumber(workPhone);
    }
    
    @Override
    public String toString() {
        String string = "";
        if (getCellPhone() != null) 
            string = string.concat("\tcell phone: "+ getCellPhone());
        if (getHomePhone()!= null) 
            string = string.concat("\n\thome phone: "+ getHomePhone());
        if (getWorkPhone() != null) 
            string = string.concat("\n\twork phone: "+ getWorkPhone());
        return string;
        }

    
}

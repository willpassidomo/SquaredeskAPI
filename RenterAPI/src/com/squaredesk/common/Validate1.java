/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.common;

import com.squaredesk.RenterAPI.*;
import java.util.Date;

/**
 *
 * @author willpassidomo
 */
public class Validate1 {
    public static Object checkNotNull(String methodName, Object o) {
        if (o!=null) {
            return o;
        }
        throw new NullPointerException("CANNOT BE NULL. Field: \""+ methodName+"\"");      
    }
    
    public static String checkAllChars(String methodName, String testString) {
        char[] chars = testString.toCharArray();
        for (char testChar: chars){
            if(!Character.isLetter(testChar)) {
                throw new IllegalArgumentException(("CANNOT CONTAIN NON LETTER CHARACTERS. Field: \"" + methodName+"\""));
            }
        }
        return testString;
    }
    
    public static int testRange(String methodName, int testInt, int min, int max) {
        if(testInt < min) {
            throw new IllegalArgumentException(("INPUT MUST NOT BE LESS THAN "+ min +". Field: \"" + methodName+"\"" +" value: \""+testInt+"\""));
        }
        if(testInt > max) {
            throw new IllegalArgumentException(("INPUT MUST NOT BE MORE THAN "+ max +". Field: \"" + methodName+"\""+" value: \""+testInt+"\""));
        }
        return testInt;
    }

    public static String makePhoneNumber(String phoneNumber) {
        char[] digits = phoneNumber.toCharArray();
        String numbers = "";
        for (int i = 0; i < digits.length; i++){
            if(Character.isDigit(digits[i])) {
                numbers = numbers.concat(String.valueOf(digits[i]));
            }
        }
        if(numbers.length() != 10 || numbers.indexOf("0") == 0) {
            throw new IllegalArgumentException("ILLEGAL PHONE NUMBER. MUST HAVE 10 DIGITS AND NOT START WITH \"0\"");
        }
        return numbers;
    }

    public static boolean checkDateOrder(Date startDate, Date endDate) {
        if (startDate.before(endDate)) {
            return true;
        }
        throw new IllegalArgumentException("START DATE MUST BE BEFORE END DATE. " + startDate + " falls after "+endDate+".");
    }
}

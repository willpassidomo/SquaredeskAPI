/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn3.test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class testJunk {
    static List<String> stuff = new ArrayList<String>();
    static List<String> stuff3 = new ArrayList<String>();
    
    public static void main (String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(UUID.fromString(uuid+""));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.KnowledgeEngine;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author willpassidomo
 */
class QueryEngineException extends Exception {
    private String description;
    private int queryNumber;
    private String triple;
    
    public QueryEngineException(String description, int count) {
        this.description = description;
        this.queryNumber = count;
        System.out.println(description + "location- triple #"+count);
    }
    
    public QueryEngineException(String description, int count, String triple) {
        this.description = description;
        this.queryNumber = count;
        this.triple = triple;
        System.out.println(description + "location- triple #"+count);
        resolveImport(triple.trim());

    }
    
    private void resolveImport(String triple) {
        System.out.println("\tyou entered '"+triple+"' Would you like to resolve this query? Enter:\n\t(1) yes\n\t(2) no");
        Scanner scanner = new Scanner(System.in);
        try{
          switch(scanner.nextInt()) {
            case 1:
                System.out.println("\tenter corrected query for'"+triple+"':");
                scanner = new Scanner(System.in);
                String entry = scanner.nextLine();
                QueryEngine.executeQuery(entry);
                return;
            case 2:
                return;
            default:
                System.out.println("incorrect entry, please try again\t");
                resolveImport(triple);
        }
        }
        catch (InputMismatchException e) {
            System.out.println("incorrect entry, please try again\t");
            resolveImport(triple);
        }
    }   
    
    public String getDescription() {
        return this.description;
    }
    
    

}

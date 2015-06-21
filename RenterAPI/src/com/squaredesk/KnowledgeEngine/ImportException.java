/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.KnowledgeEngine;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author willpassidomo
 */
class ImportException extends Exception {
    private String description;
    private int tripleNumber;
    private static Set<ImportException> exceptionItems = new HashSet<>();
    
    public ImportException(String description, int count) {
        this.description = description;
        this.tripleNumber = count;
        System.out.println(description + "location- triple #"+count);
    }
    
    public ImportException(String descrition, int count, String triple){
        this.description = description;
        this.tripleNumber = count;
        exceptionItems.add(this);
        System.out.println(description + " location- triple #"+count);
        resolveImport(triple.trim());
        
                
    }
    
    private void resolveImport(String triple) {
        System.out.println("\tyou entered '"+triple+"' Would you like to resolve this import? Enter:\n\t(1) yes\n\t(2) no");
        Scanner scanner = new Scanner(System.in);
        try{
          switch(scanner.nextInt()) {
            case 1:
                System.out.println("\tenter corrected triple for'"+triple+"':");
                scanner = new Scanner(System.in);
                String entry = scanner.nextLine();
                try{
                Importer.checkTriple(entry);
                }
                catch (ImportException e) {
                }
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
}

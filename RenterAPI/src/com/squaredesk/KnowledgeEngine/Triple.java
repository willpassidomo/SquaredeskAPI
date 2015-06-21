/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.KnowledgeEngine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  an Object which is an instance of a Subject (Node Object), a Predicate (Predicate Object)
 * and an Object (Node Object). A Triple Object represents a single fact, which can be stated in 
 * plain speech, as it is represented in the Triple's identifier
 * 
 * @author willpassidomo
 */
public class Triple implements Comparable {
    private String identifier;
    
    /**
     *  a Node Object which represents the Subject of the Triple
     */
    
    private Node subject;
    
    /**
     *  a Node Object which represents the Object of the Triple
     */
    
    private Node object;   
    
    /**
     *  a Predicate Object which represents the Predicate of the Triple
     */
    private Predicate predicate;
    
    /**
     * the cache for the existing, instatiated Predicate Objects per the Flyweight Pattern
     */
    
    private static Set<Triple> triples = new HashSet<>();
    
    /**
     *  constructor for a Triple Object with the passed Subject (Node Object), Predicate (Predicate Object), and Object (Node Object)
     * 
     * 
     * 
     * @param subject   the Subject of the Triple
     * @param predicate the Predicate of the Triple
     * @param object    the Object of the Triple
     * @throws ImportException  is thrown under when the reserved keyword "?" is attempted
     * based as a parameter (either a Subject, Predicate or Object)
     */
    protected Triple(Node subject, Predicate predicate, Node object)throws ImportException{
        if (subject.equals("?") || predicate.equals("?") || object.equals("?")){
            throw new ImportException("Illegal use of reserved character Subject/Predicate/Object: '?' is not a valid object",0,(subject+" "+predicate+" "+object));
        }
        this.subject = subject;
        this.predicate = predicate; 
        this.object = object;
        this.identifier = this.toString();        
    }
    
    protected Triple(Node subject, Predicate predicate, Node object, int i) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = object;
        this.identifier = this.toString();
    }
    
    private Triple toTriple(Node subject, Predicate predicate, Node object) {
        Triple newTriple = null;
        try {
            newTriple = new Triple(subject,predicate,object);
        } catch (ImportException ex) {
            System.out.println(ex.toString());
            ex.printStackTrace();
        }
        return newTriple;
    }
    
    public static Triple newSearchTriple(Node subject, Predicate predicate, Node object) {
        Triple newTriple = new Triple(subject, predicate, object,1);
        return newTriple;
    }
    
    /**
     *  is a method which is used to instantiate a new Triple Object from String representations of 
     * a Subject, a Predicate and an Object. this method is used to return a Triple object based on passed, Parsed text
     * from an imported file. two Node Objects and a Predicate Object are instantiated from the respected Parameters, then passed to
     * the Triple constructor to instantiate a new Triple Object. The newly instatiated
     * Triple is then checked against a chache of existing Triple Objects to insure
     * duplicate Triple's are not passed to the KnowledgeGraph when it calls the importTriples() method
     * 
     * 
     * 
     * @param subject the Subject of the Triple in String form
     * @param predicate the Predicate of the Triple in String form
     * @param object the Object of the Triple in String form
     * @param count represents the nth position of the Triples within the current import document
     * @throws ImportException is thrown under when the reserved keyword "?" is attempted
     * based as a parameter (either a Subject, Predicate or Object)
     */
    public static void newTriple(String subject, String predicate, String object)throws ImportException{
        Node subjectN = Node.newNode(subject);
        Predicate predicateN = Predicate.newPredicate(predicate);
        Node objectN = Node.newNode(object);
        Triple newTriple = new Triple(subjectN, predicateN, objectN);       
        triples.add(newTriple);
        KnowledgeGraph.getInstance().importTriples(newTriple);
    }

        /**
     *  is a method which is used to instantiate a new Triple Object from String representations of 
     * a Subject, a Predicate and an Object. this method is used to return a Triple object based on passed, Parsed text
     * from an imported file. two Node Objects and a Predicate Object are instantiated from the respected Parameters, then passed to
     * the Triple constructor to instantiate a new Triple Object. The newly instatiated
     * Triple is then checked against a chache of existing Triple Objects to insure
     * duplicate Triple's are not passed to the KnowledgeGraph when it calls the importTriples() method
     * 
     * 
     * 
     * @param subject the Subject of the Triple
     * @param predicate the Predicate of the Triple
     * @param object the Object of the Triple
     */
    
    public static Triple newTriple(Node subject, Predicate predicate, Node object) {
        Triple newTriple = null;
        try {
            newTriple = new Triple(subject, predicate, object);
        } catch (ImportException ex) {
        }
        triples.add(newTriple);
        KnowledgeGraph.getInstance().importTriples(newTriple);
        return newTriple;
    }
    
    /**
     *  returns the identifier associated with the Triple object i.e the 
     * literal, verbal representation of the "fact" represented by the given
     * Triple Object consisting of a Subject, Predicte and Object
     * 
     * @return the identifier String for the particular Triple
     */
    public String getIdentifier() {
        return this.identifier;
    }
    
    /**
     *  returns a set of all imported Triples. as Triples are created through
     * the newTriple(String subject, String predicate, String object) method, they are
     * added to a Set which is returned through this method
     * 
     * @return a Set of all imported Triples
     */
    protected static Set<Triple> getTriples() {
        return triples;
    }
    
    /**
     * is @Override so that the toString() method returns a syntactically identical
     * String representation of the Triple's identifier field
     * @return a String representation of the Triple, identical to the identifier
     */
    
    @Override
    public String toString() {
        return subject.getIdentifier()+" "+predicate.getIdentifier()+" "+ object.getIdentifier();
    }
    
    /**
     *  is to provide a representation of what a Triple's identifier would be,
     * given the parameters, without instantiating a new Triple Object. this method is used
     * to run against a Set of Triple Objects to test if a Triple with the same Subject, Predicate
     * and Object already exist within the Set
     * 
     * @param subject the Subject of the Triple in String form
     * @param predicate the Predicate of the Triple in String form
     * @param object the Object of the Triple in String form
     * @return a String representation of the Triple
     */
    protected static String tripleToString(Node subject, Predicate predicate, Node object) {
        return subject.getIdentifier()+" "+predicate.getIdentifier()+" "+ object.getIdentifier();
    }
    
    /**
     *  returns the Triple's Object (Node Object)
     * @return a Node Instance, the Triple's "Object"
     */
    public Node getObject() {
        return this.object;
    }
    
    /**
     * returns the Triple's Predicate (Predicate Object)
     * @return a Predicate Instance, the Triple's "Predicate"
     */
    public Predicate getPredicate() {
        return this.predicate;
    }
    
    /**
     *returns the Triple's Subject (Node Object)
     * @return  a Node Instance, the Triple's "Subject"
     */
    public Node getSubject() {
        return this.subject;
    }

    /**
     * The compareTo() is @Override so that the Triple's toString() method is
     * compared to the passed Object's toString method using the compareTo(String) method
     * @param o the reference object with which to compare.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Object o) {
        return this.toString().compareToIgnoreCase(o.toString());
        }
    
    /**
     * the equals(Object o) is @Override so that the Triple's to String() method is
     * tested for equality against the passed Object's to String method using using the equals(Strin)
     * method
     * @param o the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    
    @Override
    public boolean equals(Object o) {
        return this.toString().equals(o.toString());
    }

    

}

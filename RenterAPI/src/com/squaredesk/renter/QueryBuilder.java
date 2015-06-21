/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.renter;

import com.squaredesk.common.Rating;
import com.squaredesk.common.Searchable;
import com.squaredesk.provider.Address;
import com.squaredesk.provider.CommonFeature;
import com.squaredesk.provider.Coordinate;
import com.squaredesk.provider.FacilityType;
import com.squaredesk.provider.Feature;
import com.squaredesk.provider.OfficeSpace;
import com.squaredesk.provider.PrivateFeature;
import com.squaredesk.KnowledgeEngine.KnowledgeGraph;
import com.squaredesk.KnowledgeEngine.Node;
import com.squaredesk.KnowledgeEngine.Predicate;
import com.squaredesk.KnowledgeEngine.Triple;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

/**
 *
 * @author willpassidomo
 */
public class QueryBuilder {
//    private List<Triple> triples;
    private static HashMap<String, OfficeSpace> officeSpaces = new HashMap<String, OfficeSpace>();
    private List<CommonFeature> commonFeatures;
    private List<PrivateFeature> privateFeatures;
    private Coordinate coordinates;
    private Address address;
    private int rating;
    private FacilityType facilityType = null;
    private Date timeExecuted;
    private Date startDate;
    private Date endDate;
    private Set<Searchable> conditions;
    
    private static Node wildcard = Node.newNode("?");
    
    private QueryBuilder() {
        conditions = new TreeSet<Searchable>();
        commonFeatures = new ArrayList<CommonFeature>();
        privateFeatures = new ArrayList<PrivateFeature>();
    }
    
    public static QueryBuilder newQueryBuilder(Renter renter) throws RenterDoesNotExistError {
        if (Renter.getRenters().contains(renter))
        {
            return new QueryBuilder();
        }
        else {
            throw new RenterDoesNotExistError();
        }
    }
    

    
    public void addTCondition(Searchable condition) {
        this.conditions.add(condition);
    }
 
    public void setStartDate (Date startDate) {
        this.startDate = startDate;
    }
    
    public void setEndDate (Date endDate) {
        this.endDate = endDate;
    }
    
    public Set<OfficeSpace> executeTQuery() {
        KnowledgeGraph kg = KnowledgeGraph.getInstance();
        Set<Node> subjects = new TreeSet<Node>();
        for (Triple triple: kg.getTriples()) {
            subjects.add(triple.getSubject());
        }
        
        for (Searchable condition: this.conditions) {
            System.out.println(newSearchTriple(condition));
            Set<Node> resultSubjects = new TreeSet<Node>();
            Set<Triple> resultTriples = kg.executeQuery(newSearchTriple(condition));
            if (resultTriples != null) {
            for (Triple triple: kg.executeQuery(newSearchTriple(condition))) {
                resultSubjects.add(triple.getSubject());
            }
            }
            subjects.retainAll(resultSubjects);
        }
        Set<OfficeSpace> results = new TreeSet<OfficeSpace>();
        for(OfficeSpace os: QueryBuilder.getOfficeSpaces(subjects)) {
            if(this.startDate == null & this.endDate == null) {
                results.add(os);
            } else {
            if(this.startDate!= null && this.endDate != null) {
            if (Scheduler.getInstance().isAvailable(os, this.startDate, this.endDate)) {
                results.add(os);
            }
        } else {
                if (this.startDate != null && this.endDate == null) {
                    if (Scheduler.getInstance().isAvailable(os, startDate, startDate)){
                        results.add(os);
                    }
                } else { 
                    if (this.endDate != null && this.startDate == null) {
                        if (Scheduler.getInstance().isAvailable(os, endDate, endDate)) {
                            results.add(os);
                        }
                    }
                }
            }
            }
            }
        return  results;
    }
    

    private Triple newSearchTriple(Searchable tripleable) {
        if (tripleable == null) {
            return null;
        }
        Node subject = Node.newNode(tripleable.getObject());
        Predicate predicate = Predicate.newPredicate(tripleable.getPredicate());
        Node object = wildcard;
        Triple newTriple = Triple.newSearchTriple(object, predicate, subject);
        return newTriple;    }
    
    private List<Triple> newSearchTriples(List<? extends Searchable> tripleables) {
        List<Triple> triples = new ArrayList<Triple>();
        for (Searchable tripleable: tripleables) {
            triples.add(newSearchTriple(tripleable));
        }
        return triples;
    }
    
    private Triple newSearchTriple(int i) {
        Node subject = Node.newNode(rating+ " stars");
        Predicate predicate = Predicate.newPredicate("rating_of");
        Node object = wildcard;
        Triple newTriple = Triple.newSearchTriple(object, predicate, subject);
        return newTriple;
    }
    
    public static void updateIndexs(OfficeSpace officeSpace) {
        //TODO
    }
    
    //goes through and remakes all the indexs with the current officeSpaces
    
    public static void refreshIndexs() {
        Set<Triple> triples = new TreeSet<Triple>();
        for (OfficeSpace officeSpace: OfficeSpace.getOfficeSpaces()) {
            triples.add(newTriple(officeSpace, officeSpace.getFacilityType()));
            triples.add(newTriple(officeSpace, officeSpace.getCoordinates()));
            triples.addAll(newTriples(officeSpace, officeSpace.getCommonFeatures()));
            triples.addAll(newTriples(officeSpace, officeSpace.getPrivateFeatures()));
            triples.add(newTriple(officeSpace, (int)officeSpace.getAverageRating()));
            officeSpaces.put(officeSpace.getGUID()+"", officeSpace);
        }
        KnowledgeGraph.getInstance().importTriples(triples);
    }
    
    /**
     * FOR ADDING NEW OFFICESPACE TO THE KNOWLEDGE ENGINE
     * @param tripleable
     * @return 
     */
    private static Triple newTriple(OfficeSpace officeSpace, Searchable tripleable) {
        if (tripleable == null) {
            return null;
        }
        Node subject = Node.newNode(tripleable.getObject());
        Predicate predicate = Predicate.newPredicate(tripleable.getPredicate());
        Node object = Node.newNode(officeSpace.getGUID()+"");
        Triple newTriple = Triple.newTriple(object, predicate, subject);
        return newTriple;
    }
    
    private static List<Triple> newTriples(OfficeSpace officeSpace, List<? extends Searchable> tripleables) {
        List<Triple> triples = new ArrayList<Triple>();
        for (Searchable tripleable: tripleables) {
            triples.add(newTriple(officeSpace, tripleable));
        }
        return triples;
    }
        
    private static Triple newTriple(OfficeSpace officeSpace, int rating) {
        Node subject = Node.newNode(rating+ " stars");
        Predicate predicate = Predicate.newPredicate("rating_of");
        Node object = Node.newNode(officeSpace.getGUID()+"");
        Triple newTriple = Triple.newTriple(object, predicate, subject);
        return newTriple;
    }
    
    private static Set<OfficeSpace> getOfficeSpaces(Set<Node> nodes) {
        Set<OfficeSpace> results = new TreeSet<OfficeSpace>();
        for (Node node: nodes) {
            results.add(OfficeSpace.getOfficeSpaceByID(UUID.fromString(node.getIdentifier())));
        }
        return results;
    }
    
}

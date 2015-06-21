/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cscie97.asn2.sharedesk.provider;

import java.net.URI;

/**
 *
 * @author willpassidomo
 */
public class Imagec {
    private String name;
    private String referance;
    private URI uri;
    
    private Imagec(String name, String referance, URI uri) {
        this.name = name;
        this.referance = referance;
        this.uri = uri;
    }
    
    public static Imagec newImage(String name, String referance, URI uri) {
        Imagec image = new Imagec(name, referance, uri);
        return image;      
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the referance
     */
    public String getReferance() {
        return referance;
    }

    /**
     * @param referance the referance to set
     */
    public void setReferance(String referance) {
        this.referance = referance;
    }

    /**
     * @return the uri
     */
    public URI getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(URI uri) {
        this.uri = uri;
    }
}

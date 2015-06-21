/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.squaredesk.RenterAPI;

import java.net.URI;
import java.net.URISyntaxException;

/**
 *
 * @author willpassidomo
 */
public class ImageDTO {
    private String name;
    private String description;
    private URI uri;
    
    public ImageDTO (String name, String description, String uri) throws URISyntaxException {
        this.uri = new URI((String)Validate.checkNotNull("uri", uri));
        this.description = (String)Validate.checkNotNull("description", description);
        this.name = (Validate.checkAllChars("name", name));
    }
    
    public ImageDTO (String name, String description, URI uri) throws URISyntaxException {
        this.uri = (URI)Validate.checkNotNull("uri", uri);
        this.description = (String)Validate.checkNotNull("description", description);
        this.name = (Validate.checkAllChars("name", name));
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
    
    @Override
    public String toString() {
        return "title: " + this.name +
                " description: " + this.description+
                " URI: " + this.uri;
    }
}

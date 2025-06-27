package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.Data;

/**
 * Request object for Permission operations (create, update).
 */
@Data
public class PermissionRequest {

    private Long id;
    private String name;
    private String description;
    private boolean active;

    /**
     * Default constructor.
     */
    public PermissionRequest() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id the ID of the permission
     * @param name the name of the permission
     * @param description the description of the permission
     * @param active whether the permission is active
     */
    public PermissionRequest(Long id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }
}
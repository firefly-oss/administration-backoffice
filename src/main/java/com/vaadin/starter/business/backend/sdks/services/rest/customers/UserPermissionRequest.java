package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.Data;

/**
 * Request object for UserPermission operations (create, update).
 */
@Data
public class UserPermissionRequest {

    private Long id;
    private String name;
    private String description;
    private boolean active;

    /**
     * Default constructor.
     */
    public UserPermissionRequest() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id the ID of the user permission
     * @param name the name of the user permission
     * @param description the description of the user permission
     * @param active whether the user permission is active
     */
    public UserPermissionRequest(Long id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }
}
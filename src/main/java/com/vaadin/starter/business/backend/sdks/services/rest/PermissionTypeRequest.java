package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.Data;

/**
 * Request object for PermissionType operations (create, update).
 */
@Data
public class PermissionTypeRequest {

    private Long id;
    private String name;
    private String description;
    private boolean active;

    /**
     * Default constructor.
     */
    public PermissionTypeRequest() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id the ID of the permission type
     * @param name the name of the permission type
     * @param description the description of the permission type
     * @param active whether the permission type is active
     */
    public PermissionTypeRequest(Long id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }
}
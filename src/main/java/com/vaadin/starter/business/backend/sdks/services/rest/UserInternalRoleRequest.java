package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.Data;

/**
 * Request object for UserInternalRole operations (create, update).
 */
@Data
public class UserInternalRoleRequest {

    private Long id;
    private String name;
    private String description;
    private boolean active;

    /**
     * Default constructor.
     */
    public UserInternalRoleRequest() {
    }

    /**
     * Constructor with parameters.
     *
     * @param id the ID of the user internal role
     * @param name the name of the user internal role
     * @param description the description of the user internal role
     * @param active whether the user internal role is active
     */
    public UserInternalRoleRequest(Long id, String name, String description, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
    }
}
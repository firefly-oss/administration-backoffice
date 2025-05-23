package com.vaadin.starter.business.backend.dto.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for Role.
 */
public class RoleDTO {

    private String name;
    private String description;
    private boolean isActive;
    private List<String> permissions;

    /**
     * Default constructor.
     */
    public RoleDTO() {
        this.permissions = new ArrayList<>();
    }

    /**
     * Constructor with all fields.
     *
     * @param name        the role name
     * @param description the role description
     * @param isActive    whether the role is active
     * @param permissions the list of permissions
     */
    public RoleDTO(String name, String description, boolean isActive, List<String> permissions) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.permissions = permissions != null ? new ArrayList<>(permissions) : new ArrayList<>();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions != null ? new ArrayList<>(permissions) : new ArrayList<>();
    }

    /**
     * Get the number of permissions.
     *
     * @return the number of permissions
     */
    public int getPermissionCount() {
        return permissions.size();
    }
}
package com.vaadin.starter.business.dummy;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class for Role.
 */
public class Role {
    private String name;
    private String description;
    private boolean isActive;
    private List<String> permissions;

    /**
     * Default constructor.
     */
    public Role() {
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
    public Role(String name, String description, boolean isActive, List<String> permissions) {
        this.name = name;
        this.description = description;
        this.isActive = isActive;
        this.permissions = permissions != null ? new ArrayList<>(permissions) : new ArrayList<>();
    }

    /**
     * Get the role name.
     *
     * @return the role name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the role description.
     *
     * @return the role description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Check if the role is active.
     *
     * @return true if the role is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Get the list of permissions.
     *
     * @return the list of permissions
     */
    public List<String> getPermissions() {
        return permissions;
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
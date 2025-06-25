package com.vaadin.starter.business.dummy;

import java.time.LocalDate;

/**
 * Entity class for Security Policy.
 */
public class Policy {
    private String name;
    private String category;
    private String description;
    private boolean isActive;
    private LocalDate lastUpdated;

    /**
     * Default constructor.
     */
    public Policy() {
    }

    /**
     * Constructor with all fields.
     *
     * @param name        the policy name
     * @param category    the policy category
     * @param description the policy description
     * @param isActive    whether the policy is active
     * @param lastUpdated the date when the policy was last updated
     */
    public Policy(String name, String category, String description, boolean isActive, LocalDate lastUpdated) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.isActive = isActive;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Get the policy name.
     *
     * @return the policy name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the policy category.
     *
     * @return the policy category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the policy description.
     *
     * @return the policy description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Check if the policy is active.
     *
     * @return true if the policy is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Get the date when the policy was last updated.
     *
     * @return the last updated date
     */
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
}
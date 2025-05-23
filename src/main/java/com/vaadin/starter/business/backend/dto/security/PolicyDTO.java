package com.vaadin.starter.business.backend.dto.security;

import java.time.LocalDate;

/**
 * Data Transfer Object for Security Policy.
 */
public class PolicyDTO {

    private String name;
    private String category;
    private String description;
    private boolean isActive;
    private LocalDate lastUpdated;

    /**
     * Default constructor.
     */
    public PolicyDTO() {
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
    public PolicyDTO(String name, String category, String description, boolean isActive, LocalDate lastUpdated) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.isActive = isActive;
        this.lastUpdated = lastUpdated;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
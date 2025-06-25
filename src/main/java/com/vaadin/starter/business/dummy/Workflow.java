package com.vaadin.starter.business.dummy;

import java.time.LocalDate;
import java.util.List;

/**
 * Entity class for Workflow.
 */
public class Workflow {
    private String id;
    private String name;
    private String description;
    private String status;
    private String category;
    private int stepsCount;
    private List<String> roles;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public Workflow() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id           the workflow ID
     * @param name         the workflow name
     * @param description  the workflow description
     * @param status       the workflow status (Active, Draft, Deprecated)
     * @param category     the workflow category
     * @param stepsCount   the number of steps in the workflow
     * @param roles        the roles involved in the workflow
     * @param lastModified the date when the workflow was last modified
     */
    public Workflow(String id, String name, String description, String status, String category, int stepsCount, List<String> roles, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.stepsCount = stepsCount;
        this.roles = roles;
        this.lastModified = lastModified;
    }

    /**
     * Get the workflow ID.
     *
     * @return the workflow ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the workflow name.
     *
     * @return the workflow name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the workflow description.
     *
     * @return the workflow description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the workflow status.
     *
     * @return the workflow status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the workflow category.
     *
     * @return the workflow category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the number of steps in the workflow.
     *
     * @return the steps count
     */
    public int getStepsCount() {
        return stepsCount;
    }

    /**
     * Get the roles involved in the workflow.
     *
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Get the date when the workflow was last modified.
     *
     * @return the last modified date
     */
    public LocalDate getLastModified() {
        return lastModified;
    }

    /**
     * Check if the workflow is active.
     *
     * @return true if the status is "Active", false otherwise
     */
    public boolean isActive() {
        return "Active".equals(status);
    }
}
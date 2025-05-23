package com.vaadin.starter.business.backend.dto.systemconfig;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Transfer Object for Workflow.
 */
public class WorkflowDTO {

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
    public WorkflowDTO() {
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
    public WorkflowDTO(String id, String name, String description, String status, String category, int stepsCount, List<String> roles, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.stepsCount = stepsCount;
        this.roles = roles;
        this.lastModified = lastModified;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    public void setStepsCount(int stepsCount) {
        this.stepsCount = stepsCount;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
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
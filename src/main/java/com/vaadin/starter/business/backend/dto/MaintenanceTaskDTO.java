package com.vaadin.starter.business.backend.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Data Transfer Object for MaintenanceTask.
 */
public class MaintenanceTaskDTO {

    private Long id;
    private String taskName;
    private String description;
    private String status;
    private String database;
    private String frequency;
    private LocalTime scheduledTime;
    private String duration;
    private LocalDate lastRun;
    private String managedBy;

    /**
     * Default constructor.
     */
    public MaintenanceTaskDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id            the task ID
     * @param taskName      the task name
     * @param description   the description
     * @param status        the status
     * @param database      the database
     * @param frequency     the frequency
     * @param scheduledTime the scheduled time
     * @param duration      the duration
     * @param lastRun       the last run date
     * @param managedBy     the person who manages it
     */
    public MaintenanceTaskDTO(Long id, String taskName, String description, String status,
                            String database, String frequency, LocalTime scheduledTime,
                            String duration, LocalDate lastRun, String managedBy) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.database = database;
        this.frequency = frequency;
        this.scheduledTime = scheduledTime;
        this.duration = duration;
        this.lastRun = lastRun;
        this.managedBy = managedBy;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(LocalTime scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDate getLastRun() {
        return lastRun;
    }

    public void setLastRun(LocalDate lastRun) {
        this.lastRun = lastRun;
    }

    public String getManagedBy() {
        return managedBy;
    }

    public void setManagedBy(String managedBy) {
        this.managedBy = managedBy;
    }
}
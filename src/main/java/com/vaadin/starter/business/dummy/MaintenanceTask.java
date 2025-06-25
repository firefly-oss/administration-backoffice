package com.vaadin.starter.business.dummy;

import java.time.LocalDate;
import java.time.LocalTime;

public class MaintenanceTask {

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

    public MaintenanceTask(Long id, String taskName, String description, String status, 
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

    public Long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getDatabase() {
        return database;
    }

    public String getFrequency() {
        return frequency;
    }

    public LocalTime getScheduledTime() {
        return scheduledTime;
    }

    public String getDuration() {
        return duration;
    }

    public LocalDate getLastRun() {
        return lastRun;
    }

    public String getManagedBy() {
        return managedBy;
    }
}
/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.vaadin.starter.business.backend;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class MaintenanceTask {

    private UUID id;
    private String taskName;
    private String description;
    private String status;
    private String database;
    private String frequency;
    private LocalTime scheduledTime;
    private String duration;
    private LocalDate lastRun;
    private String managedBy;

    public MaintenanceTask(UUID id, String taskName, String description, String status, 
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

    public UUID getId() {
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
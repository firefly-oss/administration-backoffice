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


package com.vaadin.starter.business.backend.dto.customers;

import java.time.LocalDate;

/**
 * Data Transfer Object for OnboardingProcess.
 */
public class OnboardingProcessDTO {

    private String id;
    private String name;
    private String description;
    private String accountType;
    private int stepCount;
    private int completedSteps;
    private String status;
    private String assignedTo;
    private LocalDate createdDate;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public OnboardingProcessDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id             the process ID
     * @param name           the process name
     * @param description    the description
     * @param accountType    the account type
     * @param stepCount      the total number of steps in the process
     * @param completedSteps the number of completed steps
     * @param status         the status (Not Started, In Progress, On Hold, Completed)
     * @param assignedTo     the person assigned to this process
     * @param createdDate    the date the process was created
     * @param lastModified   the date the process was last modified
     */
    public OnboardingProcessDTO(String id, String name, String description, String accountType,
                              int stepCount, int completedSteps, String status, String assignedTo,
                              LocalDate createdDate, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.accountType = accountType;
        this.stepCount = stepCount;
        this.completedSteps = completedSteps;
        this.status = status;
        this.assignedTo = assignedTo;
        this.createdDate = createdDate;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public int getCompletedSteps() {
        return completedSteps;
    }

    public void setCompletedSteps(int completedSteps) {
        this.completedSteps = completedSteps;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
}
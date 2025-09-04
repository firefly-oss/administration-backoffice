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

/**
 * Domain object representing a customer onboarding process.
 */
public class OnboardingProcess {

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
    public OnboardingProcess(String id, String name, String description, String accountType,
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAccountType() {
        return accountType;
    }

    public int getStepCount() {
        return stepCount;
    }

    public int getCompletedSteps() {
        return completedSteps;
    }

    public double getCompletionPercentage() {
        return (double) completedSteps / stepCount;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }
}
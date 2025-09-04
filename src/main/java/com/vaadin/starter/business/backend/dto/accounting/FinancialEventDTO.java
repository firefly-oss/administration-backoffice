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


package com.vaadin.starter.business.backend.dto.accounting;

import java.time.LocalDate;

/**
 * Data Transfer Object for FinancialEvent.
 */
public class FinancialEventDTO {

    private String id;
    private String title;
    private String description;
    private LocalDate date;
    private String category;
    private double amount;
    private String status;
    private String assignedTo;

    /**
     * Default constructor.
     */
    public FinancialEventDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id          the event ID
     * @param title       the event title
     * @param description the description
     * @param date        the date of the event
     * @param category    the category of the event
     * @param amount      the amount associated with the event
     * @param status      the status of the event
     * @param assignedTo  the person assigned to the event
     */
    public FinancialEventDTO(String id, String title, String description, LocalDate date,
                           String category, double amount, String status, String assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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
}
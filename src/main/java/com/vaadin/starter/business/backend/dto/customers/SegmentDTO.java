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
 * Data Transfer Object for Segment.
 */
public class SegmentDTO {

    private String id;
    private String name;
    private String description;
    private int customerCount;
    private double averageBalance;
    private String riskProfile;
    private boolean active;
    private LocalDate createdDate;

    /**
     * Default constructor.
     */
    public SegmentDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id             the segment ID
     * @param name           the segment name
     * @param description    the description
     * @param customerCount  the number of customers in this segment
     * @param averageBalance the average balance of customers in this segment
     * @param riskProfile    the risk profile (Low, Medium, Medium-High, High)
     * @param active         whether the segment is active
     * @param createdDate    the date the segment was created
     */
    public SegmentDTO(String id, String name, String description, int customerCount,
                    double averageBalance, String riskProfile, boolean active,
                    LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.customerCount = customerCount;
        this.averageBalance = averageBalance;
        this.riskProfile = riskProfile;
        this.active = active;
        this.createdDate = createdDate;
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

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public double getAverageBalance() {
        return averageBalance;
    }

    public void setAverageBalance(double averageBalance) {
        this.averageBalance = averageBalance;
    }

    public String getRiskProfile() {
        return riskProfile;
    }

    public void setRiskProfile(String riskProfile) {
        this.riskProfile = riskProfile;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
}
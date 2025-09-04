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


package com.vaadin.starter.business.backend.dto.reports;

import java.time.LocalDate;

/**
 * Data Transfer Object for Report.
 */
public class ReportDTO {

    private String id;
    private String name;
    private String description;
    private String status;
    private String category;
    private String dataSource;
    private String outputFormat;
    private boolean includeCharts;
    private boolean scheduledDelivery;
    private String createdBy;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public ReportDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id                the report ID
     * @param name              the report name
     * @param description       the report description
     * @param status            the report status (Published, Draft, etc.)
     * @param category          the report category
     * @param dataSource        the data source for the report
     * @param outputFormat      the output format of the report
     * @param includeCharts     whether to include charts in the report
     * @param scheduledDelivery whether the report has scheduled delivery
     * @param createdBy         the email of the person who created the report
     * @param lastModified      the date the report was last modified
     */
    public ReportDTO(String id, String name, String description, String status, String category,
                    String dataSource, String outputFormat, boolean includeCharts,
                    boolean scheduledDelivery, String createdBy, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.dataSource = dataSource;
        this.outputFormat = outputFormat;
        this.includeCharts = includeCharts;
        this.scheduledDelivery = scheduledDelivery;
        this.createdBy = createdBy;
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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public boolean isIncludeCharts() {
        return includeCharts;
    }

    public void setIncludeCharts(boolean includeCharts) {
        this.includeCharts = includeCharts;
    }

    public boolean isScheduledDelivery() {
        return scheduledDelivery;
    }

    public void setScheduledDelivery(boolean scheduledDelivery) {
        this.scheduledDelivery = scheduledDelivery;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
}
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
 * Domain object representing a report.
 */
public class Report {

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
    public Report(String id, String name, String description, String status, String category,
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public String getDataSource() {
        return dataSource;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public boolean isIncludeCharts() {
        return includeCharts;
    }

    public boolean isScheduledDelivery() {
        return scheduledDelivery;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }
}
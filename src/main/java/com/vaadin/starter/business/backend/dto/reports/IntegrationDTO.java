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
 * Data Transfer Object for Integration.
 */
public class IntegrationDTO {

    private String id;
    private String name;
    private String description;
    private String status;
    private String type;
    private String endpoint;
    private String dataFormat;
    private String schedule;
    private boolean secureTransfer;
    private boolean requireAuthentication;
    private String configuredBy;
    private LocalDate lastSync;

    /**
     * Default constructor.
     */
    public IntegrationDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id                   the integration ID
     * @param name                 the integration name
     * @param description          the integration description
     * @param status               the integration status (Active, Inactive, etc.)
     * @param type                 the integration type
     * @param endpoint             the endpoint URL
     * @param dataFormat           the data format
     * @param schedule             the schedule for the integration
     * @param secureTransfer       whether to use secure transfer
     * @param requireAuthentication whether authentication is required
     * @param configuredBy         the email of the person who configured the integration
     * @param lastSync             the date of the last synchronization
     */
    public IntegrationDTO(String id, String name, String description, String status, String type,
                         String endpoint, String dataFormat, String schedule, boolean secureTransfer,
                         boolean requireAuthentication, String configuredBy, LocalDate lastSync) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
        this.endpoint = endpoint;
        this.dataFormat = dataFormat;
        this.schedule = schedule;
        this.secureTransfer = secureTransfer;
        this.requireAuthentication = requireAuthentication;
        this.configuredBy = configuredBy;
        this.lastSync = lastSync;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public boolean isSecureTransfer() {
        return secureTransfer;
    }

    public void setSecureTransfer(boolean secureTransfer) {
        this.secureTransfer = secureTransfer;
    }

    public boolean isRequireAuthentication() {
        return requireAuthentication;
    }

    public void setRequireAuthentication(boolean requireAuthentication) {
        this.requireAuthentication = requireAuthentication;
    }

    public String getConfiguredBy() {
        return configuredBy;
    }

    public void setConfiguredBy(String configuredBy) {
        this.configuredBy = configuredBy;
    }

    public LocalDate getLastSync() {
        return lastSync;
    }

    public void setLastSync(LocalDate lastSync) {
        this.lastSync = lastSync;
    }
}
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


package com.vaadin.starter.business.backend.dto.systemconfig;

import java.time.LocalDate;

/**
 * Data Transfer Object for Master Data Item.
 */
public class MasterDataItemDTO {

    private String id;
    private String name;
    private String description;
    private String status;
    private String category;
    private String dataType;
    private String format;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public MasterDataItemDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id           the master data item ID
     * @param name         the master data item name
     * @param description  the master data item description
     * @param status       the master data item status (Active, Pending, Deprecated)
     * @param category     the master data item category
     * @param dataType     the master data item data type
     * @param format       the master data item format
     * @param lastModified the date when the master data item was last modified
     */
    public MasterDataItemDTO(String id, String name, String description, String status, String category, String dataType, String format, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.dataType = dataType;
        this.format = format;
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

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
    
    /**
     * Check if the master data item is active.
     *
     * @return true if the status is "Active", false otherwise
     */
    public boolean isActive() {
        return "Active".equals(status);
    }
}
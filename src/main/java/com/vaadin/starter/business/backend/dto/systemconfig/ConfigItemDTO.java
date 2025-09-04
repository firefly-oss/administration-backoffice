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
 * Data Transfer Object for Configuration Item.
 */
public class ConfigItemDTO {

    private String id;
    private String name;
    private String description;
    private String status;
    private String category;
    private String value;
    private String scope;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public ConfigItemDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id           the configuration item ID
     * @param name         the configuration item name
     * @param description  the configuration item description
     * @param status       the configuration item status (Active, Pending, Deprecated)
     * @param category     the configuration item category
     * @param value        the configuration item value
     * @param scope        the configuration item scope
     * @param lastModified the date when the configuration item was last modified
     */
    public ConfigItemDTO(String id, String name, String description, String status, String category, String value, String scope, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.value = value;
        this.scope = scope;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }
    
    /**
     * Check if the configuration item is active.
     *
     * @return true if the status is "Active", false otherwise
     */
    public boolean isActive() {
        return "Active".equals(status);
    }
}
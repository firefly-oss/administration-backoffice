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
 * Entity class for Security Policy.
 */
public class Policy {
    private String name;
    private String category;
    private String description;
    private boolean isActive;
    private LocalDate lastUpdated;

    /**
     * Default constructor.
     */
    public Policy() {
    }

    /**
     * Constructor with all fields.
     *
     * @param name        the policy name
     * @param category    the policy category
     * @param description the policy description
     * @param isActive    whether the policy is active
     * @param lastUpdated the date when the policy was last updated
     */
    public Policy(String name, String category, String description, boolean isActive, LocalDate lastUpdated) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.isActive = isActive;
        this.lastUpdated = lastUpdated;
    }

    /**
     * Get the policy name.
     *
     * @return the policy name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the policy category.
     *
     * @return the policy category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the policy description.
     *
     * @return the policy description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Check if the policy is active.
     *
     * @return true if the policy is active, false otherwise
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Get the date when the policy was last updated.
     *
     * @return the last updated date
     */
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
}
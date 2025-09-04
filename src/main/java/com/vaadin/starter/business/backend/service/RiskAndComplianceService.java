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


package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.backend.Person;
import java.util.UUID;

/**
 * Service interface for risk and compliance-related functionality.
 */
public interface RiskAndComplianceService {

    /**
     * Get all fraud alerts.
     *
     * @return a collection of all fraud alerts
     */
    Collection<Person> getFraudAlerts();

    /**
     * Get a specific fraud alert by its ID.
     *
     * @param id the ID of the fraud alert to retrieve
     * @return the fraud alert with the specified ID, or null if not found
     */
    Person getFraudAlert(UUID id);

    /**
     * Get all compliance records.
     *
     * @return a collection of all compliance records
     */
    Collection<Person> getComplianceRecords();

    /**
     * Get a specific compliance record by its ID.
     *
     * @param id the ID of the compliance record to retrieve
     * @return the compliance record with the specified ID, or null if not found
     */
    Person getComplianceRecord(UUID id);

    /**
     * Get all risk models.
     *
     * @return a collection of all risk models
     */
    Collection<Person> getRiskModels();

    /**
     * Get a specific risk model by its ID.
     *
     * @param id the ID of the risk model to retrieve
     * @return the risk model with the specified ID, or null if not found
     */
    Person getRiskModel(UUID id);
}
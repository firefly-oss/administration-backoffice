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
import com.vaadin.starter.business.backend.Report;
import com.vaadin.starter.business.backend.Integration;

/**
 * Service interface for reports-related functionality.
 */
public interface ReportsService {

    /**
     * Get all reports.
     *
     * @return a collection of all reports
     */
    Collection<Report> getReports();

    /**
     * Get a specific report by its ID.
     *
     * @param id the ID of the report to retrieve
     * @return the report with the specified ID, or null if not found
     */
    Report getReport(String id);

    /**
     * Get all integrations.
     *
     * @return a collection of all integrations
     */
    Collection<Integration> getIntegrations();

    /**
     * Get a specific integration by its ID.
     *
     * @param id the ID of the integration to retrieve
     * @return the integration with the specified ID, or null if not found
     */
    Integration getIntegration(String id);
}
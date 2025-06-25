package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.dummy.Report;
import com.vaadin.starter.business.dummy.Integration;

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
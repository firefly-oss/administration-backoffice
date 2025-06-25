package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.dummy.Person;

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
    Person getFraudAlert(Long id);

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
    Person getComplianceRecord(Long id);

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
    Person getRiskModel(Long id);
}
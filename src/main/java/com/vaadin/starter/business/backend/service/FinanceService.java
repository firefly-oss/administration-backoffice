package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.backend.FinancialEvent;

/**
 * Service interface for finance-related functionality.
 */
public interface FinanceService {

    /**
     * Get all financial events.
     *
     * @return a collection of all financial events
     */
    Collection<FinancialEvent> getFinancialEvents();

    /**
     * Get a specific financial event by its ID.
     *
     * @param id the ID of the financial event to retrieve
     * @return the financial event with the specified ID, or null if not found
     */
    FinancialEvent getFinancialEvent(String id);
}
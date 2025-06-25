package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.dummy.Segment;
import com.vaadin.starter.business.dummy.OnboardingProcess;

/**
 * Service interface for customer-related functionality.
 */
public interface CustomersService {

    /**
     * Get all customer segments.
     *
     * @return a collection of all customer segments
     */
    Collection<Segment> getSegments();

    /**
     * Get a specific customer segment by its ID.
     *
     * @param id the ID of the segment to retrieve
     * @return the segment with the specified ID, or null if not found
     */
    Segment getSegment(String id);

    /**
     * Get all onboarding processes.
     *
     * @return a collection of all onboarding processes
     */
    Collection<OnboardingProcess> getOnboardingProcesses();

    /**
     * Get a specific onboarding process by its ID.
     *
     * @param id the ID of the onboarding process to retrieve
     * @return the onboarding process with the specified ID, or null if not found
     */
    OnboardingProcess getOnboardingProcess(String id);
}

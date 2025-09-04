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
import com.vaadin.starter.business.backend.Segment;
import com.vaadin.starter.business.backend.OnboardingProcess;

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

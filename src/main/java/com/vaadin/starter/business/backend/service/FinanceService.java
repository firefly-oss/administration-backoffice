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
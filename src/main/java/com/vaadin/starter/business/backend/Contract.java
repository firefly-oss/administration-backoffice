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
 * Domain object representing a contract.
 */
public class Contract {

    private String id;
    private String name;
    private String type;
    private String client;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String assignedTo;
    private String documentUrl;

    /**
     * Constructor with all fields.
     *
     * @param id          the contract ID
     * @param name        the contract name
     * @param type        the contract type
     * @param client      the client name
     * @param status      the contract status
     * @param startDate   the start date
     * @param endDate     the end date
     * @param assignedTo  the person assigned to the contract
     * @param documentUrl the document URL
     */
    public Contract(String id, String name, String type, String client, 
                   String status, LocalDate startDate, LocalDate endDate, 
                   String assignedTo, String documentUrl) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.client = client;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignedTo = assignedTo;
        this.documentUrl = documentUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getClient() {
        return client;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public boolean isActive() {
        return "Active".equals(status);
    }
}
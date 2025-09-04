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
import java.util.UUID;

/**
 * Domain object representing a service provider.
 */
public class ServiceProvider {

    private UUID id;
    private String name;
    private String description;
    private String status;
    private String serviceType;
    private String contactPerson;
    private String contactEmail;
    private LocalDate contractStart;
    private LocalDate contractExpiry;
    private String slaLevel;

    /**
     * Constructor with all fields.
     *
     * @param id            the provider ID
     * @param name          the provider name
     * @param description   the description
     * @param status        the status (Active, Pending, Terminated)
     * @param serviceType   the service type
     * @param contactPerson the contact person name
     * @param contactEmail  the contact email
     * @param contractStart the contract start date
     * @param contractExpiry the contract expiry date
     * @param slaLevel      the SLA level
     */
    public ServiceProvider(UUID id, String name, String description, String status, 
                          String serviceType, String contactPerson, String contactEmail, 
                          LocalDate contractStart, LocalDate contractExpiry, String slaLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.serviceType = serviceType;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contractStart = contractStart;
        this.contractExpiry = contractExpiry;
        this.slaLevel = slaLevel;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public LocalDate getContractStart() {
        return contractStart;
    }

    public LocalDate getContractExpiry() {
        return contractExpiry;
    }

    public String getSlaLevel() {
        return slaLevel;
    }
}
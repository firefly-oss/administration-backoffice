package com.vaadin.starter.business.backend;

import java.time.LocalDate;

/**
 * Domain object representing a service provider.
 */
public class ServiceProvider {

    private Long id;
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
    public ServiceProvider(Long id, String name, String description, String status, 
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

    public Long getId() {
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
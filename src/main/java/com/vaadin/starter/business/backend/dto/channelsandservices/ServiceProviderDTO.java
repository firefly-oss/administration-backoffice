package com.vaadin.starter.business.backend.dto.channelsandservices;

import java.time.LocalDate;

/**
 * Data Transfer Object for ServiceProvider.
 */
public class ServiceProviderDTO {

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
     * Default constructor.
     */
    public ServiceProviderDTO() {
    }

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
    public ServiceProviderDTO(Long id, String name, String description, String status, 
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

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public LocalDate getContractStart() {
        return contractStart;
    }

    public void setContractStart(LocalDate contractStart) {
        this.contractStart = contractStart;
    }

    public LocalDate getContractExpiry() {
        return contractExpiry;
    }

    public void setContractExpiry(LocalDate contractExpiry) {
        this.contractExpiry = contractExpiry;
    }

    public String getSlaLevel() {
        return slaLevel;
    }

    public void setSlaLevel(String slaLevel) {
        this.slaLevel = slaLevel;
    }
}
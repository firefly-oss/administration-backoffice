package com.vaadin.starter.business.backend.dto.products;

import java.time.LocalDate;

/**
 * Data Transfer Object for Contract.
 */
public class ContractDTO {

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
     * Default constructor.
     */
    public ContractDTO() {
    }

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
    public ContractDTO(String id, String name, String type, String client, 
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

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public boolean isActive() {
        return "Active".equals(status);
    }
}
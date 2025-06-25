package com.vaadin.starter.business.dummy;

import java.time.LocalDate;

/**
 * Domain object representing an integration.
 */
public class Integration {

    private String id;
    private String name;
    private String description;
    private String status;
    private String type;
    private String endpoint;
    private String dataFormat;
    private String schedule;
    private boolean secureTransfer;
    private boolean requireAuthentication;
    private String configuredBy;
    private LocalDate lastSync;

    /**
     * Constructor with all fields.
     *
     * @param id                   the integration ID
     * @param name                 the integration name
     * @param description          the integration description
     * @param status               the integration status (Active, Inactive, etc.)
     * @param type                 the integration type
     * @param endpoint             the endpoint URL
     * @param dataFormat           the data format
     * @param schedule             the schedule for the integration
     * @param secureTransfer       whether to use secure transfer
     * @param requireAuthentication whether authentication is required
     * @param configuredBy         the email of the person who configured the integration
     * @param lastSync             the date of the last synchronization
     */
    public Integration(String id, String name, String description, String status, String type,
                      String endpoint, String dataFormat, String schedule, boolean secureTransfer,
                      boolean requireAuthentication, String configuredBy, LocalDate lastSync) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.type = type;
        this.endpoint = endpoint;
        this.dataFormat = dataFormat;
        this.schedule = schedule;
        this.secureTransfer = secureTransfer;
        this.requireAuthentication = requireAuthentication;
        this.configuredBy = configuredBy;
        this.lastSync = lastSync;
    }

    public String getId() {
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

    public String getType() {
        return type;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean isSecureTransfer() {
        return secureTransfer;
    }

    public boolean isRequireAuthentication() {
        return requireAuthentication;
    }

    public String getConfiguredBy() {
        return configuredBy;
    }

    public LocalDate getLastSync() {
        return lastSync;
    }
}
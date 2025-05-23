package com.vaadin.starter.business.backend;

import java.time.LocalDateTime;

/**
 * Domain object representing a channel integration.
 */
public class Channel {

    private Long id;
    private String name;
    private String description;
    private boolean active;
    private String integrationType;
    private LocalDateTime lastUpdated;
    private String endpoint;
    private String securityLevel;

    /**
     * Constructor with all fields.
     *
     * @param id             the channel ID
     * @param name           the channel name
     * @param description    the description
     * @param active         whether the channel is active
     * @param integrationType the integration type
     * @param lastUpdated    the last updated timestamp
     * @param endpoint       the endpoint URL
     * @param securityLevel  the security level
     */
    public Channel(Long id, String name, String description, boolean active, 
                  String integrationType, LocalDateTime lastUpdated, 
                  String endpoint, String securityLevel) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.integrationType = integrationType;
        this.lastUpdated = lastUpdated;
        this.endpoint = endpoint;
        this.securityLevel = securityLevel;
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

    public boolean isActive() {
        return active;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }
}
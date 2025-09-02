package com.vaadin.starter.business.backend.dto.channelsandservices;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for Channel.
 */
public class ChannelDTO {

    private UUID id;
    private String name;
    private String description;
    private boolean active;
    private String integrationType;
    private LocalDateTime lastUpdated;
    private String endpoint;
    private String securityLevel;

    /**
     * Default constructor.
     */
    public ChannelDTO() {
    }

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
    public ChannelDTO(UUID id, String name, String description, boolean active, 
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

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getIntegrationType() {
        return integrationType;
    }

    public void setIntegrationType(String integrationType) {
        this.integrationType = integrationType;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }
}
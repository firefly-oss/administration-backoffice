package com.vaadin.starter.business.dummy;

/**
 * Domain object representing a flow configuration.
 */
public class Flow {

    public enum FlowType {
        DEFAULT,
        PROVIDER
    }

    private Long id;
    private String name;
    private String description;
    private FlowType type;
    private String xmlDefinition;
    private Long providerId;
    private boolean active;

    /**
     * Constructor with all fields.
     *
     * @param id            the flow ID
     * @param name          the flow name
     * @param description   the description
     * @param type          the flow type (DEFAULT or PROVIDER)
     * @param xmlDefinition the XML definition of the flow
     * @param providerId    the ID of the provider this flow belongs to
     * @param active        whether the flow is active
     */
    public Flow(Long id, String name, String description, FlowType type, 
                String xmlDefinition, Long providerId, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.xmlDefinition = xmlDefinition;
        this.providerId = providerId;
        this.active = active;
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

    public FlowType getType() {
        return type;
    }

    public String getXmlDefinition() {
        return xmlDefinition;
    }

    public Long getProviderId() {
        return providerId;
    }

    public boolean isActive() {
        return active;
    }
    
    public void setXmlDefinition(String xmlDefinition) {
        this.xmlDefinition = xmlDefinition;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
}
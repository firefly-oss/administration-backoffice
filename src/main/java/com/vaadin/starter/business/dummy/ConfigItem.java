package com.vaadin.starter.business.dummy;

import java.time.LocalDate;

/**
 * Entity class for Configuration Item.
 */
public class ConfigItem {
    private String id;
    private String name;
    private String description;
    private String status;
    private String category;
    private String value;
    private String scope;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public ConfigItem() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id           the configuration item ID
     * @param name         the configuration item name
     * @param description  the configuration item description
     * @param status       the configuration item status (Active, Pending, Deprecated)
     * @param category     the configuration item category
     * @param value        the configuration item value
     * @param scope        the configuration item scope
     * @param lastModified the date when the configuration item was last modified
     */
    public ConfigItem(String id, String name, String description, String status, String category, String value, String scope, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.value = value;
        this.scope = scope;
        this.lastModified = lastModified;
    }

    /**
     * Get the configuration item ID.
     *
     * @return the configuration item ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the configuration item name.
     *
     * @return the configuration item name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the configuration item description.
     *
     * @return the configuration item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the configuration item status.
     *
     * @return the configuration item status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the configuration item category.
     *
     * @return the configuration item category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the configuration item value.
     *
     * @return the configuration item value
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the configuration item scope.
     *
     * @return the configuration item scope
     */
    public String getScope() {
        return scope;
    }

    /**
     * Get the date when the configuration item was last modified.
     *
     * @return the last modified date
     */
    public LocalDate getLastModified() {
        return lastModified;
    }

    /**
     * Check if the configuration item is active.
     *
     * @return true if the status is "Active", false otherwise
     */
    public boolean isActive() {
        return "Active".equals(status);
    }
}
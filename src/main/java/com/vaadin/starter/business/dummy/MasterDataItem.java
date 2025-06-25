package com.vaadin.starter.business.dummy;

import java.time.LocalDate;

/**
 * Entity class for Master Data Item.
 */
public class MasterDataItem {
    private String id;
    private String name;
    private String description;
    private String status;
    private String category;
    private String dataType;
    private String format;
    private LocalDate lastModified;

    /**
     * Default constructor.
     */
    public MasterDataItem() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id           the master data item ID
     * @param name         the master data item name
     * @param description  the master data item description
     * @param status       the master data item status (Active, Pending, Deprecated)
     * @param category     the master data item category
     * @param dataType     the master data item data type
     * @param format       the master data item format
     * @param lastModified the date when the master data item was last modified
     */
    public MasterDataItem(String id, String name, String description, String status, String category, String dataType, String format, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.category = category;
        this.dataType = dataType;
        this.format = format;
        this.lastModified = lastModified;
    }

    /**
     * Get the master data item ID.
     *
     * @return the master data item ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the master data item name.
     *
     * @return the master data item name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the master data item description.
     *
     * @return the master data item description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the master data item status.
     *
     * @return the master data item status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the master data item category.
     *
     * @return the master data item category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Get the master data item data type.
     *
     * @return the master data item data type
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Get the master data item format.
     *
     * @return the master data item format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Get the date when the master data item was last modified.
     *
     * @return the last modified date
     */
    public LocalDate getLastModified() {
        return lastModified;
    }

    /**
     * Check if the master data item is active.
     *
     * @return true if the status is "Active", false otherwise
     */
    public boolean isActive() {
        return "Active".equals(status);
    }
}
package com.vaadin.starter.business.backend;

import java.time.LocalDate;

/**
 * Domain object representing a customer segment.
 */
public class Segment {

    private String id;
    private String name;
    private String description;
    private int customerCount;
    private double averageBalance;
    private String riskProfile;
    private boolean active;
    private LocalDate createdDate;

    /**
     * Constructor with all fields.
     *
     * @param id             the segment ID
     * @param name           the segment name
     * @param description    the description
     * @param customerCount  the number of customers in this segment
     * @param averageBalance the average balance of customers in this segment
     * @param riskProfile    the risk profile (Low, Medium, Medium-High, High)
     * @param active         whether the segment is active
     * @param createdDate    the date the segment was created
     */
    public Segment(String id, String name, String description, int customerCount,
                  double averageBalance, String riskProfile, boolean active,
                  LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.customerCount = customerCount;
        this.averageBalance = averageBalance;
        this.riskProfile = riskProfile;
        this.active = active;
        this.createdDate = createdDate;
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

    public int getCustomerCount() {
        return customerCount;
    }

    public double getAverageBalance() {
        return averageBalance;
    }

    public String getRiskProfile() {
        return riskProfile;
    }

    public boolean isActive() {
        return active;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
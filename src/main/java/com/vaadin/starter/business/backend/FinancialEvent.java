package com.vaadin.starter.business.backend;

import java.time.LocalDate;

/**
 * Domain object representing a financial event.
 */
public class FinancialEvent {

    private String id;
    private String title;
    private String description;
    private LocalDate date;
    private String category;
    private double amount;
    private String status;
    private String assignedTo;

    /**
     * Constructor with all fields.
     *
     * @param id          the event ID
     * @param title       the event title
     * @param description the description
     * @param date        the date of the event
     * @param category    the category of the event
     * @param amount      the amount associated with the event
     * @param status      the status of the event
     * @param assignedTo  the person assigned to the event
     */
    public FinancialEvent(String id, String title, String description, LocalDate date,
                        String category, double amount, String status, String assignedTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }
}
package com.vaadin.starter.business.dummy;

import java.time.LocalDate;

/**
 * Domain object representing a product.
 */
public class Product {

    private String id;
    private String name;
    private String category;
    private String description;
    private double price;
    private boolean isActive;
    private LocalDate createdDate;

    /**
     * Constructor with all fields.
     *
     * @param id          the product ID
     * @param name        the product name
     * @param category    the product category
     * @param description the product description
     * @param price       the product price
     * @param isActive    whether the product is active
     * @param createdDate the date the product was created
     */
    public Product(String id, String name, String category, String description, 
                  double price, boolean isActive, LocalDate createdDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.isActive = isActive;
        this.createdDate = createdDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }
}
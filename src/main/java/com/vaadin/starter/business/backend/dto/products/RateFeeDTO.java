package com.vaadin.starter.business.backend.dto.products;

import java.time.LocalDate;

/**
 * Data Transfer Object for RateFee.
 */
public class RateFeeDTO {

    private String id;
    private String name;
    private String productCategory;
    private String type;
    private double value;
    private String calculationMethod;
    private boolean isActive;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;

    /**
     * Default constructor.
     */
    public RateFeeDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id                the rate/fee ID
     * @param name              the rate/fee name
     * @param productCategory   the product category
     * @param type              the type (Interest Rate, Fee, etc.)
     * @param value             the value
     * @param calculationMethod the calculation method
     * @param isActive          whether the rate/fee is active
     * @param effectiveDate     the effective date
     * @param expirationDate    the expiration date
     */
    public RateFeeDTO(String id, String name, String productCategory, String type, 
                     double value, String calculationMethod, boolean isActive, 
                     LocalDate effectiveDate, LocalDate expirationDate) {
        this.id = id;
        this.name = name;
        this.productCategory = productCategory;
        this.type = type;
        this.value = value;
        this.calculationMethod = calculationMethod;
        this.isActive = isActive;
        this.effectiveDate = effectiveDate;
        this.expirationDate = expirationDate;
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

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public void setCalculationMethod(String calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
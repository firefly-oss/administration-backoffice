/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.vaadin.starter.business.backend;

import java.time.LocalDate;

/**
 * Domain object representing a rate or fee.
 */
public class RateFee {

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
    public RateFee(String id, String name, String productCategory, String type, 
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

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public String getCalculationMethod() {
        return calculationMethod;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
}
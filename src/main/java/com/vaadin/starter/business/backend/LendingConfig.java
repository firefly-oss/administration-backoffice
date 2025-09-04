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
 * Domain object representing a lending configuration.
 */
public class LendingConfig {

    private String id;
    private String name;
    private String loanType;
    private double minAmount;
    private double maxAmount;
    private double baseInterestRate;
    private int minTerm;
    private int maxTerm;
    private String riskCategory;
    private boolean isActive;
    private LocalDate lastUpdated;

    /**
     * Constructor with all fields.
     *
     * @param id               the configuration ID
     * @param name             the configuration name
     * @param loanType         the loan type
     * @param minAmount        the minimum amount
     * @param maxAmount        the maximum amount
     * @param baseInterestRate the base interest rate
     * @param minTerm          the minimum term in months
     * @param maxTerm          the maximum term in months
     * @param riskCategory     the risk category
     * @param isActive         whether the configuration is active
     * @param lastUpdated      the last updated date
     */
    public LendingConfig(String id, String name, String loanType, double minAmount, 
                        double maxAmount, double baseInterestRate, int minTerm, 
                        int maxTerm, String riskCategory, boolean isActive, 
                        LocalDate lastUpdated) {
        this.id = id;
        this.name = name;
        this.loanType = loanType;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.baseInterestRate = baseInterestRate;
        this.minTerm = minTerm;
        this.maxTerm = maxTerm;
        this.riskCategory = riskCategory;
        this.isActive = isActive;
        this.lastUpdated = lastUpdated;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLoanType() {
        return loanType;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public double getBaseInterestRate() {
        return baseInterestRate;
    }

    public int getMinTerm() {
        return minTerm;
    }

    public int getMaxTerm() {
        return maxTerm;
    }

    public String getRiskCategory() {
        return riskCategory;
    }

    public boolean isActive() {
        return isActive;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public String getAmountRange() {
        return String.format("$%.0f - $%.0f", minAmount, maxAmount);
    }

    public String getTermRange() {
        return String.format("%d - %d months", minTerm, maxTerm);
    }
}
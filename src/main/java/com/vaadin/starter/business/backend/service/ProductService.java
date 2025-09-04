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


package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.backend.Product;
import com.vaadin.starter.business.backend.RateFee;
import com.vaadin.starter.business.backend.Contract;
import com.vaadin.starter.business.backend.LendingConfig;

/**
 * Service interface for product-related functionality.
 */
public interface ProductService {

    /**
     * Get all products.
     *
     * @return a collection of all products
     */
    Collection<Product> getProducts();

    /**
     * Get a specific product by its ID.
     *
     * @param id the ID of the product to retrieve
     * @return the product with the specified ID, or null if not found
     */
    Product getProduct(String id);

    /**
     * Get all rates and fees.
     *
     * @return a collection of all rates and fees
     */
    Collection<RateFee> getRatesFees();

    /**
     * Get a specific rate or fee by its ID.
     *
     * @param id the ID of the rate or fee to retrieve
     * @return the rate or fee with the specified ID, or null if not found
     */
    RateFee getRateFee(String id);

    /**
     * Get all contracts.
     *
     * @return a collection of all contracts
     */
    Collection<Contract> getContracts();

    /**
     * Get a specific contract by its ID.
     *
     * @param id the ID of the contract to retrieve
     * @return the contract with the specified ID, or null if not found
     */
    Contract getContract(String id);

    /**
     * Get all lending configurations.
     *
     * @return a collection of all lending configurations
     */
    Collection<LendingConfig> getLendingConfigs();

    /**
     * Get a specific lending configuration by its ID.
     *
     * @param id the ID of the lending configuration to retrieve
     * @return the lending configuration with the specified ID, or null if not found
     */
    LendingConfig getLendingConfig(String id);
}
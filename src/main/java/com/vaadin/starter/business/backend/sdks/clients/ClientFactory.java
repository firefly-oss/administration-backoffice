package com.vaadin.starter.business.backend.sdks.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.starter.business.backend.sdks.properties.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Default implementation of the ClientFactory interface.
 * Creates client service instances using the appropriate API clients and dependencies.
 */
@Component
public class ClientFactory {

    private final CustomersProperties customersProperties;
    private final AccountsProperties accountsProperties;
    private final CardsProperties cardsProperties;
    private final ContractsProperties contractsProperties;
    private final ProductsProperties productsProperties;
    private final ObjectMapper objectMapper;

    @Autowired
    public ClientFactory(CustomersProperties customersProperties, AccountsProperties accountsProperties,
                         CardsProperties cardsProperties, ContractsProperties contractsProperties, ProductsProperties productsProperties,
                         ObjectMapper objectMapper) {
        this.customersProperties = customersProperties;
        this.accountsProperties = accountsProperties;
        this.cardsProperties = cardsProperties;
        this.contractsProperties = contractsProperties;
        this.productsProperties = productsProperties;
        this.objectMapper = objectMapper;
    }

    /**
     * Creates and returns a Customers service client.
     *
     * @return A configured Customers service client
     */
    @Bean
    public com.catalis.common.customer.sdk.invoker.ApiClient createCustomersClient() {
        com.catalis.common.customer.sdk.invoker.ApiClient apiClient = new com.catalis.common.customer.sdk.invoker.ApiClient();
        apiClient.setBasePath(customersProperties.getBasePath());
        return apiClient;
    }

    /**
     * Creates and returns an Accounts service client.
     *
     * @return A configured Accounts service client
     */
    @Bean
    public com.catalis.core.banking.accounts.sdk.invoker.ApiClient createAccountsClient() {
        com.catalis.core.banking.accounts.sdk.invoker.ApiClient apiClient = new com.catalis.core.banking.accounts.sdk.invoker.ApiClient();
        apiClient.setBasePath(accountsProperties.getBasePath());
        apiClient.addDefaultHeader("Content-Type", "application/json");
        apiClient.addDefaultHeader("Accept", "application/json");
        return apiClient;
    }

    /**
     * Creates and returns a Cards service client.
     *
     * @return A configured Cards service client
     */
    @Bean
    public com.catalis.core.banking.cards.sdk.invoker.ApiClient createCardsClient() {
        com.catalis.core.banking.cards.sdk.invoker.ApiClient apiClient = new com.catalis.core.banking.cards.sdk.invoker.ApiClient();
        apiClient.setBasePath(cardsProperties.getBasePath());
        return apiClient;
    }

    /**
     * Creates and returns a Contracts service client.
     *
     * @return A configured Contracts service client
     */
    @Bean
    public com.catalis.common.contract.sdk.invoker.ApiClient createContractsClient() {
        com.catalis.common.contract.sdk.invoker.ApiClient apiClient = new com.catalis.common.contract.sdk.invoker.ApiClient();
        apiClient.setBasePath(contractsProperties.getBasePath());
        return apiClient;
    }

    /**
     * Creates and returns a Products service client.
     *
     * @return A configured Products service client
     */
    @Bean
    public com.catalis.common.product.sdk.invoker.ApiClient createProductsClient() {
        com.catalis.common.product.sdk.invoker.ApiClient apiClient = new com.catalis.common.product.sdk.invoker.ApiClient();
        apiClient.setBasePath(productsProperties.getBasePath());
        return apiClient;
    }

}

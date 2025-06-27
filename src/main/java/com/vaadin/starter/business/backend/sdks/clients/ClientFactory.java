package com.vaadin.starter.business.backend.sdks.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.starter.business.backend.sdks.properties.CustomersProperties;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public ClientFactory(CustomersProperties customersProperties,
                         ObjectMapper objectMapper) {
        this.customersProperties = customersProperties;
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
        apiClient.setBasePath(customersProperties.getBasePath());
        return apiClient;
    }

}

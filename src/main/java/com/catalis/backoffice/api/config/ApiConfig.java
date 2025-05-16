package com.catalis.backoffice.api.config;

import com.catalis.backoffice.api.client.account.AccountClient;
import com.catalis.backoffice.api.client.customer.CustomerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

/**
 * Configuration class for API services.
 * This class provides the necessary beans for the service layer.
 */
@Configuration
public class ApiConfig {

    @Value("${api.base-url:http://localhost:8080}")
    private String apiBaseUrl;

    @Value("${api.timeout.connect:5000}")
    private int connectTimeout;

    @Value("${api.timeout.read:5000}")
    private int readTimeout;

    /**
     * Creates a RestTemplate bean with configured timeouts.
     *
     * @param builder the RestTemplateBuilder
     * @return the configured RestTemplate
     */
    @Bean
    public RestTemplate apiRestTemplate(RestTemplateBuilder builder) {
        return builder
                .connectTimeout(Duration.ofMillis(connectTimeout))
                .readTimeout(Duration.ofMillis(readTimeout))
                .build();
    }

    /**
     * Creates a CustomerClient bean.
     *
     * @param restTemplate the RestTemplate to use
     * @return the CustomerClient
     */
    @Bean
    public CustomerClient customerClient(RestTemplate restTemplate) {
        return new CustomerClient(restTemplate, apiBaseUrl);
    }

    /**
     * Creates an AccountClient bean.
     *
     * @param restTemplate the RestTemplate to use
     * @return the AccountClient
     */
    @Bean
    public AccountClient accountClient(RestTemplate restTemplate) {
        return new AccountClient(restTemplate, apiBaseUrl);
    }

    /**
     * Returns the base URL for the API.
     *
     * @return the base URL
     */
    public String getApiBaseUrl() {
        return apiBaseUrl;
    }
}

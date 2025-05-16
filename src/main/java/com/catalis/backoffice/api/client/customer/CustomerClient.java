package com.catalis.backoffice.api.client.customer;

import com.catalis.backoffice.api.dto.customer.CustomerDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Client for making HTTP requests to the Customer microservice.
 * This class encapsulates the details of HTTP communication with the Customer API.
 * It will be used by the real implementation of CustomerService when the microservice is available.
 */
public class CustomerClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    /**
     * Constructs a new CustomerClient with the specified RestTemplate and base URL.
     *
     * @param restTemplate the RestTemplate to use for HTTP requests
     * @param baseUrl the base URL of the Customer API
     */
    public CustomerClient(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id the customer ID
     * @return the response entity containing the customer
     */
    public ResponseEntity<CustomerDTO> getCustomerById(String id) {
        String url = baseUrl + "/api/customers/" + id;
        return restTemplate.getForEntity(url, CustomerDTO.class);
    }

    /**
     * Retrieves all customers.
     *
     * @return the response entity containing the list of customers
     */
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        String url = baseUrl + "/api/customers";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CustomerDTO>>() {}
        );
    }

    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     * @return the response entity containing the created customer
     */
    public ResponseEntity<CustomerDTO> createCustomer(CustomerDTO customer) {
        String url = baseUrl + "/api/customers";
        return restTemplate.postForEntity(url, customer, CustomerDTO.class);
    }

    /**
     * Updates an existing customer.
     *
     * @param id the ID of the customer to update
     * @param customer the updated customer data
     * @return the response entity containing the updated customer
     */
    public ResponseEntity<CustomerDTO> updateCustomer(String id, CustomerDTO customer) {
        String url = baseUrl + "/api/customers/" + id;
        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(customer),
                CustomerDTO.class
        );
    }

    /**
     * Deletes a customer by ID.
     *
     * @param id the ID of the customer to delete
     * @return the response entity
     */
    public ResponseEntity<Void> deleteCustomer(String id) {
        String url = baseUrl + "/api/customers/" + id;
        return restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

    /**
     * Searches for customers by name.
     *
     * @param name the name to search for
     * @return the response entity containing the list of matching customers
     */
    public ResponseEntity<List<CustomerDTO>> searchCustomersByName(String name) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/api/customers/search")
                .queryParam("name", name)
                .toUriString();
                
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CustomerDTO>>() {}
        );
    }

    /**
     * Searches for customers by email.
     *
     * @param email the email to search for
     * @return the response entity containing the list of matching customers
     */
    public ResponseEntity<List<CustomerDTO>> searchCustomersByEmail(String email) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl + "/api/customers/search")
                .queryParam("email", email)
                .toUriString();
                
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CustomerDTO>>() {}
        );
    }
}
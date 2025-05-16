package com.catalis.backoffice.api.service.customer;

import com.catalis.backoffice.api.dto.customer.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for customer operations.
 * This interface defines the operations that can be performed on customers through external microservices.
 */
public interface CustomerService {

    /**
     * Retrieves a customer by their ID.
     *
     * @param id the customer ID
     * @return an Optional containing the customer if found, or empty if not found
     */
    Optional<CustomerDTO> getCustomerById(String id);

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    List<CustomerDTO> getAllCustomers();

    /**
     * Retrieves a paginated list of customers.
     *
     * @param pageable pagination information
     * @return a page of customers
     */
    Page<CustomerDTO> getCustomers(Pageable pageable);

    /**
     * Creates a new customer.
     *
     * @param customer the customer to create
     * @return the created customer with assigned ID
     */
    CustomerDTO createCustomer(CustomerDTO customer);

    /**
     * Updates an existing customer.
     *
     * @param id the ID of the customer to update
     * @param customer the updated customer data
     * @return the updated customer
     * @throws IllegalArgumentException if the customer is not found
     */
    CustomerDTO updateCustomer(String id, CustomerDTO customer);

    /**
     * Deletes a customer by their ID.
     *
     * @param id the ID of the customer to delete
     * @return true if the customer was deleted, false if the customer was not found
     */
    boolean deleteCustomer(String id);

    /**
     * Searches for customers by name.
     *
     * @param name the name to search for (can be partial)
     * @return a list of customers matching the search criteria
     */
    List<CustomerDTO> searchCustomersByName(String name);

    /**
     * Searches for customers by email.
     *
     * @param email the email to search for
     * @return a list of customers matching the search criteria
     */
    List<CustomerDTO> searchCustomersByEmail(String email);
}
package com.catalis.backoffice.api.service.customer;

import com.catalis.backoffice.api.client.customer.CustomerClient;
import com.catalis.backoffice.api.dto.customer.CustomerDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Real implementation of the CustomerService interface.
 * This implementation uses the CustomerClient to make HTTP requests to the Customer microservice.
 * 
 * NOTE: This class is provided as a template for future implementation.
 * It is not currently active as the microservices are not yet available.
 * To activate this implementation, uncomment the @Service and @Primary annotations
 * and comment out the @Service annotation in MockCustomerServiceImpl.
 */
// @Service
// @Primary
public class CustomerServiceImpl implements CustomerService {

    private final CustomerClient customerClient;

    public CustomerServiceImpl(CustomerClient customerClient) {
        this.customerClient = customerClient;
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(String id) {
        try {
            ResponseEntity<CustomerDTO> response = customerClient.getCustomerById(id);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return Optional.of(response.getBody());
            }
            
            return Optional.empty();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error retrieving customer with ID: " + id, e);
            return Optional.empty();
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        try {
            ResponseEntity<List<CustomerDTO>> response = customerClient.getAllCustomers();
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            return new ArrayList<>();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error retrieving all customers", e);
            return new ArrayList<>();
        }
    }

    @Override
    public Page<CustomerDTO> getCustomers(Pageable pageable) {
        // In a real implementation, the API would support pagination directly
        // For now, we'll implement client-side pagination
        List<CustomerDTO> allCustomers = getAllCustomers();
        
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allCustomers.size());
        
        if (start > allCustomers.size()) {
            return new PageImpl<>(new ArrayList<>(), pageable, allCustomers.size());
        }
        
        List<CustomerDTO> pageContent = allCustomers.subList(start, end);
        return new PageImpl<>(pageContent, pageable, allCustomers.size());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        try {
            ResponseEntity<CustomerDTO> response = customerClient.createCustomer(customer);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new RuntimeException("Failed to create customer: " + response.getStatusCode());
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error creating customer", e);
            throw new RuntimeException("Error creating customer", e);
        }
    }

    @Override
    public CustomerDTO updateCustomer(String id, CustomerDTO customer) {
        try {
            ResponseEntity<CustomerDTO> response = customerClient.updateCustomer(id, customer);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            throw new IllegalArgumentException("Customer not found with ID: " + id);
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error updating customer with ID: " + id, e);
            throw new RuntimeException("Error updating customer with ID: " + id, e);
        }
    }

    @Override
    public boolean deleteCustomer(String id) {
        try {
            ResponseEntity<Void> response = customerClient.deleteCustomer(id);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error deleting customer with ID: " + id, e);
            return false;
        }
    }

    @Override
    public List<CustomerDTO> searchCustomersByName(String name) {
        try {
            ResponseEntity<List<CustomerDTO>> response = customerClient.searchCustomersByName(name);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            return new ArrayList<>();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error searching customers by name: " + name, e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<CustomerDTO> searchCustomersByEmail(String email) {
        try {
            ResponseEntity<List<CustomerDTO>> response = customerClient.searchCustomersByEmail(email);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                return response.getBody();
            }
            
            return new ArrayList<>();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error searching customers by email: " + email, e);
            return new ArrayList<>();
        }
    }
}
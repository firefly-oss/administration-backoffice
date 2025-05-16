package com.catalis.backoffice.api.service.customer;

import com.catalis.backoffice.api.dto.customer.CustomerDTO;
import com.catalis.backoffice.common.service.MockRestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Mock implementation of the CustomerService interface.
 * This implementation provides simulated responses for customer operations.
 * It will be replaced with a real implementation when the actual microservices are available.
 */
@Service
public class MockCustomerServiceImpl implements CustomerService {

    private static final String CUSTOMER_API_BASE_URL = "/api/customers";
    private final MockRestService mockRestService;
    private final List<CustomerDTO> mockCustomers;

    public MockCustomerServiceImpl(MockRestService mockRestService) {
        this.mockRestService = mockRestService;
        this.mockCustomers = createMockCustomers();
        registerMockResponses();
    }

    @Override
    public Optional<CustomerDTO> getCustomerById(String id) {
        ResponseEntity<CustomerDTO> response = mockRestService.get(
                CUSTOMER_API_BASE_URL + "/" + id,
                CustomerDTO.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return Optional.of(response.getBody());
        }
        
        return Optional.empty();
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        ResponseEntity<List<CustomerDTO>> response = mockRestService.get(
                CUSTOMER_API_BASE_URL,
                (Class<List<CustomerDTO>>) (Class<?>) List.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        return new ArrayList<>();
    }

    @Override
    public Page<CustomerDTO> getCustomers(Pageable pageable) {
        List<CustomerDTO> allCustomers = getAllCustomers();
        
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allCustomers.size());
        
        List<CustomerDTO> pageContent = allCustomers.subList(start, end);
        return new PageImpl<>(pageContent, pageable, allCustomers.size());
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customer) {
        // Assign a new ID if not provided
        if (customer.getId() == null || customer.getId().isEmpty()) {
            customer.setId(UUID.randomUUID().toString());
        }
        
        ResponseEntity<CustomerDTO> response = mockRestService.post(
                CUSTOMER_API_BASE_URL,
                customer,
                CustomerDTO.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        return customer; // Return the input customer as fallback
    }

    @Override
    public CustomerDTO updateCustomer(String id, CustomerDTO customer) {
        // Ensure the ID in the path matches the ID in the customer object
        customer.setId(id);
        
        ResponseEntity<CustomerDTO> response = mockRestService.put(
                CUSTOMER_API_BASE_URL + "/" + id,
                customer,
                CustomerDTO.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        throw new IllegalArgumentException("Customer not found with ID: " + id);
    }

    @Override
    public boolean deleteCustomer(String id) {
        ResponseEntity<Void> response = mockRestService.delete(
                CUSTOMER_API_BASE_URL + "/" + id
        );
        
        return response.getStatusCode().is2xxSuccessful();
    }

    @Override
    public List<CustomerDTO> searchCustomersByName(String name) {
        ResponseEntity<List<CustomerDTO>> response = mockRestService.get(
                CUSTOMER_API_BASE_URL + "/search?name=" + name,
                (Class<List<CustomerDTO>>) (Class<?>) List.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        return new ArrayList<>();
    }

    @Override
    public List<CustomerDTO> searchCustomersByEmail(String email) {
        ResponseEntity<List<CustomerDTO>> response = mockRestService.get(
                CUSTOMER_API_BASE_URL + "/search?email=" + email,
                (Class<List<CustomerDTO>>) (Class<?>) List.class
        );
        
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            return response.getBody();
        }
        
        return new ArrayList<>();
    }

    /**
     * Creates a list of mock customers for testing.
     * 
     * @return a list of mock customers
     */
    private List<CustomerDTO> createMockCustomers() {
        List<CustomerDTO> customers = new ArrayList<>();
        
        customers.add(new CustomerDTO(
                "1",
                "John",
                "Doe",
                "john.doe@example.com",
                "555-123-4567",
                LocalDate.of(1980, 1, 15),
                "123 Main St, Anytown, USA"
        ));
        
        customers.add(new CustomerDTO(
                "2",
                "Jane",
                "Smith",
                "jane.smith@example.com",
                "555-987-6543",
                LocalDate.of(1985, 5, 20),
                "456 Oak Ave, Somewhere, USA"
        ));
        
        customers.add(new CustomerDTO(
                "3",
                "Michael",
                "Johnson",
                "michael.johnson@example.com",
                "555-456-7890",
                LocalDate.of(1975, 8, 10),
                "789 Pine Rd, Nowhere, USA"
        ));
        
        customers.add(new CustomerDTO(
                "4",
                "Emily",
                "Williams",
                "emily.williams@example.com",
                "555-789-0123",
                LocalDate.of(1990, 3, 25),
                "321 Elm St, Everywhere, USA"
        ));
        
        customers.add(new CustomerDTO(
                "5",
                "David",
                "Brown",
                "david.brown@example.com",
                "555-321-6547",
                LocalDate.of(1982, 11, 5),
                "654 Maple Dr, Anywhere, USA"
        ));
        
        return customers;
    }

    /**
     * Registers mock responses with the MockRestService.
     */
    private void registerMockResponses() {
        // Register mock response for getting all customers
        mockRestService.registerMockResponse(
                CUSTOMER_API_BASE_URL,
                () -> mockCustomers
        );
        
        // Register mock responses for getting individual customers by ID
        for (CustomerDTO customer : mockCustomers) {
            mockRestService.registerMockResponse(
                    CUSTOMER_API_BASE_URL + "/" + customer.getId(),
                    () -> customer
            );
        }
        
        // Register mock response for creating a customer
        mockRestService.registerMockResponse(
                CUSTOMER_API_BASE_URL,
                () -> {
                    // This would normally create a new customer in the database
                    // For mock purposes, we'll just return the last customer in the list
                    return mockCustomers.get(mockCustomers.size() - 1);
                }
        );
        
        // Register mock responses for searching customers by name
        mockRestService.registerMockResponse(
                CUSTOMER_API_BASE_URL + "/search?name=John",
                () -> mockCustomers.stream()
                        .filter(c -> c.getFirstName().contains("John") || c.getLastName().contains("John"))
                        .collect(Collectors.toList())
        );
        
        // Register mock responses for searching customers by email
        mockRestService.registerMockResponse(
                CUSTOMER_API_BASE_URL + "/search?email=john.doe@example.com",
                () -> mockCustomers.stream()
                        .filter(c -> c.getEmail().equals("john.doe@example.com"))
                        .collect(Collectors.toList())
        );
    }
}
package com.catalis.backoffice.common.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * A service that mocks REST API calls.
 * This service is used to simulate API calls during development until the actual backend is implemented.
 */
@Service
public class MockRestService {

    private final RestTemplate restTemplate;
    private final Map<String, Supplier<?>> mockResponses = new HashMap<>();

    public MockRestService() {
        this.restTemplate = new RestTemplate();
        initializeMockResponses();
    }

    /**
     * Performs a GET request to the specified URL and returns the response.
     * If the URL is registered for mocking, returns the mocked response instead.
     *
     * @param url          the URL to send the request to
     * @param responseType the type of the response
     * @param <T>          the type of the response
     * @return the response entity
     */
    public <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        if (mockResponses.containsKey(url)) {
            @SuppressWarnings("unchecked")
            T mockResponse = (T) mockResponses.get(url).get();
            return new ResponseEntity<>(mockResponse, HttpStatus.OK);
        }
        
        // If we get here, we're not mocking this URL, so we would make a real API call
        // For now, we'll just return a 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Performs a POST request to the specified URL with the given request body and returns the response.
     * If the URL is registered for mocking, returns the mocked response instead.
     *
     * @param url          the URL to send the request to
     * @param request      the request body
     * @param responseType the type of the response
     * @param <T>          the type of the response
     * @param <R>          the type of the request
     * @return the response entity
     */
    public <T, R> ResponseEntity<T> post(String url, R request, Class<T> responseType) {
        if (mockResponses.containsKey(url)) {
            @SuppressWarnings("unchecked")
            T mockResponse = (T) mockResponses.get(url).get();
            return new ResponseEntity<>(mockResponse, HttpStatus.OK);
        }
        
        // If we get here, we're not mocking this URL, so we would make a real API call
        // For now, we'll just return a 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Performs a PUT request to the specified URL with the given request body and returns the response.
     * If the URL is registered for mocking, returns the mocked response instead.
     *
     * @param url          the URL to send the request to
     * @param request      the request body
     * @param responseType the type of the response
     * @param <T>          the type of the response
     * @param <R>          the type of the request
     * @return the response entity
     */
    public <T, R> ResponseEntity<T> put(String url, R request, Class<T> responseType) {
        if (mockResponses.containsKey(url)) {
            @SuppressWarnings("unchecked")
            T mockResponse = (T) mockResponses.get(url).get();
            return new ResponseEntity<>(mockResponse, HttpStatus.OK);
        }
        
        // If we get here, we're not mocking this URL, so we would make a real API call
        // For now, we'll just return a 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Performs a DELETE request to the specified URL and returns the response.
     * If the URL is registered for mocking, returns the mocked response instead.
     *
     * @param url the URL to send the request to
     * @return the response entity
     */
    public ResponseEntity<Void> delete(String url) {
        if (mockResponses.containsKey(url)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        
        // If we get here, we're not mocking this URL, so we would make a real API call
        // For now, we'll just return a 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Registers a mock response for the specified URL.
     *
     * @param url      the URL to mock
     * @param response the response supplier
     */
    public void registerMockResponse(String url, Supplier<?> response) {
        mockResponses.put(url, response);
    }

    /**
     * Initializes the mock responses for common API endpoints.
     * This method can be extended to add more mock responses as needed.
     */
    private void initializeMockResponses() {
        // Example of registering a mock response
        // mockResponses.put("/api/clients", () -> {
        //     List<Client> clients = new ArrayList<>();
        //     clients.add(new Client("1", "John Doe", "john.doe@example.com"));
        //     clients.add(new Client("2", "Jane Smith", "jane.smith@example.com"));
        //     return clients;
        // });
    }
}
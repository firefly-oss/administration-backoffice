# API Service Layer

This package contains classes for integrating with external microservices. It provides a clean separation between the application and the external services, allowing for easy mocking during development and smooth transition to real implementations when the microservices become available.

## Package Structure

The API service layer is organized into the following packages:

- `api/client`: Contains HTTP client implementations for external services
- `api/dto`: Contains Data Transfer Objects for API requests and responses
- `api/service`: Contains service interfaces and implementations
- `api/config`: Contains configuration classes for the API service layer

## Service Interfaces and Implementations

Each domain has its own service interface that defines the operations that can be performed. For example:

- `CustomerService`: Defines operations for customer management
- `AccountService`: Defines operations for account management

Each service interface has two implementations:

1. **Mock Implementation**: Used during development when the actual microservices are not available. These implementations provide simulated responses.
   - `MockCustomerServiceImpl`
   - `MockAccountServiceImpl`

2. **Real Implementation**: Used when the actual microservices are available. These implementations use the HTTP clients to make requests to the microservices.
   - `CustomerServiceImpl`
   - `AccountServiceImpl`

## HTTP Clients

Each domain has its own HTTP client that encapsulates the details of communication with the external microservice:

- `CustomerClient`: Makes HTTP requests to the Customer microservice
- `AccountClient`: Makes HTTP requests to the Account microservice

## Data Transfer Objects (DTOs)

DTOs are used to transfer data between the application and the external microservices. They are designed to be serialized/deserialized to/from JSON or other formats.

- `CustomerDTO`: Represents customer data
- `AccountDTO`: Represents account data

## Configuration

The `ApiConfig` class provides the necessary beans for the API service layer, including:

- `RestTemplate`: Used for making HTTP requests
- HTTP clients for each domain

## How to Use

To use a service in your code, simply inject the service interface:

```java
@Autowired
private CustomerService customerService;

// Use the service
Optional<CustomerDTO> customer = customerService.getCustomerById("123");
```

The Spring container will automatically inject the appropriate implementation based on the active profile and configuration.

## Transitioning from Mock to Real Implementation

When the actual microservices become available, follow these steps to transition from mock to real implementation:

1. Update the `application.properties` file with the base URL of the microservices:

```properties
api.base-url=http://microservices.example.com
```

2. In each service implementation class (e.g., `CustomerServiceImpl`, `AccountServiceImpl`):
   - Uncomment the `@Service` and `@Primary` annotations
   - Comment out the `@Service` annotation in the corresponding mock implementation

For example:

```java
// In CustomerServiceImpl.java
@Service  // Uncomment this
@Primary  // Uncomment this
public class CustomerServiceImpl implements CustomerService {
    // ...
}

// In MockCustomerServiceImpl.java
// @Service  // Comment this out
public class MockCustomerServiceImpl implements CustomerService {
    // ...
}
```

## Adding New Services

To add a new service for a different domain:

1. Create a DTO class in the `api/dto/<domain>` package
2. Create a service interface in the `api/service/<domain>` package
3. Create a mock implementation in the `api/service/<domain>` package
4. Create an HTTP client in the `api/client/<domain>` package
5. Create a real implementation in the `api/service/<domain>` package
6. Add a bean for the HTTP client in the `ApiConfig` class
# Backoffice Administration

A comprehensive administration backoffice application built with Spring Boot and Vaadin.

## Table of Contents
- [Overview](#overview)
- [Technology Stack](#technology-stack)
- [Project Components](#project-components)
  - [Backend Components](#backend-components)
  - [Frontend Components](#frontend-components)
- [Configuration](#configuration)
- [Development](#development)
- [Building for Production](#building-for-production)
- [Testing](#testing)

## Overview

This application provides a backoffice administration interface for managing various aspects of the business including accounts, clients, contracts, lending, and more. It's built using Spring Boot for the backend and Vaadin for the frontend UI.

## Technology Stack

- **Java**: Version 21
- **Spring Boot**: Version 3.4.3
  - Spring Data JPA
  - Spring Boot Actuator
  - Spring Boot Validation
- **Vaadin**: Version 24.7.3
- **Database**: H2 (for development)
- **Build Tool**: Maven

## Project Components

### Backend Components

The application is organized into the following main components:

1. **Accounts Module** (`com.catalis.backoffice.accounts`)
   - Manages user accounts and related functionality

2. **Base Module** (`com.catalis.backoffice.base`)
   - Contains base classes and utilities used across the application

3. **Clients Module** (`com.catalis.backoffice.clients`)
   - Handles client management and related operations

4. **Common Module** (`com.catalis.backoffice.common`)
   - Provides common utilities and shared functionality

5. **Configurations Module** (`com.catalis.backoffice.configurations`)
   - Manages application configurations and settings

6. **Contracts Module** (`com.catalis.backoffice.contracts`)
   - Handles contract management and related operations

7. **Dashboard Module** (`com.catalis.backoffice.dashboard`)
   - Provides dashboard views and analytics

8. **ERP Module** (`com.catalis.backoffice.erp`)
   - Integrates with Enterprise Resource Planning functionality

9. **Lending Module** (`com.catalis.backoffice.lending`)
   - Manages lending operations and related functionality

10. **Security Module** (`com.catalis.backoffice.security`)
    - Handles authentication, authorization, and security concerns

11. **Task Management Module** (`com.catalis.backoffice.taskmanagement`)
    - Provides task management functionality

12. **UI Module** (`com.catalis.backoffice.ui`)
    - Contains UI components and views

### Frontend Components

The frontend is built using Vaadin and includes:

1. **Bank Theme** (`src/main/frontend/themes/bank-theme`)
   - Custom theme for the application

2. **Default Theme** (`src/main/frontend/themes/default`)
   - Default Vaadin theme

## Configuration

The application is configured using Spring Boot's standard configuration mechanisms. Key configurations include:

- Server port: 8083 (configurable via PORT environment variable)
- Vaadin configuration for development mode
- JPA configuration for database access

## Development

To start the application in development mode:

1. Import the project into your IDE and run the `Application` class
2. Alternatively, run from the command line:

```bash
./mvnw
```

The application will be available at http://localhost:8083

## Building for Production

To build the application for production:

```bash
./mvnw -Pproduction package
```

This will create an optimized build with frontend resources processed for production use.

## Testing

The project uses:
- Spring Boot Test for backend testing
- ArchUnit for architecture testing

Run tests using:

```bash
./mvnw test
```

For integration tests:

```bash
./mvnw -Pintegration-test verify
```

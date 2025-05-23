package com.vaadin.starter.business.backend.service;

import java.util.Collection;

import com.vaadin.starter.business.backend.Person;
import com.vaadin.starter.business.backend.dto.security.RoleDTO;
import com.vaadin.starter.business.backend.dto.security.PolicyDTO;

/**
 * Service interface for managing security-related entities.
 */
public interface SecurityService {
    
    /**
     * Get all internal users.
     *
     * @return a collection of all internal users
     */
    Collection<Person> getInternalUsers();
    
    /**
     * Get an internal user by ID.
     *
     * @param id the ID of the user to retrieve
     * @return the user with the specified ID, or null if not found
     */
    Person getInternalUser(Long id);
    
    /**
     * Get all roles and permissions.
     *
     * @return a collection of all roles
     */
    Collection<RoleDTO> getRoles();
    
    /**
     * Get a role by name.
     *
     * @param name the name of the role to retrieve
     * @return the role with the specified name, or null if not found
     */
    RoleDTO getRole(String name);
    
    /**
     * Get all security policies.
     *
     * @return a collection of all security policies
     */
    Collection<PolicyDTO> getPolicies();
    
    /**
     * Get a security policy by name.
     *
     * @param name the name of the policy to retrieve
     * @return the policy with the specified name, or null if not found
     */
    PolicyDTO getPolicy(String name);
}
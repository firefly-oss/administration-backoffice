package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.backend.DummyData;
import com.vaadin.starter.business.backend.Person;
import com.vaadin.starter.business.backend.Policy;
import com.vaadin.starter.business.backend.Role;
import com.vaadin.starter.business.backend.dto.security.PolicyDTO;
import com.vaadin.starter.business.backend.dto.security.RoleDTO;
import com.vaadin.starter.business.backend.mapper.security.PolicyMapper;
import com.vaadin.starter.business.backend.mapper.security.RoleMapper;
import com.vaadin.starter.business.backend.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.UUID;

/**
 * Implementation of the SecurityService interface.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    private final Map<UUID, Person> internalUsers = new HashMap<>();
    private final Map<String, Role> roles = new HashMap<>();
    private final Map<String, Policy> policies = new HashMap<>();
    
    private final RoleMapper roleMapper;
    private final PolicyMapper policyMapper;

    /**
     * Constructor that initializes the security data.
     */
    @Autowired
    public SecurityServiceImpl(RoleMapper roleMapper, PolicyMapper policyMapper) {
        this.roleMapper = roleMapper;
        this.policyMapper = policyMapper;
        initInternalUsers();
        initRoles();
        initPolicies();
    }

    /**
     * Initialize the internal users data.
     */
    private void initInternalUsers() {
        // Use DummyData to get persons
        Collection<Person> persons = DummyData.getPersons();
        for (Person person : persons) {
            internalUsers.put(person.getId(), person);
        }
    }

    /**
     * Initialize the roles data.
     */
    private void initRoles() {
        // Create sample roles
        roles.put("Admin", new Role("Admin", "Full system access", true, 
            Arrays.asList("Create Users", "Edit Users", "Delete Users", "View Reports", "Manage System")));
        roles.put("Manager", new Role("Manager", "Department management", true, 
            Arrays.asList("View Users", "Edit Users", "View Reports")));
        roles.put("User", new Role("User", "Regular user access", true, 
            Arrays.asList("View Profile", "Edit Profile", "View Reports")));
        roles.put("Guest", new Role("Guest", "Limited access", false, 
            Arrays.asList("View Public Content")));
    }

    /**
     * Initialize the policies data.
     */
    private void initPolicies() {
        // Create sample policies
        policies.put("Password Policy", new Policy("Password Policy", "Authentication", 
            "Passwords must be at least 8 characters long and include uppercase, lowercase, numbers, and special characters.", 
            true, LocalDate.now().minusDays(15)));
        policies.put("Account Lockout Policy", new Policy("Account Lockout Policy", "Authentication", 
            "Accounts will be locked after 5 failed login attempts.", 
            true, LocalDate.now().minusDays(30)));
        policies.put("Session Timeout Policy", new Policy("Session Timeout Policy", "Session Management", 
            "User sessions will timeout after 30 minutes of inactivity.", 
            true, LocalDate.now().minusDays(45)));
        policies.put("Data Retention Policy", new Policy("Data Retention Policy", "Data Management", 
            "User data will be retained for a maximum of 2 years after account closure.", 
            true, LocalDate.now().minusDays(60)));
        policies.put("Two-Factor Authentication", new Policy("Two-Factor Authentication", "Authentication", 
            "Two-factor authentication is required for all administrative accounts.", 
            false, LocalDate.now().minusDays(90)));
    }

    @Override
    public Collection<Person> getInternalUsers() {
        return internalUsers.values();
    }

    @Override
    public Person getInternalUser(UUID id) {
        return internalUsers.get(id);
    }

    @Override
    public Collection<RoleDTO> getRoles() {
        return roleMapper.toDtoList(roles.values());
    }

    @Override
    public RoleDTO getRole(String name) {
        return roleMapper.toDto(roles.get(name));
    }

    @Override
    public Collection<PolicyDTO> getPolicies() {
        return policyMapper.toDtoList(policies.values());
    }

    @Override
    public PolicyDTO getPolicy(String name) {
        return policyMapper.toDto(policies.get(name));
    }
}
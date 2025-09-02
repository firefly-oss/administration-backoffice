package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.backend.DummyData;
import com.vaadin.starter.business.backend.Person;
import com.vaadin.starter.business.backend.dto.riskandcompliance.PersonDTO;
import com.vaadin.starter.business.backend.mapper.riskandcompliance.PersonMapper;
import com.vaadin.starter.business.backend.service.RiskAndComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Implementation of the RiskAndComplianceService interface.
 */
@Service
public class RiskAndComplianceServiceImpl implements RiskAndComplianceService {

    private final Map<UUID, Person> fraudAlerts = new HashMap<>();
    private final Map<UUID, Person> complianceRecords = new HashMap<>();
    private final Map<UUID, Person> riskModels = new HashMap<>();

    private final PersonMapper personMapper;

    /**
     * Constructor that initializes the mock data and injects the mapper.
     *
     * @param personMapper the mapper for Person objects
     */
    @Autowired
    public RiskAndComplianceServiceImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
        initFraudAlerts();
        initComplianceRecords();
        initRiskModels();
    }

    @Override
    public Collection<Person> getFraudAlerts() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<PersonDTO> dtos = personMapper.toDtoList(fraudAlerts.values());
        return personMapper.toEntityList(dtos);
    }

    @Override
    public Person getFraudAlert(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        PersonDTO dto = personMapper.toDto(fraudAlerts.get(id));
        return personMapper.toEntity(dto);
    }

    @Override
    public Collection<Person> getComplianceRecords() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<PersonDTO> dtos = personMapper.toDtoList(complianceRecords.values());
        return personMapper.toEntityList(dtos);
    }

    @Override
    public Person getComplianceRecord(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        PersonDTO dto = personMapper.toDto(complianceRecords.get(id));
        return personMapper.toEntity(dto);
    }

    @Override
    public Collection<Person> getRiskModels() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<PersonDTO> dtos = personMapper.toDtoList(riskModels.values());
        return personMapper.toEntityList(dtos);
    }

    @Override
    public Person getRiskModel(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        PersonDTO dto = personMapper.toDto(riskModels.get(id));
        return personMapper.toEntity(dto);
    }

    /**
     * Initialize fraud alert data.
     */
    private void initFraudAlerts() {
        // Filter persons with MANAGER role for fraud alerts
        DummyData.getPersons().stream()
                .filter(person -> person.getRole() == Person.Role.MANAGER)
                .forEach(person -> fraudAlerts.put(person.getId(), person));
    }

    /**
     * Initialize compliance record data.
     */
    private void initComplianceRecords() {
        // Filter persons with MANAGER role for compliance records
        DummyData.getPersons().stream()
                .filter(person -> person.getRole() == Person.Role.MANAGER)
                .forEach(person -> complianceRecords.put(person.getId(), person));
    }

    /**
     * Initialize risk model data.
     */
    private void initRiskModels() {
        // Filter persons with MANAGER role for risk models
        DummyData.getPersons().stream()
                .filter(person -> person.getRole() == Person.Role.MANAGER)
                .forEach(person -> riskModels.put(person.getId(), person));
    }
}

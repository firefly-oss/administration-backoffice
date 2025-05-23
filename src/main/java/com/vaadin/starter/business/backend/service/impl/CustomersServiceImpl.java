package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.backend.OnboardingProcess;
import com.vaadin.starter.business.backend.Segment;
import com.vaadin.starter.business.backend.dto.customers.OnboardingProcessDTO;
import com.vaadin.starter.business.backend.dto.customers.SegmentDTO;
import com.vaadin.starter.business.backend.mapper.customers.OnboardingProcessMapper;
import com.vaadin.starter.business.backend.mapper.customers.SegmentMapper;
import com.vaadin.starter.business.backend.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the CustomersService interface.
 */
@Service
public class CustomersServiceImpl implements CustomersService {

    private final SegmentMapper segmentMapper;
    private final OnboardingProcessMapper onboardingProcessMapper;
    
    private final Map<String, Segment> segments = new HashMap<>();
    private final Map<String, OnboardingProcess> onboardingProcesses = new HashMap<>();

    /**
     * Constructor with required dependencies.
     *
     * @param segmentMapper the segment mapper
     * @param onboardingProcessMapper the onboarding process mapper
     */
    @Autowired
    public CustomersServiceImpl(SegmentMapper segmentMapper, OnboardingProcessMapper onboardingProcessMapper) {
        this.segmentMapper = segmentMapper;
        this.onboardingProcessMapper = onboardingProcessMapper;
        
        // Initialize mock data
        initSegments();
        initOnboardingProcesses();
    }

    @Override
    public Collection<Segment> getSegments() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<SegmentDTO> dtos = segmentMapper.toDtoList(segments.values());
        return segmentMapper.toEntityList(dtos);
    }

    @Override
    public Segment getSegment(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        SegmentDTO dto = segmentMapper.toDto(segments.get(id));
        return segmentMapper.toEntity(dto);
    }

    @Override
    public Collection<OnboardingProcess> getOnboardingProcesses() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<OnboardingProcessDTO> dtos = onboardingProcessMapper.toDtoList(onboardingProcesses.values());
        return onboardingProcessMapper.toEntityList(dtos);
    }

    @Override
    public OnboardingProcess getOnboardingProcess(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        OnboardingProcessDTO dto = onboardingProcessMapper.toDto(onboardingProcesses.get(id));
        return onboardingProcessMapper.toEntity(dto);
    }

    /**
     * Initialize segment data.
     */
    private void initSegments() {
        LocalDate now = LocalDate.now();
        
        Arrays.asList(
            new Segment("SEG001", "High Net Worth", 
                "Customers with assets over $1M", 
                120, 1450000.00, "Low", true, now.minusDays(180)),
            new Segment("SEG002", "Mass Affluent", 
                "Customers with assets between $100K and $1M", 
                850, 350000.00, "Medium", true, now.minusDays(180)),
            new Segment("SEG003", "Retail Banking", 
                "Regular retail banking customers", 
                5600, 15000.00, "Medium", true, now.minusDays(180)),
            new Segment("SEG004", "Small Business", 
                "Small business owners and entrepreneurs", 
                340, 75000.00, "Medium-High", true, now.minusDays(90)),
            new Segment("SEG005", "Students", 
                "College and university students", 
                1200, 2500.00, "Low", true, now.minusDays(60)),
            new Segment("SEG006", "Seniors", 
                "Retired customers over 65", 
                780, 120000.00, "Low", true, now.minusDays(120)),
            new Segment("SEG007", "New Customers", 
                "Customers who joined in the last 3 months", 
                450, 8500.00, "Medium", true, now.minusDays(30))
        ).forEach(segment -> segments.put(segment.getId(), segment));
    }

    /**
     * Initialize onboarding process data.
     */
    private void initOnboardingProcesses() {
        LocalDate now = LocalDate.now();
        
        Arrays.asList(
            new OnboardingProcess("OBP001", "Personal Account Onboarding", 
                "Standard process for new personal account customers", 
                "Personal", 5, 5, "Completed", "Sarah Johnson", 
                now.minusDays(30), now.minusDays(15)),
            new OnboardingProcess("OBP002", "Business Account Onboarding", 
                "Process for new business account customers", 
                "Business", 7, 3, "In Progress", "Michael Brown", 
                now.minusDays(20), now.minusDays(2)),
            new OnboardingProcess("OBP003", "Premium Account Onboarding", 
                "Enhanced process for high-value customers", 
                "Premium", 6, 2, "In Progress", "Jennifer Lee", 
                now.minusDays(15), now.minusDays(1)),
            new OnboardingProcess("OBP004", "Student Account Onboarding", 
                "Simplified process for student accounts", 
                "Student", 4, 4, "Completed", "David Miller", 
                now.minusDays(45), now.minusDays(30)),
            new OnboardingProcess("OBP005", "Joint Account Onboarding", 
                "Process for joint account holders", 
                "Joint", 8, 5, "In Progress", "Patricia White", 
                now.minusDays(10), now.minusDays(1)),
            new OnboardingProcess("OBP006", "Senior Account Onboarding", 
                "Specialized process for senior citizens", 
                "Senior", 5, 0, "Not Started", "Robert Johnson", 
                now.minusDays(5), now.minusDays(5)),
            new OnboardingProcess("OBP007", "Digital-Only Onboarding", 
                "Fully digital onboarding process with no branch visit", 
                "Digital", 3, 1, "In Progress", "Lisa Taylor", 
                now.minusDays(3), now.minusDays(1))
        ).forEach(process -> onboardingProcesses.put(process.getId(), process));
    }
}
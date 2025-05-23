package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.backend.FinancialEvent;
import com.vaadin.starter.business.backend.dto.accounting.FinancialEventDTO;
import com.vaadin.starter.business.backend.mapper.accounting.FinancialEventMapper;
import com.vaadin.starter.business.backend.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the FinanceService interface.
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    private final FinancialEventMapper financialEventMapper;
    
    private final Map<String, FinancialEvent> financialEvents = new HashMap<>();

    /**
     * Constructor with required dependencies.
     *
     * @param financialEventMapper the financial event mapper
     */
    @Autowired
    public FinanceServiceImpl(FinancialEventMapper financialEventMapper) {
        this.financialEventMapper = financialEventMapper;
        
        // Initialize mock data
        initFinancialEvents();
    }

    @Override
    public Collection<FinancialEvent> getFinancialEvents() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<FinancialEventDTO> dtos = financialEventMapper.toDtoList(financialEvents.values());
        return financialEventMapper.toEntityList(dtos);
    }

    @Override
    public FinancialEvent getFinancialEvent(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        FinancialEventDTO dto = financialEventMapper.toDto(financialEvents.get(id));
        return financialEventMapper.toEntity(dto);
    }

    /**
     * Initialize financial event data.
     */
    private void initFinancialEvents() {
        LocalDate now = LocalDate.now();
        
        Arrays.asList(
            new FinancialEvent("FE001", "Quarterly Tax Payment", 
                "Q1 corporate tax payment", 
                now.plusDays(15), "Tax", 12500.00, "Pending", "Sarah Johnson"),
            new FinancialEvent("FE002", "Annual Audit", 
                "Annual financial audit with external auditors", 
                now.plusDays(30), "Audit", 0.00, "Scheduled", "Michael Brown"),
            new FinancialEvent("FE003", "Dividend Payment", 
                "Q2 dividend payment to shareholders", 
                now.plusDays(45), "Dividend", 50000.00, "Pending", "Jennifer Lee"),
            new FinancialEvent("FE004", "Budget Planning", 
                "Annual budget planning meeting", 
                now.plusDays(7), "Planning", 0.00, "Scheduled", "David Miller"),
            new FinancialEvent("FE005", "Loan Payment", 
                "Monthly loan payment", 
                now.plusDays(3), "Loan", 8750.00, "Pending", "Patricia White"),
            new FinancialEvent("FE006", "Financial Report", 
                "Monthly financial report preparation", 
                now.plusDays(10), "Reporting", 0.00, "In Progress", "Robert Johnson"),
            new FinancialEvent("FE007", "Payroll Processing", 
                "Bi-weekly payroll processing", 
                now.plusDays(2), "Payroll", 125000.00, "Pending", "Lisa Taylor")
        ).forEach(event -> financialEvents.put(event.getId(), event));
    }
}
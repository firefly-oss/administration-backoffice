package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.dummy.Integration;
import com.vaadin.starter.business.dummy.Report;
import com.vaadin.starter.business.backend.dto.reports.IntegrationDTO;
import com.vaadin.starter.business.backend.dto.reports.ReportDTO;
import com.vaadin.starter.business.backend.mapper.reports.IntegrationMapper;
import com.vaadin.starter.business.backend.mapper.reports.ReportMapper;
import com.vaadin.starter.business.backend.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the ReportsService interface.
 */
@Service
public class ReportsServiceImpl implements ReportsService {

    private final Map<String, Report> reports = new HashMap<>();
    private final Map<String, Integration> integrations = new HashMap<>();

    private final ReportMapper reportMapper;
    private final IntegrationMapper integrationMapper;

    /**
     * Constructor that initializes the mock data and injects the mappers.
     *
     * @param reportMapper      the mapper for Report objects
     * @param integrationMapper the mapper for Integration objects
     */
    @Autowired
    public ReportsServiceImpl(ReportMapper reportMapper, IntegrationMapper integrationMapper) {
        this.reportMapper = reportMapper;
        this.integrationMapper = integrationMapper;
        initReports();
        initIntegrations();
    }

    @Override
    public Collection<Report> getReports() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<ReportDTO> dtos = reportMapper.toDtoList(reports.values());
        return reportMapper.toEntityList(dtos);
    }

    @Override
    public Report getReport(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        ReportDTO dto = reportMapper.toDto(reports.get(id));
        return reportMapper.toEntity(dto);
    }

    @Override
    public Collection<Integration> getIntegrations() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<IntegrationDTO> dtos = integrationMapper.toDtoList(integrations.values());
        return integrationMapper.toEntityList(dtos);
    }

    @Override
    public Integration getIntegration(String id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        IntegrationDTO dto = integrationMapper.toDto(integrations.get(id));
        return integrationMapper.toEntity(dto);
    }

    /**
     * Initialize report data.
     */
    private void initReports() {
        LocalDate now = LocalDate.now();
        Arrays.asList(
            new Report("R001", "Monthly Financial Summary", 
                "Comprehensive financial report providing monthly summary of all transactions and balances", 
                "Published", "Financial", "Main Database", "PDF", 
                true, true, "john.smith@example.com", now.minusDays(5)),
            new Report("R002", "Customer Activity Report", 
                "Detailed analysis of customer activity and engagement metrics", 
                "Draft", "Customer", "Data Warehouse", "Excel", 
                true, false, "emily.davis@example.com", now.minusDays(10)),
            new Report("R003", "Transaction Analysis", 
                "In-depth analysis of transaction patterns and anomalies", 
                "Published", "Operational", "Analytics Platform", "Interactive Dashboard", 
                true, true, "michael.johnson@example.com", now.minusDays(15)),
            new Report("R004", "Compliance Dashboard", 
                "Real-time dashboard for monitoring regulatory compliance", 
                "Draft", "Compliance", "Data Warehouse", "HTML", 
                false, true, "sarah.williams@example.com", now.minusDays(20)),
            new Report("R005", "Performance Metrics", 
                "Key performance indicators and business metrics", 
                "Published", "Executive", "Analytics Platform", "Interactive Dashboard", 
                true, true, "robert.brown@example.com", now.minusDays(25)),
            new Report("R006", "Risk Assessment", 
                "Comprehensive risk analysis and mitigation recommendations", 
                "Draft", "Financial", "Main Database", "PDF", 
                true, false, "jennifer.wilson@example.com", now.minusDays(30))
        ).forEach(report -> reports.put(report.getId(), report));
    }

    /**
     * Initialize integration data.
     */
    private void initIntegrations() {
        LocalDate now = LocalDate.now();
        Arrays.asList(
            new Integration("I001", "Salesforce Export", 
                "Integration for exporting reports to Salesforce CRM", 
                "Active", "Export", "https://api.salesforce.com/integration/reports", 
                "JSON", "Daily", true, true, "john.smith@example.com", now.minusDays(3)),
            new Integration("I002", "Google Analytics", 
                "Integration with Google Analytics for web traffic data", 
                "Active", "Import", "https://analytics.google.com/api/v1", 
                "JSON", "Hourly", true, true, "emily.davis@example.com", now.minusDays(7)),
            new Integration("I003", "Microsoft Power BI", 
                "Bidirectional integration with Power BI for advanced analytics", 
                "Inactive", "Bidirectional", "https://powerbi.microsoft.com/api/reports", 
                "XML", "Weekly", true, true, "michael.johnson@example.com", now.minusDays(14)),
            new Integration("I004", "Tableau Connection", 
                "Connection to Tableau for visualization and reporting", 
                "Active", "Export", "https://tableau.example.com/api/v2", 
                "CSV", "Daily", true, false, "sarah.williams@example.com", now.minusDays(21)),
            new Integration("I005", "Email Distribution", 
                "Automated email distribution of reports to stakeholders", 
                "Active", "Export", "smtp://mail.example.com", 
                "PDF", "Weekly", true, true, "robert.brown@example.com", now.minusDays(28)),
            new Integration("I006", "FTP Data Transfer", 
                "Secure FTP transfer of report data to external systems", 
                "Inactive", "Export", "sftp://ftp.example.com/reports", 
                "CSV", "Monthly", true, true, "jennifer.wilson@example.com", now.minusDays(35))
        ).forEach(integration -> integrations.put(integration.getId(), integration));
    }
}
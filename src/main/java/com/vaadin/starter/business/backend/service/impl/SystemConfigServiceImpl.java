package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.dummy.ConfigItem;
import com.vaadin.starter.business.dummy.MasterDataItem;
import com.vaadin.starter.business.dummy.Notification;
import com.vaadin.starter.business.dummy.Workflow;
import com.vaadin.starter.business.backend.dto.systemconfig.ConfigItemDTO;
import com.vaadin.starter.business.backend.dto.systemconfig.MasterDataItemDTO;
import com.vaadin.starter.business.backend.dto.systemconfig.NotificationDTO;
import com.vaadin.starter.business.backend.dto.systemconfig.WorkflowDTO;
import com.vaadin.starter.business.backend.mapper.systemconfig.ConfigItemMapper;
import com.vaadin.starter.business.backend.mapper.systemconfig.MasterDataItemMapper;
import com.vaadin.starter.business.backend.mapper.systemconfig.NotificationMapper;
import com.vaadin.starter.business.backend.mapper.systemconfig.WorkflowMapper;
import com.vaadin.starter.business.backend.service.SystemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation of the SystemConfigService interface.
 */
@Service
public class SystemConfigServiceImpl implements SystemConfigService {

    private final Map<String, ConfigItem> configItems = new HashMap<>();
    private final Map<String, MasterDataItem> masterDataItems = new HashMap<>();
    private final Map<String, Notification> notifications = new HashMap<>();
    private final Map<String, Workflow> workflows = new HashMap<>();
    
    private final ConfigItemMapper configItemMapper;
    private final MasterDataItemMapper masterDataItemMapper;
    private final NotificationMapper notificationMapper;
    private final WorkflowMapper workflowMapper;

    /**
     * Constructor that initializes the system configuration data.
     */
    @Autowired
    public SystemConfigServiceImpl(
            ConfigItemMapper configItemMapper,
            MasterDataItemMapper masterDataItemMapper,
            NotificationMapper notificationMapper,
            WorkflowMapper workflowMapper) {
        this.configItemMapper = configItemMapper;
        this.masterDataItemMapper = masterDataItemMapper;
        this.notificationMapper = notificationMapper;
        this.workflowMapper = workflowMapper;
        initConfigItems();
        initMasterDataItems();
        initNotifications();
        initWorkflows();
    }

    /**
     * Initialize the configuration items data.
     */
    private void initConfigItems() {
        // Create sample configuration items
        configItems.put("SYS001", new ConfigItem("SYS001", "System Language", 
            "Global system language setting affecting all users and modules.", 
            "Active", "Regional", "English", "Global", LocalDate.now().minusDays(30)));
        
        configItems.put("SYS002", new ConfigItem("SYS002", "Date Format", 
            "Standard date format used throughout the application.", 
            "Active", "Regional", "MM/DD/YYYY", "Global", LocalDate.now().minusDays(60)));
        
        configItems.put("SYS003", new ConfigItem("SYS003", "Currency Settings", 
            "Default currency and formatting options.", 
            "Active", "Regional", "USD ($)", "Global", LocalDate.now().minusDays(90)));
        
        configItems.put("SYS004", new ConfigItem("SYS004", "Timezone Configuration", 
            "System default timezone setting.", 
            "Active", "Regional", "UTC", "Global", LocalDate.now().minusDays(120)));
        
        configItems.put("SYS005", new ConfigItem("SYS005", "Email Server Settings", 
            "SMTP server configuration for system emails.", 
            "Active", "Communication", "smtp.example.com:587", "Global", LocalDate.now().minusDays(45)));
        
        configItems.put("SYS006", new ConfigItem("SYS006", "Security Parameters", 
            "Global security settings including password policies.", 
            "Pending", "Security", "High", "Global", LocalDate.now().minusDays(15)));
    }

    /**
     * Initialize the master data items.
     */
    private void initMasterDataItems() {
        // Create sample master data items
        masterDataItems.put("MD001", new MasterDataItem("MD001", "Customer Types", 
            "Classification of customer accounts.", 
            "Active", "Customer", "Enumeration", "String", LocalDate.now().minusDays(10)));
        
        masterDataItems.put("MD002", new MasterDataItem("MD002", "Product Categories", 
            "Hierarchical product categorization.", 
            "Active", "Product", "Hierarchy", "Tree", LocalDate.now().minusDays(20)));
        
        masterDataItems.put("MD003", new MasterDataItem("MD003", "Country Codes", 
            "ISO country codes with additional metadata.", 
            "Active", "Regional", "Reference", "ISO-3166", LocalDate.now().minusDays(100)));
        
        masterDataItems.put("MD004", new MasterDataItem("MD004", "Currency Codes", 
            "ISO currency codes with conversion rates.", 
            "Active", "Financial", "Reference", "ISO-4217", LocalDate.now().minusDays(50)));
        
        masterDataItems.put("MD005", new MasterDataItem("MD005", "Department Codes", 
            "Internal department classification.", 
            "Pending", "Organization", "Enumeration", "String", LocalDate.now().minusDays(5)));
    }

    /**
     * Initialize the notifications data.
     */
    private void initNotifications() {
        // Create sample notifications
        notifications.put("NOT001", new Notification("NOT001", "System Maintenance", 
            "Scheduled maintenance on Sunday, 2AM-4AM UTC.", 
            "System", "Active", "High", "All Users", 
            LocalDateTime.now().minusDays(2), LocalDate.now().plusDays(5)));
        
        notifications.put("NOT002", new Notification("NOT002", "New Feature Available", 
            "Check out the new reporting dashboard in the Analytics section.", 
            "System", "Active", "Medium", "All Users", 
            LocalDateTime.now().minusDays(5), LocalDate.now().plusDays(10)));
        
        notifications.put("NOT003", new Notification("NOT003", "Password Expiry", 
            "Your password will expire in 7 days. Please update it.", 
            "User", "Active", "High", "Specific User", 
            LocalDateTime.now().minusDays(1), LocalDate.now().plusDays(7)));
        
        notifications.put("NOT004", new Notification("NOT004", "Compliance Update", 
            "New regulatory requirements have been added to the compliance module.", 
            "Alert", "Sent", "High", "Compliance Team", 
            LocalDateTime.now().minusDays(10), LocalDate.now().minusDays(1)));
    }

    /**
     * Initialize the workflows data.
     */
    private void initWorkflows() {
        // Create sample workflows
        workflows.put("WF001", new Workflow("WF001", "Customer Onboarding", 
            "Process for registering and verifying new customers.", 
            "Active", "Customer", 5, 
            Arrays.asList("Sales", "Compliance", "Account Manager"), 
            LocalDate.now().minusDays(45)));
        
        workflows.put("WF002", new Workflow("WF002", "Purchase Approval", 
            "Multi-level approval process for purchases.", 
            "Active", "Finance", 3, 
            Arrays.asList("Requester", "Manager", "Finance"), 
            LocalDate.now().minusDays(60)));
        
        workflows.put("WF003", new Workflow("WF003", "Document Review", 
            "Legal document review and approval process.", 
            "Draft", "Legal", 4, 
            Arrays.asList("Author", "Reviewer", "Legal", "Approver"), 
            LocalDate.now().minusDays(15)));
        
        workflows.put("WF004", new Workflow("WF004", "Incident Response", 
            "Process for handling security incidents.", 
            "Active", "Security", 6, 
            Arrays.asList("Reporter", "First Responder", "Security Team", "Management", "Communications"), 
            LocalDate.now().minusDays(30)));
    }

    @Override
    public Collection<ConfigItemDTO> getConfigItems() {
        return configItemMapper.toDtoList(configItems.values());
    }

    @Override
    public ConfigItemDTO getConfigItem(String id) {
        return configItemMapper.toDto(configItems.get(id));
    }

    @Override
    public Collection<MasterDataItemDTO> getMasterDataItems() {
        return masterDataItemMapper.toDtoList(masterDataItems.values());
    }

    @Override
    public MasterDataItemDTO getMasterDataItem(String id) {
        return masterDataItemMapper.toDto(masterDataItems.get(id));
    }

    @Override
    public Collection<NotificationDTO> getNotifications() {
        return notificationMapper.toDtoList(notifications.values());
    }

    @Override
    public NotificationDTO getNotification(String id) {
        return notificationMapper.toDto(notifications.get(id));
    }

    @Override
    public Collection<WorkflowDTO> getWorkflows() {
        return workflowMapper.toDtoList(workflows.values());
    }

    @Override
    public WorkflowDTO getWorkflow(String id) {
        return workflowMapper.toDto(workflows.get(id));
    }
}
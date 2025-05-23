package com.vaadin.starter.business.backend.service;

import java.util.Collection;

import com.vaadin.starter.business.backend.dto.systemconfig.ConfigItemDTO;
import com.vaadin.starter.business.backend.dto.systemconfig.NotificationDTO;
import com.vaadin.starter.business.backend.dto.systemconfig.MasterDataItemDTO;
import com.vaadin.starter.business.backend.dto.systemconfig.WorkflowDTO;

/**
 * Service interface for managing system configuration entities.
 */
public interface SystemConfigService {
    
    /**
     * Get all configuration items.
     *
     * @return a collection of all configuration items
     */
    Collection<ConfigItemDTO> getConfigItems();
    
    /**
     * Get a configuration item by ID.
     *
     * @param id the ID of the configuration item to retrieve
     * @return the configuration item with the specified ID, or null if not found
     */
    ConfigItemDTO getConfigItem(String id);
    
    /**
     * Get all master data items.
     *
     * @return a collection of all master data items
     */
    Collection<MasterDataItemDTO> getMasterDataItems();
    
    /**
     * Get a master data item by ID.
     *
     * @param id the ID of the master data item to retrieve
     * @return the master data item with the specified ID, or null if not found
     */
    MasterDataItemDTO getMasterDataItem(String id);
    
    /**
     * Get all notifications.
     *
     * @return a collection of all notifications
     */
    Collection<NotificationDTO> getNotifications();
    
    /**
     * Get a notification by ID.
     *
     * @param id the ID of the notification to retrieve
     * @return the notification with the specified ID, or null if not found
     */
    NotificationDTO getNotification(String id);
    
    /**
     * Get all workflows.
     *
     * @return a collection of all workflows
     */
    Collection<WorkflowDTO> getWorkflows();
    
    /**
     * Get a workflow by ID.
     *
     * @param id the ID of the workflow to retrieve
     * @return the workflow with the specified ID, or null if not found
     */
    WorkflowDTO getWorkflow(String id);
}
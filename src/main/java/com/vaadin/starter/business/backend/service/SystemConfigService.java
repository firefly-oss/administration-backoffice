/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


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
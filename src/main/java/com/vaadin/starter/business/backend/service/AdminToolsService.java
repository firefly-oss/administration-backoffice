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
import com.vaadin.starter.business.backend.VersionInfo;
import com.vaadin.starter.business.backend.SystemComponent;
import com.vaadin.starter.business.backend.MaintenanceTask;
import java.util.UUID;

/**
 * Service interface for admin tools functionality.
 */
public interface AdminToolsService {

    /**
     * Get all version information.
     *
     * @return a collection of all version information
     */
    Collection<VersionInfo> getVersions();

    /**
     * Get a specific version by its ID.
     *
     * @param id the ID of the version to retrieve
     * @return the version with the specified ID, or null if not found
     */
    VersionInfo getVersion(UUID id);

    /**
     * Get all system components for monitoring.
     *
     * @return a collection of all system components
     */
    Collection<SystemComponent> getSystemComponents();

    /**
     * Get a specific system component by its ID.
     *
     * @param id the ID of the system component to retrieve
     * @return the system component with the specified ID, or null if not found
     */
    SystemComponent getSystemComponent(UUID id);

    /**
     * Get all database maintenance tasks.
     *
     * @return a collection of all maintenance tasks
     */
    Collection<MaintenanceTask> getMaintenanceTasks();

    /**
     * Get a specific maintenance task by its ID.
     *
     * @param id the ID of the maintenance task to retrieve
     * @return the maintenance task with the specified ID, or null if not found
     */
    MaintenanceTask getMaintenanceTask(UUID id);
}

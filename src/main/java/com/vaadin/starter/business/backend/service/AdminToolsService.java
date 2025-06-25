package com.vaadin.starter.business.backend.service;

import java.util.Collection;
import com.vaadin.starter.business.dummy.VersionInfo;
import com.vaadin.starter.business.dummy.SystemComponent;
import com.vaadin.starter.business.dummy.MaintenanceTask;

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
    VersionInfo getVersion(Long id);

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
    SystemComponent getSystemComponent(Long id);

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
    MaintenanceTask getMaintenanceTask(Long id);
}

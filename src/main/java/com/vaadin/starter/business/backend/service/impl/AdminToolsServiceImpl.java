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


package com.vaadin.starter.business.backend.service.impl;

import com.vaadin.starter.business.backend.MaintenanceTask;
import com.vaadin.starter.business.backend.SystemComponent;
import com.vaadin.starter.business.backend.VersionInfo;
import com.vaadin.starter.business.backend.dto.admintools.MaintenanceTaskDTO;
import com.vaadin.starter.business.backend.dto.admintools.SystemComponentDTO;
import com.vaadin.starter.business.backend.dto.admintools.VersionInfoDTO;
import com.vaadin.starter.business.backend.mapper.admintools.MaintenanceTaskMapper;
import com.vaadin.starter.business.backend.mapper.admintools.SystemComponentMapper;
import com.vaadin.starter.business.backend.mapper.admintools.VersionInfoMapper;
import com.vaadin.starter.business.backend.service.AdminToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Implementation of the AdminToolsService interface.
 */
@Service
public class AdminToolsServiceImpl implements AdminToolsService {

    private final Map<UUID, VersionInfo> versions = new HashMap<>();
    private final Map<UUID, SystemComponent> systemComponents = new HashMap<>();
    private final Map<UUID, MaintenanceTask> maintenanceTasks = new HashMap<>();
    private final Random random = new Random(1);

    private final VersionInfoMapper versionInfoMapper;
    private final SystemComponentMapper systemComponentMapper;
    private final MaintenanceTaskMapper maintenanceTaskMapper;

    // Constants for generating mock data
    private static final String[] VERSION_STATUSES = {"Current", "Deprecated", "Planned", "Testing"};
    private static final String[] ENVIRONMENTS = {"Production", "Staging", "QA", "Development"};
    private static final String[] SYSTEM_COMPONENTS = {
            "Application Server", "Database Server", "Message Queue", 
            "Cache Server", "Load Balancer", "API Gateway"
    };
    private static final String[] SYSTEM_STATUSES = {"Healthy", "Warning", "Critical"};
    private static final String[] METRICS = {
            "CPU: 45%", "Memory: 78%", "Disk: 62%", 
            "Network: 120Mbps", "Requests: 250/s", "Latency: 85ms"
    };
    private static final String[] MAINTENANCE_TASKS = {
            "Database Backup", "Index Optimization", "Data Archiving", 
            "Integrity Check", "Statistics Update", "Log Cleanup"
    };
    private static final String[] MAINTENANCE_STATUSES = {"Scheduled", "Running", "Completed", "Failed"};
    private static final String[] DATABASES = {
            "Main Database", "Reporting DB", "Archive DB", "Customer DB", "Transaction DB"
    };
    private static final String[] FREQUENCIES = {"Daily", "Weekly", "Monthly", "On Demand"};
    private static final String[] NAMES = {
            "John Smith", "Jane Doe", "Robert Johnson", "Emily Davis", 
            "Michael Wilson", "Sarah Brown", "David Miller", "Lisa Taylor"
    };
    private static final String[] EMAIL_DOMAINS = {
            "example.com", "company.org", "enterprise.net", "business.io"
    };

    /**
     * Constructor that initializes the mock data and injects the mappers.
     *
     * @param versionInfoMapper the mapper for VersionInfo objects
     * @param systemComponentMapper the mapper for SystemComponent objects
     * @param maintenanceTaskMapper the mapper for MaintenanceTask objects
     */
    @Autowired
    public AdminToolsServiceImpl(VersionInfoMapper versionInfoMapper,
                                SystemComponentMapper systemComponentMapper,
                                MaintenanceTaskMapper maintenanceTaskMapper) {
        this.versionInfoMapper = versionInfoMapper;
        this.systemComponentMapper = systemComponentMapper;
        this.maintenanceTaskMapper = maintenanceTaskMapper;
        initVersions();
        initSystemComponents();
        initMaintenanceTasks();
    }

    @Override
    public Collection<VersionInfo> getVersions() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<VersionInfoDTO> dtos = versionInfoMapper.toDtoList(versions.values());
        return versionInfoMapper.toEntityList(dtos);
    }

    @Override
    public VersionInfo getVersion(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        VersionInfoDTO dto = versionInfoMapper.toDto(versions.get(id));
        return versionInfoMapper.toEntity(dto);
    }

    @Override
    public Collection<SystemComponent> getSystemComponents() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<SystemComponentDTO> dtos = systemComponentMapper.toDtoList(systemComponents.values());
        return systemComponentMapper.toEntityList(dtos);
    }

    @Override
    public SystemComponent getSystemComponent(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        SystemComponentDTO dto = systemComponentMapper.toDto(systemComponents.get(id));
        return systemComponentMapper.toEntity(dto);
    }

    @Override
    public Collection<MaintenanceTask> getMaintenanceTasks() {
        // Convert to DTOs and back to domain objects to demonstrate the pattern
        Collection<MaintenanceTaskDTO> dtos = maintenanceTaskMapper.toDtoList(maintenanceTasks.values());
        return maintenanceTaskMapper.toEntityList(dtos);
    }

    @Override
    public MaintenanceTask getMaintenanceTask(UUID id) {
        // Convert to DTO and back to domain object to demonstrate the pattern
        MaintenanceTaskDTO dto = maintenanceTaskMapper.toDto(maintenanceTasks.get(id));
        return maintenanceTaskMapper.toEntity(dto);
    }

    /**
     * Initialize version information data.
     */
    private void initVersions() {
        for (long i = 0; i < 20; i++) {
            UUID id = UUID.randomUUID();

            // Generate a version number like 2.3.1, 3.0.0, etc.
            int major = 1 + (int)(Math.abs(i) % 5);
            int minor = (int)(Math.abs(i) % 10);
            int patch = (int)(Math.abs(i) % 5);
            String versionNumber = major + "." + minor + "." + patch;

            String description = "This version includes new features, bug fixes, and performance improvements.";
            String status = VERSION_STATUSES[(int)(Math.abs(i) % VERSION_STATUSES.length)];
            String environment = ENVIRONMENTS[(int)(Math.abs(i) % ENVIRONMENTS.length)];

            LocalDate releaseDate = LocalDate.now().plusDays(random.nextBoolean() ? 
                    -random.nextInt(180) : random.nextInt(180));

            String releasedBy = NAMES[(int)(Math.abs(i) % NAMES.length)];
            String changeLog = "- Added new feature X\n- Fixed bug in module Y\n- Improved performance of Z";

            versions.put(id, new VersionInfo(id, versionNumber, description, status, 
                    environment, releaseDate, releasedBy, changeLog));
        }
    }

    /**
     * Initialize system component data.
     */
    private void initSystemComponents() {
        for (long i = 0; i < 20; i++) {
            UUID id = UUID.randomUUID();

            String componentName = SYSTEM_COMPONENTS[(int)(Math.abs(i) % SYSTEM_COMPONENTS.length)];
            String serverName = "server-" + (random.nextInt(100) + 1) + ".example.com";
            String description = "System component providing critical functionality for the application infrastructure.";
            String status = SYSTEM_STATUSES[(int)(Math.abs(i) % SYSTEM_STATUSES.length)];

            String cpuUsage = (10 + random.nextInt(80)) + "%";
            String memoryUsage = (20 + random.nextInt(70)) + "%";
            String diskUsage = (30 + random.nextInt(60)) + "%";

            String keyMetric = METRICS[(int)(Math.abs(i) % METRICS.length)];
            LocalDateTime lastChecked = LocalDateTime.now().minusHours(random.nextInt(24));

            systemComponents.put(id, new SystemComponent(id, componentName, serverName, description, 
                    status, cpuUsage, memoryUsage, diskUsage, keyMetric, lastChecked));
        }
    }

    /**
     * Initialize maintenance task data.
     */
    private void initMaintenanceTasks() {
        for (long i = 0; i < 20; i++) {
            UUID id = UUID.randomUUID();

            String taskName = MAINTENANCE_TASKS[(int)(Math.abs(i) % MAINTENANCE_TASKS.length)];
            String description = "Database maintenance task to ensure optimal performance and data integrity.";
            String status = MAINTENANCE_STATUSES[(int)(Math.abs(i) % MAINTENANCE_STATUSES.length)];
            String database = DATABASES[(int)(Math.abs(i) % DATABASES.length)];
            String frequency = FREQUENCIES[(int)(Math.abs(i) % FREQUENCIES.length)];

            LocalTime scheduledTime = LocalTime.of(random.nextInt(24), 0);
            String duration = (5 + random.nextInt(55)) + " minutes";
            LocalDate lastRun = LocalDate.now().minusDays(random.nextInt(10) + 1);

            String name = NAMES[(int)(Math.abs(i) % NAMES.length)];
            String domain = EMAIL_DOMAINS[(int)(Math.abs(i) % EMAIL_DOMAINS.length)];
            String managedBy = name.toLowerCase().replace(" ", ".") + "@" + domain;

            maintenanceTasks.put(id, new MaintenanceTask(id, taskName, description, status, 
                    database, frequency, scheduledTime, duration, lastRun, managedBy));
        }
    }
}

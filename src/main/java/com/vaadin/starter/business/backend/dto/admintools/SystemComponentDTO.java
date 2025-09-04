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


package com.vaadin.starter.business.backend.dto.admintools;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data Transfer Object for SystemComponent.
 */
public class SystemComponentDTO {

    private UUID id;
    private String componentName;
    private String serverName;
    private String description;
    private String status;
    private String cpuUsage;
    private String memoryUsage;
    private String diskUsage;
    private String keyMetric;
    private LocalDateTime lastChecked;

    /**
     * Default constructor.
     */
    public SystemComponentDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id            the component ID
     * @param componentName the component name
     * @param serverName    the server name
     * @param description   the description
     * @param status        the status
     * @param cpuUsage      the CPU usage
     * @param memoryUsage   the memory usage
     * @param diskUsage     the disk usage
     * @param keyMetric     the key metric
     * @param lastChecked   the last checked timestamp
     */
    public SystemComponentDTO(UUID id, String componentName, String serverName, String description,
                            String status, String cpuUsage, String memoryUsage, String diskUsage,
                            String keyMetric, LocalDateTime lastChecked) {
        this.id = id;
        this.componentName = componentName;
        this.serverName = serverName;
        this.description = description;
        this.status = status;
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.diskUsage = diskUsage;
        this.keyMetric = keyMetric;
        this.lastChecked = lastChecked;
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public String getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(String diskUsage) {
        this.diskUsage = diskUsage;
    }

    public String getKeyMetric() {
        return keyMetric;
    }

    public void setKeyMetric(String keyMetric) {
        this.keyMetric = keyMetric;
    }

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }

    public void setLastChecked(LocalDateTime lastChecked) {
        this.lastChecked = lastChecked;
    }
}
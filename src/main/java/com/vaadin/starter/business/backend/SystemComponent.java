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


package com.vaadin.starter.business.backend;

import java.time.LocalDateTime;
import java.util.UUID;

public class SystemComponent {

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

    public SystemComponent(UUID id, String componentName, String serverName, String description, 
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

    public UUID getId() {
        return id;
    }

    public String getComponentName() {
        return componentName;
    }

    public String getServerName() {
        return serverName;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public String getDiskUsage() {
        return diskUsage;
    }

    public String getKeyMetric() {
        return keyMetric;
    }

    public LocalDateTime getLastChecked() {
        return lastChecked;
    }
}
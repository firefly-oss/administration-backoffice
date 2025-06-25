package com.vaadin.starter.business.dummy;

import java.time.LocalDateTime;

public class SystemComponent {

    private Long id;
    private String componentName;
    private String serverName;
    private String description;
    private String status;
    private String cpuUsage;
    private String memoryUsage;
    private String diskUsage;
    private String keyMetric;
    private LocalDateTime lastChecked;

    public SystemComponent(Long id, String componentName, String serverName, String description, 
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

    public Long getId() {
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
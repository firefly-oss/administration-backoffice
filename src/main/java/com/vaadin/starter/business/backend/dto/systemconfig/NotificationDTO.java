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


package com.vaadin.starter.business.backend.dto.systemconfig;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Data Transfer Object for Notification.
 */
public class NotificationDTO {

    private String id;
    private String title;
    private String message;
    private String type;
    private String status;
    private String priority;
    private String target;
    private LocalDateTime createdAt;
    private LocalDate expiryDate;

    /**
     * Default constructor.
     */
    public NotificationDTO() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id        the notification ID
     * @param title     the notification title
     * @param message   the notification message
     * @param type      the notification type (System, User, Alert)
     * @param status    the notification status (Active, Sent, Expired)
     * @param priority  the notification priority (High, Medium, Low)
     * @param target    the notification target (All Users, Specific Role, Specific User)
     * @param createdAt the date and time when the notification was created
     * @param expiryDate the date when the notification expires
     */
    public NotificationDTO(String id, String title, String message, String type, String status, String priority, String target, LocalDateTime createdAt, LocalDate expiryDate) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.target = target;
        this.createdAt = createdAt;
        this.expiryDate = expiryDate;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    /**
     * Check if the notification is active.
     *
     * @return true if the status is "Active", false otherwise
     */
    public boolean isActive() {
        return "Active".equals(status);
    }
}
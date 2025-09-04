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

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entity class for Notification.
 */
public class Notification {
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
    public Notification() {
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
    public Notification(String id, String title, String message, String type, String status, String priority, String target, LocalDateTime createdAt, LocalDate expiryDate) {
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

    /**
     * Get the notification ID.
     *
     * @return the notification ID
     */
    public String getId() {
        return id;
    }

    /**
     * Get the notification title.
     *
     * @return the notification title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the notification message.
     *
     * @return the notification message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get the notification type.
     *
     * @return the notification type
     */
    public String getType() {
        return type;
    }

    /**
     * Get the notification status.
     *
     * @return the notification status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Get the notification priority.
     *
     * @return the notification priority
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Get the notification target.
     *
     * @return the notification target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Get the date and time when the notification was created.
     *
     * @return the created date and time
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Get the date when the notification expires.
     *
     * @return the expiry date
     */
    public LocalDate getExpiryDate() {
        return expiryDate;
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
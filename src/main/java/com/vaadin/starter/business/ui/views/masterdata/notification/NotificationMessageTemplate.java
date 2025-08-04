package com.vaadin.starter.business.ui.views.masterdata.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Wrapper for NotificationMessageTemplateDTO that uses a local Status enum instead of StatusEnum.
 * This is used for the UI to avoid dependency on the external StatusEnum.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessageTemplate {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    private Long templateId;
    private Long messageId;
    private NotificationMessageCatalog notificationMessage;
    private String templateName;
    private String templateContent;
    private String templateType;
    private String version;
    private Map<String, Object> templateVariables;
    private Status status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public boolean isActive() {
        return status == Status.ACTIVE;
    }
}
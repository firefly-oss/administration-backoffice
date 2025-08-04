package com.vaadin.starter.business.ui.views.masterdata.notification;

import com.vaadin.starter.business.ui.views.masterdata.message.MessageTypeCatalogDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Wrapper for NotificationMessageCatalogDTO that uses a local Status enum instead of StatusEnum.
 * This is used for the UI to avoid dependency on the external StatusEnum.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessageCatalog {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    private Long messageId;
    private String messageCode;
    private Long typeId;
    private MessageTypeCatalogDTO messageType;
    private String eventType;
    private String description;
    private String defaultSubject;
    private String defaultMessage;
    private Map<String, Object> parameters;
    private Status status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public boolean isActive() {
        return status == Status.ACTIVE;
    }
}

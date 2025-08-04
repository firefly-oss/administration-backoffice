package com.vaadin.starter.business.ui.views.masterdata.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Wrapper for NotificationMessageLocalizationDTO that uses a local Status enum instead of StatusEnum.
 * This is used for the UI to avoid dependency on the external StatusEnum.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationMessageLocalization {

    // Simple enum for status
    public enum Status {
        ACTIVE, INACTIVE
    }

    private Long localizationId;
    private Long messageId;
    private Long localeId;
    private String subject;
    private String message;
    private Status status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    
    // Reference to parent notification message
    private NotificationMessageCatalog notificationMessage;
    
    // Reference to locale information (could be expanded in a real implementation)
    private String localeName;
    private String localeCode;

    public boolean isActive() {
        return status == Status.ACTIVE;
    }
}
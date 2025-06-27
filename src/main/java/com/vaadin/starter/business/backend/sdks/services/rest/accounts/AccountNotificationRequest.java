package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

/**
 * Request object for Account Notification operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountNotificationRequest {
    private Long id;
    private Long accountId;
    private String type;
    private String message;
    private Boolean read;
    private String status;
    private String priority;
    private String category;
    private String description;
}
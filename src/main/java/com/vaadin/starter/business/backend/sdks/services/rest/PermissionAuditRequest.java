package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

/**
 * Request object for PermissionAudit operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PermissionAuditRequest {
    private Long id;
    private Long partyId;
    private String action;
    private String resourceType;
    private String resourceId;
    private String userId;
    private String timestamp;
    private String details;
    // Add any additional fields that might be needed for permission audit
}
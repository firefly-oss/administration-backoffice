package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Distributor Audit Log operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DistributorAuditLogRequest {
    private String action;
    private String details;
    private String performedBy;
    private LocalDateTime timestamp;
}
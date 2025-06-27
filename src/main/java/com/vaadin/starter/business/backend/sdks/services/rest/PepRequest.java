package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

/**
 * Request object for PEP (Politically Exposed Person) operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PepRequest {
    private Long id;
    private Long partyId;
    private String pepStatus;
    private String riskLevel;
    private String screeningDate;
    private String screeningSource;
    private String notes;
    // Add any additional fields that might be needed for PEP
}
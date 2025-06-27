package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for PartyStatus operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartyStatusRequest {
    private Long id;
    private Long partyId;
    private String status;
    private String statusReason;
    // Add any additional fields that might be needed for party status
}
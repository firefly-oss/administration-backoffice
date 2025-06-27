package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for PartyRelationship operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartyRelationshipRequest {
    private Long id;
    private Long partyId;
    private Long relatedPartyId;
    private String relationshipType;
    private String status;
    // Add any additional fields that might be needed for party relationships
}
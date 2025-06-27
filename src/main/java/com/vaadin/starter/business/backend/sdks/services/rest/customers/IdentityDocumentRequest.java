package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for IdentityDocument operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IdentityDocumentRequest {
    private Long id;
    private String type;
    private String number;
    private String issueDate;
    private String expiryDate;
    private String issuingAuthority;
    private Long partyId;

}
package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Contract Party operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractPartyRequest {
    private Long id;
    private String partyType;
    private String partyName;
    private String partyRole;
    private String contactInfo;
    private Long contractId;
    private String status;
    private String notes;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
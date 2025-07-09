package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Contract Document operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractDocumentRequest {
    private Long id;
    private String documentType;
    private String documentName;
    private String documentContent;
    private String contentType;
    private Long contractId;
    private String description;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
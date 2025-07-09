package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for Contract Term operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractTermRequest {
    private Long id;
    private String termType;
    private String termName;
    private String description;
    private String value;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Long contractId;
    private String status;
    private String notes;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for Contract operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractRequest {
    private Long id;
    private String contractNumber;
    private String contractType;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long customerId;
    private String description;
    private Double amount;
    private String currency;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for Contract Event operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractEventRequest {
    private Long id;
    private String eventType;
    private LocalDate eventDate;
    private String description;
    private Long contractId;
    private String status;
    private String notes;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
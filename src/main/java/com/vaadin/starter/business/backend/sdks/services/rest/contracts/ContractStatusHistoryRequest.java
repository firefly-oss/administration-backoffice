package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Contract Status History operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractStatusHistoryRequest {
    private Long id;
    private String status;
    private String reason;
    private String changedBy;
    private LocalDateTime statusDate;
    private Long contractId;
    private String notes;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
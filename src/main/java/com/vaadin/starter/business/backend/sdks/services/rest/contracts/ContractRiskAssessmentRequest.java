package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for Contract Risk Assessment operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractRiskAssessmentRequest {
    private Long id;
    private String riskType;
    private String riskLevel;
    private String description;
    private LocalDate assessmentDate;
    private String assessedBy;
    private Long contractId;
    private String mitigationPlan;
    private String status;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
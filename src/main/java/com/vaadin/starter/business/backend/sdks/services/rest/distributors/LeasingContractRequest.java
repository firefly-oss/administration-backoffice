package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Leasing Contract operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeasingContractRequest {
    private Long id;
    private String contractNumber;
    private Long distributorId;
    private Long productId;
    private Long partyId;
    private String status;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double amount;
    private String currency;
    private String paymentFrequency;
    private Integer termMonths;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long approvedBy;
}
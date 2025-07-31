package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering Leasing Contracts.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeasingContractFilterRequest {
    private String contractNumber;
    private Long distributorId;
    private Long productId;
    private Long partyId;
    private String status;
    private LocalDateTime startDateFrom;
    private LocalDateTime startDateTo;
    private LocalDateTime endDateFrom;
    private LocalDateTime endDateTo;
    private Double amountMin;
    private Double amountMax;
    private String currency;
    private String paymentFrequency;
    private Integer termMonthsMin;
    private Integer termMonthsMax;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private String sortDirection;
}
package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Lending Configuration operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LendingConfigurationRequest {
    private Long id;
    private Long distributorId;
    private Long productId;
    private Long lendingTypeId;
    private String name;
    private String description;
    private Boolean isDefault;
    private Boolean isActive;
    private Double interestRate;
    private Integer termMonths;
    private String paymentFrequency;
    private Double downPaymentPercentage;
    private Double maxLoanAmount;
    private String currency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
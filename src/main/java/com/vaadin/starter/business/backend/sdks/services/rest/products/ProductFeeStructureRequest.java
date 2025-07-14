package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request object for ProductFeeStructure operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFeeStructureRequest {
    private String name;
    private String description;
    private String code;
    private BigDecimal amount;
    private String currency;
    private String feeType;
    private String calculationType;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Boolean active;
    private Long productId;
    private String structureType;
    private Integer priority;
}
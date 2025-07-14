package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request object for ProductFeeComponent operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFeeComponentRequest {
    private String name;
    private String description;
    private String code;
    private BigDecimal amount;
    private String currency;
    private String componentType;
    private String calculationMethod;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Boolean active;
    private Long feeStructureId;
    private Integer displayOrder;
}
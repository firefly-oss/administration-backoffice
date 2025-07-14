package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request object for ProductLimit operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductLimitRequest {
    private String name;
    private String description;
    private String limitType;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String unit;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Boolean active;
    private Long productId;
}
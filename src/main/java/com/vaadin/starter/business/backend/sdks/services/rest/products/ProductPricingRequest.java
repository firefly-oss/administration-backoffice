package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request object for ProductPricing operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductPricingRequest {
    private String name;
    private String description;
    private String pricingType;
    private BigDecimal amount;
    private String currency;
    private String calculationMethod;
    private Boolean active;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Integer priority;
    private Long productId;
}
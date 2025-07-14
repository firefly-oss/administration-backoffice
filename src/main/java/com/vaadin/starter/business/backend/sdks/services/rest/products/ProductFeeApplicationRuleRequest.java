package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Request object for ProductFeeApplicationRule operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFeeApplicationRuleRequest {
    private String ruleName;
    private String description;
    private String ruleType;
    private String conditionExpression;
    private BigDecimal thresholdValue;
    private String comparisonOperator;
    private Boolean active;
    private Integer priority;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Long productId;
    private Long feeStructureId;
    private Long componentId;
}
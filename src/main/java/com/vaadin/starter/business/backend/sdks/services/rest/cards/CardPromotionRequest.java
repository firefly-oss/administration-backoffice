package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Promotion operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardPromotionRequest {
    private Long id;
    private Long cardId;
    private String promotionName;
    private String promotionCode;
    private String description;
    private String startDate;
    private String endDate;
    private Double discountPercentage;
    private Double discountAmount;
    private String promotionType;
    private String eligibilityCriteria;
    private Integer usageLimit;
    private Integer usageCount;
    private Boolean isActive;
    private String termsAndConditions;
    private String merchantId;
    private String categoryId;
    private String status;
}
package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Limit operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardLimitRequest {
    private Long id;
    private Long cardId;
    private String limitType;
    private Double amount;
    private String currency;
    private String period;
    private String channel;
    private String merchantCategory;
    private String country;
    private Boolean isActive;
    private String startDate;
    private String endDate;
    private String status;
    private String description;
    private String lastUpdated;
}
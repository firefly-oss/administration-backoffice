package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Balance operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardBalanceRequest {
    private Long id;
    private Long cardId;
    private String balanceType;
    private Double amount;
    private String currency;
    private String status;
    private String lastUpdated;
    private String description;
    private Boolean isAvailable;
    private Double reservedAmount;
    private Double pendingAmount;
}
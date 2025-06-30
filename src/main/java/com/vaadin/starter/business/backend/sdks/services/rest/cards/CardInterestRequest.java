package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Interest operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardInterestRequest {
    private Long id;
    private Long cardId;
    private String interestType;
    private Double rate;
    private String calculationMethod;
    private String frequency;
    private String startDate;
    private String endDate;
    private Boolean isCompound;
    private String status;
    private String description;
    private String lastCalculationDate;
    private Double minimumBalance;
    private Double maximumBalance;
    private String currency;
}
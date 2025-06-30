package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Program operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardProgramRequest {
    private Long id;
    private String programName;
    private String programCode;
    private Long issuerId;
    private Long binId;
    private Long cardTypeId;
    private Long cardNetworkId;
    private String startDate;
    private String endDate;
    private Long defaultDesignId;
    private Integer maxCardsPerParty;
    private Double defaultDailyLimit;
    private Double defaultMonthlyLimit;
    private Double defaultCreditLimit;
    private Integer defaultCardValidityYears;
    private Boolean supportsContactless;
    private Boolean supportsInternational;
    private Boolean supportsAtm;
    private Boolean supportsOnline;
    private Boolean supportsRecurring;
    private Boolean isActive;
    private String description;
}
package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardRequest {
    private Long id;
    private Long cardTypeId;
    private Long cardNetworkId;
    private Long issuerId;
    private Long binId;
    private Long partyId;
    private String cardHolderName;
    private Boolean isPhysical;
    private Boolean isVirtual;
    private Long designId;
    private Double dailyLimit;
    private Double monthlyLimit;
    private Double creditLimit;
    private String status;
    private String description;
}
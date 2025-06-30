package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Dispute operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardDisputeRequest {
    private Long id;
    private Long cardId;
    private String disputeType;
    private String disputeReason;
    private String status;
    private String description;
    private Double amount;
    private String currency;
    private String transactionReference;
    private String merchantName;
    private String disputeDate;
    private String resolutionDate;
    private String resolutionNotes;
    private String evidenceProvided;
    private String createdBy;
    private String updatedBy;
}
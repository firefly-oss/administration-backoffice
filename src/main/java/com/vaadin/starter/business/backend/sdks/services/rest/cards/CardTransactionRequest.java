package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Transaction operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardTransactionRequest {
    private Long id;
    private Long cardId;
    private Double amount;
    private String currency;
    private String transactionDate;
    private String transactionType;
    private String merchantName;
    private String merchantId;
    private String merchantCategory;
    private String merchantLocation;
    private String authorizationCode;
    private String referenceNumber;
    private String status;
    private String description;
    private String category;
    private Boolean isInternational;
    private String country;
    private String city;
    private String postalCode;
    private String terminalId;
    private String cardholderPresent;
    private String entryMode;
    private String responseCode;
    private String responseMessage;
    private Boolean isDisputed;
    private String disputeStatus;
    private String notes;
}
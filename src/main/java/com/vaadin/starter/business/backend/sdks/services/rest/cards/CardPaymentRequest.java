package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Payment operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardPaymentRequest {
    private Long id;
    private Long cardId;
    private Double amount;
    private String date;
    private String source;
    private String referenceNumber;
    private String status;
    private String description;
    private String paymentMethod;
    private Boolean isProcessed;
    private String processingDate;
    private String notes;
}
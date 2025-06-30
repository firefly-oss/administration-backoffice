package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Activity operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardActivityRequest {
    private Long id;
    private Long cardId;
    private String activityType;
    private String status;
    private String description;
    private String location;
    private String deviceInfo;
    private String ipAddress;
    private String timestamp;
    private Double amount;
    private String currency;
    private String merchantName;
    private String merchantCategory;
    private String referenceNumber;
}
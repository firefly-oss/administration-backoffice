package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Network operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardNetworkRequest {
    private Long id;
    private String name;
    private String code;
    private String regionCoverage;
    private String supportContact;
    private String apiEndpoint;
    private String authMethod;
    private String apiKey;
    private String username;
    private String password;
    private String certificatePath;
    private Boolean supportsCreditCards;
    private Boolean supportsDebitCards;
    private Boolean supportsPrepaidCards;
    private String supportedFeatures;
    private String processingFees;
    private String settlementTimeframe;
    private String status;
    private Boolean isActive;
    private String description;
}
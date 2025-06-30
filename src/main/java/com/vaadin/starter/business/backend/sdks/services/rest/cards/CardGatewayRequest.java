package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Gateway operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardGatewayRequest {
    private Long id;
    private String name;
    private String provider;
    private String integrationType;
    private String apiEndpoint;
    private String authMethod;
    private String apiKey;
    private String username;
    private String password;
    private String certificatePath;
    private Boolean supportsCreditCards;
    private Boolean supportsDebitCards;
    private Boolean supportsPrepaidCards;
    private String supportedNetworks;
    private String supportedCurrencies;
    private String status;
    private String description;
    private Boolean isActive;
}
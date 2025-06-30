package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Virtual Card operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VirtualCardRequest {
    private Long id;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private String status;
    private String activationStatus;
    private String tokenizationStatus;
    private String deviceType;
    private String walletProvider;
    private String securityFeatures;
    private Boolean isActive;
    private String description;
}
package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Provider operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardProviderRequest {
    private Long id;
    private Long cardId;
    private String providerName;
    private String providerType;
    private String providerCode;
    private String contactPerson;
    private String contactEmail;
    private String contactPhone;
    private String apiEndpoint;
    private String apiKey;
    private String username;
    private String password;
    private String authMethod;
    private String serviceLevel;
    private String supportHours;
    private String supportContact;
    private String contractNumber;
    private String contractStartDate;
    private String contractEndDate;
    private Double monthlyFee;
    private String billingCycle;
    private Boolean isActive;
    private String status;
    private String description;
}
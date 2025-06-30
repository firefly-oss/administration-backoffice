package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Merchant operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardMerchantRequest {
    private Long id;
    private String merchantReference;
    private String merchantName;
    private String merchantLegalName;
    private String merchantCategoryCode;
    private String merchantType;
    private String merchantStatus;
    private String country;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String taxId;
    private String registrationNumber;
    private String supportedNetworks;
    private String supportedPaymentMethods;
    private String riskRating;
    private Integer riskScore;
    private String settlementFrequency;
    private String settlementBankDetails;
    private Boolean isActive;
    private String description;
}
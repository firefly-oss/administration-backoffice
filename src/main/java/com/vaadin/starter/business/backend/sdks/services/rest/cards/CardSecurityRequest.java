package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Security operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardSecurityRequest {
    private Long id;
    private Long cardId;
    private String securityType;
    private String securityLevel;
    private Boolean requirePin;
    private Boolean requireCvv;
    private Boolean requireSignature;
    private Boolean enableOnlinePurchases;
    private Boolean enableInternationalTransactions;
    private Boolean enableContactlessPayments;
    private Boolean enableAtmWithdrawals;
    private Boolean enableMagneticStripe;
    private Boolean enableChip;
    private Integer maxDailyTransactions;
    private Double maxDailyAmount;
    private Integer maxMonthlyTransactions;
    private Double maxMonthlyAmount;
    private String allowedMerchantCategories;
    private String blockedMerchantCategories;
    private String allowedCountries;
    private String blockedCountries;
    private String notificationEmail;
    private String notificationPhone;
    private Boolean notifyOnPurchase;
    private Boolean notifyOnDecline;
    private Boolean notifyOnInternational;
    private Boolean notifyOnLargeAmount;
    private Double largeAmountThreshold;
    private Boolean isActive;
    private String status;
    private String description;
}
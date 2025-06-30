package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Issuer operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IssuerRequest {
    private Long id;
    private String name;
    private String code;
    private String contactName;
    private String contactEmail;
    private String contactPhone;
    private String address;
    private String country;
    private String supportedCardTypes;
    private String binRanges;
    private String regulatoryInfo;
    private String status;
    private Boolean isActive;
    private String description;
}
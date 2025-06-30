package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for BIN operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BINRequest {
    private Long id;
    private String binNumber;
    private Integer binLength;
    private Long issuerId;
    private Long cardNetworkId;
    private Long cardTypeId;
    private String countryCode;
    private String currencyCode;
    private Boolean isActive;
    private String description;
}
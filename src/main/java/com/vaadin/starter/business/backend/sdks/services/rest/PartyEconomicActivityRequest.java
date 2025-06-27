package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

/**
 * Request object for PartyEconomicActivity operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartyEconomicActivityRequest {
    private Long id;
    private Long partyId;
    private String code;
    private String description;
    private Boolean primary;
    private String status;
}
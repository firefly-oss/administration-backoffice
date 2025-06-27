package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

/**
 * Request object for Party operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartyRequest {
    private String id;
    private String type; // "LEGAL_PERSON" or "NATURAL_PERSON"
    private LegalPersonRequest legalPerson;
    private NaturalPersonRequest naturalPerson;

}
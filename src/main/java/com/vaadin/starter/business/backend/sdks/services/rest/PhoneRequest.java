package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

/**
 * Request object for Phone operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhoneRequest {
    private Long id;
    private String phoneNumber;
    private String type;
    private Boolean primary;
    private Long partyId;
}
package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for Address operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressRequest {
    private Long id;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
    private Long partyId;

}
package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for Consent operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsentRequest {
    private Long id;
    private String type;
    private String description;
    private String status;
    private Long partyId;

}
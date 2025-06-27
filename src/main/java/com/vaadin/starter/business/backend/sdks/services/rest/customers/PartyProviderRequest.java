package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for PartyProvider operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PartyProviderRequest {
    private Long id;
    private Long partyId;
    private String code;
    private String name;
    private String description;
    private String status;
}
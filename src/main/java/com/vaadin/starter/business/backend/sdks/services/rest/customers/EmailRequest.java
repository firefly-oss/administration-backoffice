package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for Email operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailRequest {
    private Long id;
    private String email;
    private String type;
    private Boolean primary;
    private Long partyId;

}
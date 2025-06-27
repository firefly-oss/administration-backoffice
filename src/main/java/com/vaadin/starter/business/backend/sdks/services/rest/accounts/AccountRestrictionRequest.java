package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

/**
 * Request object for Account Restriction operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRestrictionRequest {
    private Long id;
    private Long accountId;
    private String type;
    private String value;
    private String description;
    private String status;
}
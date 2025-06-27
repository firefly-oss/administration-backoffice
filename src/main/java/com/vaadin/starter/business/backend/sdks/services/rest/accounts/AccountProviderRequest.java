package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

/**
 * Request object for Account Provider operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountProviderRequest {
    private Long id;
    private Long accountId;
    private String code;
    private String name;
    private String description;
    private String status;
}
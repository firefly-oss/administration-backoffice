package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

/**
 * Request object for Account Parameter operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountParameterRequest {
    private Long id;
    private Long accountId;
    private String name;
    private String value;
    private String type;
    private String description;
}
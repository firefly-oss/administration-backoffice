package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

/**
 * Request object for Account Space Provider operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountSpaceProviderRequest {
    private Long id;
    private Long spaceId;
    private String code;
    private String name;
    private String description;
    private String status;
}
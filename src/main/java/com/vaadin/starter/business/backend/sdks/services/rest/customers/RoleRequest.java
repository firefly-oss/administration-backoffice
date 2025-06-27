package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

/**
 * Request object for RolePermission operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoleRequest {
    private String roleCode;

    private String roleName;

    private String roleDescription;

    private Boolean isSystemRole;
}
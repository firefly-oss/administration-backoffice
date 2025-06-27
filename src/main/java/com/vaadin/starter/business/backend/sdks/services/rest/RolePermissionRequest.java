package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

/**
 * Request object for RolePermission operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RolePermissionRequest {
    private Long id;
    private String roleName;
    private String permissionName;
    private String description;

}
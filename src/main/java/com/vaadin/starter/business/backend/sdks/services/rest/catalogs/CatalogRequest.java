package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Catalog operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatalogRequest {
    private String name;
    private String code;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
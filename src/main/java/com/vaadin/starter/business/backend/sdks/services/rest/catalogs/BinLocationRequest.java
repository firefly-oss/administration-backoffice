package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for BinLocation operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BinLocationRequest {
    private String locationCode;
    private String name;
    private String description;
    private Long warehouseId;
    private String status;
    private Integer capacity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
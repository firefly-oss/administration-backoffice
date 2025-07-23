package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Warehouse operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarehouseRequest {
    private String code;
    private String name;
    private String description;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Serial operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SerialRequest {
    private String serialNumber;
    private Long itemVariantId;
    private Long warehouseId;
    private Long binLocationId;
    private String status;
    private LocalDateTime manufactureDate;
    private LocalDateTime expirationDate;
    private String batchNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
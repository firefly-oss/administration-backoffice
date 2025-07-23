package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for MovementLog operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MovementLogRequest {
    private Long itemVariantId;
    private Long sourceWarehouseId;
    private Long sourceBinLocationId;
    private Long destinationWarehouseId;
    private Long destinationBinLocationId;
    private Integer quantity;
    private String movementType;
    private String reason;
    private String referenceNumber;
    private LocalDateTime movementDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
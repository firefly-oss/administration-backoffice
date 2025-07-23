package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Stock operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StockRequest {
    private Long itemVariantId;
    private Long warehouseId;
    private Long binLocationId;
    private Integer quantity;
    private Integer reservedQuantity;
    private Integer availableQuantity;
    private String status;
    private LocalDateTime lastCountDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
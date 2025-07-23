package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Transfer operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransferRequest {
    private String transferNumber;
    private Long sourceWarehouseId;
    private Long destinationWarehouseId;
    private String status;
    private String transferType;
    private String notes;
    private LocalDateTime transferDate;
    private LocalDateTime completionDate;
    private String referenceNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
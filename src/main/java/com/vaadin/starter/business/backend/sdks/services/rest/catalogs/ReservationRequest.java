package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Reservation operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationRequest {
    private Long itemVariantId;
    private Long warehouseId;
    private Long binLocationId;
    private Integer quantity;
    private String reservationType;
    private String referenceNumber;
    private LocalDateTime reservationDate;
    private LocalDateTime expirationDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
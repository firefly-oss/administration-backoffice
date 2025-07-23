package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for Batch operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BatchRequest {
    private String batchNumber;
    private Long itemVariantId;
    private Integer quantity;
    private LocalDate expirationDate;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Request object for ItemVariant operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemVariantRequest {
    private String sku;
    private String name;
    private String description;
    private Long catalogId;
    private String status;
    private BigDecimal price;
    private BigDecimal cost;
    private Long uomId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
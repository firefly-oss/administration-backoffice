package com.vaadin.starter.business.backend.sdks.services.rest.catalogs;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Request object for Unit of Measure operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UomRequest {
    private String code;
    private String name;
    private String description;
    private String uomType;
    private BigDecimal conversionFactor;
    private Long baseUomId;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
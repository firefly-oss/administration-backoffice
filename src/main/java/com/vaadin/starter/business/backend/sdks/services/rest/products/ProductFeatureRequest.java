package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;

/**
 * Request object for ProductFeature operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFeatureRequest {
    private String name;
    private String description;
    private String featureType;
    private String value;
    private Boolean active;
    private Integer displayOrder;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Long productId;
}
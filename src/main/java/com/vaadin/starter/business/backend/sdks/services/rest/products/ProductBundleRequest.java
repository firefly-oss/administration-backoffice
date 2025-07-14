package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for ProductBundle operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductBundleRequest {
    private String name;
    private String description;
    private String code;
    private Boolean active;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double discountPercentage;
    private String bundleType;
    private Long categoryId;
}
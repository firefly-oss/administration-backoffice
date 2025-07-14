package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.math.BigDecimal;

/**
 * Request object for ProductBundleItem operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductBundleItemRequest {
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
    private Boolean required;
    private String itemType;
    private Integer displayOrder;
    private String description;
}
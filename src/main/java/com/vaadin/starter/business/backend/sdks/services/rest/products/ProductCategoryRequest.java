package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

/**
 * Request object for ProductCategory operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategoryRequest {
    private String name;
    private String description;
    private String code;
    private Boolean active;
    private Integer displayOrder;
    private String categoryType;
    private Long parentCategoryId;
}
package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request object for product category operations.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategoryRequest {

    /**
     * The name of the product category.
     */
    private String name;

    /**
     * The code of the product category.
     */
    private String code;

    /**
     * The description of the product category.
     */
    private String description;

    /**
     * Whether the product category is active.
     */
    private Boolean active;

    /**
     * The parent category ID.
     */
    private Long parentCategoryId;
}
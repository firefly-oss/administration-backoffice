package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

/**
 * Request object for ProductCategorySubtype operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductCategorySubtypeRequest {
    private String name;
    private String description;
    private String code;
    private Boolean active;
    private Integer displayOrder;
    private String subtypeAttributes;
    private String validationRules;
}
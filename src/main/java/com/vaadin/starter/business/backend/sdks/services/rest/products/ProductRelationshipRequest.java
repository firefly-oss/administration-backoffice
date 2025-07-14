package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;

/**
 * Request object for ProductRelationship operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRelationshipRequest {
    private String relationshipType;
    private Long relatedProductId;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
    private Integer priority;
}
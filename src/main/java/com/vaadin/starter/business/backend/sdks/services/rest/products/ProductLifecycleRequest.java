package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;

/**
 * Request object for ProductLifecycle operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductLifecycleRequest {
    private String name;
    private String description;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
    private String phase;
    private Integer sequenceOrder;
    private Boolean active;
    private Long productId;
}
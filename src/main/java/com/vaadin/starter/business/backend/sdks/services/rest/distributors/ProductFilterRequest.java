package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering Products.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductFilterRequest {
    private String name;
    private String code;
    private Long categoryId;
    private String status;
    private Double minPrice;
    private Double maxPrice;
    private String currency;
    private Boolean isActive;
    private LocalDateTime createdAtFrom;
    private LocalDateTime createdAtTo;
    private LocalDateTime updatedAtFrom;
    private LocalDateTime updatedAtTo;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortBy;
    private String sortDirection;
}
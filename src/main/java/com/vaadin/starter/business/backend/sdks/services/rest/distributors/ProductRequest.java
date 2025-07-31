package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Product operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private Long id;
    private Long distributorId;
    private Long categoryId;
    private String name;
    private String code;
    private String description;
    private String status;
    private Double price;
    private String currency;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
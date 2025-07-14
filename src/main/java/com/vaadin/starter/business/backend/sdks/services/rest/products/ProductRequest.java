package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for Contract operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRequest {
    private LocalDateTime dateUpdated;
    private Long productSubtypeId;
    private Long productTypeId;
    private String productName;
    private String productCode;
    private String productDescription;
    private Long productStatusId;
    private LocalDate launchDate;
    private LocalDate endDate;

}
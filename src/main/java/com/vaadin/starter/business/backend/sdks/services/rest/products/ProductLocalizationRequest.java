package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;

/**
 * Request object for ProductLocalization operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductLocalizationRequest {
    private String language;
    private String country;
    private String name;
    private String description;
    private String content;
    private Boolean active;
    private LocalDate effectiveDate;
    private LocalDate expirationDate;
    private Long productId;
}
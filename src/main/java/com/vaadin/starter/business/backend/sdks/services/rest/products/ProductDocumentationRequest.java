package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;

/**
 * Request object for ProductDocumentation operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDocumentationRequest {
    private String title;
    private String content;
    private String documentType;
    private String format;
    private String language;
    private String version;
    private LocalDate publicationDate;
    private LocalDate lastUpdated;
    private Boolean active;
    private String url;
    private Long productId;
}
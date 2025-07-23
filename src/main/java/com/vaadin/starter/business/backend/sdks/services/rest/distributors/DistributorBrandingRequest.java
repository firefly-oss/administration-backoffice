package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Distributor Branding operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DistributorBrandingRequest {
    private String logoUrl;
    private String primaryColor;
    private String secondaryColor;
    private String tertiaryColor;
    private String fontFamily;
    private String customCss;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
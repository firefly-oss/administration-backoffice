package com.vaadin.starter.business.backend.sdks.services.rest.products;

import lombok.*;

import java.time.LocalDate;

/**
 * Request object for ProductVersion operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductVersionRequest {
    private String versionNumber;
    private String name;
    private String description;
    private LocalDate releaseDate;
    private LocalDate endOfSupportDate;
    private String status;
    private Boolean active;
    private String changeLog;
}
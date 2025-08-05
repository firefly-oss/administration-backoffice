package com.vaadin.starter.business.backend.sdks.services.rest.masterdata;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Country operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryRequest {
    private String countryName;
    private String region;
    private String isoCode;
    private String svgFlag;
    private LocalDateTime dateUpdated;
    private LocalDateTime dateCreated;
    private Boolean active;
}
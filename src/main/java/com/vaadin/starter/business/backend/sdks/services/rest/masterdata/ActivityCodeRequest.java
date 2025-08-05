package com.vaadin.starter.business.backend.sdks.services.rest.masterdata;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Activity Code operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActivityCodeRequest {
    private String code;
    private String description;
    private Long countryId;
    private Long parentCodeId;
    private LocalDateTime dateUpdated;
    private Boolean active;
}
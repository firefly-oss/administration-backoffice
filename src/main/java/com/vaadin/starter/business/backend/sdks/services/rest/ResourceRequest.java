package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

/**
 * Request object for Resource operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResourceRequest {
    private Long id;
    private String name;
    private String type;
    private String url;
    private Boolean active;
    private Long partyId;
}
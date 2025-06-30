package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Acquirer operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardAcquirerRequest {
    private Long id;
    private String name;
    private String code;
    private String type;
    private String status;
    private String contactInfo;
    private String address;
    private Boolean isActive;
    private String description;
}
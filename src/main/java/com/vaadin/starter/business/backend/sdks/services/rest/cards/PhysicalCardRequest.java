package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Physical Card operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PhysicalCardRequest {
    private Long id;
    private String cardNumber;
    private String expirationDate;
    private String cvv;
    private String status;
    private String activationStatus;
    private String deliveryStatus;
    private String deliveryAddress;
    private String designTemplate;
    private String embossedName;
    private Boolean isActive;
    private String description;
}
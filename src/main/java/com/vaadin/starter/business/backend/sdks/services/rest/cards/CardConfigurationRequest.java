package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Configuration operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardConfigurationRequest {
    private Long id;
    private Long cardId;
    private String configType;
    private String configKey;
    private String configValue;
    private Boolean isActive;
    private String status;
    private String description;
    private String lastUpdated;
    private String createdBy;
    private String updatedBy;
}
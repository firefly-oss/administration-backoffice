package com.vaadin.starter.business.ui.views.masterdata.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for message type catalog information.
 * Used for transferring message type data between different layers of the application.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageTypeCatalogDTO {

    private Long typeId;
    private String typeCode;
    private String typeName;
    private String description;
}
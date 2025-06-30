package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Terminal operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardTerminalRequest {
    private Long id;
    private String terminalReference;
    private String serialNumber;
    private String terminalType;
    private String model;
    private String manufacturer;
    private Long merchantId;
    private String location;
    private String address;
    private Boolean supportsContactless;
    private Boolean supportsChip;
    private Boolean supportsMagneticStripe;
    private String processorName;
    private String processorId;
    private String softwareVersion;
    private String firmwareVersion;
    private String encryptionType;
    private String communicationType;
    private String connectionDetails;
    private String installationDate;
    private String lastMaintenanceDate;
    private String nextMaintenanceDate;
    private String status;
    private Boolean isActive;
    private String notes;
}
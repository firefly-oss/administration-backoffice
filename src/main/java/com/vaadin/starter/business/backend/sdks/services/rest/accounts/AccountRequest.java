package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for Account operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRequest {
    private Long id;
    private String accountNumber;
    private String accountType;
    private String currency;
    private Double balance;
    private String accountStatus;
    private Long customerId;
    private String description;
    private Long contractId;
    private Long branchId;
    private String accountSubType;
    private String taxReportingStatus;
    private String regulatoryStatus;
    private LocalDate openDate;
    private LocalDateTime dateCreated;
}

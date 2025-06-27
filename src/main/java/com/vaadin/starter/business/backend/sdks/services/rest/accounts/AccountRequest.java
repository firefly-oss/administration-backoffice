package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

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
    private String status;
    private Long customerId;
    private String description;
}
package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

/**
 * Request object for Account Balance operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountBalanceRequest {
    private Long id;
    private Long accountId;
    private String balanceType;
    private Double amount;
    private String currency;
    private String status;
    private String description;
}
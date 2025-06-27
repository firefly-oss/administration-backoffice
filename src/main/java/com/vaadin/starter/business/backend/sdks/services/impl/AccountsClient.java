package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.core.banking.accounts.sdk.api.AccountsApi;
import com.catalis.core.banking.accounts.sdk.invoker.ApiClient;
import com.vaadin.starter.business.backend.sdks.services.AccountsService;
import org.springframework.stereotype.Service;


@Service
public class AccountsClient implements AccountsService {

    private final AccountsApi accountsApi;

    public AccountsClient(ApiClient apiClient) {
        this.accountsApi = new AccountsApi(apiClient);
    }






}

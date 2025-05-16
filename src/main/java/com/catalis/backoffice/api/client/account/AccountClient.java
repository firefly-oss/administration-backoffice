package com.catalis.backoffice.api.client.account;

import com.catalis.backoffice.api.dto.account.AccountDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Client for making HTTP requests to the Account microservice.
 * This class encapsulates the details of HTTP communication with the Account API.
 * It will be used by the real implementation of AccountService when the microservice is available.
 */
public class AccountClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    /**
     * Constructs a new AccountClient with the specified RestTemplate and base URL.
     *
     * @param restTemplate the RestTemplate to use for HTTP requests
     * @param baseUrl the base URL of the Account API
     */
    public AccountClient(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Retrieves an account by ID.
     *
     * @param id the account ID
     * @return the response entity containing the account
     */
    public ResponseEntity<AccountDTO> getAccountById(String id) {
        String url = baseUrl + "/api/accounts/" + id;
        return restTemplate.getForEntity(url, AccountDTO.class);
    }

    /**
     * Retrieves an account by account number.
     *
     * @param accountNumber the account number
     * @return the response entity containing the account
     */
    public ResponseEntity<AccountDTO> getAccountByNumber(String accountNumber) {
        String url = baseUrl + "/api/accounts/number/" + accountNumber;
        return restTemplate.getForEntity(url, AccountDTO.class);
    }

    /**
     * Retrieves all accounts for a customer.
     *
     * @param customerId the customer ID
     * @return the response entity containing the list of accounts
     */
    public ResponseEntity<List<AccountDTO>> getAccountsByCustomerId(String customerId) {
        String url = baseUrl + "/api/accounts/customer/" + customerId;
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDTO>>() {}
        );
    }

    /**
     * Retrieves all accounts.
     *
     * @return the response entity containing the list of accounts
     */
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        String url = baseUrl + "/api/accounts";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AccountDTO>>() {}
        );
    }

    /**
     * Creates a new account.
     *
     * @param account the account to create
     * @return the response entity containing the created account
     */
    public ResponseEntity<AccountDTO> createAccount(AccountDTO account) {
        String url = baseUrl + "/api/accounts";
        return restTemplate.postForEntity(url, account, AccountDTO.class);
    }

    /**
     * Updates an existing account.
     *
     * @param id the ID of the account to update
     * @param account the updated account data
     * @return the response entity containing the updated account
     */
    public ResponseEntity<AccountDTO> updateAccount(String id, AccountDTO account) {
        String url = baseUrl + "/api/accounts/" + id;
        return restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(account),
                AccountDTO.class
        );
    }

    /**
     * Updates the status of an account.
     *
     * @param id the ID of the account
     * @param status the new status
     * @return the response entity containing the updated account
     */
    public ResponseEntity<AccountDTO> updateAccountStatus(String id, String status) {
        String url = baseUrl + "/api/accounts/" + id + "/status";
        return restTemplate.exchange(
                url,
                HttpMethod.PATCH,
                new HttpEntity<>(Map.of("status", status)),
                AccountDTO.class
        );
    }

    /**
     * Deposits money into an account.
     *
     * @param id the ID of the account
     * @param amount the amount to deposit
     * @return the response entity containing the updated account
     */
    public ResponseEntity<AccountDTO> deposit(String id, BigDecimal amount) {
        String url = baseUrl + "/api/accounts/" + id + "/deposit";
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(Map.of("amount", amount)),
                AccountDTO.class
        );
    }

    /**
     * Withdraws money from an account.
     *
     * @param id the ID of the account
     * @param amount the amount to withdraw
     * @return the response entity containing the updated account
     */
    public ResponseEntity<AccountDTO> withdraw(String id, BigDecimal amount) {
        String url = baseUrl + "/api/accounts/" + id + "/withdraw";
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(Map.of("amount", amount)),
                AccountDTO.class
        );
    }

    /**
     * Transfers money between accounts.
     *
     * @param fromAccountId the ID of the source account
     * @param toAccountId the ID of the destination account
     * @param amount the amount to transfer
     * @return the response entity containing the updated source account
     */
    public ResponseEntity<AccountDTO> transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        String url = baseUrl + "/api/accounts/" + fromAccountId + "/transfer";
        
        Map<String, Object> requestBody = Map.of(
                "toAccountId", toAccountId,
                "amount", amount
        );
        
        return restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(requestBody),
                AccountDTO.class
        );
    }
}
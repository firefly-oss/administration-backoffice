package com.catalis.backoffice.api.service.account;

import com.catalis.backoffice.api.dto.account.AccountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Service interface for account operations.
 * This interface defines the operations that can be performed on accounts through external microservices.
 */
public interface AccountService {

    /**
     * Retrieves an account by its ID.
     *
     * @param id the account ID
     * @return an Optional containing the account if found, or empty if not found
     */
    Optional<AccountDTO> getAccountById(String id);

    /**
     * Retrieves an account by its account number.
     *
     * @param accountNumber the account number
     * @return an Optional containing the account if found, or empty if not found
     */
    Optional<AccountDTO> getAccountByNumber(String accountNumber);

    /**
     * Retrieves all accounts for a customer.
     *
     * @param customerId the customer ID
     * @return a list of accounts belonging to the customer
     */
    List<AccountDTO> getAccountsByCustomerId(String customerId);

    /**
     * Retrieves a paginated list of all accounts.
     *
     * @param pageable pagination information
     * @return a page of accounts
     */
    Page<AccountDTO> getAllAccounts(Pageable pageable);

    /**
     * Creates a new account.
     *
     * @param account the account to create
     * @return the created account with assigned ID
     */
    AccountDTO createAccount(AccountDTO account);

    /**
     * Updates an existing account.
     *
     * @param id the ID of the account to update
     * @param account the updated account data
     * @return the updated account
     * @throws IllegalArgumentException if the account is not found
     */
    AccountDTO updateAccount(String id, AccountDTO account);

    /**
     * Updates the status of an account.
     *
     * @param id the ID of the account
     * @param status the new status
     * @return the updated account
     * @throws IllegalArgumentException if the account is not found
     */
    AccountDTO updateAccountStatus(String id, String status);

    /**
     * Deposits money into an account.
     *
     * @param id the ID of the account
     * @param amount the amount to deposit
     * @return the updated account
     * @throws IllegalArgumentException if the account is not found or the amount is invalid
     */
    AccountDTO deposit(String id, BigDecimal amount);

    /**
     * Withdraws money from an account.
     *
     * @param id the ID of the account
     * @param amount the amount to withdraw
     * @return the updated account
     * @throws IllegalArgumentException if the account is not found, the amount is invalid, or there are insufficient funds
     */
    AccountDTO withdraw(String id, BigDecimal amount);

    /**
     * Transfers money between accounts.
     *
     * @param fromAccountId the ID of the source account
     * @param toAccountId the ID of the destination account
     * @param amount the amount to transfer
     * @return the updated source account
     * @throws IllegalArgumentException if either account is not found, the amount is invalid, or there are insufficient funds
     */
    AccountDTO transfer(String fromAccountId, String toAccountId, BigDecimal amount);
}
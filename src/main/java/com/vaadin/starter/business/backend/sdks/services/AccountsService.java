package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.core.banking.accounts.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.accounts.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;


public interface AccountsService {

    /**
     * Create a new account.
     *
     * @param accountRequest the account to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountDTO>> createAccount(AccountRequest accountRequest);

    /**
     * Delete an account by ID.
     *
     * @param id the ID of the account to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteAccount(Long id);

    /**
     * Get an account by ID.
     *
     * @param id the ID of the account to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountDTO>> getAccount(Long id);

    /**
     * Filter accounts based on the provided request parameters.
     *
     * @param accountFilterRequest the request containing filter criteria for accounts
     * @return a reactive Mono emitting a ResponseEntity containing a PaginationResponseAccountDTO with the filtered results
     */
    Mono<ResponseEntity<PaginationResponseAccountDTO>> filterAccounts(AccountFilterRequest accountFilterRequest);

    /**
     * Update an account.
     *
     * @param id the ID of the account to update
     * @param accountRequest the account to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountDTO>> updateAccount(Long id, AccountRequest accountRequest);

    /**
     * Create a new balance.
     *
     * @param accountId the ID of the account to create the balance for
     * @param accountBalanceRequest the balance to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountBalanceDTO>> createBalance(Long accountId, AccountBalanceRequest accountBalanceRequest);

    /**
     * Delete a balance by ID.
     *
     * @param accountId the ID of the account
     * @param id the ID of the balance to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteBalance(Long accountId, Long id);

    /**
     * Get a balance by ID.
     *
     * @param accountId the ID of the account
     * @param id the ID of the balance to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountBalanceDTO>> getBalance(Long accountId, Long id);

    /**
     * Update a balance.
     *
     * @param accountId the ID of the account
     * @param id the ID of the balance to update
     * @param accountBalanceRequest the balance to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountBalanceDTO>> updateBalance(Long accountId, Long id, AccountBalanceRequest accountBalanceRequest);

    /**
     * Create a new account notification.
     *
     * @param accountNotificationRequest the notification to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountNotificationDTO>> createAccountNotification(AccountNotificationRequest accountNotificationRequest);

    /**
     * Delete an account notification by ID.
     *
     * @param id the ID of the notification to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteAccountNotification(Long id);

    /**
     * Filter account notifications based on the provided request parameters.
     *
     * @param accountNotificationFilterRequest the request containing filter criteria for notifications
     * @return a reactive Mono emitting a ResponseEntity containing a PaginationResponseAccountDTO with the filtered results
     */
    Mono<ResponseEntity<PaginationResponse>> filterAccountNotifications(AccountNotificationFilterRequest accountNotificationFilterRequest);

    /**
     * Get account notifications by account ID.
     *
     * @param accountId the ID of the account
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountNotificationDTO>> getAccountNotificationsByAccountId(Long accountId);

    /**
     * Get account notifications by type.
     *
     * @param type the type of notifications to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountNotificationDTO>> getAccountNotificationsByType(Long id, String type);

    /**
     * Get active notifications.
     *
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountNotificationDTO>> getActiveNotifications(Long id);

    /**
     * Get unread account notifications.
     *
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountNotificationDTO>> getUnreadAccountNotifications(Long id);

    /**
     * Mark all notifications as read.
     *
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Long>> markAllNotificationsAsRead(Long id);

    /**
     * Update an account notification.
     *
     * @param id the ID of the notification to update
     * @param accountNotificationRequest the notification to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountNotificationDTO>> updateAccountNotification(Long id, AccountNotificationRequest accountNotificationRequest);

    /**
     * Create a new account parameter.
     *
     * @param accountId the ID of the account to create the parameter for
     * @param accountParameterRequest the parameter to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountParameterDTO>> createParameter(Long accountId, AccountParameterRequest accountParameterRequest);

    /**
     * Delete an account parameter by ID.
     *
     * @param accountId the ID of the account
     * @param id the ID of the parameter to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteParameter(Long accountId, Long id);

    /**
     * Get an account parameter by ID.
     *
     * @param accountId the ID of the account
     * @param id the ID of the parameter to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountParameterDTO>> getParameter(Long accountId, Long id);

    /**
     * Update an account parameter.
     *
     * @param accountId the ID of the account
     * @param id the ID of the parameter to update
     * @param accountParameterRequest the parameter to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountParameterDTO>> updateParameter(Long accountId, Long id, AccountParameterRequest accountParameterRequest);

    /**
     * Create a new account provider.
     *
     * @param accountId the ID of the account to create the provider for
     * @param accountProviderRequest the provider to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountProviderDTO>> createProvider(Long accountId, AccountProviderRequest accountProviderRequest);

    /**
     * Delete an account provider by ID.
     *
     * @param accountId the ID of the account
     * @param id the ID of the provider to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProvider(Long accountId, Long id);

    /**
     * Get an account provider by ID.
     *
     * @param accountId the ID of the account
     * @param id the ID of the provider to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountProviderDTO>> getProvider(Long accountId, Long id);

    /**
     * Update an account provider.
     *
     * @param accountId the ID of the account
     * @param id the ID of the provider to update
     * @param accountProviderRequest the provider to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountProviderDTO>> updateProvider(Long accountId, Long id, AccountProviderRequest accountProviderRequest);

    /**
     * Create a new account restriction.
     *
     * @param accountRestrictionRequest the restriction to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountRestrictionDTO>> createRestriction(AccountRestrictionRequest accountRestrictionRequest);

    /**
     * Delete an account restriction by ID.
     *
     * @param id the ID of the restriction to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteRestriction(Long id);

    /**
     * Get an account restriction by ID.
     *
     * @param id the ID of the restriction to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountRestrictionDTO>> getRestriction(Long id);

    /**
     * Update an account restriction.
     *
     * @param id the ID of the restriction to update
     * @param accountRestrictionRequest the restriction to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountRestrictionDTO>> updateRestriction(Long id, AccountRestrictionRequest accountRestrictionRequest);

    /**
     * Filter account restrictions based on the provided request parameters.
     *
     * @param accountRestrictionFilterRequest the request containing filter criteria for restrictions
     * @return a reactive Mono emitting a ResponseEntity containing a PaginationResponse with the filtered results
     */
    Mono<ResponseEntity<PaginationResponse>> filterAccountRestrictions(AccountRestrictionFilterRequest accountRestrictionFilterRequest);

    /**
     * Create a new provider for a space.
     *
     * @param spaceId the ID of the space to create the provider for
     * @param accountSpaceProviderRequest the provider to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountProviderDTO>> createProviderForSpace(Long accountId, Long spaceId, AccountSpaceProviderRequest accountSpaceProviderRequest);

    /**
     * Delete a provider for a space by ID.
     *
     * @param spaceId the ID of the space
     * @param id the ID of the provider to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProviderForSpace(Long accountId, Long spaceId, Long id);

    /**
     * Get a provider for a space by ID.
     *
     * @param spaceId the ID of the space
     * @param id the ID of the provider to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountProviderDTO>> getProviderForSpace(Long accountId, Long spaceId, Long id);

    /**
     * Update a provider for a space.
     *
     * @param spaceId the ID of the space
     * @param id the ID of the provider to update
     * @param accountSpaceProviderRequest the provider to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountProviderDTO>> updateProviderForSpace(Long accountId, Long spaceId, Long id, AccountSpaceProviderRequest accountSpaceProviderRequest);
    /**
     * Calculate the balance distribution for an account.
     *
     * @param accountId the ID of the account
     * @return a Mono with the response entity containing a map of space names to balances
     */
    Mono<ResponseEntity<Map<String, BigDecimal>>> calculateBalanceDistribution(Long accountId);

    /**
     * Calculate the goal progress for an account space.
     *
     * @param accountSpaceId the ID of the account space
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> calculateGoalProgress(Long accountSpaceId);

    /**
     * Calculate growth rates for an account.
     *
     * @param accountId the ID of the account
     * @param startDate the start date for the calculation
     * @param endDate the end date for the calculation
     * @return a Mono with the response entity containing a map of space names to growth rates
     */
    Mono<ResponseEntity<java.util.Map<String, java.math.BigDecimal>>> calculateGrowthRates(Long accountId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Configure automatic transfers for an account space.
     *
     * @param accountSpaceId the ID of the account space
     * @param enabled whether automatic transfers are enabled
     * @param frequency the frequency of transfers
     * @param amount the amount to transfer
     * @param sourceSpaceId the ID of the source space
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> configureAutomaticTransfers(Long accountSpaceId, Boolean enabled, String frequency, java.math.BigDecimal amount, Long sourceSpaceId);

    /**
     * Create a new account space.
     *
     * @param accountSpaceDTO the account space to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> createAccountSpace(AccountSpaceDTO accountSpaceDTO);

    /**
     * Delete an account space by ID.
     *
     * @param accountSpaceId the ID of the account space to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteAccountSpace(Long accountSpaceId);

    /**
     * Execute automatic transfers for an account.
     *
     * @param accountId the ID of the account
     * @return a Mono with the response entity containing the number of transfers executed
     */
    Mono<ResponseEntity<Integer>> executeAutomaticTransfers(Long accountId);

    /**
     * Freeze an account space.
     *
     * @param accountSpaceId the ID of the account space
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> freezeAccountSpace(Long accountSpaceId);

    /**
     * Get an account space by ID.
     *
     * @param accountSpaceId the ID of the account space
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> getAccountSpace(Long accountSpaceId);

    /**
     * Get account spaces by account ID.
     *
     * @param accountId the ID of the account
     * @return a Mono with the response entity containing a list of account spaces
     */
    Mono<ResponseEntity<java.util.List<AccountSpaceDTO>>> getAccountSpacesByAccountId(Long accountId);

    /**
     * Get account spaces by account ID with pagination.
     *
     * @param accountId the ID of the account
     * @param page the page number
     * @param size the page size
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponseAccountSpaceDTO>> getAccountSpacesByAccountIdPaginated(Long accountId, Integer page, Integer size);

    /**
     * Get space analytics.
     *
     * @param accountSpaceId the ID of the account space
     * @param startDate the start date
     * @param endDate the end date
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<SpaceAnalyticsDTO>> getSpaceAnalytics(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Get spaces by type.
     *
     * @param accountId the ID of the account
     * @param spaceType the type of spaces to get
     * @return a Mono with the response entity containing a list of account spaces
     */
    Mono<ResponseEntity<java.util.List<AccountSpaceDTO>>> getSpacesByType(Long accountId, String spaceType);

    /**
     * Get spaces with goals.
     *
     * @param accountId the ID of the account
     * @return a Mono with the response entity containing a list of account spaces with goals
     */
    Mono<ResponseEntity<java.util.List<AccountSpaceDTO>>> getSpacesWithGoals(Long accountId);

    /**
     * Get spaces with upcoming target dates.
     *
     * @param accountId the ID of the account
     * @param daysThreshold the threshold in days
     * @return a Mono with the response entity containing a list of account spaces with upcoming target dates
     */
    Mono<ResponseEntity<java.util.List<AccountSpaceDTO>>> getSpacesWithUpcomingTargetDates(Long accountId, Integer daysThreshold);

    /**
     * Simulate future balances.
     *
     * @param accountId the ID of the account
     * @param months the number of months to simulate
     * @return a Mono with the response entity containing a map of space names to future balances
     */
    Mono<ResponseEntity<java.util.Map<String, java.math.BigDecimal>>> simulateFutureBalances(Long accountId, Integer months);

    /**
     * Transfer between spaces.
     *
     * @param fromSpaceId the ID of the source space
     * @param toSpaceId the ID of the destination space
     * @param amount the amount to transfer
     * @return a Mono with the response entity indicating success or failure
     */
    Mono<ResponseEntity<Boolean>> transferBetweenSpaces(Long fromSpaceId, Long toSpaceId, java.math.BigDecimal amount);

    /**
     * Unfreeze an account space.
     *
     * @param accountSpaceId the ID of the account space
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> unfreezeAccountSpace(Long accountSpaceId);

    /**
     * Update an account space.
     *
     * @param accountSpaceId the ID of the account space
     * @param accountSpaceDTO the account space to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> updateAccountSpace(Long accountSpaceId, AccountSpaceDTO accountSpaceDTO);

    /**
     * Update an account space balance.
     *
     * @param accountSpaceId the ID of the account space
     * @param newBalance the new balance
     * @param reason the reason for the update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AccountSpaceDTO>> updateAccountSpaceBalance(Long accountSpaceId, java.math.BigDecimal newBalance, String reason);

    /**
     * Filter account spaces based on the provided request parameters.
     *
     * @param accountSpaceFilterRequest the request containing filter criteria for account spaces
     * @return a reactive Mono emitting a ResponseEntity containing a PaginationResponseAccountSpaceDTO with the filtered results
     */
    Mono<ResponseEntity<PaginationResponseAccountSpaceDTO>> filterAccountSpaces(AccountSpaceFilterRequest accountSpaceFilterRequest);
    /**
     * Calculate the total deposits for an account space within a specified date range.
     *
     * @param accountSpaceId Unique identifier of the account space
     * @param startDate Start date of the range (inclusive)
     * @param endDate End date of the range (inclusive)
     * @return a Mono with the response entity containing the total deposits
     */
    Mono<ResponseEntity<BigDecimal>> calculateTotalDeposits(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Calculate the total withdrawals for an account space within a specified date range.
     *
     * @param accountSpaceId Unique identifier of the account space
     * @param startDate Start date of the range (inclusive)
     * @param endDate End date of the range (inclusive)
     * @return a Mono with the response entity containing the total withdrawals
     */
    Mono<ResponseEntity<BigDecimal>> calculateTotalWithdrawals(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Get the balance of an account space at a specific point in time.
     *
     * @param accountSpaceId Unique identifier of the account space
     * @param dateTime Date and time to get the balance at
     * @return a Mono with the response entity containing the balance
     */
    Mono<ResponseEntity<BigDecimal>> getBalanceAtDateTime(Long accountSpaceId, LocalDateTime dateTime);

    /**
     * Get transactions for an account space.
     *
     * @param accountSpaceId Unique identifier of the account space
     * @param pageNumber The zero-based page number to retrieve
     * @param pageSize The number of items per page
     * @param sortBy The field to sort the results by
     * @param sortDirection The direction of sorting, either ASC or DESC
     * @return a Mono with the response entity containing the paginated transactions
     */
    Mono<ResponseEntity<PaginationResponse>> getTransactions(Long accountSpaceId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get transactions for an account space within a specific date range.
     *
     * @param accountSpaceId Unique identifier of the account space
     * @param startDate Start date of the range (inclusive)
     * @param endDate End date of the range (inclusive)
     * @param pageNumber The zero-based page number to retrieve
     * @param pageSize The number of items per page
     * @param sortBy The field to sort the results by
     * @param sortDirection The direction of sorting, either ASC or DESC
     * @return a Mono with the response entity containing the paginated transactions
     */
    Mono<ResponseEntity<PaginationResponse>> getTransactionsByDateRange(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Record a transaction for an account space.
     *
     * @param accountSpaceId Unique identifier of the account space
     * @param amount Transaction amount (positive for deposits, negative for withdrawals)
     * @param description Transaction description
     * @param referenceId Reference ID for cross-system tracking
     * @return a Mono with the response entity containing the created transaction
     */
    Mono<ResponseEntity<SpaceTransactionDTO>> recordTransaction(Long accountSpaceId, BigDecimal amount, String description, String referenceId);

    /**
     * Create a new status history record for an account.
     *
     * @param accountId Unique identifier of the account
     * @param accountStatusHistoryDTO The status history data to create
     * @return a Mono with the response entity containing the created status history
     */
    Mono<ResponseEntity<AccountStatusHistoryDTO>> createStatusHistory(Long accountId, AccountStatusHistoryDTO accountStatusHistoryDTO);

    /**
     * Delete a status history record.
     *
     * @param accountId Unique identifier of the account
     * @param historyId Unique identifier of the status history record to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteStatusHistory(Long accountId, Long historyId);

    /**
     * Get all status history records for an account.
     *
     * @param accountId Unique identifier of the account
     * @param pageNumber The zero-based page number to retrieve
     * @param pageSize The number of items per page
     * @param sortBy The field to sort the results by
     * @param sortDirection The direction of sorting, either ASC or DESC
     * @return a Mono with the response entity containing the paginated status history records
     */
    Mono<ResponseEntity<PaginationResponse>> getAllStatusHistory(Long accountId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a specific status history record.
     *
     * @param accountId Unique identifier of the account
     * @param historyId Unique identifier of the status history record
     * @return a Mono with the response entity containing the status history record
     */
    Mono<ResponseEntity<AccountStatusHistoryDTO>> getStatusHistory(Long accountId, Long historyId);

    /**
     * Update a status history record.
     *
     * @param accountId Unique identifier of the account
     * @param historyId Unique identifier of the status history record to update
     * @param accountStatusHistoryDTO The updated status history data
     * @return a Mono with the response entity containing the updated status history record
     */
    Mono<ResponseEntity<AccountStatusHistoryDTO>> updateStatusHistory(Long accountId, Long historyId, AccountStatusHistoryDTO accountStatusHistoryDTO);
}

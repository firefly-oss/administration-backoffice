package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.core.banking.accounts.sdk.api.*;
import com.catalis.core.banking.accounts.sdk.invoker.ApiClient;
import com.catalis.core.banking.accounts.sdk.model.*;
import com.vaadin.starter.business.backend.mapper.accounts.AccountsMapper;
import com.vaadin.starter.business.backend.sdks.services.AccountsService;
import com.vaadin.starter.business.backend.sdks.services.rest.accounts.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class AccountsClient implements AccountsService {

    private final AccountsApi accountsApi;
    private final AccountsMapper accountsMapper;
    private final AccountBalancesApi accountBalancesApi;
    private final AccountNotificationsApi accountNotificationsApi;
    private final AccountParametersApi accountParametersApi;
    private final AccountProvidersApi accountProvidersApi;
    private final AccountRestrictionsApi accountRestrictionsApi;
    private final AccountSpaceProvidersApi accountSpaceProvidersApi;
    private final AccountSpacesApi accountSpacesApi;
    private final AccountSpaceTransactionsApi accountSpaceTransactionsApi;
    private final AccountStatusHistoryApi accountStatusHistoryApi;

    @Autowired
    public AccountsClient(ApiClient apiClient, AccountsMapper accountsMapper) {
        this.accountsApi = new AccountsApi(apiClient);
        this.accountBalancesApi = new AccountBalancesApi(apiClient);
        this.accountNotificationsApi = new AccountNotificationsApi(apiClient);
        this.accountParametersApi = new AccountParametersApi(apiClient);
        this.accountProvidersApi = new AccountProvidersApi(apiClient);
        this.accountRestrictionsApi = new AccountRestrictionsApi(apiClient);
        this.accountSpaceProvidersApi = new AccountSpaceProvidersApi(apiClient);
        this.accountSpacesApi = new AccountSpacesApi(apiClient);
        this.accountSpaceTransactionsApi = new AccountSpaceTransactionsApi(apiClient);
        this.accountStatusHistoryApi = new AccountStatusHistoryApi(apiClient);
        this.accountsMapper = accountsMapper;
    }

    @Override
    public Mono<ResponseEntity<AccountDTO>> createAccount(AccountRequest accountRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountsApi.createAccountWithHttpInfo(accountsMapper.accountRequestToDto(accountRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteAccount(Long id) {
        return accountsApi.deleteAccountWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<AccountDTO>> getAccount(Long id) {
        return accountsApi.getAccountWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponseAccountDTO>> filterAccounts(AccountFilterRequest accountFilterRequest) {
        return accountsApi.filterAccountsWithHttpInfo(accountsMapper.accountFilterRequestToDto(accountFilterRequest));
    }

    @Override
    public Mono<ResponseEntity<AccountDTO>> updateAccount(Long id, AccountRequest accountRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountsApi.updateAccountWithHttpInfo(id, accountsMapper.accountRequestToDto(accountRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountBalanceDTO>> createBalance(Long accountId, AccountBalanceRequest accountBalanceRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountBalancesApi.createBalanceWithHttpInfo(accountId, accountsMapper.accountBalanceRequestToDto(accountBalanceRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBalance(Long accountId, Long id) {
        return accountBalancesApi.deleteBalanceWithHttpInfo(accountId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountBalanceDTO>> getBalance(Long accountId, Long id) {
        return accountBalancesApi.getBalanceWithHttpInfo(accountId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountBalanceDTO>> updateBalance(Long accountId, Long id, AccountBalanceRequest accountBalanceRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountBalancesApi.updateBalanceWithHttpInfo(accountId, id, accountsMapper.accountBalanceRequestToDto(accountBalanceRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountNotificationDTO>> createAccountNotification(AccountNotificationRequest accountNotificationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountNotificationsApi.createAccountNotificationWithHttpInfo(accountsMapper.accountNotificationRequestToDto(accountNotificationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteAccountNotification(Long id) {
        return accountNotificationsApi.deleteAccountNotificationWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterAccountNotifications(AccountNotificationFilterRequest accountNotificationFilterRequest) {
        return accountNotificationsApi.filterAccountNotificationsWithHttpInfo
                (accountsMapper.accountNotificationFilterRequestToDto(accountNotificationFilterRequest));
    }

    @Override
    public Mono<ResponseEntity<AccountNotificationDTO>> getAccountNotificationsByAccountId(Long accountId) {
        return accountNotificationsApi.getAccountNotificationsByAccountIdWithHttpInfo(accountId);
    }

    @Override
    public Mono<ResponseEntity<AccountNotificationDTO>> getAccountNotificationsByType(Long id, String type) {
        return accountNotificationsApi.getAccountNotificationsByTypeWithHttpInfo(id, type);
    }

    @Override
    public Mono<ResponseEntity<AccountNotificationDTO>> getActiveNotifications(Long id) {
        return accountNotificationsApi.getActiveNotificationsWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<AccountNotificationDTO>> getUnreadAccountNotifications(Long id) {
        return accountNotificationsApi.getUnreadAccountNotificationsWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<Long>> markAllNotificationsAsRead(Long id) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountNotificationsApi.markAllNotificationsAsReadWithHttpInfo(id, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountNotificationDTO>> updateAccountNotification(Long id, AccountNotificationRequest accountNotificationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountNotificationsApi.updateAccountNotificationWithHttpInfo(id, 
                accountsMapper.accountNotificationRequestToDto(accountNotificationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountParameterDTO>> createParameter(Long accountId, AccountParameterRequest accountParameterRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountParametersApi.createParameterWithHttpInfo(accountId, accountsMapper.accountParameterRequestToDto(accountParameterRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteParameter(Long accountId, Long id) {
        return accountParametersApi.deleteParameterWithHttpInfo(accountId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountParameterDTO>> getParameter(Long accountId, Long id) {
        return accountParametersApi.getParameterWithHttpInfo(accountId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountParameterDTO>> updateParameter(Long accountId, Long id, AccountParameterRequest accountParameterRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountParametersApi.updateParameterWithHttpInfo(accountId, id, accountsMapper.accountParameterRequestToDto(accountParameterRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountProviderDTO>> createProvider(Long accountId, AccountProviderRequest accountProviderRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountProvidersApi.createProviderWithHttpInfo(accountId, accountsMapper.accountProviderRequestToDto(accountProviderRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProvider(Long accountId, Long id) {
        return accountProvidersApi.deleteProviderWithHttpInfo(accountId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountProviderDTO>> getProvider(Long accountId, Long id) {
        return accountProvidersApi.getProviderWithHttpInfo(accountId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountProviderDTO>> updateProvider(Long accountId, Long id, AccountProviderRequest accountProviderRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountProvidersApi.updateProviderWithHttpInfo(accountId, id, accountsMapper.accountProviderRequestToDto(accountProviderRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountRestrictionDTO>> createRestriction(AccountRestrictionRequest accountRestrictionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountRestrictionsApi.createAccountRestrictionWithHttpInfo(
                accountsMapper.accountRestrictionRequestToDto(accountRestrictionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteRestriction(Long id) {
        return accountRestrictionsApi.deleteAccountRestrictionWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<AccountRestrictionDTO>> getRestriction(Long id) {
        return accountRestrictionsApi.getAccountRestrictionsByAccountIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<AccountRestrictionDTO>> updateRestriction(Long id, AccountRestrictionRequest accountRestrictionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountRestrictionsApi.updateAccountRestrictionWithHttpInfo(id, accountsMapper.accountRestrictionRequestToDto(accountRestrictionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterAccountRestrictions(AccountRestrictionFilterRequest accountRestrictionFilterRequest) {
        return accountRestrictionsApi.filterAccountRestrictionsWithHttpInfo(accountsMapper.accountRestrictionFilterRequestToDto(accountRestrictionFilterRequest));
    }

    @Override
    public Mono<ResponseEntity<AccountProviderDTO>> createProviderForSpace(Long accountId, Long spaceId, AccountSpaceProviderRequest accountSpaceProviderRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpaceProvidersApi.createProviderForSpaceWithHttpInfo(accountId, spaceId, accountsMapper.accountSpaceProviderRequestToDto(accountSpaceProviderRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProviderForSpace(Long accountId, Long spaceId, Long id) {
        return accountSpaceProvidersApi.deleteProviderForSpaceWithHttpInfo(accountId, spaceId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountProviderDTO>> getProviderForSpace(Long accountId, Long spaceId, Long id) {
        return accountSpaceProvidersApi.getProviderForSpaceWithHttpInfo(accountId, spaceId, id);
    }

    @Override
    public Mono<ResponseEntity<AccountProviderDTO>> updateProviderForSpace(Long accountId, Long spaceId, Long id, AccountSpaceProviderRequest accountSpaceProviderRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpaceProvidersApi.updateProviderForSpaceWithHttpInfo(accountId, spaceId, id, accountsMapper.accountSpaceProviderRequestToDto(accountSpaceProviderRequest), xIdempotencyKey);
    }
    @Override
    public Mono<ResponseEntity<Map<String, java.math.BigDecimal>>> calculateBalanceDistribution(Long accountId) {
        return accountSpacesApi.calculateBalanceDistributionWithHttpInfo(accountId);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> calculateGoalProgress(Long accountSpaceId) {
        return accountSpacesApi.calculateGoalProgressWithHttpInfo(accountSpaceId);
    }

    @Override
    public Mono<ResponseEntity<Map<String, BigDecimal>>> calculateGrowthRates(Long accountId, LocalDateTime startDate, LocalDateTime endDate) {
        return accountSpacesApi.calculateGrowthRatesWithHttpInfo(accountId, startDate, endDate);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> configureAutomaticTransfers(Long accountSpaceId,
                                                        Boolean enabled, String frequency, BigDecimal amount, Long sourceSpaceId) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.configureAutomaticTransfersWithHttpInfo(accountSpaceId, enabled, frequency, amount, sourceSpaceId, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> createAccountSpace(AccountSpaceDTO accountSpaceDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.createAccountSpaceWithHttpInfo(accountSpaceDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteAccountSpace(Long accountSpaceId) {
        return accountSpacesApi.deleteAccountSpaceWithHttpInfo(accountSpaceId);
    }

    @Override
    public Mono<ResponseEntity<Integer>> executeAutomaticTransfers(Long accountId) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.executeAutomaticTransfersWithHttpInfo(accountId, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> freezeAccountSpace(Long accountSpaceId) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.freezeAccountSpaceWithHttpInfo(accountSpaceId, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> getAccountSpace(Long accountSpaceId) {
        return accountSpacesApi.getAccountSpaceWithHttpInfo(accountSpaceId);
    }

    @Override
    public Mono<ResponseEntity<List<AccountSpaceDTO>>> getAccountSpacesByAccountId(Long accountId) {
        return accountSpacesApi.getAccountSpacesByAccountIdWithHttpInfo(accountId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponseAccountSpaceDTO>> getAccountSpacesByAccountIdPaginated(Long accountId, Integer page, Integer size) {
        return accountSpacesApi.getAccountSpacesByAccountIdPaginatedWithHttpInfo(accountId, page, size);
    }

    @Override
    public Mono<ResponseEntity<SpaceAnalyticsDTO>> getSpaceAnalytics(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate) {
        return accountSpacesApi.getSpaceAnalyticsWithHttpInfo(accountSpaceId, startDate, endDate);
    }

    @Override
    public Mono<ResponseEntity<List<AccountSpaceDTO>>> getSpacesByType(Long accountId, String spaceType) {
        return accountSpacesApi.getSpacesByTypeWithHttpInfo(accountId, spaceType);
    }

    @Override
    public Mono<ResponseEntity<List<AccountSpaceDTO>>> getSpacesWithGoals(Long accountId) {
        return accountSpacesApi.getSpacesWithGoalsWithHttpInfo(accountId);
    }

    @Override
    public Mono<ResponseEntity<List<AccountSpaceDTO>>> getSpacesWithUpcomingTargetDates(Long accountId, Integer daysThreshold) {
        return accountSpacesApi.getSpacesWithUpcomingTargetDatesWithHttpInfo(accountId, daysThreshold);
    }

    @Override
    public Mono<ResponseEntity<Map<String, BigDecimal>>> simulateFutureBalances(Long accountId, Integer months) {
        return accountSpacesApi.simulateFutureBalancesWithHttpInfo(accountId, months);
    }

    @Override
    public Mono<ResponseEntity<Boolean>> transferBetweenSpaces(Long fromSpaceId, Long toSpaceId, BigDecimal amount) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.transferBetweenSpacesWithHttpInfo(fromSpaceId, toSpaceId, amount, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> unfreezeAccountSpace(Long accountSpaceId) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.unfreezeAccountSpaceWithHttpInfo(accountSpaceId, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> updateAccountSpace(Long accountSpaceId, AccountSpaceDTO accountSpaceDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.updateAccountSpaceWithHttpInfo(accountSpaceId, accountSpaceDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountSpaceDTO>> updateAccountSpaceBalance(Long accountSpaceId, BigDecimal newBalance, String reason) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpacesApi.updateAccountSpaceBalanceWithHttpInfo(accountSpaceId, newBalance, reason, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponseAccountSpaceDTO>> filterAccountSpaces(AccountSpaceFilterRequest accountSpaceFilterRequest) {
        return accountSpacesApi.filterAccountSpacesWithHttpInfo(accountsMapper.accountSpaceFilterRequestToDto(accountSpaceFilterRequest));
    }

    @Override
    public Mono<ResponseEntity<BigDecimal>> calculateTotalDeposits(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate) {
        return accountSpaceTransactionsApi.calculateTotalDepositsWithHttpInfo(accountSpaceId, startDate, endDate);
    }

    @Override
    public Mono<ResponseEntity<BigDecimal>> calculateTotalWithdrawals(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate) {
        return accountSpaceTransactionsApi.calculateTotalWithdrawalsWithHttpInfo(accountSpaceId, startDate, endDate);
    }

    @Override
    public Mono<ResponseEntity<BigDecimal>> getBalanceAtDateTime(Long accountSpaceId, LocalDateTime dateTime) {
        return accountSpaceTransactionsApi.getBalanceAtDateTimeWithHttpInfo(accountSpaceId, dateTime);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getTransactions(Long accountSpaceId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return accountSpaceTransactionsApi.getTransactionsWithHttpInfo(accountSpaceId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getTransactionsByDateRange(Long accountSpaceId, LocalDateTime startDate, LocalDateTime endDate, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return accountSpaceTransactionsApi.getTransactionsByDateRangeWithHttpInfo(accountSpaceId, startDate, endDate, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<SpaceTransactionDTO>> recordTransaction(Long accountSpaceId, BigDecimal amount, String description, String referenceId) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountSpaceTransactionsApi.recordTransactionWithHttpInfo(accountSpaceId, amount, description, referenceId, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<AccountStatusHistoryDTO>> createStatusHistory(Long accountId, AccountStatusHistoryDTO accountStatusHistoryDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountStatusHistoryApi.createStatusHistoryWithHttpInfo(accountId, accountStatusHistoryDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteStatusHistory(Long accountId, Long historyId) {
        return accountStatusHistoryApi.deleteStatusHistoryWithHttpInfo(accountId, historyId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllStatusHistory(Long accountId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return accountStatusHistoryApi.getAllStatusHistoryWithHttpInfo(accountId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<AccountStatusHistoryDTO>> getStatusHistory(Long accountId, Long historyId) {
        return accountStatusHistoryApi.getStatusHistoryWithHttpInfo(accountId, historyId);
    }

    @Override
    public Mono<ResponseEntity<AccountStatusHistoryDTO>> updateStatusHistory(Long accountId, Long historyId, AccountStatusHistoryDTO accountStatusHistoryDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return accountStatusHistoryApi.updateStatusHistoryWithHttpInfo(accountId, historyId, accountStatusHistoryDTO, xIdempotencyKey);
    }
}

package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.core.banking.cards.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.cards.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface CardsService {

    /**
     * Create a new BIN
     *
     * @param binRequest the BIN data to create
     * @return the created BIN
     */
    Mono<ResponseEntity<BINDTO>> createBIN(BINRequest binRequest);

    /**
     * Delete a BIN
     *
     * @param binId the ID of the BIN to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteBIN(Long binId);

    /**
     * Get all BINs
     *
     * @param paginationRequest pagination parameters
     * @param number BIN number filter (optional)
     * @return list of BINs
     */
    Mono<ResponseEntity<GetAllBINs1200Response>> getAllBINs(PaginationRequest paginationRequest, String number);

    /**
     * Get a BIN by ID
     *
     * @param binId the ID of the BIN to retrieve
     * @return the BIN data
     */
    Mono<ResponseEntity<BINDTO>> getBIN(Long binId);

    /**
     * Update a BIN
     *
     * @param binId the ID of the BIN to update
     * @param binRequest the updated BIN data
     * @return the updated BIN
     */
    Mono<ResponseEntity<BINDTO>> updateBIN(Long binId, BINRequest binRequest);

    /**
     * Create a new Card
     *
     * @param cardRequest the Card data to create
     * @return the created Card
     */
    Mono<ResponseEntity<CardDTO>> createCard(CardRequest cardRequest);

    /**
     * Filter cards based on the provided request parameters.
     *
     * @param cardFilterRequest the request containing filter criteria for cards
     * @return a reactive Mono emitting a ResponseEntity containing a PaginationResponseCardDTO with the filtered results
     */
    Mono<ResponseEntity<PaginationResponseCardDTO>> filterCards(CardFilterRequest cardFilterRequest);

    /**
     * Delete a Card
     *
     * @param cardId the ID of the Card to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteCard(Long cardId);

    /**
     * Get a Card by ID
     *
     * @param cardId the ID of the Card to retrieve
     * @return the Card data
     */
    Mono<ResponseEntity<CardDTO>> getCard(Long cardId);

    /**
     * Update a Card
     *
     * @param cardId the ID of the Card to update
     * @param cardRequest the updated Card data
     * @return the updated Card
     */
    Mono<ResponseEntity<CardDTO>> updateCard(Long cardId, CardRequest cardRequest);

    /**
     * Create a new Card Acquirer
     *
     * @param cardAcquirerRequest the Card Acquirer data to create
     * @return the created Card Acquirer
     */
    Mono<ResponseEntity<CardAcquirerDTO>> createAcquirer(CardAcquirerRequest cardAcquirerRequest);

    /**
     * Delete a Card Acquirer
     *
     * @param acquirerId the ID of the Card Acquirer to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteAcquirer(Long acquirerId);

    /**
     * Get a Card Acquirer by ID
     *
     * @param acquirerId the ID of the Card Acquirer to retrieve
     * @return the Card Acquirer data
     */
    Mono<ResponseEntity<CardAcquirerDTO>> getAcquirer(Long acquirerId);

    /**
     * Get all Card Acquirers
     *
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Acquirers
     */
    Mono<ResponseEntity<PaginationResponse>> getAllAcquirers(String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Acquirer
     *
     * @param acquirerId the ID of the Card Acquirer to update
     * @param cardAcquirerRequest the updated Card Acquirer data
     * @return the updated Card Acquirer
     */
    Mono<ResponseEntity<CardAcquirerDTO>> updateAcquirer(Long acquirerId, CardAcquirerRequest cardAcquirerRequest);

    /**
     * Create a new Card Activity
     *
     * @param cardId the ID of the Card
     * @param cardActivityRequest the Card Activity data to create
     * @return the created Card Activity
     */
    Mono<ResponseEntity<CardActivityDTO>> createActivity(Long cardId, CardActivityRequest cardActivityRequest);

    /**
     * Delete a Card Activity
     *
     * @param cardId the ID of the Card
     * @param activityId the ID of the Card Activity to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteActivity(Long cardId, Long activityId);

    /**
     * Get a Card Activity by ID
     *
     * @param cardId the ID of the Card
     * @param activityId the ID of the Card Activity to retrieve
     * @return the Card Activity data
     */
    Mono<ResponseEntity<CardActivityDTO>> getActivity(Long cardId, Long activityId);

    /**
     * Get all Card Activities for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Activities
     */
    Mono<ResponseEntity<PaginationResponse>> getAllActivities(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Activity
     *
     * @param cardId the ID of the Card
     * @param activityId the ID of the Card Activity to update
     * @param cardActivityRequest the updated Card Activity data
     * @return the updated Card Activity
     */
    Mono<ResponseEntity<CardActivityDTO>> updateActivity(Long cardId, Long activityId, CardActivityRequest cardActivityRequest);

    /**
     * Create a new Card Balance
     *
     * @param cardId the ID of the Card
     * @param cardBalanceRequest the Card Balance data to create
     * @return the created Card Balance
     */
    Mono<ResponseEntity<CardBalanceDTO>> createBalance(Long cardId, CardBalanceRequest cardBalanceRequest);

    /**
     * Delete a Card Balance
     *
     * @param cardId the ID of the Card
     * @param balanceId the ID of the Card Balance to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteBalance(Long cardId, Long balanceId);

    /**
     * Get a Card Balance by ID
     *
     * @param cardId the ID of the Card
     * @param balanceId the ID of the Card Balance to retrieve
     * @return the Card Balance data
     */
    Mono<ResponseEntity<CardBalanceDTO>> getBalance(Long cardId, Long balanceId);

    /**
     * Get all Card Balances for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Balances
     */
    Mono<ResponseEntity<PaginationResponse>> getAllBalances(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Balance
     *
     * @param cardId the ID of the Card
     * @param balanceId the ID of the Card Balance to update
     * @param cardBalanceRequest the updated Card Balance data
     * @return the updated Card Balance
     */
    Mono<ResponseEntity<CardBalanceDTO>> updateBalance(Long cardId, Long balanceId, CardBalanceRequest cardBalanceRequest);

    /**
     * Create a new Card Configuration
     *
     * @param cardId the ID of the Card
     * @param cardConfigurationRequest the Card Configuration data to create
     * @return the created Card Configuration
     */
    Mono<ResponseEntity<CardConfigurationDTO>> createConfiguration(Long cardId, CardConfigurationRequest cardConfigurationRequest);

    /**
     * Delete a Card Configuration
     *
     * @param cardId the ID of the Card
     * @param configId the ID of the Card Configuration to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteConfiguration(Long cardId, Long configId);

    /**
     * Get a Card Configuration by ID
     *
     * @param cardId the ID of the Card
     * @param configId the ID of the Card Configuration to retrieve
     * @return the Card Configuration data
     */
    Mono<ResponseEntity<CardConfigurationDTO>> getConfiguration(Long cardId, Long configId);

    /**
     * Get all Card Configurations for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Configurations
     */
    Mono<ResponseEntity<PaginationResponse>> getAllConfigurations(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Configuration
     *
     * @param cardId the ID of the Card
     * @param configId the ID of the Card Configuration to update
     * @param cardConfigurationRequest the updated Card Configuration data
     * @return the updated Card Configuration
     */
    Mono<ResponseEntity<CardConfigurationDTO>> updateConfiguration(Long cardId, Long configId, CardConfigurationRequest cardConfigurationRequest);

    /**
     * Create a new Card Dispute
     *
     * @param cardId the ID of the Card
     * @param cardDisputeRequest the Card Dispute data to create
     * @return the created Card Dispute
     */
    Mono<ResponseEntity<CardDisputeDTO>> createDispute(Long cardId, CardDisputeRequest cardDisputeRequest);

    /**
     * Delete a Card Dispute
     *
     * @param cardId the ID of the Card
     * @param disputeId the ID of the Card Dispute to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteDispute(Long cardId, Long disputeId);

    /**
     * Get a Card Dispute by ID
     *
     * @param cardId the ID of the Card
     * @param disputeId the ID of the Card Dispute to retrieve
     * @return the Card Dispute data
     */
    Mono<ResponseEntity<CardDisputeDTO>> getDispute(Long cardId, Long disputeId);

    /**
     * Get all Card Disputes for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Disputes
     */
    Mono<ResponseEntity<PaginationResponse>> getAllDisputes(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Dispute
     *
     * @param cardId the ID of the Card
     * @param disputeId the ID of the Card Dispute to update
     * @param cardDisputeRequest the updated Card Dispute data
     * @return the updated Card Dispute
     */
    Mono<ResponseEntity<CardDisputeDTO>> updateDispute(Long cardId, Long disputeId, CardDisputeRequest cardDisputeRequest);

    /**
     * Create a new Card Enrollment
     *
     * @param cardId the ID of the Card
     * @param cardEnrollmentRequest the Card Enrollment data to create
     * @return the created Card Enrollment
     */
    Mono<ResponseEntity<CardEnrollmentDTO>> createEnrollment(Long cardId, CardEnrollmentRequest cardEnrollmentRequest);

    /**
     * Delete a Card Enrollment
     *
     * @param cardId the ID of the Card
     * @param enrollmentId the ID of the Card Enrollment to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteEnrollment(Long cardId, Long enrollmentId);

    /**
     * Get a Card Enrollment by ID
     *
     * @param cardId the ID of the Card
     * @param enrollmentId the ID of the Card Enrollment to retrieve
     * @return the Card Enrollment data
     */
    Mono<ResponseEntity<CardEnrollmentDTO>> getEnrollment(Long cardId, Long enrollmentId);

    /**
     * Get all Card Enrollments for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Enrollments
     */
    Mono<ResponseEntity<PaginationResponse>> getAllEnrollments(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Enrollment
     *
     * @param cardId the ID of the Card
     * @param enrollmentId the ID of the Card Enrollment to update
     * @param cardEnrollmentRequest the updated Card Enrollment data
     * @return the updated Card Enrollment
     */
    Mono<ResponseEntity<CardEnrollmentDTO>> updateEnrollment(Long cardId, Long enrollmentId, CardEnrollmentRequest cardEnrollmentRequest);

    /**
     * Create a new Card Gateway
     *
     * @param cardGatewayRequest the Card Gateway data to create
     * @return the created Card Gateway
     */
    Mono<ResponseEntity<CardGatewayDTO>> createGateway(CardGatewayRequest cardGatewayRequest);

    /**
     * Delete a Card Gateway
     *
     * @param gatewayId the ID of the Card Gateway to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteGateway(Long gatewayId);

    /**
     * Get a Card Gateway by ID
     *
     * @param gatewayId the ID of the Card Gateway to retrieve
     * @return the Card Gateway data
     */
    Mono<ResponseEntity<CardGatewayDTO>> getGateway(Long gatewayId);

    /**
     * Get all Card Gateways
     *
     * @param paginationRequest pagination parameters
     * @return list of Card Gateways
     */
    Mono<ResponseEntity<PaginationResponse>> getAllGateways(PaginationRequest paginationRequest);

    /**
     * Update a Card Gateway
     *
     * @param gatewayId the ID of the Card Gateway to update
     * @param cardGatewayRequest the updated Card Gateway data
     * @return the updated Card Gateway
     */
    Mono<ResponseEntity<CardGatewayDTO>> updateGateway(Long gatewayId, CardGatewayRequest cardGatewayRequest);

    /**
     * Create a new Card Interest
     *
     * @param cardId the ID of the Card
     * @param cardInterestRequest the Card Interest data to create
     * @return the created Card Interest
     */
    Mono<ResponseEntity<CardInterestDTO>> createInterest(Long cardId, CardInterestRequest cardInterestRequest);

    /**
     * Delete a Card Interest
     *
     * @param cardId the ID of the Card
     * @param interestId the ID of the Card Interest to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteInterest(Long cardId, Long interestId);

    /**
     * Get a Card Interest by ID
     *
     * @param cardId the ID of the Card
     * @param interestId the ID of the Card Interest to retrieve
     * @return the Card Interest data
     */
    Mono<ResponseEntity<CardInterestDTO>> getInterest(Long cardId, Long interestId);

    /**
     * Get all Card Interests for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Interests
     */
    Mono<ResponseEntity<PaginationResponse>> getAllInterests(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Interest
     *
     * @param cardId the ID of the Card
     * @param interestId the ID of the Card Interest to update
     * @param cardInterestRequest the updated Card Interest data
     * @return the updated Card Interest
     */
    Mono<ResponseEntity<CardInterestDTO>> updateInterest(Long cardId, Long interestId, CardInterestRequest cardInterestRequest);

    /**
     * Create a new Card Limit
     *
     * @param cardId the ID of the Card
     * @param cardLimitRequest the Card Limit data to create
     * @return the created Card Limit
     */
    Mono<ResponseEntity<CardLimitDTO>> createLimit(Long cardId, CardLimitRequest cardLimitRequest);

    /**
     * Delete a Card Limit
     *
     * @param cardId the ID of the Card
     * @param limitId the ID of the Card Limit to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteLimit(Long cardId, Long limitId);

    /**
     * Get a Card Limit by ID
     *
     * @param cardId the ID of the Card
     * @param limitId the ID of the Card Limit to retrieve
     * @return the Card Limit data
     */
    Mono<ResponseEntity<CardLimitDTO>> getLimit(Long cardId, Long limitId);

    /**
     * Get all Card Limits for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Limits
     */
    Mono<ResponseEntity<PaginationResponse>> getAllLimits(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Limit
     *
     * @param cardId the ID of the Card
     * @param limitId the ID of the Card Limit to update
     * @param cardLimitRequest the updated Card Limit data
     * @return the updated Card Limit
     */
    Mono<ResponseEntity<CardLimitDTO>> updateLimit(Long cardId, Long limitId, CardLimitRequest cardLimitRequest);

    /**
     * Create a new Card Merchant
     *
     * @param cardMerchantRequest the Card Merchant data to create
     * @return the created Card Merchant
     */
    Mono<ResponseEntity<CardMerchantDTO>> createMerchant(CardMerchantRequest cardMerchantRequest);

    /**
     * Delete a Card Merchant
     *
     * @param merchantId the ID of the Card Merchant to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteMerchant(Long merchantId);

    /**
     * Get a Card Merchant by ID
     *
     * @param merchantId the ID of the Card Merchant to retrieve
     * @return the Card Merchant data
     */
    Mono<ResponseEntity<CardMerchantDTO>> getMerchant(Long merchantId);

    /**
     * Get all Card Merchants
     *
     * @param paginationRequest the pagination request
     * @return list of Card Merchants
     */
    Mono<ResponseEntity<PaginationResponse>> getAllMerchants(PaginationRequest paginationRequest);

    /**
     * Update a Card Merchant
     *
     * @param merchantId the ID of the Card Merchant to update
     * @param cardMerchantRequest the updated Card Merchant data
     * @return the updated Card Merchant
     */
    Mono<ResponseEntity<CardMerchantDTO>> updateMerchant(Long merchantId, CardMerchantRequest cardMerchantRequest);

    /**
     * Create a new Card Network
     *
     * @param cardNetworkRequest the Card Network data to create
     * @return the created Card Network
     */
    Mono<ResponseEntity<CardNetworkDTO>> createNetwork(CardNetworkRequest cardNetworkRequest);

    /**
     * Delete a Card Network
     *
     * @param networkId the ID of the Card Network to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteNetwork(Long networkId);

    /**
     * Get a Card Network by ID
     *
     * @param networkId the ID of the Card Network to retrieve
     * @return the Card Network data
     */
    Mono<ResponseEntity<CardNetworkDTO>> getNetwork(Long networkId);

    /**
     * Get all Card Networks
     *
     * @param paginationRequest the pagination request
     * @return list of Card Networks
     */
    Mono<ResponseEntity<PaginationResponse>> getAllNetworks(PaginationRequest paginationRequest);

    /**
     * Update a Card Network
     *
     * @param networkId the ID of the Card Network to update
     * @param cardNetworkRequest the updated Card Network data
     * @return the updated Card Network
     */
    Mono<ResponseEntity<CardNetworkDTO>> updateNetwork(Long networkId, CardNetworkRequest cardNetworkRequest);

    /**
     * Create a new Card Payment
     *
     * @param cardId the ID of the Card
     * @param cardPaymentRequest the Card Payment data to create
     * @return the created Card Payment
     */
    Mono<ResponseEntity<CardPaymentDTO>> createPayment(Long cardId, CardPaymentRequest cardPaymentRequest);

    /**
     * Delete a Card Payment
     *
     * @param cardId the ID of the Card
     * @param paymentId the ID of the Card Payment to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deletePayment(Long cardId, Long paymentId);

    /**
     * Get a Card Payment by ID
     *
     * @param cardId the ID of the Card
     * @param paymentId the ID of the Card Payment to retrieve
     * @return the Card Payment data
     */
    Mono<ResponseEntity<CardPaymentDTO>> getPayment(Long cardId, Long paymentId);

    /**
     * Get all Card Payments for a Card
     *
     * @param cardId the ID of the Card
     * @param paginationRequest the pagination request
     * @return list of Card Payments
     */
    Mono<ResponseEntity<PaginationResponse>> getAllPayments(Long cardId, PaginationRequest paginationRequest);

    /**
     * Update a Card Payment
     *
     * @param cardId the ID of the Card
     * @param paymentId the ID of the Card Payment to update
     * @param cardPaymentRequest the updated Card Payment data
     * @return the updated Card Payment
     */
    Mono<ResponseEntity<CardPaymentDTO>> updatePayment(Long cardId, Long paymentId, CardPaymentRequest cardPaymentRequest);

    /**
     * Create a new Card Program
     *
     * @param cardProgramRequest the Card Program data to create
     * @return the created Card Program
     */
    Mono<ResponseEntity<CardProgramDTO>> createProgram(CardProgramRequest cardProgramRequest);

    /**
     * Delete a Card Program
     *
     * @param programId the ID of the Card Program to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteProgram(Long programId);

    /**
     * Get a Card Program by ID
     *
     * @param programId the ID of the Card Program to retrieve
     * @return the Card Program data
     */
    Mono<ResponseEntity<CardProgramDTO>> getProgram(Long programId);

    /**
     * Get all Card Programs
     *
     * @param paginationRequest the pagination request
     * @return list of Card Programs
     */
    Mono<ResponseEntity<PaginationResponse>> getAllPrograms(PaginationRequest paginationRequest);

    /**
     * Update a Card Program
     *
     * @param programId the ID of the Card Program to update
     * @param cardProgramRequest the updated Card Program data
     * @return the updated Card Program
     */
    Mono<ResponseEntity<CardProgramDTO>> updateProgram(Long programId, CardProgramRequest cardProgramRequest);

    /**
     * Create a new Card Promotion
     *
     * @param cardId the ID of the Card
     * @param cardPromotionRequest the Card Promotion data to create
     * @return the created Card Promotion
     */
    Mono<ResponseEntity<CardPromotionDTO>> createPromotion(Long cardId, CardPromotionRequest cardPromotionRequest);

    /**
     * Delete a Card Promotion
     *
     * @param cardId the ID of the Card
     * @param promotionId the ID of the Card Promotion to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deletePromotion(Long cardId, Long promotionId);

    /**
     * Get a Card Promotion by ID
     *
     * @param cardId the ID of the Card
     * @param promotionId the ID of the Card Promotion to retrieve
     * @return the Card Promotion data
     */
    Mono<ResponseEntity<CardPromotionDTO>> getPromotion(Long cardId, Long promotionId);

    /**
     * Get all Card Promotions for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Promotions
     */
    Mono<ResponseEntity<PaginationResponse>> getAllPromotions(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Promotion
     *
     * @param cardId the ID of the Card
     * @param promotionId the ID of the Card Promotion to update
     * @param cardPromotionRequest the updated Card Promotion data
     * @return the updated Card Promotion
     */
    Mono<ResponseEntity<CardPromotionDTO>> updatePromotion(Long cardId, Long promotionId, CardPromotionRequest cardPromotionRequest);

    /**
     * Create a new Card Provider
     *
     * @param cardId the ID of the Card
     * @param cardProviderRequest the Card Provider data to create
     * @return the created Card Provider
     */
    Mono<ResponseEntity<CardProviderDTO>> createProvider(Long cardId, CardProviderRequest cardProviderRequest);

    /**
     * Delete a Card Provider
     *
     * @param cardId the ID of the Card
     * @param providerId the ID of the Card Provider to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteProvider(Long cardId, Long providerId);

    /**
     * Get a Card Provider by ID
     *
     * @param cardId the ID of the Card
     * @param providerId the ID of the Card Provider to retrieve
     * @return the Card Provider data
     */
    Mono<ResponseEntity<CardProviderDTO>> getProvider(Long cardId, Long providerId);

    /**
     * Get all Card Providers for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Providers
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProviders(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Provider
     *
     * @param cardId the ID of the Card
     * @param providerId the ID of the Card Provider to update
     * @param cardProviderRequest the updated Card Provider data
     * @return the updated Card Provider
     */
    Mono<ResponseEntity<CardProviderDTO>> updateProvider(Long cardId, Long providerId, CardProviderRequest cardProviderRequest);

    /**
     * Create a new Card Security Setting
     *
     * @param cardId the ID of the Card
     * @param cardSecurityRequest the Card Security Setting data to create
     * @return the created Card Security Setting
     */
    Mono<ResponseEntity<CardSecurityDTO>> createSecuritySetting(Long cardId, CardSecurityRequest cardSecurityRequest);

    /**
     * Delete a Card Security Setting
     *
     * @param cardId the ID of the Card
     * @param securityId the ID of the Card Security Setting to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteSecuritySetting(Long cardId, Long securityId);

    /**
     * Get a Card Security Setting by ID
     *
     * @param cardId the ID of the Card
     * @param securityId the ID of the Card Security Setting to retrieve
     * @return the Card Security Setting data
     */
    Mono<ResponseEntity<CardSecurityDTO>> getSecuritySetting(Long cardId, Long securityId);

    /**
     * Get all Card Security Settings for a Card
     *
     * @param cardId the ID of the Card
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return list of Card Security Settings
     */
    Mono<ResponseEntity<PaginationResponse>> getAllSecuritySettings(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a Card Security Setting
     *
     * @param cardId the ID of the Card
     * @param securityId the ID of the Card Security Setting to update
     * @param cardSecurityRequest the updated Card Security Setting data
     * @return the updated Card Security Setting
     */
    Mono<ResponseEntity<CardSecurityDTO>> updateSecuritySetting(Long cardId, Long securityId, CardSecurityRequest cardSecurityRequest);

    /**
     * Create a new Card Terminal
     *
     * @param cardTerminalRequest the Card Terminal data to create
     * @return the created Card Terminal
     */
    Mono<ResponseEntity<CardTerminalDTO>> createTerminal(CardTerminalRequest cardTerminalRequest);

    /**
     * Delete a Card Terminal
     *
     * @param terminalId the ID of the Card Terminal to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteTerminal(Long terminalId);

    /**
     * Get a Card Terminal by ID
     *
     * @param terminalId the ID of the Card Terminal to retrieve
     * @return the Card Terminal data
     */
    Mono<ResponseEntity<CardTerminalDTO>> getTerminal(Long terminalId);

    /**
     * Get all Card Terminals
     *
     * @param paginationRequest the pagination request
     * @return list of Card Terminals
     */
    Mono<ResponseEntity<PaginationResponse>> getAllTerminals(PaginationRequest paginationRequest);

    /**
     * Update a Card Terminal
     *
     * @param terminalId the ID of the Card Terminal to update
     * @param cardTerminalRequest the updated Card Terminal data
     * @return the updated Card Terminal
     */
    Mono<ResponseEntity<CardTerminalDTO>> updateTerminal(Long terminalId, CardTerminalRequest cardTerminalRequest);

    /**
     * Create a new Card Transaction
     *
     * @param cardId the ID of the Card
     * @param cardTransactionRequest the Card Transaction data to create
     * @return the created Card Transaction
     */
    Mono<ResponseEntity<CardTransactionDTO>> createTransaction(Long cardId, CardTransactionRequest cardTransactionRequest);

    /**
     * Delete a Card Transaction
     *
     * @param cardId the ID of the Card
     * @param transactionId the ID of the Card Transaction to delete
     * @return void response
     */
    Mono<ResponseEntity<Void>> deleteTransaction(Long cardId, Long transactionId);

    /**
     * Get a Card Transaction by ID
     *
     * @param cardId the ID of the Card
     * @param transactionId the ID of the Card Transaction to retrieve
     * @return the Card Transaction data
     */
    Mono<ResponseEntity<CardTransactionDTO>> getTransaction(Long cardId, Long transactionId);

    /**
     * Get all Card Transactions for a Card
     *
     * @param cardId the ID of the Card
     * @param paginationRequest the pagination request
     * @param filterRequest the filter request
     * @return list of Card Transactions
     */
    Mono<ResponseEntity<PaginationResponse>> getAllTransactions(Long cardId, PaginationRequest paginationRequest, com.catalis.core.banking.cards.sdk.model.FilterRequestCardTransactionDTO filterRequest);

    /**
     * Update a Card Transaction
     *
     * @param cardId the ID of the Card
     * @param transactionId the ID of the Card Transaction to update
     * @param cardTransactionRequest the updated Card Transaction data
     * @return the updated Card Transaction
     */
    Mono<ResponseEntity<CardTransactionDTO>> updateTransaction(Long cardId, Long transactionId, CardTransactionRequest cardTransactionRequest);

    /**
     * Create a new issuer.
     *
     * @param issuerRequest the issuer request
     * @return the created issuer
     */
    Mono<ResponseEntity<IssuerDTO>> createIssuer(IssuerRequest issuerRequest);

    /**
     * Delete an issuer.
     *
     * @param issuerId the issuer ID
     * @return void
     */
    Mono<ResponseEntity<Void>> deleteIssuer(Long issuerId);

    /**
     * Get all issuers.
     *
     * @param paginationRequest the pagination request
     * @return the issuers
     */
    Mono<ResponseEntity<PaginationResponse>> getAllIssuers(PaginationRequest paginationRequest);

    /**
     * Get an issuer.
     *
     * @param issuerId the issuer ID
     * @return the issuer
     */
    Mono<ResponseEntity<IssuerDTO>> getIssuer(Long issuerId);

    /**
     * Update an issuer.
     *
     * @param issuerId the issuer ID
     * @param issuerRequest the issuer request
     * @return the updated issuer
     */
    Mono<ResponseEntity<IssuerDTO>> updateIssuer(Long issuerId, IssuerRequest issuerRequest);

    /**
     * Create a new physical card.
     *
     * @param cardId the card ID
     * @param physicalCardRequest the physical card request
     * @return the created physical card
     */
    Mono<ResponseEntity<PhysicalCardDTO>> createPhysicalCard(Long cardId, PhysicalCardRequest physicalCardRequest);

    /**
     * Delete a physical card.
     *
     * @param cardId the card ID
     * @param physicalCardId the physical card ID
     * @return void
     */
    Mono<ResponseEntity<Void>> deletePhysicalCard(Long cardId, Long physicalCardId);

    /**
     * Get all physical cards.
     *
     * @param cardId the card ID
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the sort by
     * @param sortDirection the sort direction
     * @return the physical cards
     */
    Mono<ResponseEntity<PaginationResponse>> getAllPhysicalCards(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a physical card.
     *
     * @param cardId the card ID
     * @param physicalCardId the physical card ID
     * @return the physical card
     */
    Mono<ResponseEntity<PhysicalCardDTO>> getPhysicalCard(Long cardId, Long physicalCardId);

    /**
     * Update a physical card.
     *
     * @param cardId the card ID
     * @param physicalCardId the physical card ID
     * @param physicalCardRequest the physical card request
     * @return the updated physical card
     */
    Mono<ResponseEntity<PhysicalCardDTO>> updatePhysicalCard(Long cardId, Long physicalCardId, PhysicalCardRequest physicalCardRequest);

    /**
     * Create a new virtual card.
     *
     * @param cardId the card ID
     * @param virtualCardRequest the virtual card request
     * @return the created virtual card
     */
    Mono<ResponseEntity<VirtualCardDTO>> createVirtualCard(Long cardId, VirtualCardRequest virtualCardRequest);

    /**
     * Delete a virtual card.
     *
     * @param cardId the card ID
     * @param virtualCardId the virtual card ID
     * @return void
     */
    Mono<ResponseEntity<Void>> deleteVirtualCard(Long cardId, Long virtualCardId);

    /**
     * Get all virtual cards.
     *
     * @param cardId the card ID
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the sort by
     * @param sortDirection the sort direction
     * @return the virtual cards
     */
    Mono<ResponseEntity<PaginationResponse>> getAllVirtualCards(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a virtual card.
     *
     * @param cardId the card ID
     * @param virtualCardId the virtual card ID
     * @return the virtual card
     */
    Mono<ResponseEntity<VirtualCardDTO>> getVirtualCard(Long cardId, Long virtualCardId);

    /**
     * Update a virtual card.
     *
     * @param cardId the card ID
     * @param virtualCardId the virtual card ID
     * @param virtualCardRequest the virtual card request
     * @return the updated virtual card
     */
    Mono<ResponseEntity<VirtualCardDTO>> updateVirtualCard(Long cardId, Long virtualCardId, VirtualCardRequest virtualCardRequest);

}

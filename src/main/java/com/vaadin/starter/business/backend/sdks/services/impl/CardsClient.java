package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.core.banking.cards.sdk.api.*;
import com.catalis.core.banking.cards.sdk.invoker.ApiClient;
import com.catalis.core.banking.cards.sdk.model.*;
import com.vaadin.starter.business.backend.mapper.cards.CardsMapper;
import com.vaadin.starter.business.backend.sdks.services.CardsService;
import com.vaadin.starter.business.backend.sdks.services.rest.cards.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CardsClient implements CardsService {

    private final BinsApi binsApi;
    private final CardsApi cardsApi;
    private final CardAcquirersApi cardAcquirersApi;
    private final CardActivitiesApi cardActivitiesApi;
    private final CardBalancesApi cardBalancesApi;
    private final CardConfigurationsApi cardConfigurationsApi;
    private final CardDisputesApi cardDisputesApi;
    private final CardEnrollmentsApi cardEnrollmentsApi;
    private final CardGatewaysApi cardGatewaysApi;
    private final CardInterestsApi cardInterestsApi;
    private final CardLimitsApi cardLimitsApi;
    private final CardMerchantsApi cardMerchantsApi;
    private final CardNetworksApi cardNetworksApi;
    private final CardPaymentsApi cardPaymentsApi;
    private final CardProgramsApi cardProgramsApi;
    private final CardPromotionsApi cardPromotionsApi;
    private final CardProvidersApi cardProvidersApi;
    private final CardSecurityApi cardSecurityApi;
    private final CardTerminalsApi cardTerminalsApi;
    private final CardTransactionsApi cardTransactionsApi;
    private final IssuersApi issuersApi;
    private final PhysicalCardsApi physicalCardsApi;
    private final VirtualCardsApi virtualCardsApi;
    private final CardsMapper cardsMapper;

    @Autowired
    public CardsClient(ApiClient apiClient, CardsMapper cardsMapper) {
        this.binsApi = new BinsApi(apiClient);
        this.cardsApi = new CardsApi(apiClient);
        this.cardAcquirersApi = new CardAcquirersApi(apiClient);
        this.cardActivitiesApi = new CardActivitiesApi(apiClient);
        this.cardBalancesApi = new CardBalancesApi(apiClient);
        this.cardConfigurationsApi = new CardConfigurationsApi(apiClient);
        this.cardDisputesApi = new CardDisputesApi(apiClient);
        this.cardEnrollmentsApi = new CardEnrollmentsApi(apiClient);
        this.cardGatewaysApi = new CardGatewaysApi(apiClient);
        this.cardInterestsApi = new CardInterestsApi(apiClient);
        this.cardLimitsApi = new CardLimitsApi(apiClient);
        this.cardMerchantsApi = new CardMerchantsApi(apiClient);
        this.cardNetworksApi = new CardNetworksApi(apiClient);
        this.cardPaymentsApi = new CardPaymentsApi(apiClient);
        this.cardProgramsApi = new CardProgramsApi(apiClient);
        this.cardPromotionsApi = new CardPromotionsApi(apiClient);
        this.cardProvidersApi = new CardProvidersApi(apiClient);
        this.cardSecurityApi = new CardSecurityApi(apiClient);
        this.cardTerminalsApi = new CardTerminalsApi(apiClient);
        this.cardTransactionsApi = new CardTransactionsApi(apiClient);
        this.issuersApi = new IssuersApi(apiClient);
        this.physicalCardsApi = new PhysicalCardsApi(apiClient);
        this.virtualCardsApi = new VirtualCardsApi(apiClient);
        this.cardsMapper = cardsMapper;
    }

    @Override
    public Mono<ResponseEntity<BINDTO>> createBIN(BINRequest binRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return binsApi.createBINWithHttpInfo(cardsMapper.binRequestToDto(binRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBIN(Long binId) {
        return binsApi.deleteBINWithHttpInfo(binId);
    }

    @Override
    public Mono<ResponseEntity<GetAllBINs1200Response>> getAllBINs(PaginationRequest paginationRequest, String number) {
        return binsApi.getAllBINs1WithHttpInfo(paginationRequest, number);
    }

    @Override
    public Mono<ResponseEntity<BINDTO>> getBIN(Long binId) {
        return binsApi.getBINWithHttpInfo(binId);
    }

    @Override
    public Mono<ResponseEntity<BINDTO>> updateBIN(Long binId, BINRequest binRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return binsApi.updateBINWithHttpInfo(binId, cardsMapper.binRequestToDto(binRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardDTO>> createCard(CardRequest cardRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardsApi.createCardWithHttpInfo(cardsMapper.cardRequestToDto(cardRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCard(Long cardId) {
        return cardsApi.deleteCardWithHttpInfo(cardId);
    }

    @Override
    public Mono<ResponseEntity<CardDTO>> getCard(Long cardId) {
        return cardsApi.getCardWithHttpInfo(cardId);
    }

    @Override
    public Mono<ResponseEntity<CardDTO>> updateCard(Long cardId, CardRequest cardRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardsApi.updateCardWithHttpInfo(cardId, cardsMapper.cardRequestToDto(cardRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardAcquirerDTO>> createAcquirer(CardAcquirerRequest cardAcquirerRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardAcquirersApi.createAcquirerWithHttpInfo(cardsMapper.cardAcquirerRequestToDto(cardAcquirerRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteAcquirer(Long acquirerId) {
        return cardAcquirersApi.deleteAcquirerWithHttpInfo(acquirerId);
    }

    @Override
    public Mono<ResponseEntity<CardAcquirerDTO>> getAcquirer(Long acquirerId) {
        return cardAcquirersApi.getAcquirerWithHttpInfo(acquirerId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllAcquirers(String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardAcquirersApi.getAllAcquirersWithHttpInfo(pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardAcquirerDTO>> updateAcquirer(Long acquirerId, CardAcquirerRequest cardAcquirerRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardAcquirersApi.updateAcquirerWithHttpInfo(acquirerId, cardsMapper.cardAcquirerRequestToDto(cardAcquirerRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardActivityDTO>> createActivity(Long cardId, CardActivityRequest cardActivityRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardActivitiesApi.createActivityWithHttpInfo(cardId, cardsMapper.cardActivityRequestToDto(cardActivityRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteActivity(Long cardId, Long activityId) {
        return cardActivitiesApi.deleteActivityWithHttpInfo(cardId, activityId);
    }

    @Override
    public Mono<ResponseEntity<CardActivityDTO>> getActivity(Long cardId, Long activityId) {
        return cardActivitiesApi.getActivityWithHttpInfo(cardId, activityId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllActivities(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardActivitiesApi.getAllActivitiesWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardActivityDTO>> updateActivity(Long cardId, Long activityId, CardActivityRequest cardActivityRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardActivitiesApi.updateActivityWithHttpInfo(cardId, activityId, cardsMapper.cardActivityRequestToDto(cardActivityRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardBalanceDTO>> createBalance(Long cardId, CardBalanceRequest cardBalanceRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardBalancesApi.createBalanceWithHttpInfo(cardId, cardsMapper.cardBalanceRequestToDto(cardBalanceRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBalance(Long cardId, Long balanceId) {
        return cardBalancesApi.deleteBalanceWithHttpInfo(cardId, balanceId);
    }

    @Override
    public Mono<ResponseEntity<CardBalanceDTO>> getBalance(Long cardId, Long balanceId) {
        return cardBalancesApi.getBalanceWithHttpInfo(cardId, balanceId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllBalances(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardBalancesApi.getAllBalancesWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardBalanceDTO>> updateBalance(Long cardId, Long balanceId, CardBalanceRequest cardBalanceRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardBalancesApi.updateBalanceWithHttpInfo(cardId, balanceId, cardsMapper.cardBalanceRequestToDto(cardBalanceRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardConfigurationDTO>> createConfiguration(Long cardId, CardConfigurationRequest cardConfigurationRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardConfigurationsApi.createConfigurationWithHttpInfo(cardId, cardsMapper.cardConfigurationRequestToDto(cardConfigurationRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteConfiguration(Long cardId, Long configId) {
        return cardConfigurationsApi.deleteConfigurationWithHttpInfo(cardId, configId);
    }

    @Override
    public Mono<ResponseEntity<CardConfigurationDTO>> getConfiguration(Long cardId, Long configId) {
        return cardConfigurationsApi.getConfigurationWithHttpInfo(cardId, configId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllConfigurations(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardConfigurationsApi.getAllConfigurationsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardConfigurationDTO>> updateConfiguration(Long cardId, Long configId, CardConfigurationRequest cardConfigurationRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardConfigurationsApi.updateConfigurationWithHttpInfo(cardId, configId, cardsMapper.cardConfigurationRequestToDto(cardConfigurationRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardDisputeDTO>> createDispute(Long cardId, CardDisputeRequest cardDisputeRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardDisputesApi.createDisputeWithHttpInfo(cardId, cardsMapper.cardDisputeRequestToDto(cardDisputeRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteDispute(Long cardId, Long disputeId) {
        return cardDisputesApi.deleteDisputeWithHttpInfo(cardId, disputeId);
    }

    @Override
    public Mono<ResponseEntity<CardDisputeDTO>> getDispute(Long cardId, Long disputeId) {
        return cardDisputesApi.getDisputeWithHttpInfo(cardId, disputeId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllDisputes(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardDisputesApi.getAllDisputesWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardDisputeDTO>> updateDispute(Long cardId, Long disputeId, CardDisputeRequest cardDisputeRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardDisputesApi.updateDisputeWithHttpInfo(cardId, disputeId, cardsMapper.cardDisputeRequestToDto(cardDisputeRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardEnrollmentDTO>> createEnrollment(Long cardId, CardEnrollmentRequest cardEnrollmentRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardEnrollmentsApi.createEnrollmentWithHttpInfo(cardId, cardsMapper.cardEnrollmentRequestToDto(cardEnrollmentRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteEnrollment(Long cardId, Long enrollmentId) {
        return cardEnrollmentsApi.deleteEnrollmentWithHttpInfo(cardId, enrollmentId);
    }

    @Override
    public Mono<ResponseEntity<CardEnrollmentDTO>> getEnrollment(Long cardId, Long enrollmentId) {
        return cardEnrollmentsApi.getEnrollmentWithHttpInfo(cardId, enrollmentId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllEnrollments(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardEnrollmentsApi.getAllEnrollmentsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardEnrollmentDTO>> updateEnrollment(Long cardId, Long enrollmentId, CardEnrollmentRequest cardEnrollmentRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardEnrollmentsApi.updateEnrollmentWithHttpInfo(cardId, enrollmentId, cardsMapper.cardEnrollmentRequestToDto(cardEnrollmentRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardGatewayDTO>> createGateway(CardGatewayRequest cardGatewayRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardGatewaysApi.createGatewayWithHttpInfo(cardsMapper.cardGatewayRequestToDto(cardGatewayRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteGateway(Long gatewayId) {
        return cardGatewaysApi.deleteGatewayWithHttpInfo(gatewayId);
    }

    @Override
    public Mono<ResponseEntity<CardGatewayDTO>> getGateway(Long gatewayId) {
        return cardGatewaysApi.getGatewayWithHttpInfo(gatewayId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllGateways(PaginationRequest paginationRequest) {
        return cardGatewaysApi.getAllGatewaysWithHttpInfo(paginationRequest);
    }

    @Override
    public Mono<ResponseEntity<CardGatewayDTO>> updateGateway(Long gatewayId, CardGatewayRequest cardGatewayRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardGatewaysApi.updateGatewayWithHttpInfo(gatewayId, cardsMapper.cardGatewayRequestToDto(cardGatewayRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardInterestDTO>> createInterest(Long cardId, CardInterestRequest cardInterestRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardInterestsApi.createInterestWithHttpInfo(cardId, cardsMapper.cardInterestRequestToDto(cardInterestRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteInterest(Long cardId, Long interestId) {
        return cardInterestsApi.deleteInterestWithHttpInfo(cardId, interestId);
    }

    @Override
    public Mono<ResponseEntity<CardInterestDTO>> getInterest(Long cardId, Long interestId) {
        return cardInterestsApi.getInterestWithHttpInfo(cardId, interestId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllInterests(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardInterestsApi.getAllInterestsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardInterestDTO>> updateInterest(Long cardId, Long interestId, CardInterestRequest cardInterestRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardInterestsApi.updateInterestWithHttpInfo(cardId, interestId, cardsMapper.cardInterestRequestToDto(cardInterestRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardLimitDTO>> createLimit(Long cardId, CardLimitRequest cardLimitRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardLimitsApi.createLimitWithHttpInfo(cardId, cardsMapper.cardLimitRequestToDto(cardLimitRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteLimit(Long cardId, Long limitId) {
        return cardLimitsApi.deleteLimitWithHttpInfo(cardId, limitId);
    }

    @Override
    public Mono<ResponseEntity<CardLimitDTO>> getLimit(Long cardId, Long limitId) {
        return cardLimitsApi.getLimitWithHttpInfo(cardId, limitId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllLimits(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardLimitsApi.getAllLimitsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardLimitDTO>> updateLimit(Long cardId, Long limitId, CardLimitRequest cardLimitRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardLimitsApi.updateLimitWithHttpInfo(cardId, limitId, cardsMapper.cardLimitRequestToDto(cardLimitRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardMerchantDTO>> createMerchant(CardMerchantRequest cardMerchantRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardMerchantsApi.createMerchantWithHttpInfo(cardsMapper.cardMerchantRequestToDto(cardMerchantRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteMerchant(Long merchantId) {
        return cardMerchantsApi.deleteMerchantWithHttpInfo(merchantId);
    }

    @Override
    public Mono<ResponseEntity<CardMerchantDTO>> getMerchant(Long merchantId) {
        return cardMerchantsApi.getMerchantWithHttpInfo(merchantId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllMerchants(PaginationRequest paginationRequest) {
        return cardMerchantsApi.getAllMerchantsWithHttpInfo(paginationRequest);
    }

    @Override
    public Mono<ResponseEntity<CardMerchantDTO>> updateMerchant(Long merchantId, CardMerchantRequest cardMerchantRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardMerchantsApi.updateMerchantWithHttpInfo(merchantId, cardsMapper.cardMerchantRequestToDto(cardMerchantRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardNetworkDTO>> createNetwork(CardNetworkRequest cardNetworkRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardNetworksApi.createNetworkWithHttpInfo(cardsMapper.cardNetworkRequestToDto(cardNetworkRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteNetwork(Long networkId) {
        return cardNetworksApi.deleteNetworkWithHttpInfo(networkId);
    }

    @Override
    public Mono<ResponseEntity<CardNetworkDTO>> getNetwork(Long networkId) {
        return cardNetworksApi.getNetworkWithHttpInfo(networkId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllNetworks(PaginationRequest paginationRequest) {
        return cardNetworksApi.getAllNetworksWithHttpInfo(paginationRequest);
    }

    @Override
    public Mono<ResponseEntity<CardNetworkDTO>> updateNetwork(Long networkId, CardNetworkRequest cardNetworkRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardNetworksApi.updateNetworkWithHttpInfo(networkId, cardsMapper.cardNetworkRequestToDto(cardNetworkRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardPaymentDTO>> createPayment(Long cardId, CardPaymentRequest cardPaymentRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardPaymentsApi.createPaymentWithHttpInfo(cardId, cardsMapper.cardPaymentRequestToDto(cardPaymentRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePayment(Long cardId, Long paymentId) {
        return cardPaymentsApi.deletePaymentWithHttpInfo(cardId, paymentId);
    }

    @Override
    public Mono<ResponseEntity<CardPaymentDTO>> getPayment(Long cardId, Long paymentId) {
        return cardPaymentsApi.getPaymentWithHttpInfo(cardId, paymentId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllPayments(Long cardId, PaginationRequest paginationRequest) {
        return cardPaymentsApi.getAllPaymentsWithHttpInfo(cardId, paginationRequest);
    }

    @Override
    public Mono<ResponseEntity<CardPaymentDTO>> updatePayment(Long cardId, Long paymentId, CardPaymentRequest cardPaymentRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardPaymentsApi.updatePaymentWithHttpInfo(cardId, paymentId, cardsMapper.cardPaymentRequestToDto(cardPaymentRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardProgramDTO>> createProgram(CardProgramRequest cardProgramRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardProgramsApi.createProgramWithHttpInfo(cardsMapper.cardProgramRequestToDto(cardProgramRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProgram(Long programId) {
        return cardProgramsApi.deleteProgramWithHttpInfo(programId);
    }

    @Override
    public Mono<ResponseEntity<CardProgramDTO>> getProgram(Long programId) {
        return cardProgramsApi.getProgramWithHttpInfo(programId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllPrograms(PaginationRequest paginationRequest) {
        return cardProgramsApi.getAllProgramsWithHttpInfo(paginationRequest);
    }

    @Override
    public Mono<ResponseEntity<CardProgramDTO>> updateProgram(Long programId, CardProgramRequest cardProgramRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardProgramsApi.updateProgramWithHttpInfo(programId, cardsMapper.cardProgramRequestToDto(cardProgramRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardPromotionDTO>> createPromotion(Long cardId, CardPromotionRequest cardPromotionRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardPromotionsApi.createPromotionWithHttpInfo(cardId, cardsMapper.cardPromotionRequestToDto(cardPromotionRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePromotion(Long cardId, Long promotionId) {
        return cardPromotionsApi.deletePromotionWithHttpInfo(cardId, promotionId);
    }

    @Override
    public Mono<ResponseEntity<CardPromotionDTO>> getPromotion(Long cardId, Long promotionId) {
        return cardPromotionsApi.getPromotionWithHttpInfo(cardId, promotionId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllPromotions(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardPromotionsApi.getAllPromotionsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardPromotionDTO>> updatePromotion(Long cardId, Long promotionId, CardPromotionRequest cardPromotionRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardPromotionsApi.updatePromotionWithHttpInfo(cardId, promotionId, cardsMapper.cardPromotionRequestToDto(cardPromotionRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardProviderDTO>> createProvider(Long cardId, CardProviderRequest cardProviderRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardProvidersApi.createProviderWithHttpInfo(cardId, cardsMapper.cardProviderRequestToDto(cardProviderRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProvider(Long cardId, Long providerId) {
        return cardProvidersApi.deleteProviderWithHttpInfo(cardId, providerId);
    }

    @Override
    public Mono<ResponseEntity<CardProviderDTO>> getProvider(Long cardId, Long providerId) {
        return cardProvidersApi.getProviderWithHttpInfo(cardId, providerId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProviders(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardProvidersApi.getAllProvidersWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardProviderDTO>> updateProvider(Long cardId, Long providerId, CardProviderRequest cardProviderRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardProvidersApi.updateProviderWithHttpInfo(cardId, providerId, cardsMapper.cardProviderRequestToDto(cardProviderRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardSecurityDTO>> createSecuritySetting(Long cardId, CardSecurityRequest cardSecurityRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardSecurityApi.createSecuritySettingWithHttpInfo(cardId, cardsMapper.cardSecurityRequestToDto(cardSecurityRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteSecuritySetting(Long cardId, Long securityId) {
        return cardSecurityApi.deleteSecuritySettingWithHttpInfo(cardId, securityId);
    }

    @Override
    public Mono<ResponseEntity<CardSecurityDTO>> getSecuritySetting(Long cardId, Long securityId) {
        return cardSecurityApi.getSecuritySettingWithHttpInfo(cardId, securityId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllSecuritySettings(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return cardSecurityApi.getAllSecuritySettingsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<CardSecurityDTO>> updateSecuritySetting(Long cardId, Long securityId, CardSecurityRequest cardSecurityRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardSecurityApi.updateSecuritySettingWithHttpInfo(cardId, securityId, cardsMapper.cardSecurityRequestToDto(cardSecurityRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardTerminalDTO>> createTerminal(CardTerminalRequest cardTerminalRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardTerminalsApi.createTerminalWithHttpInfo(cardsMapper.cardTerminalRequestToDto(cardTerminalRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteTerminal(Long terminalId) {
        return cardTerminalsApi.deleteTerminalWithHttpInfo(terminalId);
    }

    @Override
    public Mono<ResponseEntity<CardTerminalDTO>> getTerminal(Long terminalId) {
        return cardTerminalsApi.getTerminalWithHttpInfo(terminalId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllTerminals(PaginationRequest paginationRequest) {
        return cardTerminalsApi.getAllTerminalsWithHttpInfo(paginationRequest);
    }

    @Override
    public Mono<ResponseEntity<CardTerminalDTO>> updateTerminal(Long terminalId, CardTerminalRequest cardTerminalRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardTerminalsApi.updateTerminalWithHttpInfo(terminalId, cardsMapper.cardTerminalRequestToDto(cardTerminalRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CardTransactionDTO>> createTransaction(Long cardId, CardTransactionRequest cardTransactionRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardTransactionsApi.createTransactionWithHttpInfo(cardId, cardsMapper.cardTransactionRequestToDto(cardTransactionRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteTransaction(Long cardId, Long transactionId) {
        return cardTransactionsApi.deleteTransactionWithHttpInfo(cardId, transactionId);
    }

    @Override
    public Mono<ResponseEntity<CardTransactionDTO>> getTransaction(Long cardId, Long transactionId) {
        return cardTransactionsApi.getTransactionWithHttpInfo(cardId, transactionId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllTransactions(Long cardId, PaginationRequest paginationRequest, com.catalis.core.banking.cards.sdk.model.FilterRequestCardTransactionDTO filterRequest) {
        return cardTransactionsApi.getAllTransactions1WithHttpInfo(cardId, paginationRequest, filterRequest);
    }

    @Override
    public Mono<ResponseEntity<CardTransactionDTO>> updateTransaction(Long cardId, Long transactionId, CardTransactionRequest cardTransactionRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return cardTransactionsApi.updateTransactionWithHttpInfo(cardId, transactionId, cardsMapper.cardTransactionRequestToDto(cardTransactionRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<IssuerDTO>> createIssuer(IssuerRequest issuerRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return issuersApi.createIssuerWithHttpInfo(cardsMapper.issuerRequestToDto(issuerRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteIssuer(Long issuerId) {
        return issuersApi.deleteIssuerWithHttpInfo(issuerId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllIssuers(PaginationRequest paginationRequest) {
        return issuersApi.getAllIssuersWithHttpInfo(paginationRequest);
    }

    @Override
    public Mono<ResponseEntity<IssuerDTO>> getIssuer(Long issuerId) {
        return issuersApi.getIssuerWithHttpInfo(issuerId);
    }

    @Override
    public Mono<ResponseEntity<IssuerDTO>> updateIssuer(Long issuerId, IssuerRequest issuerRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return issuersApi.updateIssuerWithHttpInfo(issuerId, cardsMapper.issuerRequestToDto(issuerRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PhysicalCardDTO>> createPhysicalCard(Long cardId, PhysicalCardRequest physicalCardRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return physicalCardsApi.createPhysicalCardWithHttpInfo(cardId, cardsMapper.physicalCardRequestToDto(physicalCardRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePhysicalCard(Long cardId, Long physicalCardId) {
        return physicalCardsApi.deletePhysicalCardWithHttpInfo(cardId, physicalCardId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllPhysicalCards(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return physicalCardsApi.getAllPhysicalCardsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<PhysicalCardDTO>> getPhysicalCard(Long cardId, Long physicalCardId) {
        return physicalCardsApi.getPhysicalCardWithHttpInfo(cardId, physicalCardId);
    }

    @Override
    public Mono<ResponseEntity<PhysicalCardDTO>> updatePhysicalCard(Long cardId, Long physicalCardId, PhysicalCardRequest physicalCardRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return physicalCardsApi.updatePhysicalCardWithHttpInfo(cardId, physicalCardId, cardsMapper.physicalCardRequestToDto(physicalCardRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<VirtualCardDTO>> createVirtualCard(Long cardId, VirtualCardRequest virtualCardRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return virtualCardsApi.createVirtualCardWithHttpInfo(cardId, cardsMapper.virtualCardRequestToDto(virtualCardRequest), idempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteVirtualCard(Long cardId, Long virtualCardId) {
        return virtualCardsApi.deleteVirtualCardWithHttpInfo(cardId, virtualCardId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllVirtualCards(Long cardId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return virtualCardsApi.getAllVirtualCardsWithHttpInfo(cardId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<VirtualCardDTO>> getVirtualCard(Long cardId, Long virtualCardId) {
        return virtualCardsApi.getVirtualCardWithHttpInfo(cardId, virtualCardId);
    }

    @Override
    public Mono<ResponseEntity<VirtualCardDTO>> updateVirtualCard(Long cardId, Long virtualCardId, VirtualCardRequest virtualCardRequest) {
        String idempotencyKey = UUID.randomUUID().toString();
        return virtualCardsApi.updateVirtualCardWithHttpInfo(cardId, virtualCardId, cardsMapper.virtualCardRequestToDto(virtualCardRequest), idempotencyKey);
    }


}

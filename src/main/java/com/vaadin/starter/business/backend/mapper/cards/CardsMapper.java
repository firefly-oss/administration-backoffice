package com.vaadin.starter.business.backend.mapper.cards;

import com.catalis.core.banking.cards.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.cards.*;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between cards request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface CardsMapper {

    /**
     * Convert a BINRequest to a BINDTO.
     *
     * @param request the BINRequest to convert
     * @return the corresponding BINDTO
     */
    BINDTO binRequestToDto(BINRequest request);

    /**
     * Convert a BINDTO to a BINRequest.
     *
     * @param dto the BINDTO to convert
     * @return the corresponding BINRequest
     */
    BINRequest dtoToBinRequest(BINDTO dto);

    /**
     * Convert a CardRequest to a CardDTO.
     *
     * @param request the CardRequest to convert
     * @return the corresponding CardDTO
     */
    CardDTO cardRequestToDto(CardRequest request);

    /**
     * Convert a CardDTO to a CardRequest.
     *
     * @param dto the CardDTO to convert
     * @return the corresponding CardRequest
     */
    CardRequest dtoToCardRequest(CardDTO dto);

    /**
     * Convert a CardAcquirerRequest to a CardAcquirerDTO.
     *
     * @param request the CardAcquirerRequest to convert
     * @return the corresponding CardAcquirerDTO
     */
    CardAcquirerDTO cardAcquirerRequestToDto(CardAcquirerRequest request);

    /**
     * Convert a CardAcquirerDTO to a CardAcquirerRequest.
     *
     * @param dto the CardAcquirerDTO to convert
     * @return the corresponding CardAcquirerRequest
     */
    CardAcquirerRequest dtoToCardAcquirerRequest(CardAcquirerDTO dto);

    /**
     * Convert a CardActivityRequest to a CardActivityDTO.
     *
     * @param request the CardActivityRequest to convert
     * @return the corresponding CardActivityDTO
     */
    CardActivityDTO cardActivityRequestToDto(CardActivityRequest request);

    /**
     * Convert a CardActivityDTO to a CardActivityRequest.
     *
     * @param dto the CardActivityDTO to convert
     * @return the corresponding CardActivityRequest
     */
    CardActivityRequest dtoToCardActivityRequest(CardActivityDTO dto);

    /**
     * Convert a CardBalanceRequest to a CardBalanceDTO.
     *
     * @param request the CardBalanceRequest to convert
     * @return the corresponding CardBalanceDTO
     */
    CardBalanceDTO cardBalanceRequestToDto(CardBalanceRequest request);

    /**
     * Convert a CardBalanceDTO to a CardBalanceRequest.
     *
     * @param dto the CardBalanceDTO to convert
     * @return the corresponding CardBalanceRequest
     */
    CardBalanceRequest dtoToCardBalanceRequest(CardBalanceDTO dto);

    /**
     * Convert a CardConfigurationRequest to a CardConfigurationDTO.
     *
     * @param request the CardConfigurationRequest to convert
     * @return the corresponding CardConfigurationDTO
     */
    CardConfigurationDTO cardConfigurationRequestToDto(CardConfigurationRequest request);

    /**
     * Convert a CardConfigurationDTO to a CardConfigurationRequest.
     *
     * @param dto the CardConfigurationDTO to convert
     * @return the corresponding CardConfigurationRequest
     */
    CardConfigurationRequest dtoToCardConfigurationRequest(CardConfigurationDTO dto);

    /**
     * Convert a CardDisputeRequest to a CardDisputeDTO.
     *
     * @param request the CardDisputeRequest to convert
     * @return the corresponding CardDisputeDTO
     */
    CardDisputeDTO cardDisputeRequestToDto(CardDisputeRequest request);

    /**
     * Convert a CardDisputeDTO to a CardDisputeRequest.
     *
     * @param dto the CardDisputeDTO to convert
     * @return the corresponding CardDisputeRequest
     */
    CardDisputeRequest dtoToCardDisputeRequest(CardDisputeDTO dto);

    /**
     * Convert a CardEnrollmentRequest to a CardEnrollmentDTO.
     *
     * @param request the CardEnrollmentRequest to convert
     * @return the corresponding CardEnrollmentDTO
     */
    CardEnrollmentDTO cardEnrollmentRequestToDto(CardEnrollmentRequest request);

    /**
     * Convert a CardEnrollmentDTO to a CardEnrollmentRequest.
     *
     * @param dto the CardEnrollmentDTO to convert
     * @return the corresponding CardEnrollmentRequest
     */
    CardEnrollmentRequest dtoToCardEnrollmentRequest(CardEnrollmentDTO dto);

    /**
     * Convert a CardGatewayRequest to a CardGatewayDTO.
     *
     * @param request the CardGatewayRequest to convert
     * @return the corresponding CardGatewayDTO
     */
    CardGatewayDTO cardGatewayRequestToDto(CardGatewayRequest request);

    /**
     * Convert a CardGatewayDTO to a CardGatewayRequest.
     *
     * @param dto the CardGatewayDTO to convert
     * @return the corresponding CardGatewayRequest
     */
    CardGatewayRequest dtoToCardGatewayRequest(CardGatewayDTO dto);

    /**
     * Convert a CardInterestRequest to a CardInterestDTO.
     *
     * @param request the CardInterestRequest to convert
     * @return the corresponding CardInterestDTO
     */
    CardInterestDTO cardInterestRequestToDto(CardInterestRequest request);

    /**
     * Convert a CardInterestDTO to a CardInterestRequest.
     *
     * @param dto the CardInterestDTO to convert
     * @return the corresponding CardInterestRequest
     */
    CardInterestRequest dtoToCardInterestRequest(CardInterestDTO dto);

    /**
     * Convert a CardLimitRequest to a CardLimitDTO.
     *
     * @param request the CardLimitRequest to convert
     * @return the corresponding CardLimitDTO
     */
    CardLimitDTO cardLimitRequestToDto(CardLimitRequest request);

    /**
     * Convert a CardLimitDTO to a CardLimitRequest.
     *
     * @param dto the CardLimitDTO to convert
     * @return the corresponding CardLimitRequest
     */
    CardLimitRequest dtoToCardLimitRequest(CardLimitDTO dto);

    /**
     * Convert a CardMerchantRequest to a CardMerchantDTO.
     *
     * @param request the CardMerchantRequest to convert
     * @return the corresponding CardMerchantDTO
     */
    CardMerchantDTO cardMerchantRequestToDto(CardMerchantRequest request);

    /**
     * Convert a CardMerchantDTO to a CardMerchantRequest.
     *
     * @param dto the CardMerchantDTO to convert
     * @return the corresponding CardMerchantRequest
     */
    CardMerchantRequest dtoToCardMerchantRequest(CardMerchantDTO dto);

    /**
     * Convert a CardNetworkRequest to a CardNetworkDTO.
     *
     * @param request the CardNetworkRequest to convert
     * @return the corresponding CardNetworkDTO
     */
    CardNetworkDTO cardNetworkRequestToDto(CardNetworkRequest request);

    /**
     * Convert a CardNetworkDTO to a CardNetworkRequest.
     *
     * @param dto the CardNetworkDTO to convert
     * @return the corresponding CardNetworkRequest
     */
    CardNetworkRequest dtoToCardNetworkRequest(CardNetworkDTO dto);

    /**
     * Convert a CardPaymentRequest to a CardPaymentDTO.
     *
     * @param request the CardPaymentRequest to convert
     * @return the corresponding CardPaymentDTO
     */
    CardPaymentDTO cardPaymentRequestToDto(CardPaymentRequest request);

    /**
     * Convert a CardPaymentDTO to a CardPaymentRequest.
     *
     * @param dto the CardPaymentDTO to convert
     * @return the corresponding CardPaymentRequest
     */
    CardPaymentRequest dtoToCardPaymentRequest(CardPaymentDTO dto);

    /**
     * Convert a CardProgramRequest to a CardProgramDTO.
     *
     * @param request the CardProgramRequest to convert
     * @return the corresponding CardProgramDTO
     */
    CardProgramDTO cardProgramRequestToDto(CardProgramRequest request);

    /**
     * Convert a CardProgramDTO to a CardProgramRequest.
     *
     * @param dto the CardProgramDTO to convert
     * @return the corresponding CardProgramRequest
     */
    CardProgramRequest dtoToCardProgramRequest(CardProgramDTO dto);

    /**
     * Convert a CardPromotionRequest to a CardPromotionDTO.
     *
     * @param request the CardPromotionRequest to convert
     * @return the corresponding CardPromotionDTO
     */
    CardPromotionDTO cardPromotionRequestToDto(CardPromotionRequest request);

    /**
     * Convert a CardPromotionDTO to a CardPromotionRequest.
     *
     * @param dto the CardPromotionDTO to convert
     * @return the corresponding CardPromotionRequest
     */
    CardPromotionRequest dtoToCardPromotionRequest(CardPromotionDTO dto);

    /**
     * Convert a CardProviderRequest to a CardProviderDTO.
     *
     * @param request the CardProviderRequest to convert
     * @return the corresponding CardProviderDTO
     */
    CardProviderDTO cardProviderRequestToDto(CardProviderRequest request);

    /**
     * Convert a CardProviderDTO to a CardProviderRequest.
     *
     * @param dto the CardProviderDTO to convert
     * @return the corresponding CardProviderRequest
     */
    CardProviderRequest dtoToCardProviderRequest(CardProviderDTO dto);

    /**
     * Convert a CardSecurityRequest to a CardSecurityDTO.
     *
     * @param request the CardSecurityRequest to convert
     * @return the corresponding CardSecurityDTO
     */
    CardSecurityDTO cardSecurityRequestToDto(CardSecurityRequest request);

    /**
     * Convert a CardSecurityDTO to a CardSecurityRequest.
     *
     * @param dto the CardSecurityDTO to convert
     * @return the corresponding CardSecurityRequest
     */
    CardSecurityRequest dtoToCardSecurityRequest(CardSecurityDTO dto);

    /**
     * Convert a CardTerminalRequest to a CardTerminalDTO.
     *
     * @param request the CardTerminalRequest to convert
     * @return the corresponding CardTerminalDTO
     */
    CardTerminalDTO cardTerminalRequestToDto(CardTerminalRequest request);

    /**
     * Convert a CardTerminalDTO to a CardTerminalRequest.
     *
     * @param dto the CardTerminalDTO to convert
     * @return the corresponding CardTerminalRequest
     */
    CardTerminalRequest dtoToCardTerminalRequest(CardTerminalDTO dto);

    /**
     * Convert a CardTransactionRequest to a CardTransactionDTO.
     *
     * @param request the CardTransactionRequest to convert
     * @return the corresponding CardTransactionDTO
     */
    CardTransactionDTO cardTransactionRequestToDto(CardTransactionRequest request);

    /**
     * Convert a CardTransactionDTO to a CardTransactionRequest.
     *
     * @param dto the CardTransactionDTO to convert
     * @return the corresponding CardTransactionRequest
     */
    CardTransactionRequest dtoToCardTransactionRequest(CardTransactionDTO dto);

    /**
     * Convert an IssuerRequest to an IssuerDTO.
     *
     * @param request the IssuerRequest to convert
     * @return the corresponding IssuerDTO
     */
    IssuerDTO issuerRequestToDto(IssuerRequest request);

    /**
     * Convert an IssuerDTO to an IssuerRequest.
     *
     * @param dto the IssuerDTO to convert
     * @return the corresponding IssuerRequest
     */
    IssuerRequest dtoToIssuerRequest(IssuerDTO dto);

    /**
     * Convert a PhysicalCardRequest to a PhysicalCardDTO.
     *
     * @param request the PhysicalCardRequest to convert
     * @return the corresponding PhysicalCardDTO
     */
    PhysicalCardDTO physicalCardRequestToDto(PhysicalCardRequest request);

    /**
     * Convert a PhysicalCardDTO to a PhysicalCardRequest.
     *
     * @param dto the PhysicalCardDTO to convert
     * @return the corresponding PhysicalCardRequest
     */
    PhysicalCardRequest dtoToPhysicalCardRequest(PhysicalCardDTO dto);

    /**
     * Convert a VirtualCardRequest to a VirtualCardDTO.
     *
     * @param request the VirtualCardRequest to convert
     * @return the corresponding VirtualCardDTO
     */
    VirtualCardDTO virtualCardRequestToDto(VirtualCardRequest request);

    /**
     * Convert a VirtualCardDTO to a VirtualCardRequest.
     *
     * @param dto the VirtualCardDTO to convert
     * @return the corresponding VirtualCardRequest
     */
    VirtualCardRequest dtoToVirtualCardRequest(VirtualCardDTO dto);

    /**
     * Convert a CardFilterRequest to a FilterRequestCardDTO.
     *
     * @param request the CardFilterRequest to convert
     * @return the corresponding FilterRequestCardDTO
     */
    FilterRequestCardDTO cardFilterRequestToDto(CardFilterRequest request);

    /**
     * Convert a FilterRequestCardDTO to a CardFilterRequest.
     *
     * @param dto the FilterRequestCardDTO to convert
     * @return the corresponding CardFilterRequest
     */
    CardFilterRequest dtoToCardFilterRequest(FilterRequestCardDTO dto);
}

package com.vaadin.starter.business.backend.mapper.accounts;

import com.catalis.core.banking.accounts.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.accounts.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for converting between account request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface AccountsMapper {

    AccountsMapper INSTANCE = Mappers.getMapper(AccountsMapper.class);

    /**
     * Convert an AccountRequest to an AccountDTO.
     *
     * @param request the AccountRequest to convert
     * @return the corresponding AccountDTO
     */
    AccountDTO accountRequestToDto(AccountRequest request);

    /**
     * Convert an AccountDTO to an AccountRequest.
     *
     * @param dto the AccountDTO to convert
     * @return the corresponding AccountRequest
     */
    AccountRequest dtoToAccountRequest(AccountDTO dto);

    /**
     * Convert an AccountFilterRequest to a FilterRequestAccountDTO.
     *
     * @param request the AccountFilterRequest to convert
     * @return the corresponding FilterRequestAccountDTO
     */
    FilterRequestAccountDTO accountFilterRequestToDto(AccountFilterRequest request);

    /**
     * Convert a FilterRequestAccountDTO to an AccountFilterRequest.
     *
     * @param dto the FilterRequestAccountDTO to convert
     * @return the corresponding AccountFilterRequest
     */
    AccountFilterRequest dtoToAccountFilterRequest(FilterRequestAccountDTO dto);

    /**
     * Convert an AccountBalanceRequest to an AccountBalanceDTO.
     *
     * @param request the AccountBalanceRequest to convert
     * @return the corresponding AccountBalanceDTO
     */
    AccountBalanceDTO accountBalanceRequestToDto(AccountBalanceRequest request);

    /**
     * Convert an AccountBalanceDTO to an AccountBalanceRequest.
     *
     * @param dto the AccountBalanceDTO to convert
     * @return the corresponding AccountBalanceRequest
     */
    AccountBalanceRequest dtoToAccountBalanceRequest(AccountBalanceDTO dto);

    /**
     * Convert an AccountNotificationRequest to an AccountNotificationDTO.
     *
     * @param request the AccountNotificationRequest to convert
     * @return the corresponding AccountNotificationDTO
     */
    AccountNotificationDTO accountNotificationRequestToDto(AccountNotificationRequest request);

    /**
     * Convert an AccountNotificationDTO to an AccountNotificationRequest.
     *
     * @param dto the AccountNotificationDTO to convert
     * @return the corresponding AccountNotificationRequest
     */
    AccountNotificationRequest dtoToAccountNotificationRequest(AccountNotificationDTO dto);

    /**
     * Convert an AccountNotificationFilterRequest to a FilterRequestAccountNotificationDTO.
     *
     * @param request the AccountNotificationFilterRequest to convert
     * @return the corresponding FilterRequestAccountNotificationDTO
     */
    FilterRequestAccountNotificationDTO accountNotificationFilterRequestToDto(AccountNotificationFilterRequest request);

    /**
     * Convert a FilterRequestAccountNotificationDTO to an AccountNotificationFilterRequest.
     *
     * @param dto the FilterRequestAccountNotificationDTO to convert
     * @return the corresponding AccountNotificationFilterRequest
     */
    AccountNotificationFilterRequest dtoToAccountNotificationFilterRequest(FilterRequestAccountNotificationDTO dto);

    /**
     * Convert an AccountParameterRequest to an AccountParameterDTO.
     *
     * @param request the AccountParameterRequest to convert
     * @return the corresponding AccountParameterDTO
     */
    AccountParameterDTO accountParameterRequestToDto(AccountParameterRequest request);

    /**
     * Convert an AccountParameterDTO to an AccountParameterRequest.
     *
     * @param dto the AccountParameterDTO to convert
     * @return the corresponding AccountParameterRequest
     */
    AccountParameterRequest dtoToAccountParameterRequest(AccountParameterDTO dto);

    /**
     * Convert an AccountProviderRequest to an AccountProviderDTO.
     *
     * @param request the AccountProviderRequest to convert
     * @return the corresponding AccountProviderDTO
     */
    AccountProviderDTO accountProviderRequestToDto(AccountProviderRequest request);

    /**
     * Convert an AccountProviderDTO to an AccountProviderRequest.
     *
     * @param dto the AccountProviderDTO to convert
     * @return the corresponding AccountProviderRequest
     */
    AccountProviderRequest dtoToAccountProviderRequest(AccountProviderDTO dto);

    /**
     * Convert an AccountRestrictionRequest to an AccountRestrictionDTO.
     *
     * @param request the AccountRestrictionRequest to convert
     * @return the corresponding AccountRestrictionDTO
     */
    AccountRestrictionDTO accountRestrictionRequestToDto(AccountRestrictionRequest request);

    /**
     * Convert an AccountRestrictionDTO to an AccountRestrictionRequest.
     *
     * @param dto the AccountRestrictionDTO to convert
     * @return the corresponding AccountRestrictionRequest
     */
    AccountRestrictionRequest dtoToAccountRestrictionRequest(AccountRestrictionDTO dto);

    /**
     * Convert an AccountRestrictionFilterRequest to a FilterRequestDTO.
     *
     * @param request the AccountRestrictionFilterRequest to convert
     * @return the corresponding FilterRequestDTO
     */
    FilterRequestAccountRestrictionDTO accountRestrictionFilterRequestToDto(AccountRestrictionFilterRequest request);

    /**
     * Convert a FilterRequestDTO to an AccountRestrictionFilterRequest.
     *
     * @param dto the FilterRequestDTO to convert
     * @return the corresponding AccountRestrictionFilterRequest
     */
    AccountRestrictionFilterRequest dtoToAccountRestrictionFilterRequest(FilterRequestAccountRestrictionDTO dto);

    /**
     * Convert an AccountSpaceProviderRequest to an AccountProviderDTO.
     *
     * @param request the AccountSpaceProviderRequest to convert
     * @return the corresponding AccountProviderDTO
     */
    AccountProviderDTO accountSpaceProviderRequestToDto(AccountSpaceProviderRequest request);

    /**
     * Convert an AccountProviderDTO to an AccountSpaceProviderRequest.
     *
     * @param dto the AccountProviderDTO to convert
     * @return the corresponding AccountSpaceProviderRequest
     */
    AccountSpaceProviderRequest dtoToAccountSpaceProviderRequest(AccountProviderDTO dto);

    /**
     * Convert an AccountSpaceFilterRequest to a FilterRequestAccountSpaceDTO.
     *
     * @param request the AccountSpaceFilterRequest to convert
     * @return the corresponding FilterRequestAccountSpaceDTO
     */
    FilterRequestAccountSpaceDTO accountSpaceFilterRequestToDto(AccountSpaceFilterRequest request);

    /**
     * Convert a FilterRequestAccountSpaceDTO to an AccountSpaceFilterRequest.
     *
     * @param dto the FilterRequestAccountSpaceDTO to convert
     * @return the corresponding AccountSpaceFilterRequest
     */
    AccountSpaceFilterRequest dtoToAccountSpaceFilterRequest(FilterRequestAccountSpaceDTO dto);
}

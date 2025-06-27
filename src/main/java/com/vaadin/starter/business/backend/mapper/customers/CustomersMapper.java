package com.vaadin.starter.business.backend.mapper.customers;

import com.catalis.common.customer.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for converting between request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface CustomersMapper {

    CustomersMapper INSTANCE = Mappers.getMapper(CustomersMapper.class);

    /**
     * Convert a LegalPersonRequest to a FilterRequestLegalPersonDTO.
     *
     * @param request the LegalPersonRequest to convert
     * @return the corresponding FilterRequestLegalPersonDTO
     */
    FilterRequestLegalPersonDTO legalPersonRequestToDto(LegalPersonRequest request);

    /**
     * Convert a FilterRequestLegalPersonDTO to a LegalPersonRequest.
     *
     * @param dto the FilterRequestLegalPersonDTO to convert
     * @return the corresponding LegalPersonRequest
     */
    LegalPersonRequest dtoToLegalPersonRequest(FilterRequestLegalPersonDTO dto);

    /**
     * Convert a NaturalPersonRequest to a FilterRequestNaturalPersonDTO.
     *
     * @param request the NaturalPersonRequest to convert
     * @return the corresponding FilterRequestNaturalPersonDTO
     */
    FilterRequestNaturalPersonDTO naturalPersonRequestToDto(NaturalPersonRequest request);

    /**
     * Convert a FilterRequestNaturalPersonDTO to a NaturalPersonRequest.
     *
     * @param dto the FilterRequestNaturalPersonDTO to convert
     * @return the corresponding NaturalPersonRequest
     */
    NaturalPersonRequest dtoToNaturalPersonRequest(FilterRequestNaturalPersonDTO dto);

    /**
     * Convert a PartyRequest to a PartyDTO.
     *
     * @param request the PartyRequest to convert
     * @return the corresponding PartyDTO
     */
    PartyDTO partyRequestToDto(PartyRequest request);

    /**
     * Convert a PartyDTO to a PartyRequest.
     *
     * @param dto the PartyDTO to convert
     * @return the corresponding PartyRequest
     */
    PartyRequest dtoToPartyRequest(PartyDTO dto);

    /**
     * Convert a LegalPersonRequest to a LegalPersonDTO.
     *
     * @param request the LegalPersonRequest to convert
     * @return the corresponding LegalPersonDTO
     */
    LegalPersonDTO legalPersonRequestToLegalPersonDto(LegalPersonRequest request);

    /**
     * Convert a NaturalPersonRequest to a NaturalPersonDTO.
     *
     * @param request the NaturalPersonRequest to convert
     * @return the corresponding NaturalPersonDTO
     */
    NaturalPersonDTO naturalPersonRequestToNaturalPersonDto(NaturalPersonRequest request);

    /**
     * Convert an AddressRequest to an AddressDTO.
     *
     * @param request the AddressRequest to convert
     * @return the corresponding AddressDTO
     */
    AddressDTO addressRequestToDto(AddressRequest request);

    /**
     * Convert an AddressDTO to an AddressRequest.
     *
     * @param dto the AddressDTO to convert
     * @return the corresponding AddressRequest
     */
    AddressRequest dtoToAddressRequest(AddressDTO dto);

    /**
     * Convert a ConsentRequest to a ConsentDTO.
     *
     * @param request the ConsentRequest to convert
     * @return the corresponding ConsentDTO
     */
    ConsentDTO consentRequestToDto(ConsentRequest request);

    /**
     * Convert a ConsentDTO to a ConsentRequest.
     *
     * @param dto the ConsentDTO to convert
     * @return the corresponding ConsentRequest
     */
    ConsentRequest dtoToConsentRequest(ConsentDTO dto);

    /**
     * Convert an EmailRequest to an EmailDTO.
     *
     * @param request the EmailRequest to convert
     * @return the corresponding EmailDTO
     */
    EmailDTO emailRequestToDto(EmailRequest request);

    /**
     * Convert an EmailDTO to an EmailRequest.
     *
     * @param dto the EmailDTO to convert
     * @return the corresponding EmailRequest
     */
    EmailRequest dtoToEmailRequest(EmailDTO dto);

    /**
     * Convert an IdentityDocumentRequest to an IdentityDocumentDTO.
     *
     * @param request the IdentityDocumentRequest to convert
     * @return the corresponding IdentityDocumentDTO
     */
    IdentityDocumentDTO identityDocumentRequestToDto(IdentityDocumentRequest request);

    /**
     * Convert an IdentityDocumentDTO to an IdentityDocumentRequest.
     *
     * @param dto the IdentityDocumentDTO to convert
     * @return the corresponding IdentityDocumentRequest
     */
    IdentityDocumentRequest dtoToIdentityDocumentRequest(IdentityDocumentDTO dto);

    /**
     * Convert a RolePermissionRequest to an Object (RolePermissionDTO).
     *
     * @param request the RolePermissionRequest to convert
     * @return the corresponding RolePermissionDTO as Object
     */
    Object rolePermissionRequestToDto(RolePermissionRequest request);

    /**
     * Convert an Object (RolePermissionDTO) to a RolePermissionRequest.
     *
     * @param dto the RolePermissionDTO as Object to convert
     * @return the corresponding RolePermissionRequest
     */
    RolePermissionRequest dtoToRolePermissionRequest(Object dto);

    /**
     * Converts a RolePermissionRequest object to an InternalRolePermissionDTO.
     *
     * @param request the RolePermissionRequest object to convert
     * @return the corresponding InternalRolePermissionDTO
     */
    InternalRolePermissionDTO internalRolePermissionRequestToDto(RolePermissionRequest request);

    /**
     * Converts a RoleRequest object to an InternalRoleDTO.
     *
     * @param request the RoleRequest object to convert
     * @return the corresponding InternalRoleDTO
     */
    InternalRoleDTO internalRoleRequestToDto(RoleRequest request);

    /**
     * Convert a PartyEconomicActivityRequest to a PartyEconomicActivityDTO.
     *
     * @param request the PartyEconomicActivityRequest to convert
     * @return the corresponding PartyEconomicActivityDTO
     */
    PartyEconomicActivityDTO partyEconomicActivityRequestToDto(PartyEconomicActivityRequest request);

    /**
     * Convert a PartyEconomicActivityDTO to a PartyEconomicActivityRequest.
     *
     * @param dto the PartyEconomicActivityDTO to convert
     * @return the corresponding PartyEconomicActivityRequest
     */
    PartyEconomicActivityRequest dtoToPartyEconomicActivityRequest(PartyEconomicActivityDTO dto);

    /**
     * Convert a PartyProviderRequest to a PartyProviderDTO.
     *
     * @param request the PartyProviderRequest to convert
     * @return the corresponding PartyProviderDTO
     */
    PartyProviderDTO partyProviderRequestToDto(PartyProviderRequest request);

    /**
     * Convert a PartyProviderDTO to a PartyProviderRequest.
     *
     * @param dto the PartyProviderDTO to convert
     * @return the corresponding PartyProviderRequest
     */
    PartyProviderRequest dtoToPartyProviderRequest(PartyProviderDTO dto);

    /**
     * Convert a PartyRelationshipRequest to a PartyRelationshipDTO.
     *
     * @param request the PartyRelationshipRequest to convert
     * @return the corresponding PartyRelationshipDTO
     */
    PartyRelationshipDTO partyRelationshipRequestToDto(PartyRelationshipRequest request);

    /**
     * Convert a PartyRelationshipDTO to a PartyRelationshipRequest.
     *
     * @param dto the PartyRelationshipDTO to convert
     * @return the corresponding PartyRelationshipRequest
     */
    PartyRelationshipRequest dtoToPartyRelationshipRequest(PartyRelationshipDTO dto);

    /**
     * Convert a PartyStatusRequest to a PartyStatusDTO.
     *
     * @param request the PartyStatusRequest to convert
     * @return the corresponding PartyStatusDTO
     */
    PartyStatusDTO partyStatusRequestToDto(PartyStatusRequest request);

    /**
     * Convert a PartyStatusDTO to a PartyStatusRequest.
     *
     * @param dto the PartyStatusDTO to convert
     * @return the corresponding PartyStatusRequest
     */
    PartyStatusRequest dtoToPartyStatusRequest(PartyStatusDTO dto);

    /**
     * Convert a PepRequest to a PepDTO.
     *
     * @param request the PepRequest to convert
     * @return the corresponding PepDTO
     */
    PepDTO pepRequestToDto(PepRequest request);

    /**
     * Convert a PepDTO to a PepRequest.
     *
     * @param dto the PepDTO to convert
     * @return the corresponding PepRequest
     */
    PepRequest dtoToPepRequest(PepDTO dto);

    /**
     * Convert a PermissionAuditRequest to a PermissionAuditDTO.
     *
     * @param request the PermissionAuditRequest to convert
     * @return the corresponding PermissionAuditDTO
     */
    PermissionAuditDTO permissionAuditRequestToDto(PermissionAuditRequest request);

    /**
     * Convert a PermissionAuditDTO to a PermissionAuditRequest.
     *
     * @param dto the PermissionAuditDTO to convert
     * @return the corresponding PermissionAuditRequest
     */
    PermissionAuditRequest dtoToPermissionAuditRequest(PermissionAuditDTO dto);

    /**
     * Convert a PermissionRequest to a PermissionDTO.
     *
     * @param request the PermissionRequest to convert
     * @return the corresponding PermissionDTO
     */
    PermissionDTO permissionRequestToDto(PermissionRequest request);

    /**
     * Convert a PermissionDTO to a PermissionRequest.
     *
     * @param dto the PermissionDTO to convert
     * @return the corresponding PermissionRequest
     */
    PermissionRequest dtoToPermissionRequest(PermissionDTO dto);

    /**
     * Convert a PermissionTypeRequest to a PermissionTypeDTO.
     *
     * @param request the PermissionTypeRequest to convert
     * @return the corresponding PermissionTypeDTO
     */
    PermissionTypeDTO permissionTypeRequestToDto(PermissionTypeRequest request);

    /**
     * Convert a PermissionTypeDTO to a PermissionTypeRequest.
     *
     * @param dto the PermissionTypeDTO to convert
     * @return the corresponding PermissionTypeRequest
     */
    PermissionTypeRequest dtoToPermissionTypeRequest(PermissionTypeDTO dto);

    /**
     * Convert a PhoneRequest to a PhoneDTO.
     *
     * @param request the PhoneRequest to convert
     * @return the corresponding PhoneDTO
     */
    PhoneDTO phoneRequestToDto(PhoneRequest request);

    /**
     * Convert a PhoneDTO to a PhoneRequest.
     *
     * @param dto the PhoneDTO to convert
     * @return the corresponding PhoneRequest
     */
    PhoneRequest dtoToPhoneRequest(PhoneDTO dto);

    /**
     * Convert a ResourceRequest to a ResourceDTO.
     *
     * @param request the ResourceRequest to convert
     * @return the corresponding ResourceDTO
     */
    ResourceDTO resourceRequestToDto(ResourceRequest request);

    /**
     * Convert a ResourceDTO to a ResourceRequest.
     *
     * @param dto the ResourceDTO to convert
     * @return the corresponding ResourceRequest
     */
    ResourceRequest dtoToResourceRequest(ResourceDTO dto);

    /**
     * Convert a UserInternalRoleRequest to a UserInternalRoleDTO.
     *
     * @param request the UserInternalRoleRequest to convert
     * @return the corresponding UserInternalRoleDTO
     */
    UserInternalRoleDTO userInternalRoleRequestToDto(UserInternalRoleRequest request);

    /**
     * Convert a UserInternalRoleDTO to a UserInternalRoleRequest.
     *
     * @param dto the UserInternalRoleDTO to convert
     * @return the corresponding UserInternalRoleRequest
     */
    UserInternalRoleRequest dtoToUserInternalRoleRequest(UserInternalRoleDTO dto);

    /**
     * Convert a UserPermissionRequest to a UserPermissionDTO.
     *
     * @param request the UserPermissionRequest to convert
     * @return the corresponding UserPermissionDTO
     */
    UserPermissionDTO userPermissionRequestToDto(UserPermissionRequest request);

    /**
     * Convert a UserPermissionDTO to a UserPermissionRequest.
     *
     * @param dto the UserPermissionDTO to convert
     * @return the corresponding UserPermissionRequest
     */
    UserPermissionRequest dtoToUserPermissionRequest(UserPermissionDTO dto);
}

package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.common.customer.sdk.api.*;
import com.catalis.common.customer.sdk.invoker.ApiClient;
import com.catalis.common.customer.sdk.model.*;
import com.vaadin.starter.business.backend.mapper.customers.CustomersMapper;
import com.vaadin.starter.business.backend.sdks.services.CustomersService;
import com.vaadin.starter.business.backend.sdks.services.rest.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class CustomersClient implements CustomersService {

    private final PartyApi partyApi;
    private final LegalPersonApi legalPersonApi;
    private final NaturalPersonApi naturalPersonApi;
    private final AddressApi addressApi;
    private final ConsentApi consentApi;
    private final EmailApi emailApi;
    private final IdentityDocumentApi identityDocumentApi;
    private final InternalRolePermissionsApi internalRolePermissionsApi;
    private final CustomersMapper customersMapper;
    private final InternalRolesApi internalRolesApi;
    private final PartyEconomicActivityApi partyEconomicActivityApi;
    private final PartyProviderApi partyProviderApi;
    private final PartyRelationshipApi partyRelationshipApi;
    private final PartyStatusApi partyStatusApi;
    private final PepApi pepApi;
    private final PermissionAuditApi permissionAuditApi;
    private final PhoneApi phoneApi;
    private final ResourcesApi resourcesApi;
    private final PermissionsApi permissionsApi;
    private final PermissionTypesApi permissionTypesApi;
    private final UserInternalRolesApi userInternalRolesApi;
    private final UserPermissionsApi userPermissionsApi;

    @Autowired
    public CustomersClient(ApiClient apiClient, CustomersMapper customersMapper) {
        this.partyApi = new PartyApi(apiClient);
        this.legalPersonApi = new LegalPersonApi(apiClient);
        this.naturalPersonApi = new NaturalPersonApi(apiClient);
        this.addressApi = new AddressApi(apiClient);
        this.consentApi = new ConsentApi(apiClient);
        this.emailApi = new EmailApi(apiClient);
        this.identityDocumentApi = new IdentityDocumentApi(apiClient);
        this.internalRolePermissionsApi = new InternalRolePermissionsApi(apiClient);
        this.internalRolesApi = new InternalRolesApi(apiClient);
        this.partyEconomicActivityApi = new PartyEconomicActivityApi(apiClient);
        this.partyProviderApi = new PartyProviderApi(apiClient);
        this.partyRelationshipApi = new PartyRelationshipApi(apiClient);
        this.partyStatusApi = new PartyStatusApi(apiClient);
        this.pepApi = new PepApi(apiClient);
        this.permissionAuditApi = new PermissionAuditApi(apiClient);
        this.phoneApi = new PhoneApi(apiClient);
        this.resourcesApi = new ResourcesApi(apiClient);
        this.permissionsApi = new PermissionsApi(apiClient);
        this.permissionTypesApi = new PermissionTypesApi(apiClient);
        this.userInternalRolesApi = new UserInternalRolesApi(apiClient);
        this.userPermissionsApi = new UserPermissionsApi(apiClient);
        this.customersMapper = customersMapper;
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterLegalPerson(LegalPersonRequest legalPersonRequest) {
        return partyApi.filterLegalPersonWithHttpInfo(customersMapper.legalPersonRequestToDto(legalPersonRequest));
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterNaturalPerson(NaturalPersonRequest naturalPersonRequest) {
        return partyApi.filterNaturalPersonWithHttpInfo(customersMapper.naturalPersonRequestToDto(naturalPersonRequest));
    }

    @Override
    public Mono<ResponseEntity<PartyDTO>> createParty(PartyRequest partyRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyApi.createPartyWithHttpInfo(customersMapper.partyRequestToDto(partyRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteParty(Long id) {
        return partyApi.deletePartyWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PartyDTO>> getPartyById(Long id) {
        return partyApi.getPartyByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PartyDTO>> updateParty(Long id, PartyRequest partyRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyApi.updatePartyWithHttpInfo(id, customersMapper.partyRequestToDto(partyRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<LegalPersonDTO>> createLegalPerson(Long partyId, LegalPersonRequest legalPersonRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return legalPersonApi.createLegalPersonWithHttpInfo(partyId,
                customersMapper.legalPersonRequestToLegalPersonDto(legalPersonRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteLegalPerson(Long id) {
        return legalPersonApi.deleteLegalPersonWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<LegalPersonDTO>> getLegalPersonById(Long id) {
        return legalPersonApi.getLegalPersonWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<NaturalPersonDTO>> createNaturalPerson(Long partyId, NaturalPersonRequest naturalPersonRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return naturalPersonApi.createNaturalPersonWithHttpInfo(partyId,
                customersMapper.naturalPersonRequestToNaturalPersonDto(naturalPersonRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteNaturalPerson(Long id) {
        return naturalPersonApi.deleteNaturalPersonWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<NaturalPersonDTO>> getNaturalPersonById(Long id) {
        return naturalPersonApi.getNaturalPersonWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<AddressDTO>> createAddress(AddressRequest addressRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return addressApi.createAddressWithHttpInfo(addressRequest.getPartyId(),
                customersMapper.addressRequestToDto(addressRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteAddress(Long id, Long partyId) {
        return addressApi.deleteAddressWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<AddressDTO>> getAddressById(Long id, Long partyId) {
        return addressApi.getAddressWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<AddressDTO>> updateAddress(Long id, AddressRequest addressRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return addressApi.updateAddressWithHttpInfo(id, addressRequest.getPartyId(),
                customersMapper.addressRequestToDto(addressRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listAddressesByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return addressApi.listAddressesWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<ConsentDTO>> createConsent(ConsentRequest consentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return consentApi.createConsentWithHttpInfo(consentRequest.getPartyId(),
                customersMapper.consentRequestToDto(consentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteConsent(Long id, Long partyId) {
        return consentApi.deleteConsentWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<ConsentDTO>> getConsentById(Long id, Long partyId) {
        return consentApi.getConsentWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<ConsentDTO>> updateConsent(Long id, ConsentRequest consentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return consentApi.updateConsentWithHttpInfo(id, consentRequest.getPartyId(),
                customersMapper.consentRequestToDto(consentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listConsentsByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return consentApi.listConsentsWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<EmailDTO>> createEmail(EmailRequest emailRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return emailApi.createEmailWithHttpInfo(emailRequest.getPartyId(),
                customersMapper.emailRequestToDto(emailRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteEmail(Long id, Long partyId) {
        return emailApi.deleteEmailWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<EmailDTO>> updateEmail(Long id, EmailRequest emailRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return emailApi.updateEmailWithHttpInfo(id, emailRequest.getPartyId(),
                customersMapper.emailRequestToDto(emailRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listEmailsByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return emailApi.listEmailsWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<IdentityDocumentDTO>> createIdentityDocument(IdentityDocumentRequest identityDocumentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return identityDocumentApi.createIdentityDocumentWithHttpInfo(identityDocumentRequest.getPartyId(),
                customersMapper.identityDocumentRequestToDto(identityDocumentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteIdentityDocument(Long id, Long partyId) {
        return identityDocumentApi.deleteIdentityDocumentWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<IdentityDocumentDTO>> updateIdentityDocument(Long id, IdentityDocumentRequest identityDocumentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return identityDocumentApi.updateIdentityDocumentWithHttpInfo(id, identityDocumentRequest.getPartyId(),
                customersMapper.identityDocumentRequestToDto(identityDocumentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listIdentityDocumentsByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return identityDocumentApi.listIdentityDocumentsWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<InternalRolePermissionDTO>> createRolePermission(RolePermissionRequest rolePermissionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return internalRolePermissionsApi
                .createRolePermissionWithHttpInfo(customersMapper.internalRolePermissionRequestToDto(rolePermissionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteRolePermission(Long id) {
        return internalRolePermissionsApi.deleteRolePermissionWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<Boolean>> hasPermission(Long roleId, Long permissionId) {
        return internalRolePermissionsApi.hasPermission1WithHttpInfo(roleId, roleId);
    }

    @Override
    public Mono<ResponseEntity<InternalRolePermissionDTO>> updateRolePermission(Long id, RolePermissionRequest rolePermissionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return internalRolePermissionsApi.updateRolePermissionWithHttpInfo(id,
                customersMapper.internalRolePermissionRequestToDto(rolePermissionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InternalRoleDTO>> createRole(RoleRequest roleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return internalRolesApi
                .createInternalRoleWithHttpInfo(customersMapper.internalRoleRequestToDto(roleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteRole(Long id) {
        return internalRolesApi.deleteInternalRoleWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<InternalRoleDTO>> updateRole(Long id, RoleRequest roleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return internalRolesApi.updateInternalRoleWithHttpInfo(id,
                customersMapper.internalRoleRequestToDto(roleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PartyEconomicActivityDTO>> createPartyEconomicActivity(PartyEconomicActivityRequest partyEconomicActivityRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyEconomicActivityApi.createPartyEconomicActivityWithHttpInfo(partyEconomicActivityRequest.getPartyId(),
                customersMapper.partyEconomicActivityRequestToDto(partyEconomicActivityRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePartyEconomicActivity(Long id, Long partyId) {
        return partyEconomicActivityApi.deletePartyEconomicActivityWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PartyEconomicActivityDTO>> getPartyEconomicActivityById(Long id, Long partyId) {
        return partyEconomicActivityApi.getPartyEconomicActivityWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PartyEconomicActivityDTO>> updatePartyEconomicActivity(Long id, PartyEconomicActivityRequest partyEconomicActivityRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyEconomicActivityApi.updatePartyEconomicActivityWithHttpInfo(id, partyEconomicActivityRequest.getPartyId(),
                customersMapper.partyEconomicActivityRequestToDto(partyEconomicActivityRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listPartyEconomicActivitiesByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return partyEconomicActivityApi.listPartyEconomicActivitiesWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<PartyProviderDTO>> createPartyProvider(PartyProviderRequest partyProviderRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyProviderApi.createPartyProviderWithHttpInfo(partyProviderRequest.getPartyId(),
                customersMapper.partyProviderRequestToDto(partyProviderRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePartyProvider(Long id, Long partyId) {
        return partyProviderApi.deletePartyProviderWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PartyProviderDTO>> updatePartyProvider(Long id, PartyProviderRequest partyProviderRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyProviderApi.updatePartyProviderWithHttpInfo(id, partyProviderRequest.getPartyId(),
                customersMapper.partyProviderRequestToDto(partyProviderRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listPartyProvidersByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return partyProviderApi.listPartyProvidersWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<PartyRelationshipDTO>> createPartyRelationship(PartyRelationshipRequest partyRelationshipRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyRelationshipApi.createRelationshipWithHttpInfo(partyRelationshipRequest.getPartyId(),
                customersMapper.partyRelationshipRequestToDto(partyRelationshipRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePartyRelationship(Long id, Long partyId) {
        return partyRelationshipApi.deleteRelationshipWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PartyRelationshipDTO>> getPartyRelationshipById(Long id, Long partyId) {
        return partyRelationshipApi.getRelationshipWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PartyRelationshipDTO>> updatePartyRelationship(Long id, PartyRelationshipRequest partyRelationshipRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyRelationshipApi.updateRelationshipWithHttpInfo(id, partyRelationshipRequest.getPartyId(),
                customersMapper.partyRelationshipRequestToDto(partyRelationshipRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listPartyRelationshipsByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return partyRelationshipApi.listRelationshipsWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<PartyStatusDTO>> createPartyStatus(PartyStatusRequest partyStatusRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyStatusApi.createPartyStatusWithHttpInfo(partyStatusRequest.getPartyId(),
                customersMapper.partyStatusRequestToDto(partyStatusRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePartyStatus(Long id, Long partyId) {
        return partyStatusApi.deletePartyStatusWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PartyStatusDTO>> getPartyStatusById(Long id, Long partyId) {
        return partyStatusApi.getPartyStatusWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PartyStatusDTO>> updatePartyStatus(Long id, PartyStatusRequest partyStatusRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return partyStatusApi.updatePartyStatusWithHttpInfo(id, partyStatusRequest.getPartyId(),
                customersMapper.partyStatusRequestToDto(partyStatusRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listPartyStatusesByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return partyStatusApi.listPartyStatusesWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<PepDTO>> createPep(PepRequest pepRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return pepApi.createPepWithHttpInfo(pepRequest.getPartyId(),
                customersMapper.pepRequestToDto(pepRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePep(Long id, Long partyId) {
        return pepApi.deletePepWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PepDTO>> getPepById(Long id, Long partyId) {
        return pepApi.getPepWithHttpInfo(id, partyId);
    }

    @Override
    public Mono<ResponseEntity<PepDTO>> updatePep(Long id, PepRequest pepRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return pepApi.updatePepWithHttpInfo(id, pepRequest.getPartyId(),
                customersMapper.pepRequestToDto(pepRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listPepsByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return pepApi.listPepsWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<PermissionAuditDTO>> createPermissionAudit(PermissionAuditRequest permissionAuditRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return permissionAuditApi.createAuditRecordWithHttpInfo(
                customersMapper.permissionAuditRequestToDto(permissionAuditRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PermissionAuditDTO>> getPermissionAuditById(Long id) {
        return permissionAuditApi.getAuditRecordByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PhoneDTO>> createPhone(PhoneRequest phoneRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return phoneApi.createPhoneWithHttpInfo(phoneRequest.getPartyId(),
                customersMapper.phoneRequestToDto(phoneRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePhone(Long id, Long partyId) {
        return phoneApi.deletePhoneWithHttpInfo(id, partyId);
    }


    @Override
    public Mono<ResponseEntity<PhoneDTO>> updatePhone(Long id, PhoneRequest phoneRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return phoneApi.updatePhoneWithHttpInfo(id, phoneRequest.getPartyId(),
                customersMapper.phoneRequestToDto(phoneRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listPhonesByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return phoneApi.listPhonesWithHttpInfo(partyId,
                String.valueOf(paginationRequest.getPageNumber()), String.valueOf(paginationRequest.getPageSize()),
                paginationRequest.getSortBy(), paginationRequest.getSortDirection());
    }

    @Override
    public Mono<ResponseEntity<ResourceDTO>> createResource(ResourceRequest resourceRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return resourcesApi.createResourceWithHttpInfo(
                customersMapper.resourceRequestToDto(resourceRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteResource(Long id, Long partyId) {
        return resourcesApi.deleteResourceWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<ResourceDTO>> updateResource(Long id, ResourceRequest resourceRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return resourcesApi.updateResourceWithHttpInfo(id, 
                customersMapper.resourceRequestToDto(resourceRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<ResourceDTO>> listResourcesByPartyId(Long partyId, PaginationRequest paginationRequest) {
        return resourcesApi.getAllResourcesWithHttpInfo();
    }

    @Override
    public Mono<ResponseEntity<PermissionDTO>> createPermission(PermissionRequest permissionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return permissionsApi.createPermissionWithHttpInfo(
                customersMapper.permissionRequestToDto(permissionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PermissionDTO>> getPermissionById(Long id) {
        return permissionsApi.getPermissionByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PermissionTypeDTO>> createPermissionType(PermissionTypeRequest permissionTypeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return permissionTypesApi.createPermissionTypeWithHttpInfo(
                customersMapper.permissionTypeRequestToDto(permissionTypeRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePermissionType(Long id) {
        return permissionTypesApi.deletePermissionTypeWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PermissionTypeDTO>> getPermissionTypeById(Long id) {
        return permissionTypesApi.getPermissionTypeByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PermissionTypeDTO>> updatePermissionType(Long id, PermissionTypeRequest permissionTypeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return permissionTypesApi.updatePermissionTypeWithHttpInfo(id,
                customersMapper.permissionTypeRequestToDto(permissionTypeRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<UserInternalRoleDTO>> createUserInternalRole(UserInternalRoleRequest userInternalRoleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return userInternalRolesApi.createUserRoleWithHttpInfo(
                customersMapper.userInternalRoleRequestToDto(userInternalRoleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteUserInternalRole(Long id) {
        return userInternalRolesApi.deleteUserRoleWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<UserInternalRoleDTO>> getUserInternalRoleById(Long id) {
        return userInternalRolesApi.getUserRoleByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<UserInternalRoleDTO>> updateUserInternalRole(Long id, UserInternalRoleRequest userInternalRoleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return userInternalRolesApi.updateUserRoleWithHttpInfo(id,
                customersMapper.userInternalRoleRequestToDto(userInternalRoleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<UserPermissionDTO>> createUserPermission(UserPermissionRequest userPermissionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return userPermissionsApi.createUserPermissionWithHttpInfo(
                customersMapper.userPermissionRequestToDto(userPermissionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteUserPermission(Long id) {
        return userPermissionsApi.deleteUserPermissionWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<UserPermissionDTO>> getUserPermissionById(Long id) {
        return userPermissionsApi.getUserPermissionByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<UserPermissionDTO>> updateUserPermission(Long id, UserPermissionRequest userPermissionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return userPermissionsApi.updateUserPermissionWithHttpInfo(id,
                customersMapper.userPermissionRequestToDto(userPermissionRequest), xIdempotencyKey);
    }

}

package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.common.customer.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


public interface CustomersService {

    Mono<ResponseEntity<PaginationResponse>> filterLegalPerson(LegalPersonRequest legalPersonRequest);
    Mono<ResponseEntity<PaginationResponse>> filterNaturalPerson(NaturalPersonRequest naturalPersonRequest);

    /**
     * Create a new party.
     *
     * @param partyRequest the party to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyDTO>> createParty(PartyRequest partyRequest);

    /**
     * Delete a party by ID.
     *
     * @param id the ID of the party to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteParty(Long id);

    /**
     * Get a party by ID.
     *
     * @param id the ID of the party to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyDTO>> getPartyById(Long id);

    /**
     * Update a party.
     *
     * @param id the ID of the party to update
     * @param partyRequest the party to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyDTO>> updateParty(Long id, PartyRequest partyRequest);

    /**
     * Create a new legal person.
     *
     * @param legalPersonRequest the legal person to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LegalPersonDTO>> createLegalPerson(Long partyId, LegalPersonRequest legalPersonRequest);

    /**
     * Delete a legal person by ID.
     *
     * @param id the ID of the legal person to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteLegalPerson(Long id);

    /**
     * Get a legal person by ID.
     *
     * @param id the ID of the legal person to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LegalPersonDTO>> getLegalPersonById(Long id);

    /**
     * Create a new natural person.
     *
     * @param naturalPersonRequest the natural person to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<NaturalPersonDTO>> createNaturalPerson(Long partyId, NaturalPersonRequest naturalPersonRequest);

    /**
     * Delete a natural person by ID.
     *
     * @param id the ID of the natural person to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteNaturalPerson(Long id);

    /**
     * Get a natural person by ID.
     *
     * @param id the ID of the natural person to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<NaturalPersonDTO>> getNaturalPersonById(Long id);

    /**
     * Create a new address.
     *
     * @param addressRequest the address to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AddressDTO>> createAddress(AddressRequest addressRequest);

    /**
     * Delete an address by ID.
     *
     * @param id the ID of the address to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteAddress(Long id, Long partyId);


    /**
     * Get an address by ID.
     *
     * @param id the ID of the address to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AddressDTO>> getAddressById(Long id, Long partyId);


    /**
     * Update an address.
     *
     * @param id the ID of the address to update
     * @param addressRequest the address to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<AddressDTO>> updateAddress(Long id, AddressRequest addressRequest);

    /**
     * List addresses for a party.
     *
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listAddressesByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new consent.
     *
     * @param consentRequest the consent to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ConsentDTO>> createConsent(ConsentRequest consentRequest);

    /**
     * Delete a consent by ID.
     *
     * @param id the ID of the consent to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteConsent(Long id, Long partyId);

    /**
     * Get a consent by ID.
     *
     * @param id the ID of the consent to get
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ConsentDTO>> getConsentById(Long id, Long partyId);

    /**
     * Update a consent.
     *
     * @param id the ID of the consent to update
     * @param consentRequest the consent to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ConsentDTO>> updateConsent(Long id, ConsentRequest consentRequest);

    /**
     * List consents for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listConsentsByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new email.
     *
     * @param emailRequest the email to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<EmailDTO>> createEmail(EmailRequest emailRequest);

    /**
     * Delete an email by ID.
     *
     * @param id the ID of the email to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteEmail(Long id, Long partyId);

    /**
     * Update an email.
     *
     * @param id the ID of the email to update
     * @param emailRequest the email to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<EmailDTO>> updateEmail(Long id, EmailRequest emailRequest);

    /**
     * List emails for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listEmailsByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new identity document.
     *
     * @param identityDocumentRequest the identity document to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<IdentityDocumentDTO>> createIdentityDocument(IdentityDocumentRequest identityDocumentRequest);

    /**
     * Delete an identity document by ID.
     *
     * @param id the ID of the identity document to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteIdentityDocument(Long id, Long partyId);

    /**
     * Update an identity document.
     *
     * @param id the ID of the identity document to update
     * @param identityDocumentRequest the identity document to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<IdentityDocumentDTO>> updateIdentityDocument(Long id, IdentityDocumentRequest identityDocumentRequest);

    /**
     * List identity documents for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listIdentityDocumentsByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new role permission.
     *
     * @param rolePermissionRequest the role permission to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InternalRolePermissionDTO>> createRolePermission(RolePermissionRequest rolePermissionRequest);

    /**
     * Delete a role permission by ID.
     *
     * @param id the ID of the role permission to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteRolePermission(Long id);


    /**
     * Checks whether a role has a specific permission.
     *
     * @param roleId the ID of the role to check
     * @param permissionId the ID of the permission to check
     * @return a Mono containing a ResponseEntity with a Boolean that indicates whether the role has the specified permission
     */
    Mono<ResponseEntity<Boolean>> hasPermission(Long roleId, Long permissionId);

    /**
     * Update a role permission.
     *
     * @param id the ID of the role permission to update
     * @param rolePermissionRequest the role permission to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InternalRolePermissionDTO>> updateRolePermission(Long id, RolePermissionRequest rolePermissionRequest);

    /**
     * Creates a new role.
     *
     * @param roleRequest the role request containing details for the new role
     * @return a Mono containing a ResponseEntity with the created InternalRoleDTO
     */
    Mono<ResponseEntity<InternalRoleDTO>> createRole(RoleRequest roleRequest);

    /**
     * Delete a role by ID.
     *
     * @param id the ID of the role to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteRole(Long id);

    /**
     * Updates an existing role with the specified details.
     *
     * @param id the ID of the role to update
     * @param roleRequest the details of the role to update
     * @return a Mono with the updated role wrapped in a response entity
     */
    Mono<ResponseEntity<InternalRoleDTO>> updateRole(Long id, RoleRequest roleRequest);

    /**
     * Create a new party economic activity.
     *
     * @param partyEconomicActivityRequest the party economic activity to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyEconomicActivityDTO>> createPartyEconomicActivity(PartyEconomicActivityRequest partyEconomicActivityRequest);

    /**
     * Delete a party economic activity by ID.
     *
     * @param id the ID of the party economic activity to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deletePartyEconomicActivity(Long id, Long partyId);

    /**
     * Get a party economic activity by ID.
     *
     * @param id the ID of the party economic activity to get
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyEconomicActivityDTO>> getPartyEconomicActivityById(Long id, Long partyId);

    /**
     * Update a party economic activity.
     *
     * @param id the ID of the party economic activity to update
     * @param partyEconomicActivityRequest the party economic activity to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyEconomicActivityDTO>> updatePartyEconomicActivity(Long id, PartyEconomicActivityRequest partyEconomicActivityRequest);

    /**
     * List party economic activities for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listPartyEconomicActivitiesByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new party provider.
     *
     * @param partyProviderRequest the party provider to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyProviderDTO>> createPartyProvider(PartyProviderRequest partyProviderRequest);

    /**
     * Delete a party provider by ID.
     *
     * @param id the ID of the party provider to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deletePartyProvider(Long id, Long partyId);

    /**
     * Update a party provider.
     *
     * @param id the ID of the party provider to update
     * @param partyProviderRequest the party provider to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyProviderDTO>> updatePartyProvider(Long id, PartyProviderRequest partyProviderRequest);

    /**
     * List party providers for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listPartyProvidersByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new party relationship.
     *
     * @param partyRelationshipRequest the party relationship to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyRelationshipDTO>> createPartyRelationship(PartyRelationshipRequest partyRelationshipRequest);

    /**
     * Delete a party relationship by ID.
     *
     * @param id the ID of the party relationship to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deletePartyRelationship(Long id, Long partyId);

    /**
     * Get a party relationship by ID.
     *
     * @param id the ID of the party relationship to get
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyRelationshipDTO>> getPartyRelationshipById(Long id, Long partyId);

    /**
     * Update a party relationship.
     *
     * @param id the ID of the party relationship to update
     * @param partyRelationshipRequest the party relationship to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyRelationshipDTO>> updatePartyRelationship(Long id, PartyRelationshipRequest partyRelationshipRequest);

    /**
     * List party relationships for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listPartyRelationshipsByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new party status.
     *
     * @param partyStatusRequest the party status to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyStatusDTO>> createPartyStatus(PartyStatusRequest partyStatusRequest);

    /**
     * Delete a party status by ID.
     *
     * @param id the ID of the party status to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deletePartyStatus(Long id, Long partyId);

    /**
     * Get a party status by ID.
     *
     * @param id the ID of the party status to get
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyStatusDTO>> getPartyStatusById(Long id, Long partyId);

    /**
     * Update a party status.
     *
     * @param id the ID of the party status to update
     * @param partyStatusRequest the party status to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PartyStatusDTO>> updatePartyStatus(Long id, PartyStatusRequest partyStatusRequest);

    /**
     * List party statuses for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listPartyStatusesByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new PEP (Politically Exposed Person) record.
     *
     * @param pepRequest the PEP record to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PepDTO>> createPep(PepRequest pepRequest);

    /**
     * Delete a PEP record by ID.
     *
     * @param id the ID of the PEP record to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deletePep(Long id, Long partyId);

    /**
     * Get a PEP record by ID.
     *
     * @param id the ID of the PEP record to get
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PepDTO>> getPepById(Long id, Long partyId);

    /**
     * Update a PEP record.
     *
     * @param id the ID of the PEP record to update
     * @param pepRequest the PEP record to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PepDTO>> updatePep(Long id, PepRequest pepRequest);

    /**
     * List PEP records for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listPepsByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new permission audit record.
     *
     * @param permissionAuditRequest the permission audit record to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PermissionAuditDTO>> createPermissionAudit(PermissionAuditRequest permissionAuditRequest);

    /**
     * Get a permission audit record by ID.
     *
     * @param id the ID of the permission audit record to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PermissionAuditDTO>> getPermissionAuditById(Long id);

    /**
     * Create a new permission.
     *
     * @param permissionRequest the permission to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PermissionDTO>> createPermission(PermissionRequest permissionRequest);

    /**
     * Get a permission by ID.
     *
     * @param id the ID of the permission to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PermissionDTO>> getPermissionById(Long id);

    /**
     * Create a new permission type.
     *
     * @param permissionTypeRequest the permission type to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PermissionTypeDTO>> createPermissionType(PermissionTypeRequest permissionTypeRequest);

    /**
     * Delete a permission type by ID.
     *
     * @param id the ID of the permission type to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deletePermissionType(Long id);

    /**
     * Get a permission type by ID.
     *
     * @param id the ID of the permission type to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PermissionTypeDTO>> getPermissionTypeById(Long id);

    /**
     * Update a permission type.
     *
     * @param id the ID of the permission type to update
     * @param permissionTypeRequest the permission type to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PermissionTypeDTO>> updatePermissionType(Long id, PermissionTypeRequest permissionTypeRequest);

    /**
     * Create a new phone.
     *
     * @param phoneRequest the phone to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PhoneDTO>> createPhone(PhoneRequest phoneRequest);

    /**
     * Delete a phone by ID.
     *
     * @param id the ID of the phone to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deletePhone(Long id, Long partyId);


    /**
     * Update a phone.
     *
     * @param id the ID of the phone to update
     * @param phoneRequest the phone to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PhoneDTO>> updatePhone(Long id, PhoneRequest phoneRequest);

    /**
     * List phones for a party.
     *
     * @param partyId the ID of the party
     * @param paginationRequest the pagination request
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listPhonesByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new resource.
     *
     * @param resourceRequest the resource to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ResourceDTO>> createResource(ResourceRequest resourceRequest);

    /**
     * Delete a resource by ID.
     *
     * @param id the ID of the resource to delete
     * @param partyId the ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteResource(Long id, Long partyId);

    /**
     * Update a resource.
     *
     * @param id the ID of the resource to update
     * @param resourceRequest the resource to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ResourceDTO>> updateResource(Long id, ResourceRequest resourceRequest);

    /**
     * List all resources.
     *
     * @param partyId the ID of the party (not used)
     * @param paginationRequest the pagination request (not used)
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ResourceDTO>> listResourcesByPartyId(Long partyId, PaginationRequest paginationRequest);

    /**
     * Create a new user internal role.
     *
     * @param userInternalRoleRequest the user internal role to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<UserInternalRoleDTO>> createUserInternalRole(UserInternalRoleRequest userInternalRoleRequest);

    /**
     * Delete a user internal role by ID.
     *
     * @param id the ID of the user internal role to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteUserInternalRole(Long id);

    /**
     * Get a user internal role by ID.
     *
     * @param id the ID of the user internal role to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<UserInternalRoleDTO>> getUserInternalRoleById(Long id);

    /**
     * Update a user internal role.
     *
     * @param id the ID of the user internal role to update
     * @param userInternalRoleRequest the user internal role to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<UserInternalRoleDTO>> updateUserInternalRole(Long id, UserInternalRoleRequest userInternalRoleRequest);

    /**
     * Create a new user permission.
     *
     * @param userPermissionRequest the user permission to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<UserPermissionDTO>> createUserPermission(UserPermissionRequest userPermissionRequest);

    /**
     * Delete a user permission by ID.
     *
     * @param id the ID of the user permission to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteUserPermission(Long id);

    /**
     * Get a user permission by ID.
     *
     * @param id the ID of the user permission to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<UserPermissionDTO>> getUserPermissionById(Long id);

    /**
     * Update a user permission.
     *
     * @param id the ID of the user permission to update
     * @param userPermissionRequest the user permission to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<UserPermissionDTO>> updateUserPermission(Long id, UserPermissionRequest userPermissionRequest);

}

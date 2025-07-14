package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.common.customer.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.customers.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


public interface CustomersService {

    /**
     * Filters legal person information based on the provided request parameters.
     *
     * @param legalPersonRequest the request containing filter criteria for legal persons
     * @return a reactive Mono emitting a ResponseEntity containing a PaginationResponse with the filtered results
     */
    Mono<ResponseEntity<PaginationResponseLegalPersonDTO>> filterLegalPerson(LegalPersonRequest legalPersonRequest);
    /**
     * Filters natural person data based on the given request criteria.
     *
     * @param naturalPersonRequest the request object containing the filtering criteria for natural person data
     * @return a reactive Mono encapsulating a ResponseEntity that contains a PaginationResponse with the filtered data
     */
    Mono<ResponseEntity<PaginationResponseNaturalPersonDTO>> filterNaturalPerson(NaturalPersonRequest naturalPersonRequest);

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

}
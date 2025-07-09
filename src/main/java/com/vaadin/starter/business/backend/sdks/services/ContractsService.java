package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.common.contract.sdk.model.ContractDTO;
import com.catalis.common.contract.sdk.model.ContractDocumentDTO;
import com.catalis.common.contract.sdk.model.ContractEventDTO;
import com.catalis.common.contract.sdk.model.ContractPartyDTO;
import com.catalis.common.contract.sdk.model.ContractRiskAssessmentDTO;
import com.catalis.common.contract.sdk.model.ContractStatusHistoryDTO;
import com.catalis.common.contract.sdk.model.ContractTermDTO;
import com.catalis.common.contract.sdk.model.PaginationResponse;
import com.catalis.common.contract.sdk.model.PaginationResponseContractDTO;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ContractsService {

    /**
     * Create a new contract.
     *
     * @param contractRequest the contract to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractDTO>> createContract(ContractRequest contractRequest);

    /**
     * Delete a contract by ID.
     *
     * @param id the ID of the contract to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteContract(Long id);

    /**
     * Get a contract by ID.
     *
     * @param id the ID of the contract to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractDTO>> getContract(Long id);

    /**
     * Filter contracts based on the provided request parameters.
     *
     * @param contractFilterRequest the request containing filter criteria for contracts
     * @return a reactive Mono emitting a ResponseEntity containing a PaginationResponseContractDTO with the filtered results
     */
    Mono<ResponseEntity<PaginationResponseContractDTO>> filterContracts(ContractFilterRequest contractFilterRequest);

    /**
     * Update a contract.
     *
     * @param id the ID of the contract to update
     * @param contractRequest the contract to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractDTO>> updateContract(Long id, ContractRequest contractRequest);

    // Contract Documents API methods

    /**
     * Create a new contract document.
     *
     * @param contractId the ID of the contract
     * @param contractDocumentRequest the document to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractDocumentDTO>> createDocument(Long contractId, ContractDocumentRequest contractDocumentRequest);

    /**
     * Delete a contract document by ID.
     *
     * @param contractId the ID of the contract
     * @param documentId the ID of the document to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteDocument(Long contractId, Long documentId);

    /**
     * Get all documents for a contract.
     *
     * @param contractId the ID of the contract
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllDocuments(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a contract document by ID.
     *
     * @param contractId the ID of the contract
     * @param documentId the ID of the document to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractDocumentDTO>> getDocument(Long contractId, Long documentId);

    /**
     * Update a contract document.
     *
     * @param contractId the ID of the contract
     * @param documentId the ID of the document to update
     * @param contractDocumentRequest the document to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractDocumentDTO>> updateDocument(Long contractId, Long documentId, ContractDocumentRequest contractDocumentRequest);

    // Contract Events API methods

    /**
     * Create a new contract event.
     *
     * @param contractId the ID of the contract
     * @param contractEventRequest the event to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractEventDTO>> createEvent(Long contractId, ContractEventRequest contractEventRequest);

    /**
     * Delete a contract event by ID.
     *
     * @param contractId the ID of the contract
     * @param eventId the ID of the event to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteEvent(Long contractId, Long eventId);

    /**
     * Get all events for a contract.
     *
     * @param contractId the ID of the contract
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllEvents(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a contract event by ID.
     *
     * @param contractId the ID of the contract
     * @param eventId the ID of the event to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractEventDTO>> getEvent(Long contractId, Long eventId);

    /**
     * Update a contract event.
     *
     * @param contractId the ID of the contract
     * @param eventId the ID of the event to update
     * @param contractEventRequest the event to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractEventDTO>> updateEvent(Long contractId, Long eventId, ContractEventRequest contractEventRequest);

    // Contract Parties API methods

    /**
     * Create a new contract party link.
     *
     * @param contractId the ID of the contract
     * @param contractPartyRequest the party to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractPartyDTO>> createPartyLink(Long contractId, ContractPartyRequest contractPartyRequest);

    /**
     * Delete a contract party by ID.
     *
     * @param contractId the ID of the contract
     * @param partyId the ID of the party to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteParty(Long contractId, Long partyId);

    /**
     * Get all parties for a contract.
     *
     * @param contractId the ID of the contract
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllParties(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a contract party by ID.
     *
     * @param contractId the ID of the contract
     * @param partyId the ID of the party to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractPartyDTO>> getParty(Long contractId, Long partyId);

    /**
     * Update a contract party.
     *
     * @param contractId the ID of the contract
     * @param partyId the ID of the party to update
     * @param contractPartyRequest the party to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractPartyDTO>> updateParty(Long contractId, Long partyId, ContractPartyRequest contractPartyRequest);

    // Contract Risk Assessments API methods

    /**
     * Create a new contract risk assessment.
     *
     * @param contractId the ID of the contract
     * @param contractRiskAssessmentRequest the risk assessment to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractRiskAssessmentDTO>> createRiskAssessment(Long contractId, ContractRiskAssessmentRequest contractRiskAssessmentRequest);

    /**
     * Delete a contract risk assessment by ID.
     *
     * @param contractId the ID of the contract
     * @param riskId the ID of the risk assessment to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteRiskAssessment(Long contractId, Long riskId);

    /**
     * Get all risk assessments for a contract.
     *
     * @param contractId the ID of the contract
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllRiskAssessments(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a contract risk assessment by ID.
     *
     * @param contractId the ID of the contract
     * @param riskId the ID of the risk assessment to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractRiskAssessmentDTO>> getRiskAssessment(Long contractId, Long riskId);

    /**
     * Update a contract risk assessment.
     *
     * @param contractId the ID of the contract
     * @param riskId the ID of the risk assessment to update
     * @param contractRiskAssessmentRequest the risk assessment to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractRiskAssessmentDTO>> updateRiskAssessment(Long contractId, Long riskId, ContractRiskAssessmentRequest contractRiskAssessmentRequest);

    // Contract Terms API methods

    /**
     * Create a new contract term.
     *
     * @param contractId the ID of the contract
     * @param contractTermRequest the term to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractTermDTO>> createTerm(Long contractId, ContractTermRequest contractTermRequest);

    /**
     * Delete a contract term by ID.
     *
     * @param contractId the ID of the contract
     * @param termId the ID of the term to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteTerm(Long contractId, Long termId);

    /**
     * Get all terms for a contract.
     *
     * @param contractId the ID of the contract
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllTerms(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a contract term by ID.
     *
     * @param contractId the ID of the contract
     * @param termId the ID of the term to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractTermDTO>> getTerm(Long contractId, Long termId);

    /**
     * Update a contract term.
     *
     * @param contractId the ID of the contract
     * @param termId the ID of the term to update
     * @param contractTermRequest the term to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractTermDTO>> updateTerm(Long contractId, Long termId, ContractTermRequest contractTermRequest);

    // Contract Status History API methods

    /**
     * Create a new contract status history.
     *
     * @param contractId the ID of the contract
     * @param contractStatusHistoryRequest the status history to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractStatusHistoryDTO>> createStatusHistory(Long contractId, ContractStatusHistoryRequest contractStatusHistoryRequest);

    /**
     * Delete a contract status history by ID.
     *
     * @param contractId the ID of the contract
     * @param historyId the ID of the status history to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteStatusHistory(Long contractId, Long historyId);

    /**
     * Get all status history records for a contract.
     *
     * @param contractId the ID of the contract
     * @param pageNumber the page number
     * @param pageSize the page size
     * @param sortBy the field to sort by
     * @param sortDirection the sort direction
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllStatuses(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a contract status history by ID.
     *
     * @param contractId the ID of the contract
     * @param historyId the ID of the status history to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractStatusHistoryDTO>> getStatusHistory(Long contractId, Long historyId);

    /**
     * Update a contract status history.
     *
     * @param contractId the ID of the contract
     * @param historyId the ID of the status history to update
     * @param contractStatusHistoryRequest the status history to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ContractStatusHistoryDTO>> updateStatusHistory(Long contractId, Long historyId, ContractStatusHistoryRequest contractStatusHistoryRequest);
}

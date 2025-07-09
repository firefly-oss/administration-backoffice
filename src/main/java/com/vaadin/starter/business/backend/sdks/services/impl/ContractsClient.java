package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.common.contract.sdk.api.*;
import com.catalis.common.contract.sdk.invoker.ApiClient;
import com.catalis.common.contract.sdk.model.*;
import com.vaadin.starter.business.backend.mapper.customers.ContractsMapper;
import com.vaadin.starter.business.backend.sdks.services.ContractsService;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractDocumentRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractEventRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractFilterRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractPartyRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractRiskAssessmentRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractStatusHistoryRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractTermRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
public class ContractsClient implements ContractsService {

    private final ContractsApi contractsApi;
    private final ContractDocumentsApi contractDocumentsApi;
    private final ContractEventsApi contractEventsApi;
    private final ContractPartiesApi contractPartiesApi;
    private final ContractRiskAssessmentsApi contractRiskAssessmentsApi;
    private final ContractTermsApi contractTermsApi;
    private final ContractStatusHistoryApi contractStatusHistoryApi;
    private final ContractsMapper contractsMapper;

    @Autowired
    public ContractsClient(ApiClient apiClient, ContractsMapper contractsMapper) {
        this.contractsApi = new ContractsApi(apiClient);
        this.contractDocumentsApi = new ContractDocumentsApi(apiClient);
        this.contractEventsApi = new ContractEventsApi(apiClient);
        this.contractPartiesApi = new ContractPartiesApi(apiClient);
        this.contractRiskAssessmentsApi = new ContractRiskAssessmentsApi(apiClient);
        this.contractTermsApi = new ContractTermsApi(apiClient);
        this.contractStatusHistoryApi = new ContractStatusHistoryApi(apiClient);
        this.contractsMapper = contractsMapper;
    }

    @Override
    public Mono<ResponseEntity<ContractDTO>> createContract(ContractRequest contractRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractsApi.createContractWithHttpInfo(contractsMapper.contractRequestToDto(contractRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteContract(Long id) {
        return contractsApi.deleteContractWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<ContractDTO>> getContract(Long id) {
        return contractsApi.getContractWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponseContractDTO>> filterContracts(ContractFilterRequest contractFilterRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractsApi.filterContractsWithHttpInfo(contractsMapper.contractFilterRequestToDto(contractFilterRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<ContractDTO>> updateContract(Long id, ContractRequest contractRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractsApi.updateContractWithHttpInfo(id, contractsMapper.contractRequestToDto(contractRequest), xIdempotencyKey);
    }

    // Contract Documents API methods

    @Override
    public Mono<ResponseEntity<ContractDocumentDTO>> createDocument(Long contractId, ContractDocumentRequest contractDocumentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractDocumentsApi.createDocumentWithHttpInfo(contractId, contractsMapper.contractDocumentRequestToDto(contractDocumentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteDocument(Long contractId, Long documentId) {
        return contractDocumentsApi.deleteDocumentWithHttpInfo(contractId, documentId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllDocuments(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return contractDocumentsApi.getAllDocumentsWithHttpInfo(contractId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ContractDocumentDTO>> getDocument(Long contractId, Long documentId) {
        return contractDocumentsApi.getDocumentWithHttpInfo(contractId, documentId);
    }

    @Override
    public Mono<ResponseEntity<ContractDocumentDTO>> updateDocument(Long contractId, Long documentId, ContractDocumentRequest contractDocumentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractDocumentsApi.updateDocumentWithHttpInfo(contractId, documentId, contractsMapper.contractDocumentRequestToDto(contractDocumentRequest), xIdempotencyKey);
    }

    // Contract Events API methods

    @Override
    public Mono<ResponseEntity<ContractEventDTO>> createEvent(Long contractId, ContractEventRequest contractEventRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractEventsApi.createEventWithHttpInfo(contractId, contractsMapper.contractEventRequestToDto(contractEventRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteEvent(Long contractId, Long eventId) {
        return contractEventsApi.deleteEventWithHttpInfo(contractId, eventId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllEvents(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return contractEventsApi.getAllEventsWithHttpInfo(contractId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ContractEventDTO>> getEvent(Long contractId, Long eventId) {
        return contractEventsApi.getEventWithHttpInfo(contractId, eventId);
    }

    @Override
    public Mono<ResponseEntity<ContractEventDTO>> updateEvent(Long contractId, Long eventId, ContractEventRequest contractEventRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractEventsApi.updateEventWithHttpInfo(contractId, eventId, contractsMapper.contractEventRequestToDto(contractEventRequest), xIdempotencyKey);
    }

    // Contract Parties API methods

    @Override
    public Mono<ResponseEntity<ContractPartyDTO>> createPartyLink(Long contractId, ContractPartyRequest contractPartyRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractPartiesApi.createPartyLinkWithHttpInfo(contractId, contractsMapper.contractPartyRequestToDto(contractPartyRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteParty(Long contractId, Long partyId) {
        return contractPartiesApi.deletePartyWithHttpInfo(contractId, partyId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllParties(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return contractPartiesApi.getAllPartiesWithHttpInfo(contractId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ContractPartyDTO>> getParty(Long contractId, Long partyId) {
        return contractPartiesApi.getPartyWithHttpInfo(contractId, partyId);
    }

    @Override
    public Mono<ResponseEntity<ContractPartyDTO>> updateParty(Long contractId, Long partyId, ContractPartyRequest contractPartyRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractPartiesApi.updatePartyWithHttpInfo(contractId, partyId, contractsMapper.contractPartyRequestToDto(contractPartyRequest), xIdempotencyKey);
    }

    // Contract Risk Assessments API methods

    @Override
    public Mono<ResponseEntity<ContractRiskAssessmentDTO>> createRiskAssessment(Long contractId, ContractRiskAssessmentRequest contractRiskAssessmentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractRiskAssessmentsApi.createRiskAssessmentWithHttpInfo(contractId, contractsMapper.contractRiskAssessmentRequestToDto(contractRiskAssessmentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteRiskAssessment(Long contractId, Long riskId) {
        return contractRiskAssessmentsApi.deleteRiskAssessmentWithHttpInfo(contractId, riskId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllRiskAssessments(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return contractRiskAssessmentsApi.getAllRiskAssessmentsWithHttpInfo(contractId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ContractRiskAssessmentDTO>> getRiskAssessment(Long contractId, Long riskId) {
        return contractRiskAssessmentsApi.getRiskAssessmentWithHttpInfo(contractId, riskId);
    }

    @Override
    public Mono<ResponseEntity<ContractRiskAssessmentDTO>> updateRiskAssessment(Long contractId, Long riskId, ContractRiskAssessmentRequest contractRiskAssessmentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractRiskAssessmentsApi.updateRiskAssessmentWithHttpInfo(contractId, riskId, contractsMapper.contractRiskAssessmentRequestToDto(contractRiskAssessmentRequest), xIdempotencyKey);
    }

    // Contract Terms API methods

    @Override
    public Mono<ResponseEntity<ContractTermDTO>> createTerm(Long contractId, ContractTermRequest contractTermRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractTermsApi.createTermWithHttpInfo(contractId, contractsMapper.contractTermRequestToDto(contractTermRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteTerm(Long contractId, Long termId) {
        return contractTermsApi.deleteTermWithHttpInfo(contractId, termId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllTerms(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return contractTermsApi.getAllTermsWithHttpInfo(contractId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ContractTermDTO>> getTerm(Long contractId, Long termId) {
        return contractTermsApi.getTermWithHttpInfo(contractId, termId);
    }

    @Override
    public Mono<ResponseEntity<ContractTermDTO>> updateTerm(Long contractId, Long termId, ContractTermRequest contractTermRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractTermsApi.updateTermWithHttpInfo(contractId, termId, contractsMapper.contractTermRequestToDto(contractTermRequest), xIdempotencyKey);
    }

    // Contract Status History API methods

    @Override
    public Mono<ResponseEntity<ContractStatusHistoryDTO>> createStatusHistory(Long contractId, ContractStatusHistoryRequest contractStatusHistoryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractStatusHistoryApi.createStatusHistoryWithHttpInfo(contractId, contractsMapper.contractStatusHistoryRequestToDto(contractStatusHistoryRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteStatusHistory(Long contractId, Long historyId) {
        return contractStatusHistoryApi.deleteStatusHistoryWithHttpInfo(contractId, historyId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllStatuses(Long contractId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return contractStatusHistoryApi.getAllStatusesWithHttpInfo(contractId, pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ContractStatusHistoryDTO>> getStatusHistory(Long contractId, Long historyId) {
        return contractStatusHistoryApi.getStatusHistoryWithHttpInfo(contractId, historyId);
    }

    @Override
    public Mono<ResponseEntity<ContractStatusHistoryDTO>> updateStatusHistory(Long contractId, Long historyId, ContractStatusHistoryRequest contractStatusHistoryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return contractStatusHistoryApi.updateStatusHistoryWithHttpInfo(contractId, historyId, contractsMapper.contractStatusHistoryRequestToDto(contractStatusHistoryRequest), xIdempotencyKey);
    }
}

package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.core.distributor.sdk.api.DistributorApi;
import com.catalis.core.distributor.sdk.api.DistributorAuditLogApi;
import com.catalis.core.distributor.sdk.api.DistributorBrandingApi;
import com.catalis.core.distributor.sdk.invoker.ApiClient;
import com.catalis.core.distributor.sdk.model.*;
import com.vaadin.starter.business.backend.mapper.distributors.DistributorsMapper;
import com.vaadin.starter.business.backend.sdks.services.DistributorsService;
import com.vaadin.starter.business.backend.sdks.services.rest.distributors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class DistributorsClient implements DistributorsService {

    private final DistributorApi distributorApi;
    private final DistributorAuditLogApi distributorAuditLogApi;
    private final DistributorBrandingApi distributorBrandingApi;
    private final DistributorsMapper distributorsMapper;

    @Autowired
    public DistributorsClient(ApiClient apiClient, DistributorsMapper distributorsMapper) {
        this.distributorApi = new DistributorApi(apiClient);
        this.distributorAuditLogApi = new DistributorAuditLogApi(apiClient);
        this.distributorBrandingApi = new DistributorBrandingApi(apiClient);
        this.distributorsMapper = distributorsMapper;
    }

    @Override
    public Mono<ResponseEntity<DistributorDTO>> createDistributor(DistributorRequest distributorRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorApi.createDistributorWithHttpInfo(distributorsMapper.distributorRequestToDto(distributorRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteDistributor(Long distributorId) {
        return distributorApi.deleteDistributorWithHttpInfo(distributorId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterDistributors(FilterRequestDistributorDTO filterRequestDistributorDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorApi.filterDistributorsWithHttpInfo(filterRequestDistributorDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<DistributorDTO>> getDistributorById(Long distributorId) {
        return distributorApi.getDistributorByIdWithHttpInfo(distributorId);
    }

    @Override
    public Mono<ResponseEntity<DistributorDTO>> updateDistributor(Long distributorId, DistributorRequest distributorRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorApi.updateDistributorWithHttpInfo(distributorId, distributorsMapper.distributorRequestToDto(distributorRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<DistributorAuditLogDTO>> createDistributorAuditLog(Long distributorId, DistributorAuditLogRequest distributorAuditLogRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorAuditLogApi.createDistributorAuditLogWithHttpInfo(distributorId, distributorsMapper.distributorAuditLogRequestToDto(distributorAuditLogRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteDistributorAuditLog(Long distributorId, Long auditLogId) {
        return distributorAuditLogApi.deleteDistributorAuditLogWithHttpInfo(distributorId, auditLogId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterDistributorAuditLogs(Long distributorId, FilterRequestDistributorAuditLogDTO filterRequestDistributorAuditLogDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorAuditLogApi.filterDistributorAuditLogsWithHttpInfo(distributorId, filterRequestDistributorAuditLogDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<DistributorAuditLogDTO>> getDistributorAuditLogById(Long distributorId, Long auditLogId) {
        return distributorAuditLogApi.getDistributorAuditLogByIdWithHttpInfo(distributorId, auditLogId);
    }

    @Override
    public Mono<ResponseEntity<DistributorAuditLogDTO>> updateDistributorAuditLog(Long distributorId, Long auditLogId, DistributorAuditLogRequest distributorAuditLogRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorAuditLogApi.updateDistributorAuditLogWithHttpInfo(distributorId, auditLogId, distributorsMapper.distributorAuditLogRequestToDto(distributorAuditLogRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<DistributorBrandingDTO>> createDistributorBranding(Long distributorId, DistributorBrandingRequest distributorBrandingRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorBrandingApi.createDistributorBrandingWithHttpInfo(distributorId, distributorsMapper.distributorBrandingRequestToDto(distributorBrandingRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteDistributorBranding(Long distributorId, Long brandingId) {
        return distributorBrandingApi.deleteDistributorBrandingWithHttpInfo(distributorId, brandingId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterDistributorBrandings(Long distributorId, FilterRequestDistributorBrandingDTO filterRequestDistributorBrandingDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorBrandingApi.filterDistributorBrandingsWithHttpInfo(distributorId, filterRequestDistributorBrandingDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<DistributorBrandingDTO>> getDistributorBrandingById(Long distributorId, Long brandingId) {
        return distributorBrandingApi.getDistributorBrandingByIdWithHttpInfo(distributorId, brandingId);
    }

    @Override
    public Mono<ResponseEntity<DistributorBrandingDTO>> updateDistributorBranding(Long distributorId, Long brandingId, DistributorBrandingRequest distributorBrandingRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return distributorBrandingApi.updateDistributorBrandingWithHttpInfo(distributorId, brandingId, distributorsMapper.distributorBrandingRequestToDto(distributorBrandingRequest), xIdempotencyKey);
    }
}

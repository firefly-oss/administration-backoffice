package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.core.distributor.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.distributors.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Service interface for distributor operations.
 */
public interface DistributorsService {

    /**
     * Create a new distributor.
     *
     * @param distributorRequest the distributor to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorDTO>> createDistributor(DistributorRequest distributorRequest);

    /**
     * Delete a distributor by ID.
     *
     * @param distributorId the ID of the distributor to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteDistributor(Long distributorId);

    /**
     * Filter distributors based on criteria.
     *
     * @param filterRequestDistributorDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterDistributors(FilterRequestDistributorDTO filterRequestDistributorDTO);

    /**
     * Get a distributor by ID.
     *
     * @param distributorId the ID of the distributor to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorDTO>> getDistributorById(Long distributorId);

    /**
     * Update a distributor.
     *
     * @param distributorId the ID of the distributor to update
     * @param distributorRequest the distributor to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorDTO>> updateDistributor(Long distributorId, DistributorRequest distributorRequest);

    /**
     * Create a new distributor audit log.
     *
     * @param distributorId the ID of the distributor
     * @param distributorAuditLogRequest the distributor audit log to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorAuditLogDTO>> createDistributorAuditLog(Long distributorId, DistributorAuditLogRequest distributorAuditLogRequest);

    /**
     * Delete a distributor audit log by ID.
     *
     * @param distributorId the ID of the distributor
     * @param auditLogId the ID of the distributor audit log to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteDistributorAuditLog(Long distributorId, Long auditLogId);

    /**
     * Filter distributor audit logs based on criteria.
     *
     * @param distributorId the ID of the distributor
     * @param filterRequestDistributorAuditLogDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterDistributorAuditLogs(Long distributorId, FilterRequestDistributorAuditLogDTO filterRequestDistributorAuditLogDTO);

    /**
     * Get a distributor audit log by ID.
     *
     * @param distributorId the ID of the distributor
     * @param auditLogId the ID of the distributor audit log to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorAuditLogDTO>> getDistributorAuditLogById(Long distributorId, Long auditLogId);

    /**
     * Update a distributor audit log.
     *
     * @param distributorId the ID of the distributor
     * @param auditLogId the ID of the distributor audit log to update
     * @param distributorAuditLogRequest the distributor audit log to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorAuditLogDTO>> updateDistributorAuditLog(Long distributorId, Long auditLogId, DistributorAuditLogRequest distributorAuditLogRequest);

    /**
     * Create a new distributor branding.
     *
     * @param distributorId the ID of the distributor
     * @param distributorBrandingRequest the distributor branding to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorBrandingDTO>> createDistributorBranding(Long distributorId, DistributorBrandingRequest distributorBrandingRequest);

    /**
     * Delete a distributor branding by ID.
     *
     * @param distributorId the ID of the distributor
     * @param brandingId the ID of the distributor branding to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteDistributorBranding(Long distributorId, Long brandingId);

    /**
     * Filter distributor brandings based on criteria.
     *
     * @param distributorId the ID of the distributor
     * @param filterRequestDistributorBrandingDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterDistributorBrandings(Long distributorId, FilterRequestDistributorBrandingDTO filterRequestDistributorBrandingDTO);

    /**
     * Get a distributor branding by ID.
     *
     * @param distributorId the ID of the distributor
     * @param brandingId the ID of the distributor branding to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorBrandingDTO>> getDistributorBrandingById(Long distributorId, Long brandingId);

    /**
     * Update a distributor branding.
     *
     * @param distributorId the ID of the distributor
     * @param brandingId the ID of the distributor branding to update
     * @param distributorBrandingRequest the distributor branding to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<DistributorBrandingDTO>> updateDistributorBranding(Long distributorId, Long brandingId, DistributorBrandingRequest distributorBrandingRequest);
}

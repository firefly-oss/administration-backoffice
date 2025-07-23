package com.vaadin.starter.business.backend.mapper.distributors;

import com.catalis.core.distributor.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.distributors.*;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between distributor request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface DistributorsMapper {

    /**
     * Convert a DistributorRequest to a DistributorDTO.
     *
     * @param distributorRequest the request object
     * @return the DTO
     */
    DistributorDTO distributorRequestToDto(DistributorRequest distributorRequest);

    /**
     * Convert a DistributorAuditLogRequest to a DistributorAuditLogDTO.
     *
     * @param distributorAuditLogRequest the request object
     * @return the DTO
     */
    DistributorAuditLogDTO distributorAuditLogRequestToDto(DistributorAuditLogRequest distributorAuditLogRequest);

    /**
     * Convert a DistributorBrandingRequest to a DistributorBrandingDTO.
     *
     * @param distributorBrandingRequest the request object
     * @return the DTO
     */
    DistributorBrandingDTO distributorBrandingRequestToDto(DistributorBrandingRequest distributorBrandingRequest);
}

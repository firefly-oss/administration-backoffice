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

    /**
     * Convert a LeasingContractRequest to a LeasingContractDTO.
     *
     * @param leasingContractRequest the request object
     * @return the DTO
     */
    LeasingContractDTO leasingContractRequestToDto(LeasingContractRequest leasingContractRequest);

    /**
     * Convert a LendingConfigurationRequest to a LendingConfigurationDTO.
     *
     * @param lendingConfigurationRequest the request object
     * @return the DTO
     */
    LendingConfigurationDTO lendingConfigurationRequestToDto(LendingConfigurationRequest lendingConfigurationRequest);

    /**
     * Convert a LendingTypeRequest to a LendingTypeDTO.
     *
     * @param lendingTypeRequest the request object
     * @return the DTO
     */
    LendingTypeDTO lendingTypeRequestToDto(LendingTypeRequest lendingTypeRequest);

    /**
     * Convert a ProductRequest to a ProductDTO.
     *
     * @param productRequest the request object
     * @return the DTO
     */
    ProductDTO productRequestToDto(ProductRequest productRequest);

    /**
     * Convert a ProductFilterRequest to a FilterRequestProductDTO.
     *
     * @param productFilterRequest the request object
     * @return the DTO
     */
    com.catalis.core.distributor.sdk.model.FilterRequestProductDTO productFilterRequestToDto(ProductFilterRequest productFilterRequest);

    /**
     * Convert a ProductCategoryRequest to a ProductCategoryDTO.
     *
     * @param productCategoryRequest the request object
     * @return the DTO
     */
    ProductCategoryDTO productCategoryRequestToDto(ProductCategoryRequest productCategoryRequest);

    /**
     * Convert a ShipmentRequest to a ShipmentDTO.
     *
     * @param shipmentRequest the request object
     * @return the DTO
     */
    ShipmentDTO shipmentRequestToDto(ShipmentRequest shipmentRequest);
}

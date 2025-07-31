package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.core.distributor.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.distributors.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Service interface for distributor operations.
 */
public interface DistributorsService {

    // ProductApi methods

    /**
     * Create a new product.
     *
     * @param distributorId the ID of the distributor
     * @param productRequest the product to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> createProduct(Long distributorId, ProductRequest productRequest);

    /**
     * Delete a product by ID.
     *
     * @param distributorId the ID of the distributor
     * @param productId the ID of the product to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProduct(Long distributorId, Long productId);

    /**
     * Filter products based on criteria.
     *
     * @param distributorId the ID of the distributor
     * @param filterRequestProductDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterProducts(Long distributorId, FilterRequestProductDTO filterRequestProductDTO);

    /**
     * Get active products by distributor ID.
     *
     * @param distributorId the ID of the distributor
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> getActiveProductsByDistributorId(Long distributorId);

    /**
     * Get a product by ID.
     *
     * @param distributorId the ID of the distributor
     * @param productId the ID of the product to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> getProductById(Long distributorId, Long productId);

    /**
     * Get products by distributor ID.
     *
     * @param distributorId the ID of the distributor
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> getProductsByDistributorId(Long distributorId);

    /**
     * Get products by distributor ID and category.
     *
     * @param distributorId the ID of the distributor
     * @param categoryId the ID of the category
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> getProductsByDistributorIdAndCategory(Long distributorId, Long categoryId);

    /**
     * Update a product.
     *
     * @param distributorId the ID of the distributor
     * @param productId the ID of the product to update
     * @param productRequest the product to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> updateProduct(Long distributorId, Long productId, ProductRequest productRequest);

    // ProductCategoryApi methods

    /**
     * Create a new product category.
     *
     * @param productCategoryRequest the product category to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> createProductCategory(ProductCategoryRequest productCategoryRequest);

    /**
     * Delete a product category by ID.
     *
     * @param id the ID of the product category to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductCategory(Long id);

    /**
     * Get all active product categories.
     *
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> getActiveProductCategories();

    /**
     * Get all product categories.
     *
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> getAllProductCategories();

    /**
     * Get a product category by code.
     *
     * @param code the code of the product category to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> getProductCategoryByCode(String code);

    /**
     * Get a product category by ID.
     *
     * @param id the ID of the product category to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> getProductCategoryById(Long id);

    /**
     * Update a product category.
     *
     * @param id the ID of the product category to update
     * @param productCategoryRequest the product category to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> updateProductCategory(Long id, ProductCategoryRequest productCategoryRequest);

    // ShipmentApi methods

    /**
     * Create a new shipment.
     *
     * @param shipmentRequest the shipment to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> createShipment(ShipmentRequest shipmentRequest);

    /**
     * Delete a shipment by ID.
     *
     * @param id the ID of the shipment to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteShipment(Long id);

    /**
     * Filter shipments based on criteria.
     *
     * @param filterRequestShipmentDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterShipments(FilterRequestShipmentDTO filterRequestShipmentDTO);

    /**
     * Get a shipment by ID.
     *
     * @param id the ID of the shipment to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> getShipmentById(Long id);

    /**
     * Get a shipment by tracking number.
     *
     * @param trackingNumber the tracking number of the shipment to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> getShipmentByTrackingNumber(String trackingNumber);

    /**
     * Get shipments by leasing contract ID.
     *
     * @param leasingContractId the ID of the leasing contract
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> getShipmentsByLeasingContractId(Long leasingContractId);

    /**
     * Get shipments by product ID.
     *
     * @param productId the ID of the product
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> getShipmentsByProductId(Long productId);

    /**
     * Get shipments by status.
     *
     * @param status the status of the shipments to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> getShipmentsByStatus(String status);

    /**
     * Update a shipment.
     *
     * @param id the ID of the shipment to update
     * @param shipmentRequest the shipment to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> updateShipment(Long id, ShipmentRequest shipmentRequest);

    /**
     * Update a shipment status.
     *
     * @param id the ID of the shipment to update
     * @param status the new status
     * @param updatedBy the ID of the user updating the status
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ShipmentDTO>> updateShipmentStatus(Long id, String status, Long updatedBy);

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

    /**
     * Get all lending configurations for a distributor.
     *
     * @param distributorId ID of the distributor
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationsByDistributorId(Long distributorId);

    /**
     * Approve a leasing contract.
     *
     * @param id ID of the leasing contract to approve
     * @param approvedBy ID of the user approving the contract
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> approveLeasingContract(Long id, Long approvedBy);

    /**
     * Create a new leasing contract.
     *
     * @param leasingContractRequest the leasing contract to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> createLeasingContract(LeasingContractRequest leasingContractRequest);

    /**
     * Delete a leasing contract.
     *
     * @param id ID of the leasing contract to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteLeasingContract(Long id);

    /**
     * Filter leasing contracts.
     *
     * @param filterRequestLeasingContractDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterLeasingContracts(com.catalis.core.distributor.sdk.model.FilterRequestLeasingContractDTO filterRequestLeasingContractDTO);

    /**
     * Get leasing contract by contract ID.
     *
     * @param contractId Contract ID of the leasing contract to retrieve
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractByContractId(Long contractId);

    /**
     * Get leasing contract by ID.
     *
     * @param id ID of the leasing contract to retrieve
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractById(Long id);

    /**
     * Get leasing contracts by distributor.
     *
     * @param distributorId ID of the distributor
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByDistributorId(Long distributorId);

    /**
     * Get leasing contracts by party.
     *
     * @param partyId ID of the party
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByPartyId(Long partyId);

    /**
     * Get leasing contracts by product.
     *
     * @param productId ID of the product
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByProductId(Long productId);

    /**
     * Get leasing contracts by status.
     *
     * @param status Status value to filter leasing contracts
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByStatus(String status);

    /**
     * Update an existing leasing contract.
     *
     * @param id ID of the leasing contract to update
     * @param leasingContractRequest the leasing contract to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> updateLeasingContract(Long id, LeasingContractRequest leasingContractRequest);

    /**
     * Create a leasing contract from a lending configuration.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @param configId ID of the lending configuration
     * @param leasingContractRequest the leasing contract to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LeasingContractDTO>> createLeasingContractFromConfiguration(Long distributorId, Long productId, Long configId, LeasingContractRequest leasingContractRequest);

    /**
     * Create a new lending configuration.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @param lendingConfigurationRequest the lending configuration to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> createLendingConfiguration(Long distributorId, Long productId, LendingConfigurationRequest lendingConfigurationRequest);

    /**
     * Delete a lending configuration.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @param configId ID of the lending configuration to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteLendingConfiguration(Long distributorId, Long productId, Long configId);

    /**
     * Filter lending configurations.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @param filterRequestLendingConfigurationDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterLendingConfigurations(Long distributorId, Long productId, com.catalis.core.distributor.sdk.model.FilterRequestLendingConfigurationDTO filterRequestLendingConfigurationDTO);

    /**
     * Get all active lending configurations for a product.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> getActiveLendingConfigurationsByProductId(Long distributorId, Long productId);

    /**
     * Get default lending configuration for a product.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> getDefaultLendingConfigurationByProductId(Long distributorId, Long productId);

    /**
     * Get lending configuration by ID.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @param configId ID of the lending configuration to retrieve
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationById(Long distributorId, Long productId, Long configId);

    /**
     * Get all lending configurations for a product.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationsByProductId(Long distributorId, Long productId);

    /**
     * Get lending configurations by product and lending type.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @param lendingTypeId ID of the lending type
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationsByProductIdAndLendingType(Long distributorId, Long productId, Long lendingTypeId);

    /**
     * Update an existing lending configuration.
     *
     * @param distributorId ID of the distributor
     * @param productId ID of the product
     * @param configId ID of the lending configuration to update
     * @param lendingConfigurationRequest the lending configuration to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingConfigurationDTO>> updateLendingConfiguration(Long distributorId, Long productId, Long configId, LendingConfigurationRequest lendingConfigurationRequest);

    /**
     * Create a new lending type.
     *
     * @param lendingTypeRequest the lending type to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingTypeDTO>> createLendingType(LendingTypeRequest lendingTypeRequest);

    /**
     * Delete lending type.
     *
     * @param id ID of the lending type to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteLendingType(Long id);

    /**
     * Get all active lending types.
     *
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingTypeDTO>> getActiveLendingTypes();

    /**
     * Get all lending types.
     *
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingTypeDTO>> getAllLendingTypes();

    /**
     * Get lending type by code.
     *
     * @param code Code of the lending type to retrieve
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingTypeDTO>> getLendingTypeByCode(String code);

    /**
     * Get lending type by ID.
     *
     * @param id ID of the lending type to retrieve
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingTypeDTO>> getLendingTypeById(Long id);

    /**
     * Update lending type.
     *
     * @param id ID of the lending type to update
     * @param lendingTypeRequest the lending type to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<LendingTypeDTO>> updateLendingType(Long id, LendingTypeRequest lendingTypeRequest);
}

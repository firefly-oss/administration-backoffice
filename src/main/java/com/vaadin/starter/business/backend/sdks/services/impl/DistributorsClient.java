package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.core.distributor.sdk.api.*;
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
    private final DistributorLendingConfigurationApi distributorLendingConfigurationApi;
    private final LeasingContractApi leasingContractApi;
    private final LendingConfigurationApi lendingConfigurationApi;
    private final LendingTypeApi lendingTypeApi;
    private final ProductApi productApi;
    private final ProductCategoryApi productCategoryApi;
    private final ShipmentApi shipmentApi;

    @Autowired
    public DistributorsClient(ApiClient apiClient, DistributorsMapper distributorsMapper) {
        this.distributorApi = new DistributorApi(apiClient);
        this.distributorAuditLogApi = new DistributorAuditLogApi(apiClient);
        this.distributorBrandingApi = new DistributorBrandingApi(apiClient);
        this.distributorsMapper = distributorsMapper;
        this.distributorLendingConfigurationApi = new DistributorLendingConfigurationApi(apiClient);
        this.leasingContractApi = new LeasingContractApi(apiClient);
        this.lendingConfigurationApi = new LendingConfigurationApi(apiClient);
        this.lendingTypeApi = new LendingTypeApi(apiClient);
        this.productApi = new ProductApi(apiClient);
        this.productCategoryApi = new ProductCategoryApi(apiClient);
        this.shipmentApi = new ShipmentApi(apiClient);
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

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationsByDistributorId(Long distributorId) {
        return distributorLendingConfigurationApi.getLendingConfigurationsByDistributorIdWithHttpInfo(distributorId);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> approveLeasingContract(Long id, Long approvedBy) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return leasingContractApi.approveLeasingContractWithHttpInfo(id, approvedBy, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> createLeasingContract(LeasingContractRequest leasingContractRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return leasingContractApi.createLeasingContractWithHttpInfo(distributorsMapper.leasingContractRequestToDto(leasingContractRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteLeasingContract(Long id) {
        return leasingContractApi.deleteLeasingContractWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterLeasingContracts(FilterRequestLeasingContractDTO filterRequestLeasingContractDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return leasingContractApi.filterLeasingContractsWithHttpInfo(filterRequestLeasingContractDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractByContractId(Long contractId) {
        return leasingContractApi.getLeasingContractByContractIdWithHttpInfo(contractId);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractById(Long id) {
        return leasingContractApi.getLeasingContractByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByDistributorId(Long distributorId) {
        return leasingContractApi.getLeasingContractsByDistributorIdWithHttpInfo(distributorId);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByPartyId(Long partyId) {
        return leasingContractApi.getLeasingContractsByPartyIdWithHttpInfo(partyId);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByProductId(Long productId) {
        return leasingContractApi.getLeasingContractsByProductIdWithHttpInfo(productId);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> getLeasingContractsByStatus(String status) {
        return leasingContractApi.getLeasingContractsByStatusWithHttpInfo(status);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> updateLeasingContract(Long id, LeasingContractRequest leasingContractRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return leasingContractApi.updateLeasingContractWithHttpInfo(id, distributorsMapper.leasingContractRequestToDto(leasingContractRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<LeasingContractDTO>> createLeasingContractFromConfiguration(Long distributorId, Long productId, Long configId, LeasingContractRequest leasingContractRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return lendingConfigurationApi.createLeasingContractFromConfigurationWithHttpInfo(distributorId, productId, configId, distributorsMapper.leasingContractRequestToDto(leasingContractRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> createLendingConfiguration(Long distributorId, Long productId, LendingConfigurationRequest lendingConfigurationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return lendingConfigurationApi.createLendingConfigurationWithHttpInfo(distributorId, productId, distributorsMapper.lendingConfigurationRequestToDto(lendingConfigurationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteLendingConfiguration(Long distributorId, Long productId, Long configId) {
        return lendingConfigurationApi.deleteLendingConfigurationWithHttpInfo(distributorId, productId, configId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterLendingConfigurations(Long distributorId, Long productId, FilterRequestLendingConfigurationDTO filterRequestLendingConfigurationDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return lendingConfigurationApi.filterLendingConfigurationsWithHttpInfo(distributorId, productId, filterRequestLendingConfigurationDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> getActiveLendingConfigurationsByProductId(Long distributorId, Long productId) {
        return lendingConfigurationApi.getActiveLendingConfigurationsByProductIdWithHttpInfo(distributorId, productId);
    }

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> getDefaultLendingConfigurationByProductId(Long distributorId, Long productId) {
        return lendingConfigurationApi.getDefaultLendingConfigurationByProductIdWithHttpInfo(distributorId, productId);
    }

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationById(Long distributorId, Long productId, Long configId) {
        return lendingConfigurationApi.getLendingConfigurationByIdWithHttpInfo(distributorId, productId, configId);
    }

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationsByProductId(Long distributorId, Long productId) {
        return lendingConfigurationApi.getLendingConfigurationsByProductIdWithHttpInfo(distributorId, productId);
    }

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> getLendingConfigurationsByProductIdAndLendingType(Long distributorId, Long productId, Long lendingTypeId) {
        return lendingConfigurationApi.getLendingConfigurationsByProductIdAndLendingTypeWithHttpInfo(distributorId, productId, lendingTypeId);
    }

    @Override
    public Mono<ResponseEntity<LendingConfigurationDTO>> updateLendingConfiguration(Long distributorId, Long productId, Long configId, LendingConfigurationRequest lendingConfigurationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return lendingConfigurationApi.updateLendingConfigurationWithHttpInfo(distributorId, productId, configId, distributorsMapper.lendingConfigurationRequestToDto(lendingConfigurationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<LendingTypeDTO>> createLendingType(LendingTypeRequest lendingTypeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return lendingTypeApi.createLendingTypeWithHttpInfo(distributorsMapper.lendingTypeRequestToDto(lendingTypeRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteLendingType(Long id) {
        return lendingTypeApi.deleteLendingTypeWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<LendingTypeDTO>> getActiveLendingTypes() {
        return lendingTypeApi.getActiveLendingTypesWithHttpInfo();
    }

    @Override
    public Mono<ResponseEntity<LendingTypeDTO>> getAllLendingTypes() {
        return lendingTypeApi.getAllLendingTypesWithHttpInfo();
    }

    @Override
    public Mono<ResponseEntity<LendingTypeDTO>> getLendingTypeByCode(String code) {
        return lendingTypeApi.getLendingTypeByCodeWithHttpInfo(code);
    }

    @Override
    public Mono<ResponseEntity<LendingTypeDTO>> getLendingTypeById(Long id) {
        return lendingTypeApi.getLendingTypeByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<LendingTypeDTO>> updateLendingType(Long id, LendingTypeRequest lendingTypeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return lendingTypeApi.updateLendingTypeWithHttpInfo(id, distributorsMapper.lendingTypeRequestToDto(lendingTypeRequest), xIdempotencyKey);
    }

    // ProductApi methods

    @Override
    public Mono<ResponseEntity<ProductDTO>> createProduct(Long distributorId, ProductRequest productRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productApi.createProductWithHttpInfo(distributorId, distributorsMapper.productRequestToDto(productRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProduct(Long distributorId, Long productId) {
        return productApi.deleteProductWithHttpInfo(distributorId, productId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterProducts(Long distributorId, FilterRequestProductDTO filterRequestProductDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productApi.filterProductsWithHttpInfo(distributorId, filterRequestProductDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> getActiveProductsByDistributorId(Long distributorId) {
        return productApi.getActiveProductsByDistributorIdWithHttpInfo(distributorId);
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> getProductById(Long distributorId, Long productId) {
        return productApi.getProductByIdWithHttpInfo(distributorId, productId);
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> getProductsByDistributorId(Long distributorId) {
        return productApi.getProductsByDistributorIdWithHttpInfo(distributorId);
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> getProductsByDistributorIdAndCategory(Long distributorId, Long categoryId) {
        return productApi.getProductsByDistributorIdAndCategoryWithHttpInfo(distributorId, categoryId);
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> updateProduct(Long distributorId, Long productId, ProductRequest productRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productApi.updateProductWithHttpInfo(distributorId, productId, distributorsMapper.productRequestToDto(productRequest), xIdempotencyKey);
    }

    // ProductCategoryApi methods

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> createProductCategory(ProductCategoryRequest productCategoryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productCategoryApi.createProductCategoryWithHttpInfo(distributorsMapper.productCategoryRequestToDto(productCategoryRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductCategory(Long id) {
        return productCategoryApi.deleteProductCategoryWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> getActiveProductCategories() {
        return productCategoryApi.getActiveProductCategoriesWithHttpInfo();
    }

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> getAllProductCategories() {
        return productCategoryApi.getAllProductCategoriesWithHttpInfo();
    }

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> getProductCategoryByCode(String code) {
        return productCategoryApi.getProductCategoryByCodeWithHttpInfo(code);
    }

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> getProductCategoryById(Long id) {
        return productCategoryApi.getProductCategoryByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> updateProductCategory(Long id, ProductCategoryRequest productCategoryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productCategoryApi.updateProductCategoryWithHttpInfo(id, distributorsMapper.productCategoryRequestToDto(productCategoryRequest), xIdempotencyKey);
    }

    // ShipmentApi methods

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> createShipment(ShipmentRequest shipmentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return shipmentApi.createShipmentWithHttpInfo(distributorsMapper.shipmentRequestToDto(shipmentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteShipment(Long id) {
        return shipmentApi.deleteShipmentWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterShipments(FilterRequestShipmentDTO filterRequestShipmentDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return shipmentApi.filterShipmentsWithHttpInfo(filterRequestShipmentDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> getShipmentById(Long id) {
        return shipmentApi.getShipmentByIdWithHttpInfo(id);
    }

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> getShipmentByTrackingNumber(String trackingNumber) {
        return shipmentApi.getShipmentByTrackingNumberWithHttpInfo(trackingNumber);
    }

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> getShipmentsByLeasingContractId(Long leasingContractId) {
        return shipmentApi.getShipmentsByLeasingContractIdWithHttpInfo(leasingContractId);
    }

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> getShipmentsByProductId(Long productId) {
        return shipmentApi.getShipmentsByProductIdWithHttpInfo(productId);
    }

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> getShipmentsByStatus(String status) {
        return shipmentApi.getShipmentsByStatusWithHttpInfo(status);
    }

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> updateShipment(Long id, ShipmentRequest shipmentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return shipmentApi.updateShipmentWithHttpInfo(id, distributorsMapper.shipmentRequestToDto(shipmentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<ShipmentDTO>> updateShipmentStatus(Long id, String status, Long updatedBy) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return shipmentApi.updateShipmentStatusWithHttpInfo(id, status, updatedBy, xIdempotencyKey);
    }
}

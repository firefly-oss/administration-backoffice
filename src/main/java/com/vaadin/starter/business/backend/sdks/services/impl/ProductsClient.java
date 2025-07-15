package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.common.product.sdk.api.*;
import com.catalis.common.product.sdk.invoker.ApiClient;
import com.catalis.common.product.sdk.model.*;
import com.vaadin.starter.business.backend.mapper.products.ProductsMapper;
import com.vaadin.starter.business.backend.sdks.services.ProductsService;
import com.vaadin.starter.business.backend.sdks.services.rest.products.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ProductsClient implements ProductsService {

    private final ProductApi productApi;
    private final FeeStructureApi feeStructureApi;
    private final ProductBundleApi productBundleApi;
    private final ProductCategoryApi productCategoryApi;
    private final FeeApplicationRuleApi feeApplicationRuleApi;
    private final ProductDocumentationApi productDocumentationApi;
    private final ProductFeatureApi productFeatureApi;
    private final ProductFeeComponentApi productFeeComponentApi;
    private final ProductFeeStructureApi productFeeStructureApi;
    private final ProductLifecycleApi productLifecycleApi;
    private final ProductLimitApi productLimitApi;
    private final ProductLocalizationApi productLocalizationApi;
    private final ProductPricingApi productPricingApi;
    private final ProductRelationshipApi productRelationshipApi;
    private final ProductVersionApi productVersionApi;
    private final ProductsMapper productMapper;

    @Autowired
    public ProductsClient(ApiClient apiClient, ProductsMapper productMapper) {
        this.productApi = new ProductApi(apiClient);
        this.feeStructureApi = new FeeStructureApi(apiClient);
        this.productBundleApi = new ProductBundleApi(apiClient);
        this.productCategoryApi = new ProductCategoryApi(apiClient);
        this.feeApplicationRuleApi = new FeeApplicationRuleApi(apiClient);
        this.productDocumentationApi = new ProductDocumentationApi(apiClient);
        this.productFeatureApi = new ProductFeatureApi(apiClient);
        this.productFeeComponentApi = new ProductFeeComponentApi(apiClient);
        this.productFeeStructureApi = new ProductFeeStructureApi(apiClient);
        this.productLifecycleApi = new ProductLifecycleApi(apiClient);
        this.productLimitApi = new ProductLimitApi(apiClient);
        this.productLocalizationApi = new ProductLocalizationApi(apiClient);
        this.productPricingApi = new ProductPricingApi(apiClient);
        this.productRelationshipApi = new ProductRelationshipApi(apiClient);
        this.productVersionApi = new ProductVersionApi(apiClient);
        this.productMapper = productMapper;
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> createProduct(ProductRequest productRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productApi.createProductWithHttpInfo(productMapper.productRequestToDto(productRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProduct(Long productId) {
        return productApi.deleteProductWithHttpInfo(productId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponseProductDTO>> getAllProducts(String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productApi.getAllProductsWithHttpInfo(pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> getProduct(Long productId) {
        return productApi.getProductWithHttpInfo(productId);
    }

    @Override
    public Mono<ResponseEntity<ProductDTO>> updateProduct(Long productId, ProductRequest productRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productApi.updateProductWithHttpInfo(productId, productMapper.productRequestToDto(productRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<FeeStructureDTO>> createFeeStructure(FeeStructureRequest feeStructureRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return feeStructureApi.createFeeStructure1WithHttpInfo(productMapper.feeStructureRequestToDto(feeStructureRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteFeeStructure(Long feeStructureId) {
        return feeStructureApi.deleteFeeStructure1WithHttpInfo(feeStructureId);
    }

    @Override
    public Mono<ResponseEntity<FeeStructureDTO>> getFeeStructure(Long feeStructureId) {
        return feeStructureApi.getFeeStructure1WithHttpInfo(feeStructureId);
    }

    @Override
    public Mono<ResponseEntity<FeeStructureDTO>> updateFeeStructure(Long feeStructureId, FeeStructureRequest feeStructureRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return feeStructureApi.updateFeeStructure1WithHttpInfo(feeStructureId, productMapper.feeStructureRequestToDto(feeStructureRequest), xIdempotencyKey);
    }

    // Product Bundle operations

    @Override
    public Mono<ResponseEntity<ProductBundleDTO>> createProductBundle(ProductBundleRequest productBundleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productBundleApi.createWithHttpInfo(productMapper.productBundleRequestToDto(productBundleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductBundle(Long bundleId) {
        return productBundleApi.deleteWithHttpInfo(bundleId);
    }

    @Override
    public Mono<ResponseEntity<ProductBundleDTO>> getProductBundle(Long bundleId) {
        return productBundleApi.getByIdWithHttpInfo(bundleId);
    }

    @Override
    public Mono<ResponseEntity<ProductBundleDTO>> updateProductBundle(Long bundleId, ProductBundleRequest productBundleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productBundleApi.updateWithHttpInfo(bundleId, productMapper.productBundleRequestToDto(productBundleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductBundles(String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productBundleApi.getAllWithHttpInfo(pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ProductBundleItemDTO>> createProductBundleItem(Long bundleId, ProductBundleItemRequest productBundleItemRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productBundleApi.createProductBundleItemWithHttpInfo(bundleId, productMapper.productBundleItemRequestToDto(productBundleItemRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductBundleItem(Long bundleId, Long itemId) {
        return productBundleApi.deleteProductBundleItemWithHttpInfo(bundleId, itemId);
    }

    @Override
    public Mono<ResponseEntity<ProductBundleItemDTO>> getProductBundleItem(Long bundleId, Long itemId) {
        return productBundleApi.getProductBundleItemByIdWithHttpInfo(bundleId, itemId);
    }

    @Override
    public Mono<ResponseEntity<ProductBundleItemDTO>> updateProductBundleItem(Long bundleId, Long itemId, ProductBundleItemRequest productBundleItemRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productBundleApi.updateProductBundleItemWithHttpInfo(bundleId, itemId, productMapper.productBundleItemRequestToDto(productBundleItemRequest), xIdempotencyKey);
    }

    // Product Category operations

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> createProductCategory(ProductCategoryRequest productCategoryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productCategoryApi.createCategoryWithHttpInfo(productMapper.productCategoryRequestToDto(productCategoryRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductCategory(Long categoryId) {
        return productCategoryApi.deleteCategoryWithHttpInfo(categoryId);
    }

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> getProductCategory(Long categoryId) {
        return productCategoryApi.getCategoryWithHttpInfo(categoryId);
    }

    @Override
    public Mono<ResponseEntity<ProductCategoryDTO>> updateProductCategory(Long categoryId, ProductCategoryRequest productCategoryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productCategoryApi.updateCategoryWithHttpInfo(categoryId, productMapper.productCategoryRequestToDto(productCategoryRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<ProductCategorySubtypeDTO>> createProductCategorySubtype(Long categoryId, ProductCategorySubtypeRequest productCategorySubtypeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productCategoryApi.createSubtypeWithHttpInfo(categoryId, productMapper.productCategorySubtypeRequestToDto(productCategorySubtypeRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductCategorySubtype(Long categoryId, Long subtypeId) {
        return productCategoryApi.deleteSubtypeWithHttpInfo(categoryId, subtypeId);
    }

    @Override
    public Mono<ResponseEntity<ProductCategorySubtypeDTO>> getProductCategorySubtype(Long categoryId, Long subtypeId) {
        return productCategoryApi.getSubtypeWithHttpInfo(categoryId, subtypeId);
    }

    @Override
    public Mono<ResponseEntity<ProductCategorySubtypeDTO>> updateProductCategorySubtype(Long categoryId, Long subtypeId, ProductCategorySubtypeRequest productCategorySubtypeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productCategoryApi.updateSubtypeWithHttpInfo(categoryId, subtypeId, productMapper.productCategorySubtypeRequestToDto(productCategorySubtypeRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductCategorySubtypes(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productCategoryApi.getAllSubtypesWithHttpInfo(categoryId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Fee Application Rule operations

    @Override
    public Mono<ResponseEntity<FeeApplicationRuleDTO>> createFeeApplicationRule(Long feeStructureId, Long componentId, FeeApplicationRuleRequest feeApplicationRuleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return feeApplicationRuleApi.createRuleWithHttpInfo(feeStructureId, componentId, productMapper.feeApplicationRuleRequestToDto(feeApplicationRuleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteFeeApplicationRule(Long feeStructureId, Long componentId, Long ruleId) {
        return feeApplicationRuleApi.deleteRuleWithHttpInfo(feeStructureId, componentId, ruleId);
    }

    @Override
    public Mono<ResponseEntity<FeeApplicationRuleDTO>> getFeeApplicationRule(Long feeStructureId, Long componentId, Long ruleId) {
        return feeApplicationRuleApi.getRuleWithHttpInfo(feeStructureId, componentId, ruleId);
    }

    @Override
    public Mono<ResponseEntity<FeeApplicationRuleDTO>> updateFeeApplicationRule(Long feeStructureId, Long componentId, Long ruleId, FeeApplicationRuleRequest feeApplicationRuleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return feeApplicationRuleApi.updateRuleWithHttpInfo(feeStructureId, componentId, ruleId, productMapper.feeApplicationRuleRequestToDto(feeApplicationRuleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getFeeApplicationRulesByComponentId(Long feeStructureId, Long componentId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return feeApplicationRuleApi.getRulesByComponentIdWithHttpInfo(feeStructureId, componentId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Documentation operations

    @Override
    public Mono<ResponseEntity<ProductDocumentationDTO>> createProductDocumentation(Long productId, ProductDocumentationRequest productDocumentationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productDocumentationApi.createDocumentationWithHttpInfo(productId, productMapper.productDocumentationRequestToDto(productDocumentationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductDocumentation(Long productId, Long docId) {
        return productDocumentationApi.deleteDocumentationWithHttpInfo(productId, docId);
    }

    @Override
    public Mono<ResponseEntity<ProductDocumentationDTO>> getProductDocumentation(Long productId, Long docId) {
        return productDocumentationApi.getDocumentationWithHttpInfo(productId, docId);
    }

    @Override
    public Mono<ResponseEntity<ProductDocumentationDTO>> updateProductDocumentation(Long productId, Long docId, ProductDocumentationRequest productDocumentationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productDocumentationApi.updateDocumentationWithHttpInfo(productId, docId, productMapper.productDocumentationRequestToDto(productDocumentationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductDocumentation(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productDocumentationApi.getAllDocumentationWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Feature operations

    @Override
    public Mono<ResponseEntity<ProductFeatureDTO>> createProductFeature(Long productId, ProductFeatureRequest productFeatureRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productFeatureApi.createFeatureWithHttpInfo(productId, productMapper.productFeatureRequestToDto(productFeatureRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductFeature(Long productId, Long featureId) {
        return productFeatureApi.deleteFeatureWithHttpInfo(productId, featureId);
    }

    @Override
    public Mono<ResponseEntity<ProductFeatureDTO>> getProductFeature(Long productId, Long featureId) {
        return productFeatureApi.getFeatureWithHttpInfo(productId, featureId);
    }

    @Override
    public Mono<ResponseEntity<ProductFeatureDTO>> updateProductFeature(Long productId, Long featureId, ProductFeatureRequest productFeatureRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productFeatureApi.updateFeatureWithHttpInfo(productId, featureId, productMapper.productFeatureRequestToDto(productFeatureRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductFeatures(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productFeatureApi.getAllFeaturesWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Fee Component operations

    @Override
    public Mono<ResponseEntity<FeeComponentDTO>> createProductFeeComponent(Long feeStructureId, ProductFeeComponentRequest productFeeComponentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productFeeComponentApi.createFeeComponentWithHttpInfo(feeStructureId, productMapper.productFeeComponentRequestToDto(productFeeComponentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductFeeComponent(Long feeStructureId, Long componentId) {
        return productFeeComponentApi.deleteFeeComponentWithHttpInfo(feeStructureId, componentId);
    }

    @Override
    public Mono<ResponseEntity<FeeComponentDTO>> getProductFeeComponent(Long feeStructureId, Long componentId) {
        return productFeeComponentApi.getFeeComponentWithHttpInfo(feeStructureId, componentId);
    }

    @Override
    public Mono<ResponseEntity<FeeComponentDTO>> updateProductFeeComponent(Long feeStructureId, Long componentId, ProductFeeComponentRequest productFeeComponentRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productFeeComponentApi.updateFeeComponentWithHttpInfo(feeStructureId, componentId, productMapper.productFeeComponentRequestToDto(productFeeComponentRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getProductFeeComponentsByFeeStructureId(Long feeStructureId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productFeeComponentApi.getByFeeStructureIdWithHttpInfo(feeStructureId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Fee Structure operations

    @Override
    public Mono<ResponseEntity<ProductFeeStructureDTO>> createProductFeeStructure(Long productId, ProductFeeStructureRequest productFeeStructureRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productFeeStructureApi.createFeeStructureWithHttpInfo(productId, productMapper.productFeeStructureRequestToDto(productFeeStructureRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductFeeStructure(Long productId, Long productFeeStructId) {
        return productFeeStructureApi.deleteFeeStructureWithHttpInfo(productId, productFeeStructId);
    }

    @Override
    public Mono<ResponseEntity<ProductFeeStructureDTO>> getProductFeeStructure(Long productId, Long productFeeStructId) {
        return productFeeStructureApi.getFeeStructureWithHttpInfo(productId, productFeeStructId);
    }

    @Override
    public Mono<ResponseEntity<ProductFeeStructureDTO>> updateProductFeeStructure(Long productId, Long productFeeStructId, ProductFeeStructureRequest productFeeStructureRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productFeeStructureApi.updateFeeStructureWithHttpInfo(productId, productFeeStructId, productMapper.productFeeStructureRequestToDto(productFeeStructureRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductFeeStructures(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productFeeStructureApi.getAllFeeStructuresWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Lifecycle operations

    @Override
    public Mono<ResponseEntity<ProductLifecycleDTO>> createProductLifecycle(Long productId, ProductLifecycleRequest productLifecycleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productLifecycleApi.createProductLifecycleWithHttpInfo(productId, productMapper.productLifecycleRequestToDto(productLifecycleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductLifecycle(Long productId, Long lifecycleId) {
        return productLifecycleApi.deleteProductLifecycleWithHttpInfo(productId, lifecycleId);
    }

    @Override
    public Mono<ResponseEntity<ProductLifecycleDTO>> getProductLifecycle(Long productId, Long lifecycleId) {
        return productLifecycleApi.getProductLifecycleWithHttpInfo(productId, lifecycleId);
    }

    @Override
    public Mono<ResponseEntity<ProductLifecycleDTO>> updateProductLifecycle(Long productId, Long lifecycleId, ProductLifecycleRequest productLifecycleRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productLifecycleApi.updateProductLifecycleWithHttpInfo(productId, lifecycleId, productMapper.productLifecycleRequestToDto(productLifecycleRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getProductLifecycles(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productLifecycleApi.getProductLifecyclesWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Limit operations

    @Override
    public Mono<ResponseEntity<ProductLimitDTO>> createProductLimit(Long productId, ProductLimitRequest productLimitRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productLimitApi.createProductLimitWithHttpInfo(productId, productMapper.productLimitRequestToDto(productLimitRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductLimit(Long productId, Long limitId) {
        return productLimitApi.deleteProductLimitWithHttpInfo(productId, limitId);
    }

    @Override
    public Mono<ResponseEntity<ProductLimitDTO>> getProductLimit(Long productId, Long limitId) {
        return productLimitApi.getProductLimitWithHttpInfo(productId, limitId);
    }

    @Override
    public Mono<ResponseEntity<ProductLimitDTO>> updateProductLimit(Long productId, Long limitId, ProductLimitRequest productLimitRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productLimitApi.updateProductLimitWithHttpInfo(productId, limitId, productMapper.productLimitRequestToDto(productLimitRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductLimits(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productLimitApi.getAllProductLimitsWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Localization operations

    @Override
    public Mono<ResponseEntity<ProductLocalizationDTO>> createProductLocalization(Long productId, ProductLocalizationRequest productLocalizationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productLocalizationApi.createLocalization1WithHttpInfo(productId, productMapper.productLocalizationRequestToDto(productLocalizationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductLocalization(Long productId, Long localizationId) {
        return productLocalizationApi.deleteLocalization1WithHttpInfo(productId, localizationId);
    }

    @Override
    public Mono<ResponseEntity<ProductLocalizationDTO>> getProductLocalization(Long productId, Long localizationId) {
        return productLocalizationApi.getLocalizationByIdWithHttpInfo(productId, localizationId);
    }

    @Override
    public Mono<ResponseEntity<ProductLocalizationDTO>> updateProductLocalization(Long productId, Long localizationId, ProductLocalizationRequest productLocalizationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productLocalizationApi.updateLocalization1WithHttpInfo(productId, localizationId, productMapper.productLocalizationRequestToDto(productLocalizationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductLocalizations(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productLocalizationApi.getAllLocalizations1WithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Pricing operations

    @Override
    public Mono<ResponseEntity<ProductPricingDTO>> createProductPricing(Long productId, ProductPricingRequest productPricingRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productPricingApi.createPricingWithHttpInfo(productId, productMapper.productPricingRequestToDto(productPricingRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductPricing(Long productId, Long pricingId) {
        return productPricingApi.deletePricingWithHttpInfo(productId, pricingId);
    }

    @Override
    public Mono<ResponseEntity<ProductPricingDTO>> getProductPricing(Long productId, Long pricingId) {
        return productPricingApi.getPricingWithHttpInfo(productId, pricingId);
    }

    @Override
    public Mono<ResponseEntity<ProductPricingDTO>> updateProductPricing(Long productId, Long pricingId, ProductPricingRequest productPricingRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productPricingApi.updatePricingWithHttpInfo(productId, pricingId, productMapper.productPricingRequestToDto(productPricingRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductPricings(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productPricingApi.getAllPricingsWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Relationship operations

    @Override
    public Mono<ResponseEntity<ProductRelationshipDTO>> createProductRelationship(Long productId, ProductRelationshipRequest productRelationshipRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productRelationshipApi.createRelationshipWithHttpInfo(productId, productMapper.productRelationshipRequestToDto(productRelationshipRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductRelationship(Long productId, Long relationshipId) {
        return productRelationshipApi.deleteRelationshipWithHttpInfo(productId, relationshipId);
    }

    @Override
    public Mono<ResponseEntity<ProductRelationshipDTO>> getProductRelationship(Long productId, Long relationshipId) {
        return productRelationshipApi.getRelationshipWithHttpInfo(productId, relationshipId);
    }

    @Override
    public Mono<ResponseEntity<ProductRelationshipDTO>> updateProductRelationship(Long productId, Long relationshipId, ProductRelationshipRequest productRelationshipRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productRelationshipApi.updateRelationshipWithHttpInfo(productId, relationshipId, productMapper.productRelationshipRequestToDto(productRelationshipRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductRelationships(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productRelationshipApi.getAllRelationshipsWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }

    // Product Version operations

    @Override
    public Mono<ResponseEntity<ProductVersionDTO>> createProductVersion(Long productId, ProductVersionRequest productVersionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productVersionApi.createProductVersionWithHttpInfo(productId, productMapper.productVersionRequestToDto(productVersionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteProductVersion(Long productId, Long versionId) {
        return productVersionApi.deleteProductVersionWithHttpInfo(productId, versionId);
    }

    @Override
    public Mono<ResponseEntity<ProductVersionDTO>> getProductVersion(Long productId, Long versionId) {
        return productVersionApi.getProductVersionWithHttpInfo(productId, versionId);
    }

    @Override
    public Mono<ResponseEntity<ProductVersionDTO>> updateProductVersion(Long productId, Long versionId, ProductVersionRequest productVersionRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return productVersionApi.updateProductVersionWithHttpInfo(productId, versionId, productMapper.productVersionRequestToDto(productVersionRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> getAllProductVersions(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return productVersionApi.getAllProductVersionsWithHttpInfo(productId, pageNumber, pageSize, sortBy, sortDirection);
    }
}

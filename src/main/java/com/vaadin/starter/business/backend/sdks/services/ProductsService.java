package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.common.product.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.products.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Service interface for product operations.
 */
public interface ProductsService {

    /**
     * Create a new product.
     *
     * @param productRequest the product to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> createProduct(ProductRequest productRequest);

    /**
     * Delete a product by ID.
     *
     * @param productId the ID of the product to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProduct(Long productId);

    /**
     * Retrieves a paginated list of products with sorting options.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting (e.g., ASC for ascending or DESC for descending)
     * @return a Mono containing a ResponseEntity with the paginated response for the requested products
     */
    Mono<ResponseEntity<PaginationResponseProductDTO>> getAllProducts(String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Get a product by ID.
     *
     * @param productId the ID of the product to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> getProduct(Long productId);

    /**
     * Update a product.
     *
     * @param productId the ID of the product to update
     * @param productRequest the product to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDTO>> updateProduct(Long productId, ProductRequest productRequest);

    /**
     * Create a new fee structure.
     *
     * @param feeStructureRequest the fee structure to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeStructureDTO>> createFeeStructure(FeeStructureRequest feeStructureRequest);

    /**
     * Delete a fee structure by ID.
     *
     * @param feeStructureId the ID of the fee structure to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteFeeStructure(Long feeStructureId);

    /**
     * Get a fee structure by ID.
     *
     * @param feeStructureId the ID of the fee structure to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeStructureDTO>> getFeeStructure(Long feeStructureId);

    /**
     * Update a fee structure.
     *
     * @param feeStructureId the ID of the fee structure to update
     * @param feeStructureRequest the fee structure to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeStructureDTO>> updateFeeStructure(Long feeStructureId, FeeStructureRequest feeStructureRequest);

    // Product Bundle operations

    /**
     * Create a new product bundle.
     *
     * @param productBundleRequest the product bundle to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductBundleDTO>> createProductBundle(ProductBundleRequest productBundleRequest);

    /**
     * Delete a product bundle by ID.
     *
     * @param bundleId the ID of the product bundle to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductBundle(Long bundleId);

    /**
     * Get a product bundle by ID.
     *
     * @param bundleId the ID of the product bundle to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductBundleDTO>> getProductBundle(Long bundleId);

    /**
     * Update a product bundle.
     *
     * @param bundleId the ID of the product bundle to update
     * @param productBundleRequest the product bundle to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductBundleDTO>> updateProductBundle(Long bundleId, ProductBundleRequest productBundleRequest);

    /**
     * Get all product bundles with pagination.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductBundles(String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Create a new product bundle item.
     *
     * @param bundleId the ID of the product bundle to add the item to
     * @param productBundleItemRequest the product bundle item to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductBundleItemDTO>> createProductBundleItem(Long bundleId, ProductBundleItemRequest productBundleItemRequest);

    /**
     * Delete a product bundle item by ID.
     *
     * @param bundleId the ID of the product bundle
     * @param itemId the ID of the product bundle item to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductBundleItem(Long bundleId, Long itemId);

    /**
     * Get a product bundle item by ID.
     *
     * @param bundleId the ID of the product bundle
     * @param itemId the ID of the product bundle item to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductBundleItemDTO>> getProductBundleItem(Long bundleId, Long itemId);

    /**
     * Update a product bundle item.
     *
     * @param bundleId the ID of the product bundle
     * @param itemId the ID of the product bundle item to update
     * @param productBundleItemRequest the product bundle item to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductBundleItemDTO>> updateProductBundleItem(Long bundleId, Long itemId, ProductBundleItemRequest productBundleItemRequest);

    // Product Category operations

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
     * @param categoryId the ID of the product category to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductCategory(Long categoryId);

    /**
     * Get a product category by ID.
     *
     * @param categoryId the ID of the product category to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> getProductCategory(Long categoryId);

    /**
     * Update a product category.
     *
     * @param categoryId the ID of the product category to update
     * @param productCategoryRequest the product category to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategoryDTO>> updateProductCategory(Long categoryId, ProductCategoryRequest productCategoryRequest);

    /**
     * Create a new product category subtype.
     *
     * @param categoryId the ID of the product category to add the subtype to
     * @param productCategorySubtypeRequest the product category subtype to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategorySubtypeDTO>> createProductCategorySubtype(Long categoryId, ProductCategorySubtypeRequest productCategorySubtypeRequest);

    /**
     * Delete a product category subtype by ID.
     *
     * @param categoryId the ID of the product category
     * @param subtypeId the ID of the product category subtype to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductCategorySubtype(Long categoryId, Long subtypeId);

    /**
     * Get a product category subtype by ID.
     *
     * @param categoryId the ID of the product category
     * @param subtypeId the ID of the product category subtype to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategorySubtypeDTO>> getProductCategorySubtype(Long categoryId, Long subtypeId);

    /**
     * Update a product category subtype.
     *
     * @param categoryId the ID of the product category
     * @param subtypeId the ID of the product category subtype to update
     * @param productCategorySubtypeRequest the product category subtype to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductCategorySubtypeDTO>> updateProductCategorySubtype(Long categoryId, Long subtypeId, ProductCategorySubtypeRequest productCategorySubtypeRequest);

    /**
     * Get all product category subtypes with pagination.
     *
     * @param categoryId the ID of the product category
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductCategorySubtypes(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Fee Application Rule operations

    /**
     * Create a new fee application rule.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the fee component
     * @param feeApplicationRuleRequest the fee application rule to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeApplicationRuleDTO>> createFeeApplicationRule(Long feeStructureId, Long componentId, FeeApplicationRuleRequest feeApplicationRuleRequest);

    /**
     * Delete a fee application rule by ID.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the fee component
     * @param ruleId the ID of the fee application rule to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteFeeApplicationRule(Long feeStructureId, Long componentId, Long ruleId);

    /**
     * Get a fee application rule by ID.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the fee component
     * @param ruleId the ID of the fee application rule to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeApplicationRuleDTO>> getFeeApplicationRule(Long feeStructureId, Long componentId, Long ruleId);

    /**
     * Update a fee application rule.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the fee component
     * @param ruleId the ID of the fee application rule to update
     * @param feeApplicationRuleRequest the fee application rule to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeApplicationRuleDTO>> updateFeeApplicationRule(Long feeStructureId, Long componentId, Long ruleId, FeeApplicationRuleRequest feeApplicationRuleRequest);

    /**
     * Get all fee application rules for a fee component with pagination.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the fee component
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getFeeApplicationRulesByComponentId(Long feeStructureId, Long componentId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Documentation operations

    /**
     * Create a new product documentation.
     *
     * @param productId the ID of the product
     * @param productDocumentationRequest the product documentation to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDocumentationDTO>> createProductDocumentation(Long productId, ProductDocumentationRequest productDocumentationRequest);

    /**
     * Delete a product documentation by ID.
     *
     * @param productId the ID of the product
     * @param docId the ID of the product documentation to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductDocumentation(Long productId, Long docId);

    /**
     * Get a product documentation by ID.
     *
     * @param productId the ID of the product
     * @param docId the ID of the product documentation to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDocumentationDTO>> getProductDocumentation(Long productId, Long docId);

    /**
     * Update a product documentation.
     *
     * @param productId the ID of the product
     * @param docId the ID of the product documentation to update
     * @param productDocumentationRequest the product documentation to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductDocumentationDTO>> updateProductDocumentation(Long productId, Long docId, ProductDocumentationRequest productDocumentationRequest);

    /**
     * Get all product documentation for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductDocumentation(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Feature operations

    /**
     * Create a new product feature.
     *
     * @param productId the ID of the product
     * @param productFeatureRequest the product feature to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductFeatureDTO>> createProductFeature(Long productId, ProductFeatureRequest productFeatureRequest);

    /**
     * Delete a product feature by ID.
     *
     * @param productId the ID of the product
     * @param featureId the ID of the product feature to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductFeature(Long productId, Long featureId);

    /**
     * Get a product feature by ID.
     *
     * @param productId the ID of the product
     * @param featureId the ID of the product feature to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductFeatureDTO>> getProductFeature(Long productId, Long featureId);

    /**
     * Update a product feature.
     *
     * @param productId the ID of the product
     * @param featureId the ID of the product feature to update
     * @param productFeatureRequest the product feature to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductFeatureDTO>> updateProductFeature(Long productId, Long featureId, ProductFeatureRequest productFeatureRequest);

    /**
     * Get all product features for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductFeatures(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Fee Component operations

    /**
     * Create a new product fee component.
     *
     * @param feeStructureId the ID of the fee structure
     * @param productFeeComponentRequest the product fee component to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeComponentDTO>> createProductFeeComponent(Long feeStructureId, ProductFeeComponentRequest productFeeComponentRequest);

    /**
     * Delete a product fee component by ID.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the product fee component to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductFeeComponent(Long feeStructureId, Long componentId);

    /**
     * Get a product fee component by ID.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the product fee component to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeComponentDTO>> getProductFeeComponent(Long feeStructureId, Long componentId);

    /**
     * Update a product fee component.
     *
     * @param feeStructureId the ID of the fee structure
     * @param componentId the ID of the product fee component to update
     * @param productFeeComponentRequest the product fee component to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<FeeComponentDTO>> updateProductFeeComponent(Long feeStructureId, Long componentId, ProductFeeComponentRequest productFeeComponentRequest);

    /**
     * Get all product fee components for a fee structure with pagination.
     *
     * @param feeStructureId the ID of the fee structure
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getProductFeeComponentsByFeeStructureId(Long feeStructureId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Fee Structure operations

    /**
     * Create a new product fee structure.
     *
     * @param productId the ID of the product
     * @param productFeeStructureRequest the product fee structure to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductFeeStructureDTO>> createProductFeeStructure(Long productId, ProductFeeStructureRequest productFeeStructureRequest);

    /**
     * Delete a product fee structure by ID.
     *
     * @param productId the ID of the product
     * @param productFeeStructId the ID of the product fee structure to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductFeeStructure(Long productId, Long productFeeStructId);

    /**
     * Get a product fee structure by ID.
     *
     * @param productId the ID of the product
     * @param productFeeStructId the ID of the product fee structure to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductFeeStructureDTO>> getProductFeeStructure(Long productId, Long productFeeStructId);

    /**
     * Update a product fee structure.
     *
     * @param productId the ID of the product
     * @param productFeeStructId the ID of the product fee structure to update
     * @param productFeeStructureRequest the product fee structure to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductFeeStructureDTO>> updateProductFeeStructure(Long productId, Long productFeeStructId, ProductFeeStructureRequest productFeeStructureRequest);

    /**
     * Get all product fee structures for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductFeeStructures(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Lifecycle operations

    /**
     * Create a new product lifecycle.
     *
     * @param productId the ID of the product
     * @param productLifecycleRequest the product lifecycle to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLifecycleDTO>> createProductLifecycle(Long productId, ProductLifecycleRequest productLifecycleRequest);

    /**
     * Delete a product lifecycle by ID.
     *
     * @param productId the ID of the product
     * @param lifecycleId the ID of the product lifecycle to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductLifecycle(Long productId, Long lifecycleId);

    /**
     * Get a product lifecycle by ID.
     *
     * @param productId the ID of the product
     * @param lifecycleId the ID of the product lifecycle to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLifecycleDTO>> getProductLifecycle(Long productId, Long lifecycleId);

    /**
     * Update a product lifecycle.
     *
     * @param productId the ID of the product
     * @param lifecycleId the ID of the product lifecycle to update
     * @param productLifecycleRequest the product lifecycle to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLifecycleDTO>> updateProductLifecycle(Long productId, Long lifecycleId, ProductLifecycleRequest productLifecycleRequest);

    /**
     * Get all product lifecycles for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getProductLifecycles(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Limit operations

    /**
     * Create a new product limit.
     *
     * @param productId the ID of the product
     * @param productLimitRequest the product limit to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLimitDTO>> createProductLimit(Long productId, ProductLimitRequest productLimitRequest);

    /**
     * Delete a product limit by ID.
     *
     * @param productId the ID of the product
     * @param limitId the ID of the product limit to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductLimit(Long productId, Long limitId);

    /**
     * Get a product limit by ID.
     *
     * @param productId the ID of the product
     * @param limitId the ID of the product limit to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLimitDTO>> getProductLimit(Long productId, Long limitId);

    /**
     * Update a product limit.
     *
     * @param productId the ID of the product
     * @param limitId the ID of the product limit to update
     * @param productLimitRequest the product limit to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLimitDTO>> updateProductLimit(Long productId, Long limitId, ProductLimitRequest productLimitRequest);

    /**
     * Get all product limits for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductLimits(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Localization operations

    /**
     * Create a new product localization.
     *
     * @param productId the ID of the product
     * @param productLocalizationRequest the product localization to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLocalizationDTO>> createProductLocalization(Long productId, ProductLocalizationRequest productLocalizationRequest);

    /**
     * Delete a product localization by ID.
     *
     * @param productId the ID of the product
     * @param localizationId the ID of the product localization to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductLocalization(Long productId, Long localizationId);

    /**
     * Get a product localization by ID.
     *
     * @param productId the ID of the product
     * @param localizationId the ID of the product localization to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLocalizationDTO>> getProductLocalization(Long productId, Long localizationId);

    /**
     * Update a product localization.
     *
     * @param productId the ID of the product
     * @param localizationId the ID of the product localization to update
     * @param productLocalizationRequest the product localization to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductLocalizationDTO>> updateProductLocalization(Long productId, Long localizationId, ProductLocalizationRequest productLocalizationRequest);

    /**
     * Get all product localizations for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductLocalizations(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Pricing operations

    /**
     * Create a new product pricing.
     *
     * @param productId the ID of the product
     * @param productPricingRequest the product pricing to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductPricingDTO>> createProductPricing(Long productId, ProductPricingRequest productPricingRequest);

    /**
     * Delete a product pricing by ID.
     *
     * @param productId the ID of the product
     * @param pricingId the ID of the product pricing to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductPricing(Long productId, Long pricingId);

    /**
     * Get a product pricing by ID.
     *
     * @param productId the ID of the product
     * @param pricingId the ID of the product pricing to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductPricingDTO>> getProductPricing(Long productId, Long pricingId);

    /**
     * Update a product pricing.
     *
     * @param productId the ID of the product
     * @param pricingId the ID of the product pricing to update
     * @param productPricingRequest the product pricing to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductPricingDTO>> updateProductPricing(Long productId, Long pricingId, ProductPricingRequest productPricingRequest);

    /**
     * Get all product pricings for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductPricings(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Relationship operations

    /**
     * Create a new product relationship.
     *
     * @param productId the ID of the product
     * @param productRelationshipRequest the product relationship to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductRelationshipDTO>> createProductRelationship(Long productId, ProductRelationshipRequest productRelationshipRequest);

    /**
     * Delete a product relationship by ID.
     *
     * @param productId the ID of the product
     * @param relationshipId the ID of the product relationship to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductRelationship(Long productId, Long relationshipId);

    /**
     * Get a product relationship by ID.
     *
     * @param productId the ID of the product
     * @param relationshipId the ID of the product relationship to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductRelationshipDTO>> getProductRelationship(Long productId, Long relationshipId);

    /**
     * Update a product relationship.
     *
     * @param productId the ID of the product
     * @param relationshipId the ID of the product relationship to update
     * @param productRelationshipRequest the product relationship to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductRelationshipDTO>> updateProductRelationship(Long productId, Long relationshipId, ProductRelationshipRequest productRelationshipRequest);

    /**
     * Get all product relationships for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductRelationships(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);

    // Product Version operations

    /**
     * Create a new product version.
     *
     * @param productId the ID of the product
     * @param productVersionRequest the product version to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductVersionDTO>> createProductVersion(Long productId, ProductVersionRequest productVersionRequest);

    /**
     * Delete a product version by ID.
     *
     * @param productId the ID of the product
     * @param versionId the ID of the product version to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteProductVersion(Long productId, Long versionId);

    /**
     * Get a product version by ID.
     *
     * @param productId the ID of the product
     * @param versionId the ID of the product version to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductVersionDTO>> getProductVersion(Long productId, Long versionId);

    /**
     * Update a product version.
     *
     * @param productId the ID of the product
     * @param versionId the ID of the product version to update
     * @param productVersionRequest the product version to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ProductVersionDTO>> updateProductVersion(Long productId, Long versionId, ProductVersionRequest productVersionRequest);

    /**
     * Get all product versions for a product with pagination.
     *
     * @param productId the ID of the product
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> getAllProductVersions(Long productId, String pageNumber, String pageSize, String sortBy, String sortDirection);
}

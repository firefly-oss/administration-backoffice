package com.vaadin.starter.business.backend.mapper.products;

import com.catalis.common.product.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.products.*;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between product request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface ProductsMapper {

    ProductDTO productRequestToDto(ProductRequest productRequest);

    FeeStructureDTO feeStructureRequestToDto(FeeStructureRequest feeStructureRequest);

    ProductBundleDTO productBundleRequestToDto(ProductBundleRequest productBundleRequest);

    ProductBundleItemDTO productBundleItemRequestToDto(ProductBundleItemRequest productBundleItemRequest);

    ProductCategoryDTO productCategoryRequestToDto(ProductCategoryRequest productCategoryRequest);

    ProductCategorySubtypeDTO productCategorySubtypeRequestToDto(ProductCategorySubtypeRequest productCategorySubtypeRequest);

    FeeApplicationRuleDTO feeApplicationRuleRequestToDto(FeeApplicationRuleRequest feeApplicationRuleRequest);

    ProductDocumentationDTO productDocumentationRequestToDto(ProductDocumentationRequest productDocumentationRequest);

    ProductFeatureDTO productFeatureRequestToDto(ProductFeatureRequest productFeatureRequest);

    FeeApplicationRuleDTO productFeeApplicationRuleRequestToDto(ProductFeeApplicationRuleRequest productFeeApplicationRuleRequest);

    FeeComponentDTO productFeeComponentRequestToDto(ProductFeeComponentRequest productFeeComponentRequest);

    ProductFeeStructureDTO productFeeStructureRequestToDto(ProductFeeStructureRequest productFeeStructureRequest);

    ProductLifecycleDTO productLifecycleRequestToDto(ProductLifecycleRequest productLifecycleRequest);

    ProductLimitDTO productLimitRequestToDto(ProductLimitRequest productLimitRequest);

    ProductLocalizationDTO productLocalizationRequestToDto(ProductLocalizationRequest productLocalizationRequest);

    ProductPricingDTO productPricingRequestToDto(ProductPricingRequest productPricingRequest);

    ProductRelationshipDTO productRelationshipRequestToDto(ProductRelationshipRequest productRelationshipRequest);

    ProductVersionDTO productVersionRequestToDto(ProductVersionRequest productVersionRequest);
}

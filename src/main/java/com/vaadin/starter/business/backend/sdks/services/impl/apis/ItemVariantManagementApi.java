package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryItemVariantDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryItemVariantDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.PaginationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-07-23T10:06:26.160650600+02:00[Europe/Madrid]")
public class ItemVariantManagementApi {
    private ApiClient apiClient;

    public ItemVariantManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public ItemVariantManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create item variant in catalog
     * Create a new item variant in a specific catalog
     * <p><b>201</b> - Item variant created successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryItemVariantDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createItemVariantInCatalogRequestCreation(Long catalogId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryItemVariantDTO;
        // verify the required parameter 'catalogId' is set
        if (catalogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'catalogId' when calling createItemVariantInCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryItemVariantDTO' is set
        if (inventoryItemVariantDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryItemVariantDTO' when calling createItemVariantInCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("catalogId", catalogId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (xIdempotencyKey != null)
        headerParams.add("X-Idempotency-Key", apiClient.parameterToString(xIdempotencyKey));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return apiClient.invokeAPI("/api/v1/catalogs/{catalogId}/item-variants", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create item variant in catalog
     * Create a new item variant in a specific catalog
     * <p><b>201</b> - Item variant created successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryItemVariantDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryItemVariantDTO> createItemVariantInCatalog(Long catalogId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return createItemVariantInCatalogRequestCreation(catalogId, inventoryItemVariantDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create item variant in catalog
     * Create a new item variant in a specific catalog
     * <p><b>201</b> - Item variant created successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryItemVariantDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryItemVariantDTO>> createItemVariantInCatalogWithHttpInfo(Long catalogId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return createItemVariantInCatalogRequestCreation(catalogId, inventoryItemVariantDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create item variant in catalog
     * Create a new item variant in a specific catalog
     * <p><b>201</b> - Item variant created successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createItemVariantInCatalogWithResponseSpec(Long catalogId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createItemVariantInCatalogRequestCreation(catalogId, inventoryItemVariantDTO, xIdempotencyKey);
    }
    /**
     * Delete item variant
     * Delete an item variant by its ID
     * <p><b>204</b> - Item variant deleted successfully
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteItemVariantRequestCreation(Long itemVariantId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'itemVariantId' is set
        if (itemVariantId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemVariantId' when calling deleteItemVariant", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("itemVariantId", itemVariantId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return apiClient.invokeAPI("/api/v1/item-variants/{itemVariantId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete item variant
     * Delete an item variant by its ID
     * <p><b>204</b> - Item variant deleted successfully
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteItemVariant(Long itemVariantId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteItemVariantRequestCreation(itemVariantId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete item variant
     * Delete an item variant by its ID
     * <p><b>204</b> - Item variant deleted successfully
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteItemVariantWithHttpInfo(Long itemVariantId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteItemVariantRequestCreation(itemVariantId).toEntity(localVarReturnType);
    }

    /**
     * Delete item variant
     * Delete an item variant by its ID
     * <p><b>204</b> - Item variant deleted successfully
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteItemVariantWithResponseSpec(Long itemVariantId) throws WebClientResponseException {
        return deleteItemVariantRequestCreation(itemVariantId);
    }
    /**
     * Filter item variants
     * Filter item variants based on specified criteria
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryItemVariantDTO The filterRequestInventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterItemVariantsRequestCreation(FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryItemVariantDTO;
        // verify the required parameter 'filterRequestInventoryItemVariantDTO' is set
        if (filterRequestInventoryItemVariantDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryItemVariantDTO' when calling filterItemVariants", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (xIdempotencyKey != null)
        headerParams.add("X-Idempotency-Key", apiClient.parameterToString(xIdempotencyKey));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return apiClient.invokeAPI("/api/v1/item-variants/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter item variants
     * Filter item variants based on specified criteria
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryItemVariantDTO The filterRequestInventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterItemVariants(FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterItemVariantsRequestCreation(filterRequestInventoryItemVariantDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter item variants
     * Filter item variants based on specified criteria
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryItemVariantDTO The filterRequestInventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterItemVariantsWithHttpInfo(FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterItemVariantsRequestCreation(filterRequestInventoryItemVariantDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter item variants
     * Filter item variants based on specified criteria
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryItemVariantDTO The filterRequestInventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterItemVariantsWithResponseSpec(FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterItemVariantsRequestCreation(filterRequestInventoryItemVariantDTO, xIdempotencyKey);
    }
    /**
     * Filter item variants by catalog
     * Filter item variants in a specific catalog
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterItemVariantsByCatalogRequestCreation(Long catalogId, FilterRequestInventoryItemVariantDTO filterRequest) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'catalogId' is set
        if (catalogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'catalogId' when calling filterItemVariantsByCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'filterRequest' is set
        if (filterRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequest' when calling filterItemVariantsByCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("catalogId", catalogId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "filterRequest", filterRequest));

        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return apiClient.invokeAPI("/api/v1/catalogs/{catalogId}/item-variants", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter item variants by catalog
     * Filter item variants in a specific catalog
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterItemVariantsByCatalog(Long catalogId, FilterRequestInventoryItemVariantDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterItemVariantsByCatalogRequestCreation(catalogId, filterRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Filter item variants by catalog
     * Filter item variants in a specific catalog
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param filterRequest The filterRequest parameter
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterItemVariantsByCatalogWithHttpInfo(Long catalogId, FilterRequestInventoryItemVariantDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterItemVariantsByCatalogRequestCreation(catalogId, filterRequest).toEntity(localVarReturnType);
    }

    /**
     * Filter item variants by catalog
     * Filter item variants in a specific catalog
     * <p><b>200</b> - Successfully retrieved item variants
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog
     * @param filterRequest The filterRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterItemVariantsByCatalogWithResponseSpec(Long catalogId, FilterRequestInventoryItemVariantDTO filterRequest) throws WebClientResponseException {
        return filterItemVariantsByCatalogRequestCreation(catalogId, filterRequest);
    }
    /**
     * Get item variant by ID
     * Retrieve an item variant by its ID
     * <p><b>200</b> - Successfully retrieved item variant
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to retrieve
     * @return InventoryItemVariantDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getItemVariantByIdRequestCreation(Long itemVariantId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'itemVariantId' is set
        if (itemVariantId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemVariantId' when calling getItemVariantById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("itemVariantId", itemVariantId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return apiClient.invokeAPI("/api/v1/item-variants/{itemVariantId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get item variant by ID
     * Retrieve an item variant by its ID
     * <p><b>200</b> - Successfully retrieved item variant
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to retrieve
     * @return InventoryItemVariantDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryItemVariantDTO> getItemVariantById(Long itemVariantId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return getItemVariantByIdRequestCreation(itemVariantId).bodyToMono(localVarReturnType);
    }

    /**
     * Get item variant by ID
     * Retrieve an item variant by its ID
     * <p><b>200</b> - Successfully retrieved item variant
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to retrieve
     * @return ResponseEntity&lt;InventoryItemVariantDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryItemVariantDTO>> getItemVariantByIdWithHttpInfo(Long itemVariantId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return getItemVariantByIdRequestCreation(itemVariantId).toEntity(localVarReturnType);
    }

    /**
     * Get item variant by ID
     * Retrieve an item variant by its ID
     * <p><b>200</b> - Successfully retrieved item variant
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getItemVariantByIdWithResponseSpec(Long itemVariantId) throws WebClientResponseException {
        return getItemVariantByIdRequestCreation(itemVariantId);
    }
    /**
     * Update item variant
     * Update an existing item variant
     * <p><b>200</b> - Item variant updated successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to update
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryItemVariantDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateItemVariantRequestCreation(Long itemVariantId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryItemVariantDTO;
        // verify the required parameter 'itemVariantId' is set
        if (itemVariantId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemVariantId' when calling updateItemVariant", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryItemVariantDTO' is set
        if (inventoryItemVariantDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryItemVariantDTO' when calling updateItemVariant", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("itemVariantId", itemVariantId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (xIdempotencyKey != null)
        headerParams.add("X-Idempotency-Key", apiClient.parameterToString(xIdempotencyKey));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return apiClient.invokeAPI("/api/v1/item-variants/{itemVariantId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update item variant
     * Update an existing item variant
     * <p><b>200</b> - Item variant updated successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to update
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryItemVariantDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryItemVariantDTO> updateItemVariant(Long itemVariantId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return updateItemVariantRequestCreation(itemVariantId, inventoryItemVariantDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update item variant
     * Update an existing item variant
     * <p><b>200</b> - Item variant updated successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to update
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryItemVariantDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryItemVariantDTO>> updateItemVariantWithHttpInfo(Long itemVariantId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryItemVariantDTO> localVarReturnType = new ParameterizedTypeReference<InventoryItemVariantDTO>() {};
        return updateItemVariantRequestCreation(itemVariantId, inventoryItemVariantDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update item variant
     * Update an existing item variant
     * <p><b>200</b> - Item variant updated successfully
     * <p><b>400</b> - Invalid item variant data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant to update
     * @param inventoryItemVariantDTO The inventoryItemVariantDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateItemVariantWithResponseSpec(Long itemVariantId, InventoryItemVariantDTO inventoryItemVariantDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateItemVariantRequestCreation(itemVariantId, inventoryItemVariantDTO, xIdempotencyKey);
    }
}

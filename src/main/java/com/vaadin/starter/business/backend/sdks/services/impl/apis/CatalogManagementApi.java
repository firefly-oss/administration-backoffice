package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryCatalogDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryCatalogDTO;
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
public class CatalogManagementApi {
    private ApiClient apiClient;

    public CatalogManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public CatalogManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create catalog
     * Create a new inventory catalog
     * <p><b>201</b> - Catalog created successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createCatalogRequestCreation(InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryCatalogDTO;
        // verify the required parameter 'inventoryCatalogDTO' is set
        if (inventoryCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryCatalogDTO' when calling createCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/catalogs", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create catalog
     * Create a new inventory catalog
     * <p><b>201</b> - Catalog created successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryCatalogDTO> createCatalog(InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return createCatalogRequestCreation(inventoryCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create catalog
     * Create a new inventory catalog
     * <p><b>201</b> - Catalog created successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryCatalogDTO>> createCatalogWithHttpInfo(InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return createCatalogRequestCreation(inventoryCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create catalog
     * Create a new inventory catalog
     * <p><b>201</b> - Catalog created successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createCatalogWithResponseSpec(InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createCatalogRequestCreation(inventoryCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete catalog
     * Delete a catalog by its ID
     * <p><b>204</b> - Catalog deleted successfully
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteCatalogRequestCreation(Long catalogId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'catalogId' is set
        if (catalogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'catalogId' when calling deleteCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("catalogId", catalogId);

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
        return apiClient.invokeAPI("/api/v1/catalogs/{catalogId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete catalog
     * Delete a catalog by its ID
     * <p><b>204</b> - Catalog deleted successfully
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteCatalog(Long catalogId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteCatalogRequestCreation(catalogId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete catalog
     * Delete a catalog by its ID
     * <p><b>204</b> - Catalog deleted successfully
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteCatalogWithHttpInfo(Long catalogId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteCatalogRequestCreation(catalogId).toEntity(localVarReturnType);
    }

    /**
     * Delete catalog
     * Delete a catalog by its ID
     * <p><b>204</b> - Catalog deleted successfully
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteCatalogWithResponseSpec(Long catalogId) throws WebClientResponseException {
        return deleteCatalogRequestCreation(catalogId);
    }
    /**
     * Filter catalogs
     * Filter catalogs based on specified criteria
     * <p><b>200</b> - Successfully retrieved catalogs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryCatalogDTO The filterRequestInventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterCatalogsRequestCreation(FilterRequestInventoryCatalogDTO filterRequestInventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryCatalogDTO;
        // verify the required parameter 'filterRequestInventoryCatalogDTO' is set
        if (filterRequestInventoryCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryCatalogDTO' when calling filterCatalogs", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/catalogs/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter catalogs
     * Filter catalogs based on specified criteria
     * <p><b>200</b> - Successfully retrieved catalogs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryCatalogDTO The filterRequestInventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterCatalogs(FilterRequestInventoryCatalogDTO filterRequestInventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterCatalogsRequestCreation(filterRequestInventoryCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter catalogs
     * Filter catalogs based on specified criteria
     * <p><b>200</b> - Successfully retrieved catalogs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryCatalogDTO The filterRequestInventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterCatalogsWithHttpInfo(FilterRequestInventoryCatalogDTO filterRequestInventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterCatalogsRequestCreation(filterRequestInventoryCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter catalogs
     * Filter catalogs based on specified criteria
     * <p><b>200</b> - Successfully retrieved catalogs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryCatalogDTO The filterRequestInventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterCatalogsWithResponseSpec(FilterRequestInventoryCatalogDTO filterRequestInventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterCatalogsRequestCreation(filterRequestInventoryCatalogDTO, xIdempotencyKey);
    }
    /**
     * Get catalog by ID
     * Retrieve a catalog by its ID
     * <p><b>200</b> - Successfully retrieved catalog
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to retrieve
     * @return InventoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getCatalogByIdRequestCreation(Long catalogId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'catalogId' is set
        if (catalogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'catalogId' when calling getCatalogById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("catalogId", catalogId);

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

        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/catalogs/{catalogId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get catalog by ID
     * Retrieve a catalog by its ID
     * <p><b>200</b> - Successfully retrieved catalog
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to retrieve
     * @return InventoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryCatalogDTO> getCatalogById(Long catalogId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return getCatalogByIdRequestCreation(catalogId).bodyToMono(localVarReturnType);
    }

    /**
     * Get catalog by ID
     * Retrieve a catalog by its ID
     * <p><b>200</b> - Successfully retrieved catalog
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to retrieve
     * @return ResponseEntity&lt;InventoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryCatalogDTO>> getCatalogByIdWithHttpInfo(Long catalogId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return getCatalogByIdRequestCreation(catalogId).toEntity(localVarReturnType);
    }

    /**
     * Get catalog by ID
     * Retrieve a catalog by its ID
     * <p><b>200</b> - Successfully retrieved catalog
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getCatalogByIdWithResponseSpec(Long catalogId) throws WebClientResponseException {
        return getCatalogByIdRequestCreation(catalogId);
    }
    /**
     * Update catalog
     * Update an existing catalog
     * <p><b>200</b> - Catalog updated successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to update
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateCatalogRequestCreation(Long catalogId, InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryCatalogDTO;
        // verify the required parameter 'catalogId' is set
        if (catalogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'catalogId' when calling updateCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryCatalogDTO' is set
        if (inventoryCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryCatalogDTO' when calling updateCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/catalogs/{catalogId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update catalog
     * Update an existing catalog
     * <p><b>200</b> - Catalog updated successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to update
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryCatalogDTO> updateCatalog(Long catalogId, InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return updateCatalogRequestCreation(catalogId, inventoryCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update catalog
     * Update an existing catalog
     * <p><b>200</b> - Catalog updated successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to update
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryCatalogDTO>> updateCatalogWithHttpInfo(Long catalogId, InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryCatalogDTO>() {};
        return updateCatalogRequestCreation(catalogId, inventoryCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update catalog
     * Update an existing catalog
     * <p><b>200</b> - Catalog updated successfully
     * <p><b>400</b> - Invalid catalog data provided
     * <p><b>404</b> - Catalog not found
     * <p><b>500</b> - Internal server error
     * @param catalogId ID of the catalog to update
     * @param inventoryCatalogDTO The inventoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateCatalogWithResponseSpec(Long catalogId, InventoryCatalogDTO inventoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateCatalogRequestCreation(catalogId, inventoryCatalogDTO, xIdempotencyKey);
    }
}

package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryUomDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryUomDTO;
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
public class UnitOfMeasureManagementApi {
    private ApiClient apiClient;

    public UnitOfMeasureManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public UnitOfMeasureManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create unit of measure
     * Create a new inventory unit of measure
     * <p><b>201</b> - Unit of measure created successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryUomDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createUomRequestCreation(InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryUomDTO;
        // verify the required parameter 'inventoryUomDTO' is set
        if (inventoryUomDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryUomDTO' when calling createUom", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return apiClient.invokeAPI("/api/v1/uoms", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create unit of measure
     * Create a new inventory unit of measure
     * <p><b>201</b> - Unit of measure created successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryUomDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryUomDTO> createUom(InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return createUomRequestCreation(inventoryUomDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create unit of measure
     * Create a new inventory unit of measure
     * <p><b>201</b> - Unit of measure created successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryUomDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryUomDTO>> createUomWithHttpInfo(InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return createUomRequestCreation(inventoryUomDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create unit of measure
     * Create a new inventory unit of measure
     * <p><b>201</b> - Unit of measure created successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createUomWithResponseSpec(InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createUomRequestCreation(inventoryUomDTO, xIdempotencyKey);
    }
    /**
     * Delete unit of measure
     * Delete a unit of measure by its ID
     * <p><b>204</b> - Unit of measure deleted successfully
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteUomRequestCreation(Long uomId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'uomId' is set
        if (uomId == null) {
            throw new WebClientResponseException("Missing the required parameter 'uomId' when calling deleteUom", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("uomId", uomId);

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
        return apiClient.invokeAPI("/api/v1/uoms/{uomId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete unit of measure
     * Delete a unit of measure by its ID
     * <p><b>204</b> - Unit of measure deleted successfully
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteUom(Long uomId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteUomRequestCreation(uomId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete unit of measure
     * Delete a unit of measure by its ID
     * <p><b>204</b> - Unit of measure deleted successfully
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteUomWithHttpInfo(Long uomId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteUomRequestCreation(uomId).toEntity(localVarReturnType);
    }

    /**
     * Delete unit of measure
     * Delete a unit of measure by its ID
     * <p><b>204</b> - Unit of measure deleted successfully
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteUomWithResponseSpec(Long uomId) throws WebClientResponseException {
        return deleteUomRequestCreation(uomId);
    }
    /**
     * Filter units of measure
     * Filter units of measure based on specified criteria
     * <p><b>200</b> - Successfully retrieved units of measure
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryUomDTO The filterRequestInventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterUomsRequestCreation(FilterRequestInventoryUomDTO filterRequestInventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryUomDTO;
        // verify the required parameter 'filterRequestInventoryUomDTO' is set
        if (filterRequestInventoryUomDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryUomDTO' when calling filterUoms", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/uoms/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter units of measure
     * Filter units of measure based on specified criteria
     * <p><b>200</b> - Successfully retrieved units of measure
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryUomDTO The filterRequestInventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterUoms(FilterRequestInventoryUomDTO filterRequestInventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterUomsRequestCreation(filterRequestInventoryUomDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter units of measure
     * Filter units of measure based on specified criteria
     * <p><b>200</b> - Successfully retrieved units of measure
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryUomDTO The filterRequestInventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterUomsWithHttpInfo(FilterRequestInventoryUomDTO filterRequestInventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterUomsRequestCreation(filterRequestInventoryUomDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter units of measure
     * Filter units of measure based on specified criteria
     * <p><b>200</b> - Successfully retrieved units of measure
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryUomDTO The filterRequestInventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterUomsWithResponseSpec(FilterRequestInventoryUomDTO filterRequestInventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterUomsRequestCreation(filterRequestInventoryUomDTO, xIdempotencyKey);
    }
    /**
     * Get unit of measure by ID
     * Retrieve a unit of measure by its ID
     * <p><b>200</b> - Successfully retrieved unit of measure
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to retrieve
     * @return InventoryUomDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getUomByIdRequestCreation(Long uomId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'uomId' is set
        if (uomId == null) {
            throw new WebClientResponseException("Missing the required parameter 'uomId' when calling getUomById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("uomId", uomId);

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

        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return apiClient.invokeAPI("/api/v1/uoms/{uomId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get unit of measure by ID
     * Retrieve a unit of measure by its ID
     * <p><b>200</b> - Successfully retrieved unit of measure
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to retrieve
     * @return InventoryUomDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryUomDTO> getUomById(Long uomId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return getUomByIdRequestCreation(uomId).bodyToMono(localVarReturnType);
    }

    /**
     * Get unit of measure by ID
     * Retrieve a unit of measure by its ID
     * <p><b>200</b> - Successfully retrieved unit of measure
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to retrieve
     * @return ResponseEntity&lt;InventoryUomDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryUomDTO>> getUomByIdWithHttpInfo(Long uomId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return getUomByIdRequestCreation(uomId).toEntity(localVarReturnType);
    }

    /**
     * Get unit of measure by ID
     * Retrieve a unit of measure by its ID
     * <p><b>200</b> - Successfully retrieved unit of measure
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getUomByIdWithResponseSpec(Long uomId) throws WebClientResponseException {
        return getUomByIdRequestCreation(uomId);
    }
    /**
     * Update unit of measure
     * Update an existing unit of measure
     * <p><b>200</b> - Unit of measure updated successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to update
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryUomDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateUomRequestCreation(Long uomId, InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryUomDTO;
        // verify the required parameter 'uomId' is set
        if (uomId == null) {
            throw new WebClientResponseException("Missing the required parameter 'uomId' when calling updateUom", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryUomDTO' is set
        if (inventoryUomDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryUomDTO' when calling updateUom", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("uomId", uomId);

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

        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return apiClient.invokeAPI("/api/v1/uoms/{uomId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update unit of measure
     * Update an existing unit of measure
     * <p><b>200</b> - Unit of measure updated successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to update
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryUomDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryUomDTO> updateUom(Long uomId, InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return updateUomRequestCreation(uomId, inventoryUomDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update unit of measure
     * Update an existing unit of measure
     * <p><b>200</b> - Unit of measure updated successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to update
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryUomDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryUomDTO>> updateUomWithHttpInfo(Long uomId, InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryUomDTO> localVarReturnType = new ParameterizedTypeReference<InventoryUomDTO>() {};
        return updateUomRequestCreation(uomId, inventoryUomDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update unit of measure
     * Update an existing unit of measure
     * <p><b>200</b> - Unit of measure updated successfully
     * <p><b>400</b> - Invalid unit of measure data provided
     * <p><b>404</b> - Unit of measure not found
     * <p><b>500</b> - Internal server error
     * @param uomId ID of the unit of measure to update
     * @param inventoryUomDTO The inventoryUomDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateUomWithResponseSpec(Long uomId, InventoryUomDTO inventoryUomDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateUomRequestCreation(uomId, inventoryUomDTO, xIdempotencyKey);
    }
}

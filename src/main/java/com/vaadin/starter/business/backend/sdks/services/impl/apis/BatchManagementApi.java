package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryBatchDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryBatchDTO;
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
public class BatchManagementApi {
    private ApiClient apiClient;

    public BatchManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public BatchManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create batch
     * Create a new inventory batch
     * <p><b>201</b> - Batch created successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBatchDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createBatchRequestCreation(InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryBatchDTO;
        // verify the required parameter 'inventoryBatchDTO' is set
        if (inventoryBatchDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryBatchDTO' when calling createBatch", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return apiClient.invokeAPI("/api/v1/batches", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create batch
     * Create a new inventory batch
     * <p><b>201</b> - Batch created successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBatchDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryBatchDTO> createBatch(InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return createBatchRequestCreation(inventoryBatchDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create batch
     * Create a new inventory batch
     * <p><b>201</b> - Batch created successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryBatchDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryBatchDTO>> createBatchWithHttpInfo(InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return createBatchRequestCreation(inventoryBatchDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create batch
     * Create a new inventory batch
     * <p><b>201</b> - Batch created successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createBatchWithResponseSpec(InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createBatchRequestCreation(inventoryBatchDTO, xIdempotencyKey);
    }
    /**
     * Delete batch
     * Delete a batch by its ID
     * <p><b>204</b> - Batch deleted successfully
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteBatchRequestCreation(Long batchId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'batchId' is set
        if (batchId == null) {
            throw new WebClientResponseException("Missing the required parameter 'batchId' when calling deleteBatch", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("batchId", batchId);

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
        return apiClient.invokeAPI("/api/v1/batches/{batchId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete batch
     * Delete a batch by its ID
     * <p><b>204</b> - Batch deleted successfully
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteBatch(Long batchId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteBatchRequestCreation(batchId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete batch
     * Delete a batch by its ID
     * <p><b>204</b> - Batch deleted successfully
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteBatchWithHttpInfo(Long batchId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteBatchRequestCreation(batchId).toEntity(localVarReturnType);
    }

    /**
     * Delete batch
     * Delete a batch by its ID
     * <p><b>204</b> - Batch deleted successfully
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteBatchWithResponseSpec(Long batchId) throws WebClientResponseException {
        return deleteBatchRequestCreation(batchId);
    }
    /**
     * Filter batches
     * Filter batches based on specified criteria
     * <p><b>200</b> - Successfully retrieved batches
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBatchDTO The filterRequestInventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterBatchesRequestCreation(FilterRequestInventoryBatchDTO filterRequestInventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryBatchDTO;
        // verify the required parameter 'filterRequestInventoryBatchDTO' is set
        if (filterRequestInventoryBatchDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryBatchDTO' when calling filterBatches", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/batches/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter batches
     * Filter batches based on specified criteria
     * <p><b>200</b> - Successfully retrieved batches
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBatchDTO The filterRequestInventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterBatches(FilterRequestInventoryBatchDTO filterRequestInventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterBatchesRequestCreation(filterRequestInventoryBatchDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter batches
     * Filter batches based on specified criteria
     * <p><b>200</b> - Successfully retrieved batches
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBatchDTO The filterRequestInventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterBatchesWithHttpInfo(FilterRequestInventoryBatchDTO filterRequestInventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterBatchesRequestCreation(filterRequestInventoryBatchDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter batches
     * Filter batches based on specified criteria
     * <p><b>200</b> - Successfully retrieved batches
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBatchDTO The filterRequestInventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterBatchesWithResponseSpec(FilterRequestInventoryBatchDTO filterRequestInventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterBatchesRequestCreation(filterRequestInventoryBatchDTO, xIdempotencyKey);
    }
    /**
     * Get batch by ID
     * Retrieve a batch by its ID
     * <p><b>200</b> - Successfully retrieved batch
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to retrieve
     * @return InventoryBatchDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getBatchByIdRequestCreation(Long batchId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'batchId' is set
        if (batchId == null) {
            throw new WebClientResponseException("Missing the required parameter 'batchId' when calling getBatchById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("batchId", batchId);

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

        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return apiClient.invokeAPI("/api/v1/batches/{batchId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get batch by ID
     * Retrieve a batch by its ID
     * <p><b>200</b> - Successfully retrieved batch
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to retrieve
     * @return InventoryBatchDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryBatchDTO> getBatchById(Long batchId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return getBatchByIdRequestCreation(batchId).bodyToMono(localVarReturnType);
    }

    /**
     * Get batch by ID
     * Retrieve a batch by its ID
     * <p><b>200</b> - Successfully retrieved batch
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to retrieve
     * @return ResponseEntity&lt;InventoryBatchDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryBatchDTO>> getBatchByIdWithHttpInfo(Long batchId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return getBatchByIdRequestCreation(batchId).toEntity(localVarReturnType);
    }

    /**
     * Get batch by ID
     * Retrieve a batch by its ID
     * <p><b>200</b> - Successfully retrieved batch
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getBatchByIdWithResponseSpec(Long batchId) throws WebClientResponseException {
        return getBatchByIdRequestCreation(batchId);
    }
    /**
     * Update batch
     * Update an existing batch
     * <p><b>200</b> - Batch updated successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to update
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBatchDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateBatchRequestCreation(Long batchId, InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryBatchDTO;
        // verify the required parameter 'batchId' is set
        if (batchId == null) {
            throw new WebClientResponseException("Missing the required parameter 'batchId' when calling updateBatch", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryBatchDTO' is set
        if (inventoryBatchDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryBatchDTO' when calling updateBatch", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("batchId", batchId);

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

        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return apiClient.invokeAPI("/api/v1/batches/{batchId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update batch
     * Update an existing batch
     * <p><b>200</b> - Batch updated successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to update
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBatchDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryBatchDTO> updateBatch(Long batchId, InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return updateBatchRequestCreation(batchId, inventoryBatchDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update batch
     * Update an existing batch
     * <p><b>200</b> - Batch updated successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to update
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryBatchDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryBatchDTO>> updateBatchWithHttpInfo(Long batchId, InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBatchDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBatchDTO>() {};
        return updateBatchRequestCreation(batchId, inventoryBatchDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update batch
     * Update an existing batch
     * <p><b>200</b> - Batch updated successfully
     * <p><b>400</b> - Invalid batch data provided
     * <p><b>404</b> - Batch not found
     * <p><b>500</b> - Internal server error
     * @param batchId ID of the batch to update
     * @param inventoryBatchDTO The inventoryBatchDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateBatchWithResponseSpec(Long batchId, InventoryBatchDTO inventoryBatchDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateBatchRequestCreation(batchId, inventoryBatchDTO, xIdempotencyKey);
    }
}

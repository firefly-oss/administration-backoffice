package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryMovementLogDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryMovementLogDTO;
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
public class MovementLogManagementApi {
    private ApiClient apiClient;

    public MovementLogManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public MovementLogManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create movement log
     * Create a new inventory movement log
     * <p><b>201</b> - Movement log created successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryMovementLogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createMovementLogRequestCreation(InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryMovementLogDTO;
        // verify the required parameter 'inventoryMovementLogDTO' is set
        if (inventoryMovementLogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryMovementLogDTO' when calling createMovementLog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return apiClient.invokeAPI("/api/v1/movement-logs", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create movement log
     * Create a new inventory movement log
     * <p><b>201</b> - Movement log created successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryMovementLogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryMovementLogDTO> createMovementLog(InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return createMovementLogRequestCreation(inventoryMovementLogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create movement log
     * Create a new inventory movement log
     * <p><b>201</b> - Movement log created successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryMovementLogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryMovementLogDTO>> createMovementLogWithHttpInfo(InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return createMovementLogRequestCreation(inventoryMovementLogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create movement log
     * Create a new inventory movement log
     * <p><b>201</b> - Movement log created successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createMovementLogWithResponseSpec(InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createMovementLogRequestCreation(inventoryMovementLogDTO, xIdempotencyKey);
    }
    /**
     * Delete movement log
     * Delete a movement log by its ID
     * <p><b>204</b> - Movement log deleted successfully
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteMovementLogRequestCreation(Long movementLogId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'movementLogId' is set
        if (movementLogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'movementLogId' when calling deleteMovementLog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("movementLogId", movementLogId);

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
        return apiClient.invokeAPI("/api/v1/movement-logs/{movementLogId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete movement log
     * Delete a movement log by its ID
     * <p><b>204</b> - Movement log deleted successfully
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteMovementLog(Long movementLogId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteMovementLogRequestCreation(movementLogId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete movement log
     * Delete a movement log by its ID
     * <p><b>204</b> - Movement log deleted successfully
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteMovementLogWithHttpInfo(Long movementLogId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteMovementLogRequestCreation(movementLogId).toEntity(localVarReturnType);
    }

    /**
     * Delete movement log
     * Delete a movement log by its ID
     * <p><b>204</b> - Movement log deleted successfully
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteMovementLogWithResponseSpec(Long movementLogId) throws WebClientResponseException {
        return deleteMovementLogRequestCreation(movementLogId);
    }
    /**
     * Filter movement logs
     * Filter movement logs based on specified criteria
     * <p><b>200</b> - Successfully retrieved movement logs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryMovementLogDTO The filterRequestInventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterMovementLogsRequestCreation(FilterRequestInventoryMovementLogDTO filterRequestInventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryMovementLogDTO;
        // verify the required parameter 'filterRequestInventoryMovementLogDTO' is set
        if (filterRequestInventoryMovementLogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryMovementLogDTO' when calling filterMovementLogs", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/movement-logs/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter movement logs
     * Filter movement logs based on specified criteria
     * <p><b>200</b> - Successfully retrieved movement logs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryMovementLogDTO The filterRequestInventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterMovementLogs(FilterRequestInventoryMovementLogDTO filterRequestInventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterMovementLogsRequestCreation(filterRequestInventoryMovementLogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter movement logs
     * Filter movement logs based on specified criteria
     * <p><b>200</b> - Successfully retrieved movement logs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryMovementLogDTO The filterRequestInventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterMovementLogsWithHttpInfo(FilterRequestInventoryMovementLogDTO filterRequestInventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterMovementLogsRequestCreation(filterRequestInventoryMovementLogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter movement logs
     * Filter movement logs based on specified criteria
     * <p><b>200</b> - Successfully retrieved movement logs
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryMovementLogDTO The filterRequestInventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterMovementLogsWithResponseSpec(FilterRequestInventoryMovementLogDTO filterRequestInventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterMovementLogsRequestCreation(filterRequestInventoryMovementLogDTO, xIdempotencyKey);
    }
    /**
     * Get movement log by ID
     * Retrieve a movement log by its ID
     * <p><b>200</b> - Successfully retrieved movement log
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to retrieve
     * @return InventoryMovementLogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getMovementLogByIdRequestCreation(Long movementLogId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'movementLogId' is set
        if (movementLogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'movementLogId' when calling getMovementLogById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("movementLogId", movementLogId);

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

        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return apiClient.invokeAPI("/api/v1/movement-logs/{movementLogId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get movement log by ID
     * Retrieve a movement log by its ID
     * <p><b>200</b> - Successfully retrieved movement log
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to retrieve
     * @return InventoryMovementLogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryMovementLogDTO> getMovementLogById(Long movementLogId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return getMovementLogByIdRequestCreation(movementLogId).bodyToMono(localVarReturnType);
    }

    /**
     * Get movement log by ID
     * Retrieve a movement log by its ID
     * <p><b>200</b> - Successfully retrieved movement log
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to retrieve
     * @return ResponseEntity&lt;InventoryMovementLogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryMovementLogDTO>> getMovementLogByIdWithHttpInfo(Long movementLogId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return getMovementLogByIdRequestCreation(movementLogId).toEntity(localVarReturnType);
    }

    /**
     * Get movement log by ID
     * Retrieve a movement log by its ID
     * <p><b>200</b> - Successfully retrieved movement log
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getMovementLogByIdWithResponseSpec(Long movementLogId) throws WebClientResponseException {
        return getMovementLogByIdRequestCreation(movementLogId);
    }
    /**
     * Update movement log
     * Update an existing movement log
     * <p><b>200</b> - Movement log updated successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to update
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryMovementLogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateMovementLogRequestCreation(Long movementLogId, InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryMovementLogDTO;
        // verify the required parameter 'movementLogId' is set
        if (movementLogId == null) {
            throw new WebClientResponseException("Missing the required parameter 'movementLogId' when calling updateMovementLog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryMovementLogDTO' is set
        if (inventoryMovementLogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryMovementLogDTO' when calling updateMovementLog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("movementLogId", movementLogId);

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

        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return apiClient.invokeAPI("/api/v1/movement-logs/{movementLogId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update movement log
     * Update an existing movement log
     * <p><b>200</b> - Movement log updated successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to update
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryMovementLogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryMovementLogDTO> updateMovementLog(Long movementLogId, InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return updateMovementLogRequestCreation(movementLogId, inventoryMovementLogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update movement log
     * Update an existing movement log
     * <p><b>200</b> - Movement log updated successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to update
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryMovementLogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryMovementLogDTO>> updateMovementLogWithHttpInfo(Long movementLogId, InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryMovementLogDTO> localVarReturnType = new ParameterizedTypeReference<InventoryMovementLogDTO>() {};
        return updateMovementLogRequestCreation(movementLogId, inventoryMovementLogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update movement log
     * Update an existing movement log
     * <p><b>200</b> - Movement log updated successfully
     * <p><b>400</b> - Invalid movement log data provided
     * <p><b>404</b> - Movement log not found
     * <p><b>500</b> - Internal server error
     * @param movementLogId ID of the movement log to update
     * @param inventoryMovementLogDTO The inventoryMovementLogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateMovementLogWithResponseSpec(Long movementLogId, InventoryMovementLogDTO inventoryMovementLogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateMovementLogRequestCreation(movementLogId, inventoryMovementLogDTO, xIdempotencyKey);
    }
}

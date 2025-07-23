package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventorySerialDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventorySerialDTO;
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
public class SerialManagementApi {
    private ApiClient apiClient;

    public SerialManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public SerialManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create serial
     * Create a new inventory serial
     * <p><b>201</b> - Serial created successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>500</b> - Internal server error
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventorySerialDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createSerialRequestCreation(InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventorySerialDTO;
        // verify the required parameter 'inventorySerialDTO' is set
        if (inventorySerialDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventorySerialDTO' when calling createSerial", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return apiClient.invokeAPI("/api/v1/serials", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create serial
     * Create a new inventory serial
     * <p><b>201</b> - Serial created successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>500</b> - Internal server error
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventorySerialDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventorySerialDTO> createSerial(InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return createSerialRequestCreation(inventorySerialDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create serial
     * Create a new inventory serial
     * <p><b>201</b> - Serial created successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>500</b> - Internal server error
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventorySerialDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventorySerialDTO>> createSerialWithHttpInfo(InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return createSerialRequestCreation(inventorySerialDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create serial
     * Create a new inventory serial
     * <p><b>201</b> - Serial created successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>500</b> - Internal server error
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createSerialWithResponseSpec(InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createSerialRequestCreation(inventorySerialDTO, xIdempotencyKey);
    }
    /**
     * Delete serial
     * Delete a serial by its ID
     * <p><b>204</b> - Serial deleted successfully
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteSerialRequestCreation(Long serialId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'serialId' is set
        if (serialId == null) {
            throw new WebClientResponseException("Missing the required parameter 'serialId' when calling deleteSerial", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("serialId", serialId);

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
        return apiClient.invokeAPI("/api/v1/serials/{serialId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete serial
     * Delete a serial by its ID
     * <p><b>204</b> - Serial deleted successfully
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteSerial(Long serialId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteSerialRequestCreation(serialId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete serial
     * Delete a serial by its ID
     * <p><b>204</b> - Serial deleted successfully
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteSerialWithHttpInfo(Long serialId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteSerialRequestCreation(serialId).toEntity(localVarReturnType);
    }

    /**
     * Delete serial
     * Delete a serial by its ID
     * <p><b>204</b> - Serial deleted successfully
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteSerialWithResponseSpec(Long serialId) throws WebClientResponseException {
        return deleteSerialRequestCreation(serialId);
    }
    /**
     * Filter serials
     * Filter serials based on specified criteria
     * <p><b>200</b> - Successfully retrieved serials
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventorySerialDTO The filterRequestInventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterSerialsRequestCreation(FilterRequestInventorySerialDTO filterRequestInventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventorySerialDTO;
        // verify the required parameter 'filterRequestInventorySerialDTO' is set
        if (filterRequestInventorySerialDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventorySerialDTO' when calling filterSerials", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/serials/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter serials
     * Filter serials based on specified criteria
     * <p><b>200</b> - Successfully retrieved serials
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventorySerialDTO The filterRequestInventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterSerials(FilterRequestInventorySerialDTO filterRequestInventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterSerialsRequestCreation(filterRequestInventorySerialDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter serials
     * Filter serials based on specified criteria
     * <p><b>200</b> - Successfully retrieved serials
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventorySerialDTO The filterRequestInventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterSerialsWithHttpInfo(FilterRequestInventorySerialDTO filterRequestInventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterSerialsRequestCreation(filterRequestInventorySerialDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter serials
     * Filter serials based on specified criteria
     * <p><b>200</b> - Successfully retrieved serials
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventorySerialDTO The filterRequestInventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterSerialsWithResponseSpec(FilterRequestInventorySerialDTO filterRequestInventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterSerialsRequestCreation(filterRequestInventorySerialDTO, xIdempotencyKey);
    }
    /**
     * Get serial by ID
     * Retrieve a serial by its ID
     * <p><b>200</b> - Successfully retrieved serial
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to retrieve
     * @return InventorySerialDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getSerialByIdRequestCreation(Long serialId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'serialId' is set
        if (serialId == null) {
            throw new WebClientResponseException("Missing the required parameter 'serialId' when calling getSerialById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("serialId", serialId);

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

        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return apiClient.invokeAPI("/api/v1/serials/{serialId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get serial by ID
     * Retrieve a serial by its ID
     * <p><b>200</b> - Successfully retrieved serial
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to retrieve
     * @return InventorySerialDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventorySerialDTO> getSerialById(Long serialId) throws WebClientResponseException {
        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return getSerialByIdRequestCreation(serialId).bodyToMono(localVarReturnType);
    }

    /**
     * Get serial by ID
     * Retrieve a serial by its ID
     * <p><b>200</b> - Successfully retrieved serial
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to retrieve
     * @return ResponseEntity&lt;InventorySerialDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventorySerialDTO>> getSerialByIdWithHttpInfo(Long serialId) throws WebClientResponseException {
        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return getSerialByIdRequestCreation(serialId).toEntity(localVarReturnType);
    }

    /**
     * Get serial by ID
     * Retrieve a serial by its ID
     * <p><b>200</b> - Successfully retrieved serial
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getSerialByIdWithResponseSpec(Long serialId) throws WebClientResponseException {
        return getSerialByIdRequestCreation(serialId);
    }
    /**
     * Update serial
     * Update an existing serial
     * <p><b>200</b> - Serial updated successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to update
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventorySerialDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateSerialRequestCreation(Long serialId, InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventorySerialDTO;
        // verify the required parameter 'serialId' is set
        if (serialId == null) {
            throw new WebClientResponseException("Missing the required parameter 'serialId' when calling updateSerial", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventorySerialDTO' is set
        if (inventorySerialDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventorySerialDTO' when calling updateSerial", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("serialId", serialId);

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

        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return apiClient.invokeAPI("/api/v1/serials/{serialId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update serial
     * Update an existing serial
     * <p><b>200</b> - Serial updated successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to update
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventorySerialDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventorySerialDTO> updateSerial(Long serialId, InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return updateSerialRequestCreation(serialId, inventorySerialDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update serial
     * Update an existing serial
     * <p><b>200</b> - Serial updated successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to update
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventorySerialDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventorySerialDTO>> updateSerialWithHttpInfo(Long serialId, InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventorySerialDTO> localVarReturnType = new ParameterizedTypeReference<InventorySerialDTO>() {};
        return updateSerialRequestCreation(serialId, inventorySerialDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update serial
     * Update an existing serial
     * <p><b>200</b> - Serial updated successfully
     * <p><b>400</b> - Invalid serial data provided
     * <p><b>404</b> - Serial not found
     * <p><b>500</b> - Internal server error
     * @param serialId ID of the serial to update
     * @param inventorySerialDTO The inventorySerialDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateSerialWithResponseSpec(Long serialId, InventorySerialDTO inventorySerialDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateSerialRequestCreation(serialId, inventorySerialDTO, xIdempotencyKey);
    }
}

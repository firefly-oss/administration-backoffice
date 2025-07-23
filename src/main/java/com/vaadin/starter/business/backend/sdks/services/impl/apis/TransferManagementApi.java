package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryTransferDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryTransferDTO;
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
public class TransferManagementApi {
    private ApiClient apiClient;

    public TransferManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public TransferManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create transfer
     * Create a new inventory transfer
     * <p><b>201</b> - Transfer created successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryTransferDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createTransferRequestCreation(InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryTransferDTO;
        // verify the required parameter 'inventoryTransferDTO' is set
        if (inventoryTransferDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryTransferDTO' when calling createTransfer", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return apiClient.invokeAPI("/api/v1/transfers", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create transfer
     * Create a new inventory transfer
     * <p><b>201</b> - Transfer created successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryTransferDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryTransferDTO> createTransfer(InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return createTransferRequestCreation(inventoryTransferDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create transfer
     * Create a new inventory transfer
     * <p><b>201</b> - Transfer created successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryTransferDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryTransferDTO>> createTransferWithHttpInfo(InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return createTransferRequestCreation(inventoryTransferDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create transfer
     * Create a new inventory transfer
     * <p><b>201</b> - Transfer created successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createTransferWithResponseSpec(InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createTransferRequestCreation(inventoryTransferDTO, xIdempotencyKey);
    }
    /**
     * Delete transfer
     * Delete a transfer by its ID
     * <p><b>204</b> - Transfer deleted successfully
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteTransferRequestCreation(Long transferId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'transferId' is set
        if (transferId == null) {
            throw new WebClientResponseException("Missing the required parameter 'transferId' when calling deleteTransfer", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("transferId", transferId);

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
        return apiClient.invokeAPI("/api/v1/transfers/{transferId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete transfer
     * Delete a transfer by its ID
     * <p><b>204</b> - Transfer deleted successfully
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteTransfer(Long transferId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTransferRequestCreation(transferId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete transfer
     * Delete a transfer by its ID
     * <p><b>204</b> - Transfer deleted successfully
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteTransferWithHttpInfo(Long transferId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTransferRequestCreation(transferId).toEntity(localVarReturnType);
    }

    /**
     * Delete transfer
     * Delete a transfer by its ID
     * <p><b>204</b> - Transfer deleted successfully
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteTransferWithResponseSpec(Long transferId) throws WebClientResponseException {
        return deleteTransferRequestCreation(transferId);
    }
    /**
     * Filter transfers
     * Filter transfers based on specified criteria
     * <p><b>200</b> - Successfully retrieved transfers
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryTransferDTO The filterRequestInventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterTransfersRequestCreation(FilterRequestInventoryTransferDTO filterRequestInventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryTransferDTO;
        // verify the required parameter 'filterRequestInventoryTransferDTO' is set
        if (filterRequestInventoryTransferDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryTransferDTO' when calling filterTransfers", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/transfers/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter transfers
     * Filter transfers based on specified criteria
     * <p><b>200</b> - Successfully retrieved transfers
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryTransferDTO The filterRequestInventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterTransfers(FilterRequestInventoryTransferDTO filterRequestInventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterTransfersRequestCreation(filterRequestInventoryTransferDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter transfers
     * Filter transfers based on specified criteria
     * <p><b>200</b> - Successfully retrieved transfers
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryTransferDTO The filterRequestInventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterTransfersWithHttpInfo(FilterRequestInventoryTransferDTO filterRequestInventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterTransfersRequestCreation(filterRequestInventoryTransferDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter transfers
     * Filter transfers based on specified criteria
     * <p><b>200</b> - Successfully retrieved transfers
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryTransferDTO The filterRequestInventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterTransfersWithResponseSpec(FilterRequestInventoryTransferDTO filterRequestInventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterTransfersRequestCreation(filterRequestInventoryTransferDTO, xIdempotencyKey);
    }
    /**
     * Get transfer by ID
     * Retrieve a transfer by its ID
     * <p><b>200</b> - Successfully retrieved transfer
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to retrieve
     * @return InventoryTransferDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTransferByIdRequestCreation(Long transferId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'transferId' is set
        if (transferId == null) {
            throw new WebClientResponseException("Missing the required parameter 'transferId' when calling getTransferById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("transferId", transferId);

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

        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return apiClient.invokeAPI("/api/v1/transfers/{transferId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get transfer by ID
     * Retrieve a transfer by its ID
     * <p><b>200</b> - Successfully retrieved transfer
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to retrieve
     * @return InventoryTransferDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryTransferDTO> getTransferById(Long transferId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return getTransferByIdRequestCreation(transferId).bodyToMono(localVarReturnType);
    }

    /**
     * Get transfer by ID
     * Retrieve a transfer by its ID
     * <p><b>200</b> - Successfully retrieved transfer
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to retrieve
     * @return ResponseEntity&lt;InventoryTransferDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryTransferDTO>> getTransferByIdWithHttpInfo(Long transferId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return getTransferByIdRequestCreation(transferId).toEntity(localVarReturnType);
    }

    /**
     * Get transfer by ID
     * Retrieve a transfer by its ID
     * <p><b>200</b> - Successfully retrieved transfer
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTransferByIdWithResponseSpec(Long transferId) throws WebClientResponseException {
        return getTransferByIdRequestCreation(transferId);
    }
    /**
     * Update transfer
     * Update an existing transfer
     * <p><b>200</b> - Transfer updated successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to update
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryTransferDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateTransferRequestCreation(Long transferId, InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryTransferDTO;
        // verify the required parameter 'transferId' is set
        if (transferId == null) {
            throw new WebClientResponseException("Missing the required parameter 'transferId' when calling updateTransfer", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryTransferDTO' is set
        if (inventoryTransferDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryTransferDTO' when calling updateTransfer", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("transferId", transferId);

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

        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return apiClient.invokeAPI("/api/v1/transfers/{transferId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update transfer
     * Update an existing transfer
     * <p><b>200</b> - Transfer updated successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to update
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryTransferDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryTransferDTO> updateTransfer(Long transferId, InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return updateTransferRequestCreation(transferId, inventoryTransferDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update transfer
     * Update an existing transfer
     * <p><b>200</b> - Transfer updated successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to update
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryTransferDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryTransferDTO>> updateTransferWithHttpInfo(Long transferId, InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryTransferDTO> localVarReturnType = new ParameterizedTypeReference<InventoryTransferDTO>() {};
        return updateTransferRequestCreation(transferId, inventoryTransferDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update transfer
     * Update an existing transfer
     * <p><b>200</b> - Transfer updated successfully
     * <p><b>400</b> - Invalid transfer data provided
     * <p><b>404</b> - Transfer not found
     * <p><b>500</b> - Internal server error
     * @param transferId ID of the transfer to update
     * @param inventoryTransferDTO The inventoryTransferDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateTransferWithResponseSpec(Long transferId, InventoryTransferDTO inventoryTransferDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateTransferRequestCreation(transferId, inventoryTransferDTO, xIdempotencyKey);
    }
}

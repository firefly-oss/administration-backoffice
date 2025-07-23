package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryWarehouseDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryWarehouseDTO;
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
public class WarehouseManagementApi {
    private ApiClient apiClient;

    public WarehouseManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public WarehouseManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create warehouse
     * Create a new inventory warehouse
     * <p><b>201</b> - Warehouse created successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryWarehouseDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createWarehouseRequestCreation(InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryWarehouseDTO;
        // verify the required parameter 'inventoryWarehouseDTO' is set
        if (inventoryWarehouseDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryWarehouseDTO' when calling createWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return apiClient.invokeAPI("/api/v1/warehouses", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create warehouse
     * Create a new inventory warehouse
     * <p><b>201</b> - Warehouse created successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryWarehouseDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryWarehouseDTO> createWarehouse(InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return createWarehouseRequestCreation(inventoryWarehouseDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create warehouse
     * Create a new inventory warehouse
     * <p><b>201</b> - Warehouse created successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryWarehouseDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryWarehouseDTO>> createWarehouseWithHttpInfo(InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return createWarehouseRequestCreation(inventoryWarehouseDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create warehouse
     * Create a new inventory warehouse
     * <p><b>201</b> - Warehouse created successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createWarehouseWithResponseSpec(InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createWarehouseRequestCreation(inventoryWarehouseDTO, xIdempotencyKey);
    }
    /**
     * Delete warehouse
     * Delete a warehouse by its ID
     * <p><b>204</b> - Warehouse deleted successfully
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteWarehouseRequestCreation(Long warehouseId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling deleteWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("warehouseId", warehouseId);

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
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete warehouse
     * Delete a warehouse by its ID
     * <p><b>204</b> - Warehouse deleted successfully
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteWarehouse(Long warehouseId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteWarehouseRequestCreation(warehouseId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete warehouse
     * Delete a warehouse by its ID
     * <p><b>204</b> - Warehouse deleted successfully
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteWarehouseWithHttpInfo(Long warehouseId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteWarehouseRequestCreation(warehouseId).toEntity(localVarReturnType);
    }

    /**
     * Delete warehouse
     * Delete a warehouse by its ID
     * <p><b>204</b> - Warehouse deleted successfully
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteWarehouseWithResponseSpec(Long warehouseId) throws WebClientResponseException {
        return deleteWarehouseRequestCreation(warehouseId);
    }
    /**
     * Filter warehouses
     * Filter warehouses based on specified criteria
     * <p><b>200</b> - Successfully retrieved warehouses
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryWarehouseDTO The filterRequestInventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterWarehousesRequestCreation(FilterRequestInventoryWarehouseDTO filterRequestInventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryWarehouseDTO;
        // verify the required parameter 'filterRequestInventoryWarehouseDTO' is set
        if (filterRequestInventoryWarehouseDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryWarehouseDTO' when calling filterWarehouses", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/warehouses/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter warehouses
     * Filter warehouses based on specified criteria
     * <p><b>200</b> - Successfully retrieved warehouses
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryWarehouseDTO The filterRequestInventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterWarehouses(FilterRequestInventoryWarehouseDTO filterRequestInventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterWarehousesRequestCreation(filterRequestInventoryWarehouseDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter warehouses
     * Filter warehouses based on specified criteria
     * <p><b>200</b> - Successfully retrieved warehouses
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryWarehouseDTO The filterRequestInventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterWarehousesWithHttpInfo(FilterRequestInventoryWarehouseDTO filterRequestInventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterWarehousesRequestCreation(filterRequestInventoryWarehouseDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter warehouses
     * Filter warehouses based on specified criteria
     * <p><b>200</b> - Successfully retrieved warehouses
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryWarehouseDTO The filterRequestInventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterWarehousesWithResponseSpec(FilterRequestInventoryWarehouseDTO filterRequestInventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterWarehousesRequestCreation(filterRequestInventoryWarehouseDTO, xIdempotencyKey);
    }
    /**
     * Get warehouse by ID
     * Retrieve a warehouse by its ID
     * <p><b>200</b> - Successfully retrieved warehouse
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to retrieve
     * @return InventoryWarehouseDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getWarehouseByIdRequestCreation(Long warehouseId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling getWarehouseById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("warehouseId", warehouseId);

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

        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get warehouse by ID
     * Retrieve a warehouse by its ID
     * <p><b>200</b> - Successfully retrieved warehouse
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to retrieve
     * @return InventoryWarehouseDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryWarehouseDTO> getWarehouseById(Long warehouseId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return getWarehouseByIdRequestCreation(warehouseId).bodyToMono(localVarReturnType);
    }

    /**
     * Get warehouse by ID
     * Retrieve a warehouse by its ID
     * <p><b>200</b> - Successfully retrieved warehouse
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to retrieve
     * @return ResponseEntity&lt;InventoryWarehouseDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryWarehouseDTO>> getWarehouseByIdWithHttpInfo(Long warehouseId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return getWarehouseByIdRequestCreation(warehouseId).toEntity(localVarReturnType);
    }

    /**
     * Get warehouse by ID
     * Retrieve a warehouse by its ID
     * <p><b>200</b> - Successfully retrieved warehouse
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getWarehouseByIdWithResponseSpec(Long warehouseId) throws WebClientResponseException {
        return getWarehouseByIdRequestCreation(warehouseId);
    }
    /**
     * Update warehouse
     * Update an existing warehouse
     * <p><b>200</b> - Warehouse updated successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to update
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryWarehouseDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateWarehouseRequestCreation(Long warehouseId, InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryWarehouseDTO;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling updateWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryWarehouseDTO' is set
        if (inventoryWarehouseDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryWarehouseDTO' when calling updateWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("warehouseId", warehouseId);

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

        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update warehouse
     * Update an existing warehouse
     * <p><b>200</b> - Warehouse updated successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to update
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryWarehouseDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryWarehouseDTO> updateWarehouse(Long warehouseId, InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return updateWarehouseRequestCreation(warehouseId, inventoryWarehouseDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update warehouse
     * Update an existing warehouse
     * <p><b>200</b> - Warehouse updated successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to update
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryWarehouseDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryWarehouseDTO>> updateWarehouseWithHttpInfo(Long warehouseId, InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryWarehouseDTO> localVarReturnType = new ParameterizedTypeReference<InventoryWarehouseDTO>() {};
        return updateWarehouseRequestCreation(warehouseId, inventoryWarehouseDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update warehouse
     * Update an existing warehouse
     * <p><b>200</b> - Warehouse updated successfully
     * <p><b>400</b> - Invalid warehouse data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse to update
     * @param inventoryWarehouseDTO The inventoryWarehouseDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateWarehouseWithResponseSpec(Long warehouseId, InventoryWarehouseDTO inventoryWarehouseDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateWarehouseRequestCreation(warehouseId, inventoryWarehouseDTO, xIdempotencyKey);
    }
}

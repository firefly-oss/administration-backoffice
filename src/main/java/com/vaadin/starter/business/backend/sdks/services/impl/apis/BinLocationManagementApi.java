package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryBinLocationDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryBinLocationDTO;
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
public class BinLocationManagementApi {
    private ApiClient apiClient;

    public BinLocationManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public BinLocationManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create bin location in warehouse
     * Create a new bin location in a specific warehouse
     * <p><b>201</b> - Bin location created successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBinLocationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createBinLocationInWarehouseRequestCreation(Long warehouseId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryBinLocationDTO;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling createBinLocationInWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryBinLocationDTO' is set
        if (inventoryBinLocationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryBinLocationDTO' when calling createBinLocationInWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}/bin-locations", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create bin location in warehouse
     * Create a new bin location in a specific warehouse
     * <p><b>201</b> - Bin location created successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBinLocationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryBinLocationDTO> createBinLocationInWarehouse(Long warehouseId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return createBinLocationInWarehouseRequestCreation(warehouseId, inventoryBinLocationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create bin location in warehouse
     * Create a new bin location in a specific warehouse
     * <p><b>201</b> - Bin location created successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryBinLocationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryBinLocationDTO>> createBinLocationInWarehouseWithHttpInfo(Long warehouseId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return createBinLocationInWarehouseRequestCreation(warehouseId, inventoryBinLocationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create bin location in warehouse
     * Create a new bin location in a specific warehouse
     * <p><b>201</b> - Bin location created successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createBinLocationInWarehouseWithResponseSpec(Long warehouseId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createBinLocationInWarehouseRequestCreation(warehouseId, inventoryBinLocationDTO, xIdempotencyKey);
    }
    /**
     * Delete bin location
     * Delete a bin location by its ID
     * <p><b>204</b> - Bin location deleted successfully
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteBinLocationRequestCreation(Long binLocationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'binLocationId' is set
        if (binLocationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'binLocationId' when calling deleteBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("binLocationId", binLocationId);

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
        return apiClient.invokeAPI("/api/v1/bin-locations/{binLocationId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete bin location
     * Delete a bin location by its ID
     * <p><b>204</b> - Bin location deleted successfully
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteBinLocation(Long binLocationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteBinLocationRequestCreation(binLocationId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete bin location
     * Delete a bin location by its ID
     * <p><b>204</b> - Bin location deleted successfully
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteBinLocationWithHttpInfo(Long binLocationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteBinLocationRequestCreation(binLocationId).toEntity(localVarReturnType);
    }

    /**
     * Delete bin location
     * Delete a bin location by its ID
     * <p><b>204</b> - Bin location deleted successfully
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteBinLocationWithResponseSpec(Long binLocationId) throws WebClientResponseException {
        return deleteBinLocationRequestCreation(binLocationId);
    }
    /**
     * Filter bin locations
     * Filter bin locations based on specified criteria
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBinLocationDTO The filterRequestInventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterBinLocationsRequestCreation(FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryBinLocationDTO;
        // verify the required parameter 'filterRequestInventoryBinLocationDTO' is set
        if (filterRequestInventoryBinLocationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryBinLocationDTO' when calling filterBinLocations", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/bin-locations/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter bin locations
     * Filter bin locations based on specified criteria
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBinLocationDTO The filterRequestInventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterBinLocations(FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterBinLocationsRequestCreation(filterRequestInventoryBinLocationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter bin locations
     * Filter bin locations based on specified criteria
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBinLocationDTO The filterRequestInventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterBinLocationsWithHttpInfo(FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterBinLocationsRequestCreation(filterRequestInventoryBinLocationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter bin locations
     * Filter bin locations based on specified criteria
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryBinLocationDTO The filterRequestInventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterBinLocationsWithResponseSpec(FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterBinLocationsRequestCreation(filterRequestInventoryBinLocationDTO, xIdempotencyKey);
    }
    /**
     * Filter bin locations by warehouse
     * Filter bin locations in a specific warehouse
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterBinLocationsByWarehouseRequestCreation(Long warehouseId, FilterRequestInventoryBinLocationDTO filterRequest) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling filterBinLocationsByWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'filterRequest' is set
        if (filterRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequest' when calling filterBinLocationsByWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("warehouseId", warehouseId);

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
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}/bin-locations", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter bin locations by warehouse
     * Filter bin locations in a specific warehouse
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterBinLocationsByWarehouse(Long warehouseId, FilterRequestInventoryBinLocationDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterBinLocationsByWarehouseRequestCreation(warehouseId, filterRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Filter bin locations by warehouse
     * Filter bin locations in a specific warehouse
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterBinLocationsByWarehouseWithHttpInfo(Long warehouseId, FilterRequestInventoryBinLocationDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterBinLocationsByWarehouseRequestCreation(warehouseId, filterRequest).toEntity(localVarReturnType);
    }

    /**
     * Filter bin locations by warehouse
     * Filter bin locations in a specific warehouse
     * <p><b>200</b> - Successfully retrieved bin locations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterBinLocationsByWarehouseWithResponseSpec(Long warehouseId, FilterRequestInventoryBinLocationDTO filterRequest) throws WebClientResponseException {
        return filterBinLocationsByWarehouseRequestCreation(warehouseId, filterRequest);
    }
    /**
     * Get bin location by ID
     * Retrieve a bin location by its ID
     * <p><b>200</b> - Successfully retrieved bin location
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to retrieve
     * @return InventoryBinLocationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getBinLocationByIdRequestCreation(Long binLocationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'binLocationId' is set
        if (binLocationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'binLocationId' when calling getBinLocationById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("binLocationId", binLocationId);

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

        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return apiClient.invokeAPI("/api/v1/bin-locations/{binLocationId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get bin location by ID
     * Retrieve a bin location by its ID
     * <p><b>200</b> - Successfully retrieved bin location
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to retrieve
     * @return InventoryBinLocationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryBinLocationDTO> getBinLocationById(Long binLocationId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return getBinLocationByIdRequestCreation(binLocationId).bodyToMono(localVarReturnType);
    }

    /**
     * Get bin location by ID
     * Retrieve a bin location by its ID
     * <p><b>200</b> - Successfully retrieved bin location
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to retrieve
     * @return ResponseEntity&lt;InventoryBinLocationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryBinLocationDTO>> getBinLocationByIdWithHttpInfo(Long binLocationId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return getBinLocationByIdRequestCreation(binLocationId).toEntity(localVarReturnType);
    }

    /**
     * Get bin location by ID
     * Retrieve a bin location by its ID
     * <p><b>200</b> - Successfully retrieved bin location
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getBinLocationByIdWithResponseSpec(Long binLocationId) throws WebClientResponseException {
        return getBinLocationByIdRequestCreation(binLocationId);
    }
    /**
     * Update bin location
     * Update an existing bin location
     * <p><b>200</b> - Bin location updated successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to update
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBinLocationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateBinLocationRequestCreation(Long binLocationId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryBinLocationDTO;
        // verify the required parameter 'binLocationId' is set
        if (binLocationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'binLocationId' when calling updateBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryBinLocationDTO' is set
        if (inventoryBinLocationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryBinLocationDTO' when calling updateBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("binLocationId", binLocationId);

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

        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return apiClient.invokeAPI("/api/v1/bin-locations/{binLocationId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update bin location
     * Update an existing bin location
     * <p><b>200</b> - Bin location updated successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to update
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryBinLocationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryBinLocationDTO> updateBinLocation(Long binLocationId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return updateBinLocationRequestCreation(binLocationId, inventoryBinLocationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update bin location
     * Update an existing bin location
     * <p><b>200</b> - Bin location updated successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to update
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryBinLocationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryBinLocationDTO>> updateBinLocationWithHttpInfo(Long binLocationId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryBinLocationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryBinLocationDTO>() {};
        return updateBinLocationRequestCreation(binLocationId, inventoryBinLocationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update bin location
     * Update an existing bin location
     * <p><b>200</b> - Bin location updated successfully
     * <p><b>400</b> - Invalid bin location data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param binLocationId ID of the bin location to update
     * @param inventoryBinLocationDTO The inventoryBinLocationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateBinLocationWithResponseSpec(Long binLocationId, InventoryBinLocationDTO inventoryBinLocationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateBinLocationRequestCreation(binLocationId, inventoryBinLocationDTO, xIdempotencyKey);
    }
}

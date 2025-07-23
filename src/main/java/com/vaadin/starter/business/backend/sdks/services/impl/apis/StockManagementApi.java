package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryStockDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryStockDTO;
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
public class StockManagementApi {
    private ApiClient apiClient;

    public StockManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public StockManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create stock for item variant
     * Create a new stock for a specific item variant
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createStockForItemVariantRequestCreation(Long itemVariantId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryStockDTO;
        // verify the required parameter 'itemVariantId' is set
        if (itemVariantId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemVariantId' when calling createStockForItemVariant", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryStockDTO' is set
        if (inventoryStockDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryStockDTO' when calling createStockForItemVariant", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return apiClient.invokeAPI("/api/v1/item-variants/{itemVariantId}/stocks", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create stock for item variant
     * Create a new stock for a specific item variant
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryStockDTO> createStockForItemVariant(Long itemVariantId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return createStockForItemVariantRequestCreation(itemVariantId, inventoryStockDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create stock for item variant
     * Create a new stock for a specific item variant
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryStockDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryStockDTO>> createStockForItemVariantWithHttpInfo(Long itemVariantId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return createStockForItemVariantRequestCreation(itemVariantId, inventoryStockDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create stock for item variant
     * Create a new stock for a specific item variant
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createStockForItemVariantWithResponseSpec(Long itemVariantId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createStockForItemVariantRequestCreation(itemVariantId, inventoryStockDTO, xIdempotencyKey);
    }
    /**
     * Create stock in bin location
     * Create a new stock in a specific bin location
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createStockInBinLocationRequestCreation(Long warehouseId, Long binLocationId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryStockDTO;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling createStockInBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'binLocationId' is set
        if (binLocationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'binLocationId' when calling createStockInBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryStockDTO' is set
        if (inventoryStockDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryStockDTO' when calling createStockInBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("warehouseId", warehouseId);
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

        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}/bin-locations/{binLocationId}/stocks", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create stock in bin location
     * Create a new stock in a specific bin location
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryStockDTO> createStockInBinLocation(Long warehouseId, Long binLocationId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return createStockInBinLocationRequestCreation(warehouseId, binLocationId, inventoryStockDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create stock in bin location
     * Create a new stock in a specific bin location
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryStockDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryStockDTO>> createStockInBinLocationWithHttpInfo(Long warehouseId, Long binLocationId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return createStockInBinLocationRequestCreation(warehouseId, binLocationId, inventoryStockDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create stock in bin location
     * Create a new stock in a specific bin location
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createStockInBinLocationWithResponseSpec(Long warehouseId, Long binLocationId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createStockInBinLocationRequestCreation(warehouseId, binLocationId, inventoryStockDTO, xIdempotencyKey);
    }
    /**
     * Create stock in warehouse
     * Create a new stock in a specific warehouse
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createStockInWarehouseRequestCreation(Long warehouseId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryStockDTO;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling createStockInWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryStockDTO' is set
        if (inventoryStockDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryStockDTO' when calling createStockInWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}/stocks", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create stock in warehouse
     * Create a new stock in a specific warehouse
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryStockDTO> createStockInWarehouse(Long warehouseId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return createStockInWarehouseRequestCreation(warehouseId, inventoryStockDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create stock in warehouse
     * Create a new stock in a specific warehouse
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryStockDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryStockDTO>> createStockInWarehouseWithHttpInfo(Long warehouseId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return createStockInWarehouseRequestCreation(warehouseId, inventoryStockDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create stock in warehouse
     * Create a new stock in a specific warehouse
     * <p><b>201</b> - Stock created successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createStockInWarehouseWithResponseSpec(Long warehouseId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createStockInWarehouseRequestCreation(warehouseId, inventoryStockDTO, xIdempotencyKey);
    }
    /**
     * Delete stock
     * Delete a stock by its ID
     * <p><b>204</b> - Stock deleted successfully
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteStockRequestCreation(Long stockId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'stockId' is set
        if (stockId == null) {
            throw new WebClientResponseException("Missing the required parameter 'stockId' when calling deleteStock", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("stockId", stockId);

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
        return apiClient.invokeAPI("/api/v1/stocks/{stockId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete stock
     * Delete a stock by its ID
     * <p><b>204</b> - Stock deleted successfully
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteStock(Long stockId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteStockRequestCreation(stockId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete stock
     * Delete a stock by its ID
     * <p><b>204</b> - Stock deleted successfully
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteStockWithHttpInfo(Long stockId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteStockRequestCreation(stockId).toEntity(localVarReturnType);
    }

    /**
     * Delete stock
     * Delete a stock by its ID
     * <p><b>204</b> - Stock deleted successfully
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteStockWithResponseSpec(Long stockId) throws WebClientResponseException {
        return deleteStockRequestCreation(stockId);
    }
    /**
     * Filter stocks
     * Filter stocks based on specified criteria
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryStockDTO The filterRequestInventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterStocksRequestCreation(FilterRequestInventoryStockDTO filterRequestInventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryStockDTO;
        // verify the required parameter 'filterRequestInventoryStockDTO' is set
        if (filterRequestInventoryStockDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryStockDTO' when calling filterStocks", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/stocks/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter stocks
     * Filter stocks based on specified criteria
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryStockDTO The filterRequestInventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterStocks(FilterRequestInventoryStockDTO filterRequestInventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksRequestCreation(filterRequestInventoryStockDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter stocks
     * Filter stocks based on specified criteria
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryStockDTO The filterRequestInventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterStocksWithHttpInfo(FilterRequestInventoryStockDTO filterRequestInventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksRequestCreation(filterRequestInventoryStockDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter stocks
     * Filter stocks based on specified criteria
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryStockDTO The filterRequestInventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterStocksWithResponseSpec(FilterRequestInventoryStockDTO filterRequestInventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterStocksRequestCreation(filterRequestInventoryStockDTO, xIdempotencyKey);
    }
    /**
     * Filter stocks by bin location
     * Filter stocks in a specific bin location
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterStocksByBinLocationRequestCreation(Long warehouseId, Long binLocationId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling filterStocksByBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'binLocationId' is set
        if (binLocationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'binLocationId' when calling filterStocksByBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'filterRequest' is set
        if (filterRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequest' when calling filterStocksByBinLocation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("warehouseId", warehouseId);
        pathParams.put("binLocationId", binLocationId);

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
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}/bin-locations/{binLocationId}/stocks", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter stocks by bin location
     * Filter stocks in a specific bin location
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterStocksByBinLocation(Long warehouseId, Long binLocationId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksByBinLocationRequestCreation(warehouseId, binLocationId, filterRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Filter stocks by bin location
     * Filter stocks in a specific bin location
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param filterRequest The filterRequest parameter
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterStocksByBinLocationWithHttpInfo(Long warehouseId, Long binLocationId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksByBinLocationRequestCreation(warehouseId, binLocationId, filterRequest).toEntity(localVarReturnType);
    }

    /**
     * Filter stocks by bin location
     * Filter stocks in a specific bin location
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Bin location not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param binLocationId ID of the bin location
     * @param filterRequest The filterRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterStocksByBinLocationWithResponseSpec(Long warehouseId, Long binLocationId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        return filterStocksByBinLocationRequestCreation(warehouseId, binLocationId, filterRequest);
    }
    /**
     * Filter stocks by item variant
     * Filter stocks for a specific item variant
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterStocksByItemVariantRequestCreation(Long itemVariantId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'itemVariantId' is set
        if (itemVariantId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemVariantId' when calling filterStocksByItemVariant", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'filterRequest' is set
        if (filterRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequest' when calling filterStocksByItemVariant", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("itemVariantId", itemVariantId);

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
        return apiClient.invokeAPI("/api/v1/item-variants/{itemVariantId}/stocks", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter stocks by item variant
     * Filter stocks for a specific item variant
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterStocksByItemVariant(Long itemVariantId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksByItemVariantRequestCreation(itemVariantId, filterRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Filter stocks by item variant
     * Filter stocks for a specific item variant
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param filterRequest The filterRequest parameter
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterStocksByItemVariantWithHttpInfo(Long itemVariantId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksByItemVariantRequestCreation(itemVariantId, filterRequest).toEntity(localVarReturnType);
    }

    /**
     * Filter stocks by item variant
     * Filter stocks for a specific item variant
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Item variant not found
     * <p><b>500</b> - Internal server error
     * @param itemVariantId ID of the item variant
     * @param filterRequest The filterRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterStocksByItemVariantWithResponseSpec(Long itemVariantId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        return filterStocksByItemVariantRequestCreation(itemVariantId, filterRequest);
    }
    /**
     * Filter stocks by warehouse
     * Filter stocks in a specific warehouse
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterStocksByWarehouseRequestCreation(Long warehouseId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'warehouseId' is set
        if (warehouseId == null) {
            throw new WebClientResponseException("Missing the required parameter 'warehouseId' when calling filterStocksByWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'filterRequest' is set
        if (filterRequest == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequest' when calling filterStocksByWarehouse", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/warehouses/{warehouseId}/stocks", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter stocks by warehouse
     * Filter stocks in a specific warehouse
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterStocksByWarehouse(Long warehouseId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksByWarehouseRequestCreation(warehouseId, filterRequest).bodyToMono(localVarReturnType);
    }

    /**
     * Filter stocks by warehouse
     * Filter stocks in a specific warehouse
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterStocksByWarehouseWithHttpInfo(Long warehouseId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterStocksByWarehouseRequestCreation(warehouseId, filterRequest).toEntity(localVarReturnType);
    }

    /**
     * Filter stocks by warehouse
     * Filter stocks in a specific warehouse
     * <p><b>200</b> - Successfully retrieved stocks
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>404</b> - Warehouse not found
     * <p><b>500</b> - Internal server error
     * @param warehouseId ID of the warehouse
     * @param filterRequest The filterRequest parameter
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterStocksByWarehouseWithResponseSpec(Long warehouseId, FilterRequestInventoryStockDTO filterRequest) throws WebClientResponseException {
        return filterStocksByWarehouseRequestCreation(warehouseId, filterRequest);
    }
    /**
     * Get stock by ID
     * Retrieve a stock by its ID
     * <p><b>200</b> - Successfully retrieved stock
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to retrieve
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getStockByIdRequestCreation(Long stockId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'stockId' is set
        if (stockId == null) {
            throw new WebClientResponseException("Missing the required parameter 'stockId' when calling getStockById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("stockId", stockId);

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

        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return apiClient.invokeAPI("/api/v1/stocks/{stockId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get stock by ID
     * Retrieve a stock by its ID
     * <p><b>200</b> - Successfully retrieved stock
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to retrieve
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryStockDTO> getStockById(Long stockId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return getStockByIdRequestCreation(stockId).bodyToMono(localVarReturnType);
    }

    /**
     * Get stock by ID
     * Retrieve a stock by its ID
     * <p><b>200</b> - Successfully retrieved stock
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to retrieve
     * @return ResponseEntity&lt;InventoryStockDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryStockDTO>> getStockByIdWithHttpInfo(Long stockId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return getStockByIdRequestCreation(stockId).toEntity(localVarReturnType);
    }

    /**
     * Get stock by ID
     * Retrieve a stock by its ID
     * <p><b>200</b> - Successfully retrieved stock
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getStockByIdWithResponseSpec(Long stockId) throws WebClientResponseException {
        return getStockByIdRequestCreation(stockId);
    }
    /**
     * Update stock
     * Update an existing stock
     * <p><b>200</b> - Stock updated successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to update
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateStockRequestCreation(Long stockId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryStockDTO;
        // verify the required parameter 'stockId' is set
        if (stockId == null) {
            throw new WebClientResponseException("Missing the required parameter 'stockId' when calling updateStock", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryStockDTO' is set
        if (inventoryStockDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryStockDTO' when calling updateStock", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("stockId", stockId);

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

        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return apiClient.invokeAPI("/api/v1/stocks/{stockId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update stock
     * Update an existing stock
     * <p><b>200</b> - Stock updated successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to update
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryStockDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryStockDTO> updateStock(Long stockId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return updateStockRequestCreation(stockId, inventoryStockDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update stock
     * Update an existing stock
     * <p><b>200</b> - Stock updated successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to update
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryStockDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryStockDTO>> updateStockWithHttpInfo(Long stockId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryStockDTO> localVarReturnType = new ParameterizedTypeReference<InventoryStockDTO>() {};
        return updateStockRequestCreation(stockId, inventoryStockDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update stock
     * Update an existing stock
     * <p><b>200</b> - Stock updated successfully
     * <p><b>400</b> - Invalid stock data provided
     * <p><b>404</b> - Stock not found
     * <p><b>500</b> - Internal server error
     * @param stockId ID of the stock to update
     * @param inventoryStockDTO The inventoryStockDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateStockWithResponseSpec(Long stockId, InventoryStockDTO inventoryStockDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateStockRequestCreation(stockId, inventoryStockDTO, xIdempotencyKey);
    }
}

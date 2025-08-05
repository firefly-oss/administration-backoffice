package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.LookupItemDTO;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponse;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-08-05T11:16:42.322362700+02:00[Europe/Madrid]")
public class LookupItemsApi {
    private ApiClient apiClient;

    public LookupItemsApi() {
        this(new ApiClient());
    }

    @Autowired
    public LookupItemsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Lookup Item
     * Create a new lookup item.
     * <p><b>200</b> - Lookup item created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createItemRequestCreation(LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = lookupItemDTO;
        // verify the required parameter 'lookupItemDTO' is set
        if (lookupItemDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'lookupItemDTO' when calling createItem", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return apiClient.invokeAPI("/api/v1/lookup/items", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Lookup Item
     * Create a new lookup item.
     * <p><b>200</b> - Lookup item created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LookupItemDTO> createItem(LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return createItemRequestCreation(lookupItemDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Lookup Item
     * Create a new lookup item.
     * <p><b>200</b> - Lookup item created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LookupItemDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LookupItemDTO>> createItemWithHttpInfo(LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return createItemRequestCreation(lookupItemDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Lookup Item
     * Create a new lookup item.
     * <p><b>200</b> - Lookup item created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createItemWithResponseSpec(LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createItemRequestCreation(lookupItemDTO, xIdempotencyKey);
    }
    /**
     * Delete Lookup Item
     * Delete a specific lookup item by its ID.
     * <p><b>204</b> - Lookup item deleted successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteItemRequestCreation(Long itemId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'itemId' is set
        if (itemId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemId' when calling deleteItem", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("itemId", itemId);

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
        return apiClient.invokeAPI("/api/v1/lookup/items/{itemId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Lookup Item
     * Delete a specific lookup item by its ID.
     * <p><b>204</b> - Lookup item deleted successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteItem(Long itemId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteItemRequestCreation(itemId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Lookup Item
     * Delete a specific lookup item by its ID.
     * <p><b>204</b> - Lookup item deleted successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteItemWithHttpInfo(Long itemId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteItemRequestCreation(itemId).toEntity(localVarReturnType);
    }

    /**
     * Delete Lookup Item
     * Delete a specific lookup item by its ID.
     * <p><b>204</b> - Lookup item deleted successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteItemWithResponseSpec(Long itemId) throws WebClientResponseException {
        return deleteItemRequestCreation(itemId);
    }
    /**
     * Get Lookup Item by ID
     * Retrieve a specific lookup item by its ID.
     * <p><b>200</b> - Lookup item retrieved successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getItemRequestCreation(Long itemId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'itemId' is set
        if (itemId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemId' when calling getItem", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("itemId", itemId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return apiClient.invokeAPI("/api/v1/lookup/items/{itemId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Lookup Item by ID
     * Retrieve a specific lookup item by its ID.
     * <p><b>200</b> - Lookup item retrieved successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LookupItemDTO> getItem(Long itemId) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return getItemRequestCreation(itemId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Lookup Item by ID
     * Retrieve a specific lookup item by its ID.
     * <p><b>200</b> - Lookup item retrieved successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @return ResponseEntity&lt;LookupItemDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LookupItemDTO>> getItemWithHttpInfo(Long itemId) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return getItemRequestCreation(itemId).toEntity(localVarReturnType);
    }

    /**
     * Get Lookup Item by ID
     * Retrieve a specific lookup item by its ID.
     * <p><b>200</b> - Lookup item retrieved successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getItemWithResponseSpec(Long itemId) throws WebClientResponseException {
        return getItemRequestCreation(itemId);
    }
    /**
     * Get Lookup Items by Domain
     * Retrieve all lookup items for a specific domain.
     * <p><b>200</b> - Successfully retrieved lookup items for the domain
     * @param domainId ID of the domain
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getItemsByDomainRequestCreation(Long domainId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'domainId' is set
        if (domainId == null) {
            throw new WebClientResponseException("Missing the required parameter 'domainId' when calling getItemsByDomain", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("domainId", domainId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return apiClient.invokeAPI("/api/v1/lookup/items/domain/{domainId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Lookup Items by Domain
     * Retrieve all lookup items for a specific domain.
     * <p><b>200</b> - Successfully retrieved lookup items for the domain
     * @param domainId ID of the domain
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LookupItemDTO> getItemsByDomain(Long domainId) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return getItemsByDomainRequestCreation(domainId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Lookup Items by Domain
     * Retrieve all lookup items for a specific domain.
     * <p><b>200</b> - Successfully retrieved lookup items for the domain
     * @param domainId ID of the domain
     * @return ResponseEntity&lt;LookupItemDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LookupItemDTO>> getItemsByDomainWithHttpInfo(Long domainId) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return getItemsByDomainRequestCreation(domainId).toEntity(localVarReturnType);
    }

    /**
     * Get Lookup Items by Domain
     * Retrieve all lookup items for a specific domain.
     * <p><b>200</b> - Successfully retrieved lookup items for the domain
     * @param domainId ID of the domain
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getItemsByDomainWithResponseSpec(Long domainId) throws WebClientResponseException {
        return getItemsByDomainRequestCreation(domainId);
    }
    /**
     * List Lookup Items
     * Retrieve a paginated list of lookup items.
     * <p><b>200</b> - Successfully retrieved list of lookup items
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listItemsRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "pageNumber", pageNumber));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "pageSize", pageSize));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortBy", sortBy));
        queryParams.putAll(apiClient.parameterToMultiValueMap(null, "sortDirection", sortDirection));

        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return apiClient.invokeAPI("/api/v1/lookup/items", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Lookup Items
     * Retrieve a paginated list of lookup items.
     * <p><b>200</b> - Successfully retrieved list of lookup items
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listItems(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listItemsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Lookup Items
     * Retrieve a paginated list of lookup items.
     * <p><b>200</b> - Successfully retrieved list of lookup items
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listItemsWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listItemsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Lookup Items
     * Retrieve a paginated list of lookup items.
     * <p><b>200</b> - Successfully retrieved list of lookup items
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listItemsWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listItemsRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Lookup Item
     * Update an existing lookup item by its ID.
     * <p><b>200</b> - Lookup item updated successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateItemRequestCreation(Long itemId, LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = lookupItemDTO;
        // verify the required parameter 'itemId' is set
        if (itemId == null) {
            throw new WebClientResponseException("Missing the required parameter 'itemId' when calling updateItem", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'lookupItemDTO' is set
        if (lookupItemDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'lookupItemDTO' when calling updateItem", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("itemId", itemId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (xIdempotencyKey != null)
        headerParams.add("X-Idempotency-Key", apiClient.parameterToString(xIdempotencyKey));
        final String[] localVarAccepts = { 
            "application/json"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return apiClient.invokeAPI("/api/v1/lookup/items/{itemId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Lookup Item
     * Update an existing lookup item by its ID.
     * <p><b>200</b> - Lookup item updated successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupItemDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LookupItemDTO> updateItem(Long itemId, LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return updateItemRequestCreation(itemId, lookupItemDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Lookup Item
     * Update an existing lookup item by its ID.
     * <p><b>200</b> - Lookup item updated successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LookupItemDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LookupItemDTO>> updateItemWithHttpInfo(Long itemId, LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupItemDTO> localVarReturnType = new ParameterizedTypeReference<LookupItemDTO>() {};
        return updateItemRequestCreation(itemId, lookupItemDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Lookup Item
     * Update an existing lookup item by its ID.
     * <p><b>200</b> - Lookup item updated successfully
     * <p><b>404</b> - Lookup item not found
     * @param itemId ID of the lookup item
     * @param lookupItemDTO The lookupItemDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateItemWithResponseSpec(Long itemId, LookupItemDTO lookupItemDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateItemRequestCreation(itemId, lookupItemDTO, xIdempotencyKey);
    }
}

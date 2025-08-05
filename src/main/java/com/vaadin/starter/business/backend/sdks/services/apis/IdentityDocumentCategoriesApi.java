package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.IdentityDocumentCategoryCatalogDTO;
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
public class IdentityDocumentCategoriesApi {
    private ApiClient apiClient;

    public IdentityDocumentCategoriesApi() {
        this(new ApiClient());
    }

    @Autowired
    public IdentityDocumentCategoriesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Identity Document Category
     * Create a new identity document category.
     * <p><b>200</b> - Identity document category created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createIdentityDocumentCategoryRequestCreation(IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = identityDocumentCategoryCatalogDTO;
        // verify the required parameter 'identityDocumentCategoryCatalogDTO' is set
        if (identityDocumentCategoryCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'identityDocumentCategoryCatalogDTO' when calling createIdentityDocumentCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-document-categories", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Identity Document Category
     * Create a new identity document category.
     * <p><b>200</b> - Identity document category created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCategoryCatalogDTO> createIdentityDocumentCategory(IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return createIdentityDocumentCategoryRequestCreation(identityDocumentCategoryCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Identity Document Category
     * Create a new identity document category.
     * <p><b>200</b> - Identity document category created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;IdentityDocumentCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCategoryCatalogDTO>> createIdentityDocumentCategoryWithHttpInfo(IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return createIdentityDocumentCategoryRequestCreation(identityDocumentCategoryCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Identity Document Category
     * Create a new identity document category.
     * <p><b>200</b> - Identity document category created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createIdentityDocumentCategoryWithResponseSpec(IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createIdentityDocumentCategoryRequestCreation(identityDocumentCategoryCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Identity Document Category
     * Delete a specific identity document category by its ID.
     * <p><b>204</b> - Identity document category deleted successfully
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteIdentityDocumentCategoryRequestCreation(Long categoryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling deleteIdentityDocumentCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("categoryId", categoryId);

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
        return apiClient.invokeAPI("/api/v1/identity-document-categories/{categoryId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Identity Document Category
     * Delete a specific identity document category by its ID.
     * <p><b>204</b> - Identity document category deleted successfully
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteIdentityDocumentCategory(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteIdentityDocumentCategoryRequestCreation(categoryId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Identity Document Category
     * Delete a specific identity document category by its ID.
     * <p><b>204</b> - Identity document category deleted successfully
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteIdentityDocumentCategoryWithHttpInfo(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteIdentityDocumentCategoryRequestCreation(categoryId).toEntity(localVarReturnType);
    }

    /**
     * Delete Identity Document Category
     * Delete a specific identity document category by its ID.
     * <p><b>204</b> - Identity document category deleted successfully
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteIdentityDocumentCategoryWithResponseSpec(Long categoryId) throws WebClientResponseException {
        return deleteIdentityDocumentCategoryRequestCreation(categoryId);
    }
    /**
     * Get Identity Document Category
     * Get a specific identity document category by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getIdentityDocumentCategoryRequestCreation(Long categoryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling getIdentityDocumentCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("categoryId", categoryId);

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

        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-document-categories/{categoryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Identity Document Category
     * Get a specific identity document category by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCategoryCatalogDTO> getIdentityDocumentCategory(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return getIdentityDocumentCategoryRequestCreation(categoryId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Identity Document Category
     * Get a specific identity document category by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @return ResponseEntity&lt;IdentityDocumentCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCategoryCatalogDTO>> getIdentityDocumentCategoryWithHttpInfo(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return getIdentityDocumentCategoryRequestCreation(categoryId).toEntity(localVarReturnType);
    }

    /**
     * Get Identity Document Category
     * Get a specific identity document category by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getIdentityDocumentCategoryWithResponseSpec(Long categoryId) throws WebClientResponseException {
        return getIdentityDocumentCategoryRequestCreation(categoryId);
    }
    /**
     * Get Identity Document Category by Code
     * Get a specific identity document category by its code.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryCode Code of the identity document category
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getIdentityDocumentCategoryByCodeRequestCreation(String categoryCode) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryCode' is set
        if (categoryCode == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryCode' when calling getIdentityDocumentCategoryByCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("categoryCode", categoryCode);

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

        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-document-categories/code/{categoryCode}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Identity Document Category by Code
     * Get a specific identity document category by its code.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryCode Code of the identity document category
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCategoryCatalogDTO> getIdentityDocumentCategoryByCode(String categoryCode) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return getIdentityDocumentCategoryByCodeRequestCreation(categoryCode).bodyToMono(localVarReturnType);
    }

    /**
     * Get Identity Document Category by Code
     * Get a specific identity document category by its code.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryCode Code of the identity document category
     * @return ResponseEntity&lt;IdentityDocumentCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCategoryCatalogDTO>> getIdentityDocumentCategoryByCodeWithHttpInfo(String categoryCode) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return getIdentityDocumentCategoryByCodeRequestCreation(categoryCode).toEntity(localVarReturnType);
    }

    /**
     * Get Identity Document Category by Code
     * Get a specific identity document category by its code.
     * <p><b>200</b> - Successfully retrieved the identity document category
     * <p><b>404</b> - Identity document category not found
     * @param categoryCode Code of the identity document category
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getIdentityDocumentCategoryByCodeWithResponseSpec(String categoryCode) throws WebClientResponseException {
        return getIdentityDocumentCategoryByCodeRequestCreation(categoryCode);
    }
    /**
     * List Identity Document Categories
     * Retrieve a paginated list of identity document categories.
     * <p><b>200</b> - Successfully retrieved list of identity document categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listIdentityDocumentCategoriesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/identity-document-categories", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Identity Document Categories
     * Retrieve a paginated list of identity document categories.
     * <p><b>200</b> - Successfully retrieved list of identity document categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listIdentityDocumentCategories(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Identity Document Categories
     * Retrieve a paginated list of identity document categories.
     * <p><b>200</b> - Successfully retrieved list of identity document categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listIdentityDocumentCategoriesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Identity Document Categories
     * Retrieve a paginated list of identity document categories.
     * <p><b>200</b> - Successfully retrieved list of identity document categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listIdentityDocumentCategoriesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listIdentityDocumentCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Identity Document Category
     * Update a specific identity document category by its ID.
     * <p><b>200</b> - Identity document category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateIdentityDocumentCategoryRequestCreation(Long categoryId, IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = identityDocumentCategoryCatalogDTO;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling updateIdentityDocumentCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'identityDocumentCategoryCatalogDTO' is set
        if (identityDocumentCategoryCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'identityDocumentCategoryCatalogDTO' when calling updateIdentityDocumentCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("categoryId", categoryId);

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

        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-document-categories/{categoryId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Identity Document Category
     * Update a specific identity document category by its ID.
     * <p><b>200</b> - Identity document category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCategoryCatalogDTO> updateIdentityDocumentCategory(Long categoryId, IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return updateIdentityDocumentCategoryRequestCreation(categoryId, identityDocumentCategoryCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Identity Document Category
     * Update a specific identity document category by its ID.
     * <p><b>200</b> - Identity document category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;IdentityDocumentCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCategoryCatalogDTO>> updateIdentityDocumentCategoryWithHttpInfo(Long categoryId, IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCategoryCatalogDTO>() {};
        return updateIdentityDocumentCategoryRequestCreation(categoryId, identityDocumentCategoryCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Identity Document Category
     * Update a specific identity document category by its ID.
     * <p><b>200</b> - Identity document category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document category not found
     * @param categoryId ID of the identity document category
     * @param identityDocumentCategoryCatalogDTO The identityDocumentCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateIdentityDocumentCategoryWithResponseSpec(Long categoryId, IdentityDocumentCategoryCatalogDTO identityDocumentCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateIdentityDocumentCategoryRequestCreation(categoryId, identityDocumentCategoryCatalogDTO, xIdempotencyKey);
    }
}

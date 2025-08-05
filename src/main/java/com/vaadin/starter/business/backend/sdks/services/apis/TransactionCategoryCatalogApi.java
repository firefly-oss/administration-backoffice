package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponse;
import com.catalis.common.reference.master.data.sdk.model.TransactionCategoryCatalogDTO;
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
public class TransactionCategoryCatalogApi {
    private ApiClient apiClient;

    public TransactionCategoryCatalogApi() {
        this(new ApiClient());
    }

    @Autowired
    public TransactionCategoryCatalogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Transaction Category
     * Create a new transaction category.
     * <p><b>200</b> - Transaction category created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createTransactionCategoryRequestCreation(TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = transactionCategoryCatalogDTO;
        // verify the required parameter 'transactionCategoryCatalogDTO' is set
        if (transactionCategoryCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'transactionCategoryCatalogDTO' when calling createTransactionCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-categories", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Transaction Category
     * Create a new transaction category.
     * <p><b>200</b> - Transaction category created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryCatalogDTO> createTransactionCategory(TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return createTransactionCategoryRequestCreation(transactionCategoryCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Transaction Category
     * Create a new transaction category.
     * <p><b>200</b> - Transaction category created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;TransactionCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryCatalogDTO>> createTransactionCategoryWithHttpInfo(TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return createTransactionCategoryRequestCreation(transactionCategoryCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Transaction Category
     * Create a new transaction category.
     * <p><b>200</b> - Transaction category created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createTransactionCategoryWithResponseSpec(TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createTransactionCategoryRequestCreation(transactionCategoryCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Transaction Category
     * Delete a transaction category.
     * <p><b>204</b> - Transaction category deleted successfully
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteTransactionCategoryRequestCreation(Long categoryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling deleteTransactionCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/transaction-categories/{categoryId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Transaction Category
     * Delete a transaction category.
     * <p><b>204</b> - Transaction category deleted successfully
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteTransactionCategory(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTransactionCategoryRequestCreation(categoryId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Transaction Category
     * Delete a transaction category.
     * <p><b>204</b> - Transaction category deleted successfully
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteTransactionCategoryWithHttpInfo(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTransactionCategoryRequestCreation(categoryId).toEntity(localVarReturnType);
    }

    /**
     * Delete Transaction Category
     * Delete a transaction category.
     * <p><b>204</b> - Transaction category deleted successfully
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteTransactionCategoryWithResponseSpec(Long categoryId) throws WebClientResponseException {
        return deleteTransactionCategoryRequestCreation(categoryId);
    }
    /**
     * Get Transaction Category by ID
     * Retrieve a transaction category by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTransactionCategoryRequestCreation(Long categoryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling getTransactionCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-categories/{categoryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Transaction Category by ID
     * Retrieve a transaction category by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryCatalogDTO> getTransactionCategory(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return getTransactionCategoryRequestCreation(categoryId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Transaction Category by ID
     * Retrieve a transaction category by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category
     * @return ResponseEntity&lt;TransactionCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryCatalogDTO>> getTransactionCategoryWithHttpInfo(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return getTransactionCategoryRequestCreation(categoryId).toEntity(localVarReturnType);
    }

    /**
     * Get Transaction Category by ID
     * Retrieve a transaction category by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTransactionCategoryWithResponseSpec(Long categoryId) throws WebClientResponseException {
        return getTransactionCategoryRequestCreation(categoryId);
    }
    /**
     * Get Transaction Category by Code
     * Retrieve a transaction category by its code.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryCode Code of the transaction category
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTransactionCategoryByCodeRequestCreation(String categoryCode) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryCode' is set
        if (categoryCode == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryCode' when calling getTransactionCategoryByCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-categories/code/{categoryCode}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Transaction Category by Code
     * Retrieve a transaction category by its code.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryCode Code of the transaction category
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryCatalogDTO> getTransactionCategoryByCode(String categoryCode) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return getTransactionCategoryByCodeRequestCreation(categoryCode).bodyToMono(localVarReturnType);
    }

    /**
     * Get Transaction Category by Code
     * Retrieve a transaction category by its code.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryCode Code of the transaction category
     * @return ResponseEntity&lt;TransactionCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryCatalogDTO>> getTransactionCategoryByCodeWithHttpInfo(String categoryCode) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return getTransactionCategoryByCodeRequestCreation(categoryCode).toEntity(localVarReturnType);
    }

    /**
     * Get Transaction Category by Code
     * Retrieve a transaction category by its code.
     * <p><b>200</b> - Successfully retrieved the transaction category
     * <p><b>404</b> - Transaction category not found
     * @param categoryCode Code of the transaction category
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTransactionCategoryByCodeWithResponseSpec(String categoryCode) throws WebClientResponseException {
        return getTransactionCategoryByCodeRequestCreation(categoryCode);
    }
    /**
     * List Child Transaction Categories
     * Retrieve a paginated list of child transaction categories for a specific parent category.
     * <p><b>200</b> - Successfully retrieved list of child transaction categories
     * @param parentCategoryId ID of the parent category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listChildTransactionCategoriesRequestCreation(Long parentCategoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'parentCategoryId' is set
        if (parentCategoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'parentCategoryId' when calling listChildTransactionCategories", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("parentCategoryId", parentCategoryId);

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
        return apiClient.invokeAPI("/api/v1/transaction-categories/parent/{parentCategoryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Child Transaction Categories
     * Retrieve a paginated list of child transaction categories for a specific parent category.
     * <p><b>200</b> - Successfully retrieved list of child transaction categories
     * @param parentCategoryId ID of the parent category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listChildTransactionCategories(Long parentCategoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listChildTransactionCategoriesRequestCreation(parentCategoryId, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Child Transaction Categories
     * Retrieve a paginated list of child transaction categories for a specific parent category.
     * <p><b>200</b> - Successfully retrieved list of child transaction categories
     * @param parentCategoryId ID of the parent category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listChildTransactionCategoriesWithHttpInfo(Long parentCategoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listChildTransactionCategoriesRequestCreation(parentCategoryId, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Child Transaction Categories
     * Retrieve a paginated list of child transaction categories for a specific parent category.
     * <p><b>200</b> - Successfully retrieved list of child transaction categories
     * @param parentCategoryId ID of the parent category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listChildTransactionCategoriesWithResponseSpec(Long parentCategoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listChildTransactionCategoriesRequestCreation(parentCategoryId, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Root Transaction Categories
     * Retrieve a paginated list of root transaction categories (categories without a parent).
     * <p><b>200</b> - Successfully retrieved list of root transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listRootTransactionCategoriesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/transaction-categories/root", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Root Transaction Categories
     * Retrieve a paginated list of root transaction categories (categories without a parent).
     * <p><b>200</b> - Successfully retrieved list of root transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listRootTransactionCategories(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listRootTransactionCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Root Transaction Categories
     * Retrieve a paginated list of root transaction categories (categories without a parent).
     * <p><b>200</b> - Successfully retrieved list of root transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listRootTransactionCategoriesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listRootTransactionCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Root Transaction Categories
     * Retrieve a paginated list of root transaction categories (categories without a parent).
     * <p><b>200</b> - Successfully retrieved list of root transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listRootTransactionCategoriesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listRootTransactionCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List All Transaction Categories
     * Retrieve a paginated list of all transaction categories.
     * <p><b>200</b> - Successfully retrieved list of transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listTransactionCategoriesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/transaction-categories", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List All Transaction Categories
     * Retrieve a paginated list of all transaction categories.
     * <p><b>200</b> - Successfully retrieved list of transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listTransactionCategories(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listTransactionCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List All Transaction Categories
     * Retrieve a paginated list of all transaction categories.
     * <p><b>200</b> - Successfully retrieved list of transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listTransactionCategoriesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listTransactionCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List All Transaction Categories
     * Retrieve a paginated list of all transaction categories.
     * <p><b>200</b> - Successfully retrieved list of transaction categories
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listTransactionCategoriesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listTransactionCategoriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Transaction Category
     * Update an existing transaction category.
     * <p><b>200</b> - Transaction category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to update
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateTransactionCategoryRequestCreation(Long categoryId, TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = transactionCategoryCatalogDTO;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling updateTransactionCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'transactionCategoryCatalogDTO' is set
        if (transactionCategoryCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'transactionCategoryCatalogDTO' when calling updateTransactionCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-categories/{categoryId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Transaction Category
     * Update an existing transaction category.
     * <p><b>200</b> - Transaction category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to update
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryCatalogDTO> updateTransactionCategory(Long categoryId, TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return updateTransactionCategoryRequestCreation(categoryId, transactionCategoryCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Transaction Category
     * Update an existing transaction category.
     * <p><b>200</b> - Transaction category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to update
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;TransactionCategoryCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryCatalogDTO>> updateTransactionCategoryWithHttpInfo(Long categoryId, TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryCatalogDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryCatalogDTO>() {};
        return updateTransactionCategoryRequestCreation(categoryId, transactionCategoryCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Transaction Category
     * Update an existing transaction category.
     * <p><b>200</b> - Transaction category updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category not found
     * @param categoryId ID of the transaction category to update
     * @param transactionCategoryCatalogDTO The transactionCategoryCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateTransactionCategoryWithResponseSpec(Long categoryId, TransactionCategoryCatalogDTO transactionCategoryCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateTransactionCategoryRequestCreation(categoryId, transactionCategoryCatalogDTO, xIdempotencyKey);
    }
}

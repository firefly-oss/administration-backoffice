package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponse;
import com.catalis.common.reference.master.data.sdk.model.TransactionCategoryLocalizationDTO;
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
public class TransactionCategoryLocalizationApi {
    private ApiClient apiClient;

    public TransactionCategoryLocalizationApi() {
        this(new ApiClient());
    }

    @Autowired
    public TransactionCategoryLocalizationApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Transaction Category Localization
     * Create a new transaction category localization.
     * <p><b>200</b> - Transaction category localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createTransactionCategoryLocalizationRequestCreation(TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = transactionCategoryLocalizationDTO;
        // verify the required parameter 'transactionCategoryLocalizationDTO' is set
        if (transactionCategoryLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'transactionCategoryLocalizationDTO' when calling createTransactionCategoryLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-category-localizations", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Transaction Category Localization
     * Create a new transaction category localization.
     * <p><b>200</b> - Transaction category localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryLocalizationDTO> createTransactionCategoryLocalization(TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return createTransactionCategoryLocalizationRequestCreation(transactionCategoryLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Transaction Category Localization
     * Create a new transaction category localization.
     * <p><b>200</b> - Transaction category localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;TransactionCategoryLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryLocalizationDTO>> createTransactionCategoryLocalizationWithHttpInfo(TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return createTransactionCategoryLocalizationRequestCreation(transactionCategoryLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Transaction Category Localization
     * Create a new transaction category localization.
     * <p><b>200</b> - Transaction category localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createTransactionCategoryLocalizationWithResponseSpec(TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createTransactionCategoryLocalizationRequestCreation(transactionCategoryLocalizationDTO, xIdempotencyKey);
    }
    /**
     * Delete Transaction Category Localization
     * Delete a transaction category localization.
     * <p><b>204</b> - Transaction category localization deleted successfully
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteTransactionCategoryLocalizationRequestCreation(Long localizationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling deleteTransactionCategoryLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("localizationId", localizationId);

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
        return apiClient.invokeAPI("/api/v1/transaction-category-localizations/{localizationId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Transaction Category Localization
     * Delete a transaction category localization.
     * <p><b>204</b> - Transaction category localization deleted successfully
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteTransactionCategoryLocalization(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTransactionCategoryLocalizationRequestCreation(localizationId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Transaction Category Localization
     * Delete a transaction category localization.
     * <p><b>204</b> - Transaction category localization deleted successfully
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteTransactionCategoryLocalizationWithHttpInfo(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTransactionCategoryLocalizationRequestCreation(localizationId).toEntity(localVarReturnType);
    }

    /**
     * Delete Transaction Category Localization
     * Delete a transaction category localization.
     * <p><b>204</b> - Transaction category localization deleted successfully
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteTransactionCategoryLocalizationWithResponseSpec(Long localizationId) throws WebClientResponseException {
        return deleteTransactionCategoryLocalizationRequestCreation(localizationId);
    }
    /**
     * List Localizations by Category
     * Retrieve all localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved localizations for the specified category
     * <p><b>404</b> - No localizations found for the specified category
     * @param categoryId ID of the transaction category
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLocalizationsByCategoryIdRequestCreation(Long categoryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling getLocalizationsByCategoryId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-category-localizations/category/{categoryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Localizations by Category
     * Retrieve all localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved localizations for the specified category
     * <p><b>404</b> - No localizations found for the specified category
     * @param categoryId ID of the transaction category
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryLocalizationDTO> getLocalizationsByCategoryId(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return getLocalizationsByCategoryIdRequestCreation(categoryId).bodyToMono(localVarReturnType);
    }

    /**
     * List Localizations by Category
     * Retrieve all localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved localizations for the specified category
     * <p><b>404</b> - No localizations found for the specified category
     * @param categoryId ID of the transaction category
     * @return ResponseEntity&lt;TransactionCategoryLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryLocalizationDTO>> getLocalizationsByCategoryIdWithHttpInfo(Long categoryId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return getLocalizationsByCategoryIdRequestCreation(categoryId).toEntity(localVarReturnType);
    }

    /**
     * List Localizations by Category
     * Retrieve all localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved localizations for the specified category
     * <p><b>404</b> - No localizations found for the specified category
     * @param categoryId ID of the transaction category
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLocalizationsByCategoryIdWithResponseSpec(Long categoryId) throws WebClientResponseException {
        return getLocalizationsByCategoryIdRequestCreation(categoryId);
    }
    /**
     * Get Localization by ID
     * Retrieve a transaction category localization by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTransactionCategoryLocalizationRequestCreation(Long localizationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling getTransactionCategoryLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("localizationId", localizationId);

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

        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-category-localizations/{localizationId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Localization by ID
     * Retrieve a transaction category localization by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryLocalizationDTO> getTransactionCategoryLocalization(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return getTransactionCategoryLocalizationRequestCreation(localizationId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Localization by ID
     * Retrieve a transaction category localization by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization
     * @return ResponseEntity&lt;TransactionCategoryLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryLocalizationDTO>> getTransactionCategoryLocalizationWithHttpInfo(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return getTransactionCategoryLocalizationRequestCreation(localizationId).toEntity(localVarReturnType);
    }

    /**
     * Get Localization by ID
     * Retrieve a transaction category localization by its ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTransactionCategoryLocalizationWithResponseSpec(Long localizationId) throws WebClientResponseException {
        return getTransactionCategoryLocalizationRequestCreation(localizationId);
    }
    /**
     * Get Localization by Category and Locale
     * Retrieve a transaction category localization by category ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param categoryId ID of the transaction category
     * @param localeId ID of the locale
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTransactionCategoryLocalizationByCategoryAndLocaleRequestCreation(Long categoryId, Long localeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling getTransactionCategoryLocalizationByCategoryAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'localeId' is set
        if (localeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localeId' when calling getTransactionCategoryLocalizationByCategoryAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("categoryId", categoryId);
        pathParams.put("localeId", localeId);

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

        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-category-localizations/category/{categoryId}/locale/{localeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Localization by Category and Locale
     * Retrieve a transaction category localization by category ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param categoryId ID of the transaction category
     * @param localeId ID of the locale
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryLocalizationDTO> getTransactionCategoryLocalizationByCategoryAndLocale(Long categoryId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return getTransactionCategoryLocalizationByCategoryAndLocaleRequestCreation(categoryId, localeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Localization by Category and Locale
     * Retrieve a transaction category localization by category ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param categoryId ID of the transaction category
     * @param localeId ID of the locale
     * @return ResponseEntity&lt;TransactionCategoryLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryLocalizationDTO>> getTransactionCategoryLocalizationByCategoryAndLocaleWithHttpInfo(Long categoryId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return getTransactionCategoryLocalizationByCategoryAndLocaleRequestCreation(categoryId, localeId).toEntity(localVarReturnType);
    }

    /**
     * Get Localization by Category and Locale
     * Retrieve a transaction category localization by category ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the transaction category localization
     * <p><b>404</b> - Transaction category localization not found
     * @param categoryId ID of the transaction category
     * @param localeId ID of the locale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTransactionCategoryLocalizationByCategoryAndLocaleWithResponseSpec(Long categoryId, Long localeId) throws WebClientResponseException {
        return getTransactionCategoryLocalizationByCategoryAndLocaleRequestCreation(categoryId, localeId);
    }
    /**
     * List Localizations by Category with Pagination
     * Retrieve a paginated list of localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved paginated localizations for the specified category
     * @param categoryId ID of the transaction category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listLocalizationsByCategoryIdRequestCreation(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling listLocalizationsByCategoryId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("categoryId", categoryId);

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
        return apiClient.invokeAPI("/api/v1/transaction-category-localizations/category/{categoryId}/paginated", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Localizations by Category with Pagination
     * Retrieve a paginated list of localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved paginated localizations for the specified category
     * @param categoryId ID of the transaction category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listLocalizationsByCategoryId(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listLocalizationsByCategoryIdRequestCreation(categoryId, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Localizations by Category with Pagination
     * Retrieve a paginated list of localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved paginated localizations for the specified category
     * @param categoryId ID of the transaction category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listLocalizationsByCategoryIdWithHttpInfo(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listLocalizationsByCategoryIdRequestCreation(categoryId, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Localizations by Category with Pagination
     * Retrieve a paginated list of localizations for a specific transaction category.
     * <p><b>200</b> - Successfully retrieved paginated localizations for the specified category
     * @param categoryId ID of the transaction category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listLocalizationsByCategoryIdWithResponseSpec(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listLocalizationsByCategoryIdRequestCreation(categoryId, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Transaction Category Localization
     * Update an existing transaction category localization.
     * <p><b>200</b> - Transaction category localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to update
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateTransactionCategoryLocalizationRequestCreation(Long localizationId, TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = transactionCategoryLocalizationDTO;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling updateTransactionCategoryLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'transactionCategoryLocalizationDTO' is set
        if (transactionCategoryLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'transactionCategoryLocalizationDTO' when calling updateTransactionCategoryLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("localizationId", localizationId);

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

        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/transaction-category-localizations/{localizationId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Transaction Category Localization
     * Update an existing transaction category localization.
     * <p><b>200</b> - Transaction category localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to update
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TransactionCategoryLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TransactionCategoryLocalizationDTO> updateTransactionCategoryLocalization(Long localizationId, TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return updateTransactionCategoryLocalizationRequestCreation(localizationId, transactionCategoryLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Transaction Category Localization
     * Update an existing transaction category localization.
     * <p><b>200</b> - Transaction category localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to update
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;TransactionCategoryLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TransactionCategoryLocalizationDTO>> updateTransactionCategoryLocalizationWithHttpInfo(Long localizationId, TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TransactionCategoryLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<TransactionCategoryLocalizationDTO>() {};
        return updateTransactionCategoryLocalizationRequestCreation(localizationId, transactionCategoryLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Transaction Category Localization
     * Update an existing transaction category localization.
     * <p><b>200</b> - Transaction category localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Transaction category localization not found
     * @param localizationId ID of the transaction category localization to update
     * @param transactionCategoryLocalizationDTO The transactionCategoryLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateTransactionCategoryLocalizationWithResponseSpec(Long localizationId, TransactionCategoryLocalizationDTO transactionCategoryLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateTransactionCategoryLocalizationRequestCreation(localizationId, transactionCategoryLocalizationDTO, xIdempotencyKey);
    }
}

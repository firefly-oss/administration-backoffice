package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.CurrencyDTO;
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
public class CurrenciesApi {
    private ApiClient apiClient;

    public CurrenciesApi() {
        this(new ApiClient());
    }

    @Autowired
    public CurrenciesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Currency
     * Create a new currency.
     * <p><b>200</b> - Currency created successfully
     * <p><b>400</b> - Invalid input data
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CurrencyDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createCurrencyRequestCreation(CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = currencyDTO;
        // verify the required parameter 'currencyDTO' is set
        if (currencyDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'currencyDTO' when calling createCurrency", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return apiClient.invokeAPI("/api/v1/currencies", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Currency
     * Create a new currency.
     * <p><b>200</b> - Currency created successfully
     * <p><b>400</b> - Invalid input data
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CurrencyDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CurrencyDTO> createCurrency(CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return createCurrencyRequestCreation(currencyDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Currency
     * Create a new currency.
     * <p><b>200</b> - Currency created successfully
     * <p><b>400</b> - Invalid input data
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;CurrencyDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CurrencyDTO>> createCurrencyWithHttpInfo(CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return createCurrencyRequestCreation(currencyDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Currency
     * Create a new currency.
     * <p><b>200</b> - Currency created successfully
     * <p><b>400</b> - Invalid input data
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createCurrencyWithResponseSpec(CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createCurrencyRequestCreation(currencyDTO, xIdempotencyKey);
    }
    /**
     * Delete Currency
     * Delete a specific currency by its ID.
     * <p><b>204</b> - Currency deleted successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteCurrencyRequestCreation(Long currencyId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'currencyId' is set
        if (currencyId == null) {
            throw new WebClientResponseException("Missing the required parameter 'currencyId' when calling deleteCurrency", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("currencyId", currencyId);

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
        return apiClient.invokeAPI("/api/v1/currencies/{currencyId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Currency
     * Delete a specific currency by its ID.
     * <p><b>204</b> - Currency deleted successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteCurrency(Long currencyId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteCurrencyRequestCreation(currencyId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Currency
     * Delete a specific currency by its ID.
     * <p><b>204</b> - Currency deleted successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteCurrencyWithHttpInfo(Long currencyId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteCurrencyRequestCreation(currencyId).toEntity(localVarReturnType);
    }

    /**
     * Delete Currency
     * Delete a specific currency by its ID.
     * <p><b>204</b> - Currency deleted successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteCurrencyWithResponseSpec(Long currencyId) throws WebClientResponseException {
        return deleteCurrencyRequestCreation(currencyId);
    }
    /**
     * Get Currency by ID
     * Retrieve a specific currency by its ID.
     * <p><b>200</b> - Currency retrieved successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @return CurrencyDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getCurrencyRequestCreation(Long currencyId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'currencyId' is set
        if (currencyId == null) {
            throw new WebClientResponseException("Missing the required parameter 'currencyId' when calling getCurrency", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("currencyId", currencyId);

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

        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return apiClient.invokeAPI("/api/v1/currencies/{currencyId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Currency by ID
     * Retrieve a specific currency by its ID.
     * <p><b>200</b> - Currency retrieved successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @return CurrencyDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CurrencyDTO> getCurrency(Long currencyId) throws WebClientResponseException {
        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return getCurrencyRequestCreation(currencyId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Currency by ID
     * Retrieve a specific currency by its ID.
     * <p><b>200</b> - Currency retrieved successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @return ResponseEntity&lt;CurrencyDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CurrencyDTO>> getCurrencyWithHttpInfo(Long currencyId) throws WebClientResponseException {
        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return getCurrencyRequestCreation(currencyId).toEntity(localVarReturnType);
    }

    /**
     * Get Currency by ID
     * Retrieve a specific currency by its ID.
     * <p><b>200</b> - Currency retrieved successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getCurrencyWithResponseSpec(Long currencyId) throws WebClientResponseException {
        return getCurrencyRequestCreation(currencyId);
    }
    /**
     * List Currencies
     * Retrieve a paginated list of currencies.
     * <p><b>200</b> - Successfully retrieved list of currencies
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listCurrenciesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/currencies", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Currencies
     * Retrieve a paginated list of currencies.
     * <p><b>200</b> - Successfully retrieved list of currencies
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listCurrencies(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listCurrenciesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Currencies
     * Retrieve a paginated list of currencies.
     * <p><b>200</b> - Successfully retrieved list of currencies
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listCurrenciesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listCurrenciesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Currencies
     * Retrieve a paginated list of currencies.
     * <p><b>200</b> - Successfully retrieved list of currencies
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listCurrenciesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listCurrenciesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Currency
     * Update an existing currency by its ID.
     * <p><b>200</b> - Currency updated successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CurrencyDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateCurrencyRequestCreation(Long currencyId, CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = currencyDTO;
        // verify the required parameter 'currencyId' is set
        if (currencyId == null) {
            throw new WebClientResponseException("Missing the required parameter 'currencyId' when calling updateCurrency", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'currencyDTO' is set
        if (currencyDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'currencyDTO' when calling updateCurrency", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("currencyId", currencyId);

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

        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return apiClient.invokeAPI("/api/v1/currencies/{currencyId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Currency
     * Update an existing currency by its ID.
     * <p><b>200</b> - Currency updated successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CurrencyDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CurrencyDTO> updateCurrency(Long currencyId, CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return updateCurrencyRequestCreation(currencyId, currencyDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Currency
     * Update an existing currency by its ID.
     * <p><b>200</b> - Currency updated successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;CurrencyDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CurrencyDTO>> updateCurrencyWithHttpInfo(Long currencyId, CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CurrencyDTO> localVarReturnType = new ParameterizedTypeReference<CurrencyDTO>() {};
        return updateCurrencyRequestCreation(currencyId, currencyDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Currency
     * Update an existing currency by its ID.
     * <p><b>200</b> - Currency updated successfully
     * <p><b>404</b> - Currency not found
     * @param currencyId ID of the currency
     * @param currencyDTO The currencyDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateCurrencyWithResponseSpec(Long currencyId, CurrencyDTO currencyDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateCurrencyRequestCreation(currencyId, currencyDTO, xIdempotencyKey);
    }
}

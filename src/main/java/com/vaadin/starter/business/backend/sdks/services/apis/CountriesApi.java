package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.CountryDTO;
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
public class CountriesApi {
    private ApiClient apiClient;

    public CountriesApi() {
        this(new ApiClient());
    }

    @Autowired
    public CountriesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Country
     * Create a new country.
     * <p><b>200</b> - Country created successfully
     * <p><b>400</b> - Invalid input data
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CountryDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createCountryRequestCreation(CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = countryDTO;
        // verify the required parameter 'countryDTO' is set
        if (countryDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryDTO' when calling createCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return apiClient.invokeAPI("/api/v1/countries", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Country
     * Create a new country.
     * <p><b>200</b> - Country created successfully
     * <p><b>400</b> - Invalid input data
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CountryDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CountryDTO> createCountry(CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return createCountryRequestCreation(countryDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Country
     * Create a new country.
     * <p><b>200</b> - Country created successfully
     * <p><b>400</b> - Invalid input data
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;CountryDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CountryDTO>> createCountryWithHttpInfo(CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return createCountryRequestCreation(countryDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Country
     * Create a new country.
     * <p><b>200</b> - Country created successfully
     * <p><b>400</b> - Invalid input data
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createCountryWithResponseSpec(CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createCountryRequestCreation(countryDTO, xIdempotencyKey);
    }
    /**
     * Delete Country
     * Delete a specific country by its ID.
     * <p><b>204</b> - Country deleted successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteCountryRequestCreation(Long countryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'countryId' is set
        if (countryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryId' when calling deleteCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("countryId", countryId);

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
        return apiClient.invokeAPI("/api/v1/countries/{countryId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Country
     * Delete a specific country by its ID.
     * <p><b>204</b> - Country deleted successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteCountry(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteCountryRequestCreation(countryId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Country
     * Delete a specific country by its ID.
     * <p><b>204</b> - Country deleted successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteCountryWithHttpInfo(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteCountryRequestCreation(countryId).toEntity(localVarReturnType);
    }

    /**
     * Delete Country
     * Delete a specific country by its ID.
     * <p><b>204</b> - Country deleted successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteCountryWithResponseSpec(Long countryId) throws WebClientResponseException {
        return deleteCountryRequestCreation(countryId);
    }
    /**
     * Get Country by ID
     * Retrieve a specific country by its ID.
     * <p><b>200</b> - Country retrieved successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @return CountryDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getCountryRequestCreation(Long countryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'countryId' is set
        if (countryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryId' when calling getCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("countryId", countryId);

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

        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return apiClient.invokeAPI("/api/v1/countries/{countryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Country by ID
     * Retrieve a specific country by its ID.
     * <p><b>200</b> - Country retrieved successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @return CountryDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CountryDTO> getCountry(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return getCountryRequestCreation(countryId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Country by ID
     * Retrieve a specific country by its ID.
     * <p><b>200</b> - Country retrieved successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @return ResponseEntity&lt;CountryDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CountryDTO>> getCountryWithHttpInfo(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return getCountryRequestCreation(countryId).toEntity(localVarReturnType);
    }

    /**
     * Get Country by ID
     * Retrieve a specific country by its ID.
     * <p><b>200</b> - Country retrieved successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getCountryWithResponseSpec(Long countryId) throws WebClientResponseException {
        return getCountryRequestCreation(countryId);
    }
    /**
     * List Countries
     * Retrieve a paginated list of countries.
     * <p><b>200</b> - Successfully retrieved list of countries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listCountriesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/countries", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Countries
     * Retrieve a paginated list of countries.
     * <p><b>200</b> - Successfully retrieved list of countries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listCountries(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listCountriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Countries
     * Retrieve a paginated list of countries.
     * <p><b>200</b> - Successfully retrieved list of countries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listCountriesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listCountriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Countries
     * Retrieve a paginated list of countries.
     * <p><b>200</b> - Successfully retrieved list of countries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listCountriesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listCountriesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Country
     * Update an existing country by its ID.
     * <p><b>200</b> - Country updated successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CountryDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateCountryRequestCreation(Long countryId, CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = countryDTO;
        // verify the required parameter 'countryId' is set
        if (countryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryId' when calling updateCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'countryDTO' is set
        if (countryDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryDTO' when calling updateCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("countryId", countryId);

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

        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return apiClient.invokeAPI("/api/v1/countries/{countryId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Country
     * Update an existing country by its ID.
     * <p><b>200</b> - Country updated successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return CountryDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<CountryDTO> updateCountry(Long countryId, CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return updateCountryRequestCreation(countryId, countryDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Country
     * Update an existing country by its ID.
     * <p><b>200</b> - Country updated successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;CountryDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<CountryDTO>> updateCountryWithHttpInfo(Long countryId, CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<CountryDTO> localVarReturnType = new ParameterizedTypeReference<CountryDTO>() {};
        return updateCountryRequestCreation(countryId, countryDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Country
     * Update an existing country by its ID.
     * <p><b>200</b> - Country updated successfully
     * <p><b>404</b> - Country not found
     * @param countryId ID of the country
     * @param countryDTO The countryDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateCountryWithResponseSpec(Long countryId, CountryDTO countryDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateCountryRequestCreation(countryId, countryDTO, xIdempotencyKey);
    }
}

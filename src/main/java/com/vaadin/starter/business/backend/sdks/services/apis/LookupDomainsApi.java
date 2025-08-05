package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.LookupDomainDTO;
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
public class LookupDomainsApi {
    private ApiClient apiClient;

    public LookupDomainsApi() {
        this(new ApiClient());
    }

    @Autowired
    public LookupDomainsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Lookup Domain
     * Create a new lookup domain.
     * <p><b>200</b> - Lookup domain created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupDomainDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createDomainRequestCreation(LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = lookupDomainDTO;
        // verify the required parameter 'lookupDomainDTO' is set
        if (lookupDomainDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'lookupDomainDTO' when calling createDomain", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return apiClient.invokeAPI("/api/v1/lookup/domains", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Lookup Domain
     * Create a new lookup domain.
     * <p><b>200</b> - Lookup domain created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupDomainDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LookupDomainDTO> createDomain(LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return createDomainRequestCreation(lookupDomainDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Lookup Domain
     * Create a new lookup domain.
     * <p><b>200</b> - Lookup domain created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LookupDomainDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LookupDomainDTO>> createDomainWithHttpInfo(LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return createDomainRequestCreation(lookupDomainDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Lookup Domain
     * Create a new lookup domain.
     * <p><b>200</b> - Lookup domain created successfully
     * <p><b>400</b> - Invalid input data
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createDomainWithResponseSpec(LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createDomainRequestCreation(lookupDomainDTO, xIdempotencyKey);
    }
    /**
     * Delete Lookup Domain
     * Delete a specific lookup domain by its ID.
     * <p><b>204</b> - Lookup domain deleted successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteDomainRequestCreation(Long domainId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'domainId' is set
        if (domainId == null) {
            throw new WebClientResponseException("Missing the required parameter 'domainId' when calling deleteDomain", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("domainId", domainId);

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
        return apiClient.invokeAPI("/api/v1/lookup/domains/{domainId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Lookup Domain
     * Delete a specific lookup domain by its ID.
     * <p><b>204</b> - Lookup domain deleted successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteDomain(Long domainId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDomainRequestCreation(domainId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Lookup Domain
     * Delete a specific lookup domain by its ID.
     * <p><b>204</b> - Lookup domain deleted successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteDomainWithHttpInfo(Long domainId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDomainRequestCreation(domainId).toEntity(localVarReturnType);
    }

    /**
     * Delete Lookup Domain
     * Delete a specific lookup domain by its ID.
     * <p><b>204</b> - Lookup domain deleted successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteDomainWithResponseSpec(Long domainId) throws WebClientResponseException {
        return deleteDomainRequestCreation(domainId);
    }
    /**
     * Get Lookup Domain by ID
     * Retrieve a specific lookup domain by its ID.
     * <p><b>200</b> - Lookup domain retrieved successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @return LookupDomainDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDomainRequestCreation(Long domainId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'domainId' is set
        if (domainId == null) {
            throw new WebClientResponseException("Missing the required parameter 'domainId' when calling getDomain", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return apiClient.invokeAPI("/api/v1/lookup/domains/{domainId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Lookup Domain by ID
     * Retrieve a specific lookup domain by its ID.
     * <p><b>200</b> - Lookup domain retrieved successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @return LookupDomainDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LookupDomainDTO> getDomain(Long domainId) throws WebClientResponseException {
        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return getDomainRequestCreation(domainId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Lookup Domain by ID
     * Retrieve a specific lookup domain by its ID.
     * <p><b>200</b> - Lookup domain retrieved successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @return ResponseEntity&lt;LookupDomainDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LookupDomainDTO>> getDomainWithHttpInfo(Long domainId) throws WebClientResponseException {
        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return getDomainRequestCreation(domainId).toEntity(localVarReturnType);
    }

    /**
     * Get Lookup Domain by ID
     * Retrieve a specific lookup domain by its ID.
     * <p><b>200</b> - Lookup domain retrieved successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDomainWithResponseSpec(Long domainId) throws WebClientResponseException {
        return getDomainRequestCreation(domainId);
    }
    /**
     * List Lookup Domains
     * Retrieve a paginated list of lookup domains.
     * <p><b>200</b> - Successfully retrieved list of lookup domains
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listDomainsRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/lookup/domains", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Lookup Domains
     * Retrieve a paginated list of lookup domains.
     * <p><b>200</b> - Successfully retrieved list of lookup domains
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listDomains(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDomainsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Lookup Domains
     * Retrieve a paginated list of lookup domains.
     * <p><b>200</b> - Successfully retrieved list of lookup domains
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listDomainsWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDomainsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Lookup Domains
     * Retrieve a paginated list of lookup domains.
     * <p><b>200</b> - Successfully retrieved list of lookup domains
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listDomainsWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listDomainsRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Lookup Domain
     * Update an existing lookup domain by its ID.
     * <p><b>200</b> - Lookup domain updated successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupDomainDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateDomainRequestCreation(Long domainId, LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = lookupDomainDTO;
        // verify the required parameter 'domainId' is set
        if (domainId == null) {
            throw new WebClientResponseException("Missing the required parameter 'domainId' when calling updateDomain", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'lookupDomainDTO' is set
        if (lookupDomainDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'lookupDomainDTO' when calling updateDomain", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("domainId", domainId);

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

        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return apiClient.invokeAPI("/api/v1/lookup/domains/{domainId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Lookup Domain
     * Update an existing lookup domain by its ID.
     * <p><b>200</b> - Lookup domain updated successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LookupDomainDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LookupDomainDTO> updateDomain(Long domainId, LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return updateDomainRequestCreation(domainId, lookupDomainDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Lookup Domain
     * Update an existing lookup domain by its ID.
     * <p><b>200</b> - Lookup domain updated successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LookupDomainDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LookupDomainDTO>> updateDomainWithHttpInfo(Long domainId, LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LookupDomainDTO> localVarReturnType = new ParameterizedTypeReference<LookupDomainDTO>() {};
        return updateDomainRequestCreation(domainId, lookupDomainDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Lookup Domain
     * Update an existing lookup domain by its ID.
     * <p><b>200</b> - Lookup domain updated successfully
     * <p><b>404</b> - Lookup domain not found
     * @param domainId ID of the lookup domain
     * @param lookupDomainDTO The lookupDomainDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateDomainWithResponseSpec(Long domainId, LookupDomainDTO lookupDomainDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateDomainRequestCreation(domainId, lookupDomainDTO, xIdempotencyKey);
    }
}

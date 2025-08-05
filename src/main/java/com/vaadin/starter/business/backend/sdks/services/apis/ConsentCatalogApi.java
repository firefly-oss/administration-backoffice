package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.ConsentCatalogDTO;
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
public class ConsentCatalogApi {
    private ApiClient apiClient;

    public ConsentCatalogApi() {
        this(new ApiClient());
    }

    @Autowired
    public ConsentCatalogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Consent Catalog Entry
     * Create a new consent catalog entry.
     * <p><b>200</b> - Consent catalog entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ConsentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createConsentCatalogRequestCreation(ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = consentCatalogDTO;
        // verify the required parameter 'consentCatalogDTO' is set
        if (consentCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'consentCatalogDTO' when calling createConsentCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/consent-catalog", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Consent Catalog Entry
     * Create a new consent catalog entry.
     * <p><b>200</b> - Consent catalog entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ConsentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ConsentCatalogDTO> createConsentCatalog(ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return createConsentCatalogRequestCreation(consentCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Consent Catalog Entry
     * Create a new consent catalog entry.
     * <p><b>200</b> - Consent catalog entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;ConsentCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ConsentCatalogDTO>> createConsentCatalogWithHttpInfo(ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return createConsentCatalogRequestCreation(consentCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Consent Catalog Entry
     * Create a new consent catalog entry.
     * <p><b>200</b> - Consent catalog entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createConsentCatalogWithResponseSpec(ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createConsentCatalogRequestCreation(consentCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Consent Catalog Entry
     * Delete a specific consent catalog entry by its ID.
     * <p><b>204</b> - Consent catalog entry deleted successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteConsentCatalogRequestCreation(Long id) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling deleteConsentCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

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
        return apiClient.invokeAPI("/api/v1/consent-catalog/{id}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Consent Catalog Entry
     * Delete a specific consent catalog entry by its ID.
     * <p><b>204</b> - Consent catalog entry deleted successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteConsentCatalog(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteConsentCatalogRequestCreation(id).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Consent Catalog Entry
     * Delete a specific consent catalog entry by its ID.
     * <p><b>204</b> - Consent catalog entry deleted successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteConsentCatalogWithHttpInfo(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteConsentCatalogRequestCreation(id).toEntity(localVarReturnType);
    }

    /**
     * Delete Consent Catalog Entry
     * Delete a specific consent catalog entry by its ID.
     * <p><b>204</b> - Consent catalog entry deleted successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteConsentCatalogWithResponseSpec(Long id) throws WebClientResponseException {
        return deleteConsentCatalogRequestCreation(id);
    }
    /**
     * Get Consent Catalog Entry by ID
     * Retrieve a specific consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry retrieved successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @return ConsentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getConsentCatalogRequestCreation(Long id) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling getConsentCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

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

        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/consent-catalog/{id}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Consent Catalog Entry by ID
     * Retrieve a specific consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry retrieved successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @return ConsentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ConsentCatalogDTO> getConsentCatalog(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return getConsentCatalogRequestCreation(id).bodyToMono(localVarReturnType);
    }

    /**
     * Get Consent Catalog Entry by ID
     * Retrieve a specific consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry retrieved successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @return ResponseEntity&lt;ConsentCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ConsentCatalogDTO>> getConsentCatalogWithHttpInfo(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return getConsentCatalogRequestCreation(id).toEntity(localVarReturnType);
    }

    /**
     * Get Consent Catalog Entry by ID
     * Retrieve a specific consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry retrieved successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getConsentCatalogWithResponseSpec(Long id) throws WebClientResponseException {
        return getConsentCatalogRequestCreation(id);
    }
    /**
     * List Consent Catalog
     * Retrieve a paginated list of consent catalog entries.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listConsentCatalogRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/consent-catalog", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Consent Catalog
     * Retrieve a paginated list of consent catalog entries.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listConsentCatalog(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listConsentCatalogRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Consent Catalog
     * Retrieve a paginated list of consent catalog entries.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listConsentCatalogWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listConsentCatalogRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Consent Catalog
     * Retrieve a paginated list of consent catalog entries.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listConsentCatalogWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listConsentCatalogRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Consent Catalog by Type
     * Retrieve a paginated list of consent catalog entries of a specific type.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries of the specified type
     * @param consentType Type of consent
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listConsentCatalogByTypeRequestCreation(String consentType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'consentType' is set
        if (consentType == null) {
            throw new WebClientResponseException("Missing the required parameter 'consentType' when calling listConsentCatalogByType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("consentType", consentType);

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
        return apiClient.invokeAPI("/api/v1/consent-catalog/type/{consentType}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Consent Catalog by Type
     * Retrieve a paginated list of consent catalog entries of a specific type.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries of the specified type
     * @param consentType Type of consent
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listConsentCatalogByType(String consentType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listConsentCatalogByTypeRequestCreation(consentType, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Consent Catalog by Type
     * Retrieve a paginated list of consent catalog entries of a specific type.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries of the specified type
     * @param consentType Type of consent
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listConsentCatalogByTypeWithHttpInfo(String consentType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listConsentCatalogByTypeRequestCreation(consentType, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Consent Catalog by Type
     * Retrieve a paginated list of consent catalog entries of a specific type.
     * <p><b>200</b> - Successfully retrieved list of consent catalog entries of the specified type
     * @param consentType Type of consent
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listConsentCatalogByTypeWithResponseSpec(String consentType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listConsentCatalogByTypeRequestCreation(consentType, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Consent Catalog Entry
     * Update an existing consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry updated successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ConsentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateConsentCatalogRequestCreation(Long id, ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = consentCatalogDTO;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling updateConsentCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'consentCatalogDTO' is set
        if (consentCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'consentCatalogDTO' when calling updateConsentCatalog", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

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

        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/consent-catalog/{id}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Consent Catalog Entry
     * Update an existing consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry updated successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ConsentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ConsentCatalogDTO> updateConsentCatalog(Long id, ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return updateConsentCatalogRequestCreation(id, consentCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Consent Catalog Entry
     * Update an existing consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry updated successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;ConsentCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ConsentCatalogDTO>> updateConsentCatalogWithHttpInfo(Long id, ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ConsentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<ConsentCatalogDTO>() {};
        return updateConsentCatalogRequestCreation(id, consentCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Consent Catalog Entry
     * Update an existing consent catalog entry by its ID.
     * <p><b>200</b> - Consent catalog entry updated successfully
     * <p><b>404</b> - Consent catalog entry not found
     * @param id ID of the consent catalog entry
     * @param consentCatalogDTO The consentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateConsentCatalogWithResponseSpec(Long id, ConsentCatalogDTO consentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateConsentCatalogRequestCreation(id, consentCatalogDTO, xIdempotencyKey);
    }
}

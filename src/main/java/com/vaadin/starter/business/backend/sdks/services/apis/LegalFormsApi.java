package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.LegalFormDTO;
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
public class LegalFormsApi {
    private ApiClient apiClient;

    public LegalFormsApi() {
        this(new ApiClient());
    }

    @Autowired
    public LegalFormsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Legal Form
     * Create a new legal form.
     * <p><b>200</b> - Legal form created successfully
     * <p><b>400</b> - Invalid input data
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createLegalFormRequestCreation(LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = legalFormDTO;
        // verify the required parameter 'legalFormDTO' is set
        if (legalFormDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'legalFormDTO' when calling createLegalForm", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return apiClient.invokeAPI("/api/v1/legal-forms", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Legal Form
     * Create a new legal form.
     * <p><b>200</b> - Legal form created successfully
     * <p><b>400</b> - Invalid input data
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LegalFormDTO> createLegalForm(LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return createLegalFormRequestCreation(legalFormDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Legal Form
     * Create a new legal form.
     * <p><b>200</b> - Legal form created successfully
     * <p><b>400</b> - Invalid input data
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LegalFormDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LegalFormDTO>> createLegalFormWithHttpInfo(LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return createLegalFormRequestCreation(legalFormDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Legal Form
     * Create a new legal form.
     * <p><b>200</b> - Legal form created successfully
     * <p><b>400</b> - Invalid input data
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createLegalFormWithResponseSpec(LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createLegalFormRequestCreation(legalFormDTO, xIdempotencyKey);
    }
    /**
     * Delete Legal Form
     * Delete a specific legal form by its ID.
     * <p><b>204</b> - Legal form deleted successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteLegalFormRequestCreation(Long legalFormId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'legalFormId' is set
        if (legalFormId == null) {
            throw new WebClientResponseException("Missing the required parameter 'legalFormId' when calling deleteLegalForm", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("legalFormId", legalFormId);

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
        return apiClient.invokeAPI("/api/v1/legal-forms/{legalFormId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Legal Form
     * Delete a specific legal form by its ID.
     * <p><b>204</b> - Legal form deleted successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteLegalForm(Long legalFormId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLegalFormRequestCreation(legalFormId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Legal Form
     * Delete a specific legal form by its ID.
     * <p><b>204</b> - Legal form deleted successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteLegalFormWithHttpInfo(Long legalFormId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLegalFormRequestCreation(legalFormId).toEntity(localVarReturnType);
    }

    /**
     * Delete Legal Form
     * Delete a specific legal form by its ID.
     * <p><b>204</b> - Legal form deleted successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteLegalFormWithResponseSpec(Long legalFormId) throws WebClientResponseException {
        return deleteLegalFormRequestCreation(legalFormId);
    }
    /**
     * Get Legal Form by ID
     * Retrieve a specific legal form by its ID.
     * <p><b>200</b> - Legal form retrieved successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLegalFormRequestCreation(Long legalFormId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'legalFormId' is set
        if (legalFormId == null) {
            throw new WebClientResponseException("Missing the required parameter 'legalFormId' when calling getLegalForm", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("legalFormId", legalFormId);

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

        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return apiClient.invokeAPI("/api/v1/legal-forms/{legalFormId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Legal Form by ID
     * Retrieve a specific legal form by its ID.
     * <p><b>200</b> - Legal form retrieved successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LegalFormDTO> getLegalForm(Long legalFormId) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return getLegalFormRequestCreation(legalFormId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Legal Form by ID
     * Retrieve a specific legal form by its ID.
     * <p><b>200</b> - Legal form retrieved successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @return ResponseEntity&lt;LegalFormDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LegalFormDTO>> getLegalFormWithHttpInfo(Long legalFormId) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return getLegalFormRequestCreation(legalFormId).toEntity(localVarReturnType);
    }

    /**
     * Get Legal Form by ID
     * Retrieve a specific legal form by its ID.
     * <p><b>200</b> - Legal form retrieved successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLegalFormWithResponseSpec(Long legalFormId) throws WebClientResponseException {
        return getLegalFormRequestCreation(legalFormId);
    }
    /**
     * Get Legal Forms by Country
     * Retrieve all legal forms for a specific country.
     * <p><b>200</b> - Successfully retrieved legal forms for the country
     * @param countryId ID of the country
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLegalFormsByCountryRequestCreation(Long countryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'countryId' is set
        if (countryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryId' when calling getLegalFormsByCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return apiClient.invokeAPI("/api/v1/legal-forms/country/{countryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Legal Forms by Country
     * Retrieve all legal forms for a specific country.
     * <p><b>200</b> - Successfully retrieved legal forms for the country
     * @param countryId ID of the country
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LegalFormDTO> getLegalFormsByCountry(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return getLegalFormsByCountryRequestCreation(countryId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Legal Forms by Country
     * Retrieve all legal forms for a specific country.
     * <p><b>200</b> - Successfully retrieved legal forms for the country
     * @param countryId ID of the country
     * @return ResponseEntity&lt;LegalFormDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LegalFormDTO>> getLegalFormsByCountryWithHttpInfo(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return getLegalFormsByCountryRequestCreation(countryId).toEntity(localVarReturnType);
    }

    /**
     * Get Legal Forms by Country
     * Retrieve all legal forms for a specific country.
     * <p><b>200</b> - Successfully retrieved legal forms for the country
     * @param countryId ID of the country
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLegalFormsByCountryWithResponseSpec(Long countryId) throws WebClientResponseException {
        return getLegalFormsByCountryRequestCreation(countryId);
    }
    /**
     * List Legal Forms
     * Retrieve a paginated list of legal forms.
     * <p><b>200</b> - Successfully retrieved list of legal forms
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listLegalFormsRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/legal-forms", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Legal Forms
     * Retrieve a paginated list of legal forms.
     * <p><b>200</b> - Successfully retrieved list of legal forms
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listLegalForms(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listLegalFormsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Legal Forms
     * Retrieve a paginated list of legal forms.
     * <p><b>200</b> - Successfully retrieved list of legal forms
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listLegalFormsWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listLegalFormsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Legal Forms
     * Retrieve a paginated list of legal forms.
     * <p><b>200</b> - Successfully retrieved list of legal forms
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listLegalFormsWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listLegalFormsRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Legal Form
     * Update an existing legal form by its ID.
     * <p><b>200</b> - Legal form updated successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateLegalFormRequestCreation(Long legalFormId, LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = legalFormDTO;
        // verify the required parameter 'legalFormId' is set
        if (legalFormId == null) {
            throw new WebClientResponseException("Missing the required parameter 'legalFormId' when calling updateLegalForm", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'legalFormDTO' is set
        if (legalFormDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'legalFormDTO' when calling updateLegalForm", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("legalFormId", legalFormId);

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

        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return apiClient.invokeAPI("/api/v1/legal-forms/{legalFormId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Legal Form
     * Update an existing legal form by its ID.
     * <p><b>200</b> - Legal form updated successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LegalFormDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LegalFormDTO> updateLegalForm(Long legalFormId, LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return updateLegalFormRequestCreation(legalFormId, legalFormDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Legal Form
     * Update an existing legal form by its ID.
     * <p><b>200</b> - Legal form updated successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LegalFormDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LegalFormDTO>> updateLegalFormWithHttpInfo(Long legalFormId, LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LegalFormDTO> localVarReturnType = new ParameterizedTypeReference<LegalFormDTO>() {};
        return updateLegalFormRequestCreation(legalFormId, legalFormDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Legal Form
     * Update an existing legal form by its ID.
     * <p><b>200</b> - Legal form updated successfully
     * <p><b>404</b> - Legal form not found
     * @param legalFormId ID of the legal form
     * @param legalFormDTO The legalFormDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateLegalFormWithResponseSpec(Long legalFormId, LegalFormDTO legalFormDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateLegalFormRequestCreation(legalFormId, legalFormDTO, xIdempotencyKey);
    }
}

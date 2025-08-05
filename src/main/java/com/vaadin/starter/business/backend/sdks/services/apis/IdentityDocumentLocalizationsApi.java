package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.IdentityDocumentLocalizationDTO;
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
public class IdentityDocumentLocalizationsApi {
    private ApiClient apiClient;

    public IdentityDocumentLocalizationsApi() {
        this(new ApiClient());
    }

    @Autowired
    public IdentityDocumentLocalizationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Identity Document Localization
     * Create a new identity document localization.
     * <p><b>200</b> - Identity document localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createIdentityDocumentLocalizationRequestCreation(IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = identityDocumentLocalizationDTO;
        // verify the required parameter 'identityDocumentLocalizationDTO' is set
        if (identityDocumentLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'identityDocumentLocalizationDTO' when calling createIdentityDocumentLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-document-localizations", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Identity Document Localization
     * Create a new identity document localization.
     * <p><b>200</b> - Identity document localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentLocalizationDTO> createIdentityDocumentLocalization(IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return createIdentityDocumentLocalizationRequestCreation(identityDocumentLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Identity Document Localization
     * Create a new identity document localization.
     * <p><b>200</b> - Identity document localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;IdentityDocumentLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentLocalizationDTO>> createIdentityDocumentLocalizationWithHttpInfo(IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return createIdentityDocumentLocalizationRequestCreation(identityDocumentLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Identity Document Localization
     * Create a new identity document localization.
     * <p><b>200</b> - Identity document localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createIdentityDocumentLocalizationWithResponseSpec(IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createIdentityDocumentLocalizationRequestCreation(identityDocumentLocalizationDTO, xIdempotencyKey);
    }
    /**
     * Delete Identity Document Localization
     * Delete a specific identity document localization by its ID.
     * <p><b>204</b> - Identity document localization deleted successfully
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteIdentityDocumentLocalizationRequestCreation(Long localizationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling deleteIdentityDocumentLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/identity-document-localizations/{localizationId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Identity Document Localization
     * Delete a specific identity document localization by its ID.
     * <p><b>204</b> - Identity document localization deleted successfully
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteIdentityDocumentLocalization(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteIdentityDocumentLocalizationRequestCreation(localizationId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Identity Document Localization
     * Delete a specific identity document localization by its ID.
     * <p><b>204</b> - Identity document localization deleted successfully
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteIdentityDocumentLocalizationWithHttpInfo(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteIdentityDocumentLocalizationRequestCreation(localizationId).toEntity(localVarReturnType);
    }

    /**
     * Delete Identity Document Localization
     * Delete a specific identity document localization by its ID.
     * <p><b>204</b> - Identity document localization deleted successfully
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteIdentityDocumentLocalizationWithResponseSpec(Long localizationId) throws WebClientResponseException {
        return deleteIdentityDocumentLocalizationRequestCreation(localizationId);
    }
    /**
     * Delete Localizations by Document ID
     * Delete all localizations for a specific identity document.
     * <p><b>204</b> - Identity document localizations deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteLocalizationsByDocumentIdRequestCreation(Long documentId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'documentId' is set
        if (documentId == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentId' when calling deleteLocalizationsByDocumentId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("documentId", documentId);

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
        return apiClient.invokeAPI("/api/v1/identity-document-localizations/document/{documentId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Localizations by Document ID
     * Delete all localizations for a specific identity document.
     * <p><b>204</b> - Identity document localizations deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteLocalizationsByDocumentId(Long documentId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLocalizationsByDocumentIdRequestCreation(documentId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Localizations by Document ID
     * Delete all localizations for a specific identity document.
     * <p><b>204</b> - Identity document localizations deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteLocalizationsByDocumentIdWithHttpInfo(Long documentId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLocalizationsByDocumentIdRequestCreation(documentId).toEntity(localVarReturnType);
    }

    /**
     * Delete Localizations by Document ID
     * Delete all localizations for a specific identity document.
     * <p><b>204</b> - Identity document localizations deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteLocalizationsByDocumentIdWithResponseSpec(Long documentId) throws WebClientResponseException {
        return deleteLocalizationsByDocumentIdRequestCreation(documentId);
    }
    /**
     * Get Identity Document Localization by Document and Locale
     * Get a specific identity document localization by document ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the identity document localization
     * <p><b>404</b> - Identity document localization not found
     * @param documentId ID of the identity document
     * @param localeId ID of the language locale
     * @return IdentityDocumentLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getIdentityDocumentLocalizationByDocumentAndLocaleRequestCreation(Long documentId, Long localeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'documentId' is set
        if (documentId == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentId' when calling getIdentityDocumentLocalizationByDocumentAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'localeId' is set
        if (localeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localeId' when calling getIdentityDocumentLocalizationByDocumentAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("documentId", documentId);
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

        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-document-localizations/document/{documentId}/locale/{localeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Identity Document Localization by Document and Locale
     * Get a specific identity document localization by document ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the identity document localization
     * <p><b>404</b> - Identity document localization not found
     * @param documentId ID of the identity document
     * @param localeId ID of the language locale
     * @return IdentityDocumentLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentLocalizationDTO> getIdentityDocumentLocalizationByDocumentAndLocale(Long documentId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return getIdentityDocumentLocalizationByDocumentAndLocaleRequestCreation(documentId, localeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Identity Document Localization by Document and Locale
     * Get a specific identity document localization by document ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the identity document localization
     * <p><b>404</b> - Identity document localization not found
     * @param documentId ID of the identity document
     * @param localeId ID of the language locale
     * @return ResponseEntity&lt;IdentityDocumentLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentLocalizationDTO>> getIdentityDocumentLocalizationByDocumentAndLocaleWithHttpInfo(Long documentId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return getIdentityDocumentLocalizationByDocumentAndLocaleRequestCreation(documentId, localeId).toEntity(localVarReturnType);
    }

    /**
     * Get Identity Document Localization by Document and Locale
     * Get a specific identity document localization by document ID and locale ID.
     * <p><b>200</b> - Successfully retrieved the identity document localization
     * <p><b>404</b> - Identity document localization not found
     * @param documentId ID of the identity document
     * @param localeId ID of the language locale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getIdentityDocumentLocalizationByDocumentAndLocaleWithResponseSpec(Long documentId, Long localeId) throws WebClientResponseException {
        return getIdentityDocumentLocalizationByDocumentAndLocaleRequestCreation(documentId, localeId);
    }
    /**
     * List Localizations by Document ID
     * Retrieve a paginated list of localizations for a specific identity document.
     * <p><b>200</b> - Successfully retrieved list of localizations for the specified document
     * @param documentId ID of the identity document
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLocalizationsByDocumentIdRequestCreation(Long documentId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'documentId' is set
        if (documentId == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentId' when calling getLocalizationsByDocumentId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("documentId", documentId);

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
        return apiClient.invokeAPI("/api/v1/identity-document-localizations/document/{documentId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Localizations by Document ID
     * Retrieve a paginated list of localizations for a specific identity document.
     * <p><b>200</b> - Successfully retrieved list of localizations for the specified document
     * @param documentId ID of the identity document
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> getLocalizationsByDocumentId(Long documentId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return getLocalizationsByDocumentIdRequestCreation(documentId, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Localizations by Document ID
     * Retrieve a paginated list of localizations for a specific identity document.
     * <p><b>200</b> - Successfully retrieved list of localizations for the specified document
     * @param documentId ID of the identity document
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> getLocalizationsByDocumentIdWithHttpInfo(Long documentId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return getLocalizationsByDocumentIdRequestCreation(documentId, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Localizations by Document ID
     * Retrieve a paginated list of localizations for a specific identity document.
     * <p><b>200</b> - Successfully retrieved list of localizations for the specified document
     * @param documentId ID of the identity document
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLocalizationsByDocumentIdWithResponseSpec(Long documentId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return getLocalizationsByDocumentIdRequestCreation(documentId, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Identity Document Localizations
     * Retrieve a paginated list of identity document localizations.
     * <p><b>200</b> - Successfully retrieved list of identity document localizations
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listIdentityDocumentLocalizationsRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/identity-document-localizations", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Identity Document Localizations
     * Retrieve a paginated list of identity document localizations.
     * <p><b>200</b> - Successfully retrieved list of identity document localizations
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listIdentityDocumentLocalizations(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentLocalizationsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Identity Document Localizations
     * Retrieve a paginated list of identity document localizations.
     * <p><b>200</b> - Successfully retrieved list of identity document localizations
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listIdentityDocumentLocalizationsWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentLocalizationsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Identity Document Localizations
     * Retrieve a paginated list of identity document localizations.
     * <p><b>200</b> - Successfully retrieved list of identity document localizations
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listIdentityDocumentLocalizationsWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listIdentityDocumentLocalizationsRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Identity Document Localization
     * Update a specific identity document localization by its ID.
     * <p><b>200</b> - Identity document localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateIdentityDocumentLocalizationRequestCreation(Long localizationId, IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = identityDocumentLocalizationDTO;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling updateIdentityDocumentLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'identityDocumentLocalizationDTO' is set
        if (identityDocumentLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'identityDocumentLocalizationDTO' when calling updateIdentityDocumentLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-document-localizations/{localizationId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Identity Document Localization
     * Update a specific identity document localization by its ID.
     * <p><b>200</b> - Identity document localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentLocalizationDTO> updateIdentityDocumentLocalization(Long localizationId, IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return updateIdentityDocumentLocalizationRequestCreation(localizationId, identityDocumentLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Identity Document Localization
     * Update a specific identity document localization by its ID.
     * <p><b>200</b> - Identity document localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;IdentityDocumentLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentLocalizationDTO>> updateIdentityDocumentLocalizationWithHttpInfo(Long localizationId, IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentLocalizationDTO>() {};
        return updateIdentityDocumentLocalizationRequestCreation(localizationId, identityDocumentLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Identity Document Localization
     * Update a specific identity document localization by its ID.
     * <p><b>200</b> - Identity document localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document localization not found
     * @param localizationId ID of the identity document localization
     * @param identityDocumentLocalizationDTO The identityDocumentLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateIdentityDocumentLocalizationWithResponseSpec(Long localizationId, IdentityDocumentLocalizationDTO identityDocumentLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateIdentityDocumentLocalizationRequestCreation(localizationId, identityDocumentLocalizationDTO, xIdempotencyKey);
    }
}

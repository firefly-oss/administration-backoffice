package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.IdentityDocumentCatalogDTO;
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
public class IdentityDocumentsApi {
    private ApiClient apiClient;

    public IdentityDocumentsApi() {
        this(new ApiClient());
    }

    @Autowired
    public IdentityDocumentsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Identity Document
     * Create a new identity document.
     * <p><b>200</b> - Identity document created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createIdentityDocumentRequestCreation(IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = identityDocumentCatalogDTO;
        // verify the required parameter 'identityDocumentCatalogDTO' is set
        if (identityDocumentCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'identityDocumentCatalogDTO' when calling createIdentityDocument", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-documents", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Identity Document
     * Create a new identity document.
     * <p><b>200</b> - Identity document created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCatalogDTO> createIdentityDocument(IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return createIdentityDocumentRequestCreation(identityDocumentCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Identity Document
     * Create a new identity document.
     * <p><b>200</b> - Identity document created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;IdentityDocumentCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCatalogDTO>> createIdentityDocumentWithHttpInfo(IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return createIdentityDocumentRequestCreation(identityDocumentCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Identity Document
     * Create a new identity document.
     * <p><b>200</b> - Identity document created successfully
     * <p><b>400</b> - Invalid input data
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createIdentityDocumentWithResponseSpec(IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createIdentityDocumentRequestCreation(identityDocumentCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Identity Document
     * Delete a specific identity document by its ID.
     * <p><b>204</b> - Identity document deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteIdentityDocumentRequestCreation(Long documentId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'documentId' is set
        if (documentId == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentId' when calling deleteIdentityDocument", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/identity-documents/{documentId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Identity Document
     * Delete a specific identity document by its ID.
     * <p><b>204</b> - Identity document deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteIdentityDocument(Long documentId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteIdentityDocumentRequestCreation(documentId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Identity Document
     * Delete a specific identity document by its ID.
     * <p><b>204</b> - Identity document deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteIdentityDocumentWithHttpInfo(Long documentId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteIdentityDocumentRequestCreation(documentId).toEntity(localVarReturnType);
    }

    /**
     * Delete Identity Document
     * Delete a specific identity document by its ID.
     * <p><b>204</b> - Identity document deleted successfully
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteIdentityDocumentWithResponseSpec(Long documentId) throws WebClientResponseException {
        return deleteIdentityDocumentRequestCreation(documentId);
    }
    /**
     * Get Identity Document
     * Get a specific identity document by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getIdentityDocumentRequestCreation(Long documentId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'documentId' is set
        if (documentId == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentId' when calling getIdentityDocument", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("documentId", documentId);

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

        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-documents/{documentId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Identity Document
     * Get a specific identity document by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCatalogDTO> getIdentityDocument(Long documentId) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return getIdentityDocumentRequestCreation(documentId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Identity Document
     * Get a specific identity document by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @return ResponseEntity&lt;IdentityDocumentCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCatalogDTO>> getIdentityDocumentWithHttpInfo(Long documentId) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return getIdentityDocumentRequestCreation(documentId).toEntity(localVarReturnType);
    }

    /**
     * Get Identity Document
     * Get a specific identity document by its ID.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getIdentityDocumentWithResponseSpec(Long documentId) throws WebClientResponseException {
        return getIdentityDocumentRequestCreation(documentId);
    }
    /**
     * Get Identity Document by Code
     * Get a specific identity document by its code.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentCode Code of the identity document
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getIdentityDocumentByCodeRequestCreation(String documentCode) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'documentCode' is set
        if (documentCode == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentCode' when calling getIdentityDocumentByCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("documentCode", documentCode);

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

        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-documents/code/{documentCode}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Identity Document by Code
     * Get a specific identity document by its code.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentCode Code of the identity document
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCatalogDTO> getIdentityDocumentByCode(String documentCode) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return getIdentityDocumentByCodeRequestCreation(documentCode).bodyToMono(localVarReturnType);
    }

    /**
     * Get Identity Document by Code
     * Get a specific identity document by its code.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentCode Code of the identity document
     * @return ResponseEntity&lt;IdentityDocumentCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCatalogDTO>> getIdentityDocumentByCodeWithHttpInfo(String documentCode) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return getIdentityDocumentByCodeRequestCreation(documentCode).toEntity(localVarReturnType);
    }

    /**
     * Get Identity Document by Code
     * Get a specific identity document by its code.
     * <p><b>200</b> - Successfully retrieved the identity document
     * <p><b>404</b> - Identity document not found
     * @param documentCode Code of the identity document
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getIdentityDocumentByCodeWithResponseSpec(String documentCode) throws WebClientResponseException {
        return getIdentityDocumentByCodeRequestCreation(documentCode);
    }
    /**
     * List Identity Documents
     * Retrieve a paginated list of identity documents.
     * <p><b>200</b> - Successfully retrieved list of identity documents
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listIdentityDocumentsRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/identity-documents", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Identity Documents
     * Retrieve a paginated list of identity documents.
     * <p><b>200</b> - Successfully retrieved list of identity documents
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listIdentityDocuments(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Identity Documents
     * Retrieve a paginated list of identity documents.
     * <p><b>200</b> - Successfully retrieved list of identity documents
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listIdentityDocumentsWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Identity Documents
     * Retrieve a paginated list of identity documents.
     * <p><b>200</b> - Successfully retrieved list of identity documents
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listIdentityDocumentsWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listIdentityDocumentsRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Identity Documents by Category
     * Retrieve a paginated list of identity documents of a specific category.
     * <p><b>200</b> - Successfully retrieved list of identity documents of the specified category
     * @param categoryId ID of the identity document category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listIdentityDocumentsByCategoryRequestCreation(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'categoryId' is set
        if (categoryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'categoryId' when calling listIdentityDocumentsByCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/identity-documents/category/{categoryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Identity Documents by Category
     * Retrieve a paginated list of identity documents of a specific category.
     * <p><b>200</b> - Successfully retrieved list of identity documents of the specified category
     * @param categoryId ID of the identity document category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listIdentityDocumentsByCategory(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentsByCategoryRequestCreation(categoryId, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Identity Documents by Category
     * Retrieve a paginated list of identity documents of a specific category.
     * <p><b>200</b> - Successfully retrieved list of identity documents of the specified category
     * @param categoryId ID of the identity document category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listIdentityDocumentsByCategoryWithHttpInfo(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentsByCategoryRequestCreation(categoryId, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Identity Documents by Category
     * Retrieve a paginated list of identity documents of a specific category.
     * <p><b>200</b> - Successfully retrieved list of identity documents of the specified category
     * @param categoryId ID of the identity document category
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listIdentityDocumentsByCategoryWithResponseSpec(Long categoryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listIdentityDocumentsByCategoryRequestCreation(categoryId, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Identity Documents by Country
     * Retrieve a paginated list of identity documents for a specific country.
     * <p><b>200</b> - Successfully retrieved list of identity documents for the specified country
     * @param countryId ID of the country
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listIdentityDocumentsByCountryRequestCreation(Long countryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'countryId' is set
        if (countryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryId' when calling listIdentityDocumentsByCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("countryId", countryId);

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
        return apiClient.invokeAPI("/api/v1/identity-documents/country/{countryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Identity Documents by Country
     * Retrieve a paginated list of identity documents for a specific country.
     * <p><b>200</b> - Successfully retrieved list of identity documents for the specified country
     * @param countryId ID of the country
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listIdentityDocumentsByCountry(Long countryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentsByCountryRequestCreation(countryId, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Identity Documents by Country
     * Retrieve a paginated list of identity documents for a specific country.
     * <p><b>200</b> - Successfully retrieved list of identity documents for the specified country
     * @param countryId ID of the country
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listIdentityDocumentsByCountryWithHttpInfo(Long countryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listIdentityDocumentsByCountryRequestCreation(countryId, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Identity Documents by Country
     * Retrieve a paginated list of identity documents for a specific country.
     * <p><b>200</b> - Successfully retrieved list of identity documents for the specified country
     * @param countryId ID of the country
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listIdentityDocumentsByCountryWithResponseSpec(Long countryId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listIdentityDocumentsByCountryRequestCreation(countryId, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Identity Document
     * Update a specific identity document by its ID.
     * <p><b>200</b> - Identity document updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateIdentityDocumentRequestCreation(Long documentId, IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = identityDocumentCatalogDTO;
        // verify the required parameter 'documentId' is set
        if (documentId == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentId' when calling updateIdentityDocument", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'identityDocumentCatalogDTO' is set
        if (identityDocumentCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'identityDocumentCatalogDTO' when calling updateIdentityDocument", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("documentId", documentId);

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

        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/identity-documents/{documentId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Identity Document
     * Update a specific identity document by its ID.
     * <p><b>200</b> - Identity document updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return IdentityDocumentCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<IdentityDocumentCatalogDTO> updateIdentityDocument(Long documentId, IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return updateIdentityDocumentRequestCreation(documentId, identityDocumentCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Identity Document
     * Update a specific identity document by its ID.
     * <p><b>200</b> - Identity document updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;IdentityDocumentCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<IdentityDocumentCatalogDTO>> updateIdentityDocumentWithHttpInfo(Long documentId, IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<IdentityDocumentCatalogDTO> localVarReturnType = new ParameterizedTypeReference<IdentityDocumentCatalogDTO>() {};
        return updateIdentityDocumentRequestCreation(documentId, identityDocumentCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Identity Document
     * Update a specific identity document by its ID.
     * <p><b>200</b> - Identity document updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Identity document not found
     * @param documentId ID of the identity document
     * @param identityDocumentCatalogDTO The identityDocumentCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateIdentityDocumentWithResponseSpec(Long documentId, IdentityDocumentCatalogDTO identityDocumentCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateIdentityDocumentRequestCreation(documentId, identityDocumentCatalogDTO, xIdempotencyKey);
    }
}

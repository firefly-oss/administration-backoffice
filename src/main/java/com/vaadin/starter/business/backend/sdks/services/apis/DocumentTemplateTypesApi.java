package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.DocumentTemplateTypeCatalogDTO;
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
public class DocumentTemplateTypesApi {
    private ApiClient apiClient;

    public DocumentTemplateTypesApi() {
        this(new ApiClient());
    }

    @Autowired
    public DocumentTemplateTypesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Document Template Type
     * Create a new document template type.
     * <p><b>201</b> - Document template type created successfully
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createDocumentTemplateTypeRequestCreation(DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = documentTemplateTypeCatalogDTO;
        // verify the required parameter 'documentTemplateTypeCatalogDTO' is set
        if (documentTemplateTypeCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentTemplateTypeCatalogDTO' when calling createDocumentTemplateType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-types", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Document Template Type
     * Create a new document template type.
     * <p><b>201</b> - Document template type created successfully
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateTypeCatalogDTO> createDocumentTemplateType(DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return createDocumentTemplateTypeRequestCreation(documentTemplateTypeCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Document Template Type
     * Create a new document template type.
     * <p><b>201</b> - Document template type created successfully
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;DocumentTemplateTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateTypeCatalogDTO>> createDocumentTemplateTypeWithHttpInfo(DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return createDocumentTemplateTypeRequestCreation(documentTemplateTypeCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Document Template Type
     * Create a new document template type.
     * <p><b>201</b> - Document template type created successfully
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createDocumentTemplateTypeWithResponseSpec(DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createDocumentTemplateTypeRequestCreation(documentTemplateTypeCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Document Template Type
     * Delete a document template type by its ID.
     * <p><b>204</b> - Document template type deleted successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteDocumentTemplateTypeRequestCreation(Long typeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling deleteDocumentTemplateType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeId", typeId);

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
        return apiClient.invokeAPI("/api/v1/document-template-types/{typeId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Document Template Type
     * Delete a document template type by its ID.
     * <p><b>204</b> - Document template type deleted successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteDocumentTemplateType(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDocumentTemplateTypeRequestCreation(typeId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Document Template Type
     * Delete a document template type by its ID.
     * <p><b>204</b> - Document template type deleted successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteDocumentTemplateTypeWithHttpInfo(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDocumentTemplateTypeRequestCreation(typeId).toEntity(localVarReturnType);
    }

    /**
     * Delete Document Template Type
     * Delete a document template type by its ID.
     * <p><b>204</b> - Document template type deleted successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteDocumentTemplateTypeWithResponseSpec(Long typeId) throws WebClientResponseException {
        return deleteDocumentTemplateTypeRequestCreation(typeId);
    }
    /**
     * Get Document Template Type by ID
     * Retrieve a document template type by its ID.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDocumentTemplateTypeRequestCreation(Long typeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling getDocumentTemplateType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeId", typeId);

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

        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-types/{typeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Document Template Type by ID
     * Retrieve a document template type by its ID.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateTypeCatalogDTO> getDocumentTemplateType(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return getDocumentTemplateTypeRequestCreation(typeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Document Template Type by ID
     * Retrieve a document template type by its ID.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @return ResponseEntity&lt;DocumentTemplateTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateTypeCatalogDTO>> getDocumentTemplateTypeWithHttpInfo(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return getDocumentTemplateTypeRequestCreation(typeId).toEntity(localVarReturnType);
    }

    /**
     * Get Document Template Type by ID
     * Retrieve a document template type by its ID.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDocumentTemplateTypeWithResponseSpec(Long typeId) throws WebClientResponseException {
        return getDocumentTemplateTypeRequestCreation(typeId);
    }
    /**
     * Get Document Template Type by Code
     * Retrieve a document template type by its code.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeCode Code of the document template type
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDocumentTemplateTypeByCodeRequestCreation(String typeCode) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeCode' is set
        if (typeCode == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeCode' when calling getDocumentTemplateTypeByCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeCode", typeCode);

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

        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-types/code/{typeCode}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Document Template Type by Code
     * Retrieve a document template type by its code.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeCode Code of the document template type
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateTypeCatalogDTO> getDocumentTemplateTypeByCode(String typeCode) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return getDocumentTemplateTypeByCodeRequestCreation(typeCode).bodyToMono(localVarReturnType);
    }

    /**
     * Get Document Template Type by Code
     * Retrieve a document template type by its code.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeCode Code of the document template type
     * @return ResponseEntity&lt;DocumentTemplateTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateTypeCatalogDTO>> getDocumentTemplateTypeByCodeWithHttpInfo(String typeCode) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return getDocumentTemplateTypeByCodeRequestCreation(typeCode).toEntity(localVarReturnType);
    }

    /**
     * Get Document Template Type by Code
     * Retrieve a document template type by its code.
     * <p><b>200</b> - Successfully retrieved document template type
     * <p><b>404</b> - Document template type not found
     * @param typeCode Code of the document template type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDocumentTemplateTypeByCodeWithResponseSpec(String typeCode) throws WebClientResponseException {
        return getDocumentTemplateTypeByCodeRequestCreation(typeCode);
    }
    /**
     * List Document Template Types
     * Retrieve a paginated list of document template types.
     * <p><b>200</b> - Successfully retrieved list of document template types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listDocumentTemplateTypesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/document-template-types", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Document Template Types
     * Retrieve a paginated list of document template types.
     * <p><b>200</b> - Successfully retrieved list of document template types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listDocumentTemplateTypes(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplateTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Document Template Types
     * Retrieve a paginated list of document template types.
     * <p><b>200</b> - Successfully retrieved list of document template types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listDocumentTemplateTypesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplateTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Document Template Types
     * Retrieve a paginated list of document template types.
     * <p><b>200</b> - Successfully retrieved list of document template types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listDocumentTemplateTypesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listDocumentTemplateTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Document Template Type
     * Update an existing document template type.
     * <p><b>200</b> - Document template type updated successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateDocumentTemplateTypeRequestCreation(Long typeId, DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = documentTemplateTypeCatalogDTO;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling updateDocumentTemplateType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'documentTemplateTypeCatalogDTO' is set
        if (documentTemplateTypeCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentTemplateTypeCatalogDTO' when calling updateDocumentTemplateType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeId", typeId);

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

        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-types/{typeId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Document Template Type
     * Update an existing document template type.
     * <p><b>200</b> - Document template type updated successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateTypeCatalogDTO> updateDocumentTemplateType(Long typeId, DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return updateDocumentTemplateTypeRequestCreation(typeId, documentTemplateTypeCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Document Template Type
     * Update an existing document template type.
     * <p><b>200</b> - Document template type updated successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;DocumentTemplateTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateTypeCatalogDTO>> updateDocumentTemplateTypeWithHttpInfo(Long typeId, DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateTypeCatalogDTO>() {};
        return updateDocumentTemplateTypeRequestCreation(typeId, documentTemplateTypeCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Document Template Type
     * Update an existing document template type.
     * <p><b>200</b> - Document template type updated successfully
     * <p><b>404</b> - Document template type not found
     * @param typeId ID of the document template type
     * @param documentTemplateTypeCatalogDTO The documentTemplateTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateDocumentTemplateTypeWithResponseSpec(Long typeId, DocumentTemplateTypeCatalogDTO documentTemplateTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateDocumentTemplateTypeRequestCreation(typeId, documentTemplateTypeCatalogDTO, xIdempotencyKey);
    }
}

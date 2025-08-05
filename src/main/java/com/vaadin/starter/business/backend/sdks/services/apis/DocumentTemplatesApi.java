package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.DocumentTemplateCatalogDTO;
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
public class DocumentTemplatesApi {
    private ApiClient apiClient;

    public DocumentTemplatesApi() {
        this(new ApiClient());
    }

    @Autowired
    public DocumentTemplatesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Document Template
     * Create a new document template.
     * <p><b>201</b> - Document template created successfully
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createDocumentTemplateRequestCreation(DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = documentTemplateCatalogDTO;
        // verify the required parameter 'documentTemplateCatalogDTO' is set
        if (documentTemplateCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentTemplateCatalogDTO' when calling createDocumentTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-templates", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Document Template
     * Create a new document template.
     * <p><b>201</b> - Document template created successfully
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateCatalogDTO> createDocumentTemplate(DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return createDocumentTemplateRequestCreation(documentTemplateCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Document Template
     * Create a new document template.
     * <p><b>201</b> - Document template created successfully
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;DocumentTemplateCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateCatalogDTO>> createDocumentTemplateWithHttpInfo(DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return createDocumentTemplateRequestCreation(documentTemplateCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Document Template
     * Create a new document template.
     * <p><b>201</b> - Document template created successfully
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createDocumentTemplateWithResponseSpec(DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createDocumentTemplateRequestCreation(documentTemplateCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Document Template
     * Delete a document template by its ID.
     * <p><b>204</b> - Document template deleted successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteDocumentTemplateRequestCreation(Long templateId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling deleteDocumentTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("templateId", templateId);

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
        return apiClient.invokeAPI("/api/v1/document-templates/{templateId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Document Template
     * Delete a document template by its ID.
     * <p><b>204</b> - Document template deleted successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteDocumentTemplate(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDocumentTemplateRequestCreation(templateId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Document Template
     * Delete a document template by its ID.
     * <p><b>204</b> - Document template deleted successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteDocumentTemplateWithHttpInfo(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDocumentTemplateRequestCreation(templateId).toEntity(localVarReturnType);
    }

    /**
     * Delete Document Template
     * Delete a document template by its ID.
     * <p><b>204</b> - Document template deleted successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteDocumentTemplateWithResponseSpec(Long templateId) throws WebClientResponseException {
        return deleteDocumentTemplateRequestCreation(templateId);
    }
    /**
     * Get Document Template by ID
     * Retrieve a document template by its ID.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDocumentTemplateRequestCreation(Long templateId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling getDocumentTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("templateId", templateId);

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

        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-templates/{templateId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Document Template by ID
     * Retrieve a document template by its ID.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateCatalogDTO> getDocumentTemplate(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return getDocumentTemplateRequestCreation(templateId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Document Template by ID
     * Retrieve a document template by its ID.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @return ResponseEntity&lt;DocumentTemplateCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateCatalogDTO>> getDocumentTemplateWithHttpInfo(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return getDocumentTemplateRequestCreation(templateId).toEntity(localVarReturnType);
    }

    /**
     * Get Document Template by ID
     * Retrieve a document template by its ID.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDocumentTemplateWithResponseSpec(Long templateId) throws WebClientResponseException {
        return getDocumentTemplateRequestCreation(templateId);
    }
    /**
     * Get Document Template by Code
     * Retrieve a document template by its code.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateCode Code of the document template
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDocumentTemplateByCodeRequestCreation(String templateCode) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateCode' is set
        if (templateCode == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateCode' when calling getDocumentTemplateByCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("templateCode", templateCode);

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

        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-templates/code/{templateCode}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Document Template by Code
     * Retrieve a document template by its code.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateCode Code of the document template
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateCatalogDTO> getDocumentTemplateByCode(String templateCode) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return getDocumentTemplateByCodeRequestCreation(templateCode).bodyToMono(localVarReturnType);
    }

    /**
     * Get Document Template by Code
     * Retrieve a document template by its code.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateCode Code of the document template
     * @return ResponseEntity&lt;DocumentTemplateCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateCatalogDTO>> getDocumentTemplateByCodeWithHttpInfo(String templateCode) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return getDocumentTemplateByCodeRequestCreation(templateCode).toEntity(localVarReturnType);
    }

    /**
     * Get Document Template by Code
     * Retrieve a document template by its code.
     * <p><b>200</b> - Successfully retrieved document template
     * <p><b>404</b> - Document template not found
     * @param templateCode Code of the document template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDocumentTemplateByCodeWithResponseSpec(String templateCode) throws WebClientResponseException {
        return getDocumentTemplateByCodeRequestCreation(templateCode);
    }
    /**
     * List Document Templates
     * Retrieve a paginated list of document templates.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listDocumentTemplatesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/document-templates", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Document Templates
     * Retrieve a paginated list of document templates.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listDocumentTemplates(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplatesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Document Templates
     * Retrieve a paginated list of document templates.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listDocumentTemplatesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplatesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Document Templates
     * Retrieve a paginated list of document templates.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listDocumentTemplatesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listDocumentTemplatesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Document Templates by Category
     * Retrieve a paginated list of document templates for a specific category.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param category Category of templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listDocumentTemplatesByCategoryRequestCreation(String category, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'category' is set
        if (category == null) {
            throw new WebClientResponseException("Missing the required parameter 'category' when calling listDocumentTemplatesByCategory", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("category", category);

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
        return apiClient.invokeAPI("/api/v1/document-templates/category/{category}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Document Templates by Category
     * Retrieve a paginated list of document templates for a specific category.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param category Category of templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listDocumentTemplatesByCategory(String category, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplatesByCategoryRequestCreation(category, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Document Templates by Category
     * Retrieve a paginated list of document templates for a specific category.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param category Category of templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listDocumentTemplatesByCategoryWithHttpInfo(String category, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplatesByCategoryRequestCreation(category, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Document Templates by Category
     * Retrieve a paginated list of document templates for a specific category.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param category Category of templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listDocumentTemplatesByCategoryWithResponseSpec(String category, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listDocumentTemplatesByCategoryRequestCreation(category, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Document Templates by Type
     * Retrieve a paginated list of document templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param typeId Template type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listDocumentTemplatesByTypeIdRequestCreation(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling listDocumentTemplatesByTypeId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeId", typeId);

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
        return apiClient.invokeAPI("/api/v1/document-templates/type/{typeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Document Templates by Type
     * Retrieve a paginated list of document templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param typeId Template type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listDocumentTemplatesByTypeId(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplatesByTypeIdRequestCreation(typeId, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Document Templates by Type
     * Retrieve a paginated list of document templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param typeId Template type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listDocumentTemplatesByTypeIdWithHttpInfo(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDocumentTemplatesByTypeIdRequestCreation(typeId, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Document Templates by Type
     * Retrieve a paginated list of document templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of document templates
     * @param typeId Template type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listDocumentTemplatesByTypeIdWithResponseSpec(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listDocumentTemplatesByTypeIdRequestCreation(typeId, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Document Template
     * Update an existing document template.
     * <p><b>200</b> - Document template updated successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateDocumentTemplateRequestCreation(Long templateId, DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = documentTemplateCatalogDTO;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling updateDocumentTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'documentTemplateCatalogDTO' is set
        if (documentTemplateCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentTemplateCatalogDTO' when calling updateDocumentTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("templateId", templateId);

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

        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-templates/{templateId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Document Template
     * Update an existing document template.
     * <p><b>200</b> - Document template updated successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateCatalogDTO> updateDocumentTemplate(Long templateId, DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return updateDocumentTemplateRequestCreation(templateId, documentTemplateCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Document Template
     * Update an existing document template.
     * <p><b>200</b> - Document template updated successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;DocumentTemplateCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateCatalogDTO>> updateDocumentTemplateWithHttpInfo(Long templateId, DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateCatalogDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateCatalogDTO>() {};
        return updateDocumentTemplateRequestCreation(templateId, documentTemplateCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Document Template
     * Update an existing document template.
     * <p><b>200</b> - Document template updated successfully
     * <p><b>404</b> - Document template not found
     * @param templateId ID of the document template
     * @param documentTemplateCatalogDTO The documentTemplateCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateDocumentTemplateWithResponseSpec(Long templateId, DocumentTemplateCatalogDTO documentTemplateCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateDocumentTemplateRequestCreation(templateId, documentTemplateCatalogDTO, xIdempotencyKey);
    }
}

package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.DocumentTemplateLocalizationDTO;
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
public class DocumentTemplateLocalizationsApi {
    private ApiClient apiClient;

    public DocumentTemplateLocalizationsApi() {
        this(new ApiClient());
    }

    @Autowired
    public DocumentTemplateLocalizationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Document Template Localization
     * Create a new document template localization.
     * <p><b>201</b> - Document template localization created successfully
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createDocumentTemplateLocalizationRequestCreation(DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = documentTemplateLocalizationDTO;
        // verify the required parameter 'documentTemplateLocalizationDTO' is set
        if (documentTemplateLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentTemplateLocalizationDTO' when calling createDocumentTemplateLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-localizations", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Document Template Localization
     * Create a new document template localization.
     * <p><b>201</b> - Document template localization created successfully
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateLocalizationDTO> createDocumentTemplateLocalization(DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return createDocumentTemplateLocalizationRequestCreation(documentTemplateLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Document Template Localization
     * Create a new document template localization.
     * <p><b>201</b> - Document template localization created successfully
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;DocumentTemplateLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateLocalizationDTO>> createDocumentTemplateLocalizationWithHttpInfo(DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return createDocumentTemplateLocalizationRequestCreation(documentTemplateLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Document Template Localization
     * Create a new document template localization.
     * <p><b>201</b> - Document template localization created successfully
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createDocumentTemplateLocalizationWithResponseSpec(DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createDocumentTemplateLocalizationRequestCreation(documentTemplateLocalizationDTO, xIdempotencyKey);
    }
    /**
     * Delete Document Template Localization
     * Delete a document template localization by its ID.
     * <p><b>204</b> - Document template localization deleted successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteDocumentTemplateLocalizationRequestCreation(Long localizationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling deleteDocumentTemplateLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/document-template-localizations/{localizationId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Document Template Localization
     * Delete a document template localization by its ID.
     * <p><b>204</b> - Document template localization deleted successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteDocumentTemplateLocalization(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDocumentTemplateLocalizationRequestCreation(localizationId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Document Template Localization
     * Delete a document template localization by its ID.
     * <p><b>204</b> - Document template localization deleted successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteDocumentTemplateLocalizationWithHttpInfo(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDocumentTemplateLocalizationRequestCreation(localizationId).toEntity(localVarReturnType);
    }

    /**
     * Delete Document Template Localization
     * Delete a document template localization by its ID.
     * <p><b>204</b> - Document template localization deleted successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteDocumentTemplateLocalizationWithResponseSpec(Long localizationId) throws WebClientResponseException {
        return deleteDocumentTemplateLocalizationRequestCreation(localizationId);
    }
    /**
     * Delete Localizations by Template ID
     * Delete all localizations for a specific document template.
     * <p><b>204</b> - Document template localizations deleted successfully
     * @param templateId ID of the document template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteLocalizationsByTemplateIdRequestCreation(Long templateId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling deleteLocalizationsByTemplateId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/document-template-localizations/template/{templateId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Localizations by Template ID
     * Delete all localizations for a specific document template.
     * <p><b>204</b> - Document template localizations deleted successfully
     * @param templateId ID of the document template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteLocalizationsByTemplateId(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLocalizationsByTemplateIdRequestCreation(templateId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Localizations by Template ID
     * Delete all localizations for a specific document template.
     * <p><b>204</b> - Document template localizations deleted successfully
     * @param templateId ID of the document template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteLocalizationsByTemplateIdWithHttpInfo(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLocalizationsByTemplateIdRequestCreation(templateId).toEntity(localVarReturnType);
    }

    /**
     * Delete Localizations by Template ID
     * Delete all localizations for a specific document template.
     * <p><b>204</b> - Document template localizations deleted successfully
     * @param templateId ID of the document template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteLocalizationsByTemplateIdWithResponseSpec(Long templateId) throws WebClientResponseException {
        return deleteLocalizationsByTemplateIdRequestCreation(templateId);
    }
    /**
     * Get Document Template Localization by ID
     * Retrieve a document template localization by its ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDocumentTemplateLocalizationRequestCreation(Long localizationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling getDocumentTemplateLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-localizations/{localizationId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Document Template Localization by ID
     * Retrieve a document template localization by its ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateLocalizationDTO> getDocumentTemplateLocalization(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getDocumentTemplateLocalizationRequestCreation(localizationId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Document Template Localization by ID
     * Retrieve a document template localization by its ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @return ResponseEntity&lt;DocumentTemplateLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateLocalizationDTO>> getDocumentTemplateLocalizationWithHttpInfo(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getDocumentTemplateLocalizationRequestCreation(localizationId).toEntity(localVarReturnType);
    }

    /**
     * Get Document Template Localization by ID
     * Retrieve a document template localization by its ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDocumentTemplateLocalizationWithResponseSpec(Long localizationId) throws WebClientResponseException {
        return getDocumentTemplateLocalizationRequestCreation(localizationId);
    }
    /**
     * Get Document Template Localization by Template and Locale
     * Retrieve a document template localization by template ID and locale ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param templateId ID of the document template
     * @param localeId ID of the language locale
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDocumentTemplateLocalizationByTemplateAndLocaleRequestCreation(Long templateId, Long localeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling getDocumentTemplateLocalizationByTemplateAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'localeId' is set
        if (localeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localeId' when calling getDocumentTemplateLocalizationByTemplateAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("templateId", templateId);
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

        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-localizations/template/{templateId}/locale/{localeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Document Template Localization by Template and Locale
     * Retrieve a document template localization by template ID and locale ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param templateId ID of the document template
     * @param localeId ID of the language locale
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateLocalizationDTO> getDocumentTemplateLocalizationByTemplateAndLocale(Long templateId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getDocumentTemplateLocalizationByTemplateAndLocaleRequestCreation(templateId, localeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Document Template Localization by Template and Locale
     * Retrieve a document template localization by template ID and locale ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param templateId ID of the document template
     * @param localeId ID of the language locale
     * @return ResponseEntity&lt;DocumentTemplateLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateLocalizationDTO>> getDocumentTemplateLocalizationByTemplateAndLocaleWithHttpInfo(Long templateId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getDocumentTemplateLocalizationByTemplateAndLocaleRequestCreation(templateId, localeId).toEntity(localVarReturnType);
    }

    /**
     * Get Document Template Localization by Template and Locale
     * Retrieve a document template localization by template ID and locale ID.
     * <p><b>200</b> - Successfully retrieved document template localization
     * <p><b>404</b> - Document template localization not found
     * @param templateId ID of the document template
     * @param localeId ID of the language locale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDocumentTemplateLocalizationByTemplateAndLocaleWithResponseSpec(Long templateId, Long localeId) throws WebClientResponseException {
        return getDocumentTemplateLocalizationByTemplateAndLocaleRequestCreation(templateId, localeId);
    }
    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified locale
     * @param localeId ID of the language locale
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLocalizationsByLocaleId1RequestCreation(Long localeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localeId' is set
        if (localeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localeId' when calling getLocalizationsByLocaleId1", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

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

        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-localizations/locale/{localeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified locale
     * @param localeId ID of the language locale
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateLocalizationDTO> getLocalizationsByLocaleId1(Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getLocalizationsByLocaleId1RequestCreation(localeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified locale
     * @param localeId ID of the language locale
     * @return ResponseEntity&lt;DocumentTemplateLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateLocalizationDTO>> getLocalizationsByLocaleId1WithHttpInfo(Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getLocalizationsByLocaleId1RequestCreation(localeId).toEntity(localVarReturnType);
    }

    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified locale
     * @param localeId ID of the language locale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLocalizationsByLocaleId1WithResponseSpec(Long localeId) throws WebClientResponseException {
        return getLocalizationsByLocaleId1RequestCreation(localeId);
    }
    /**
     * Get Localizations by Template ID
     * Retrieve all localizations for a specific document template.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified template
     * @param templateId ID of the document template
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLocalizationsByTemplateIdRequestCreation(Long templateId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling getLocalizationsByTemplateId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-localizations/template/{templateId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Localizations by Template ID
     * Retrieve all localizations for a specific document template.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified template
     * @param templateId ID of the document template
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateLocalizationDTO> getLocalizationsByTemplateId(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getLocalizationsByTemplateIdRequestCreation(templateId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Localizations by Template ID
     * Retrieve all localizations for a specific document template.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified template
     * @param templateId ID of the document template
     * @return ResponseEntity&lt;DocumentTemplateLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateLocalizationDTO>> getLocalizationsByTemplateIdWithHttpInfo(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return getLocalizationsByTemplateIdRequestCreation(templateId).toEntity(localVarReturnType);
    }

    /**
     * Get Localizations by Template ID
     * Retrieve all localizations for a specific document template.
     * <p><b>200</b> - Successfully retrieved localizations
     * <p><b>404</b> - No localizations found for the specified template
     * @param templateId ID of the document template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLocalizationsByTemplateIdWithResponseSpec(Long templateId) throws WebClientResponseException {
        return getLocalizationsByTemplateIdRequestCreation(templateId);
    }
    /**
     * Update Document Template Localization
     * Update an existing document template localization.
     * <p><b>200</b> - Document template localization updated successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateDocumentTemplateLocalizationRequestCreation(Long localizationId, DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = documentTemplateLocalizationDTO;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling updateDocumentTemplateLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'documentTemplateLocalizationDTO' is set
        if (documentTemplateLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'documentTemplateLocalizationDTO' when calling updateDocumentTemplateLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/document-template-localizations/{localizationId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Document Template Localization
     * Update an existing document template localization.
     * <p><b>200</b> - Document template localization updated successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return DocumentTemplateLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<DocumentTemplateLocalizationDTO> updateDocumentTemplateLocalization(Long localizationId, DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return updateDocumentTemplateLocalizationRequestCreation(localizationId, documentTemplateLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Document Template Localization
     * Update an existing document template localization.
     * <p><b>200</b> - Document template localization updated successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;DocumentTemplateLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<DocumentTemplateLocalizationDTO>> updateDocumentTemplateLocalizationWithHttpInfo(Long localizationId, DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<DocumentTemplateLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<DocumentTemplateLocalizationDTO>() {};
        return updateDocumentTemplateLocalizationRequestCreation(localizationId, documentTemplateLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Document Template Localization
     * Update an existing document template localization.
     * <p><b>200</b> - Document template localization updated successfully
     * <p><b>404</b> - Document template localization not found
     * @param localizationId ID of the document template localization
     * @param documentTemplateLocalizationDTO The documentTemplateLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateDocumentTemplateLocalizationWithResponseSpec(Long localizationId, DocumentTemplateLocalizationDTO documentTemplateLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateDocumentTemplateLocalizationRequestCreation(localizationId, documentTemplateLocalizationDTO, xIdempotencyKey);
    }
}

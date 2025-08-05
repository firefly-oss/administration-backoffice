package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.LanguageLocaleDTO;
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
public class LanguageLocaleApi {
    private ApiClient apiClient;

    public LanguageLocaleApi() {
        this(new ApiClient());
    }

    @Autowired
    public LanguageLocaleApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create LanguageLocale
     * Create a new language/locale entry.
     * <p><b>200</b> - LanguageLocale entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LanguageLocaleDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createLanguageLocaleRequestCreation(LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = languageLocaleDTO;
        // verify the required parameter 'languageLocaleDTO' is set
        if (languageLocaleDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'languageLocaleDTO' when calling createLanguageLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return apiClient.invokeAPI("/api/v1/language-locales", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create LanguageLocale
     * Create a new language/locale entry.
     * <p><b>200</b> - LanguageLocale entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LanguageLocaleDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LanguageLocaleDTO> createLanguageLocale(LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return createLanguageLocaleRequestCreation(languageLocaleDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create LanguageLocale
     * Create a new language/locale entry.
     * <p><b>200</b> - LanguageLocale entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LanguageLocaleDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LanguageLocaleDTO>> createLanguageLocaleWithHttpInfo(LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return createLanguageLocaleRequestCreation(languageLocaleDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create LanguageLocale
     * Create a new language/locale entry.
     * <p><b>200</b> - LanguageLocale entry created successfully
     * <p><b>400</b> - Invalid input data
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createLanguageLocaleWithResponseSpec(LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createLanguageLocaleRequestCreation(languageLocaleDTO, xIdempotencyKey);
    }
    /**
     * Delete LanguageLocale
     * Delete a specific language/locale record by its ID.
     * <p><b>204</b> - LanguageLocale entry deleted successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteLanguageLocaleRequestCreation(Long id) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling deleteLanguageLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/language-locales/{id}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete LanguageLocale
     * Delete a specific language/locale record by its ID.
     * <p><b>204</b> - LanguageLocale entry deleted successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteLanguageLocale(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLanguageLocaleRequestCreation(id).bodyToMono(localVarReturnType);
    }

    /**
     * Delete LanguageLocale
     * Delete a specific language/locale record by its ID.
     * <p><b>204</b> - LanguageLocale entry deleted successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteLanguageLocaleWithHttpInfo(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLanguageLocaleRequestCreation(id).toEntity(localVarReturnType);
    }

    /**
     * Delete LanguageLocale
     * Delete a specific language/locale record by its ID.
     * <p><b>204</b> - LanguageLocale entry deleted successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteLanguageLocaleWithResponseSpec(Long id) throws WebClientResponseException {
        return deleteLanguageLocaleRequestCreation(id);
    }
    /**
     * Get LanguageLocale by ID
     * Retrieve a specific language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale retrieved successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @return LanguageLocaleDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLanguageLocaleRequestCreation(Long id) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling getLanguageLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return apiClient.invokeAPI("/api/v1/language-locales/{id}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get LanguageLocale by ID
     * Retrieve a specific language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale retrieved successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @return LanguageLocaleDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LanguageLocaleDTO> getLanguageLocale(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return getLanguageLocaleRequestCreation(id).bodyToMono(localVarReturnType);
    }

    /**
     * Get LanguageLocale by ID
     * Retrieve a specific language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale retrieved successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @return ResponseEntity&lt;LanguageLocaleDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LanguageLocaleDTO>> getLanguageLocaleWithHttpInfo(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return getLanguageLocaleRequestCreation(id).toEntity(localVarReturnType);
    }

    /**
     * Get LanguageLocale by ID
     * Retrieve a specific language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale retrieved successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLanguageLocaleWithResponseSpec(Long id) throws WebClientResponseException {
        return getLanguageLocaleRequestCreation(id);
    }
    /**
     * List LanguageLocales
     * Retrieve a paginated list of language/locale records.
     * <p><b>200</b> - Successfully retrieved list of language locales
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listLanguageLocalesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/language-locales", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List LanguageLocales
     * Retrieve a paginated list of language/locale records.
     * <p><b>200</b> - Successfully retrieved list of language locales
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listLanguageLocales(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listLanguageLocalesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List LanguageLocales
     * Retrieve a paginated list of language/locale records.
     * <p><b>200</b> - Successfully retrieved list of language locales
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listLanguageLocalesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listLanguageLocalesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List LanguageLocales
     * Retrieve a paginated list of language/locale records.
     * <p><b>200</b> - Successfully retrieved list of language locales
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listLanguageLocalesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listLanguageLocalesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update LanguageLocale
     * Update an existing language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale updated successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LanguageLocaleDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateLanguageLocaleRequestCreation(Long id, LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = languageLocaleDTO;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling updateLanguageLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'languageLocaleDTO' is set
        if (languageLocaleDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'languageLocaleDTO' when calling updateLanguageLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return apiClient.invokeAPI("/api/v1/language-locales/{id}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update LanguageLocale
     * Update an existing language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale updated successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return LanguageLocaleDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<LanguageLocaleDTO> updateLanguageLocale(Long id, LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return updateLanguageLocaleRequestCreation(id, languageLocaleDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update LanguageLocale
     * Update an existing language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale updated successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;LanguageLocaleDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<LanguageLocaleDTO>> updateLanguageLocaleWithHttpInfo(Long id, LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<LanguageLocaleDTO> localVarReturnType = new ParameterizedTypeReference<LanguageLocaleDTO>() {};
        return updateLanguageLocaleRequestCreation(id, languageLocaleDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update LanguageLocale
     * Update an existing language/locale record by its ID.
     * <p><b>200</b> - LanguageLocale updated successfully
     * <p><b>404</b> - LanguageLocale not found
     * @param id ID of the languageLocale
     * @param languageLocaleDTO The languageLocaleDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateLanguageLocaleWithResponseSpec(Long id, LanguageLocaleDTO languageLocaleDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateLanguageLocaleRequestCreation(id, languageLocaleDTO, xIdempotencyKey);
    }
}

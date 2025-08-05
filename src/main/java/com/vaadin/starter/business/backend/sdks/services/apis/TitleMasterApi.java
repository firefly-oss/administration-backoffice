package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponse;
import com.catalis.common.reference.master.data.sdk.model.TitleMasterDTO;
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
public class TitleMasterApi {
    private ApiClient apiClient;

    public TitleMasterApi() {
        this(new ApiClient());
    }

    @Autowired
    public TitleMasterApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Title
     * Create a new title master record.
     * <p><b>200</b> - Title created successfully
     * <p><b>400</b> - Invalid input data
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TitleMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createTitleRequestCreation(TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = titleMasterDTO;
        // verify the required parameter 'titleMasterDTO' is set
        if (titleMasterDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'titleMasterDTO' when calling createTitle", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return apiClient.invokeAPI("/api/v1/titles", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Title
     * Create a new title master record.
     * <p><b>200</b> - Title created successfully
     * <p><b>400</b> - Invalid input data
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TitleMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TitleMasterDTO> createTitle(TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return createTitleRequestCreation(titleMasterDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Title
     * Create a new title master record.
     * <p><b>200</b> - Title created successfully
     * <p><b>400</b> - Invalid input data
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;TitleMasterDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TitleMasterDTO>> createTitleWithHttpInfo(TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return createTitleRequestCreation(titleMasterDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Title
     * Create a new title master record.
     * <p><b>200</b> - Title created successfully
     * <p><b>400</b> - Invalid input data
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createTitleWithResponseSpec(TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createTitleRequestCreation(titleMasterDTO, xIdempotencyKey);
    }
    /**
     * Delete Title
     * Delete a specific title by its ID.
     * <p><b>204</b> - Title deleted successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteTitleRequestCreation(Long titleId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'titleId' is set
        if (titleId == null) {
            throw new WebClientResponseException("Missing the required parameter 'titleId' when calling deleteTitle", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("titleId", titleId);

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
        return apiClient.invokeAPI("/api/v1/titles/{titleId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Title
     * Delete a specific title by its ID.
     * <p><b>204</b> - Title deleted successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteTitle(Long titleId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTitleRequestCreation(titleId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Title
     * Delete a specific title by its ID.
     * <p><b>204</b> - Title deleted successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteTitleWithHttpInfo(Long titleId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTitleRequestCreation(titleId).toEntity(localVarReturnType);
    }

    /**
     * Delete Title
     * Delete a specific title by its ID.
     * <p><b>204</b> - Title deleted successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteTitleWithResponseSpec(Long titleId) throws WebClientResponseException {
        return deleteTitleRequestCreation(titleId);
    }
    /**
     * Get Title by ID
     * Retrieve a specific title by its ID.
     * <p><b>200</b> - Title retrieved successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @return TitleMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTitleRequestCreation(Long titleId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'titleId' is set
        if (titleId == null) {
            throw new WebClientResponseException("Missing the required parameter 'titleId' when calling getTitle", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("titleId", titleId);

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

        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return apiClient.invokeAPI("/api/v1/titles/{titleId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Title by ID
     * Retrieve a specific title by its ID.
     * <p><b>200</b> - Title retrieved successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @return TitleMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TitleMasterDTO> getTitle(Long titleId) throws WebClientResponseException {
        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return getTitleRequestCreation(titleId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Title by ID
     * Retrieve a specific title by its ID.
     * <p><b>200</b> - Title retrieved successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @return ResponseEntity&lt;TitleMasterDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TitleMasterDTO>> getTitleWithHttpInfo(Long titleId) throws WebClientResponseException {
        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return getTitleRequestCreation(titleId).toEntity(localVarReturnType);
    }

    /**
     * Get Title by ID
     * Retrieve a specific title by its ID.
     * <p><b>200</b> - Title retrieved successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTitleWithResponseSpec(Long titleId) throws WebClientResponseException {
        return getTitleRequestCreation(titleId);
    }
    /**
     * List Titles
     * Retrieve a paginated list of titles.
     * <p><b>200</b> - Successfully retrieved list of titles
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listTitlesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/titles", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Titles
     * Retrieve a paginated list of titles.
     * <p><b>200</b> - Successfully retrieved list of titles
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listTitles(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listTitlesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Titles
     * Retrieve a paginated list of titles.
     * <p><b>200</b> - Successfully retrieved list of titles
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listTitlesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listTitlesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Titles
     * Retrieve a paginated list of titles.
     * <p><b>200</b> - Successfully retrieved list of titles
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listTitlesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listTitlesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Title
     * Update an existing title by its ID.
     * <p><b>200</b> - Title updated successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TitleMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateTitleRequestCreation(Long titleId, TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = titleMasterDTO;
        // verify the required parameter 'titleId' is set
        if (titleId == null) {
            throw new WebClientResponseException("Missing the required parameter 'titleId' when calling updateTitle", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'titleMasterDTO' is set
        if (titleMasterDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'titleMasterDTO' when calling updateTitle", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("titleId", titleId);

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

        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return apiClient.invokeAPI("/api/v1/titles/{titleId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Title
     * Update an existing title by its ID.
     * <p><b>200</b> - Title updated successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return TitleMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<TitleMasterDTO> updateTitle(Long titleId, TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return updateTitleRequestCreation(titleId, titleMasterDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Title
     * Update an existing title by its ID.
     * <p><b>200</b> - Title updated successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;TitleMasterDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<TitleMasterDTO>> updateTitleWithHttpInfo(Long titleId, TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<TitleMasterDTO> localVarReturnType = new ParameterizedTypeReference<TitleMasterDTO>() {};
        return updateTitleRequestCreation(titleId, titleMasterDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Title
     * Update an existing title by its ID.
     * <p><b>200</b> - Title updated successfully
     * <p><b>404</b> - Title not found
     * @param titleId ID of the title
     * @param titleMasterDTO The titleMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateTitleWithResponseSpec(Long titleId, TitleMasterDTO titleMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateTitleRequestCreation(titleId, titleMasterDTO, xIdempotencyKey);
    }
}

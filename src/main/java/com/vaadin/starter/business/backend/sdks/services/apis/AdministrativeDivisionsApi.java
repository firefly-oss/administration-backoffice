package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.AdministrativeDivisionDTO;
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
public class AdministrativeDivisionsApi {
    private ApiClient apiClient;

    public AdministrativeDivisionsApi() {
        this(new ApiClient());
    }

    @Autowired
    public AdministrativeDivisionsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Administrative Division
     * Create a new administrative division.
     * <p><b>200</b> - Administrative division created successfully
     * <p><b>400</b> - Invalid input data
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AdministrativeDivisionDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createDivisionRequestCreation(AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = administrativeDivisionDTO;
        // verify the required parameter 'administrativeDivisionDTO' is set
        if (administrativeDivisionDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'administrativeDivisionDTO' when calling createDivision", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return apiClient.invokeAPI("/api/v1/divisions", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Administrative Division
     * Create a new administrative division.
     * <p><b>200</b> - Administrative division created successfully
     * <p><b>400</b> - Invalid input data
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AdministrativeDivisionDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<AdministrativeDivisionDTO> createDivision(AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return createDivisionRequestCreation(administrativeDivisionDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Administrative Division
     * Create a new administrative division.
     * <p><b>200</b> - Administrative division created successfully
     * <p><b>400</b> - Invalid input data
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;AdministrativeDivisionDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<AdministrativeDivisionDTO>> createDivisionWithHttpInfo(AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return createDivisionRequestCreation(administrativeDivisionDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Administrative Division
     * Create a new administrative division.
     * <p><b>200</b> - Administrative division created successfully
     * <p><b>400</b> - Invalid input data
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createDivisionWithResponseSpec(AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createDivisionRequestCreation(administrativeDivisionDTO, xIdempotencyKey);
    }
    /**
     * Delete Administrative Division
     * Delete a specific administrative division by its ID.
     * <p><b>204</b> - Administrative division deleted successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteDivisionRequestCreation(Long divisionId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'divisionId' is set
        if (divisionId == null) {
            throw new WebClientResponseException("Missing the required parameter 'divisionId' when calling deleteDivision", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("divisionId", divisionId);

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
        return apiClient.invokeAPI("/api/v1/divisions/{divisionId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Administrative Division
     * Delete a specific administrative division by its ID.
     * <p><b>204</b> - Administrative division deleted successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteDivision(Long divisionId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDivisionRequestCreation(divisionId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Administrative Division
     * Delete a specific administrative division by its ID.
     * <p><b>204</b> - Administrative division deleted successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteDivisionWithHttpInfo(Long divisionId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteDivisionRequestCreation(divisionId).toEntity(localVarReturnType);
    }

    /**
     * Delete Administrative Division
     * Delete a specific administrative division by its ID.
     * <p><b>204</b> - Administrative division deleted successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteDivisionWithResponseSpec(Long divisionId) throws WebClientResponseException {
        return deleteDivisionRequestCreation(divisionId);
    }
    /**
     * Get Administrative Division by ID
     * Retrieve a specific administrative division by its ID.
     * <p><b>200</b> - Administrative division retrieved successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @return AdministrativeDivisionDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getDivisionRequestCreation(Long divisionId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'divisionId' is set
        if (divisionId == null) {
            throw new WebClientResponseException("Missing the required parameter 'divisionId' when calling getDivision", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("divisionId", divisionId);

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

        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return apiClient.invokeAPI("/api/v1/divisions/{divisionId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Administrative Division by ID
     * Retrieve a specific administrative division by its ID.
     * <p><b>200</b> - Administrative division retrieved successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @return AdministrativeDivisionDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<AdministrativeDivisionDTO> getDivision(Long divisionId) throws WebClientResponseException {
        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return getDivisionRequestCreation(divisionId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Administrative Division by ID
     * Retrieve a specific administrative division by its ID.
     * <p><b>200</b> - Administrative division retrieved successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @return ResponseEntity&lt;AdministrativeDivisionDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<AdministrativeDivisionDTO>> getDivisionWithHttpInfo(Long divisionId) throws WebClientResponseException {
        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return getDivisionRequestCreation(divisionId).toEntity(localVarReturnType);
    }

    /**
     * Get Administrative Division by ID
     * Retrieve a specific administrative division by its ID.
     * <p><b>200</b> - Administrative division retrieved successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getDivisionWithResponseSpec(Long divisionId) throws WebClientResponseException {
        return getDivisionRequestCreation(divisionId);
    }
    /**
     * List Administrative Divisions
     * Retrieve a paginated list of administrative divisions.
     * <p><b>200</b> - Successfully retrieved list of administrative divisions
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listDivisionsRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/divisions", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Administrative Divisions
     * Retrieve a paginated list of administrative divisions.
     * <p><b>200</b> - Successfully retrieved list of administrative divisions
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listDivisions(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDivisionsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Administrative Divisions
     * Retrieve a paginated list of administrative divisions.
     * <p><b>200</b> - Successfully retrieved list of administrative divisions
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listDivisionsWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listDivisionsRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Administrative Divisions
     * Retrieve a paginated list of administrative divisions.
     * <p><b>200</b> - Successfully retrieved list of administrative divisions
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listDivisionsWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listDivisionsRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Administrative Division
     * Update an existing administrative division by its ID.
     * <p><b>200</b> - Administrative division updated successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AdministrativeDivisionDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateDivisionRequestCreation(Long divisionId, AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = administrativeDivisionDTO;
        // verify the required parameter 'divisionId' is set
        if (divisionId == null) {
            throw new WebClientResponseException("Missing the required parameter 'divisionId' when calling updateDivision", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'administrativeDivisionDTO' is set
        if (administrativeDivisionDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'administrativeDivisionDTO' when calling updateDivision", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("divisionId", divisionId);

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

        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return apiClient.invokeAPI("/api/v1/divisions/{divisionId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Administrative Division
     * Update an existing administrative division by its ID.
     * <p><b>200</b> - Administrative division updated successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AdministrativeDivisionDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<AdministrativeDivisionDTO> updateDivision(Long divisionId, AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return updateDivisionRequestCreation(divisionId, administrativeDivisionDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Administrative Division
     * Update an existing administrative division by its ID.
     * <p><b>200</b> - Administrative division updated successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;AdministrativeDivisionDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<AdministrativeDivisionDTO>> updateDivisionWithHttpInfo(Long divisionId, AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AdministrativeDivisionDTO> localVarReturnType = new ParameterizedTypeReference<AdministrativeDivisionDTO>() {};
        return updateDivisionRequestCreation(divisionId, administrativeDivisionDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Administrative Division
     * Update an existing administrative division by its ID.
     * <p><b>200</b> - Administrative division updated successfully
     * <p><b>404</b> - Administrative division not found
     * @param divisionId ID of the administrative division
     * @param administrativeDivisionDTO The administrativeDivisionDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateDivisionWithResponseSpec(Long divisionId, AdministrativeDivisionDTO administrativeDivisionDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateDivisionRequestCreation(divisionId, administrativeDivisionDTO, xIdempotencyKey);
    }
}

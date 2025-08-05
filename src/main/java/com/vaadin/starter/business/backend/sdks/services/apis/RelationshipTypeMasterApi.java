package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponse;
import com.catalis.common.reference.master.data.sdk.model.RelationshipTypeMasterDTO;
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
public class RelationshipTypeMasterApi {
    private ApiClient apiClient;

    public RelationshipTypeMasterApi() {
        this(new ApiClient());
    }

    @Autowired
    public RelationshipTypeMasterApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Relationship Type
     * Create a new relationship type record.
     * <p><b>200</b> - Relationship type created successfully
     * <p><b>400</b> - Invalid input data
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return RelationshipTypeMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createRelationshipTypeRequestCreation(RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = relationshipTypeMasterDTO;
        // verify the required parameter 'relationshipTypeMasterDTO' is set
        if (relationshipTypeMasterDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'relationshipTypeMasterDTO' when calling createRelationshipType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return apiClient.invokeAPI("/api/v1/relationship-types", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Relationship Type
     * Create a new relationship type record.
     * <p><b>200</b> - Relationship type created successfully
     * <p><b>400</b> - Invalid input data
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return RelationshipTypeMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<RelationshipTypeMasterDTO> createRelationshipType(RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return createRelationshipTypeRequestCreation(relationshipTypeMasterDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Relationship Type
     * Create a new relationship type record.
     * <p><b>200</b> - Relationship type created successfully
     * <p><b>400</b> - Invalid input data
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;RelationshipTypeMasterDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<RelationshipTypeMasterDTO>> createRelationshipTypeWithHttpInfo(RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return createRelationshipTypeRequestCreation(relationshipTypeMasterDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Relationship Type
     * Create a new relationship type record.
     * <p><b>200</b> - Relationship type created successfully
     * <p><b>400</b> - Invalid input data
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createRelationshipTypeWithResponseSpec(RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createRelationshipTypeRequestCreation(relationshipTypeMasterDTO, xIdempotencyKey);
    }
    /**
     * Delete Relationship Type
     * Delete a specific relationship type by its ID.
     * <p><b>204</b> - Relationship type deleted successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteRelationshipTypeRequestCreation(Long relationshipTypeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'relationshipTypeId' is set
        if (relationshipTypeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'relationshipTypeId' when calling deleteRelationshipType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("relationshipTypeId", relationshipTypeId);

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
        return apiClient.invokeAPI("/api/v1/relationship-types/{relationshipTypeId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Relationship Type
     * Delete a specific relationship type by its ID.
     * <p><b>204</b> - Relationship type deleted successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteRelationshipType(Long relationshipTypeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteRelationshipTypeRequestCreation(relationshipTypeId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Relationship Type
     * Delete a specific relationship type by its ID.
     * <p><b>204</b> - Relationship type deleted successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteRelationshipTypeWithHttpInfo(Long relationshipTypeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteRelationshipTypeRequestCreation(relationshipTypeId).toEntity(localVarReturnType);
    }

    /**
     * Delete Relationship Type
     * Delete a specific relationship type by its ID.
     * <p><b>204</b> - Relationship type deleted successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteRelationshipTypeWithResponseSpec(Long relationshipTypeId) throws WebClientResponseException {
        return deleteRelationshipTypeRequestCreation(relationshipTypeId);
    }
    /**
     * Get Relationship Type by ID
     * Retrieve a specific relationship type by its ID.
     * <p><b>200</b> - Relationship type retrieved successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @return RelationshipTypeMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getRelationshipTypeRequestCreation(Long relationshipTypeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'relationshipTypeId' is set
        if (relationshipTypeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'relationshipTypeId' when calling getRelationshipType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("relationshipTypeId", relationshipTypeId);

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

        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return apiClient.invokeAPI("/api/v1/relationship-types/{relationshipTypeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Relationship Type by ID
     * Retrieve a specific relationship type by its ID.
     * <p><b>200</b> - Relationship type retrieved successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @return RelationshipTypeMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<RelationshipTypeMasterDTO> getRelationshipType(Long relationshipTypeId) throws WebClientResponseException {
        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return getRelationshipTypeRequestCreation(relationshipTypeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Relationship Type by ID
     * Retrieve a specific relationship type by its ID.
     * <p><b>200</b> - Relationship type retrieved successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @return ResponseEntity&lt;RelationshipTypeMasterDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<RelationshipTypeMasterDTO>> getRelationshipTypeWithHttpInfo(Long relationshipTypeId) throws WebClientResponseException {
        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return getRelationshipTypeRequestCreation(relationshipTypeId).toEntity(localVarReturnType);
    }

    /**
     * Get Relationship Type by ID
     * Retrieve a specific relationship type by its ID.
     * <p><b>200</b> - Relationship type retrieved successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getRelationshipTypeWithResponseSpec(Long relationshipTypeId) throws WebClientResponseException {
        return getRelationshipTypeRequestCreation(relationshipTypeId);
    }
    /**
     * List Relationship Types
     * Retrieve a paginated list of relationship types.
     * <p><b>200</b> - Successfully retrieved list of relationship types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listRelationshipTypesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/relationship-types", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Relationship Types
     * Retrieve a paginated list of relationship types.
     * <p><b>200</b> - Successfully retrieved list of relationship types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listRelationshipTypes(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listRelationshipTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Relationship Types
     * Retrieve a paginated list of relationship types.
     * <p><b>200</b> - Successfully retrieved list of relationship types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listRelationshipTypesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listRelationshipTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Relationship Types
     * Retrieve a paginated list of relationship types.
     * <p><b>200</b> - Successfully retrieved list of relationship types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listRelationshipTypesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listRelationshipTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Relationship Type
     * Update an existing relationship type by its ID.
     * <p><b>200</b> - Relationship type updated successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return RelationshipTypeMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateRelationshipTypeRequestCreation(Long relationshipTypeId, RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = relationshipTypeMasterDTO;
        // verify the required parameter 'relationshipTypeId' is set
        if (relationshipTypeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'relationshipTypeId' when calling updateRelationshipType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'relationshipTypeMasterDTO' is set
        if (relationshipTypeMasterDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'relationshipTypeMasterDTO' when calling updateRelationshipType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("relationshipTypeId", relationshipTypeId);

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

        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return apiClient.invokeAPI("/api/v1/relationship-types/{relationshipTypeId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Relationship Type
     * Update an existing relationship type by its ID.
     * <p><b>200</b> - Relationship type updated successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return RelationshipTypeMasterDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<RelationshipTypeMasterDTO> updateRelationshipType(Long relationshipTypeId, RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return updateRelationshipTypeRequestCreation(relationshipTypeId, relationshipTypeMasterDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Relationship Type
     * Update an existing relationship type by its ID.
     * <p><b>200</b> - Relationship type updated successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;RelationshipTypeMasterDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<RelationshipTypeMasterDTO>> updateRelationshipTypeWithHttpInfo(Long relationshipTypeId, RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<RelationshipTypeMasterDTO> localVarReturnType = new ParameterizedTypeReference<RelationshipTypeMasterDTO>() {};
        return updateRelationshipTypeRequestCreation(relationshipTypeId, relationshipTypeMasterDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Relationship Type
     * Update an existing relationship type by its ID.
     * <p><b>200</b> - Relationship type updated successfully
     * <p><b>404</b> - Relationship type not found
     * @param relationshipTypeId ID of the relationship type
     * @param relationshipTypeMasterDTO The relationshipTypeMasterDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateRelationshipTypeWithResponseSpec(Long relationshipTypeId, RelationshipTypeMasterDTO relationshipTypeMasterDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateRelationshipTypeRequestCreation(relationshipTypeId, relationshipTypeMasterDTO, xIdempotencyKey);
    }
}

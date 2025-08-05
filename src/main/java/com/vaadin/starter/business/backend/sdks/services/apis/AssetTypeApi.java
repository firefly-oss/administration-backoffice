package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.AssetTypeDTO;
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
public class AssetTypeApi {
    private ApiClient apiClient;

    public AssetTypeApi() {
        this(new ApiClient());
    }

    @Autowired
    public AssetTypeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Asset Type
     * Create a new asset type record.
     * <p><b>200</b> - Asset type created successfully
     * <p><b>400</b> - Invalid input data
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AssetTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createAssetTypeRequestCreation(AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = assetTypeDTO;
        // verify the required parameter 'assetTypeDTO' is set
        if (assetTypeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'assetTypeDTO' when calling createAssetType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return apiClient.invokeAPI("/api/v1/asset-types", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Asset Type
     * Create a new asset type record.
     * <p><b>200</b> - Asset type created successfully
     * <p><b>400</b> - Invalid input data
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AssetTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<AssetTypeDTO> createAssetType(AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return createAssetTypeRequestCreation(assetTypeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Asset Type
     * Create a new asset type record.
     * <p><b>200</b> - Asset type created successfully
     * <p><b>400</b> - Invalid input data
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;AssetTypeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<AssetTypeDTO>> createAssetTypeWithHttpInfo(AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return createAssetTypeRequestCreation(assetTypeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Asset Type
     * Create a new asset type record.
     * <p><b>200</b> - Asset type created successfully
     * <p><b>400</b> - Invalid input data
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createAssetTypeWithResponseSpec(AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createAssetTypeRequestCreation(assetTypeDTO, xIdempotencyKey);
    }
    /**
     * Delete Asset Type
     * Delete a specific asset type by its ID.
     * <p><b>204</b> - Asset type deleted successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteAssetTypeRequestCreation(Long assetId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'assetId' is set
        if (assetId == null) {
            throw new WebClientResponseException("Missing the required parameter 'assetId' when calling deleteAssetType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("assetId", assetId);

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
        return apiClient.invokeAPI("/api/v1/asset-types/{assetId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Asset Type
     * Delete a specific asset type by its ID.
     * <p><b>204</b> - Asset type deleted successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteAssetType(Long assetId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteAssetTypeRequestCreation(assetId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Asset Type
     * Delete a specific asset type by its ID.
     * <p><b>204</b> - Asset type deleted successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteAssetTypeWithHttpInfo(Long assetId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteAssetTypeRequestCreation(assetId).toEntity(localVarReturnType);
    }

    /**
     * Delete Asset Type
     * Delete a specific asset type by its ID.
     * <p><b>204</b> - Asset type deleted successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteAssetTypeWithResponseSpec(Long assetId) throws WebClientResponseException {
        return deleteAssetTypeRequestCreation(assetId);
    }
    /**
     * Get Asset Type by ID
     * Retrieve a specific asset type by its ID.
     * <p><b>200</b> - Asset type retrieved successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @return AssetTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getAssetTypeRequestCreation(Long assetId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'assetId' is set
        if (assetId == null) {
            throw new WebClientResponseException("Missing the required parameter 'assetId' when calling getAssetType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("assetId", assetId);

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

        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return apiClient.invokeAPI("/api/v1/asset-types/{assetId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Asset Type by ID
     * Retrieve a specific asset type by its ID.
     * <p><b>200</b> - Asset type retrieved successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @return AssetTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<AssetTypeDTO> getAssetType(Long assetId) throws WebClientResponseException {
        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return getAssetTypeRequestCreation(assetId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Asset Type by ID
     * Retrieve a specific asset type by its ID.
     * <p><b>200</b> - Asset type retrieved successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @return ResponseEntity&lt;AssetTypeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<AssetTypeDTO>> getAssetTypeWithHttpInfo(Long assetId) throws WebClientResponseException {
        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return getAssetTypeRequestCreation(assetId).toEntity(localVarReturnType);
    }

    /**
     * Get Asset Type by ID
     * Retrieve a specific asset type by its ID.
     * <p><b>200</b> - Asset type retrieved successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getAssetTypeWithResponseSpec(Long assetId) throws WebClientResponseException {
        return getAssetTypeRequestCreation(assetId);
    }
    /**
     * List Asset Types
     * Retrieve a paginated list of asset types.
     * <p><b>200</b> - Successfully retrieved list of asset types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listAssetTypesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/asset-types", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Asset Types
     * Retrieve a paginated list of asset types.
     * <p><b>200</b> - Successfully retrieved list of asset types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listAssetTypes(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listAssetTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Asset Types
     * Retrieve a paginated list of asset types.
     * <p><b>200</b> - Successfully retrieved list of asset types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listAssetTypesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listAssetTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Asset Types
     * Retrieve a paginated list of asset types.
     * <p><b>200</b> - Successfully retrieved list of asset types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listAssetTypesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listAssetTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Asset Type
     * Update an existing asset type by its ID.
     * <p><b>200</b> - Asset type updated successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AssetTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateAssetTypeRequestCreation(Long assetId, AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = assetTypeDTO;
        // verify the required parameter 'assetId' is set
        if (assetId == null) {
            throw new WebClientResponseException("Missing the required parameter 'assetId' when calling updateAssetType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'assetTypeDTO' is set
        if (assetTypeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'assetTypeDTO' when calling updateAssetType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("assetId", assetId);

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

        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return apiClient.invokeAPI("/api/v1/asset-types/{assetId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Asset Type
     * Update an existing asset type by its ID.
     * <p><b>200</b> - Asset type updated successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return AssetTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<AssetTypeDTO> updateAssetType(Long assetId, AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return updateAssetTypeRequestCreation(assetId, assetTypeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Asset Type
     * Update an existing asset type by its ID.
     * <p><b>200</b> - Asset type updated successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;AssetTypeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<AssetTypeDTO>> updateAssetTypeWithHttpInfo(Long assetId, AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<AssetTypeDTO> localVarReturnType = new ParameterizedTypeReference<AssetTypeDTO>() {};
        return updateAssetTypeRequestCreation(assetId, assetTypeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Asset Type
     * Update an existing asset type by its ID.
     * <p><b>200</b> - Asset type updated successfully
     * <p><b>404</b> - Asset type not found
     * @param assetId ID of the asset type
     * @param assetTypeDTO The assetTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateAssetTypeWithResponseSpec(Long assetId, AssetTypeDTO assetTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateAssetTypeRequestCreation(assetId, assetTypeDTO, xIdempotencyKey);
    }
}

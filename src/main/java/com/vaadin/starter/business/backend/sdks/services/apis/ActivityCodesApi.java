package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.ActivityCodeDTO;
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
public class ActivityCodesApi {
    private ApiClient apiClient;

    public ActivityCodesApi() {
        this(new ApiClient());
    }

    @Autowired
    public ActivityCodesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Activity Code
     * Create a new activity code.
     * <p><b>200</b> - Activity code created successfully
     * <p><b>400</b> - Invalid input data
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createActivityCodeRequestCreation(ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = activityCodeDTO;
        // verify the required parameter 'activityCodeDTO' is set
        if (activityCodeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'activityCodeDTO' when calling createActivityCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/activity-codes", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Activity Code
     * Create a new activity code.
     * <p><b>200</b> - Activity code created successfully
     * <p><b>400</b> - Invalid input data
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ActivityCodeDTO> createActivityCode(ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return createActivityCodeRequestCreation(activityCodeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Activity Code
     * Create a new activity code.
     * <p><b>200</b> - Activity code created successfully
     * <p><b>400</b> - Invalid input data
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;ActivityCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ActivityCodeDTO>> createActivityCodeWithHttpInfo(ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return createActivityCodeRequestCreation(activityCodeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Activity Code
     * Create a new activity code.
     * <p><b>200</b> - Activity code created successfully
     * <p><b>400</b> - Invalid input data
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createActivityCodeWithResponseSpec(ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createActivityCodeRequestCreation(activityCodeDTO, xIdempotencyKey);
    }
    /**
     * Delete Activity Code
     * Delete a specific activity code by its ID.
     * <p><b>204</b> - Activity code deleted successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteActivityCodeRequestCreation(Long activityCodeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'activityCodeId' is set
        if (activityCodeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'activityCodeId' when calling deleteActivityCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("activityCodeId", activityCodeId);

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
        return apiClient.invokeAPI("/api/v1/activity-codes/{activityCodeId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Activity Code
     * Delete a specific activity code by its ID.
     * <p><b>204</b> - Activity code deleted successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteActivityCode(Long activityCodeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteActivityCodeRequestCreation(activityCodeId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Activity Code
     * Delete a specific activity code by its ID.
     * <p><b>204</b> - Activity code deleted successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteActivityCodeWithHttpInfo(Long activityCodeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteActivityCodeRequestCreation(activityCodeId).toEntity(localVarReturnType);
    }

    /**
     * Delete Activity Code
     * Delete a specific activity code by its ID.
     * <p><b>204</b> - Activity code deleted successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteActivityCodeWithResponseSpec(Long activityCodeId) throws WebClientResponseException {
        return deleteActivityCodeRequestCreation(activityCodeId);
    }
    /**
     * Get Activity Code by ID
     * Retrieve a specific activity code by its ID.
     * <p><b>200</b> - Activity code retrieved successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getActivityCodeRequestCreation(Long activityCodeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'activityCodeId' is set
        if (activityCodeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'activityCodeId' when calling getActivityCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("activityCodeId", activityCodeId);

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

        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/activity-codes/{activityCodeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Activity Code by ID
     * Retrieve a specific activity code by its ID.
     * <p><b>200</b> - Activity code retrieved successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ActivityCodeDTO> getActivityCode(Long activityCodeId) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return getActivityCodeRequestCreation(activityCodeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Activity Code by ID
     * Retrieve a specific activity code by its ID.
     * <p><b>200</b> - Activity code retrieved successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @return ResponseEntity&lt;ActivityCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ActivityCodeDTO>> getActivityCodeWithHttpInfo(Long activityCodeId) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return getActivityCodeRequestCreation(activityCodeId).toEntity(localVarReturnType);
    }

    /**
     * Get Activity Code by ID
     * Retrieve a specific activity code by its ID.
     * <p><b>200</b> - Activity code retrieved successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getActivityCodeWithResponseSpec(Long activityCodeId) throws WebClientResponseException {
        return getActivityCodeRequestCreation(activityCodeId);
    }
    /**
     * Get Activity Codes by Country
     * Retrieve all activity codes for a specific country.
     * <p><b>200</b> - Successfully retrieved activity codes for the country
     * @param countryId ID of the country
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getActivityCodesByCountryRequestCreation(Long countryId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'countryId' is set
        if (countryId == null) {
            throw new WebClientResponseException("Missing the required parameter 'countryId' when calling getActivityCodesByCountry", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("countryId", countryId);

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

        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/activity-codes/country/{countryId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Activity Codes by Country
     * Retrieve all activity codes for a specific country.
     * <p><b>200</b> - Successfully retrieved activity codes for the country
     * @param countryId ID of the country
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ActivityCodeDTO> getActivityCodesByCountry(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return getActivityCodesByCountryRequestCreation(countryId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Activity Codes by Country
     * Retrieve all activity codes for a specific country.
     * <p><b>200</b> - Successfully retrieved activity codes for the country
     * @param countryId ID of the country
     * @return ResponseEntity&lt;ActivityCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ActivityCodeDTO>> getActivityCodesByCountryWithHttpInfo(Long countryId) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return getActivityCodesByCountryRequestCreation(countryId).toEntity(localVarReturnType);
    }

    /**
     * Get Activity Codes by Country
     * Retrieve all activity codes for a specific country.
     * <p><b>200</b> - Successfully retrieved activity codes for the country
     * @param countryId ID of the country
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getActivityCodesByCountryWithResponseSpec(Long countryId) throws WebClientResponseException {
        return getActivityCodesByCountryRequestCreation(countryId);
    }
    /**
     * Get Child Activity Codes
     * Retrieve all child activity codes for a specific parent activity code.
     * <p><b>200</b> - Successfully retrieved child activity codes
     * @param parentCodeId ID of the parent activity code
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getChildActivityCodesRequestCreation(Long parentCodeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'parentCodeId' is set
        if (parentCodeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'parentCodeId' when calling getChildActivityCodes", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("parentCodeId", parentCodeId);

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

        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/activity-codes/parent/{parentCodeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Child Activity Codes
     * Retrieve all child activity codes for a specific parent activity code.
     * <p><b>200</b> - Successfully retrieved child activity codes
     * @param parentCodeId ID of the parent activity code
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ActivityCodeDTO> getChildActivityCodes(Long parentCodeId) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return getChildActivityCodesRequestCreation(parentCodeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Child Activity Codes
     * Retrieve all child activity codes for a specific parent activity code.
     * <p><b>200</b> - Successfully retrieved child activity codes
     * @param parentCodeId ID of the parent activity code
     * @return ResponseEntity&lt;ActivityCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ActivityCodeDTO>> getChildActivityCodesWithHttpInfo(Long parentCodeId) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return getChildActivityCodesRequestCreation(parentCodeId).toEntity(localVarReturnType);
    }

    /**
     * Get Child Activity Codes
     * Retrieve all child activity codes for a specific parent activity code.
     * <p><b>200</b> - Successfully retrieved child activity codes
     * @param parentCodeId ID of the parent activity code
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getChildActivityCodesWithResponseSpec(Long parentCodeId) throws WebClientResponseException {
        return getChildActivityCodesRequestCreation(parentCodeId);
    }
    /**
     * List Activity Codes
     * Retrieve a paginated list of activity codes.
     * <p><b>200</b> - Successfully retrieved list of activity codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listActivityCodesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/activity-codes", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Activity Codes
     * Retrieve a paginated list of activity codes.
     * <p><b>200</b> - Successfully retrieved list of activity codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listActivityCodes(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listActivityCodesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Activity Codes
     * Retrieve a paginated list of activity codes.
     * <p><b>200</b> - Successfully retrieved list of activity codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listActivityCodesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listActivityCodesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Activity Codes
     * Retrieve a paginated list of activity codes.
     * <p><b>200</b> - Successfully retrieved list of activity codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listActivityCodesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listActivityCodesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Activity Code
     * Update an existing activity code by its ID.
     * <p><b>200</b> - Activity code updated successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateActivityCodeRequestCreation(Long activityCodeId, ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = activityCodeDTO;
        // verify the required parameter 'activityCodeId' is set
        if (activityCodeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'activityCodeId' when calling updateActivityCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'activityCodeDTO' is set
        if (activityCodeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'activityCodeDTO' when calling updateActivityCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("activityCodeId", activityCodeId);

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

        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/activity-codes/{activityCodeId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Activity Code
     * Update an existing activity code by its ID.
     * <p><b>200</b> - Activity code updated successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ActivityCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ActivityCodeDTO> updateActivityCode(Long activityCodeId, ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return updateActivityCodeRequestCreation(activityCodeId, activityCodeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Activity Code
     * Update an existing activity code by its ID.
     * <p><b>200</b> - Activity code updated successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;ActivityCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ActivityCodeDTO>> updateActivityCodeWithHttpInfo(Long activityCodeId, ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ActivityCodeDTO> localVarReturnType = new ParameterizedTypeReference<ActivityCodeDTO>() {};
        return updateActivityCodeRequestCreation(activityCodeId, activityCodeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Activity Code
     * Update an existing activity code by its ID.
     * <p><b>200</b> - Activity code updated successfully
     * <p><b>404</b> - Activity code not found
     * @param activityCodeId ID of the activity code
     * @param activityCodeDTO The activityCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateActivityCodeWithResponseSpec(Long activityCodeId, ActivityCodeDTO activityCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateActivityCodeRequestCreation(activityCodeId, activityCodeDTO, xIdempotencyKey);
    }
}

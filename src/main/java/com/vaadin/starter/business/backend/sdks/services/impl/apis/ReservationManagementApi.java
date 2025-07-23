package com.vaadin.starter.business.backend.sdks.services.impl.apis;

import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.FilterRequestInventoryReservationDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.InventoryReservationDTO;
import com.catalis.core.erp.inventory.mgmt.sdk.model.PaginationResponse;
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

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2025-07-23T10:06:26.160650600+02:00[Europe/Madrid]")
public class ReservationManagementApi {
    private ApiClient apiClient;

    public ReservationManagementApi() {
        this(new ApiClient());
    }

    @Autowired
    public ReservationManagementApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create reservation
     * Create a new inventory reservation
     * <p><b>201</b> - Reservation created successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryReservationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createReservationRequestCreation(InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryReservationDTO;
        // verify the required parameter 'inventoryReservationDTO' is set
        if (inventoryReservationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryReservationDTO' when calling createReservation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return apiClient.invokeAPI("/api/v1/reservations", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create reservation
     * Create a new inventory reservation
     * <p><b>201</b> - Reservation created successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryReservationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryReservationDTO> createReservation(InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return createReservationRequestCreation(inventoryReservationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create reservation
     * Create a new inventory reservation
     * <p><b>201</b> - Reservation created successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryReservationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryReservationDTO>> createReservationWithHttpInfo(InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return createReservationRequestCreation(inventoryReservationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create reservation
     * Create a new inventory reservation
     * <p><b>201</b> - Reservation created successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>500</b> - Internal server error
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createReservationWithResponseSpec(InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createReservationRequestCreation(inventoryReservationDTO, xIdempotencyKey);
    }
    /**
     * Delete reservation
     * Delete a reservation by its ID
     * <p><b>204</b> - Reservation deleted successfully
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteReservationRequestCreation(Long reservationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'reservationId' is set
        if (reservationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'reservationId' when calling deleteReservation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("reservationId", reservationId);

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
        return apiClient.invokeAPI("/api/v1/reservations/{reservationId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete reservation
     * Delete a reservation by its ID
     * <p><b>204</b> - Reservation deleted successfully
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteReservation(Long reservationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteReservationRequestCreation(reservationId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete reservation
     * Delete a reservation by its ID
     * <p><b>204</b> - Reservation deleted successfully
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to delete
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteReservationWithHttpInfo(Long reservationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteReservationRequestCreation(reservationId).toEntity(localVarReturnType);
    }

    /**
     * Delete reservation
     * Delete a reservation by its ID
     * <p><b>204</b> - Reservation deleted successfully
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to delete
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteReservationWithResponseSpec(Long reservationId) throws WebClientResponseException {
        return deleteReservationRequestCreation(reservationId);
    }
    /**
     * Filter reservations
     * Filter reservations based on specified criteria
     * <p><b>200</b> - Successfully retrieved reservations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryReservationDTO The filterRequestInventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec filterReservationsRequestCreation(FilterRequestInventoryReservationDTO filterRequestInventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = filterRequestInventoryReservationDTO;
        // verify the required parameter 'filterRequestInventoryReservationDTO' is set
        if (filterRequestInventoryReservationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'filterRequestInventoryReservationDTO' when calling filterReservations", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return apiClient.invokeAPI("/api/v1/reservations/filter", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Filter reservations
     * Filter reservations based on specified criteria
     * <p><b>200</b> - Successfully retrieved reservations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryReservationDTO The filterRequestInventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> filterReservations(FilterRequestInventoryReservationDTO filterRequestInventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterReservationsRequestCreation(filterRequestInventoryReservationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Filter reservations
     * Filter reservations based on specified criteria
     * <p><b>200</b> - Successfully retrieved reservations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryReservationDTO The filterRequestInventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> filterReservationsWithHttpInfo(FilterRequestInventoryReservationDTO filterRequestInventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return filterReservationsRequestCreation(filterRequestInventoryReservationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Filter reservations
     * Filter reservations based on specified criteria
     * <p><b>200</b> - Successfully retrieved reservations
     * <p><b>400</b> - Invalid filter criteria provided
     * <p><b>500</b> - Internal server error
     * @param filterRequestInventoryReservationDTO The filterRequestInventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec filterReservationsWithResponseSpec(FilterRequestInventoryReservationDTO filterRequestInventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return filterReservationsRequestCreation(filterRequestInventoryReservationDTO, xIdempotencyKey);
    }
    /**
     * Get reservation by ID
     * Retrieve a reservation by its ID
     * <p><b>200</b> - Successfully retrieved reservation
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to retrieve
     * @return InventoryReservationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getReservationByIdRequestCreation(Long reservationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'reservationId' is set
        if (reservationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'reservationId' when calling getReservationById", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("reservationId", reservationId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return apiClient.invokeAPI("/api/v1/reservations/{reservationId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get reservation by ID
     * Retrieve a reservation by its ID
     * <p><b>200</b> - Successfully retrieved reservation
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to retrieve
     * @return InventoryReservationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryReservationDTO> getReservationById(Long reservationId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return getReservationByIdRequestCreation(reservationId).bodyToMono(localVarReturnType);
    }

    /**
     * Get reservation by ID
     * Retrieve a reservation by its ID
     * <p><b>200</b> - Successfully retrieved reservation
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to retrieve
     * @return ResponseEntity&lt;InventoryReservationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryReservationDTO>> getReservationByIdWithHttpInfo(Long reservationId) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return getReservationByIdRequestCreation(reservationId).toEntity(localVarReturnType);
    }

    /**
     * Get reservation by ID
     * Retrieve a reservation by its ID
     * <p><b>200</b> - Successfully retrieved reservation
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to retrieve
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getReservationByIdWithResponseSpec(Long reservationId) throws WebClientResponseException {
        return getReservationByIdRequestCreation(reservationId);
    }
    /**
     * Update reservation
     * Update an existing reservation
     * <p><b>200</b> - Reservation updated successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to update
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryReservationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateReservationRequestCreation(Long reservationId, InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = inventoryReservationDTO;
        // verify the required parameter 'reservationId' is set
        if (reservationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'reservationId' when calling updateReservation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'inventoryReservationDTO' is set
        if (inventoryReservationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'inventoryReservationDTO' when calling updateReservation", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("reservationId", reservationId);

        final MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<String, String>();
        final HttpHeaders headerParams = new HttpHeaders();
        final MultiValueMap<String, String> cookieParams = new LinkedMultiValueMap<String, String>();
        final MultiValueMap<String, Object> formParams = new LinkedMultiValueMap<String, Object>();

        if (xIdempotencyKey != null)
        headerParams.add("X-Idempotency-Key", apiClient.parameterToString(xIdempotencyKey));
        final String[] localVarAccepts = { 
            "*/*"
        };
        final List<MediaType> localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        final String[] localVarContentTypes = { 
            "application/json"
        };
        final MediaType localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return apiClient.invokeAPI("/api/v1/reservations/{reservationId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update reservation
     * Update an existing reservation
     * <p><b>200</b> - Reservation updated successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to update
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return InventoryReservationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<InventoryReservationDTO> updateReservation(Long reservationId, InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return updateReservationRequestCreation(reservationId, inventoryReservationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update reservation
     * Update an existing reservation
     * <p><b>200</b> - Reservation updated successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to update
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;InventoryReservationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<InventoryReservationDTO>> updateReservationWithHttpInfo(Long reservationId, InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<InventoryReservationDTO> localVarReturnType = new ParameterizedTypeReference<InventoryReservationDTO>() {};
        return updateReservationRequestCreation(reservationId, inventoryReservationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update reservation
     * Update an existing reservation
     * <p><b>200</b> - Reservation updated successfully
     * <p><b>400</b> - Invalid reservation data provided
     * <p><b>404</b> - Reservation not found
     * <p><b>500</b> - Internal server error
     * @param reservationId ID of the reservation to update
     * @param inventoryReservationDTO The inventoryReservationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateReservationWithResponseSpec(Long reservationId, InventoryReservationDTO inventoryReservationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateReservationRequestCreation(reservationId, inventoryReservationDTO, xIdempotencyKey);
    }
}

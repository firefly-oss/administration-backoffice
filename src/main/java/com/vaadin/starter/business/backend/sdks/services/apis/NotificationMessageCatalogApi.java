package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.NotificationMessageCatalogDTO;
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
public class NotificationMessageCatalogApi {
    private ApiClient apiClient;

    public NotificationMessageCatalogApi() {
        this(new ApiClient());
    }

    @Autowired
    public NotificationMessageCatalogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Notification Message
     * Create a new notification message.
     * <p><b>200</b> - Notification message created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createNotificationMessageRequestCreation(NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = notificationMessageCatalogDTO;
        // verify the required parameter 'notificationMessageCatalogDTO' is set
        if (notificationMessageCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'notificationMessageCatalogDTO' when calling createNotificationMessage", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-messages", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Notification Message
     * Create a new notification message.
     * <p><b>200</b> - Notification message created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageCatalogDTO> createNotificationMessage(NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return createNotificationMessageRequestCreation(notificationMessageCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Notification Message
     * Create a new notification message.
     * <p><b>200</b> - Notification message created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;NotificationMessageCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageCatalogDTO>> createNotificationMessageWithHttpInfo(NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return createNotificationMessageRequestCreation(notificationMessageCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Notification Message
     * Create a new notification message.
     * <p><b>200</b> - Notification message created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createNotificationMessageWithResponseSpec(NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createNotificationMessageRequestCreation(notificationMessageCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Notification Message
     * Delete a specific notification message by its ID.
     * <p><b>204</b> - Notification message deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteNotificationMessageRequestCreation(Long messageId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling deleteNotificationMessage", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("messageId", messageId);

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
        return apiClient.invokeAPI("/api/v1/notification-messages/{messageId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Notification Message
     * Delete a specific notification message by its ID.
     * <p><b>204</b> - Notification message deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteNotificationMessage(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteNotificationMessageRequestCreation(messageId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Notification Message
     * Delete a specific notification message by its ID.
     * <p><b>204</b> - Notification message deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteNotificationMessageWithHttpInfo(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteNotificationMessageRequestCreation(messageId).toEntity(localVarReturnType);
    }

    /**
     * Delete Notification Message
     * Delete a specific notification message by its ID.
     * <p><b>204</b> - Notification message deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteNotificationMessageWithResponseSpec(Long messageId) throws WebClientResponseException {
        return deleteNotificationMessageRequestCreation(messageId);
    }
    /**
     * Get Notification Message by ID
     * Retrieve a specific notification message by its ID.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getNotificationMessageRequestCreation(Long messageId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling getNotificationMessage", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("messageId", messageId);

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

        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-messages/{messageId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Notification Message by ID
     * Retrieve a specific notification message by its ID.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageCatalogDTO> getNotificationMessage(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return getNotificationMessageRequestCreation(messageId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Notification Message by ID
     * Retrieve a specific notification message by its ID.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @return ResponseEntity&lt;NotificationMessageCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageCatalogDTO>> getNotificationMessageWithHttpInfo(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return getNotificationMessageRequestCreation(messageId).toEntity(localVarReturnType);
    }

    /**
     * Get Notification Message by ID
     * Retrieve a specific notification message by its ID.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getNotificationMessageWithResponseSpec(Long messageId) throws WebClientResponseException {
        return getNotificationMessageRequestCreation(messageId);
    }
    /**
     * Get Notification Message by Code
     * Retrieve a specific notification message by its code.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageCode Code of the notification message
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getNotificationMessageByCodeRequestCreation(String messageCode) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageCode' is set
        if (messageCode == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageCode' when calling getNotificationMessageByCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("messageCode", messageCode);

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

        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-messages/code/{messageCode}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Notification Message by Code
     * Retrieve a specific notification message by its code.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageCode Code of the notification message
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageCatalogDTO> getNotificationMessageByCode(String messageCode) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return getNotificationMessageByCodeRequestCreation(messageCode).bodyToMono(localVarReturnType);
    }

    /**
     * Get Notification Message by Code
     * Retrieve a specific notification message by its code.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageCode Code of the notification message
     * @return ResponseEntity&lt;NotificationMessageCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageCatalogDTO>> getNotificationMessageByCodeWithHttpInfo(String messageCode) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return getNotificationMessageByCodeRequestCreation(messageCode).toEntity(localVarReturnType);
    }

    /**
     * Get Notification Message by Code
     * Retrieve a specific notification message by its code.
     * <p><b>200</b> - Notification message retrieved successfully
     * <p><b>404</b> - Notification message not found
     * @param messageCode Code of the notification message
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getNotificationMessageByCodeWithResponseSpec(String messageCode) throws WebClientResponseException {
        return getNotificationMessageByCodeRequestCreation(messageCode);
    }
    /**
     * List Notification Messages
     * Retrieve a paginated list of notification messages.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listNotificationMessagesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/notification-messages", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Notification Messages
     * Retrieve a paginated list of notification messages.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listNotificationMessages(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessagesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Notification Messages
     * Retrieve a paginated list of notification messages.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listNotificationMessagesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessagesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Notification Messages
     * Retrieve a paginated list of notification messages.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listNotificationMessagesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listNotificationMessagesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Notification Messages by Event Type
     * Retrieve a paginated list of notification messages for a specific event type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param eventType Event type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listNotificationMessagesByEventTypeRequestCreation(String eventType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'eventType' is set
        if (eventType == null) {
            throw new WebClientResponseException("Missing the required parameter 'eventType' when calling listNotificationMessagesByEventType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("eventType", eventType);

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
        return apiClient.invokeAPI("/api/v1/notification-messages/event-type/{eventType}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Notification Messages by Event Type
     * Retrieve a paginated list of notification messages for a specific event type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param eventType Event type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listNotificationMessagesByEventType(String eventType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessagesByEventTypeRequestCreation(eventType, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Notification Messages by Event Type
     * Retrieve a paginated list of notification messages for a specific event type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param eventType Event type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listNotificationMessagesByEventTypeWithHttpInfo(String eventType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessagesByEventTypeRequestCreation(eventType, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Notification Messages by Event Type
     * Retrieve a paginated list of notification messages for a specific event type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param eventType Event type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listNotificationMessagesByEventTypeWithResponseSpec(String eventType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listNotificationMessagesByEventTypeRequestCreation(eventType, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Notification Messages by Message Type
     * Retrieve a paginated list of notification messages for a specific message type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param typeId Message type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listNotificationMessagesByTypeIdRequestCreation(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling listNotificationMessagesByTypeId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/notification-messages/message-type/{typeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Notification Messages by Message Type
     * Retrieve a paginated list of notification messages for a specific message type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param typeId Message type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listNotificationMessagesByTypeId(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessagesByTypeIdRequestCreation(typeId, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Notification Messages by Message Type
     * Retrieve a paginated list of notification messages for a specific message type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param typeId Message type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listNotificationMessagesByTypeIdWithHttpInfo(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessagesByTypeIdRequestCreation(typeId, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Notification Messages by Message Type
     * Retrieve a paginated list of notification messages for a specific message type.
     * <p><b>200</b> - Successfully retrieved list of notification messages
     * @param typeId Message type ID
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listNotificationMessagesByTypeIdWithResponseSpec(Long typeId, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listNotificationMessagesByTypeIdRequestCreation(typeId, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Notification Message
     * Update a specific notification message by its ID.
     * <p><b>200</b> - Notification message updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateNotificationMessageRequestCreation(Long messageId, NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = notificationMessageCatalogDTO;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling updateNotificationMessage", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'notificationMessageCatalogDTO' is set
        if (notificationMessageCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'notificationMessageCatalogDTO' when calling updateNotificationMessage", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("messageId", messageId);

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

        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-messages/{messageId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Notification Message
     * Update a specific notification message by its ID.
     * <p><b>200</b> - Notification message updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageCatalogDTO> updateNotificationMessage(Long messageId, NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return updateNotificationMessageRequestCreation(messageId, notificationMessageCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Notification Message
     * Update a specific notification message by its ID.
     * <p><b>200</b> - Notification message updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;NotificationMessageCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageCatalogDTO>> updateNotificationMessageWithHttpInfo(Long messageId, NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageCatalogDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageCatalogDTO>() {};
        return updateNotificationMessageRequestCreation(messageId, notificationMessageCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Notification Message
     * Update a specific notification message by its ID.
     * <p><b>200</b> - Notification message updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @param notificationMessageCatalogDTO The notificationMessageCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateNotificationMessageWithResponseSpec(Long messageId, NotificationMessageCatalogDTO notificationMessageCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateNotificationMessageRequestCreation(messageId, notificationMessageCatalogDTO, xIdempotencyKey);
    }
}

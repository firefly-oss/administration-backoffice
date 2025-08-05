package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.NotificationMessageTemplateDTO;
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
public class NotificationMessageTemplatesApi {
    private ApiClient apiClient;

    public NotificationMessageTemplatesApi() {
        this(new ApiClient());
    }

    @Autowired
    public NotificationMessageTemplatesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Notification Message Template
     * Create a new notification message template.
     * <p><b>200</b> - Notification message template created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createNotificationMessageTemplateRequestCreation(NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = notificationMessageTemplateDTO;
        // verify the required parameter 'notificationMessageTemplateDTO' is set
        if (notificationMessageTemplateDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'notificationMessageTemplateDTO' when calling createNotificationMessageTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-templates", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Notification Message Template
     * Create a new notification message template.
     * <p><b>200</b> - Notification message template created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageTemplateDTO> createNotificationMessageTemplate(NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return createNotificationMessageTemplateRequestCreation(notificationMessageTemplateDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Notification Message Template
     * Create a new notification message template.
     * <p><b>200</b> - Notification message template created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;NotificationMessageTemplateDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageTemplateDTO>> createNotificationMessageTemplateWithHttpInfo(NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return createNotificationMessageTemplateRequestCreation(notificationMessageTemplateDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Notification Message Template
     * Create a new notification message template.
     * <p><b>200</b> - Notification message template created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createNotificationMessageTemplateWithResponseSpec(NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createNotificationMessageTemplateRequestCreation(notificationMessageTemplateDTO, xIdempotencyKey);
    }
    /**
     * Delete Notification Message Template
     * Delete a specific notification message template by its ID.
     * <p><b>204</b> - Notification message template deleted successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteNotificationMessageTemplateRequestCreation(Long templateId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling deleteNotificationMessageTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/notification-templates/{templateId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Notification Message Template
     * Delete a specific notification message template by its ID.
     * <p><b>204</b> - Notification message template deleted successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteNotificationMessageTemplate(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteNotificationMessageTemplateRequestCreation(templateId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Notification Message Template
     * Delete a specific notification message template by its ID.
     * <p><b>204</b> - Notification message template deleted successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteNotificationMessageTemplateWithHttpInfo(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteNotificationMessageTemplateRequestCreation(templateId).toEntity(localVarReturnType);
    }

    /**
     * Delete Notification Message Template
     * Delete a specific notification message template by its ID.
     * <p><b>204</b> - Notification message template deleted successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteNotificationMessageTemplateWithResponseSpec(Long templateId) throws WebClientResponseException {
        return deleteNotificationMessageTemplateRequestCreation(templateId);
    }
    /**
     * Delete Templates by Message ID
     * Delete all templates for a specific notification message.
     * <p><b>204</b> - Templates deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteTemplatesByMessageIdRequestCreation(Long messageId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling deleteTemplatesByMessageId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/notification-templates/message/{messageId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Templates by Message ID
     * Delete all templates for a specific notification message.
     * <p><b>204</b> - Templates deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteTemplatesByMessageId(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTemplatesByMessageIdRequestCreation(messageId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Templates by Message ID
     * Delete all templates for a specific notification message.
     * <p><b>204</b> - Templates deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteTemplatesByMessageIdWithHttpInfo(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteTemplatesByMessageIdRequestCreation(messageId).toEntity(localVarReturnType);
    }

    /**
     * Delete Templates by Message ID
     * Delete all templates for a specific notification message.
     * <p><b>204</b> - Templates deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteTemplatesByMessageIdWithResponseSpec(Long messageId) throws WebClientResponseException {
        return deleteTemplatesByMessageIdRequestCreation(messageId);
    }
    /**
     * Get Notification Message Template by ID
     * Retrieve a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getNotificationMessageTemplateRequestCreation(Long templateId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling getNotificationMessageTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-templates/{templateId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Notification Message Template by ID
     * Retrieve a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageTemplateDTO> getNotificationMessageTemplate(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return getNotificationMessageTemplateRequestCreation(templateId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Notification Message Template by ID
     * Retrieve a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @return ResponseEntity&lt;NotificationMessageTemplateDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageTemplateDTO>> getNotificationMessageTemplateWithHttpInfo(Long templateId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return getNotificationMessageTemplateRequestCreation(templateId).toEntity(localVarReturnType);
    }

    /**
     * Get Notification Message Template by ID
     * Retrieve a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getNotificationMessageTemplateWithResponseSpec(Long templateId) throws WebClientResponseException {
        return getNotificationMessageTemplateRequestCreation(templateId);
    }
    /**
     * Get Notification Message Template by Name and Message ID
     * Retrieve a specific notification message template by its name and message ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param messageId ID of the notification message
     * @param templateName Name of the template
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getNotificationMessageTemplateByNameAndMessageIdRequestCreation(Long messageId, String templateName) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling getNotificationMessageTemplateByNameAndMessageId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'templateName' is set
        if (templateName == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateName' when calling getNotificationMessageTemplateByNameAndMessageId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("messageId", messageId);
        pathParams.put("templateName", templateName);

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

        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-templates/message/{messageId}/name/{templateName}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Notification Message Template by Name and Message ID
     * Retrieve a specific notification message template by its name and message ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param messageId ID of the notification message
     * @param templateName Name of the template
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageTemplateDTO> getNotificationMessageTemplateByNameAndMessageId(Long messageId, String templateName) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return getNotificationMessageTemplateByNameAndMessageIdRequestCreation(messageId, templateName).bodyToMono(localVarReturnType);
    }

    /**
     * Get Notification Message Template by Name and Message ID
     * Retrieve a specific notification message template by its name and message ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param messageId ID of the notification message
     * @param templateName Name of the template
     * @return ResponseEntity&lt;NotificationMessageTemplateDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageTemplateDTO>> getNotificationMessageTemplateByNameAndMessageIdWithHttpInfo(Long messageId, String templateName) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return getNotificationMessageTemplateByNameAndMessageIdRequestCreation(messageId, templateName).toEntity(localVarReturnType);
    }

    /**
     * Get Notification Message Template by Name and Message ID
     * Retrieve a specific notification message template by its name and message ID.
     * <p><b>200</b> - Notification message template retrieved successfully
     * <p><b>404</b> - Notification message template not found
     * @param messageId ID of the notification message
     * @param templateName Name of the template
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getNotificationMessageTemplateByNameAndMessageIdWithResponseSpec(Long messageId, String templateName) throws WebClientResponseException {
        return getNotificationMessageTemplateByNameAndMessageIdRequestCreation(messageId, templateName);
    }
    /**
     * Get Templates by Message ID
     * Retrieve all templates for a specific notification message.
     * <p><b>200</b> - Successfully retrieved templates
     * @param messageId ID of the notification message
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getTemplatesByMessageIdRequestCreation(Long messageId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling getTemplatesByMessageId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-templates/message/{messageId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Templates by Message ID
     * Retrieve all templates for a specific notification message.
     * <p><b>200</b> - Successfully retrieved templates
     * @param messageId ID of the notification message
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageTemplateDTO> getTemplatesByMessageId(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return getTemplatesByMessageIdRequestCreation(messageId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Templates by Message ID
     * Retrieve all templates for a specific notification message.
     * <p><b>200</b> - Successfully retrieved templates
     * @param messageId ID of the notification message
     * @return ResponseEntity&lt;NotificationMessageTemplateDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageTemplateDTO>> getTemplatesByMessageIdWithHttpInfo(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return getTemplatesByMessageIdRequestCreation(messageId).toEntity(localVarReturnType);
    }

    /**
     * Get Templates by Message ID
     * Retrieve all templates for a specific notification message.
     * <p><b>200</b> - Successfully retrieved templates
     * @param messageId ID of the notification message
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getTemplatesByMessageIdWithResponseSpec(Long messageId) throws WebClientResponseException {
        return getTemplatesByMessageIdRequestCreation(messageId);
    }
    /**
     * List Notification Message Templates
     * Retrieve a paginated list of notification message templates.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listNotificationMessageTemplatesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/notification-templates", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Notification Message Templates
     * Retrieve a paginated list of notification message templates.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listNotificationMessageTemplates(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessageTemplatesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Notification Message Templates
     * Retrieve a paginated list of notification message templates.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listNotificationMessageTemplatesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessageTemplatesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Notification Message Templates
     * Retrieve a paginated list of notification message templates.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listNotificationMessageTemplatesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listNotificationMessageTemplatesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * List Notification Message Templates by Type
     * Retrieve a paginated list of notification message templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param templateType Template type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listNotificationMessageTemplatesByTypeRequestCreation(String templateType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'templateType' is set
        if (templateType == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateType' when calling listNotificationMessageTemplatesByType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("templateType", templateType);

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
        return apiClient.invokeAPI("/api/v1/notification-templates/type/{templateType}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Notification Message Templates by Type
     * Retrieve a paginated list of notification message templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param templateType Template type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listNotificationMessageTemplatesByType(String templateType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessageTemplatesByTypeRequestCreation(templateType, pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Notification Message Templates by Type
     * Retrieve a paginated list of notification message templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param templateType Template type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listNotificationMessageTemplatesByTypeWithHttpInfo(String templateType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listNotificationMessageTemplatesByTypeRequestCreation(templateType, pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Notification Message Templates by Type
     * Retrieve a paginated list of notification message templates for a specific template type.
     * <p><b>200</b> - Successfully retrieved list of notification message templates
     * @param templateType Template type
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listNotificationMessageTemplatesByTypeWithResponseSpec(String templateType, String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listNotificationMessageTemplatesByTypeRequestCreation(templateType, pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Notification Message Template
     * Update a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateNotificationMessageTemplateRequestCreation(Long templateId, NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = notificationMessageTemplateDTO;
        // verify the required parameter 'templateId' is set
        if (templateId == null) {
            throw new WebClientResponseException("Missing the required parameter 'templateId' when calling updateNotificationMessageTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'notificationMessageTemplateDTO' is set
        if (notificationMessageTemplateDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'notificationMessageTemplateDTO' when calling updateNotificationMessageTemplate", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-templates/{templateId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Notification Message Template
     * Update a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageTemplateDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageTemplateDTO> updateNotificationMessageTemplate(Long templateId, NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return updateNotificationMessageTemplateRequestCreation(templateId, notificationMessageTemplateDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Notification Message Template
     * Update a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;NotificationMessageTemplateDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageTemplateDTO>> updateNotificationMessageTemplateWithHttpInfo(Long templateId, NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageTemplateDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageTemplateDTO>() {};
        return updateNotificationMessageTemplateRequestCreation(templateId, notificationMessageTemplateDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Notification Message Template
     * Update a specific notification message template by its ID.
     * <p><b>200</b> - Notification message template updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message template not found
     * @param templateId ID of the notification message template
     * @param notificationMessageTemplateDTO The notificationMessageTemplateDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateNotificationMessageTemplateWithResponseSpec(Long templateId, NotificationMessageTemplateDTO notificationMessageTemplateDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateNotificationMessageTemplateRequestCreation(templateId, notificationMessageTemplateDTO, xIdempotencyKey);
    }
}

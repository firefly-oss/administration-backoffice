package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.NotificationMessageLocalizationDTO;
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
public class NotificationMessageLocalizationsApi {
    private ApiClient apiClient;

    public NotificationMessageLocalizationsApi() {
        this(new ApiClient());
    }

    @Autowired
    public NotificationMessageLocalizationsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Notification Message Localization
     * Create a new notification message localization.
     * <p><b>200</b> - Notification message localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createNotificationMessageLocalizationRequestCreation(NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = notificationMessageLocalizationDTO;
        // verify the required parameter 'notificationMessageLocalizationDTO' is set
        if (notificationMessageLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'notificationMessageLocalizationDTO' when calling createNotificationMessageLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-localizations", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Notification Message Localization
     * Create a new notification message localization.
     * <p><b>200</b> - Notification message localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageLocalizationDTO> createNotificationMessageLocalization(NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return createNotificationMessageLocalizationRequestCreation(notificationMessageLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Notification Message Localization
     * Create a new notification message localization.
     * <p><b>200</b> - Notification message localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;NotificationMessageLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageLocalizationDTO>> createNotificationMessageLocalizationWithHttpInfo(NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return createNotificationMessageLocalizationRequestCreation(notificationMessageLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Notification Message Localization
     * Create a new notification message localization.
     * <p><b>200</b> - Notification message localization created successfully
     * <p><b>400</b> - Invalid input data
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createNotificationMessageLocalizationWithResponseSpec(NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createNotificationMessageLocalizationRequestCreation(notificationMessageLocalizationDTO, xIdempotencyKey);
    }
    /**
     * Delete Localizations by Message ID
     * Delete all localizations for a specific notification message.
     * <p><b>204</b> - Localizations deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteLocalizationsByMessageIdRequestCreation(Long messageId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling deleteLocalizationsByMessageId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/notification-localizations/message/{messageId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Localizations by Message ID
     * Delete all localizations for a specific notification message.
     * <p><b>204</b> - Localizations deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteLocalizationsByMessageId(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLocalizationsByMessageIdRequestCreation(messageId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Localizations by Message ID
     * Delete all localizations for a specific notification message.
     * <p><b>204</b> - Localizations deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteLocalizationsByMessageIdWithHttpInfo(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteLocalizationsByMessageIdRequestCreation(messageId).toEntity(localVarReturnType);
    }

    /**
     * Delete Localizations by Message ID
     * Delete all localizations for a specific notification message.
     * <p><b>204</b> - Localizations deleted successfully
     * <p><b>404</b> - Notification message not found
     * @param messageId ID of the notification message
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteLocalizationsByMessageIdWithResponseSpec(Long messageId) throws WebClientResponseException {
        return deleteLocalizationsByMessageIdRequestCreation(messageId);
    }
    /**
     * Delete Notification Message Localization
     * Delete a specific notification message localization by its ID.
     * <p><b>204</b> - Notification message localization deleted successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteNotificationMessageLocalizationRequestCreation(Long localizationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling deleteNotificationMessageLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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
        return apiClient.invokeAPI("/api/v1/notification-localizations/{localizationId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Notification Message Localization
     * Delete a specific notification message localization by its ID.
     * <p><b>204</b> - Notification message localization deleted successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteNotificationMessageLocalization(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteNotificationMessageLocalizationRequestCreation(localizationId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Notification Message Localization
     * Delete a specific notification message localization by its ID.
     * <p><b>204</b> - Notification message localization deleted successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteNotificationMessageLocalizationWithHttpInfo(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteNotificationMessageLocalizationRequestCreation(localizationId).toEntity(localVarReturnType);
    }

    /**
     * Delete Notification Message Localization
     * Delete a specific notification message localization by its ID.
     * <p><b>204</b> - Notification message localization deleted successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteNotificationMessageLocalizationWithResponseSpec(Long localizationId) throws WebClientResponseException {
        return deleteNotificationMessageLocalizationRequestCreation(localizationId);
    }
    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param localeId ID of the language locale
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLocalizationsByLocaleIdRequestCreation(Long localeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localeId' is set
        if (localeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localeId' when calling getLocalizationsByLocaleId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-localizations/locale/{localeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param localeId ID of the language locale
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageLocalizationDTO> getLocalizationsByLocaleId(Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getLocalizationsByLocaleIdRequestCreation(localeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param localeId ID of the language locale
     * @return ResponseEntity&lt;NotificationMessageLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageLocalizationDTO>> getLocalizationsByLocaleIdWithHttpInfo(Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getLocalizationsByLocaleIdRequestCreation(localeId).toEntity(localVarReturnType);
    }

    /**
     * Get Localizations by Locale ID
     * Retrieve all localizations for a specific locale.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param localeId ID of the language locale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLocalizationsByLocaleIdWithResponseSpec(Long localeId) throws WebClientResponseException {
        return getLocalizationsByLocaleIdRequestCreation(localeId);
    }
    /**
     * Get Localizations by Message ID
     * Retrieve all localizations for a specific notification message.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param messageId ID of the notification message
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getLocalizationsByMessageIdRequestCreation(Long messageId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling getLocalizationsByMessageId", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-localizations/message/{messageId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Localizations by Message ID
     * Retrieve all localizations for a specific notification message.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param messageId ID of the notification message
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageLocalizationDTO> getLocalizationsByMessageId(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getLocalizationsByMessageIdRequestCreation(messageId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Localizations by Message ID
     * Retrieve all localizations for a specific notification message.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param messageId ID of the notification message
     * @return ResponseEntity&lt;NotificationMessageLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageLocalizationDTO>> getLocalizationsByMessageIdWithHttpInfo(Long messageId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getLocalizationsByMessageIdRequestCreation(messageId).toEntity(localVarReturnType);
    }

    /**
     * Get Localizations by Message ID
     * Retrieve all localizations for a specific notification message.
     * <p><b>200</b> - Successfully retrieved localizations
     * @param messageId ID of the notification message
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getLocalizationsByMessageIdWithResponseSpec(Long messageId) throws WebClientResponseException {
        return getLocalizationsByMessageIdRequestCreation(messageId);
    }
    /**
     * Get Notification Message Localization by ID
     * Retrieve a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getNotificationMessageLocalizationRequestCreation(Long localizationId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling getNotificationMessageLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-localizations/{localizationId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Notification Message Localization by ID
     * Retrieve a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageLocalizationDTO> getNotificationMessageLocalization(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getNotificationMessageLocalizationRequestCreation(localizationId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Notification Message Localization by ID
     * Retrieve a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @return ResponseEntity&lt;NotificationMessageLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageLocalizationDTO>> getNotificationMessageLocalizationWithHttpInfo(Long localizationId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getNotificationMessageLocalizationRequestCreation(localizationId).toEntity(localVarReturnType);
    }

    /**
     * Get Notification Message Localization by ID
     * Retrieve a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getNotificationMessageLocalizationWithResponseSpec(Long localizationId) throws WebClientResponseException {
        return getNotificationMessageLocalizationRequestCreation(localizationId);
    }
    /**
     * Get Notification Message Localization by Message and Locale
     * Retrieve a specific notification message localization by message ID and locale ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param messageId ID of the notification message
     * @param localeId ID of the language locale
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getNotificationMessageLocalizationByMessageAndLocaleRequestCreation(Long messageId, Long localeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'messageId' is set
        if (messageId == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageId' when calling getNotificationMessageLocalizationByMessageAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'localeId' is set
        if (localeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localeId' when calling getNotificationMessageLocalizationByMessageAndLocale", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("messageId", messageId);
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

        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-localizations/message/{messageId}/locale/{localeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Notification Message Localization by Message and Locale
     * Retrieve a specific notification message localization by message ID and locale ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param messageId ID of the notification message
     * @param localeId ID of the language locale
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageLocalizationDTO> getNotificationMessageLocalizationByMessageAndLocale(Long messageId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getNotificationMessageLocalizationByMessageAndLocaleRequestCreation(messageId, localeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Notification Message Localization by Message and Locale
     * Retrieve a specific notification message localization by message ID and locale ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param messageId ID of the notification message
     * @param localeId ID of the language locale
     * @return ResponseEntity&lt;NotificationMessageLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageLocalizationDTO>> getNotificationMessageLocalizationByMessageAndLocaleWithHttpInfo(Long messageId, Long localeId) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return getNotificationMessageLocalizationByMessageAndLocaleRequestCreation(messageId, localeId).toEntity(localVarReturnType);
    }

    /**
     * Get Notification Message Localization by Message and Locale
     * Retrieve a specific notification message localization by message ID and locale ID.
     * <p><b>200</b> - Notification message localization retrieved successfully
     * <p><b>404</b> - Notification message localization not found
     * @param messageId ID of the notification message
     * @param localeId ID of the language locale
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getNotificationMessageLocalizationByMessageAndLocaleWithResponseSpec(Long messageId, Long localeId) throws WebClientResponseException {
        return getNotificationMessageLocalizationByMessageAndLocaleRequestCreation(messageId, localeId);
    }
    /**
     * Update Notification Message Localization
     * Update a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateNotificationMessageLocalizationRequestCreation(Long localizationId, NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = notificationMessageLocalizationDTO;
        // verify the required parameter 'localizationId' is set
        if (localizationId == null) {
            throw new WebClientResponseException("Missing the required parameter 'localizationId' when calling updateNotificationMessageLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'notificationMessageLocalizationDTO' is set
        if (notificationMessageLocalizationDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'notificationMessageLocalizationDTO' when calling updateNotificationMessageLocalization", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return apiClient.invokeAPI("/api/v1/notification-localizations/{localizationId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Notification Message Localization
     * Update a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return NotificationMessageLocalizationDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<NotificationMessageLocalizationDTO> updateNotificationMessageLocalization(Long localizationId, NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return updateNotificationMessageLocalizationRequestCreation(localizationId, notificationMessageLocalizationDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Notification Message Localization
     * Update a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;NotificationMessageLocalizationDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<NotificationMessageLocalizationDTO>> updateNotificationMessageLocalizationWithHttpInfo(Long localizationId, NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<NotificationMessageLocalizationDTO> localVarReturnType = new ParameterizedTypeReference<NotificationMessageLocalizationDTO>() {};
        return updateNotificationMessageLocalizationRequestCreation(localizationId, notificationMessageLocalizationDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Notification Message Localization
     * Update a specific notification message localization by its ID.
     * <p><b>200</b> - Notification message localization updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Notification message localization not found
     * @param localizationId ID of the notification message localization
     * @param notificationMessageLocalizationDTO The notificationMessageLocalizationDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateNotificationMessageLocalizationWithResponseSpec(Long localizationId, NotificationMessageLocalizationDTO notificationMessageLocalizationDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateNotificationMessageLocalizationRequestCreation(localizationId, notificationMessageLocalizationDTO, xIdempotencyKey);
    }
}

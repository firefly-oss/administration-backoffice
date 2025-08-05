package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.MessageTypeCatalogDTO;
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
public class MessageTypeCatalogApi {
    private ApiClient apiClient;

    public MessageTypeCatalogApi() {
        this(new ApiClient());
    }

    @Autowired
    public MessageTypeCatalogApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Message Type
     * Create a new message type.
     * <p><b>200</b> - Message type created successfully
     * <p><b>400</b> - Invalid input data
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createMessageTypeRequestCreation(MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = messageTypeCatalogDTO;
        // verify the required parameter 'messageTypeCatalogDTO' is set
        if (messageTypeCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageTypeCatalogDTO' when calling createMessageType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/message-types", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Message Type
     * Create a new message type.
     * <p><b>200</b> - Message type created successfully
     * <p><b>400</b> - Invalid input data
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<MessageTypeCatalogDTO> createMessageType(MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return createMessageTypeRequestCreation(messageTypeCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Message Type
     * Create a new message type.
     * <p><b>200</b> - Message type created successfully
     * <p><b>400</b> - Invalid input data
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;MessageTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<MessageTypeCatalogDTO>> createMessageTypeWithHttpInfo(MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return createMessageTypeRequestCreation(messageTypeCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Message Type
     * Create a new message type.
     * <p><b>200</b> - Message type created successfully
     * <p><b>400</b> - Invalid input data
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createMessageTypeWithResponseSpec(MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createMessageTypeRequestCreation(messageTypeCatalogDTO, xIdempotencyKey);
    }
    /**
     * Delete Message Type
     * Delete a specific message type by its ID.
     * <p><b>204</b> - Message type deleted successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteMessageTypeRequestCreation(Long typeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling deleteMessageType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeId", typeId);

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
        return apiClient.invokeAPI("/api/v1/message-types/{typeId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Message Type
     * Delete a specific message type by its ID.
     * <p><b>204</b> - Message type deleted successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteMessageType(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteMessageTypeRequestCreation(typeId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Message Type
     * Delete a specific message type by its ID.
     * <p><b>204</b> - Message type deleted successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteMessageTypeWithHttpInfo(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteMessageTypeRequestCreation(typeId).toEntity(localVarReturnType);
    }

    /**
     * Delete Message Type
     * Delete a specific message type by its ID.
     * <p><b>204</b> - Message type deleted successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteMessageTypeWithResponseSpec(Long typeId) throws WebClientResponseException {
        return deleteMessageTypeRequestCreation(typeId);
    }
    /**
     * Get Message Type by ID
     * Retrieve a specific message type by its ID.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getMessageTypeRequestCreation(Long typeId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling getMessageType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeId", typeId);

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

        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/message-types/{typeId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Message Type by ID
     * Retrieve a specific message type by its ID.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<MessageTypeCatalogDTO> getMessageType(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return getMessageTypeRequestCreation(typeId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Message Type by ID
     * Retrieve a specific message type by its ID.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @return ResponseEntity&lt;MessageTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<MessageTypeCatalogDTO>> getMessageTypeWithHttpInfo(Long typeId) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return getMessageTypeRequestCreation(typeId).toEntity(localVarReturnType);
    }

    /**
     * Get Message Type by ID
     * Retrieve a specific message type by its ID.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getMessageTypeWithResponseSpec(Long typeId) throws WebClientResponseException {
        return getMessageTypeRequestCreation(typeId);
    }
    /**
     * Get Message Type by Code
     * Retrieve a specific message type by its code.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeCode Code of the message type
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getMessageTypeByCodeRequestCreation(String typeCode) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'typeCode' is set
        if (typeCode == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeCode' when calling getMessageTypeByCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeCode", typeCode);

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

        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/message-types/code/{typeCode}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Message Type by Code
     * Retrieve a specific message type by its code.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeCode Code of the message type
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<MessageTypeCatalogDTO> getMessageTypeByCode(String typeCode) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return getMessageTypeByCodeRequestCreation(typeCode).bodyToMono(localVarReturnType);
    }

    /**
     * Get Message Type by Code
     * Retrieve a specific message type by its code.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeCode Code of the message type
     * @return ResponseEntity&lt;MessageTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<MessageTypeCatalogDTO>> getMessageTypeByCodeWithHttpInfo(String typeCode) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return getMessageTypeByCodeRequestCreation(typeCode).toEntity(localVarReturnType);
    }

    /**
     * Get Message Type by Code
     * Retrieve a specific message type by its code.
     * <p><b>200</b> - Message type retrieved successfully
     * <p><b>404</b> - Message type not found
     * @param typeCode Code of the message type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getMessageTypeByCodeWithResponseSpec(String typeCode) throws WebClientResponseException {
        return getMessageTypeByCodeRequestCreation(typeCode);
    }
    /**
     * List Message Types
     * Retrieve a paginated list of message types.
     * <p><b>200</b> - Successfully retrieved list of message types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listMessageTypesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/message-types", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Message Types
     * Retrieve a paginated list of message types.
     * <p><b>200</b> - Successfully retrieved list of message types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listMessageTypes(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listMessageTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Message Types
     * Retrieve a paginated list of message types.
     * <p><b>200</b> - Successfully retrieved list of message types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listMessageTypesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listMessageTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Message Types
     * Retrieve a paginated list of message types.
     * <p><b>200</b> - Successfully retrieved list of message types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listMessageTypesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listMessageTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Message Type
     * Update a specific message type by its ID.
     * <p><b>200</b> - Message type updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateMessageTypeRequestCreation(Long typeId, MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = messageTypeCatalogDTO;
        // verify the required parameter 'typeId' is set
        if (typeId == null) {
            throw new WebClientResponseException("Missing the required parameter 'typeId' when calling updateMessageType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'messageTypeCatalogDTO' is set
        if (messageTypeCatalogDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'messageTypeCatalogDTO' when calling updateMessageType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("typeId", typeId);

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

        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return apiClient.invokeAPI("/api/v1/message-types/{typeId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Message Type
     * Update a specific message type by its ID.
     * <p><b>200</b> - Message type updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return MessageTypeCatalogDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<MessageTypeCatalogDTO> updateMessageType(Long typeId, MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return updateMessageTypeRequestCreation(typeId, messageTypeCatalogDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Message Type
     * Update a specific message type by its ID.
     * <p><b>200</b> - Message type updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;MessageTypeCatalogDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<MessageTypeCatalogDTO>> updateMessageTypeWithHttpInfo(Long typeId, MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<MessageTypeCatalogDTO> localVarReturnType = new ParameterizedTypeReference<MessageTypeCatalogDTO>() {};
        return updateMessageTypeRequestCreation(typeId, messageTypeCatalogDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Message Type
     * Update a specific message type by its ID.
     * <p><b>200</b> - Message type updated successfully
     * <p><b>400</b> - Invalid input data
     * <p><b>404</b> - Message type not found
     * @param typeId ID of the message type
     * @param messageTypeCatalogDTO The messageTypeCatalogDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateMessageTypeWithResponseSpec(Long typeId, MessageTypeCatalogDTO messageTypeCatalogDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateMessageTypeRequestCreation(typeId, messageTypeCatalogDTO, xIdempotencyKey);
    }
}

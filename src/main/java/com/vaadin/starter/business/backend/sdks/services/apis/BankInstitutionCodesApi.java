package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.BankInstitutionCodeDTO;
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
public class BankInstitutionCodesApi {
    private ApiClient apiClient;

    public BankInstitutionCodesApi() {
        this(new ApiClient());
    }

    @Autowired
    public BankInstitutionCodesApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Bank Institution Code
     * Create a new bank or institution code.
     * <p><b>200</b> - Bank institution code created successfully
     * <p><b>400</b> - Invalid input data
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return BankInstitutionCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createBankInstitutionCodeRequestCreation(BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = bankInstitutionCodeDTO;
        // verify the required parameter 'bankInstitutionCodeDTO' is set
        if (bankInstitutionCodeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'bankInstitutionCodeDTO' when calling createBankInstitutionCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/bank-institution-codes", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Bank Institution Code
     * Create a new bank or institution code.
     * <p><b>200</b> - Bank institution code created successfully
     * <p><b>400</b> - Invalid input data
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return BankInstitutionCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<BankInstitutionCodeDTO> createBankInstitutionCode(BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return createBankInstitutionCodeRequestCreation(bankInstitutionCodeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Bank Institution Code
     * Create a new bank or institution code.
     * <p><b>200</b> - Bank institution code created successfully
     * <p><b>400</b> - Invalid input data
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;BankInstitutionCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<BankInstitutionCodeDTO>> createBankInstitutionCodeWithHttpInfo(BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return createBankInstitutionCodeRequestCreation(bankInstitutionCodeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Bank Institution Code
     * Create a new bank or institution code.
     * <p><b>200</b> - Bank institution code created successfully
     * <p><b>400</b> - Invalid input data
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createBankInstitutionCodeWithResponseSpec(BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createBankInstitutionCodeRequestCreation(bankInstitutionCodeDTO, xIdempotencyKey);
    }
    /**
     * Delete Bank Institution Code
     * Delete a specific bank or institution code by its ID.
     * <p><b>204</b> - Bank institution code deleted successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteBankInstitutionCodeRequestCreation(Long id) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling deleteBankInstitutionCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

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
        return apiClient.invokeAPI("/api/v1/bank-institution-codes/{id}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Bank Institution Code
     * Delete a specific bank or institution code by its ID.
     * <p><b>204</b> - Bank institution code deleted successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteBankInstitutionCode(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteBankInstitutionCodeRequestCreation(id).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Bank Institution Code
     * Delete a specific bank or institution code by its ID.
     * <p><b>204</b> - Bank institution code deleted successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteBankInstitutionCodeWithHttpInfo(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteBankInstitutionCodeRequestCreation(id).toEntity(localVarReturnType);
    }

    /**
     * Delete Bank Institution Code
     * Delete a specific bank or institution code by its ID.
     * <p><b>204</b> - Bank institution code deleted successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteBankInstitutionCodeWithResponseSpec(Long id) throws WebClientResponseException {
        return deleteBankInstitutionCodeRequestCreation(id);
    }
    /**
     * Get Bank Institution Code by ID
     * Retrieve a specific bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code retrieved successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @return BankInstitutionCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getBankInstitutionCodeRequestCreation(Long id) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling getBankInstitutionCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

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

        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/bank-institution-codes/{id}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Bank Institution Code by ID
     * Retrieve a specific bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code retrieved successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @return BankInstitutionCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<BankInstitutionCodeDTO> getBankInstitutionCode(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return getBankInstitutionCodeRequestCreation(id).bodyToMono(localVarReturnType);
    }

    /**
     * Get Bank Institution Code by ID
     * Retrieve a specific bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code retrieved successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @return ResponseEntity&lt;BankInstitutionCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<BankInstitutionCodeDTO>> getBankInstitutionCodeWithHttpInfo(Long id) throws WebClientResponseException {
        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return getBankInstitutionCodeRequestCreation(id).toEntity(localVarReturnType);
    }

    /**
     * Get Bank Institution Code by ID
     * Retrieve a specific bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code retrieved successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getBankInstitutionCodeWithResponseSpec(Long id) throws WebClientResponseException {
        return getBankInstitutionCodeRequestCreation(id);
    }
    /**
     * List Bank Institution Codes
     * Retrieve a paginated list of bank or institution codes.
     * <p><b>200</b> - Successfully retrieved list of bank/institution codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listBankInstitutionCodesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/bank-institution-codes", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Bank Institution Codes
     * Retrieve a paginated list of bank or institution codes.
     * <p><b>200</b> - Successfully retrieved list of bank/institution codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listBankInstitutionCodes(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listBankInstitutionCodesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Bank Institution Codes
     * Retrieve a paginated list of bank or institution codes.
     * <p><b>200</b> - Successfully retrieved list of bank/institution codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listBankInstitutionCodesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listBankInstitutionCodesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Bank Institution Codes
     * Retrieve a paginated list of bank or institution codes.
     * <p><b>200</b> - Successfully retrieved list of bank/institution codes
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listBankInstitutionCodesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listBankInstitutionCodesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Bank Institution Code
     * Update an existing bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code updated successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return BankInstitutionCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateBankInstitutionCodeRequestCreation(Long id, BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = bankInstitutionCodeDTO;
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new WebClientResponseException("Missing the required parameter 'id' when calling updateBankInstitutionCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'bankInstitutionCodeDTO' is set
        if (bankInstitutionCodeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'bankInstitutionCodeDTO' when calling updateBankInstitutionCode", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("id", id);

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

        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return apiClient.invokeAPI("/api/v1/bank-institution-codes/{id}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Bank Institution Code
     * Update an existing bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code updated successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return BankInstitutionCodeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<BankInstitutionCodeDTO> updateBankInstitutionCode(Long id, BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return updateBankInstitutionCodeRequestCreation(id, bankInstitutionCodeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Bank Institution Code
     * Update an existing bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code updated successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;BankInstitutionCodeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<BankInstitutionCodeDTO>> updateBankInstitutionCodeWithHttpInfo(Long id, BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<BankInstitutionCodeDTO> localVarReturnType = new ParameterizedTypeReference<BankInstitutionCodeDTO>() {};
        return updateBankInstitutionCodeRequestCreation(id, bankInstitutionCodeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Bank Institution Code
     * Update an existing bank or institution code by its ID.
     * <p><b>200</b> - Bank institution code updated successfully
     * <p><b>404</b> - Bank institution code not found
     * @param id ID of the bank institution code
     * @param bankInstitutionCodeDTO The bankInstitutionCodeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateBankInstitutionCodeWithResponseSpec(Long id, BankInstitutionCodeDTO bankInstitutionCodeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateBankInstitutionCodeRequestCreation(id, bankInstitutionCodeDTO, xIdempotencyKey);
    }
}

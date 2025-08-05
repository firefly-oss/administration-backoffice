package com.vaadin.starter.business.backend.sdks.services.apis;

import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.ContractTypeDTO;
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
public class ContractTypeApi {
    private ApiClient apiClient;

    public ContractTypeApi() {
        this(new ApiClient());
    }

    @Autowired
    public ContractTypeApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create Contract Type
     * Create a new contract type record.
     * <p><b>200</b> - Contract type created successfully
     * <p><b>400</b> - Invalid input data
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ContractTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec createContractTypeRequestCreation(ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = contractTypeDTO;
        // verify the required parameter 'contractTypeDTO' is set
        if (contractTypeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'contractTypeDTO' when calling createContractType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
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

        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return apiClient.invokeAPI("/api/v1/contract-types", HttpMethod.POST, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Create Contract Type
     * Create a new contract type record.
     * <p><b>200</b> - Contract type created successfully
     * <p><b>400</b> - Invalid input data
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ContractTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ContractTypeDTO> createContractType(ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return createContractTypeRequestCreation(contractTypeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Create Contract Type
     * Create a new contract type record.
     * <p><b>200</b> - Contract type created successfully
     * <p><b>400</b> - Invalid input data
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;ContractTypeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ContractTypeDTO>> createContractTypeWithHttpInfo(ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return createContractTypeRequestCreation(contractTypeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Create Contract Type
     * Create a new contract type record.
     * <p><b>200</b> - Contract type created successfully
     * <p><b>400</b> - Invalid input data
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec createContractTypeWithResponseSpec(ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return createContractTypeRequestCreation(contractTypeDTO, xIdempotencyKey);
    }
    /**
     * Delete Contract Type
     * Delete a specific contract type by its ID.
     * <p><b>204</b> - Contract type deleted successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec deleteContractTypeRequestCreation(Long contractId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'contractId' is set
        if (contractId == null) {
            throw new WebClientResponseException("Missing the required parameter 'contractId' when calling deleteContractType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("contractId", contractId);

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
        return apiClient.invokeAPI("/api/v1/contract-types/{contractId}", HttpMethod.DELETE, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Delete Contract Type
     * Delete a specific contract type by its ID.
     * <p><b>204</b> - Contract type deleted successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<Void> deleteContractType(Long contractId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteContractTypeRequestCreation(contractId).bodyToMono(localVarReturnType);
    }

    /**
     * Delete Contract Type
     * Delete a specific contract type by its ID.
     * <p><b>204</b> - Contract type deleted successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<Void>> deleteContractTypeWithHttpInfo(Long contractId) throws WebClientResponseException {
        ParameterizedTypeReference<Void> localVarReturnType = new ParameterizedTypeReference<Void>() {};
        return deleteContractTypeRequestCreation(contractId).toEntity(localVarReturnType);
    }

    /**
     * Delete Contract Type
     * Delete a specific contract type by its ID.
     * <p><b>204</b> - Contract type deleted successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec deleteContractTypeWithResponseSpec(Long contractId) throws WebClientResponseException {
        return deleteContractTypeRequestCreation(contractId);
    }
    /**
     * Get Contract Type by ID
     * Retrieve a specific contract type by its ID.
     * <p><b>200</b> - Contract type retrieved successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @return ContractTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec getContractTypeRequestCreation(Long contractId) throws WebClientResponseException {
        Object postBody = null;
        // verify the required parameter 'contractId' is set
        if (contractId == null) {
            throw new WebClientResponseException("Missing the required parameter 'contractId' when calling getContractType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("contractId", contractId);

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

        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return apiClient.invokeAPI("/api/v1/contract-types/{contractId}", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Get Contract Type by ID
     * Retrieve a specific contract type by its ID.
     * <p><b>200</b> - Contract type retrieved successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @return ContractTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ContractTypeDTO> getContractType(Long contractId) throws WebClientResponseException {
        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return getContractTypeRequestCreation(contractId).bodyToMono(localVarReturnType);
    }

    /**
     * Get Contract Type by ID
     * Retrieve a specific contract type by its ID.
     * <p><b>200</b> - Contract type retrieved successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @return ResponseEntity&lt;ContractTypeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ContractTypeDTO>> getContractTypeWithHttpInfo(Long contractId) throws WebClientResponseException {
        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return getContractTypeRequestCreation(contractId).toEntity(localVarReturnType);
    }

    /**
     * Get Contract Type by ID
     * Retrieve a specific contract type by its ID.
     * <p><b>200</b> - Contract type retrieved successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec getContractTypeWithResponseSpec(Long contractId) throws WebClientResponseException {
        return getContractTypeRequestCreation(contractId);
    }
    /**
     * List Contract Types
     * Retrieve a paginated list of contract types.
     * <p><b>200</b> - Successfully retrieved list of contract types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec listContractTypesRequestCreation(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
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
        return apiClient.invokeAPI("/api/v1/contract-types", HttpMethod.GET, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * List Contract Types
     * Retrieve a paginated list of contract types.
     * <p><b>200</b> - Successfully retrieved list of contract types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return PaginationResponse
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<PaginationResponse> listContractTypes(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listContractTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).bodyToMono(localVarReturnType);
    }

    /**
     * List Contract Types
     * Retrieve a paginated list of contract types.
     * <p><b>200</b> - Successfully retrieved list of contract types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseEntity&lt;PaginationResponse&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<PaginationResponse>> listContractTypesWithHttpInfo(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        ParameterizedTypeReference<PaginationResponse> localVarReturnType = new ParameterizedTypeReference<PaginationResponse>() {};
        return listContractTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection).toEntity(localVarReturnType);
    }

    /**
     * List Contract Types
     * Retrieve a paginated list of contract types.
     * <p><b>200</b> - Successfully retrieved list of contract types
     * @param pageNumber The zero-based page number to retrieve.
     * @param pageSize The number of items per page.
     * @param sortBy The field to sort the results by.
     * @param sortDirection The direction of sorting, either ASC or DESC.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec listContractTypesWithResponseSpec(String pageNumber, String pageSize, String sortBy, String sortDirection) throws WebClientResponseException {
        return listContractTypesRequestCreation(pageNumber, pageSize, sortBy, sortDirection);
    }
    /**
     * Update Contract Type
     * Update an existing contract type by its ID.
     * <p><b>200</b> - Contract type updated successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ContractTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    private ResponseSpec updateContractTypeRequestCreation(Long contractId, ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        Object postBody = contractTypeDTO;
        // verify the required parameter 'contractId' is set
        if (contractId == null) {
            throw new WebClientResponseException("Missing the required parameter 'contractId' when calling updateContractType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // verify the required parameter 'contractTypeDTO' is set
        if (contractTypeDTO == null) {
            throw new WebClientResponseException("Missing the required parameter 'contractTypeDTO' when calling updateContractType", HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), null, null, null);
        }
        // create path and map variables
        final Map<String, Object> pathParams = new HashMap<String, Object>();

        pathParams.put("contractId", contractId);

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

        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return apiClient.invokeAPI("/api/v1/contract-types/{contractId}", HttpMethod.PUT, pathParams, queryParams, postBody, headerParams, cookieParams, formParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    }

    /**
     * Update Contract Type
     * Update an existing contract type by its ID.
     * <p><b>200</b> - Contract type updated successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ContractTypeDTO
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ContractTypeDTO> updateContractType(Long contractId, ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return updateContractTypeRequestCreation(contractId, contractTypeDTO, xIdempotencyKey).bodyToMono(localVarReturnType);
    }

    /**
     * Update Contract Type
     * Update an existing contract type by its ID.
     * <p><b>200</b> - Contract type updated successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseEntity&lt;ContractTypeDTO&gt;
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public Mono<ResponseEntity<ContractTypeDTO>> updateContractTypeWithHttpInfo(Long contractId, ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        ParameterizedTypeReference<ContractTypeDTO> localVarReturnType = new ParameterizedTypeReference<ContractTypeDTO>() {};
        return updateContractTypeRequestCreation(contractId, contractTypeDTO, xIdempotencyKey).toEntity(localVarReturnType);
    }

    /**
     * Update Contract Type
     * Update an existing contract type by its ID.
     * <p><b>200</b> - Contract type updated successfully
     * <p><b>404</b> - Contract type not found
     * @param contractId ID of the contract type
     * @param contractTypeDTO The contractTypeDTO parameter
     * @param xIdempotencyKey Unique key for idempotent requests. If provided, ensures that identical requests with the same key will only be processed once.
     * @return ResponseSpec
     * @throws WebClientResponseException if an error occurs while attempting to invoke the API
     */
    public ResponseSpec updateContractTypeWithResponseSpec(Long contractId, ContractTypeDTO contractTypeDTO, String xIdempotencyKey) throws WebClientResponseException {
        return updateContractTypeRequestCreation(contractId, contractTypeDTO, xIdempotencyKey);
    }
}

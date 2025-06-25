package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.common.customer.sdk.api.PartyApi;
import com.catalis.common.customer.sdk.invoker.ApiClient;
import com.catalis.common.customer.sdk.model.PaginationResponse;
import com.vaadin.starter.business.backend.mapper.customers.CustomerRequestMapper;
import com.vaadin.starter.business.backend.sdks.services.CustomersService;
import com.vaadin.starter.business.backend.sdks.services.rest.LegalPersonRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.NaturalPersonRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class CustomersClient implements CustomersService {

    private final PartyApi partyApi;
    private final CustomerRequestMapper customerRequestMapper;

    @Autowired
    public CustomersClient(ApiClient apiClient, CustomerRequestMapper customerRequestMapper) {
        this.partyApi = new PartyApi(apiClient);
        this.customerRequestMapper = customerRequestMapper;
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterLegalPerson(LegalPersonRequest legalPersonRequest) {

        return partyApi.filterLegalPersonWithHttpInfo(customerRequestMapper.legalPersonRequestToDto(legalPersonRequest));
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterNaturalPerson(NaturalPersonRequest naturalPersonRequest) {

        return partyApi.filterNaturalPersonWithHttpInfo(customerRequestMapper.naturalPersonRequestToDto(naturalPersonRequest));
    }

}

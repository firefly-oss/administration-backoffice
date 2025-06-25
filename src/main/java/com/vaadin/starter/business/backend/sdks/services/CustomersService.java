package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.common.customer.sdk.model.PaginationResponse;
import com.vaadin.starter.business.backend.sdks.services.rest.LegalPersonRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.NaturalPersonRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


public interface CustomersService {

    Mono<ResponseEntity<PaginationResponse>> filterLegalPerson(LegalPersonRequest legalPersonRequest);
    Mono<ResponseEntity<PaginationResponse>> filterNaturalPerson(NaturalPersonRequest naturalPersonRequest);

}

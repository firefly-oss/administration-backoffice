package com.vaadin.starter.business.backend.mapper.customers;

import com.catalis.common.customer.sdk.model.FilterRequestLegalPersonDTO;
import com.catalis.common.customer.sdk.model.FilterRequestNaturalPersonDTO;
import com.vaadin.starter.business.backend.sdks.services.rest.LegalPersonRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.NaturalPersonRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for converting between request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    CustomerRequestMapper INSTANCE = Mappers.getMapper(CustomerRequestMapper.class);

    /**
     * Convert a LegalPersonRequest to a FilterRequestLegalPersonDTO.
     *
     * @param request the LegalPersonRequest to convert
     * @return the corresponding FilterRequestLegalPersonDTO
     */
    FilterRequestLegalPersonDTO legalPersonRequestToDto(LegalPersonRequest request);

    /**
     * Convert a FilterRequestLegalPersonDTO to a LegalPersonRequest.
     *
     * @param dto the FilterRequestLegalPersonDTO to convert
     * @return the corresponding LegalPersonRequest
     */
    LegalPersonRequest dtoToLegalPersonRequest(FilterRequestLegalPersonDTO dto);

    /**
     * Convert a NaturalPersonRequest to a FilterRequestNaturalPersonDTO.
     *
     * @param request the NaturalPersonRequest to convert
     * @return the corresponding FilterRequestNaturalPersonDTO
     */
    FilterRequestNaturalPersonDTO naturalPersonRequestToDto(NaturalPersonRequest request);

    /**
     * Convert a FilterRequestNaturalPersonDTO to a NaturalPersonRequest.
     *
     * @param dto the FilterRequestNaturalPersonDTO to convert
     * @return the corresponding NaturalPersonRequest
     */
    NaturalPersonRequest dtoToNaturalPersonRequest(FilterRequestNaturalPersonDTO dto);
}

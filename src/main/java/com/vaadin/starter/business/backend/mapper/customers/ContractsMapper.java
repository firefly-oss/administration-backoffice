package com.vaadin.starter.business.backend.mapper.customers;

import com.catalis.common.contract.sdk.model.ContractDTO;
import com.catalis.common.contract.sdk.model.ContractDocumentDTO;
import com.catalis.common.contract.sdk.model.ContractEventDTO;
import com.catalis.common.contract.sdk.model.ContractPartyDTO;
import com.catalis.common.contract.sdk.model.ContractRiskAssessmentDTO;
import com.catalis.common.contract.sdk.model.ContractStatusHistoryDTO;
import com.catalis.common.contract.sdk.model.ContractTermDTO;
import com.catalis.common.contract.sdk.model.FilterRequestContractDTO;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractDocumentRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractEventRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractFilterRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractPartyRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractRiskAssessmentRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractStatusHistoryRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.contracts.ContractTermRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for converting between contract request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface ContractsMapper {

    /**
     * Convert a ContractRequest to a ContractDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    ContractDTO contractRequestToDto(ContractRequest request);

    /**
     * Convert a ContractDTO to a ContractRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractRequest dtoToContractRequest(ContractDTO dto);

    /**
     * Convert a ContractFilterRequest to a FilterRequestContractDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    FilterRequestContractDTO contractFilterRequestToDto(ContractFilterRequest request);

    /**
     * Convert a FilterRequestContractDTO to a ContractFilterRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractFilterRequest dtoToContractFilterRequest(FilterRequestContractDTO dto);

    /**
     * Convert a ContractDocumentRequest to a ContractDocumentDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    ContractDocumentDTO contractDocumentRequestToDto(ContractDocumentRequest request);

    /**
     * Convert a ContractDocumentDTO to a ContractDocumentRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractDocumentRequest dtoToContractDocumentRequest(ContractDocumentDTO dto);

    /**
     * Convert a ContractEventRequest to a ContractEventDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    ContractEventDTO contractEventRequestToDto(ContractEventRequest request);

    /**
     * Convert a ContractEventDTO to a ContractEventRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractEventRequest dtoToContractEventRequest(ContractEventDTO dto);

    /**
     * Convert a ContractPartyRequest to a ContractPartyDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    ContractPartyDTO contractPartyRequestToDto(ContractPartyRequest request);

    /**
     * Convert a ContractPartyDTO to a ContractPartyRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractPartyRequest dtoToContractPartyRequest(ContractPartyDTO dto);

    /**
     * Convert a ContractRiskAssessmentRequest to a ContractRiskAssessmentDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    ContractRiskAssessmentDTO contractRiskAssessmentRequestToDto(ContractRiskAssessmentRequest request);

    /**
     * Convert a ContractRiskAssessmentDTO to a ContractRiskAssessmentRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractRiskAssessmentRequest dtoToContractRiskAssessmentRequest(ContractRiskAssessmentDTO dto);

    /**
     * Convert a ContractTermRequest to a ContractTermDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    ContractTermDTO contractTermRequestToDto(ContractTermRequest request);

    /**
     * Convert a ContractTermDTO to a ContractTermRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractTermRequest dtoToContractTermRequest(ContractTermDTO dto);

    /**
     * Convert a ContractStatusHistoryRequest to a ContractStatusHistoryDTO.
     *
     * @param request the request to convert
     * @return the converted DTO
     */
    ContractStatusHistoryDTO contractStatusHistoryRequestToDto(ContractStatusHistoryRequest request);

    /**
     * Convert a ContractStatusHistoryDTO to a ContractStatusHistoryRequest.
     *
     * @param dto the DTO to convert
     * @return the converted request
     */
    ContractStatusHistoryRequest dtoToContractStatusHistoryRequest(ContractStatusHistoryDTO dto);
}

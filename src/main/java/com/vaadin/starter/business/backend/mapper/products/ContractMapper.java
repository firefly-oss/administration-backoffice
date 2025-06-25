package com.vaadin.starter.business.backend.mapper.products;

import com.vaadin.starter.business.dummy.Contract;
import com.vaadin.starter.business.backend.dto.products.ContractDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Contract and ContractDTO objects.
 */
@Component
public class ContractMapper {

    /**
     * Convert a Contract entity to a ContractDTO.
     *
     * @param contract the Contract entity to convert
     * @return the corresponding ContractDTO
     */
    public ContractDTO toDto(Contract contract) {
        if (contract == null) {
            return null;
        }
        
        return new ContractDTO(
            contract.getId(),
            contract.getName(),
            contract.getType(),
            contract.getClient(),
            contract.getStatus(),
            contract.getStartDate(),
            contract.getEndDate(),
            contract.getAssignedTo(),
            contract.getDocumentUrl()
        );
    }

    /**
     * Convert a ContractDTO to a Contract entity.
     *
     * @param dto the ContractDTO to convert
     * @return the corresponding Contract entity
     */
    public Contract toEntity(ContractDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Contract(
            dto.getId(),
            dto.getName(),
            dto.getType(),
            dto.getClient(),
            dto.getStatus(),
            dto.getStartDate(),
            dto.getEndDate(),
            dto.getAssignedTo(),
            dto.getDocumentUrl()
        );
    }

    /**
     * Convert a collection of Contract entities to a list of ContractDTOs.
     *
     * @param contracts the collection of Contract entities to convert
     * @return a list of corresponding ContractDTOs
     */
    public List<ContractDTO> toDtoList(Collection<Contract> contracts) {
        if (contracts == null) {
            return List.of();
        }
        
        return contracts.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of ContractDTOs to a list of Contract entities.
     *
     * @param dtos the collection of ContractDTOs to convert
     * @return a list of corresponding Contract entities
     */
    public List<Contract> toEntityList(Collection<ContractDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
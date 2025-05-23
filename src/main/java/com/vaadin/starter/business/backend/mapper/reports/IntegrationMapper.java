package com.vaadin.starter.business.backend.mapper.reports;

import com.vaadin.starter.business.backend.Integration;
import com.vaadin.starter.business.backend.dto.reports.IntegrationDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Integration and IntegrationDTO objects.
 */
@Component
public class IntegrationMapper {

    /**
     * Convert an Integration entity to an IntegrationDTO.
     *
     * @param integration the Integration entity to convert
     * @return the corresponding IntegrationDTO
     */
    public IntegrationDTO toDto(Integration integration) {
        if (integration == null) {
            return null;
        }
        
        return new IntegrationDTO(
            integration.getId(),
            integration.getName(),
            integration.getDescription(),
            integration.getStatus(),
            integration.getType(),
            integration.getEndpoint(),
            integration.getDataFormat(),
            integration.getSchedule(),
            integration.isSecureTransfer(),
            integration.isRequireAuthentication(),
            integration.getConfiguredBy(),
            integration.getLastSync()
        );
    }

    /**
     * Convert an IntegrationDTO to an Integration entity.
     *
     * @param dto the IntegrationDTO to convert
     * @return the corresponding Integration entity
     */
    public Integration toEntity(IntegrationDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Integration(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getType(),
            dto.getEndpoint(),
            dto.getDataFormat(),
            dto.getSchedule(),
            dto.isSecureTransfer(),
            dto.isRequireAuthentication(),
            dto.getConfiguredBy(),
            dto.getLastSync()
        );
    }

    /**
     * Convert a collection of Integration entities to a list of IntegrationDTOs.
     *
     * @param integrations the collection of Integration entities to convert
     * @return a list of corresponding IntegrationDTOs
     */
    public List<IntegrationDTO> toDtoList(Collection<Integration> integrations) {
        if (integrations == null) {
            return List.of();
        }
        
        return integrations.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of IntegrationDTOs to a list of Integration entities.
     *
     * @param dtos the collection of IntegrationDTOs to convert
     * @return a list of corresponding Integration entities
     */
    public List<Integration> toEntityList(Collection<IntegrationDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
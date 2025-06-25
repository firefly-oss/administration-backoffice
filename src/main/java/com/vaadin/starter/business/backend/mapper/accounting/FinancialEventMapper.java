package com.vaadin.starter.business.backend.mapper.accounting;

import com.vaadin.starter.business.dummy.FinancialEvent;
import com.vaadin.starter.business.backend.dto.accounting.FinancialEventDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between FinancialEvent and FinancialEventDTO objects.
 */
@Component
public class FinancialEventMapper {

    /**
     * Convert a FinancialEvent entity to a FinancialEventDTO.
     *
     * @param event the FinancialEvent entity to convert
     * @return the corresponding FinancialEventDTO
     */
    public FinancialEventDTO toDto(FinancialEvent event) {
        if (event == null) {
            return null;
        }
        
        return new FinancialEventDTO(
            event.getId(),
            event.getTitle(),
            event.getDescription(),
            event.getDate(),
            event.getCategory(),
            event.getAmount(),
            event.getStatus(),
            event.getAssignedTo()
        );
    }

    /**
     * Convert a FinancialEventDTO to a FinancialEvent entity.
     *
     * @param dto the FinancialEventDTO to convert
     * @return the corresponding FinancialEvent entity
     */
    public FinancialEvent toEntity(FinancialEventDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new FinancialEvent(
            dto.getId(),
            dto.getTitle(),
            dto.getDescription(),
            dto.getDate(),
            dto.getCategory(),
            dto.getAmount(),
            dto.getStatus(),
            dto.getAssignedTo()
        );
    }

    /**
     * Convert a collection of FinancialEvent entities to a list of FinancialEventDTOs.
     *
     * @param events the collection of FinancialEvent entities to convert
     * @return a list of corresponding FinancialEventDTOs
     */
    public List<FinancialEventDTO> toDtoList(Collection<FinancialEvent> events) {
        if (events == null) {
            return List.of();
        }
        
        return events.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of FinancialEventDTOs to a list of FinancialEvent entities.
     *
     * @param dtos the collection of FinancialEventDTOs to convert
     * @return a list of corresponding FinancialEvent entities
     */
    public List<FinancialEvent> toEntityList(Collection<FinancialEventDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
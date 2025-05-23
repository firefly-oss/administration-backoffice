package com.vaadin.starter.business.backend.mapper.customers;

import com.vaadin.starter.business.backend.OnboardingProcess;
import com.vaadin.starter.business.backend.dto.customers.OnboardingProcessDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between OnboardingProcess and OnboardingProcessDTO objects.
 */
@Component
public class OnboardingProcessMapper {

    /**
     * Convert an OnboardingProcess entity to an OnboardingProcessDTO.
     *
     * @param process the OnboardingProcess entity to convert
     * @return the corresponding OnboardingProcessDTO
     */
    public OnboardingProcessDTO toDto(OnboardingProcess process) {
        if (process == null) {
            return null;
        }
        
        return new OnboardingProcessDTO(
            process.getId(),
            process.getName(),
            process.getDescription(),
            process.getAccountType(),
            process.getStepCount(),
            process.getCompletedSteps(),
            process.getStatus(),
            process.getAssignedTo(),
            process.getCreatedDate(),
            process.getLastModified()
        );
    }

    /**
     * Convert an OnboardingProcessDTO to an OnboardingProcess entity.
     *
     * @param dto the OnboardingProcessDTO to convert
     * @return the corresponding OnboardingProcess entity
     */
    public OnboardingProcess toEntity(OnboardingProcessDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new OnboardingProcess(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getAccountType(),
            dto.getStepCount(),
            dto.getCompletedSteps(),
            dto.getStatus(),
            dto.getAssignedTo(),
            dto.getCreatedDate(),
            dto.getLastModified()
        );
    }

    /**
     * Convert a collection of OnboardingProcess entities to a list of OnboardingProcessDTOs.
     *
     * @param processes the collection of OnboardingProcess entities to convert
     * @return a list of corresponding OnboardingProcessDTOs
     */
    public List<OnboardingProcessDTO> toDtoList(Collection<OnboardingProcess> processes) {
        if (processes == null) {
            return List.of();
        }
        
        return processes.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of OnboardingProcessDTOs to a list of OnboardingProcess entities.
     *
     * @param dtos the collection of OnboardingProcessDTOs to convert
     * @return a list of corresponding OnboardingProcess entities
     */
    public List<OnboardingProcess> toEntityList(Collection<OnboardingProcessDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
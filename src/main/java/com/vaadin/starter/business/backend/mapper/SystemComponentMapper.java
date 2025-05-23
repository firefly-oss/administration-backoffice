package com.vaadin.starter.business.backend.mapper;

import com.vaadin.starter.business.backend.SystemComponent;
import com.vaadin.starter.business.backend.dto.SystemComponentDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between SystemComponent and SystemComponentDTO objects.
 */
@Component
public class SystemComponentMapper {

    /**
     * Convert a SystemComponent entity to a SystemComponentDTO.
     *
     * @param systemComponent the SystemComponent entity to convert
     * @return the corresponding SystemComponentDTO
     */
    public SystemComponentDTO toDto(SystemComponent systemComponent) {
        if (systemComponent == null) {
            return null;
        }
        
        return new SystemComponentDTO(
            systemComponent.getId(),
            systemComponent.getComponentName(),
            systemComponent.getServerName(),
            systemComponent.getDescription(),
            systemComponent.getStatus(),
            systemComponent.getCpuUsage(),
            systemComponent.getMemoryUsage(),
            systemComponent.getDiskUsage(),
            systemComponent.getKeyMetric(),
            systemComponent.getLastChecked()
        );
    }

    /**
     * Convert a SystemComponentDTO to a SystemComponent entity.
     *
     * @param dto the SystemComponentDTO to convert
     * @return the corresponding SystemComponent entity
     */
    public SystemComponent toEntity(SystemComponentDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new SystemComponent(
            dto.getId(),
            dto.getComponentName(),
            dto.getServerName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getCpuUsage(),
            dto.getMemoryUsage(),
            dto.getDiskUsage(),
            dto.getKeyMetric(),
            dto.getLastChecked()
        );
    }

    /**
     * Convert a collection of SystemComponent entities to a list of SystemComponentDTOs.
     *
     * @param systemComponents the collection of SystemComponent entities to convert
     * @return a list of corresponding SystemComponentDTOs
     */
    public List<SystemComponentDTO> toDtoList(Collection<SystemComponent> systemComponents) {
        if (systemComponents == null) {
            return List.of();
        }
        
        return systemComponents.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of SystemComponentDTOs to a list of SystemComponent entities.
     *
     * @param dtos the collection of SystemComponentDTOs to convert
     * @return a list of corresponding SystemComponent entities
     */
    public List<SystemComponent> toEntityList(Collection<SystemComponentDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
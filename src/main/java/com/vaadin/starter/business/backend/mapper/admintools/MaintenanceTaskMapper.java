package com.vaadin.starter.business.backend.mapper.admintools;

import com.vaadin.starter.business.backend.MaintenanceTask;
import com.vaadin.starter.business.backend.dto.admintools.MaintenanceTaskDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between MaintenanceTask and MaintenanceTaskDTO objects.
 */
@Component
public class MaintenanceTaskMapper {

    /**
     * Convert a MaintenanceTask entity to a MaintenanceTaskDTO.
     *
     * @param maintenanceTask the MaintenanceTask entity to convert
     * @return the corresponding MaintenanceTaskDTO
     */
    public MaintenanceTaskDTO toDto(MaintenanceTask maintenanceTask) {
        if (maintenanceTask == null) {
            return null;
        }
        
        return new MaintenanceTaskDTO(
            maintenanceTask.getId(),
            maintenanceTask.getTaskName(),
            maintenanceTask.getDescription(),
            maintenanceTask.getStatus(),
            maintenanceTask.getDatabase(),
            maintenanceTask.getFrequency(),
            maintenanceTask.getScheduledTime(),
            maintenanceTask.getDuration(),
            maintenanceTask.getLastRun(),
            maintenanceTask.getManagedBy()
        );
    }

    /**
     * Convert a MaintenanceTaskDTO to a MaintenanceTask entity.
     *
     * @param dto the MaintenanceTaskDTO to convert
     * @return the corresponding MaintenanceTask entity
     */
    public MaintenanceTask toEntity(MaintenanceTaskDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new MaintenanceTask(
            dto.getId(),
            dto.getTaskName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getDatabase(),
            dto.getFrequency(),
            dto.getScheduledTime(),
            dto.getDuration(),
            dto.getLastRun(),
            dto.getManagedBy()
        );
    }

    /**
     * Convert a collection of MaintenanceTask entities to a list of MaintenanceTaskDTOs.
     *
     * @param maintenanceTasks the collection of MaintenanceTask entities to convert
     * @return a list of corresponding MaintenanceTaskDTOs
     */
    public List<MaintenanceTaskDTO> toDtoList(Collection<MaintenanceTask> maintenanceTasks) {
        if (maintenanceTasks == null) {
            return List.of();
        }
        
        return maintenanceTasks.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of MaintenanceTaskDTOs to a list of MaintenanceTask entities.
     *
     * @param dtos the collection of MaintenanceTaskDTOs to convert
     * @return a list of corresponding MaintenanceTask entities
     */
    public List<MaintenanceTask> toEntityList(Collection<MaintenanceTaskDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
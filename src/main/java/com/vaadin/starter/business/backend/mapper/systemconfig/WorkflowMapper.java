/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.vaadin.starter.business.backend.mapper.systemconfig;

import com.vaadin.starter.business.backend.Workflow;
import com.vaadin.starter.business.backend.dto.systemconfig.WorkflowDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Workflow and WorkflowDTO objects.
 */
@Component
public class WorkflowMapper {

    /**
     * Convert a Workflow entity to a WorkflowDTO.
     *
     * @param workflow the Workflow entity to convert
     * @return the corresponding WorkflowDTO
     */
    public WorkflowDTO toDto(Workflow workflow) {
        if (workflow == null) {
            return null;
        }
        
        return new WorkflowDTO(
            workflow.getId(),
            workflow.getName(),
            workflow.getDescription(),
            workflow.getStatus(),
            workflow.getCategory(),
            workflow.getStepsCount(),
            workflow.getRoles(),
            workflow.getLastModified()
        );
    }

    /**
     * Convert a WorkflowDTO to a Workflow entity.
     *
     * @param dto the WorkflowDTO to convert
     * @return the corresponding Workflow entity
     */
    public Workflow toEntity(WorkflowDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Workflow(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getCategory(),
            dto.getStepsCount(),
            dto.getRoles(),
            dto.getLastModified()
        );
    }

    /**
     * Convert a collection of Workflow entities to a list of WorkflowDTOs.
     *
     * @param workflows the collection of Workflow entities to convert
     * @return a list of corresponding WorkflowDTOs
     */
    public List<WorkflowDTO> toDtoList(Collection<Workflow> workflows) {
        if (workflows == null) {
            return List.of();
        }
        
        return workflows.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of WorkflowDTOs to a list of Workflow entities.
     *
     * @param dtos the collection of WorkflowDTOs to convert
     * @return a list of corresponding Workflow entities
     */
    public List<Workflow> toEntityList(Collection<WorkflowDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
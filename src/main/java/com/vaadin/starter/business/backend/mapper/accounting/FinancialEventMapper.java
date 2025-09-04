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


package com.vaadin.starter.business.backend.mapper.accounting;

import com.vaadin.starter.business.backend.FinancialEvent;
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
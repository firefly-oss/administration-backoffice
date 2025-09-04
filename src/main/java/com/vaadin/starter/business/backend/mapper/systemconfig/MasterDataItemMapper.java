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

import com.vaadin.starter.business.backend.MasterDataItem;
import com.vaadin.starter.business.backend.dto.systemconfig.MasterDataItemDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between MasterDataItem and MasterDataItemDTO objects.
 */
@Component
public class MasterDataItemMapper {

    /**
     * Convert a MasterDataItem entity to a MasterDataItemDTO.
     *
     * @param masterDataItem the MasterDataItem entity to convert
     * @return the corresponding MasterDataItemDTO
     */
    public MasterDataItemDTO toDto(MasterDataItem masterDataItem) {
        if (masterDataItem == null) {
            return null;
        }
        
        return new MasterDataItemDTO(
            masterDataItem.getId(),
            masterDataItem.getName(),
            masterDataItem.getDescription(),
            masterDataItem.getStatus(),
            masterDataItem.getCategory(),
            masterDataItem.getDataType(),
            masterDataItem.getFormat(),
            masterDataItem.getLastModified()
        );
    }

    /**
     * Convert a MasterDataItemDTO to a MasterDataItem entity.
     *
     * @param dto the MasterDataItemDTO to convert
     * @return the corresponding MasterDataItem entity
     */
    public MasterDataItem toEntity(MasterDataItemDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new MasterDataItem(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getCategory(),
            dto.getDataType(),
            dto.getFormat(),
            dto.getLastModified()
        );
    }

    /**
     * Convert a collection of MasterDataItem entities to a list of MasterDataItemDTOs.
     *
     * @param masterDataItems the collection of MasterDataItem entities to convert
     * @return a list of corresponding MasterDataItemDTOs
     */
    public List<MasterDataItemDTO> toDtoList(Collection<MasterDataItem> masterDataItems) {
        if (masterDataItems == null) {
            return List.of();
        }
        
        return masterDataItems.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of MasterDataItemDTOs to a list of MasterDataItem entities.
     *
     * @param dtos the collection of MasterDataItemDTOs to convert
     * @return a list of corresponding MasterDataItem entities
     */
    public List<MasterDataItem> toEntityList(Collection<MasterDataItemDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
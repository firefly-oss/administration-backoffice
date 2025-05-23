package com.vaadin.starter.business.backend.mapper.systemconfig;

import com.vaadin.starter.business.backend.ConfigItem;
import com.vaadin.starter.business.backend.dto.systemconfig.ConfigItemDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between ConfigItem and ConfigItemDTO objects.
 */
@Component
public class ConfigItemMapper {

    /**
     * Convert a ConfigItem entity to a ConfigItemDTO.
     *
     * @param configItem the ConfigItem entity to convert
     * @return the corresponding ConfigItemDTO
     */
    public ConfigItemDTO toDto(ConfigItem configItem) {
        if (configItem == null) {
            return null;
        }
        
        return new ConfigItemDTO(
            configItem.getId(),
            configItem.getName(),
            configItem.getDescription(),
            configItem.getStatus(),
            configItem.getCategory(),
            configItem.getValue(),
            configItem.getScope(),
            configItem.getLastModified()
        );
    }

    /**
     * Convert a ConfigItemDTO to a ConfigItem entity.
     *
     * @param dto the ConfigItemDTO to convert
     * @return the corresponding ConfigItem entity
     */
    public ConfigItem toEntity(ConfigItemDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new ConfigItem(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getCategory(),
            dto.getValue(),
            dto.getScope(),
            dto.getLastModified()
        );
    }

    /**
     * Convert a collection of ConfigItem entities to a list of ConfigItemDTOs.
     *
     * @param configItems the collection of ConfigItem entities to convert
     * @return a list of corresponding ConfigItemDTOs
     */
    public List<ConfigItemDTO> toDtoList(Collection<ConfigItem> configItems) {
        if (configItems == null) {
            return List.of();
        }
        
        return configItems.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of ConfigItemDTOs to a list of ConfigItem entities.
     *
     * @param dtos the collection of ConfigItemDTOs to convert
     * @return a list of corresponding ConfigItem entities
     */
    public List<ConfigItem> toEntityList(Collection<ConfigItemDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
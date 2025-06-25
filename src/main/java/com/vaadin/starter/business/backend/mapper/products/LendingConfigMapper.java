package com.vaadin.starter.business.backend.mapper.products;

import com.vaadin.starter.business.dummy.LendingConfig;
import com.vaadin.starter.business.backend.dto.products.LendingConfigDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between LendingConfig and LendingConfigDTO objects.
 */
@Component
public class LendingConfigMapper {

    /**
     * Convert a LendingConfig entity to a LendingConfigDTO.
     *
     * @param lendingConfig the LendingConfig entity to convert
     * @return the corresponding LendingConfigDTO
     */
    public LendingConfigDTO toDto(LendingConfig lendingConfig) {
        if (lendingConfig == null) {
            return null;
        }
        
        return new LendingConfigDTO(
            lendingConfig.getId(),
            lendingConfig.getName(),
            lendingConfig.getLoanType(),
            lendingConfig.getMinAmount(),
            lendingConfig.getMaxAmount(),
            lendingConfig.getBaseInterestRate(),
            lendingConfig.getMinTerm(),
            lendingConfig.getMaxTerm(),
            lendingConfig.getRiskCategory(),
            lendingConfig.isActive(),
            lendingConfig.getLastUpdated()
        );
    }

    /**
     * Convert a LendingConfigDTO to a LendingConfig entity.
     *
     * @param dto the LendingConfigDTO to convert
     * @return the corresponding LendingConfig entity
     */
    public LendingConfig toEntity(LendingConfigDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new LendingConfig(
            dto.getId(),
            dto.getName(),
            dto.getLoanType(),
            dto.getMinAmount(),
            dto.getMaxAmount(),
            dto.getBaseInterestRate(),
            dto.getMinTerm(),
            dto.getMaxTerm(),
            dto.getRiskCategory(),
            dto.isActive(),
            dto.getLastUpdated()
        );
    }

    /**
     * Convert a collection of LendingConfig entities to a list of LendingConfigDTOs.
     *
     * @param lendingConfigs the collection of LendingConfig entities to convert
     * @return a list of corresponding LendingConfigDTOs
     */
    public List<LendingConfigDTO> toDtoList(Collection<LendingConfig> lendingConfigs) {
        if (lendingConfigs == null) {
            return List.of();
        }
        
        return lendingConfigs.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of LendingConfigDTOs to a list of LendingConfig entities.
     *
     * @param dtos the collection of LendingConfigDTOs to convert
     * @return a list of corresponding LendingConfig entities
     */
    public List<LendingConfig> toEntityList(Collection<LendingConfigDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
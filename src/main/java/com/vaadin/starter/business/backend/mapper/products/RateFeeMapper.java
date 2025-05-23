package com.vaadin.starter.business.backend.mapper.products;

import com.vaadin.starter.business.backend.RateFee;
import com.vaadin.starter.business.backend.dto.products.RateFeeDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between RateFee and RateFeeDTO objects.
 */
@Component
public class RateFeeMapper {

    /**
     * Convert a RateFee entity to a RateFeeDTO.
     *
     * @param rateFee the RateFee entity to convert
     * @return the corresponding RateFeeDTO
     */
    public RateFeeDTO toDto(RateFee rateFee) {
        if (rateFee == null) {
            return null;
        }
        
        return new RateFeeDTO(
            rateFee.getId(),
            rateFee.getName(),
            rateFee.getProductCategory(),
            rateFee.getType(),
            rateFee.getValue(),
            rateFee.getCalculationMethod(),
            rateFee.isActive(),
            rateFee.getEffectiveDate(),
            rateFee.getExpirationDate()
        );
    }

    /**
     * Convert a RateFeeDTO to a RateFee entity.
     *
     * @param dto the RateFeeDTO to convert
     * @return the corresponding RateFee entity
     */
    public RateFee toEntity(RateFeeDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new RateFee(
            dto.getId(),
            dto.getName(),
            dto.getProductCategory(),
            dto.getType(),
            dto.getValue(),
            dto.getCalculationMethod(),
            dto.isActive(),
            dto.getEffectiveDate(),
            dto.getExpirationDate()
        );
    }

    /**
     * Convert a collection of RateFee entities to a list of RateFeeDTOs.
     *
     * @param rateFees the collection of RateFee entities to convert
     * @return a list of corresponding RateFeeDTOs
     */
    public List<RateFeeDTO> toDtoList(Collection<RateFee> rateFees) {
        if (rateFees == null) {
            return List.of();
        }
        
        return rateFees.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of RateFeeDTOs to a list of RateFee entities.
     *
     * @param dtos the collection of RateFeeDTOs to convert
     * @return a list of corresponding RateFee entities
     */
    public List<RateFee> toEntityList(Collection<RateFeeDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
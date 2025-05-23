package com.vaadin.starter.business.backend.mapper.customers;

import com.vaadin.starter.business.backend.Segment;
import com.vaadin.starter.business.backend.dto.customers.SegmentDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Segment and SegmentDTO objects.
 */
@Component
public class SegmentMapper {

    /**
     * Convert a Segment entity to a SegmentDTO.
     *
     * @param segment the Segment entity to convert
     * @return the corresponding SegmentDTO
     */
    public SegmentDTO toDto(Segment segment) {
        if (segment == null) {
            return null;
        }
        
        return new SegmentDTO(
            segment.getId(),
            segment.getName(),
            segment.getDescription(),
            segment.getCustomerCount(),
            segment.getAverageBalance(),
            segment.getRiskProfile(),
            segment.isActive(),
            segment.getCreatedDate()
        );
    }

    /**
     * Convert a SegmentDTO to a Segment entity.
     *
     * @param dto the SegmentDTO to convert
     * @return the corresponding Segment entity
     */
    public Segment toEntity(SegmentDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Segment(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getCustomerCount(),
            dto.getAverageBalance(),
            dto.getRiskProfile(),
            dto.isActive(),
            dto.getCreatedDate()
        );
    }

    /**
     * Convert a collection of Segment entities to a list of SegmentDTOs.
     *
     * @param segments the collection of Segment entities to convert
     * @return a list of corresponding SegmentDTOs
     */
    public List<SegmentDTO> toDtoList(Collection<Segment> segments) {
        if (segments == null) {
            return List.of();
        }
        
        return segments.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of SegmentDTOs to a list of Segment entities.
     *
     * @param dtos the collection of SegmentDTOs to convert
     * @return a list of corresponding Segment entities
     */
    public List<Segment> toEntityList(Collection<SegmentDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
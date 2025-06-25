package com.vaadin.starter.business.backend.mapper.security;

import com.vaadin.starter.business.dummy.Policy;
import com.vaadin.starter.business.backend.dto.security.PolicyDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Policy and PolicyDTO objects.
 */
@Component
public class PolicyMapper {

    /**
     * Convert a Policy entity to a PolicyDTO.
     *
     * @param policy the Policy entity to convert
     * @return the corresponding PolicyDTO
     */
    public PolicyDTO toDto(Policy policy) {
        if (policy == null) {
            return null;
        }
        
        return new PolicyDTO(
            policy.getName(),
            policy.getCategory(),
            policy.getDescription(),
            policy.isActive(),
            policy.getLastUpdated()
        );
    }

    /**
     * Convert a PolicyDTO to a Policy entity.
     *
     * @param dto the PolicyDTO to convert
     * @return the corresponding Policy entity
     */
    public Policy toEntity(PolicyDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Policy(
            dto.getName(),
            dto.getCategory(),
            dto.getDescription(),
            dto.isActive(),
            dto.getLastUpdated()
        );
    }

    /**
     * Convert a collection of Policy entities to a list of PolicyDTOs.
     *
     * @param policies the collection of Policy entities to convert
     * @return a list of corresponding PolicyDTOs
     */
    public List<PolicyDTO> toDtoList(Collection<Policy> policies) {
        if (policies == null) {
            return List.of();
        }
        
        return policies.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of PolicyDTOs to a list of Policy entities.
     *
     * @param dtos the collection of PolicyDTOs to convert
     * @return a list of corresponding Policy entities
     */
    public List<Policy> toEntityList(Collection<PolicyDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
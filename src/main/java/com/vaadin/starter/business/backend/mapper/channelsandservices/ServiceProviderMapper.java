package com.vaadin.starter.business.backend.mapper.channelsandservices;

import com.vaadin.starter.business.backend.ServiceProvider;
import com.vaadin.starter.business.backend.dto.channelsandservices.ServiceProviderDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between ServiceProvider and ServiceProviderDTO objects.
 */
@Component
public class ServiceProviderMapper {

    /**
     * Convert a ServiceProvider entity to a ServiceProviderDTO.
     *
     * @param serviceProvider the ServiceProvider entity to convert
     * @return the corresponding ServiceProviderDTO
     */
    public ServiceProviderDTO toDto(ServiceProvider serviceProvider) {
        if (serviceProvider == null) {
            return null;
        }
        
        return new ServiceProviderDTO(
            serviceProvider.getId(),
            serviceProvider.getName(),
            serviceProvider.getDescription(),
            serviceProvider.getStatus(),
            serviceProvider.getServiceType(),
            serviceProvider.getContactPerson(),
            serviceProvider.getContactEmail(),
            serviceProvider.getContractStart(),
            serviceProvider.getContractExpiry(),
            serviceProvider.getSlaLevel()
        );
    }

    /**
     * Convert a ServiceProviderDTO to a ServiceProvider entity.
     *
     * @param dto the ServiceProviderDTO to convert
     * @return the corresponding ServiceProvider entity
     */
    public ServiceProvider toEntity(ServiceProviderDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new ServiceProvider(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getServiceType(),
            dto.getContactPerson(),
            dto.getContactEmail(),
            dto.getContractStart(),
            dto.getContractExpiry(),
            dto.getSlaLevel()
        );
    }

    /**
     * Convert a collection of ServiceProvider entities to a list of ServiceProviderDTOs.
     *
     * @param serviceProviders the collection of ServiceProvider entities to convert
     * @return a list of corresponding ServiceProviderDTOs
     */
    public List<ServiceProviderDTO> toDtoList(Collection<ServiceProvider> serviceProviders) {
        if (serviceProviders == null) {
            return List.of();
        }
        
        return serviceProviders.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of ServiceProviderDTOs to a list of ServiceProvider entities.
     *
     * @param dtos the collection of ServiceProviderDTOs to convert
     * @return a list of corresponding ServiceProvider entities
     */
    public List<ServiceProvider> toEntityList(Collection<ServiceProviderDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
package com.vaadin.starter.business.backend.mapper.channelsandservices;

import com.vaadin.starter.business.backend.Channel;
import com.vaadin.starter.business.backend.dto.channelsandservices.ChannelDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Channel and ChannelDTO objects.
 */
@Component
public class ChannelMapper {

    /**
     * Convert a Channel entity to a ChannelDTO.
     *
     * @param channel the Channel entity to convert
     * @return the corresponding ChannelDTO
     */
    public ChannelDTO toDto(Channel channel) {
        if (channel == null) {
            return null;
        }
        
        return new ChannelDTO(
            channel.getId(),
            channel.getName(),
            channel.getDescription(),
            channel.isActive(),
            channel.getIntegrationType(),
            channel.getLastUpdated(),
            channel.getEndpoint(),
            channel.getSecurityLevel()
        );
    }

    /**
     * Convert a ChannelDTO to a Channel entity.
     *
     * @param dto the ChannelDTO to convert
     * @return the corresponding Channel entity
     */
    public Channel toEntity(ChannelDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Channel(
            dto.getId(),
            dto.getName(),
            dto.getDescription(),
            dto.isActive(),
            dto.getIntegrationType(),
            dto.getLastUpdated(),
            dto.getEndpoint(),
            dto.getSecurityLevel()
        );
    }

    /**
     * Convert a collection of Channel entities to a list of ChannelDTOs.
     *
     * @param channels the collection of Channel entities to convert
     * @return a list of corresponding ChannelDTOs
     */
    public List<ChannelDTO> toDtoList(Collection<Channel> channels) {
        if (channels == null) {
            return List.of();
        }
        
        return channels.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of ChannelDTOs to a list of Channel entities.
     *
     * @param dtos the collection of ChannelDTOs to convert
     * @return a list of corresponding Channel entities
     */
    public List<Channel> toEntityList(Collection<ChannelDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
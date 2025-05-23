package com.vaadin.starter.business.backend.mapper.systemconfig;

import com.vaadin.starter.business.backend.Notification;
import com.vaadin.starter.business.backend.dto.systemconfig.NotificationDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Notification and NotificationDTO objects.
 */
@Component
public class NotificationMapper {

    /**
     * Convert a Notification entity to a NotificationDTO.
     *
     * @param notification the Notification entity to convert
     * @return the corresponding NotificationDTO
     */
    public NotificationDTO toDto(Notification notification) {
        if (notification == null) {
            return null;
        }
        
        return new NotificationDTO(
            notification.getId(),
            notification.getTitle(),
            notification.getMessage(),
            notification.getType(),
            notification.getStatus(),
            notification.getPriority(),
            notification.getTarget(),
            notification.getCreatedAt(),
            notification.getExpiryDate()
        );
    }

    /**
     * Convert a NotificationDTO to a Notification entity.
     *
     * @param dto the NotificationDTO to convert
     * @return the corresponding Notification entity
     */
    public Notification toEntity(NotificationDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Notification(
            dto.getId(),
            dto.getTitle(),
            dto.getMessage(),
            dto.getType(),
            dto.getStatus(),
            dto.getPriority(),
            dto.getTarget(),
            dto.getCreatedAt(),
            dto.getExpiryDate()
        );
    }

    /**
     * Convert a collection of Notification entities to a list of NotificationDTOs.
     *
     * @param notifications the collection of Notification entities to convert
     * @return a list of corresponding NotificationDTOs
     */
    public List<NotificationDTO> toDtoList(Collection<Notification> notifications) {
        if (notifications == null) {
            return List.of();
        }
        
        return notifications.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of NotificationDTOs to a list of Notification entities.
     *
     * @param dtos the collection of NotificationDTOs to convert
     * @return a list of corresponding Notification entities
     */
    public List<Notification> toEntityList(Collection<NotificationDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
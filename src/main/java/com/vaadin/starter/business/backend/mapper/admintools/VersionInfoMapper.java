package com.vaadin.starter.business.backend.mapper.admintools;

import com.vaadin.starter.business.backend.VersionInfo;
import com.vaadin.starter.business.backend.dto.admintools.VersionInfoDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between VersionInfo and VersionInfoDTO objects.
 */
@Component
public class VersionInfoMapper {

    /**
     * Convert a VersionInfo entity to a VersionInfoDTO.
     *
     * @param versionInfo the VersionInfo entity to convert
     * @return the corresponding VersionInfoDTO
     */
    public VersionInfoDTO toDto(VersionInfo versionInfo) {
        if (versionInfo == null) {
            return null;
        }
        
        return new VersionInfoDTO(
            versionInfo.getId(),
            versionInfo.getVersionNumber(),
            versionInfo.getDescription(),
            versionInfo.getStatus(),
            versionInfo.getEnvironment(),
            versionInfo.getReleaseDate(),
            versionInfo.getReleasedBy(),
            versionInfo.getChangeLog()
        );
    }

    /**
     * Convert a VersionInfoDTO to a VersionInfo entity.
     *
     * @param dto the VersionInfoDTO to convert
     * @return the corresponding VersionInfo entity
     */
    public VersionInfo toEntity(VersionInfoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new VersionInfo(
            dto.getId(),
            dto.getVersionNumber(),
            dto.getDescription(),
            dto.getStatus(),
            dto.getEnvironment(),
            dto.getReleaseDate(),
            dto.getReleasedBy(),
            dto.getChangeLog()
        );
    }

    /**
     * Convert a collection of VersionInfo entities to a list of VersionInfoDTOs.
     *
     * @param versionInfos the collection of VersionInfo entities to convert
     * @return a list of corresponding VersionInfoDTOs
     */
    public List<VersionInfoDTO> toDtoList(Collection<VersionInfo> versionInfos) {
        if (versionInfos == null) {
            return List.of();
        }
        
        return versionInfos.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of VersionInfoDTOs to a list of VersionInfo entities.
     *
     * @param dtos the collection of VersionInfoDTOs to convert
     * @return a list of corresponding VersionInfo entities
     */
    public List<VersionInfo> toEntityList(Collection<VersionInfoDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
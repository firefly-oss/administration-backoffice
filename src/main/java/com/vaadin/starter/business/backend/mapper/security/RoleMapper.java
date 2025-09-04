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


package com.vaadin.starter.business.backend.mapper.security;

import com.vaadin.starter.business.backend.Role;
import com.vaadin.starter.business.backend.dto.security.RoleDTO;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper for converting between Role and RoleDTO objects.
 */
@Component
public class RoleMapper {

    /**
     * Convert a Role entity to a RoleDTO.
     *
     * @param role the Role entity to convert
     * @return the corresponding RoleDTO
     */
    public RoleDTO toDto(Role role) {
        if (role == null) {
            return null;
        }
        
        return new RoleDTO(
            role.getName(),
            role.getDescription(),
            role.isActive(),
            role.getPermissions()
        );
    }

    /**
     * Convert a RoleDTO to a Role entity.
     *
     * @param dto the RoleDTO to convert
     * @return the corresponding Role entity
     */
    public Role toEntity(RoleDTO dto) {
        if (dto == null) {
            return null;
        }
        
        return new Role(
            dto.getName(),
            dto.getDescription(),
            dto.isActive(),
            dto.getPermissions()
        );
    }

    /**
     * Convert a collection of Role entities to a list of RoleDTOs.
     *
     * @param roles the collection of Role entities to convert
     * @return a list of corresponding RoleDTOs
     */
    public List<RoleDTO> toDtoList(Collection<Role> roles) {
        if (roles == null) {
            return List.of();
        }
        
        return roles.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }

    /**
     * Convert a collection of RoleDTOs to a list of Role entities.
     *
     * @param dtos the collection of RoleDTOs to convert
     * @return a list of corresponding Role entities
     */
    public List<Role> toEntityList(Collection<RoleDTO> dtos) {
        if (dtos == null) {
            return List.of();
        }
        
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
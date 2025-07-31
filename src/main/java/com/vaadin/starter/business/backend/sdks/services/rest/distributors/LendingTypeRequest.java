package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for Lending Type operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LendingTypeRequest {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

/**
 * Request object for Card Enrollment operations (create, update).
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardEnrollmentRequest {
    private Long id;
    private Long cardId;
    private String enrollmentType;
    private String status;
    private String enrollmentDate;
    private String expiryDate;
    private String serviceName;
    private String serviceProvider;
    private String enrollmentReference;
    private String description;
    private Boolean isActive;
    private String createdBy;
    private String updatedBy;
}
package com.vaadin.starter.business.backend.sdks.services.rest;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NaturalPersonRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private String filtersTitleDescription;
    private String filtersFirstName;
    private String filtersMiddleName;
    private String filtersFirstSurname;
    private String filtersSecondSurname;
    private String filtersDateOfBirth;
    private String filtersPlaceOfBirth;
    private String filtersNationalityDescription;
    private String filtersGender;
    private String filtersMaritalStatus;
    private String filtersTaxIdentificationNumber;
    private String filtersResidencyStatus;
    private String filtersCountryOfResidenceDescription;
    private String filtersAvatarUrl;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;

}
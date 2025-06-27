package com.vaadin.starter.business.backend.sdks.services.rest.customers;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LegalPersonRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private String filtersLegalName;
    private String filtersTradeName;
    private String filtersRegistrationNumber;
    private String filtersTaxIdentificationNumber;
    private String filtersDateOfIncorporation;
    private String filtersBusinessActivity;
    private Integer filtersNumberOfEmployees;
    private Integer rangeFiltersRangesNumberOfEmployeesFrom;
    private Integer rangeFiltersRangesNumberOfEmployeesTo;
    private String filtersShareCapital;
    private String rangeFiltersRangesShareCapitalFrom;
    private String rangeFiltersRangesShareCapitalTo;
    private String filtersWebsiteUrl;
    private String filtersIncorporationCountry;
    private String filtersPhoneNumber;
    private String filtersEmailAddress;
    private String filtersMainContactName;
    private String filtersMainContactTitle;
    private String filtersLogoUrl;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;

}
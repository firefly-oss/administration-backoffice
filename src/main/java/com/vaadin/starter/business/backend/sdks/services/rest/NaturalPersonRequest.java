package com.vaadin.starter.business.backend.sdks.services.rest;

import java.time.LocalDateTime;

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

    public NaturalPersonRequest(Integer paginationPageNumber, Integer paginationPageSize, String paginationSortBy,
                               String paginationSortDirection, Boolean optionsCaseInsensitiveStrings,
                               Boolean optionsIncludeInheritedFields, String filtersTitleDescription,
                               String filtersFirstName, String filtersMiddleName, String filtersFirstSurname,
                               String filtersSecondSurname, String filtersDateOfBirth, String filtersPlaceOfBirth,
                               String filtersNationalityDescription, String filtersGender, String filtersMaritalStatus,
                               String filtersTaxIdentificationNumber, String filtersResidencyStatus,
                               String filtersCountryOfResidenceDescription, String filtersAvatarUrl,
                               LocalDateTime filtersDateCreated, LocalDateTime rangeFiltersRangesDateCreatedFrom,
                               LocalDateTime rangeFiltersRangesDateCreatedTo, LocalDateTime filtersDateUpdated,
                               LocalDateTime rangeFiltersRangesDateUpdatedFrom, LocalDateTime rangeFiltersRangesDateUpdatedTo) {
        this.paginationPageNumber = paginationPageNumber;
        this.paginationPageSize = paginationPageSize;
        this.paginationSortBy = paginationSortBy;
        this.paginationSortDirection = paginationSortDirection;
        this.optionsCaseInsensitiveStrings = optionsCaseInsensitiveStrings;
        this.optionsIncludeInheritedFields = optionsIncludeInheritedFields;
        this.filtersTitleDescription = filtersTitleDescription;
        this.filtersFirstName = filtersFirstName;
        this.filtersMiddleName = filtersMiddleName;
        this.filtersFirstSurname = filtersFirstSurname;
        this.filtersSecondSurname = filtersSecondSurname;
        this.filtersDateOfBirth = filtersDateOfBirth;
        this.filtersPlaceOfBirth = filtersPlaceOfBirth;
        this.filtersNationalityDescription = filtersNationalityDescription;
        this.filtersGender = filtersGender;
        this.filtersMaritalStatus = filtersMaritalStatus;
        this.filtersTaxIdentificationNumber = filtersTaxIdentificationNumber;
        this.filtersResidencyStatus = filtersResidencyStatus;
        this.filtersCountryOfResidenceDescription = filtersCountryOfResidenceDescription;
        this.filtersAvatarUrl = filtersAvatarUrl;
        this.filtersDateCreated = filtersDateCreated;
        this.rangeFiltersRangesDateCreatedFrom = rangeFiltersRangesDateCreatedFrom;
        this.rangeFiltersRangesDateCreatedTo = rangeFiltersRangesDateCreatedTo;
        this.filtersDateUpdated = filtersDateUpdated;
        this.rangeFiltersRangesDateUpdatedFrom = rangeFiltersRangesDateUpdatedFrom;
        this.rangeFiltersRangesDateUpdatedTo = rangeFiltersRangesDateUpdatedTo;
    }

    // Getters
    public Integer getPaginationPageNumber() {
        return paginationPageNumber;
    }

    public Integer getPaginationPageSize() {
        return paginationPageSize;
    }

    public String getPaginationSortBy() {
        return paginationSortBy;
    }

    public String getPaginationSortDirection() {
        return paginationSortDirection;
    }

    public Boolean getOptionsCaseInsensitiveStrings() {
        return optionsCaseInsensitiveStrings;
    }

    public Boolean getOptionsIncludeInheritedFields() {
        return optionsIncludeInheritedFields;
    }

    public String getFiltersTitleDescription() {
        return filtersTitleDescription;
    }

    public String getFiltersFirstName() {
        return filtersFirstName;
    }

    public String getFiltersMiddleName() {
        return filtersMiddleName;
    }

    public String getFiltersFirstSurname() {
        return filtersFirstSurname;
    }

    public String getFiltersSecondSurname() {
        return filtersSecondSurname;
    }

    public String getFiltersDateOfBirth() {
        return filtersDateOfBirth;
    }

    public String getFiltersPlaceOfBirth() {
        return filtersPlaceOfBirth;
    }

    public String getFiltersNationalityDescription() {
        return filtersNationalityDescription;
    }

    public String getFiltersGender() {
        return filtersGender;
    }

    public String getFiltersMaritalStatus() {
        return filtersMaritalStatus;
    }

    public String getFiltersTaxIdentificationNumber() {
        return filtersTaxIdentificationNumber;
    }

    public String getFiltersResidencyStatus() {
        return filtersResidencyStatus;
    }

    public String getFiltersCountryOfResidenceDescription() {
        return filtersCountryOfResidenceDescription;
    }

    public String getFiltersAvatarUrl() {
        return filtersAvatarUrl;
    }

    public LocalDateTime getFiltersDateCreated() {
        return filtersDateCreated;
    }

    public LocalDateTime getRangeFiltersRangesDateCreatedFrom() {
        return rangeFiltersRangesDateCreatedFrom;
    }

    public LocalDateTime getRangeFiltersRangesDateCreatedTo() {
        return rangeFiltersRangesDateCreatedTo;
    }

    public LocalDateTime getFiltersDateUpdated() {
        return filtersDateUpdated;
    }

    public LocalDateTime getRangeFiltersRangesDateUpdatedFrom() {
        return rangeFiltersRangesDateUpdatedFrom;
    }

    public LocalDateTime getRangeFiltersRangesDateUpdatedTo() {
        return rangeFiltersRangesDateUpdatedTo;
    }
}
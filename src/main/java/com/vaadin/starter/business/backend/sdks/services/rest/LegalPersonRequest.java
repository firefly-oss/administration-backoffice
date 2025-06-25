package com.vaadin.starter.business.backend.sdks.services.rest;

import java.time.LocalDateTime;

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

    public LegalPersonRequest(Integer paginationPageNumber, Integer paginationPageSize, String paginationSortBy,
                             String paginationSortDirection, Boolean optionsCaseInsensitiveStrings,
                             Boolean optionsIncludeInheritedFields, String filtersLegalName, String filtersTradeName,
                             String filtersRegistrationNumber, String filtersTaxIdentificationNumber,
                             String filtersDateOfIncorporation, String filtersBusinessActivity,
                             Integer filtersNumberOfEmployees, Integer rangeFiltersRangesNumberOfEmployeesFrom,
                             Integer rangeFiltersRangesNumberOfEmployeesTo, String filtersShareCapital,
                             String rangeFiltersRangesShareCapitalFrom, String rangeFiltersRangesShareCapitalTo,
                             String filtersWebsiteUrl, String filtersIncorporationCountry, String filtersPhoneNumber,
                             String filtersEmailAddress, String filtersMainContactName, String filtersMainContactTitle,
                             String filtersLogoUrl, LocalDateTime filtersDateCreated,
                             LocalDateTime rangeFiltersRangesDateCreatedFrom, LocalDateTime rangeFiltersRangesDateCreatedTo,
                             LocalDateTime filtersDateUpdated, LocalDateTime rangeFiltersRangesDateUpdatedFrom,
                             LocalDateTime rangeFiltersRangesDateUpdatedTo) {
        this.paginationPageNumber = paginationPageNumber;
        this.paginationPageSize = paginationPageSize;
        this.paginationSortBy = paginationSortBy;
        this.paginationSortDirection = paginationSortDirection;
        this.optionsCaseInsensitiveStrings = optionsCaseInsensitiveStrings;
        this.optionsIncludeInheritedFields = optionsIncludeInheritedFields;
        this.filtersLegalName = filtersLegalName;
        this.filtersTradeName = filtersTradeName;
        this.filtersRegistrationNumber = filtersRegistrationNumber;
        this.filtersTaxIdentificationNumber = filtersTaxIdentificationNumber;
        this.filtersDateOfIncorporation = filtersDateOfIncorporation;
        this.filtersBusinessActivity = filtersBusinessActivity;
        this.filtersNumberOfEmployees = filtersNumberOfEmployees;
        this.rangeFiltersRangesNumberOfEmployeesFrom = rangeFiltersRangesNumberOfEmployeesFrom;
        this.rangeFiltersRangesNumberOfEmployeesTo = rangeFiltersRangesNumberOfEmployeesTo;
        this.filtersShareCapital = filtersShareCapital;
        this.rangeFiltersRangesShareCapitalFrom = rangeFiltersRangesShareCapitalFrom;
        this.rangeFiltersRangesShareCapitalTo = rangeFiltersRangesShareCapitalTo;
        this.filtersWebsiteUrl = filtersWebsiteUrl;
        this.filtersIncorporationCountry = filtersIncorporationCountry;
        this.filtersPhoneNumber = filtersPhoneNumber;
        this.filtersEmailAddress = filtersEmailAddress;
        this.filtersMainContactName = filtersMainContactName;
        this.filtersMainContactTitle = filtersMainContactTitle;
        this.filtersLogoUrl = filtersLogoUrl;
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

    public String getFiltersLegalName() {
        return filtersLegalName;
    }

    public String getFiltersTradeName() {
        return filtersTradeName;
    }

    public String getFiltersRegistrationNumber() {
        return filtersRegistrationNumber;
    }

    public String getFiltersTaxIdentificationNumber() {
        return filtersTaxIdentificationNumber;
    }

    public String getFiltersDateOfIncorporation() {
        return filtersDateOfIncorporation;
    }

    public String getFiltersBusinessActivity() {
        return filtersBusinessActivity;
    }

    public Integer getFiltersNumberOfEmployees() {
        return filtersNumberOfEmployees;
    }

    public Integer getRangeFiltersRangesNumberOfEmployeesFrom() {
        return rangeFiltersRangesNumberOfEmployeesFrom;
    }

    public Integer getRangeFiltersRangesNumberOfEmployeesTo() {
        return rangeFiltersRangesNumberOfEmployeesTo;
    }

    public String getFiltersShareCapital() {
        return filtersShareCapital;
    }

    public String getRangeFiltersRangesShareCapitalFrom() {
        return rangeFiltersRangesShareCapitalFrom;
    }

    public String getRangeFiltersRangesShareCapitalTo() {
        return rangeFiltersRangesShareCapitalTo;
    }

    public String getFiltersWebsiteUrl() {
        return filtersWebsiteUrl;
    }

    public String getFiltersIncorporationCountry() {
        return filtersIncorporationCountry;
    }

    public String getFiltersPhoneNumber() {
        return filtersPhoneNumber;
    }

    public String getFiltersEmailAddress() {
        return filtersEmailAddress;
    }

    public String getFiltersMainContactName() {
        return filtersMainContactName;
    }

    public String getFiltersMainContactTitle() {
        return filtersMainContactTitle;
    }

    public String getFiltersLogoUrl() {
        return filtersLogoUrl;
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
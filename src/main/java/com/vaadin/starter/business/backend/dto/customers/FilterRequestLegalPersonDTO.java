package com.vaadin.starter.business.backend.dto.customers;

import java.time.LocalDateTime;

/**
 * This class has been replaced by com.catalis.common.customer.sdk.model.FilterRequestLegalPersonDTO
 * @deprecated Use com.catalis.common.customer.sdk.model.FilterRequestLegalPersonDTO instead
 */
public class FilterRequestLegalPersonDTO {
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

    // Default constructor
    public FilterRequestLegalPersonDTO() {
    }

    // All-args constructor
    public FilterRequestLegalPersonDTO(Integer paginationPageNumber, Integer paginationPageSize, String paginationSortBy,
                                      String paginationSortDirection, Boolean optionsCaseInsensitiveStrings,
                                      Boolean optionsIncludeInheritedFields, String filtersLegalName,
                                      String filtersTradeName, String filtersRegistrationNumber,
                                      String filtersTaxIdentificationNumber, String filtersDateOfIncorporation,
                                      String filtersBusinessActivity, Integer filtersNumberOfEmployees,
                                      Integer rangeFiltersRangesNumberOfEmployeesFrom, Integer rangeFiltersRangesNumberOfEmployeesTo,
                                      String filtersShareCapital, String rangeFiltersRangesShareCapitalFrom,
                                      String rangeFiltersRangesShareCapitalTo, String filtersWebsiteUrl,
                                      String filtersIncorporationCountry, String filtersPhoneNumber,
                                      String filtersEmailAddress, String filtersMainContactName,
                                      String filtersMainContactTitle, String filtersLogoUrl,
                                      LocalDateTime filtersDateCreated, LocalDateTime rangeFiltersRangesDateCreatedFrom,
                                      LocalDateTime rangeFiltersRangesDateCreatedTo, LocalDateTime filtersDateUpdated,
                                      LocalDateTime rangeFiltersRangesDateUpdatedFrom, LocalDateTime rangeFiltersRangesDateUpdatedTo) {
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

    // Getters and setters
    public Integer getPaginationPageNumber() {
        return paginationPageNumber;
    }

    public void setPaginationPageNumber(Integer paginationPageNumber) {
        this.paginationPageNumber = paginationPageNumber;
    }

    public Integer getPaginationPageSize() {
        return paginationPageSize;
    }

    public void setPaginationPageSize(Integer paginationPageSize) {
        this.paginationPageSize = paginationPageSize;
    }

    public String getPaginationSortBy() {
        return paginationSortBy;
    }

    public void setPaginationSortBy(String paginationSortBy) {
        this.paginationSortBy = paginationSortBy;
    }

    public String getPaginationSortDirection() {
        return paginationSortDirection;
    }

    public void setPaginationSortDirection(String paginationSortDirection) {
        this.paginationSortDirection = paginationSortDirection;
    }

    public Boolean getOptionsCaseInsensitiveStrings() {
        return optionsCaseInsensitiveStrings;
    }

    public void setOptionsCaseInsensitiveStrings(Boolean optionsCaseInsensitiveStrings) {
        this.optionsCaseInsensitiveStrings = optionsCaseInsensitiveStrings;
    }

    public Boolean getOptionsIncludeInheritedFields() {
        return optionsIncludeInheritedFields;
    }

    public void setOptionsIncludeInheritedFields(Boolean optionsIncludeInheritedFields) {
        this.optionsIncludeInheritedFields = optionsIncludeInheritedFields;
    }

    public String getFiltersLegalName() {
        return filtersLegalName;
    }

    public void setFiltersLegalName(String filtersLegalName) {
        this.filtersLegalName = filtersLegalName;
    }

    public String getFiltersTradeName() {
        return filtersTradeName;
    }

    public void setFiltersTradeName(String filtersTradeName) {
        this.filtersTradeName = filtersTradeName;
    }

    public String getFiltersRegistrationNumber() {
        return filtersRegistrationNumber;
    }

    public void setFiltersRegistrationNumber(String filtersRegistrationNumber) {
        this.filtersRegistrationNumber = filtersRegistrationNumber;
    }

    public String getFiltersTaxIdentificationNumber() {
        return filtersTaxIdentificationNumber;
    }

    public void setFiltersTaxIdentificationNumber(String filtersTaxIdentificationNumber) {
        this.filtersTaxIdentificationNumber = filtersTaxIdentificationNumber;
    }

    public String getFiltersDateOfIncorporation() {
        return filtersDateOfIncorporation;
    }

    public void setFiltersDateOfIncorporation(String filtersDateOfIncorporation) {
        this.filtersDateOfIncorporation = filtersDateOfIncorporation;
    }

    public String getFiltersBusinessActivity() {
        return filtersBusinessActivity;
    }

    public void setFiltersBusinessActivity(String filtersBusinessActivity) {
        this.filtersBusinessActivity = filtersBusinessActivity;
    }

    public Integer getFiltersNumberOfEmployees() {
        return filtersNumberOfEmployees;
    }

    public void setFiltersNumberOfEmployees(Integer filtersNumberOfEmployees) {
        this.filtersNumberOfEmployees = filtersNumberOfEmployees;
    }

    public Integer getRangeFiltersRangesNumberOfEmployeesFrom() {
        return rangeFiltersRangesNumberOfEmployeesFrom;
    }

    public void setRangeFiltersRangesNumberOfEmployeesFrom(Integer rangeFiltersRangesNumberOfEmployeesFrom) {
        this.rangeFiltersRangesNumberOfEmployeesFrom = rangeFiltersRangesNumberOfEmployeesFrom;
    }

    public Integer getRangeFiltersRangesNumberOfEmployeesTo() {
        return rangeFiltersRangesNumberOfEmployeesTo;
    }

    public void setRangeFiltersRangesNumberOfEmployeesTo(Integer rangeFiltersRangesNumberOfEmployeesTo) {
        this.rangeFiltersRangesNumberOfEmployeesTo = rangeFiltersRangesNumberOfEmployeesTo;
    }

    public String getFiltersShareCapital() {
        return filtersShareCapital;
    }

    public void setFiltersShareCapital(String filtersShareCapital) {
        this.filtersShareCapital = filtersShareCapital;
    }

    public String getRangeFiltersRangesShareCapitalFrom() {
        return rangeFiltersRangesShareCapitalFrom;
    }

    public void setRangeFiltersRangesShareCapitalFrom(String rangeFiltersRangesShareCapitalFrom) {
        this.rangeFiltersRangesShareCapitalFrom = rangeFiltersRangesShareCapitalFrom;
    }

    public String getRangeFiltersRangesShareCapitalTo() {
        return rangeFiltersRangesShareCapitalTo;
    }

    public void setRangeFiltersRangesShareCapitalTo(String rangeFiltersRangesShareCapitalTo) {
        this.rangeFiltersRangesShareCapitalTo = rangeFiltersRangesShareCapitalTo;
    }

    public String getFiltersWebsiteUrl() {
        return filtersWebsiteUrl;
    }

    public void setFiltersWebsiteUrl(String filtersWebsiteUrl) {
        this.filtersWebsiteUrl = filtersWebsiteUrl;
    }

    public String getFiltersIncorporationCountry() {
        return filtersIncorporationCountry;
    }

    public void setFiltersIncorporationCountry(String filtersIncorporationCountry) {
        this.filtersIncorporationCountry = filtersIncorporationCountry;
    }

    public String getFiltersPhoneNumber() {
        return filtersPhoneNumber;
    }

    public void setFiltersPhoneNumber(String filtersPhoneNumber) {
        this.filtersPhoneNumber = filtersPhoneNumber;
    }

    public String getFiltersEmailAddress() {
        return filtersEmailAddress;
    }

    public void setFiltersEmailAddress(String filtersEmailAddress) {
        this.filtersEmailAddress = filtersEmailAddress;
    }

    public String getFiltersMainContactName() {
        return filtersMainContactName;
    }

    public void setFiltersMainContactName(String filtersMainContactName) {
        this.filtersMainContactName = filtersMainContactName;
    }

    public String getFiltersMainContactTitle() {
        return filtersMainContactTitle;
    }

    public void setFiltersMainContactTitle(String filtersMainContactTitle) {
        this.filtersMainContactTitle = filtersMainContactTitle;
    }

    public String getFiltersLogoUrl() {
        return filtersLogoUrl;
    }

    public void setFiltersLogoUrl(String filtersLogoUrl) {
        this.filtersLogoUrl = filtersLogoUrl;
    }

    public LocalDateTime getFiltersDateCreated() {
        return filtersDateCreated;
    }

    public void setFiltersDateCreated(LocalDateTime filtersDateCreated) {
        this.filtersDateCreated = filtersDateCreated;
    }

    public LocalDateTime getRangeFiltersRangesDateCreatedFrom() {
        return rangeFiltersRangesDateCreatedFrom;
    }

    public void setRangeFiltersRangesDateCreatedFrom(LocalDateTime rangeFiltersRangesDateCreatedFrom) {
        this.rangeFiltersRangesDateCreatedFrom = rangeFiltersRangesDateCreatedFrom;
    }

    public LocalDateTime getRangeFiltersRangesDateCreatedTo() {
        return rangeFiltersRangesDateCreatedTo;
    }

    public void setRangeFiltersRangesDateCreatedTo(LocalDateTime rangeFiltersRangesDateCreatedTo) {
        this.rangeFiltersRangesDateCreatedTo = rangeFiltersRangesDateCreatedTo;
    }

    public LocalDateTime getFiltersDateUpdated() {
        return filtersDateUpdated;
    }

    public void setFiltersDateUpdated(LocalDateTime filtersDateUpdated) {
        this.filtersDateUpdated = filtersDateUpdated;
    }

    public LocalDateTime getRangeFiltersRangesDateUpdatedFrom() {
        return rangeFiltersRangesDateUpdatedFrom;
    }

    public void setRangeFiltersRangesDateUpdatedFrom(LocalDateTime rangeFiltersRangesDateUpdatedFrom) {
        this.rangeFiltersRangesDateUpdatedFrom = rangeFiltersRangesDateUpdatedFrom;
    }

    public LocalDateTime getRangeFiltersRangesDateUpdatedTo() {
        return rangeFiltersRangesDateUpdatedTo;
    }

    public void setRangeFiltersRangesDateUpdatedTo(LocalDateTime rangeFiltersRangesDateUpdatedTo) {
        this.rangeFiltersRangesDateUpdatedTo = rangeFiltersRangesDateUpdatedTo;
    }
}

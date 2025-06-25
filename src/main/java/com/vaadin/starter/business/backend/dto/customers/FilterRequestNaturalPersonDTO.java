package com.vaadin.starter.business.backend.dto.customers;

import java.time.LocalDateTime;

/**
 * This class has been replaced by com.catalis.common.customer.sdk.model.FilterRequestNaturalPersonDTO
 * @deprecated Use com.catalis.common.customer.sdk.model.FilterRequestNaturalPersonDTO instead
 */
public class FilterRequestNaturalPersonDTO {
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

    // Default constructor
    public FilterRequestNaturalPersonDTO() {
    }

    // All-args constructor
    public FilterRequestNaturalPersonDTO(Integer paginationPageNumber, Integer paginationPageSize, String paginationSortBy,
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

    public String getFiltersTitleDescription() {
        return filtersTitleDescription;
    }

    public void setFiltersTitleDescription(String filtersTitleDescription) {
        this.filtersTitleDescription = filtersTitleDescription;
    }

    public String getFiltersFirstName() {
        return filtersFirstName;
    }

    public void setFiltersFirstName(String filtersFirstName) {
        this.filtersFirstName = filtersFirstName;
    }

    public String getFiltersMiddleName() {
        return filtersMiddleName;
    }

    public void setFiltersMiddleName(String filtersMiddleName) {
        this.filtersMiddleName = filtersMiddleName;
    }

    public String getFiltersFirstSurname() {
        return filtersFirstSurname;
    }

    public void setFiltersFirstSurname(String filtersFirstSurname) {
        this.filtersFirstSurname = filtersFirstSurname;
    }

    public String getFiltersSecondSurname() {
        return filtersSecondSurname;
    }

    public void setFiltersSecondSurname(String filtersSecondSurname) {
        this.filtersSecondSurname = filtersSecondSurname;
    }

    public String getFiltersDateOfBirth() {
        return filtersDateOfBirth;
    }

    public void setFiltersDateOfBirth(String filtersDateOfBirth) {
        this.filtersDateOfBirth = filtersDateOfBirth;
    }

    public String getFiltersPlaceOfBirth() {
        return filtersPlaceOfBirth;
    }

    public void setFiltersPlaceOfBirth(String filtersPlaceOfBirth) {
        this.filtersPlaceOfBirth = filtersPlaceOfBirth;
    }

    public String getFiltersNationalityDescription() {
        return filtersNationalityDescription;
    }

    public void setFiltersNationalityDescription(String filtersNationalityDescription) {
        this.filtersNationalityDescription = filtersNationalityDescription;
    }

    public String getFiltersGender() {
        return filtersGender;
    }

    public void setFiltersGender(String filtersGender) {
        this.filtersGender = filtersGender;
    }

    public String getFiltersMaritalStatus() {
        return filtersMaritalStatus;
    }

    public void setFiltersMaritalStatus(String filtersMaritalStatus) {
        this.filtersMaritalStatus = filtersMaritalStatus;
    }

    public String getFiltersTaxIdentificationNumber() {
        return filtersTaxIdentificationNumber;
    }

    public void setFiltersTaxIdentificationNumber(String filtersTaxIdentificationNumber) {
        this.filtersTaxIdentificationNumber = filtersTaxIdentificationNumber;
    }

    public String getFiltersResidencyStatus() {
        return filtersResidencyStatus;
    }

    public void setFiltersResidencyStatus(String filtersResidencyStatus) {
        this.filtersResidencyStatus = filtersResidencyStatus;
    }

    public String getFiltersCountryOfResidenceDescription() {
        return filtersCountryOfResidenceDescription;
    }

    public void setFiltersCountryOfResidenceDescription(String filtersCountryOfResidenceDescription) {
        this.filtersCountryOfResidenceDescription = filtersCountryOfResidenceDescription;
    }

    public String getFiltersAvatarUrl() {
        return filtersAvatarUrl;
    }

    public void setFiltersAvatarUrl(String filtersAvatarUrl) {
        this.filtersAvatarUrl = filtersAvatarUrl;
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

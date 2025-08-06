package com.vaadin.starter.business.backend.sdks.services.rest.masterdata;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering countries.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CountryFilterRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;

    private String filtersIsoCode;
    private String filtersCountryName;
    private Long filtersRegionLkpId;
    private Boolean filtersIsActive;
    private String filtersSvgFlag;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;
}

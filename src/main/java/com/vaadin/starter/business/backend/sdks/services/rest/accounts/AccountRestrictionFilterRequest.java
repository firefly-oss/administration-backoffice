package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering account restrictions.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRestrictionFilterRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private Long filtersAccountId;
    private String filtersType;
    private String filtersValue;
    private String filtersDescription;
    private String filtersStatus;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;
}
package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering account balances.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountBalanceFilterRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private Long filtersAccountId;
    private String filtersBalanceType;
    private Double filtersAmount;
    private Double rangeFiltersRangesAmountFrom;
    private Double rangeFiltersRangesAmountTo;
    private String filtersCurrency;
    private String filtersStatus;
    private String filtersDescription;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;
}
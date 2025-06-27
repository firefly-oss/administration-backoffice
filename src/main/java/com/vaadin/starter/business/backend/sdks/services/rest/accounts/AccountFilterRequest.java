package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering accounts.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountFilterRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private String filtersAccountNumber;
    private String filtersAccountType;
    private String filtersCurrency;
    private Double filtersBalance;
    private Double rangeFiltersRangesBalanceFrom;
    private Double rangeFiltersRangesBalanceTo;
    private String filtersStatus;
    private Long filtersCustomerId;
    private String filtersDescription;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;
}
package com.vaadin.starter.business.backend.sdks.services.rest.contracts;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Request object for filtering contracts.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContractFilterRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private String filtersContractNumber;
    private String filtersContractType;
    private String filtersStatus;
    private LocalDate filtersStartDate;
    private LocalDate rangeFiltersRangesStartDateFrom;
    private LocalDate rangeFiltersRangesStartDateTo;
    private LocalDate filtersEndDate;
    private LocalDate rangeFiltersRangesEndDateFrom;
    private LocalDate rangeFiltersRangesEndDateTo;
    private Long filtersCustomerId;
    private String filtersDescription;
    private Double filtersAmount;
    private Double rangeFiltersRangesAmountFrom;
    private Double rangeFiltersRangesAmountTo;
    private String filtersCurrency;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;
}
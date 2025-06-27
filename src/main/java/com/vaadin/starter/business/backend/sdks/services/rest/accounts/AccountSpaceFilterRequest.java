package com.vaadin.starter.business.backend.sdks.services.rest.accounts;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering account spaces.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountSpaceFilterRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private Integer filtersAccountId;
    private String filtersSpaceName;
    private String filtersSpaceType;
    private String filtersBalance;
    private String rangeFiltersRangesBalanceFrom;
    private String rangeFiltersRangesBalanceTo;
    private String filtersTargetAmount;
    private String rangeFiltersRangesTargetAmountFrom;
    private String rangeFiltersRangesTargetAmountTo;
    private LocalDateTime filtersTargetDate;
    private LocalDateTime rangeFiltersRangesTargetDateFrom;
    private LocalDateTime rangeFiltersRangesTargetDateTo;
    private String filtersColorCode;
    private String filtersDescription;
    private Boolean filtersIsVisible;
    private String filtersGoalProgressPercentage;
    private String rangeFiltersRangesGoalProgressPercentageFrom;
    private String rangeFiltersRangesGoalProgressPercentageTo;
    private String filtersRemainingToTarget;
    private String rangeFiltersRangesRemainingToTargetFrom;
    private String rangeFiltersRangesRemainingToTargetTo;
    private LocalDateTime filtersEstimatedCompletionDate;
    private LocalDateTime rangeFiltersRangesEstimatedCompletionDateFrom;
    private LocalDateTime rangeFiltersRangesEstimatedCompletionDateTo;
    private Boolean filtersIsGoalCompleted;
    private Boolean filtersEnableAutomaticTransfers;
    private String filtersTransferFrequency;
    private String filtersTransferAmount;
    private String rangeFiltersRangesTransferAmountFrom;
    private String rangeFiltersRangesTransferAmountTo;
    private String filtersAverageGrowthRate;
    private String rangeFiltersRangesAverageGrowthRateFrom;
    private String rangeFiltersRangesAverageGrowthRateTo;
    private String filtersContributionToAccountTotal;
    private String rangeFiltersRangesContributionToAccountTotalFrom;
    private String rangeFiltersRangesContributionToAccountTotalTo;
    private Boolean filtersIsFrozen;
    private LocalDateTime filtersFrozenDateTime;
    private LocalDateTime rangeFiltersRangesFrozenDateTimeFrom;
    private LocalDateTime rangeFiltersRangesFrozenDateTimeTo;
    private LocalDateTime filtersUnfrozenDateTime;
    private LocalDateTime rangeFiltersRangesUnfrozenDateTimeFrom;
    private LocalDateTime rangeFiltersRangesUnfrozenDateTimeTo;
    private String filtersLastBalanceUpdateReason;
    private LocalDateTime filtersLastBalanceUpdateDateTime;
    private LocalDateTime rangeFiltersRangesLastBalanceUpdateDateTimeFrom;
    private LocalDateTime rangeFiltersRangesLastBalanceUpdateDateTimeTo;
    private LocalDateTime filtersDateCreated;
    private LocalDateTime rangeFiltersRangesDateCreatedFrom;
    private LocalDateTime rangeFiltersRangesDateCreatedTo;
    private LocalDateTime filtersDateUpdated;
    private LocalDateTime rangeFiltersRangesDateUpdatedFrom;
    private LocalDateTime rangeFiltersRangesDateUpdatedTo;
}
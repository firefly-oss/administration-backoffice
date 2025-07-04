package com.vaadin.starter.business.backend.sdks.services.rest.cards;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Request object for filtering cards.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardFilterRequest {
    private Integer paginationPageNumber;
    private Integer paginationPageSize;
    private String paginationSortBy;
    private String paginationSortDirection;
    private Boolean optionsCaseInsensitiveStrings;
    private Boolean optionsIncludeInheritedFields;
    private String filtersCardNumber;
    private String filtersCardType;
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
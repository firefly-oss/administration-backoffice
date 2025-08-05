package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.common.reference.master.data.sdk.model.ActivityCodeDTO;
import com.catalis.common.reference.master.data.sdk.model.CountryDTO;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponse;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.ActivityCodeRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Service interface for master data operations.
 */
public interface MasterDataService {

    /**
     * Create a new activity code.
     *
     * @param activityCodeRequest the activity code to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ActivityCodeDTO>> createActivityCode(ActivityCodeRequest activityCodeRequest);

    /**
     * Delete an activity code by ID.
     *
     * @param activityCodeId the ID of the activity code to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteActivityCode(Long activityCodeId);

    /**
     * Get an activity code by ID.
     *
     * @param activityCodeId the ID of the activity code to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ActivityCodeDTO>> getActivityCode(Long activityCodeId);

    /**
     * Get activity codes by country.
     *
     * @param countryId the ID of the country
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ActivityCodeDTO>> getActivityCodesByCountry(Long countryId);

    /**
     * Get child activity codes.
     *
     * @param parentCodeId the ID of the parent activity code
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ActivityCodeDTO>> getChildActivityCodes(Long parentCodeId);

    /**
     * List activity codes with pagination.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listActivityCodes(String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update an activity code.
     *
     * @param activityCodeId the ID of the activity code to update
     * @param activityCodeRequest the activity code to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<ActivityCodeDTO>> updateActivityCode(Long activityCodeId, ActivityCodeRequest activityCodeRequest);

    /**
     * Create a new country.
     *
     * @param countryRequest the country to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<CountryDTO>> createCountry(CountryRequest countryRequest);

    /**
     * Delete a country by ID.
     *
     * @param countryId the ID of the country to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteCountry(Long countryId);

    /**
     * Get a country by ID.
     *
     * @param countryId the ID of the country to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<CountryDTO>> getCountry(Long countryId);

    /**
     * List countries with pagination.
     *
     * @param pageNumber the page number to retrieve
     * @param pageSize the number of items per page
     * @param sortBy the field to sort by
     * @param sortDirection the direction of sorting
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> listCountries(String pageNumber, String pageSize, String sortBy, String sortDirection);

    /**
     * Update a country.
     *
     * @param countryId the ID of the country to update
     * @param countryRequest the country to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<CountryDTO>> updateCountry(Long countryId, CountryRequest countryRequest);
}

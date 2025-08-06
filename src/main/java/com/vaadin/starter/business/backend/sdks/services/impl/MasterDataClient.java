package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.common.reference.master.data.sdk.api.ActivityCodesApi;
import com.catalis.common.reference.master.data.sdk.api.CountriesApi;
import com.catalis.common.reference.master.data.sdk.invoker.ApiClient;
import com.catalis.common.reference.master.data.sdk.model.ActivityCodeDTO;
import com.catalis.common.reference.master.data.sdk.model.CountryDTO;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponse;
import com.catalis.common.reference.master.data.sdk.model.PaginationResponseCountryDTO;
import com.vaadin.starter.business.backend.mapper.masterdata.MasterDataMapper;
import com.vaadin.starter.business.backend.sdks.services.MasterDataService;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.ActivityCodeRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryFilterRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class MasterDataClient implements MasterDataService {

    private final ActivityCodesApi activityCodesApi;
    private final CountriesApi countriesApi;
    private final MasterDataMapper masterDataMapper;

    @Autowired
    public MasterDataClient(ApiClient apiClient, MasterDataMapper masterDataMapper) {
        this.activityCodesApi = new ActivityCodesApi(apiClient);
        this.countriesApi = new CountriesApi(apiClient);
        this.masterDataMapper = masterDataMapper;
    }

    @Override
    public Mono<ResponseEntity<ActivityCodeDTO>> createActivityCode(ActivityCodeRequest activityCodeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return activityCodesApi.createActivityCodeWithHttpInfo(masterDataMapper.activityCodeRequestToDto(activityCodeRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteActivityCode(Long activityCodeId) {
        return activityCodesApi.deleteActivityCodeWithHttpInfo(activityCodeId);
    }

    @Override
    public Mono<ResponseEntity<ActivityCodeDTO>> getActivityCode(Long activityCodeId) {
        return activityCodesApi.getActivityCodeWithHttpInfo(activityCodeId);
    }

    @Override
    public Mono<ResponseEntity<ActivityCodeDTO>> getActivityCodesByCountry(Long countryId) {
        return activityCodesApi.getActivityCodesByCountryWithHttpInfo(countryId);
    }

    @Override
    public Mono<ResponseEntity<ActivityCodeDTO>> getChildActivityCodes(Long parentCodeId) {
        return activityCodesApi.getChildActivityCodesWithHttpInfo(parentCodeId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> listActivityCodes(String pageNumber, String pageSize, String sortBy, String sortDirection) {
        return activityCodesApi.listActivityCodesWithHttpInfo(pageNumber, pageSize, sortBy, sortDirection);
    }

    @Override
    public Mono<ResponseEntity<ActivityCodeDTO>> updateActivityCode(Long activityCodeId, ActivityCodeRequest activityCodeRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return activityCodesApi.updateActivityCodeWithHttpInfo(activityCodeId, masterDataMapper.activityCodeRequestToDto(activityCodeRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CountryDTO>> createCountry(CountryRequest countryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return countriesApi.createCountryWithHttpInfo(masterDataMapper.countryRequestToDto(countryRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCountry(Long countryId) {
        return countriesApi.deleteCountryWithHttpInfo(countryId);
    }

    @Override
    public Mono<ResponseEntity<CountryDTO>> getCountry(Long countryId) {
        return countriesApi.getCountryWithHttpInfo(countryId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponseCountryDTO>> filterCountries(CountryFilterRequest countryFilterRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return countriesApi.filterCountriesWithHttpInfo(masterDataMapper.countryFilterRequestToDto(countryFilterRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<CountryDTO>> updateCountry(Long countryId, CountryRequest countryRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return countriesApi.updateCountryWithHttpInfo(countryId, masterDataMapper.countryRequestToDto(countryRequest), xIdempotencyKey);
    }
}

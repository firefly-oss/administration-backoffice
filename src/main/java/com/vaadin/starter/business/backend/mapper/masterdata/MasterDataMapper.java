package com.vaadin.starter.business.backend.mapper.masterdata;

import com.catalis.common.reference.master.data.sdk.model.ActivityCodeDTO;
import com.catalis.common.reference.master.data.sdk.model.CountryDTO;
import com.catalis.common.reference.master.data.sdk.model.FilterRequestCountryDTO;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.ActivityCodeRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryFilterRequest;
import com.vaadin.starter.business.backend.sdks.services.rest.masterdata.CountryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Mapper for converting between master data request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface MasterDataMapper {

    /**
     * Convert an ActivityCodeRequest to an ActivityCodeDTO.
     *
     * @param activityCodeRequest the request object
     * @return the DTO object
     */
    ActivityCodeDTO activityCodeRequestToDto(ActivityCodeRequest activityCodeRequest);

    /**
     * Convert a CountryRequest to a CountryDTO.
     *
     * @param countryRequest the request object
     * @return the DTO object
     */
    @Mapping(source = "active", target = "status", qualifiedByName = "booleanToStatusEnum")
    @Mapping(source = "region", target = "region", qualifiedByName = "stringToRegionEnum")
    CountryDTO countryRequestToDto(CountryRequest countryRequest);

    /**
     * Convert a Boolean active value to a StatusEnum.
     *
     * @param active the Boolean active value
     * @return the corresponding StatusEnum value
     */
    @Named("booleanToStatusEnum")
    default CountryDTO.StatusEnum booleanToStatusEnum(Boolean active) {
        if (active == null) {
            return null;
        }
        return active ? CountryDTO.StatusEnum.ACTIVE : CountryDTO.StatusEnum.INACTIVE;
    }

    /**
     * Convert a String region value to a RegionEnum.
     *
     * @param region the String region value
     * @return the corresponding RegionEnum value
     */
    @Named("stringToRegionEnum")
    default CountryDTO.RegionEnum stringToRegionEnum(String region) {
        if (region == null) {
            return null;
        }
        try {
            return CountryDTO.RegionEnum.valueOf(region.toUpperCase());
        } catch (IllegalArgumentException e) {
            // If the exact enum name doesn't match, try to find a close match
            for (CountryDTO.RegionEnum r : CountryDTO.RegionEnum.values()) {
                if (r.name().equalsIgnoreCase(region) || 
                    (r.name().replace("_", " ").equalsIgnoreCase(region))) {
                    return r;
                }
            }
            return null;
        }
    }

    FilterRequestCountryDTO countryFilterRequestToDto(CountryFilterRequest countryFilterRequest);
}

package com.vaadin.starter.business.backend.mapper.catalogs;

import com.catalis.core.erp.inventory.mgmt.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.catalogs.*;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between catalog request objects and SDK DTOs.
 */
@Mapper(componentModel = "spring")
public interface CatalogsMapper {

    /**
     * Convert a CatalogRequest to an InventoryCatalogDTO.
     *
     * @param catalogRequest the request object
     * @return the DTO
     */
    InventoryCatalogDTO catalogRequestToDto(CatalogRequest catalogRequest);

    /**
     * Convert a BatchRequest to an InventoryBatchDTO.
     *
     * @param batchRequest the request object
     * @return the DTO
     */
    InventoryBatchDTO batchRequestToDto(BatchRequest batchRequest);

    /**
     * Convert a BinLocationRequest to an InventoryBinLocationDTO.
     *
     * @param binLocationRequest the request object
     * @return the DTO
     */
    InventoryBinLocationDTO binLocationRequestToDto(BinLocationRequest binLocationRequest);

    /**
     * Convert an ItemVariantRequest to an InventoryItemVariantDTO.
     *
     * @param itemVariantRequest the request object
     * @return the DTO
     */
    InventoryItemVariantDTO itemVariantRequestToDto(ItemVariantRequest itemVariantRequest);

    /**
     * Convert a MovementLogRequest to an InventoryMovementLogDTO.
     *
     * @param movementLogRequest the request object
     * @return the DTO
     */
    InventoryMovementLogDTO movementLogRequestToDto(MovementLogRequest movementLogRequest);

    /**
     * Convert a ReservationRequest to an InventoryReservationDTO.
     *
     * @param reservationRequest the request object
     * @return the DTO
     */
    InventoryReservationDTO reservationRequestToDto(ReservationRequest reservationRequest);

    /**
     * Convert a SerialRequest to an InventorySerialDTO.
     *
     * @param serialRequest the request object
     * @return the DTO
     */
    InventorySerialDTO serialRequestToDto(SerialRequest serialRequest);

    /**
     * Convert a StockRequest to an InventoryStockDTO.
     *
     * @param stockRequest the request object
     * @return the DTO
     */
    InventoryStockDTO stockRequestToDto(StockRequest stockRequest);

    /**
     * Convert a TransferRequest to an InventoryTransferDTO.
     *
     * @param transferRequest the request object
     * @return the DTO
     */
    InventoryTransferDTO transferRequestToDto(TransferRequest transferRequest);

    /**
     * Convert a UomRequest to an InventoryUomDTO.
     *
     * @param uomRequest the request object
     * @return the DTO
     */
    InventoryUomDTO uomRequestToDto(UomRequest uomRequest);

    /**
     * Convert a WarehouseRequest to an InventoryWarehouseDTO.
     *
     * @param warehouseRequest the request object
     * @return the DTO
     */
    InventoryWarehouseDTO warehouseRequestToDto(WarehouseRequest warehouseRequest);
}
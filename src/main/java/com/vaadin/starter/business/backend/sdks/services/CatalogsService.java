package com.vaadin.starter.business.backend.sdks.services;

import com.catalis.core.erp.inventory.mgmt.sdk.model.*;
import com.vaadin.starter.business.backend.sdks.services.rest.catalogs.*;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * Service interface for catalog operations.
 */
public interface CatalogsService {

    // CatalogManagementApi methods
    
    /**
     * Create a new catalog.
     *
     * @param catalogRequest the catalog to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryCatalogDTO>> createCatalog(CatalogRequest catalogRequest);

    /**
     * Delete a catalog by ID.
     *
     * @param catalogId the ID of the catalog to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteCatalog(Long catalogId);

    /**
     * Filter catalogs based on criteria.
     *
     * @param filterRequestInventoryCatalogDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterCatalogs(FilterRequestInventoryCatalogDTO filterRequestInventoryCatalogDTO);

    /**
     * Get a catalog by ID.
     *
     * @param catalogId the ID of the catalog to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryCatalogDTO>> getCatalogById(Long catalogId);

    /**
     * Update a catalog.
     *
     * @param catalogId the ID of the catalog to update
     * @param catalogRequest the catalog to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryCatalogDTO>> updateCatalog(Long catalogId, CatalogRequest catalogRequest);

    // BatchManagementApi methods
    
    /**
     * Create a new batch.
     *
     * @param batchRequest the batch to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryBatchDTO>> createBatch(BatchRequest batchRequest);

    /**
     * Delete a batch by ID.
     *
     * @param batchId the ID of the batch to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteBatch(Long batchId);

    /**
     * Filter batches based on criteria.
     *
     * @param filterRequestInventoryBatchDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterBatches(FilterRequestInventoryBatchDTO filterRequestInventoryBatchDTO);

    /**
     * Get a batch by ID.
     *
     * @param batchId the ID of the batch to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryBatchDTO>> getBatchById(Long batchId);

    /**
     * Update a batch.
     *
     * @param batchId the ID of the batch to update
     * @param batchRequest the batch to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryBatchDTO>> updateBatch(Long batchId, BatchRequest batchRequest);

    // BinLocationManagementApi methods
    
    /**
     * Create a new bin location in warehouse.
     *
     * @param warehouseId the ID of the warehouse
     * @param binLocationRequest the bin location to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryBinLocationDTO>> createBinLocationInWarehouse(Long warehouseId, BinLocationRequest binLocationRequest);

    /**
     * Delete a bin location by ID.
     *
     * @param binLocationId the ID of the bin location to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteBinLocation(Long binLocationId);

    /**
     * Filter bin locations based on criteria.
     *
     * @param filterRequestInventoryBinLocationDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterBinLocations(FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO);

    /**
     * Filter bin locations by warehouse.
     *
     * @param warehouseId the ID of the warehouse
     * @param filterRequestInventoryBinLocationDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterBinLocationsByWarehouse(Long warehouseId, FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO);

    /**
     * Get a bin location by ID.
     *
     * @param binLocationId the ID of the bin location to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryBinLocationDTO>> getBinLocationById(Long binLocationId);

    /**
     * Update a bin location.
     *
     * @param binLocationId the ID of the bin location to update
     * @param binLocationRequest the bin location to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryBinLocationDTO>> updateBinLocation(Long binLocationId, BinLocationRequest binLocationRequest);

    // ItemVariantManagementApi methods
    
    /**
     * Create a new item variant in catalog.
     *
     * @param catalogId the ID of the catalog
     * @param itemVariantRequest the item variant to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryItemVariantDTO>> createItemVariantInCatalog(Long catalogId, ItemVariantRequest itemVariantRequest);

    /**
     * Delete an item variant by ID.
     *
     * @param itemVariantId the ID of the item variant to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteItemVariant(Long itemVariantId);

    /**
     * Filter item variants based on criteria.
     *
     * @param filterRequestInventoryItemVariantDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterItemVariants(FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO);

    /**
     * Filter item variants by catalog.
     *
     * @param catalogId the ID of the catalog
     * @param filterRequestInventoryItemVariantDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterItemVariantsByCatalog(Long catalogId, FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO);

    /**
     * Get an item variant by ID.
     *
     * @param itemVariantId the ID of the item variant to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryItemVariantDTO>> getItemVariantById(Long itemVariantId);

    /**
     * Update an item variant.
     *
     * @param itemVariantId the ID of the item variant to update
     * @param itemVariantRequest the item variant to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryItemVariantDTO>> updateItemVariant(Long itemVariantId, ItemVariantRequest itemVariantRequest);

    // MovementLogManagementApi methods
    
    /**
     * Create a new movement log.
     *
     * @param movementLogRequest the movement log to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryMovementLogDTO>> createMovementLog(MovementLogRequest movementLogRequest);

    /**
     * Delete a movement log by ID.
     *
     * @param movementLogId the ID of the movement log to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteMovementLog(Long movementLogId);

    /**
     * Filter movement logs based on criteria.
     *
     * @param filterRequestInventoryMovementLogDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterMovementLogs(FilterRequestInventoryMovementLogDTO filterRequestInventoryMovementLogDTO);

    /**
     * Get a movement log by ID.
     *
     * @param movementLogId the ID of the movement log to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryMovementLogDTO>> getMovementLogById(Long movementLogId);

    /**
     * Update a movement log.
     *
     * @param movementLogId the ID of the movement log to update
     * @param movementLogRequest the movement log to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryMovementLogDTO>> updateMovementLog(Long movementLogId, MovementLogRequest movementLogRequest);

    // ReservationManagementApi methods
    
    /**
     * Create a new reservation.
     *
     * @param reservationRequest the reservation to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryReservationDTO>> createReservation(ReservationRequest reservationRequest);

    /**
     * Delete a reservation by ID.
     *
     * @param reservationId the ID of the reservation to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteReservation(Long reservationId);

    /**
     * Filter reservations based on criteria.
     *
     * @param filterRequestInventoryReservationDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterReservations(FilterRequestInventoryReservationDTO filterRequestInventoryReservationDTO);

    /**
     * Get a reservation by ID.
     *
     * @param reservationId the ID of the reservation to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryReservationDTO>> getReservationById(Long reservationId);

    /**
     * Update a reservation.
     *
     * @param reservationId the ID of the reservation to update
     * @param reservationRequest the reservation to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryReservationDTO>> updateReservation(Long reservationId, ReservationRequest reservationRequest);

    // SerialManagementApi methods
    
    /**
     * Create a new serial.
     *
     * @param serialRequest the serial to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventorySerialDTO>> createSerial(SerialRequest serialRequest);

    /**
     * Delete a serial by ID.
     *
     * @param serialId the ID of the serial to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteSerial(Long serialId);

    /**
     * Filter serials based on criteria.
     *
     * @param filterRequestInventorySerialDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterSerials(FilterRequestInventorySerialDTO filterRequestInventorySerialDTO);

    /**
     * Get a serial by ID.
     *
     * @param serialId the ID of the serial to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventorySerialDTO>> getSerialById(Long serialId);

    /**
     * Update a serial.
     *
     * @param serialId the ID of the serial to update
     * @param serialRequest the serial to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventorySerialDTO>> updateSerial(Long serialId, SerialRequest serialRequest);

    // StockManagementApi methods
    
    /**
     * Create a new stock for item variant.
     *
     * @param itemVariantId the ID of the item variant
     * @param stockRequest the stock to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryStockDTO>> createStockForItemVariant(Long itemVariantId, StockRequest stockRequest);

    /**
     * Create a new stock in bin location.
     *
     * @param warehouseId the ID of the warehouse
     * @param binLocationId the ID of the bin location
     * @param stockRequest the stock to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryStockDTO>> createStockInBinLocation(Long warehouseId, Long binLocationId, StockRequest stockRequest);

    /**
     * Create a new stock in warehouse.
     *
     * @param warehouseId the ID of the warehouse
     * @param stockRequest the stock to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryStockDTO>> createStockInWarehouse(Long warehouseId, StockRequest stockRequest);

    /**
     * Delete a stock by ID.
     *
     * @param stockId the ID of the stock to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteStock(Long stockId);

    /**
     * Filter stocks based on criteria.
     *
     * @param filterRequestInventoryStockDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterStocks(FilterRequestInventoryStockDTO filterRequestInventoryStockDTO);

    /**
     * Filter stocks by bin location.
     *
     * @param warehouseId the ID of the warehouse
     * @param binLocationId the ID of the bin location
     * @param filterRequestInventoryStockDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterStocksByBinLocation(Long warehouseId, Long binLocationId, FilterRequestInventoryStockDTO filterRequestInventoryStockDTO);

    /**
     * Filter stocks by item variant.
     *
     * @param itemVariantId the ID of the item variant
     * @param filterRequestInventoryStockDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterStocksByItemVariant(Long itemVariantId, FilterRequestInventoryStockDTO filterRequestInventoryStockDTO);

    /**
     * Filter stocks by warehouse.
     *
     * @param warehouseId the ID of the warehouse
     * @param filterRequestInventoryStockDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterStocksByWarehouse(Long warehouseId, FilterRequestInventoryStockDTO filterRequestInventoryStockDTO);

    /**
     * Get a stock by ID.
     *
     * @param stockId the ID of the stock to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryStockDTO>> getStockById(Long stockId);

    /**
     * Update a stock.
     *
     * @param stockId the ID of the stock to update
     * @param stockRequest the stock to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryStockDTO>> updateStock(Long stockId, StockRequest stockRequest);

    // TransferManagementApi methods
    
    /**
     * Create a new transfer.
     *
     * @param transferRequest the transfer to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryTransferDTO>> createTransfer(TransferRequest transferRequest);

    /**
     * Delete a transfer by ID.
     *
     * @param transferId the ID of the transfer to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteTransfer(Long transferId);

    /**
     * Filter transfers based on criteria.
     *
     * @param filterRequestInventoryTransferDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterTransfers(FilterRequestInventoryTransferDTO filterRequestInventoryTransferDTO);

    /**
     * Get a transfer by ID.
     *
     * @param transferId the ID of the transfer to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryTransferDTO>> getTransferById(Long transferId);

    /**
     * Update a transfer.
     *
     * @param transferId the ID of the transfer to update
     * @param transferRequest the transfer to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryTransferDTO>> updateTransfer(Long transferId, TransferRequest transferRequest);

    // UnitOfMeasureManagementApi methods
    
    /**
     * Create a new unit of measure.
     *
     * @param uomRequest the unit of measure to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryUomDTO>> createUom(UomRequest uomRequest);

    /**
     * Delete a unit of measure by ID.
     *
     * @param uomId the ID of the unit of measure to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteUom(Long uomId);

    /**
     * Filter units of measure based on criteria.
     *
     * @param filterRequestInventoryUomDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterUoms(FilterRequestInventoryUomDTO filterRequestInventoryUomDTO);

    /**
     * Get a unit of measure by ID.
     *
     * @param uomId the ID of the unit of measure to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryUomDTO>> getUomById(Long uomId);

    /**
     * Update a unit of measure.
     *
     * @param uomId the ID of the unit of measure to update
     * @param uomRequest the unit of measure to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryUomDTO>> updateUom(Long uomId, UomRequest uomRequest);

    // WarehouseManagementApi methods
    
    /**
     * Create a new warehouse.
     *
     * @param warehouseRequest the warehouse to create
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryWarehouseDTO>> createWarehouse(WarehouseRequest warehouseRequest);

    /**
     * Delete a warehouse by ID.
     *
     * @param warehouseId the ID of the warehouse to delete
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<Void>> deleteWarehouse(Long warehouseId);

    /**
     * Filter warehouses based on criteria.
     *
     * @param filterRequestInventoryWarehouseDTO the filter criteria
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<PaginationResponse>> filterWarehouses(FilterRequestInventoryWarehouseDTO filterRequestInventoryWarehouseDTO);

    /**
     * Get a warehouse by ID.
     *
     * @param warehouseId the ID of the warehouse to get
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryWarehouseDTO>> getWarehouseById(Long warehouseId);

    /**
     * Update a warehouse.
     *
     * @param warehouseId the ID of the warehouse to update
     * @param warehouseRequest the warehouse to update
     * @return a Mono with the response entity
     */
    Mono<ResponseEntity<InventoryWarehouseDTO>> updateWarehouse(Long warehouseId, WarehouseRequest warehouseRequest);
}
package com.vaadin.starter.business.backend.sdks.services.impl;

import com.catalis.core.erp.inventory.mgmt.sdk.api.*;
import com.catalis.core.erp.inventory.mgmt.sdk.invoker.ApiClient;
import com.catalis.core.erp.inventory.mgmt.sdk.model.*;
import com.vaadin.starter.business.backend.mapper.catalogs.CatalogsMapper;
import com.vaadin.starter.business.backend.sdks.services.CatalogsService;
import com.vaadin.starter.business.backend.sdks.services.rest.catalogs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CatalogsClient implements CatalogsService {

    private final CatalogManagementApi catalogManagementApi;
    private final BatchManagementApi batchManagementApi;
    private final BinLocationManagementApi binLocationManagementApi;
    private final ItemVariantManagementApi itemVariantManagementApi;
    private final MovementLogManagementApi movementLogManagementApi;
    private final ReservationManagementApi reservationManagementApi;
    private final SerialManagementApi serialManagementApi;
    private final StockManagementApi stockManagementApi;
    private final TransferManagementApi transferManagementApi;
    private final UnitOfMeasureManagementApi unitOfMeasureManagementApi;
    private final WarehouseManagementApi warehouseManagementApi;
    private final CatalogsMapper catalogsMapper;

    @Autowired
    public CatalogsClient(ApiClient apiClient, CatalogsMapper catalogsMapper) {
        this.catalogManagementApi = new CatalogManagementApi(apiClient);
        this.batchManagementApi = new BatchManagementApi(apiClient);
        this.binLocationManagementApi = new BinLocationManagementApi(apiClient);
        this.itemVariantManagementApi = new ItemVariantManagementApi(apiClient);
        this.movementLogManagementApi = new MovementLogManagementApi(apiClient);
        this.reservationManagementApi = new ReservationManagementApi(apiClient);
        this.serialManagementApi = new SerialManagementApi(apiClient);
        this.stockManagementApi = new StockManagementApi(apiClient);
        this.transferManagementApi = new TransferManagementApi(apiClient);
        this.unitOfMeasureManagementApi = new UnitOfMeasureManagementApi(apiClient);
        this.warehouseManagementApi = new WarehouseManagementApi(apiClient);
        this.catalogsMapper = catalogsMapper;
    }

    // CatalogManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryCatalogDTO>> createCatalog(CatalogRequest catalogRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return catalogManagementApi.createCatalogWithHttpInfo(catalogsMapper.catalogRequestToDto(catalogRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCatalog(Long catalogId) {
        return catalogManagementApi.deleteCatalogWithHttpInfo(catalogId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterCatalogs(FilterRequestInventoryCatalogDTO filterRequestInventoryCatalogDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return catalogManagementApi.filterCatalogsWithHttpInfo(filterRequestInventoryCatalogDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryCatalogDTO>> getCatalogById(Long catalogId) {
        return catalogManagementApi.getCatalogByIdWithHttpInfo(catalogId);
    }

    @Override
    public Mono<ResponseEntity<InventoryCatalogDTO>> updateCatalog(Long catalogId, CatalogRequest catalogRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return catalogManagementApi.updateCatalogWithHttpInfo(catalogId, catalogsMapper.catalogRequestToDto(catalogRequest), xIdempotencyKey);
    }

    // BatchManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryBatchDTO>> createBatch(BatchRequest batchRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return batchManagementApi.createBatchWithHttpInfo(catalogsMapper.batchRequestToDto(batchRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBatch(Long batchId) {
        return batchManagementApi.deleteBatchWithHttpInfo(batchId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterBatches(FilterRequestInventoryBatchDTO filterRequestInventoryBatchDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return batchManagementApi.filterBatchesWithHttpInfo(filterRequestInventoryBatchDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryBatchDTO>> getBatchById(Long batchId) {
        return batchManagementApi.getBatchByIdWithHttpInfo(batchId);
    }

    @Override
    public Mono<ResponseEntity<InventoryBatchDTO>> updateBatch(Long batchId, BatchRequest batchRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return batchManagementApi.updateBatchWithHttpInfo(batchId, catalogsMapper.batchRequestToDto(batchRequest), xIdempotencyKey);
    }

    // BinLocationManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryBinLocationDTO>> createBinLocationInWarehouse(Long warehouseId, BinLocationRequest binLocationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return binLocationManagementApi.createBinLocationInWarehouseWithHttpInfo(warehouseId, catalogsMapper.binLocationRequestToDto(binLocationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBinLocation(Long binLocationId) {
        return binLocationManagementApi.deleteBinLocationWithHttpInfo(binLocationId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterBinLocations(FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return binLocationManagementApi.filterBinLocationsWithHttpInfo(filterRequestInventoryBinLocationDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterBinLocationsByWarehouse(Long warehouseId, FilterRequestInventoryBinLocationDTO filterRequestInventoryBinLocationDTO) {
        return binLocationManagementApi.filterBinLocationsByWarehouseWithHttpInfo(warehouseId, filterRequestInventoryBinLocationDTO);
    }

    @Override
    public Mono<ResponseEntity<InventoryBinLocationDTO>> getBinLocationById(Long binLocationId) {
        return binLocationManagementApi.getBinLocationByIdWithHttpInfo(binLocationId);
    }

    @Override
    public Mono<ResponseEntity<InventoryBinLocationDTO>> updateBinLocation(Long binLocationId, BinLocationRequest binLocationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return binLocationManagementApi.updateBinLocationWithHttpInfo(binLocationId, catalogsMapper.binLocationRequestToDto(binLocationRequest), xIdempotencyKey);
    }

    // ItemVariantManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryItemVariantDTO>> createItemVariantInCatalog(Long catalogId, ItemVariantRequest itemVariantRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return itemVariantManagementApi.createItemVariantInCatalogWithHttpInfo(catalogId, catalogsMapper.itemVariantRequestToDto(itemVariantRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteItemVariant(Long itemVariantId) {
        return itemVariantManagementApi.deleteItemVariantWithHttpInfo(itemVariantId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterItemVariants(FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return itemVariantManagementApi.filterItemVariantsWithHttpInfo(filterRequestInventoryItemVariantDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterItemVariantsByCatalog(Long catalogId, FilterRequestInventoryItemVariantDTO filterRequestInventoryItemVariantDTO) {
        return itemVariantManagementApi.filterItemVariantsByCatalogWithHttpInfo(catalogId, filterRequestInventoryItemVariantDTO);
    }

    @Override
    public Mono<ResponseEntity<InventoryItemVariantDTO>> getItemVariantById(Long itemVariantId) {
        return itemVariantManagementApi.getItemVariantByIdWithHttpInfo(itemVariantId);
    }

    @Override
    public Mono<ResponseEntity<InventoryItemVariantDTO>> updateItemVariant(Long itemVariantId, ItemVariantRequest itemVariantRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return itemVariantManagementApi.updateItemVariantWithHttpInfo(itemVariantId, catalogsMapper.itemVariantRequestToDto(itemVariantRequest), xIdempotencyKey);
    }

    // MovementLogManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryMovementLogDTO>> createMovementLog(MovementLogRequest movementLogRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return movementLogManagementApi.createMovementLogWithHttpInfo(catalogsMapper.movementLogRequestToDto(movementLogRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteMovementLog(Long movementLogId) {
        return movementLogManagementApi.deleteMovementLogWithHttpInfo(movementLogId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterMovementLogs(FilterRequestInventoryMovementLogDTO filterRequestInventoryMovementLogDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return movementLogManagementApi.filterMovementLogsWithHttpInfo(filterRequestInventoryMovementLogDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryMovementLogDTO>> getMovementLogById(Long movementLogId) {
        return movementLogManagementApi.getMovementLogByIdWithHttpInfo(movementLogId);
    }

    @Override
    public Mono<ResponseEntity<InventoryMovementLogDTO>> updateMovementLog(Long movementLogId, MovementLogRequest movementLogRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return movementLogManagementApi.updateMovementLogWithHttpInfo(movementLogId, catalogsMapper.movementLogRequestToDto(movementLogRequest), xIdempotencyKey);
    }

    // ReservationManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryReservationDTO>> createReservation(ReservationRequest reservationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return reservationManagementApi.createReservationWithHttpInfo(catalogsMapper.reservationRequestToDto(reservationRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteReservation(Long reservationId) {
        return reservationManagementApi.deleteReservationWithHttpInfo(reservationId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterReservations(FilterRequestInventoryReservationDTO filterRequestInventoryReservationDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return reservationManagementApi.filterReservationsWithHttpInfo(filterRequestInventoryReservationDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryReservationDTO>> getReservationById(Long reservationId) {
        return reservationManagementApi.getReservationByIdWithHttpInfo(reservationId);
    }

    @Override
    public Mono<ResponseEntity<InventoryReservationDTO>> updateReservation(Long reservationId, ReservationRequest reservationRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return reservationManagementApi.updateReservationWithHttpInfo(reservationId, catalogsMapper.reservationRequestToDto(reservationRequest), xIdempotencyKey);
    }

    // SerialManagementApi methods

    @Override
    public Mono<ResponseEntity<InventorySerialDTO>> createSerial(SerialRequest serialRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return serialManagementApi.createSerialWithHttpInfo(catalogsMapper.serialRequestToDto(serialRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteSerial(Long serialId) {
        return serialManagementApi.deleteSerialWithHttpInfo(serialId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterSerials(FilterRequestInventorySerialDTO filterRequestInventorySerialDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return serialManagementApi.filterSerialsWithHttpInfo(filterRequestInventorySerialDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventorySerialDTO>> getSerialById(Long serialId) {
        return serialManagementApi.getSerialByIdWithHttpInfo(serialId);
    }

    @Override
    public Mono<ResponseEntity<InventorySerialDTO>> updateSerial(Long serialId, SerialRequest serialRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return serialManagementApi.updateSerialWithHttpInfo(serialId, catalogsMapper.serialRequestToDto(serialRequest), xIdempotencyKey);
    }

    // StockManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryStockDTO>> createStockForItemVariant(Long itemVariantId, StockRequest stockRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return stockManagementApi.createStockForItemVariantWithHttpInfo(itemVariantId, catalogsMapper.stockRequestToDto(stockRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryStockDTO>> createStockInBinLocation(Long warehouseId, Long binLocationId, StockRequest stockRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return stockManagementApi.createStockInBinLocationWithHttpInfo(warehouseId, binLocationId, catalogsMapper.stockRequestToDto(stockRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryStockDTO>> createStockInWarehouse(Long warehouseId, StockRequest stockRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return stockManagementApi.createStockInWarehouseWithHttpInfo(warehouseId, catalogsMapper.stockRequestToDto(stockRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteStock(Long stockId) {
        return stockManagementApi.deleteStockWithHttpInfo(stockId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterStocks(FilterRequestInventoryStockDTO filterRequestInventoryStockDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return stockManagementApi.filterStocksWithHttpInfo(filterRequestInventoryStockDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterStocksByBinLocation(Long warehouseId, Long binLocationId, FilterRequestInventoryStockDTO filterRequestInventoryStockDTO) {
        return stockManagementApi.filterStocksByBinLocationWithHttpInfo(warehouseId, binLocationId, filterRequestInventoryStockDTO);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterStocksByItemVariant(Long itemVariantId, FilterRequestInventoryStockDTO filterRequestInventoryStockDTO) {
        return stockManagementApi.filterStocksByItemVariantWithHttpInfo(itemVariantId, filterRequestInventoryStockDTO);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterStocksByWarehouse(Long warehouseId, FilterRequestInventoryStockDTO filterRequestInventoryStockDTO) {
        return stockManagementApi.filterStocksByWarehouseWithHttpInfo(warehouseId, filterRequestInventoryStockDTO);
    }

    @Override
    public Mono<ResponseEntity<InventoryStockDTO>> getStockById(Long stockId) {
        return stockManagementApi.getStockByIdWithHttpInfo(stockId);
    }

    @Override
    public Mono<ResponseEntity<InventoryStockDTO>> updateStock(Long stockId, StockRequest stockRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return stockManagementApi.updateStockWithHttpInfo(stockId, catalogsMapper.stockRequestToDto(stockRequest), xIdempotencyKey);
    }

    // TransferManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryTransferDTO>> createTransfer(TransferRequest transferRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return transferManagementApi.createTransferWithHttpInfo(catalogsMapper.transferRequestToDto(transferRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteTransfer(Long transferId) {
        return transferManagementApi.deleteTransferWithHttpInfo(transferId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterTransfers(FilterRequestInventoryTransferDTO filterRequestInventoryTransferDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return transferManagementApi.filterTransfersWithHttpInfo(filterRequestInventoryTransferDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryTransferDTO>> getTransferById(Long transferId) {
        return transferManagementApi.getTransferByIdWithHttpInfo(transferId);
    }

    @Override
    public Mono<ResponseEntity<InventoryTransferDTO>> updateTransfer(Long transferId, TransferRequest transferRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return transferManagementApi.updateTransferWithHttpInfo(transferId, catalogsMapper.transferRequestToDto(transferRequest), xIdempotencyKey);
    }

    // UnitOfMeasureManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryUomDTO>> createUom(UomRequest uomRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return unitOfMeasureManagementApi.createUomWithHttpInfo(catalogsMapper.uomRequestToDto(uomRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteUom(Long uomId) {
        return unitOfMeasureManagementApi.deleteUomWithHttpInfo(uomId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterUoms(FilterRequestInventoryUomDTO filterRequestInventoryUomDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return unitOfMeasureManagementApi.filterUomsWithHttpInfo(filterRequestInventoryUomDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryUomDTO>> getUomById(Long uomId) {
        return unitOfMeasureManagementApi.getUomByIdWithHttpInfo(uomId);
    }

    @Override
    public Mono<ResponseEntity<InventoryUomDTO>> updateUom(Long uomId, UomRequest uomRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return unitOfMeasureManagementApi.updateUomWithHttpInfo(uomId, catalogsMapper.uomRequestToDto(uomRequest), xIdempotencyKey);
    }

    // WarehouseManagementApi methods

    @Override
    public Mono<ResponseEntity<InventoryWarehouseDTO>> createWarehouse(WarehouseRequest warehouseRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return warehouseManagementApi.createWarehouseWithHttpInfo(catalogsMapper.warehouseRequestToDto(warehouseRequest), xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteWarehouse(Long warehouseId) {
        return warehouseManagementApi.deleteWarehouseWithHttpInfo(warehouseId);
    }

    @Override
    public Mono<ResponseEntity<PaginationResponse>> filterWarehouses(FilterRequestInventoryWarehouseDTO filterRequestInventoryWarehouseDTO) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return warehouseManagementApi.filterWarehousesWithHttpInfo(filterRequestInventoryWarehouseDTO, xIdempotencyKey);
    }

    @Override
    public Mono<ResponseEntity<InventoryWarehouseDTO>> getWarehouseById(Long warehouseId) {
        return warehouseManagementApi.getWarehouseByIdWithHttpInfo(warehouseId);
    }

    @Override
    public Mono<ResponseEntity<InventoryWarehouseDTO>> updateWarehouse(Long warehouseId, WarehouseRequest warehouseRequest) {
        String xIdempotencyKey = UUID.randomUUID().toString();
        return warehouseManagementApi.updateWarehouseWithHttpInfo(warehouseId, catalogsMapper.warehouseRequestToDto(warehouseRequest), xIdempotencyKey);
    }
}

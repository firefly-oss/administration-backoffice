package com.vaadin.starter.business.backend.sdks.services.rest.distributors;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Request object for shipment operations.
 */
public class ShipmentRequest {
    private Long id;
    private String trackingNumber;
    private String status;
    private LocalDateTime shipDate;
    private LocalDateTime estimatedDeliveryDate;
    private LocalDateTime actualDeliveryDate;
    private String carrier;
    private String serviceLevel;
    private Double weight;
    private String weightUnit;
    private String fromAddress;
    private String toAddress;
    private Long leasingContractId;
    private Long productId;
    private String notes;

    /**
     * Default constructor.
     */
    public ShipmentRequest() {
    }

    /**
     * Get the shipment ID.
     *
     * @return the shipment ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the shipment ID.
     *
     * @param id the shipment ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the tracking number.
     *
     * @return the tracking number
     */
    public String getTrackingNumber() {
        return trackingNumber;
    }

    /**
     * Set the tracking number.
     *
     * @param trackingNumber the tracking number
     */
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    /**
     * Get the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get the ship date.
     *
     * @return the ship date
     */
    public LocalDateTime getShipDate() {
        return shipDate;
    }

    /**
     * Set the ship date.
     *
     * @param shipDate the ship date
     */
    public void setShipDate(LocalDateTime shipDate) {
        this.shipDate = shipDate;
    }

    /**
     * Get the estimated delivery date.
     *
     * @return the estimated delivery date
     */
    public LocalDateTime getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    /**
     * Set the estimated delivery date.
     *
     * @param estimatedDeliveryDate the estimated delivery date
     */
    public void setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    /**
     * Get the actual delivery date.
     *
     * @return the actual delivery date
     */
    public LocalDateTime getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    /**
     * Set the actual delivery date.
     *
     * @param actualDeliveryDate the actual delivery date
     */
    public void setActualDeliveryDate(LocalDateTime actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    /**
     * Get the carrier.
     *
     * @return the carrier
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Set the carrier.
     *
     * @param carrier the carrier
     */
    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     * Get the service level.
     *
     * @return the service level
     */
    public String getServiceLevel() {
        return serviceLevel;
    }

    /**
     * Set the service level.
     *
     * @param serviceLevel the service level
     */
    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    /**
     * Get the weight.
     *
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Set the weight.
     *
     * @param weight the weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Get the weight unit.
     *
     * @return the weight unit
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * Set the weight unit.
     *
     * @param weightUnit the weight unit
     */
    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    /**
     * Get the from address.
     *
     * @return the from address
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * Set the from address.
     *
     * @param fromAddress the from address
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    /**
     * Get the to address.
     *
     * @return the to address
     */
    public String getToAddress() {
        return toAddress;
    }

    /**
     * Set the to address.
     *
     * @param toAddress the to address
     */
    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    /**
     * Get the leasing contract ID.
     *
     * @return the leasing contract ID
     */
    public Long getLeasingContractId() {
        return leasingContractId;
    }

    /**
     * Set the leasing contract ID.
     *
     * @param leasingContractId the leasing contract ID
     */
    public void setLeasingContractId(Long leasingContractId) {
        this.leasingContractId = leasingContractId;
    }

    /**
     * Get the product ID.
     *
     * @return the product ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Set the product ID.
     *
     * @param productId the product ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Get the notes.
     *
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Set the notes.
     *
     * @param notes the notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipmentRequest that = (ShipmentRequest) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(trackingNumber, that.trackingNumber) &&
                Objects.equals(status, that.status) &&
                Objects.equals(shipDate, that.shipDate) &&
                Objects.equals(estimatedDeliveryDate, that.estimatedDeliveryDate) &&
                Objects.equals(actualDeliveryDate, that.actualDeliveryDate) &&
                Objects.equals(carrier, that.carrier) &&
                Objects.equals(serviceLevel, that.serviceLevel) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(weightUnit, that.weightUnit) &&
                Objects.equals(fromAddress, that.fromAddress) &&
                Objects.equals(toAddress, that.toAddress) &&
                Objects.equals(leasingContractId, that.leasingContractId) &&
                Objects.equals(productId, that.productId) &&
                Objects.equals(notes, that.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trackingNumber, status, shipDate, estimatedDeliveryDate, actualDeliveryDate, carrier, serviceLevel, weight, weightUnit, fromAddress, toAddress, leasingContractId, productId, notes);
    }

    @Override
    public String toString() {
        return "ShipmentRequest{" +
                "id=" + id +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", status='" + status + '\'' +
                ", shipDate=" + shipDate +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +
                ", actualDeliveryDate=" + actualDeliveryDate +
                ", carrier='" + carrier + '\'' +
                ", serviceLevel='" + serviceLevel + '\'' +
                ", weight=" + weight +
                ", weightUnit='" + weightUnit + '\'' +
                ", fromAddress='" + fromAddress + '\'' +
                ", toAddress='" + toAddress + '\'' +
                ", leasingContractId=" + leasingContractId +
                ", productId=" + productId +
                ", notes='" + notes + '\'' +
                '}';
    }
}
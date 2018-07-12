package com.jnj.pangea.edm.inventory_stock.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMInventoryStockBo extends BaseBo {

    private String sourceSystem;
    private String localPlant;
    private String localMaterial;
    private String localStorageLocation;
    private String localBatchId;
    private String localVendorNumber;
    private String localSpecialStockIndicator;
    private String localConsignmentSpecialStockIndicator;
    private String localUnrestrictedStock;
    private String localUnrestrictedSpecialStock;
    private String localUnrestrictedBatchStock;
    private String localUnrestrictedConsignmentStock;
    private String localQualityInspectionStock;
    private String localQualityInspectionSpecialStock;
    private String localQualityInspectionBatchStock;
    private String localQualityInspectionConsignmentStock;
    private String localRestrictedStock;
    private String localRestrictedSpecialStock;
    private String localRestrictedBatchStock;
    private String localRestrictedConsignmentStock;
    private String localBlockedStock;
    private String localBlockedBatchStock;
    private String localBlockedConsignmentStock;
    private String localReturnsStock;
    private String localReturnsBatchStock;
    private String localStockInTransitStorageLocation;
    private String localStockInTransitPlantToPlant;
    private String localStockInTransitPlant;
    private String localStockInTransitSpecial;
    private String localStockInTransitBatch;
    private String localRestrictedUseConsignment;
    private String localConsignmentStockInQualityInspection;
    private String localUnrestrictedUseConsignment;
    private String localBlkdConstStkNonBm;
    private String localTotalStockAllRestrictedBatches;

    private String objectName;

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localPlant", this.localPlant)
                .add("localBatchId", this.localBatchId)
                .add("localSpecialStockIndicator", this.localSpecialStockIndicator)
                .add("localConsignmentSpecialStockIndicator", this.localConsignmentSpecialStockIndicator)
                .add("localStorageLocation", this.localStorageLocation)
                .add("localMaterial", this.localMaterial)
                .add("localVendorNumber", this.localVendorNumber)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalMaterial() {
        return this.localMaterial;
    }

    public void setLocalMaterial(String localMaterial) {
        this.localMaterial = localMaterial;
    }

    public String getLocalStorageLocation() {
        return this.localStorageLocation;
    }

    public void setLocalStorageLocation(String localStorageLocation) {
        this.localStorageLocation = localStorageLocation;
    }

    public String getLocalBatchId() {
        return this.localBatchId;
    }

    public void setLocalBatchId(String localBatchId) {
        this.localBatchId = localBatchId;
    }

    public String getLocalVendorNumber() {
        return this.localVendorNumber;
    }

    public void setLocalVendorNumber(String localVendorNumber) {
        this.localVendorNumber = localVendorNumber;
    }

    public String getLocalSpecialStockIndicator() {
        return this.localSpecialStockIndicator;
    }

    public void setLocalSpecialStockIndicator(String localSpecialStockIndicator) {
        this.localSpecialStockIndicator = localSpecialStockIndicator;
    }

    public String getLocalConsignmentSpecialStockIndicator() {
        return this.localConsignmentSpecialStockIndicator;
    }

    public void setLocalConsignmentSpecialStockIndicator(String localConsignmentSpecialStockIndicator) {
        this.localConsignmentSpecialStockIndicator = localConsignmentSpecialStockIndicator;
    }

    public String getLocalUnrestrictedStock() {
        return this.localUnrestrictedStock;
    }

    public void setLocalUnrestrictedStock(String localUnrestrictedStock) {
        this.localUnrestrictedStock = localUnrestrictedStock;
    }

    public String getLocalUnrestrictedSpecialStock() {
        return this.localUnrestrictedSpecialStock;
    }

    public void setLocalUnrestrictedSpecialStock(String localUnrestrictedSpecialStock) {
        this.localUnrestrictedSpecialStock = localUnrestrictedSpecialStock;
    }

    public String getLocalUnrestrictedBatchStock() {
        return this.localUnrestrictedBatchStock;
    }

    public void setLocalUnrestrictedBatchStock(String localUnrestrictedBatchStock) {
        this.localUnrestrictedBatchStock = localUnrestrictedBatchStock;
    }

    public String getLocalUnrestrictedConsignmentStock() {
        return this.localUnrestrictedConsignmentStock;
    }

    public void setLocalUnrestrictedConsignmentStock(String localUnrestrictedConsignmentStock) {
        this.localUnrestrictedConsignmentStock = localUnrestrictedConsignmentStock;
    }

    public String getLocalQualityInspectionStock() {
        return this.localQualityInspectionStock;
    }

    public void setLocalQualityInspectionStock(String localQualityInspectionStock) {
        this.localQualityInspectionStock = localQualityInspectionStock;
    }

    public String getLocalQualityInspectionSpecialStock() {
        return this.localQualityInspectionSpecialStock;
    }

    public void setLocalQualityInspectionSpecialStock(String localQualityInspectionSpecialStock) {
        this.localQualityInspectionSpecialStock = localQualityInspectionSpecialStock;
    }

    public String getLocalQualityInspectionBatchStock() {
        return this.localQualityInspectionBatchStock;
    }

    public void setLocalQualityInspectionBatchStock(String localQualityInspectionBatchStock) {
        this.localQualityInspectionBatchStock = localQualityInspectionBatchStock;
    }

    public String getLocalQualityInspectionConsignmentStock() {
        return this.localQualityInspectionConsignmentStock;
    }

    public void setLocalQualityInspectionConsignmentStock(String localQualityInspectionConsignmentStock) {
        this.localQualityInspectionConsignmentStock = localQualityInspectionConsignmentStock;
    }

    public String getLocalRestrictedStock() {
        return this.localRestrictedStock;
    }

    public void setLocalRestrictedStock(String localRestrictedStock) {
        this.localRestrictedStock = localRestrictedStock;
    }

    public String getLocalRestrictedSpecialStock() {
        return this.localRestrictedSpecialStock;
    }

    public void setLocalRestrictedSpecialStock(String localRestrictedSpecialStock) {
        this.localRestrictedSpecialStock = localRestrictedSpecialStock;
    }

    public String getLocalRestrictedBatchStock() {
        return this.localRestrictedBatchStock;
    }

    public void setLocalRestrictedBatchStock(String localRestrictedBatchStock) {
        this.localRestrictedBatchStock = localRestrictedBatchStock;
    }

    public String getLocalRestrictedConsignmentStock() {
        return this.localRestrictedConsignmentStock;
    }

    public void setLocalRestrictedConsignmentStock(String localRestrictedConsignmentStock) {
        this.localRestrictedConsignmentStock = localRestrictedConsignmentStock;
    }

    public String getLocalBlockedStock() {
        return this.localBlockedStock;
    }

    public void setLocalBlockedStock(String localBlockedStock) {
        this.localBlockedStock = localBlockedStock;
    }

    public String getLocalBlockedBatchStock() {
        return this.localBlockedBatchStock;
    }

    public void setLocalBlockedBatchStock(String localBlockedBatchStock) {
        this.localBlockedBatchStock = localBlockedBatchStock;
    }

    public String getLocalBlockedConsignmentStock() {
        return this.localBlockedConsignmentStock;
    }

    public void setLocalBlockedConsignmentStock(String localBlockedConsignmentStock) {
        this.localBlockedConsignmentStock = localBlockedConsignmentStock;
    }

    public String getLocalReturnsStock() {
        return this.localReturnsStock;
    }

    public void setLocalReturnsStock(String localReturnsStock) {
        this.localReturnsStock = localReturnsStock;
    }

    public String getLocalReturnsBatchStock() {
        return this.localReturnsBatchStock;
    }

    public void setLocalReturnsBatchStock(String localReturnsBatchStock) {
        this.localReturnsBatchStock = localReturnsBatchStock;
    }

    public String getLocalStockInTransitStorageLocation() {
        return this.localStockInTransitStorageLocation;
    }

    public void setLocalStockInTransitStorageLocation(String localStockInTransitStorageLocation) {
        this.localStockInTransitStorageLocation = localStockInTransitStorageLocation;
    }

    public String getLocalStockInTransitPlantToPlant() {
        return this.localStockInTransitPlantToPlant;
    }

    public void setLocalStockInTransitPlantToPlant(String localStockInTransitPlantToPlant) {
        this.localStockInTransitPlantToPlant = localStockInTransitPlantToPlant;
    }

    public String getLocalStockInTransitPlant() {
        return this.localStockInTransitPlant;
    }

    public void setLocalStockInTransitPlant(String localStockInTransitPlant) {
        this.localStockInTransitPlant = localStockInTransitPlant;
    }

    public String getLocalStockInTransitSpecial() {
        return this.localStockInTransitSpecial;
    }

    public void setLocalStockInTransitSpecial(String localStockInTransitSpecial) {
        this.localStockInTransitSpecial = localStockInTransitSpecial;
    }

    public String getLocalStockInTransitBatch() {
        return this.localStockInTransitBatch;
    }

    public void setLocalStockInTransitBatch(String localStockInTransitBatch) {
        this.localStockInTransitBatch = localStockInTransitBatch;
    }

    public String getLocalRestrictedUseConsignment() {
        return this.localRestrictedUseConsignment;
    }

    public void setLocalRestrictedUseConsignment(String localRestrictedUseConsignment) {
        this.localRestrictedUseConsignment = localRestrictedUseConsignment;
    }

    public String getLocalConsignmentStockInQualityInspection() {
        return this.localConsignmentStockInQualityInspection;
    }

    public void setLocalConsignmentStockInQualityInspection(String localConsignmentStockInQualityInspection) {
        this.localConsignmentStockInQualityInspection = localConsignmentStockInQualityInspection;
    }

    public String getLocalUnrestrictedUseConsignment() {
        return this.localUnrestrictedUseConsignment;
    }

    public void setLocalUnrestrictedUseConsignment(String localUnrestrictedUseConsignment) {
        this.localUnrestrictedUseConsignment = localUnrestrictedUseConsignment;
    }

    public String getLocalBlkdConstStkNonBm() {
        return this.localBlkdConstStkNonBm;
    }

    public void setLocalBlkdConstStkNonBm(String localBlkdConstStkNonBm) {
        this.localBlkdConstStkNonBm = localBlkdConstStkNonBm;
    }

    public String getLocalTotalStockAllRestrictedBatches() {
        return this.localTotalStockAllRestrictedBatches;
    }

    public void setLocalTotalStockAllRestrictedBatches(String localTotalStockAllRestrictedBatches) {
        this.localTotalStockAllRestrictedBatches = localTotalStockAllRestrictedBatches;
    }

}

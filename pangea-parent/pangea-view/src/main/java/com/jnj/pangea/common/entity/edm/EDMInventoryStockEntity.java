package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMInventoryStockEntity extends CommonEntity {

    private String sourceSystem;
    private String localBatchId;
    private String localMaterial;
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

    public EDMInventoryStockEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalBatchId((String) map.get("localBatchId"));
        setLocalMaterial((String) map.get("localMaterial"));
        setLocalUnrestrictedStock((String) map.get("localUnrestrictedStock"));
        setLocalUnrestrictedSpecialStock((String) map.get("localUnrestrictedSpecialStock"));
        setLocalUnrestrictedBatchStock((String) map.get("localUnrestrictedBatchStock"));
        setLocalUnrestrictedConsignmentStock((String) map.get("localUnrestrictedConsignmentStock"));
        setLocalQualityInspectionStock((String) map.get("localQualityInspectionStock"));
        setLocalQualityInspectionSpecialStock((String) map.get("localQualityInspectionSpecialStock"));
        setLocalQualityInspectionBatchStock((String) map.get("localQualityInspectionBatchStock"));
        setLocalQualityInspectionConsignmentStock((String) map.get("localQualityInspectionConsignmentStock"));
        setLocalRestrictedStock((String) map.get("localRestrictedStock"));
        setLocalRestrictedSpecialStock((String) map.get("localRestrictedSpecialStock"));
        setLocalRestrictedBatchStock((String) map.get("localRestrictedBatchStock"));
        setLocalRestrictedConsignmentStock((String) map.get("localRestrictedConsignmentStock"));
        setLocalBlockedStock((String) map.get("localBlockedStock"));
        setLocalBlockedBatchStock((String) map.get("localBlockedBatchStock"));
        setLocalBlockedConsignmentStock((String) map.get("localBlockedConsignmentStock"));
        setLocalReturnsStock((String) map.get("localReturnsStock"));
        setLocalReturnsBatchStock((String) map.get("localReturnsBatchStock"));
        setLocalStockInTransitStorageLocation((String) map.get("localStockInTransitStorageLocation"));
        setLocalStockInTransitPlantToPlant((String) map.get("localStockInTransitPlantToPlant"));
        setLocalStockInTransitPlant((String) map.get("localStockInTransitPlant"));
        setLocalStockInTransitSpecial((String) map.get("localStockInTransitSpecial"));
        setLocalStockInTransitBatch((String) map.get("localStockInTransitBatch"));
        setLocalRestrictedUseConsignment((String) map.get("localRestrictedUseConsignment"));
        setLocalConsignmentStockInQualityInspection((String) map.get("localConsignmentStockInQualityInspection"));
        setLocalUnrestrictedUseConsignment((String) map.get("localUnrestrictedUseConsignment"));
        setLocalBlkdConstStkNonBm((String) map.get("localBlkdConstStkNonBm"));
        setLocalTotalStockAllRestrictedBatches((String) map.get("localTotalStockAllRestrictedBatches"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalBatchId() {
        return this.localBatchId;
    }

    public void setLocalBatchId(String localBatchId) {
        this.localBatchId = localBatchId;
    }

    public String getLocalMaterial() {
        return this.localMaterial;
    }

    public void setLocalMaterial(String localMaterial) {
        this.localMaterial = localMaterial;
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

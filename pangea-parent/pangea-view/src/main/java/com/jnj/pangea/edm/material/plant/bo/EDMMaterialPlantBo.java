package com.jnj.pangea.edm.material.plant.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMaterialPlantBo  extends BaseBo
{
    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String localDeletionFlagPlant;
    private String materialNumber;
    private String plant;
    private String localPlantStatus;
    private String plantStatus;
    private String localProcurementType;
    private String localFixedLotSize;
    private String localMaximumLotSize;
    private String localMinimumLotSize;
    private String localRoundingValueForPoq;
    private String localLotSize;
    private String localMrpType;
    private String localMrpController;
    private String localInHouseProcessingTime;
    private String localSafetyStock;
    private String localMinimumSafetyStock;
    private String localProductionSupervisor;
    private String localProductionUnit;
    private String localPostToInspectionStock;
    private String localComponentScrapInPercent;
    private String localCriticalPart;
    private String localAbcIndicator;
    private String localMaximumStockLevel;
    private String localCheckingGroupForAvailabilityCheck;
    private String localPlannedDeliveryTimeInDays;
    private String localDependentRequirements;
    private String localSafetyTimeIndicator;
    private String localSafetyTime;
    private String localSpecialProcurementType;
    private String localPlanningStrategyGroup;
    private String localConsumptionPeriodBackward;
    private String localConsumptionPeriodForward;
    private String localConsumptionMode;
    private String localGoodsReceiptProcessingTimeInDays;
    private String localBatchManagementRequirementIndicator;
    private String localPlanningTimeFence;
    private String localPostToInspStk;
    private String localComponentScrap;
    private String localBaseQuantity;
    private String purchsngGrpCd;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalDeletionFlagPlant() {
        return localDeletionFlagPlant;
    }

    public void setLocalDeletionFlagPlant(String localDeletionFlagPlant) {
        this.localDeletionFlagPlant = localDeletionFlagPlant;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getLocalPlantStatus() {
        return localPlantStatus;
    }

    public void setLocalPlantStatus(String localPlantStatus) {
        this.localPlantStatus = localPlantStatus;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
    }

    public String getLocalProcurementType() {
        return localProcurementType;
    }

    public void setLocalProcurementType(String localProcurementType) {
        this.localProcurementType = localProcurementType;
    }

    public String getLocalFixedLotSize() {
        return localFixedLotSize;
    }

    public void setLocalFixedLotSize(String localFixedLotSize) {
        this.localFixedLotSize = localFixedLotSize;
    }

    public String getLocalMaximumLotSize() {
        return localMaximumLotSize;
    }

    public void setLocalMaximumLotSize(String localMaximumLotSize) {
        this.localMaximumLotSize = localMaximumLotSize;
    }

    public String getLocalMinimumLotSize() {
        return localMinimumLotSize;
    }

    public void setLocalMinimumLotSize(String localMinimumLotSize) {
        this.localMinimumLotSize = localMinimumLotSize;
    }

    public String getLocalRoundingValueForPoq() {
        return localRoundingValueForPoq;
    }

    public void setLocalRoundingValueForPoq(String localRoundingValueForPoq) {
        this.localRoundingValueForPoq = localRoundingValueForPoq;
    }

    public String getLocalLotSize() {
        return localLotSize;
    }

    public void setLocalLotSize(String localLotSize) {
        this.localLotSize = localLotSize;
    }

    public String getLocalMrpType() {
        return localMrpType;
    }

    public void setLocalMrpType(String localMrpType) {
        this.localMrpType = localMrpType;
    }

    public String getLocalMrpController() {
        return localMrpController;
    }

    public void setLocalMrpController(String localMrpController) {
        this.localMrpController = localMrpController;
    }

    public String getLocalInHouseProcessingTime() {
        return localInHouseProcessingTime;
    }

    public void setLocalInHouseProcessingTime(String localInHouseProcessingTime) {
        this.localInHouseProcessingTime = localInHouseProcessingTime;
    }

    public String getLocalSafetyStock() {
        return localSafetyStock;
    }

    public void setLocalSafetyStock(String localSafetyStock) {
        this.localSafetyStock = localSafetyStock;
    }

    public String getLocalMinimumSafetyStock() {
        return localMinimumSafetyStock;
    }

    public void setLocalMinimumSafetyStock(String localMinimumSafetyStock) {
        this.localMinimumSafetyStock = localMinimumSafetyStock;
    }

    public String getLocalProductionSupervisor() {
        return localProductionSupervisor;
    }

    public void setLocalProductionSupervisor(String localProductionSupervisor) {
        this.localProductionSupervisor = localProductionSupervisor;
    }

    public String getLocalProductionUnit() {
        return localProductionUnit;
    }

    public void setLocalProductionUnit(String localProductionUnit) {
        this.localProductionUnit = localProductionUnit;
    }

    public String getLocalPostToInspectionStock() {
        return localPostToInspectionStock;
    }

    public void setLocalPostToInspectionStock(String localPostToInspectionStock) {
        this.localPostToInspectionStock = localPostToInspectionStock;
    }

    public String getLocalComponentScrapInPercent() {
        return localComponentScrapInPercent;
    }

    public void setLocalComponentScrapInPercent(String localComponentScrapInPercent) {
        this.localComponentScrapInPercent = localComponentScrapInPercent;
    }

    public String getLocalCriticalPart() {
        return localCriticalPart;
    }

    public void setLocalCriticalPart(String localCriticalPart) {
        this.localCriticalPart = localCriticalPart;
    }

    public String getLocalAbcIndicator() {
        return localAbcIndicator;
    }

    public void setLocalAbcIndicator(String localAbcIndicator) {
        this.localAbcIndicator = localAbcIndicator;
    }

    public String getLocalMaximumStockLevel() {
        return localMaximumStockLevel;
    }

    public void setLocalMaximumStockLevel(String localMaximumStockLevel) {
        this.localMaximumStockLevel = localMaximumStockLevel;
    }

    public String getLocalCheckingGroupForAvailabilityCheck() {
        return localCheckingGroupForAvailabilityCheck;
    }

    public void setLocalCheckingGroupForAvailabilityCheck(String localCheckingGroupForAvailabilityCheck) {
        this.localCheckingGroupForAvailabilityCheck = localCheckingGroupForAvailabilityCheck;
    }

    public String getLocalPlannedDeliveryTimeInDays() {
        return localPlannedDeliveryTimeInDays;
    }

    public void setLocalPlannedDeliveryTimeInDays(String localPlannedDeliveryTimeInDays) {
        this.localPlannedDeliveryTimeInDays = localPlannedDeliveryTimeInDays;
    }

    public String getLocalDependentRequirements() {
        return localDependentRequirements;
    }

    public void setLocalDependentRequirements(String localDependentRequirements) {
        this.localDependentRequirements = localDependentRequirements;
    }

    public String getLocalSafetyTimeIndicator() {
        return localSafetyTimeIndicator;
    }

    public void setLocalSafetyTimeIndicator(String localSafetyTimeIndicator) {
        this.localSafetyTimeIndicator = localSafetyTimeIndicator;
    }

    public String getLocalSafetyTime() {
        return localSafetyTime;
    }

    public void setLocalSafetyTime(String localSafetyTime) {
        this.localSafetyTime = localSafetyTime;
    }

    public String getLocalSpecialProcurementType() {
        return localSpecialProcurementType;
    }

    public void setLocalSpecialProcurementType(String localSpecialProcurementType) {
        this.localSpecialProcurementType = localSpecialProcurementType;
    }

    public String getLocalPlanningStrategyGroup() {
        return localPlanningStrategyGroup;
    }

    public void setLocalPlanningStrategyGroup(String localPlanningStrategyGroup) {
        this.localPlanningStrategyGroup = localPlanningStrategyGroup;
    }

    public String getLocalConsumptionPeriodBackward() {
        return localConsumptionPeriodBackward;
    }

    public void setLocalConsumptionPeriodBackward(String localConsumptionPeriodBackward) {
        this.localConsumptionPeriodBackward = localConsumptionPeriodBackward;
    }

    public String getLocalConsumptionPeriodForward() {
        return localConsumptionPeriodForward;
    }

    public void setLocalConsumptionPeriodForward(String localConsumptionPeriodForward) {
        this.localConsumptionPeriodForward = localConsumptionPeriodForward;
    }

    public String getLocalConsumptionMode() {
        return localConsumptionMode;
    }

    public void setLocalConsumptionMode(String localConsumptionMode) {
        this.localConsumptionMode = localConsumptionMode;
    }

    public String getLocalGoodsReceiptProcessingTimeInDays() {
        return localGoodsReceiptProcessingTimeInDays;
    }

    public void setLocalGoodsReceiptProcessingTimeInDays(String localGoodsReceiptProcessingTimeInDays) {
        this.localGoodsReceiptProcessingTimeInDays = localGoodsReceiptProcessingTimeInDays;
    }

    public String getLocalBatchManagementRequirementIndicator() {
        return localBatchManagementRequirementIndicator;
    }

    public void setLocalBatchManagementRequirementIndicator(String localBatchManagementRequirementIndicator) {
        this.localBatchManagementRequirementIndicator = localBatchManagementRequirementIndicator;
    }

    public String getLocalPlanningTimeFence() {
        return localPlanningTimeFence;
    }

    public void setLocalPlanningTimeFence(String localPlanningTimeFence) {
        this.localPlanningTimeFence = localPlanningTimeFence;
    }

    public String getLocalPostToInspStk() {
        return localPostToInspStk;
    }

    public void setLocalPostToInspStk(String localPostToInspStk) {
        this.localPostToInspStk = localPostToInspStk;
    }

    public String getLocalComponentScrap() {
        return localComponentScrap;
    }

    public void setLocalComponentScrap(String localComponentScrap) {
        this.localComponentScrap = localComponentScrap;
    }

    public String getLocalBaseQuantity() {
        return localBaseQuantity;
    }

    public void setLocalBaseQuantity(String localBaseQuantity) {
        this.localBaseQuantity = localBaseQuantity;
    }

    public String getPurchsngGrpCd() {
        return purchsngGrpCd;
    }

    public void setPurchsngGrpCd(String purchsngGrpCd) {
        this.purchsngGrpCd = purchsngGrpCd;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .add("localPlant", this.localPlant)
                .toJsonString();
    }
}


package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMaterialPlantV1Entity extends CommonEntity{

    private String localMaterialNumber;
    private String localPlant;
    private String materialNumber;
    private String localDeletionFlagPlant;
    private String localMrpType;
    private String localMaterialType;

	private String localBaseQuantity;
	private String plantStatus;
	private String localInHouseProcessingTime;
	private String localRoundingValueForPoq;
	private String sourceSystem;
	private String localFixedLotSize;
	private String localGoodsReceiptProcessingTimeInDays;
	private String localConsumptionMode;
	private String localMaximumStockLevel;
	private String localPlanningStrategyGroup;
	private String localSafetyStock;
	private String localMrpController;
	private String localCheckingGroupForAvailabilityCheck;
	private String localPostToInspectionStock;
	private String localMinimumSafetyStock;
	private String localProcurementType;
	private String localPlantStatus;
	private String localProductionUnit;
	private String localPostToInspStk;
	private String localComponentScrapInPercent;
	private String localBatchManagementRequirementIndicator;
	private String localMaximumLotSize;
	private String localPlanningTimeFence;
	private String localCriticalPart;
	private String localDependentRequirements;
	private String localComponentScrap;
	private String localConsumptionPeriodBackward;
	private String localSafetyTime;
	private String localMinimumLotSize;
	private String localSafetyTimeIndicator;
	private String plant;
	private String localConsumptionPeriodForward;
	private String localAbcIndicator;
	private String localLotSize;
	private String localSpecialProcurementType;
	private String localProductionSupervisor;
	private String localPlannedDeliveryTimeInDays;

	private String localPurchasingGroup;

    public EDMMaterialPlantV1Entity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String)map.get("localMaterialNumber"));
        setLocalPlant((String)map.get("localPlant"));
        setMaterialNumber((String)map.get("materialNumber"));
        setLocalDeletionFlagPlant((String)map.get("localDeletionFlagPlant"));
        setLocalMrpType((String)map.get("localMrpType"));
        setLocalMaterialType((String)map.get("localMaterialType"));
        setLocalMaterialType((String)map.get("localPurchasingGroup"));
    }


    public String getLocalPurchasingGroup () {
        return localPurchasingGroup;
    }

    public void setLocalPurchasingGroup (String localPurchasingGroup) {
        this.localPurchasingGroup = localPurchasingGroup;
    }

    public String getLocalDeletionFlagPlant() {
        return localDeletionFlagPlant;
    }

    public void setLocalDeletionFlagPlant(String localDeletionFlagPlant) {
        this.localDeletionFlagPlant = localDeletionFlagPlant;
    }

    public String getLocalMrpType() {
        return localMrpType;
    }

    public void setLocalMrpType(String localMrpType) {
        this.localMrpType = localMrpType;
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

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalMaterialType() {
        return localMaterialType;
    }

    public void setLocalMaterialType(String localMaterialType) {
        this.localMaterialType = localMaterialType;
    }


    public String getLocalBaseQuantity () {
        return localBaseQuantity;
    }

    public void setLocalBaseQuantity (String localBaseQuantity) {
        this.localBaseQuantity = localBaseQuantity;
    }

    public String getPlantStatus () {
        return plantStatus;
    }

    public void setPlantStatus (String plantStatus) {
        this.plantStatus = plantStatus;
    }

    public String getLocalInHouseProcessingTime () {
        return localInHouseProcessingTime;
    }

    public void setLocalInHouseProcessingTime (String localInHouseProcessingTime) {
        this.localInHouseProcessingTime = localInHouseProcessingTime;
    }

    public String getLocalRoundingValueForPoq () {
        return localRoundingValueForPoq;
    }

    public void setLocalRoundingValueForPoq (String localRoundingValueForPoq) {
        this.localRoundingValueForPoq = localRoundingValueForPoq;
    }

    public String getSourceSystem () {
        return sourceSystem;
    }

    public void setSourceSystem (String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalFixedLotSize () {
        return localFixedLotSize;
    }

    public void setLocalFixedLotSize (String localFixedLotSize) {
        this.localFixedLotSize = localFixedLotSize;
    }

    public String getLocalGoodsReceiptProcessingTimeInDays () {
        return localGoodsReceiptProcessingTimeInDays;
    }

    public void setLocalGoodsReceiptProcessingTimeInDays (String localGoodsReceiptProcessingTimeInDays) {
        this.localGoodsReceiptProcessingTimeInDays = localGoodsReceiptProcessingTimeInDays;
    }

    public String getLocalConsumptionMode () {
        return localConsumptionMode;
    }

    public void setLocalConsumptionMode (String localConsumptionMode) {
        this.localConsumptionMode = localConsumptionMode;
    }

    public String getLocalMaximumStockLevel () {
        return localMaximumStockLevel;
    }

    public void setLocalMaximumStockLevel (String localMaximumStockLevel) {
        this.localMaximumStockLevel = localMaximumStockLevel;
    }

    public String getLocalPlanningStrategyGroup () {
        return localPlanningStrategyGroup;
    }

    public void setLocalPlanningStrategyGroup (String localPlanningStrategyGroup) {
        this.localPlanningStrategyGroup = localPlanningStrategyGroup;
    }

    public String getLocalSafetyStock () {
        return localSafetyStock;
    }

    public void setLocalSafetyStock (String localSafetyStock) {
        this.localSafetyStock = localSafetyStock;
    }

    public String getLocalMrpController () {
        return localMrpController;
    }

    public void setLocalMrpController (String localMrpController) {
        this.localMrpController = localMrpController;
    }

    public String getLocalCheckingGroupForAvailabilityCheck () {
        return localCheckingGroupForAvailabilityCheck;
    }

    public void setLocalCheckingGroupForAvailabilityCheck (String localCheckingGroupForAvailabilityCheck) {
        this.localCheckingGroupForAvailabilityCheck = localCheckingGroupForAvailabilityCheck;
    }

    public String getLocalPostToInspectionStock () {
        return localPostToInspectionStock;
    }

    public void setLocalPostToInspectionStock (String localPostToInspectionStock) {
        this.localPostToInspectionStock = localPostToInspectionStock;
    }

    public String getLocalMinimumSafetyStock () {
        return localMinimumSafetyStock;
    }

    public void setLocalMinimumSafetyStock (String localMinimumSafetyStock) {
        this.localMinimumSafetyStock = localMinimumSafetyStock;
    }

    public String getLocalProcurementType () {
        return localProcurementType;
    }

    public void setLocalProcurementType (String localProcurementType) {
        this.localProcurementType = localProcurementType;
    }

    public String getLocalPlantStatus () {
        return localPlantStatus;
    }

    public void setLocalPlantStatus (String localPlantStatus) {
        this.localPlantStatus = localPlantStatus;
    }

    public String getLocalProductionUnit () {
        return localProductionUnit;
    }

    public void setLocalProductionUnit (String localProductionUnit) {
        this.localProductionUnit = localProductionUnit;
    }

    public String getLocalPostToInspStk () {
        return localPostToInspStk;
    }

    public void setLocalPostToInspStk (String localPostToInspStk) {
        this.localPostToInspStk = localPostToInspStk;
    }

    public String getLocalComponentScrapInPercent () {
        return localComponentScrapInPercent;
    }

    public void setLocalComponentScrapInPercent (String localComponentScrapInPercent) {
        this.localComponentScrapInPercent = localComponentScrapInPercent;
    }

    public String getLocalBatchManagementRequirementIndicator () {
        return localBatchManagementRequirementIndicator;
    }

    public void setLocalBatchManagementRequirementIndicator (String localBatchManagementRequirementIndicator) {
        this.localBatchManagementRequirementIndicator = localBatchManagementRequirementIndicator;
    }

    public String getLocalMaximumLotSize () {
        return localMaximumLotSize;
    }

    public void setLocalMaximumLotSize (String localMaximumLotSize) {
        this.localMaximumLotSize = localMaximumLotSize;
    }

    public String getLocalPlanningTimeFence () {
        return localPlanningTimeFence;
    }

    public void setLocalPlanningTimeFence (String localPlanningTimeFence) {
        this.localPlanningTimeFence = localPlanningTimeFence;
    }

    public String getLocalCriticalPart () {
        return localCriticalPart;
    }

    public void setLocalCriticalPart (String localCriticalPart) {
        this.localCriticalPart = localCriticalPart;
    }

    public String getLocalDependentRequirements () {
        return localDependentRequirements;
    }

    public void setLocalDependentRequirements (String localDependentRequirements) {
        this.localDependentRequirements = localDependentRequirements;
    }

    public String getLocalComponentScrap () {
        return localComponentScrap;
    }

    public void setLocalComponentScrap (String localComponentScrap) {
        this.localComponentScrap = localComponentScrap;
    }

    public String getLocalConsumptionPeriodBackward () {
        return localConsumptionPeriodBackward;
    }

    public void setLocalConsumptionPeriodBackward (String localConsumptionPeriodBackward) {
        this.localConsumptionPeriodBackward = localConsumptionPeriodBackward;
    }

    public String getLocalSafetyTime () {
        return localSafetyTime;
    }

    public void setLocalSafetyTime (String localSafetyTime) {
        this.localSafetyTime = localSafetyTime;
    }

    public String getLocalMinimumLotSize () {
        return localMinimumLotSize;
    }

    public void setLocalMinimumLotSize (String localMinimumLotSize) {
        this.localMinimumLotSize = localMinimumLotSize;
    }

    public String getLocalSafetyTimeIndicator () {
        return localSafetyTimeIndicator;
    }

    public void setLocalSafetyTimeIndicator (String localSafetyTimeIndicator) {
        this.localSafetyTimeIndicator = localSafetyTimeIndicator;
    }

    public String getPlant () {
        return plant;
    }

    public void setPlant (String plant) {
        this.plant = plant;
    }

    public String getLocalConsumptionPeriodForward () {
        return localConsumptionPeriodForward;
    }

    public void setLocalConsumptionPeriodForward (String localConsumptionPeriodForward) {
        this.localConsumptionPeriodForward = localConsumptionPeriodForward;
    }

    public String getLocalAbcIndicator () {
        return localAbcIndicator;
    }

    public void setLocalAbcIndicator (String localAbcIndicator) {
        this.localAbcIndicator = localAbcIndicator;
    }

    public String getLocalLotSize () {
        return localLotSize;
    }

    public void setLocalLotSize (String localLotSize) {
        this.localLotSize = localLotSize;
    }

    public String getLocalSpecialProcurementType () {
        return localSpecialProcurementType;
    }

    public void setLocalSpecialProcurementType (String localSpecialProcurementType) {
        this.localSpecialProcurementType = localSpecialProcurementType;
    }

    public String getLocalProductionSupervisor () {
        return localProductionSupervisor;
    }

    public void setLocalProductionSupervisor (String localProductionSupervisor) {
        this.localProductionSupervisor = localProductionSupervisor;
    }

    public String getLocalPlannedDeliveryTimeInDays () {
        return localPlannedDeliveryTimeInDays;
    }

    public void setLocalPlannedDeliveryTimeInDays (String localPlannedDeliveryTimeInDays) {
        this.localPlannedDeliveryTimeInDays = localPlannedDeliveryTimeInDays;
    }
}

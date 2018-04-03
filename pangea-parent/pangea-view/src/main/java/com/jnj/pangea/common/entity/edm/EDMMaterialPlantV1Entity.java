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
    private String sourceSystem;
    private String localProcurementType;
    private String localAbcIndicator;
    private String localSpecialProcurementType;
    private String localPlanningStrategyGroup;
    private String localConsumptionMode;
    private String localLotSize;
    private String localBaseQuantity;
    private String localFixedlotsize;
    private String localMaximumLotSize;
    private String localMinimumLotSize;
    private String localRoundingvalueforpurchaseorderquantity;
    private String localMRPController;
    private String localInHouseProcessingTime;
    private String localSafetyStock;
    private String localMinimumSafetyStock;
    private String localProductionSupervisor;
    private String localPlanningTimeFence;
    private String localProductionUnit;
    private String localPosttoInspectionStock;
    private String localComponentscrapinpercent;
    private String localCriticalpart;
    private String localPlannedDeliveryTimeinDays;
    private String localMaximumstocklevel;
    private String localPlantStatus;
    private String localCheckingGroupforAvailabilityCheck;
    private String localInstalledReplenishmentLotSize;
    private String localDependentrequirements;
    private String localSafetytimeindicator;
    private String localSafetytime;
    private String localConsumptionperiodBackward;
    private String localConsumptionperiodForward;
    private String localGoodsReceiptProcessingTimeinDays;
    private String localBatchmanagementrequirmentindicator;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalProcurementType() {
        return localProcurementType;
    }

    public void setLocalProcurementType(String localProcurementType) {
        this.localProcurementType = localProcurementType;
    }

    public String getLocalAbcIndicator() {
        return localAbcIndicator;
    }

    public void setLocalAbcIndicator(String localAbcIndicator) {
        this.localAbcIndicator = localAbcIndicator;
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

    public String getLocalConsumptionMode() {
        return localConsumptionMode;
    }

    public void setLocalConsumptionMode(String localConsumptionMode) {
        this.localConsumptionMode = localConsumptionMode;
    }

    public String getLocalLotSize() {
        return localLotSize;
    }

    public void setLocalLotSize(String localLotSize) {
        this.localLotSize = localLotSize;
    }

    public String getLocalBaseQuantity() {
        return localBaseQuantity;
    }

    public void setLocalBaseQuantity(String localBaseQuantity) {
        this.localBaseQuantity = localBaseQuantity;
    }

    public String getLocalFixedlotsize() {
        return localFixedlotsize;
    }

    public void setLocalFixedlotsize(String localFixedlotsize) {
        this.localFixedlotsize = localFixedlotsize;
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

    public String getLocalRoundingvalueforpurchaseorderquantity() {
        return localRoundingvalueforpurchaseorderquantity;
    }

    public void setLocalRoundingvalueforpurchaseorderquantity(String localRoundingvalueforpurchaseorderquantity) {
        this.localRoundingvalueforpurchaseorderquantity = localRoundingvalueforpurchaseorderquantity;
    }

    public String getLocalMRPController() {
        return localMRPController;
    }

    public void setLocalMRPController(String localMRPController) {
        this.localMRPController = localMRPController;
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

    public String getLocalPlanningTimeFence() {
        return localPlanningTimeFence;
    }

    public void setLocalPlanningTimeFence(String localPlanningTimeFence) {
        this.localPlanningTimeFence = localPlanningTimeFence;
    }

    public String getLocalProductionUnit() {
        return localProductionUnit;
    }

    public void setLocalProductionUnit(String localProductionUnit) {
        this.localProductionUnit = localProductionUnit;
    }

    public String getLocalPosttoInspectionStock() {
        return localPosttoInspectionStock;
    }

    public void setLocalPosttoInspectionStock(String localPosttoInspectionStock) {
        this.localPosttoInspectionStock = localPosttoInspectionStock;
    }

    public String getLocalComponentscrapinpercent() {
        return localComponentscrapinpercent;
    }

    public void setLocalComponentscrapinpercent(String localComponentscrapinpercent) {
        this.localComponentscrapinpercent = localComponentscrapinpercent;
    }

    public String getLocalCriticalpart() {
        return localCriticalpart;
    }

    public void setLocalCriticalpart(String localCriticalpart) {
        this.localCriticalpart = localCriticalpart;
    }

    public String getLocalPlannedDeliveryTimeinDays() {
        return localPlannedDeliveryTimeinDays;
    }

    public void setLocalPlannedDeliveryTimeinDays(String localPlannedDeliveryTimeinDays) {
        this.localPlannedDeliveryTimeinDays = localPlannedDeliveryTimeinDays;
    }

    public String getLocalMaximumstocklevel() {
        return localMaximumstocklevel;
    }

    public void setLocalMaximumstocklevel(String localMaximumstocklevel) {
        this.localMaximumstocklevel = localMaximumstocklevel;
    }

    public String getLocalPlantStatus() {
        return localPlantStatus;
    }

    public void setLocalPlantStatus(String localPlantStatus) {
        this.localPlantStatus = localPlantStatus;
    }

    public String getLocalCheckingGroupforAvailabilityCheck() {
        return localCheckingGroupforAvailabilityCheck;
    }

    public void setLocalCheckingGroupforAvailabilityCheck(String localCheckingGroupforAvailabilityCheck) {
        this.localCheckingGroupforAvailabilityCheck = localCheckingGroupforAvailabilityCheck;
    }

    public String getLocalInstalledReplenishmentLotSize() {
        return localInstalledReplenishmentLotSize;
    }

    public void setLocalInstalledReplenishmentLotSize(String localInstalledReplenishmentLotSize) {
        this.localInstalledReplenishmentLotSize = localInstalledReplenishmentLotSize;
    }

    public String getLocalDependentrequirements() {
        return localDependentrequirements;
    }

    public void setLocalDependentrequirements(String localDependentrequirements) {
        this.localDependentrequirements = localDependentrequirements;
    }

    public String getLocalSafetytimeindicator() {
        return localSafetytimeindicator;
    }

    public void setLocalSafetytimeindicator(String localSafetytimeindicator) {
        this.localSafetytimeindicator = localSafetytimeindicator;
    }

    public String getLocalSafetytime() {
        return localSafetytime;
    }

    public void setLocalSafetytime(String localSafetytime) {
        this.localSafetytime = localSafetytime;
    }

    public String getLocalConsumptionperiodBackward() {
        return localConsumptionperiodBackward;
    }

    public void setLocalConsumptionperiodBackward(String localConsumptionperiodBackward) {
        this.localConsumptionperiodBackward = localConsumptionperiodBackward;
    }

    public String getLocalConsumptionperiodForward() {
        return localConsumptionperiodForward;
    }

    public void setLocalConsumptionperiodForward(String localConsumptionperiodForward) {
        this.localConsumptionperiodForward = localConsumptionperiodForward;
    }

    public String getLocalGoodsReceiptProcessingTimeinDays() {
        return localGoodsReceiptProcessingTimeinDays;
    }

    public void setLocalGoodsReceiptProcessingTimeinDays(String localGoodsReceiptProcessingTimeinDays) {
        this.localGoodsReceiptProcessingTimeinDays = localGoodsReceiptProcessingTimeinDays;
    }

    public String getLocalBatchmanagementrequirmentindicator() {
        return localBatchmanagementrequirmentindicator;
    }

    public void setLocalBatchmanagementrequirmentindicator(String localBatchmanagementrequirmentindicator) {
        this.localBatchmanagementrequirmentindicator = localBatchmanagementrequirmentindicator;
    }

    public EDMMaterialPlantV1Entity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String)map.get("localMaterialNumber"));
        setLocalPlant((String)map.get("localPlant"));
        setMaterialNumber((String)map.get("materialNumber"));
        setLocalDeletionFlagPlant((String)map.get("localDeletionFlagPlant"));
        setLocalMrpType((String)map.get("localMrpType"));
        setLocalMaterialType((String)map.get("localMaterialType"));
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalProcurementType((String) map.get("localProcurementType"));
        setLocalAbcIndicator((String) map.get("localAbcIndicator"));
        setLocalSpecialProcurementType((String) map.get("localSpecialProcurementType"));
        setLocalPlanningStrategyGroup((String) map.get("localPlanningStrategyGroup"));
        setLocalConsumptionMode((String) map.get("localConsumptionMode"));
        setLocalLotSize((String) map.get("localLotSize"));
        setLocalBaseQuantity((String) map.get("localBaseQuantity"));
        setLocalFixedlotsize((String) map.get("localFixedlotsize"));
        setLocalMaximumLotSize((String) map.get("localMaximumLotSize"));
        setLocalMinimumLotSize((String) map.get("localMinimumLotSize"));
        setLocalRoundingvalueforpurchaseorderquantity((String) map.get("localRoundingvalueforpurchaseorderquantity"));
        setLocalMRPController((String) map.get("localMRPController"));
        setLocalInHouseProcessingTime((String) map.get("localInHouseProcessingTime"));
        setLocalSafetyStock((String) map.get("localSafetyStock"));
        setLocalMinimumSafetyStock((String) map.get("localMinimumSafetyStock"));
        setLocalProductionSupervisor((String) map.get("localProductionSupervisor"));
        setLocalPlanningTimeFence((String) map.get("localPlanningTimeFence"));
        setLocalProductionUnit((String) map.get("localProductionUnit"));
        setLocalPosttoInspectionStock((String) map.get("localPosttoInspectionStock"));
        setLocalComponentscrapinpercent((String) map.get("localComponentscrapinpercent"));
        setLocalCriticalpart((String) map.get("localCriticalpart"));
        setLocalPlannedDeliveryTimeinDays((String) map.get("localPlannedDeliveryTimeinDays"));
        setLocalMaximumstocklevel((String) map.get("localMaximumstocklevel"));
        setLocalPlantStatus((String) map.get("localPlantStatus"));
        setLocalCheckingGroupforAvailabilityCheck((String) map.get("localCheckingGroupforAvailabilityCheck"));
        setLocalInstalledReplenishmentLotSize((String) map.get("localInstalledReplenishmentLotSize"));
        setLocalDependentrequirements((String) map.get("localDependentrequirements"));
        setLocalSafetytimeindicator((String) map.get("localSafetytimeindicator"));
        setLocalSafetytime((String) map.get("localSafetytime"));
        setLocalConsumptionperiodBackward((String) map.get("localConsumptionperiodBackward"));
        setLocalConsumptionperiodForward((String) map.get("localConsumptionperiodForward"));
        setLocalGoodsReceiptProcessingTimeinDays((String) map.get("localGoodsReceiptProcessingTimeinDays"));
        setLocalBatchmanagementrequirmentindicator((String) map.get("localBatchmanagementrequirmentindicator"));
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
}

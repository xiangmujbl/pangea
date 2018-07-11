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
    private String localFixedLotSize;
    private String localMaximumLotSize;
    private String localMinimumLotSize;
    private String localRoundingValueForPoq;
    private String localMrpController;
    private String localInHouseProcessingTime;
    private String localSafetyStock;
    private String localMinimumSafetyStock;
    private String localProductionSupervisor;
    private String localPlanningTimeFence;
    private String localProductionUnit;
    private String localPostToInspectionStock;
    private String localComponentScrapInPercent;
    private String localCriticalPart;
    private String localPlannedDeliveryTimeInDays;
    private String localMaximumStockLevel;
    private String localPlantStatus;
    private String localCheckingGroupForAvailabilityCheck;
    private String localInstalledReplenishmentLotSize;
    private String localDependentRequirements;
    private String localSafetyTimeIndicator;
    private String localSafetyTime;
    private String localConsumptionPeriodBackward;
    private String localConsumptionPeriodForward;
    private String localGoodsReceiptProcessingTimeInDays;
    private String localBatchManagementRequirementIndicator;
    private String localPurchasingGroup;
    private String purchsngGrpCd;
    private String plantStatus;
    private String localPostToInspStk;
    private String localComponentScrap;
    private String plant;
    private String XCHPF;

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
        setLocalFixedLotSize((String) map.get("localFixedLotSize"));
        setLocalMaximumLotSize((String) map.get("localMaximumLotSize"));
        setLocalMinimumLotSize((String) map.get("localMinimumLotSize"));
        setLocalRoundingValueForPoq((String) map.get("localRoundingValueForPoq"));
        setLocalMrpController((String) map.get("localMrpController"));
        setLocalInHouseProcessingTime((String) map.get("localInHouseProcessingTime"));
        setLocalSafetyStock((String) map.get("localSafetyStock"));
        setLocalMinimumSafetyStock((String) map.get("localMinimumSafetyStock"));
        setLocalProductionSupervisor((String) map.get("localProductionSupervisor"));
        setLocalPlanningTimeFence((String) map.get("localPlanningTimeFence"));
        setLocalProductionUnit((String) map.get("localProductionUnit"));
        setLocalPostToInspectionStock((String) map.get("localPostToInspectionStock"));
        setLocalComponentScrapInPercent((String) map.get("localComponentScrapInPercent"));
        setLocalCriticalPart((String) map.get("localCriticalPart"));
        setLocalPlannedDeliveryTimeInDays((String) map.get("localPlannedDeliveryTimeInDays"));
        setLocalMaximumStockLevel((String) map.get("localMaximumStockLevel"));
        setLocalPlantStatus((String) map.get("localPlantStatus"));
        setLocalCheckingGroupForAvailabilityCheck((String) map.get("localCheckingGroupForAvailabilityCheck"));
        setLocalInstalledReplenishmentLotSize((String) map.get("localInstalledReplenishmentLotSize"));
        setLocalDependentRequirements((String) map.get("localDependentRequirements"));
        setLocalSafetyTimeIndicator((String) map.get("localSafetyTimeIndicator"));
        setLocalSafetyTime((String) map.get("localSafetyTime"));
        setLocalConsumptionPeriodBackward((String) map.get("localConsumptionPeriodBackward"));
        setLocalConsumptionPeriodForward((String) map.get("localConsumptionPeriodForward"));
        setLocalGoodsReceiptProcessingTimeInDays((String) map.get("localGoodsReceiptProcessingTimeInDays"));
        setLocalBatchManagementRequirementIndicator((String) map.get("localBatchManagementRequirementIndicator"));
        setLocalPurchasingGroup((String)map.get("localPurchasingGroup"));
        setPurchsngGrpCd((String)map.get("purchsngGrpCd"));
        setXCHPF((String)map.get("XCHPF"));
        setPlantStatus((String) map.get("plantStatus"));
    }

    public String getXCHPF() {
        return XCHPF;
    }

    public void setXCHPF(String XCHPF) {
        this.XCHPF = XCHPF;
    }

    public String getLocalMaterialNumber () {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber (String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalPlant () {
        return localPlant;
    }

    public void setLocalPlant (String localPlant) {
        this.localPlant = localPlant;
    }

    public String getMaterialNumber () {
        return materialNumber;
    }

    public void setMaterialNumber (String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalDeletionFlagPlant () {
        return localDeletionFlagPlant;
    }

    public void setLocalDeletionFlagPlant (String localDeletionFlagPlant) {
        this.localDeletionFlagPlant = localDeletionFlagPlant;
    }

    public String getLocalMrpType () {
        return localMrpType;
    }

    public void setLocalMrpType (String localMrpType) {
        this.localMrpType = localMrpType;
    }

    public String getLocalMaterialType () {
        return localMaterialType;
    }

    public void setLocalMaterialType (String localMaterialType) {
        this.localMaterialType = localMaterialType;
    }

    public String getSourceSystem () {
        return sourceSystem;
    }

    public void setSourceSystem (String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalProcurementType () {
        return localProcurementType;
    }

    public void setLocalProcurementType (String localProcurementType) {
        this.localProcurementType = localProcurementType;
    }

    public String getLocalAbcIndicator () {
        return localAbcIndicator;
    }

    public void setLocalAbcIndicator (String localAbcIndicator) {
        this.localAbcIndicator = localAbcIndicator;
    }

    public String getLocalSpecialProcurementType () {
        return localSpecialProcurementType;
    }

    public void setLocalSpecialProcurementType (String localSpecialProcurementType) {
        this.localSpecialProcurementType = localSpecialProcurementType;
    }

    public String getLocalPlanningStrategyGroup () {
        return localPlanningStrategyGroup;
    }

    public void setLocalPlanningStrategyGroup (String localPlanningStrategyGroup) {
        this.localPlanningStrategyGroup = localPlanningStrategyGroup;
    }

    public String getLocalConsumptionMode () {
        return localConsumptionMode;
    }

    public void setLocalConsumptionMode (String localConsumptionMode) {
        this.localConsumptionMode = localConsumptionMode;
    }

    public String getLocalLotSize () {
        return localLotSize;
    }

    public void setLocalLotSize (String localLotSize) {
        this.localLotSize = localLotSize;
    }

    public String getLocalBaseQuantity () {
        return localBaseQuantity;
    }

    public void setLocalBaseQuantity (String localBaseQuantity) {
        this.localBaseQuantity = localBaseQuantity;
    }

    public String getLocalFixedLotSize () {
        return localFixedLotSize;
    }

    public void setLocalFixedLotSize (String localFixedLotSize) {
        this.localFixedLotSize = localFixedLotSize;
    }

    public String getLocalMaximumLotSize () {
        return localMaximumLotSize;
    }

    public void setLocalMaximumLotSize (String localMaximumLotSize) {
        this.localMaximumLotSize = localMaximumLotSize;
    }

    public String getLocalMinimumLotSize () {
        return localMinimumLotSize;
    }

    public void setLocalMinimumLotSize (String localMinimumLotSize) {
        this.localMinimumLotSize = localMinimumLotSize;
    }

    public String getLocalRoundingValueForPoq () {
        return localRoundingValueForPoq;
    }

    public void setLocalRoundingValueForPoq (String localRoundingValueForPoq) {
        this.localRoundingValueForPoq = localRoundingValueForPoq;
    }

    public String getLocalMrpController() {
        return localMrpController;
    }

    public void setLocalMrpController(String localMrpController) {
        this.localMrpController = localMrpController;
    }

    public String getLocalInHouseProcessingTime () {
        return localInHouseProcessingTime;
    }

    public void setLocalInHouseProcessingTime (String localInHouseProcessingTime) {
        this.localInHouseProcessingTime = localInHouseProcessingTime;
    }

    public String getLocalSafetyStock () {
        return localSafetyStock;
    }

    public void setLocalSafetyStock (String localSafetyStock) {
        this.localSafetyStock = localSafetyStock;
    }

    public String getLocalMinimumSafetyStock () {
        return localMinimumSafetyStock;
    }

    public void setLocalMinimumSafetyStock (String localMinimumSafetyStock) {
        this.localMinimumSafetyStock = localMinimumSafetyStock;
    }

    public String getLocalProductionSupervisor () {
        return localProductionSupervisor;
    }

    public void setLocalProductionSupervisor (String localProductionSupervisor) {
        this.localProductionSupervisor = localProductionSupervisor;
    }

    public String getLocalPlanningTimeFence () {
        return localPlanningTimeFence;
    }

    public void setLocalPlanningTimeFence (String localPlanningTimeFence) {
        this.localPlanningTimeFence = localPlanningTimeFence;
    }

    public String getLocalProductionUnit () {
        return localProductionUnit;
    }

    public void setLocalProductionUnit (String localProductionUnit) {
        this.localProductionUnit = localProductionUnit;
    }

    public String getLocalPostToInspectionStock () {
        return localPostToInspectionStock;
    }

    public void setLocalPostToInspectionStock (String localPostToInspectionStock) {
        this.localPostToInspectionStock = localPostToInspectionStock;
    }

    public String getLocalComponentScrapInPercent () {
        return localComponentScrapInPercent;
    }

    public void setLocalComponentScrapInPercent (String localComponentScrapInPercent) {
        this.localComponentScrapInPercent = localComponentScrapInPercent;
    }

    public String getLocalCriticalPart () {
        return localCriticalPart;
    }

    public void setLocalCriticalPart (String localCriticalPart) {
        this.localCriticalPart = localCriticalPart;
    }

    public String getLocalPlannedDeliveryTimeInDays () {
        return localPlannedDeliveryTimeInDays;
    }

    public void setLocalPlannedDeliveryTimeInDays (String localPlannedDeliveryTimeInDays) {
        this.localPlannedDeliveryTimeInDays = localPlannedDeliveryTimeInDays;
    }

    public String getLocalMaximumStockLevel () {
        return localMaximumStockLevel;
    }

    public void setLocalMaximumStockLevel (String localMaximumStockLevel) {
        this.localMaximumStockLevel = localMaximumStockLevel;
    }

    public String getLocalPlantStatus () {
        return localPlantStatus;
    }

    public void setLocalPlantStatus (String localPlantStatus) {
        this.localPlantStatus = localPlantStatus;
    }

    public String getLocalCheckingGroupForAvailabilityCheck () {
        return localCheckingGroupForAvailabilityCheck;
    }

    public void setLocalCheckingGroupForAvailabilityCheck (String localCheckingGroupForAvailabilityCheck) {
        this.localCheckingGroupForAvailabilityCheck = localCheckingGroupForAvailabilityCheck;
    }

    public String getLocalInstalledReplenishmentLotSize () {
        return localInstalledReplenishmentLotSize;
    }

    public void setLocalInstalledReplenishmentLotSize (String localInstalledReplenishmentLotSize) {
        this.localInstalledReplenishmentLotSize = localInstalledReplenishmentLotSize;
    }

    public String getLocalDependentRequirements () {
        return localDependentRequirements;
    }

    public void setLocalDependentRequirements (String localDependentRequirements) {
        this.localDependentRequirements = localDependentRequirements;
    }

    public String getLocalSafetyTimeIndicator () {
        return localSafetyTimeIndicator;
    }

    public void setLocalSafetyTimeIndicator (String localSafetyTimeIndicator) {
        this.localSafetyTimeIndicator = localSafetyTimeIndicator;
    }

    public String getLocalSafetyTime () {
        return localSafetyTime;
    }

    public void setLocalSafetyTime (String localSafetyTime) {
        this.localSafetyTime = localSafetyTime;
    }

    public String getLocalConsumptionPeriodBackward () {
        return localConsumptionPeriodBackward;
    }

    public void setLocalConsumptionPeriodBackward (String localConsumptionPeriodBackward) {
        this.localConsumptionPeriodBackward = localConsumptionPeriodBackward;
    }

    public String getLocalConsumptionPeriodForward () {
        return localConsumptionPeriodForward;
    }

    public void setLocalConsumptionPeriodForward (String localConsumptionPeriodForward) {
        this.localConsumptionPeriodForward = localConsumptionPeriodForward;
    }

    public String getLocalGoodsReceiptProcessingTimeInDays () {
        return localGoodsReceiptProcessingTimeInDays;
    }

    public void setLocalGoodsReceiptProcessingTimeInDays (String localGoodsReceiptProcessingTimeInDays) {
        this.localGoodsReceiptProcessingTimeInDays = localGoodsReceiptProcessingTimeInDays;
    }

    public String getLocalBatchManagementRequirementIndicator () {
        return localBatchManagementRequirementIndicator;
    }

    public void setLocalBatchManagementRequirementIndicator (String localBatchManagementRequirementIndicator) {
        this.localBatchManagementRequirementIndicator = localBatchManagementRequirementIndicator;
    }

    public String getLocalPurchasingGroup() {
        return localPurchasingGroup;
    }

    public void setLocalPurchasingGroup(String localPurchasingGroup) {
        this.localPurchasingGroup = localPurchasingGroup;
    }

    public String getPlantStatus() {
        return plantStatus;
    }

    public void setPlantStatus(String plantStatus) {
        this.plantStatus = plantStatus;
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

    public String getPlant() {
        return plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getPurchsngGrpCd () {
        return purchsngGrpCd;
    }

    public void setPurchsngGrpCd (String purchasingGrpCd) {
        this.purchsngGrpCd = purchasingGrpCd;
    }
}

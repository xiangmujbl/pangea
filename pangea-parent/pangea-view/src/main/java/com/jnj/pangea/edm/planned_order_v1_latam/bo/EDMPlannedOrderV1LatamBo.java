package com.jnj.pangea.edm.planned_order_v1_latam.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMPlannedOrderV1LatamBo extends BaseBo {

    private String sourceSystem;
    private String mfgPlannedOrderId;
    private String localPlant;
    private String plant;
    private String localproductionPlant;
    private String localMaterialNumber;
    private String materialNumber;
    private String localUom;
    private String localProcurementType;
    private String localSplProcType;
    private String localPrdVersion;
    private String localPrdOrdType;
    private String plannedOrdQty;
    private String localScrapQty;
    private String requirementQty;
    private String orderstrtDate;
    private String ordStrtTime;
    private String ordFinishDate;
    private String ordEndTime;
    private String grProcessDays;
    private String ordFirmInd;
    private String localStorageLoc;
    private String localDocVersion;
    private String prdStartDate;
    private String prdFinishDate;
    private String mrpController;
    private String localPlanningScenario;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("mfgPlannedOrderId", this.mfgPlannedOrderId)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getMfgPlannedOrderId() {
        return this.mfgPlannedOrderId;
    }

    public void setMfgPlannedOrderId(String mfgPlannedOrderId) {
        this.mfgPlannedOrderId = mfgPlannedOrderId;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getPlant() {
        return this.plant;
    }

    public void setPlant(String plant) {
        this.plant = plant;
    }

    public String getLocalproductionPlant() {
        return this.localproductionPlant;
    }

    public void setLocalproductionPlant(String localproductionPlant) {
        this.localproductionPlant = localproductionPlant;
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getMaterialNumber() {
        return this.materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalUom() {
        return this.localUom;
    }

    public void setLocalUom(String localUom) {
        this.localUom = localUom;
    }

    public String getLocalProcurementType() {
        return this.localProcurementType;
    }

    public void setLocalProcurementType(String localProcurementType) {
        this.localProcurementType = localProcurementType;
    }

    public String getLocalSplProcType() {
        return this.localSplProcType;
    }

    public void setLocalSplProcType(String localSplProcType) {
        this.localSplProcType = localSplProcType;
    }

    public String getLocalPrdVersion() {
        return this.localPrdVersion;
    }

    public void setLocalPrdVersion(String localPrdVersion) {
        this.localPrdVersion = localPrdVersion;
    }

    public String getLocalPrdOrdType() {
        return this.localPrdOrdType;
    }

    public void setLocalPrdOrdType(String localPrdOrdType) {
        this.localPrdOrdType = localPrdOrdType;
    }

    public String getPlannedOrdQty() {
        return this.plannedOrdQty;
    }

    public void setPlannedOrdQty(String plannedOrdQty) {
        this.plannedOrdQty = plannedOrdQty;
    }

    public String getLocalScrapQty() {
        return this.localScrapQty;
    }

    public void setLocalScrapQty(String localScrapQty) {
        this.localScrapQty = localScrapQty;
    }

    public String getRequirementQty() {
        return this.requirementQty;
    }

    public void setRequirementQty(String requirementQty) {
        this.requirementQty = requirementQty;
    }

    public String getOrderstrtDate() {
        return this.orderstrtDate;
    }

    public void setOrderstrtDate(String orderstrtDate) {
        this.orderstrtDate = orderstrtDate;
    }

    public String getOrdStrtTime() {
        return this.ordStrtTime;
    }

    public void setOrdStrtTime(String ordStrtTime) {
        this.ordStrtTime = ordStrtTime;
    }

    public String getOrdFinishDate() {
        return this.ordFinishDate;
    }

    public void setOrdFinishDate(String ordFinishDate) {
        this.ordFinishDate = ordFinishDate;
    }

    public String getOrdEndTime() {
        return this.ordEndTime;
    }

    public void setOrdEndTime(String ordEndTime) {
        this.ordEndTime = ordEndTime;
    }

    public String getGrProcessDays() {
        return this.grProcessDays;
    }

    public void setGrProcessDays(String grProcessDays) {
        this.grProcessDays = grProcessDays;
    }

    public String getOrdFirmInd() {
        return this.ordFirmInd;
    }

    public void setOrdFirmInd(String ordFirmInd) {
        this.ordFirmInd = ordFirmInd;
    }

    public String getLocalStorageLoc() {
        return this.localStorageLoc;
    }

    public void setLocalStorageLoc(String localStorageLoc) {
        this.localStorageLoc = localStorageLoc;
    }

    public String getLocalDocVersion() {
        return this.localDocVersion;
    }

    public void setLocalDocVersion(String localDocVersion) {
        this.localDocVersion = localDocVersion;
    }

    public String getPrdStartDate() {
        return this.prdStartDate;
    }

    public void setPrdStartDate(String prdStartDate) {
        this.prdStartDate = prdStartDate;
    }

    public String getPrdFinishDate() {
        return this.prdFinishDate;
    }

    public void setPrdFinishDate(String prdFinishDate) {
        this.prdFinishDate = prdFinishDate;
    }

    public String getMrpController() {
        return this.mrpController;
    }

    public void setMrpController(String mrpController) {
        this.mrpController = mrpController;
    }

    public String getLocalPlanningScenario() {
        return this.localPlanningScenario;
    }

    public void setLocalPlanningScenario(String localPlanningScenario) {
        this.localPlanningScenario = localPlanningScenario;
    }

}

package com.jnj.pangea.edm.material_global.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMaterialGlobalBo extends BaseBo {

    private String sourceSystem;
    private String localMaterialNumber;
    private String localRefDescription;
    private String localMaterialType;
    private String localBaseUom;
    private String materialNumber;
    private String refDescription;
    private String materialType;
    private String baseUom;
    private String localDpParentCode;
    private String parentCode;
    private String globalDpParentCode;
    private String form;
    private String category;
    private String subBrand;
    private String brand;
    private String franchise;
    private String globalBusinessUnit;
    private String productFamily;
    private String localManufacturingTechnology;
    private String manufacturingTechnology;
    private String localMaterialGroup;
    private String materialGroup;
    private String flagForDeletion;
    private String materialStatus;
    private String division;
    private String batchManageIndicator;
    private String minRemShelfLife;
    private String totalShelfLife;
    private String primaryPlanningCode;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalRefDescription() {
        return this.localRefDescription;
    }

    public void setLocalRefDescription(String localRefDescription) {
        this.localRefDescription = localRefDescription;
    }

    public String getLocalMaterialType() {
        return this.localMaterialType;
    }

    public void setLocalMaterialType(String localMaterialType) {
        this.localMaterialType = localMaterialType;
    }

    public String getLocalBaseUom() {
        return this.localBaseUom;
    }

    public void setLocalBaseUom(String localBaseUom) {
        this.localBaseUom = localBaseUom;
    }

    public String getMaterialNumber() {
        return this.materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getRefDescription() {
        return this.refDescription;
    }

    public void setRefDescription(String refDescription) {
        this.refDescription = refDescription;
    }

    public String getMaterialType() {
        return this.materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getBaseUom() {
        return this.baseUom;
    }

    public void setBaseUom(String baseUom) {
        this.baseUom = baseUom;
    }

    public String getLocalDpParentCode() {
        return this.localDpParentCode;
    }

    public void setLocalDpParentCode(String localDpParentCode) {
        this.localDpParentCode = localDpParentCode;
    }

    public String getParentCode() {
        return this.parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getGlobalDpParentCode() {
        return this.globalDpParentCode;
    }

    public void setGlobalDpParentCode(String globalDpParentCode) {
        this.globalDpParentCode = globalDpParentCode;
    }

    public String getForm() {
        return this.form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubBrand() {
        return this.subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFranchise() {
        return this.franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getGlobalBusinessUnit() {
        return this.globalBusinessUnit;
    }

    public void setGlobalBusinessUnit(String globalBusinessUnit) {
        this.globalBusinessUnit = globalBusinessUnit;
    }

    public String getProductFamily() {
        return this.productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getLocalManufacturingTechnology() {
        return this.localManufacturingTechnology;
    }

    public void setLocalManufacturingTechnology(String localManufacturingTechnology) {
        this.localManufacturingTechnology = localManufacturingTechnology;
    }

    public String getManufacturingTechnology() {
        return this.manufacturingTechnology;
    }

    public void setManufacturingTechnology(String manufacturingTechnology) {
        this.manufacturingTechnology = manufacturingTechnology;
    }

    public String getLocalMaterialGroup() {
        return this.localMaterialGroup;
    }

    public void setLocalMaterialGroup(String localMaterialGroup) {
        this.localMaterialGroup = localMaterialGroup;
    }

    public String getMaterialGroup() {
        return this.materialGroup;
    }

    public void setMaterialGroup(String materialGroup) {
        this.materialGroup = materialGroup;
    }

    public String getFlagForDeletion() {
        return this.flagForDeletion;
    }

    public void setFlagForDeletion(String flagForDeletion) {
        this.flagForDeletion = flagForDeletion;
    }

    public String getMaterialStatus() {
        return this.materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getDivision() {
        return this.division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBatchManageIndicator() {
        return this.batchManageIndicator;
    }

    public void setBatchManageIndicator(String batchManageIndicator) {
        this.batchManageIndicator = batchManageIndicator;
    }

    public String getMinRemShelfLife() {
        return this.minRemShelfLife;
    }

    public void setMinRemShelfLife(String minRemShelfLife) {
        this.minRemShelfLife = minRemShelfLife;
    }

    public String getTotalShelfLife() {
        return this.totalShelfLife;
    }

    public void setTotalShelfLife(String totalShelfLife) {
        this.totalShelfLife = totalShelfLife;
    }

    public String getPrimaryPlanningCode() {
        return this.primaryPlanningCode;
    }

    public void setPrimaryPlanningCode(String primaryPlanningCode) {
        this.primaryPlanningCode = primaryPlanningCode;
    }

}

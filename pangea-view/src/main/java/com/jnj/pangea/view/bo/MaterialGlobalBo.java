package com.jnj.pangea.view.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;

public class MaterialGlobalBo extends BaseBo {

    private String sourceSystem;
    private String localMaterialNumber;
    private String localRefDescription;
    private String localMaterialType;
    private String localBaseUnit;
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

    public String getLocalRefDescription() {
        return localRefDescription;
    }

    public void setLocalRefDescription(String localRefDescription) {
        this.localRefDescription = localRefDescription;
    }

    public String getLocalMaterialType() {
        return localMaterialType;
    }

    public void setLocalMaterialType(String localMaterialType) {
        this.localMaterialType = localMaterialType;
    }

    public String getLocalBaseUnit() {
        return localBaseUnit;
    }

    public void setLocalBaseUnit(String localBaseUnit) {
        this.localBaseUnit = localBaseUnit;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getRefDescription() {
        return refDescription;
    }

    public void setRefDescription(String refDescription) {
        this.refDescription = refDescription;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getBaseUom() {
        return baseUom;
    }

    public void setBaseUom(String baseUom) {
        this.baseUom = baseUom;
    }

    public String getLocalDpParentCode() {
        return localDpParentCode;
    }

    public void setLocalDpParentCode(String localDpParentCod) {
        this.localDpParentCode = localDpParentCod;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getGlobalDpParentCode() {
        return globalDpParentCode;
    }

    public void setGlobalDpParentCode(String globalDpParentCode) {
        this.globalDpParentCode = globalDpParentCode;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getGlobalBusinessUnit() {
        return globalBusinessUnit;
    }

    public void setGlobalBusinessUnit(String globalBusinessUnit) {
        this.globalBusinessUnit = globalBusinessUnit;
    }

    public String getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getLocalManufacturingTechnology() {
        return localManufacturingTechnology;
    }

    public void setLocalManufacturingTechnology(String localManufacturingTechnology) {
        this.localManufacturingTechnology = localManufacturingTechnology;
    }

    public String getManufacturingTechnology() {
        return manufacturingTechnology;
    }

    public void setManufacturingTechnology(String manufacturingTechnology) {
        this.manufacturingTechnology = manufacturingTechnology;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .toJsonString();
    }
}

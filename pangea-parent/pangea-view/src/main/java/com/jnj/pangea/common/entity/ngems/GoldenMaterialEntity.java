package com.jnj.pangea.common.entity.ngems;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class GoldenMaterialEntity extends CommonEntity {

    private String materialNumber;
    private String materialDescription;
    private String franchise;
    private String materialType;
    private String baseUom;
    private String parentCode;
    private String globalDpParentCode;
    private String brand;
    private String form;
    private String materialStatus;
    private String subBrand;
    private String manufTechnology;
    private String category;
    private String productFamily;
    private String globalBusinessUnit;
    private String primaryPlanningCode;

    public GoldenMaterialEntity(Map<String, Object> map) {
        super(map);
        setMaterialNumber((String) map.get("materialNumber"));
        setMaterialDescription((String) map.get("materialDescription"));
        setFranchise((String) map.get("franchise"));
        setMaterialType((String) map.get("materialType"));
        setBaseUom((String) map.get("baseUom"));
        setGlobalDpParentCode((String) map.get("globalDpParentCode"));
        setParentCode((String) map.get("parentCode"));
        setGlobalDpParentCode((String) map.get("globalDpParentCode"));
        setBrand((String) map.get("brand"));
        setForm((String) map.get("form"));
        setMaterialStatus((String) map.get("materialStatus"));
        setSubBrand((String) map.get("subBrand"));
        setManufTechnology((String) map.get("manufTechnology"));
        setCategory((String) map.get("category"));
        setProductFamily((String) map.get("productFamily"));
        setGlobalBusinessUnit((String) map.get("globalBusinessUnit"));
        setPrimaryPlanningCode((String) map.get("primaryPlanningCode"));
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialDescription() {
        return materialDescription;
    }

    public void setMaterialDescription(String materialDescription) {
        this.materialDescription = materialDescription;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
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

    public String getGlobalDpParentCode() {
        return globalDpParentCode;
    }

    public void setGlobalDpParentCode(String globalDpParentCode) {
        this.globalDpParentCode = globalDpParentCode;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getMaterialStatus() {
        return materialStatus;
    }

    public void setMaterialStatus(String materialStatus) {
        this.materialStatus = materialStatus;
    }

    public String getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }

    public String getManufTechnology() {
        return manufTechnology;
    }

    public void setManufTechnology(String manufTechnology) {
        this.manufTechnology = manufTechnology;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getGlobalBusinessUnit() {
        return globalBusinessUnit;
    }

    public void setGlobalBusinessUnit(String globalBusinessUnit) {
        this.globalBusinessUnit = globalBusinessUnit;
    }

    public String getPrimaryPlanningCode() {
        return primaryPlanningCode;
    }

    public void setPrimaryPlanningCode(String primaryPlanningCode) {
        this.primaryPlanningCode = primaryPlanningCode;
    }
}

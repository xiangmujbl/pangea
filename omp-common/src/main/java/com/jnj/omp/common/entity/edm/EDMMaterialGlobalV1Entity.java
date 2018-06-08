package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public class EDMMaterialGlobalV1Entity extends CommonEntity {

    private String productFamily;
    private String franchise;
    private String manufacturingTechnology;
    private String sourceSystem;
    private String materialType;
    private String localMaterialType;
    private String baseUom;
    private String localMaterialNumber;
    private String localBaseUnit;
    private String form;
    private String localManufacturingTechnology;
    private String materialNumber;
    private String parentCode;
    private String refDescription;
    private String subBrand;
    private String localRefDescription;
    private String globalDpParentCode;
    private String globalBusinessUnit;
    private String category;
    private String brand;
    private String localDpParentCode;
    private String primaryPlanningCode;
    private String localBaseUom;

    public String getLocalBaseUom() {
        return localBaseUom;
    }

    public void setLocalBaseUom(String localBaseUom) {
        this.localBaseUom = localBaseUom;
    }

    public EDMMaterialGlobalV1Entity(Map<String, Object> map) {
        super(map);
        setLocalBaseUom((String) map.get("localBaseUom"));
        setProductFamily((String) map.get("productFamily"));
        setFranchise((String) map.get("franchise"));
        setManufacturingTechnology((String) map.get("manufacturingTechnology"));
        setSourceSystem((String) map.get("sourceSystem"));
        setMaterialType((String) map.get("materialType"));
        setLocalMaterialType((String) map.get("localMaterialType"));
        setBaseUom((String) map.get("baseUom"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalBaseUnit((String) map.get("localBaseUnit"));
        setForm((String) map.get("form"));
        setLocalManufacturingTechnology((String) map.get("localManufacturingTechnology"));
        setMaterialNumber((String) map.get("materialNumber"));
        setParentCode((String) map.get("parentCode"));
        setRefDescription((String) map.get("refDescription"));
        setSubBrand((String) map.get("subBrand"));
        setLocalRefDescription((String) map.get("localRefDescription"));
        setGlobalDpParentCode((String) map.get("globalDpParentCode"));
        setGlobalBusinessUnit((String) map.get("globalBusinessUnit"));
        setCategory((String) map.get("category"));
        setBrand((String) map.get("brand"));
        setLocalDpParentCode((String) map.get("localDpParentCode"));
        setPrimaryPlanningCode((String) map.get("primaryPlanningCode"));
    }

    public String getLocalDpParentCode() {
        return localDpParentCode;
    }

    public void setLocalDpParentCode(String localDpParentCode) {
        this.localDpParentCode = localDpParentCode;
    }

    public String getPrimaryPlanningCode() {
        return primaryPlanningCode;
    }

    public void setPrimaryPlanningCode(String primaryPlanningCode) {
        this.primaryPlanningCode = primaryPlanningCode;
    }

    public String getProductFamily() {
        return productFamily;
    }

    public void setProductFamily(String productFamily) {
        this.productFamily = productFamily;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getManufacturingTechnology() {
        return manufacturingTechnology;
    }

    public void setManufacturingTechnology(String manufacturingTechnology) {
        this.manufacturingTechnology = manufacturingTechnology;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getLocalMaterialType() {
        return localMaterialType;
    }

    public void setLocalMaterialType(String localMaterialType) {
        this.localMaterialType = localMaterialType;
    }

    public String getBaseUom() {
        return baseUom;
    }

    public void setBaseUom(String baseUom) {
        this.baseUom = baseUom;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalBaseUnit() {
        return localBaseUnit;
    }

    public void setLocalBaseUnit(String localBaseUnit) {
        this.localBaseUnit = localBaseUnit;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getLocalManufacturingTechnology() {
        return localManufacturingTechnology;
    }

    public void setLocalManufacturingTechnology(String localManufacturingTechnology) {
        this.localManufacturingTechnology = localManufacturingTechnology;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getRefDescription() {
        return refDescription;
    }

    public void setRefDescription(String refDescription) {
        this.refDescription = refDescription;
    }

    public String getSubBrand() {
        return subBrand;
    }

    public void setSubBrand(String subBrand) {
        this.subBrand = subBrand;
    }

    public String getLocalRefDescription() {
        return localRefDescription;
    }

    public void setLocalRefDescription(String localRefDescription) {
        this.localRefDescription = localRefDescription;
    }

    public String getGlobalDpParentCode() {
        return globalDpParentCode;
    }

    public void setGlobalDpParentCode(String globalDpParentCode) {
        this.globalDpParentCode = globalDpParentCode;
    }

    public String getGlobalBusinessUnit() {
        return globalBusinessUnit;
    }

    public void setGlobalBusinessUnit(String globalBusinessUnit) {
        this.globalBusinessUnit = globalBusinessUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

package com.jnj.pangea.common.entity.p360;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MaterialEntity extends CommonEntity {

	private String enterpriseMaterialNumber;
	private String materialDescription;
	private String franchise;
	private String materialType;
	private String baseUnitOfMeasure;
	private String parentCode;
	private String globalDpParentCode;
	private String brand;
	private String form;
	private String materialStatus;
	private String subBrand;
	private String manufacturingTechnology;
	private String category;
	private String productFamily;
	private String globalBusinessUnitGbu;
	private String materialGroup;

	public MaterialEntity(Map<String, Object> map) {
		super(map);
		setEnterpriseMaterialNumber((String) map.get("enterpriseMaterialNumber"));
		setMaterialDescription((String) map.get("materialDescription"));
		setFranchise((String) map.get("franchise"));
		setMaterialType((String) map.get("materialType"));
		setBaseUnitOfMeasure((String) map.get("baseUnitOfMeasure"));
		setGlobalDpParentCode((String) map.get("globalDpParentCode"));
		setParentCode((String) map.get("parentCode"));
		setBrand((String) map.get("brand"));
		setForm((String) map.get("form"));
		setMaterialStatus((String) map.get("materialStatus"));
		setSubBrand((String) map.get("subBrand"));
		setManufacturingTechnology((String) map.get("manufacturingTechnology"));
		setCategory((String) map.get("category"));
		setProductFamily((String) map.get("productFamily"));
		setGlobalBusinessUnitGbu((String) map.get("globalBusinessUnitGbu"));
		setMaterialGroup((String) map.get("materialGroup"));
	}

	public String getEnterpriseMaterialNumber() {
		return enterpriseMaterialNumber;
	}

	public void setEnterpriseMaterialNumber(String enterpriseMaterialNumber) {
		this.enterpriseMaterialNumber = enterpriseMaterialNumber;
	}

	public String getBaseUnitOfMeasure() {
		return baseUnitOfMeasure;
	}

	public void setBaseUnitOfMeasure(String baseUnitOfMeasure) {
		this.baseUnitOfMeasure = baseUnitOfMeasure;
	}

	public String getManufacturingTechnology() {
		return manufacturingTechnology;
	}

	public void setManufacturingTechnology(String manufacturingTechnology) {
		this.manufacturingTechnology = manufacturingTechnology;
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

	public String getGlobalBusinessUnitGbu() {
		return globalBusinessUnitGbu;
	}

	public void setGlobalBusinessUnitGbu(String globalBusinessUnitGbu) {
		this.globalBusinessUnitGbu = globalBusinessUnitGbu;
	}

	public String getMaterialGroup() {
		return materialGroup;
	}

	public void setMaterialGroup(String materialGroup) {
		this.materialGroup = materialGroup;
	}
}

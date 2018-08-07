package com.jnj.pangea.common.entity.p360;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MaterialCrossReferenceEntity extends CommonEntity {

    private String sourceSystemName;
    private String sourceSystemMaterialNumber;
    private String enterpriseMaterialNumber;

    public MaterialCrossReferenceEntity(Map<String, Object> map) {
        super(map);
        setSourceSystemName((String) map.get("sourceSystemName"));
        setSourceSystemMaterialNumber((String) map.get("sourceSystemMaterialNumber"));
        setEnterpriseMaterialNumber((String) map.get("enterpriseMaterialNumber"));
    }

	public String getSourceSystemName() {
		return sourceSystemName;
	}

	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}

	public String getSourceSystemMaterialNumber() {
		return sourceSystemMaterialNumber;
	}

	public void setSourceSystemMaterialNumber(String sourceSystemMaterialNumber) {
		this.sourceSystemMaterialNumber = sourceSystemMaterialNumber;
	}

	public String getEnterpriseMaterialNumber() {
		return enterpriseMaterialNumber;
	}

	public void setEnterpriseMaterialNumber(String enterpriseMaterialNumber) {
		this.enterpriseMaterialNumber = enterpriseMaterialNumber;
	}
}

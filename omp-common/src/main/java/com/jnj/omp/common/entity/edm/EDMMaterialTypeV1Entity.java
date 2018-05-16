package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EDMMaterialTypeV1Entity extends CommonEntity {

    private String materialType;
    private String materialTypeName;

    public EDMMaterialTypeV1Entity(Map<String, Object> map) {
        super(map);

        setMaterialType((String) map.get("materialType"));
        setMaterialTypeName((String) map.get("materialTypeName"));
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaterialTypeName() {
        return materialTypeName;
    }

    public void setMaterialTypeName(String materialTypeName) {
        this.materialTypeName = materialTypeName;
    }
}

package com.jnj.pangea.common.entity.ngems;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MaterialLinkageEntity extends CommonEntity {

    private String sourceSystem;
    private String localMaterialNumber;
    private String materialNumber;

    public MaterialLinkageEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setMaterialNumber((String) map.get("materialNumber"));
    }

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

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }
}

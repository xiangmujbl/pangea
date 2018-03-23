package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsMaterialInclEntity extends CommonEntity {

    private String localMaterialNumber;
    private String planningType;

    public CnsMaterialInclEntity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setPlanningType((String) map.get("planningType"));
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getPlanningType() {
        return planningType;
    }

    public void setPlanningType(String planningType) {
        this.planningType = planningType;
    }
}

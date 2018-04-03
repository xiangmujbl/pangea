package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsFinPlanValEntity extends CommonEntity {

    private String localMaterialNumber;
    private String value;

    public PlanCnsFinPlanValEntity(Map<String, Object> map) {
        super(map);

        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setValue((String) map.get("value"));
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

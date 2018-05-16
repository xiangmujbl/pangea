package com.jnj.omp.common.entity.plan;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsFinPlanValEntity extends CommonEntity {

    private String localMaterialNumber;
    private String identifier;
    private String value;

    public PlanCnsFinPlanValEntity(Map<String, Object> map) {
        super(map);

        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setIdentifier((String) map.get("identifier"));
        setValue((String) map.get("value"));
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsConModeEntity extends CommonEntity {

    private String localConsumptionMode;
    private String sourceSystem;

    public PlanCnsConModeEntity(Map<String, Object> map) {
        super(map);

        setLocalConsumptionMode((String) map.get("localConsumptionMode"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getLocalConsumptionMode() {
        return this.localConsumptionMode;
    }

    public void setLocalConsumptionMode(String localConsumptionMode) {
        this.localConsumptionMode = localConsumptionMode;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}

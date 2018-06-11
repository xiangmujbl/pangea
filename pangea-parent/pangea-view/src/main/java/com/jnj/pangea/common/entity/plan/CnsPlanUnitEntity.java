package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsPlanUnitEntity extends CommonEntity {

    private String planFlag;
    private String unit;
    private String localUom;
    private String sourceSystem;

    public CnsPlanUnitEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setPlanFlag((String) map.get("planFlag"));
        setUnit((String) map.get("unit"));
        setLocalUom((String) map.get("localUom"));
    }

    public String getPlanFlag() {
        return planFlag;
    }

    public void setPlanFlag(String planFlag) {
        this.planFlag = planFlag;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocalUom() {
        return localUom;
    }

    public void setLocalUom(String localUom) {
        this.localUom = localUom;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }
}

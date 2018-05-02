package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlanUnitEntity extends CommonEntity {



    private String sourceSystem;
    private String localUom;
    private String localUomName;
    private String planFlag;
    private String unit;

    public PlanCnsPlanUnitEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalUom((String) map.get("localUom"));
        setLocalUomName((String) map.get("localUomName"));
        setPlanFlag((String) map.get("planFlag"));
        setUnit((String) map.get("unit"));
        setLocalUom((String) map.get("localUom"));
    }


    public String getLocalUom() {
        return this.localUom;
    }

    public void setLocalUom(String localUom) {
        this.localUom = localUom;
    }

    public String getLocalUomName() {
        return this.localUomName;
    }

    public void setLocalUomName(String localUomName) {
        this.localUomName = localUomName;
    }

    public String getPlanFlag() {
        return planFlag;
    }

    public void setPlanFlag(String planFlag) {
        this.planFlag = planFlag;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}

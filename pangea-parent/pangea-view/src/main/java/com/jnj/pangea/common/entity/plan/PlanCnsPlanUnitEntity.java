package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlanUnitEntity extends CommonEntity {

    private String localUom;
    private String localUomName;
    private String planFlag;
    private String sourceSystem;
    private String unit;
    private String factor;
    private String roundingDecimal;

    public PlanCnsPlanUnitEntity(Map<String, Object> map) {
        super(map);

        setLocalUom((String) map.get("localUom"));
        setLocalUomName((String) map.get("localUomName"));
        setPlanFlag((String) map.get("planFlag"));
        setSourceSystem((String) map.get("sourceSystem"));
        setUnit((String) map.get("unit"));
        setFactor((String) map.get("factor"));
        setRoundingDecimal((String) map.get("roundingDecimal"));
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
        return this.planFlag;
    }

    public void setPlanFlag(String planFlag) {
        this.planFlag = planFlag;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getFactor() {
        return this.factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getRoundingDecimal() {
        return this.roundingDecimal;
    }

    public void setRoundingDecimal(String roundingDecimal) {
        this.roundingDecimal = roundingDecimal;
    }

}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlanUnitEntity extends CommonEntity {

    private String localUom;
    private String localUomName;
    private String plantFlag;
    private String sourceSystem;
    private String unit;

    public PlanCnsPlanUnitEntity(Map<String, Object> map) {
        super(map);

        setLocalUom((String) map.get("localUom"));
        setLocalUomName((String) map.get("localUomName"));
        setPlantFlag((String) map.get("plantFlag"));
        setSourceSystem((String) map.get("sourceSystem"));
        setUnit((String) map.get("unit"));
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

    public String getPlantFlag() {
        return this.plantFlag;
    }

    public void setPlantFlag(String plantFlag) {
        this.plantFlag = plantFlag;
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

}

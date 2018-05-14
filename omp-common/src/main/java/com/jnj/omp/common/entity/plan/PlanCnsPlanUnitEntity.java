package com.jnj.omp.common.entity.plan;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlanUnitEntity extends CommonEntity {

    private String unit;
    private String localUom;

    public PlanCnsPlanUnitEntity(Map<String, Object> map) {
        super(map);

        setUnit((String) map.get("unit"));
        setLocalUom((String) map.get("localUom"));
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLocalUom() {
        return this.localUom;
    }

    public void setLocalUom(String localUom) {
        this.localUom = localUom;
    }

}

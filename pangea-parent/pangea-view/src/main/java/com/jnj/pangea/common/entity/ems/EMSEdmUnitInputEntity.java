package com.jnj.pangea.common.entity.ems;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EMSEdmUnitInputEntity extends CommonEntity {

    private String factor;
    private String roundingDecimal;
    private String sourceSystem;
    private String localUom;
    public EMSEdmUnitInputEntity(Map<String, Object> map) {
        super(map);
        setFactor((String) map.get("factor"));
        setRoundingDecimal((String) map.get("roundingDecimal"));
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalUom((String) map.get("localUom"));
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getRoundingDecimal() {
        return roundingDecimal;
    }

    public void setRoundingDecimal(String roundingDecimal) {
        this.roundingDecimal = roundingDecimal;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalUom() {
        return localUom;
    }

    public void setLocalUom(String localUom) {
        this.localUom = localUom;
    }
}

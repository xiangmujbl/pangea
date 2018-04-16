package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMUnitOfMeasureV1Entity extends CommonEntity {

    private String uom;
    private String factor;
    private String isoCode;
    private String uomName;
    private String measure;
    private String roundingDecimal;
    private String localUom;
    private String sourceSystem;

    public EDMUnitOfMeasureV1Entity(Map<String, Object> map) {
        super(map);

        setUom((String) map.get("uom"));
        setFactor((String) map.get("factor"));
        setIsoCode((String) map.get("isoCode"));
        setUomName((String) map.get("uomName"));
        setMeasure((String) map.get("measure"));
        setRoundingDecimal((String) map.get("roundingDecimal"));
        setLocalUom((String) map.get("localUom"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getUom() {
        return this.uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getFactor() {
        return this.factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getIsoCode() {
        return this.isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getUomName() {
        return this.uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getMeasure() {
        return this.measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getRoundingDecimal() {
        return this.roundingDecimal;
    }

    public void setRoundingDecimal(String roundingDecimal) {
        this.roundingDecimal = roundingDecimal;
    }

    public String getLocalUom() {
        return this.localUom;
    }

    public void setLocalUom(String localUom) {
        this.localUom = localUom;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

}

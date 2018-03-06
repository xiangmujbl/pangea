package com.jnj.pangea.view.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;

public class EDMUnitofMeasureBo extends BaseBo {
    private String sourceSystem;
    private String localUom;
    private String localUomName;
    private String uom;
    private String uomName;
    private String isocode;
    private String measure;
    private String factor;
    private String roundingDecimal;

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

    public String getLocalUomName() {
        return localUomName;
    }

    public void setLocalUomName(String localUomName) {
        this.localUomName = localUomName;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getUomName() {
        return uomName;
    }

    public void setUomName(String uomName) {
        this.uomName = uomName;
    }

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
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

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localUom", this.localUom)
                .toJsonString();
    }
}

package com.jnj.pangea.omp.gdm_unit_currency.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmUnitCurrencyBo extends BaseBo {

    private String unitId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String factor;
    private String iSOCode;
    private String longDescription;
    private String measure;
    private String precision;
    private String shortDescription;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("unitId", this.unitId)
                .toJsonString();
    }

    public String getUnitId() {
        return this.unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveFCTERP() {
        return this.activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return this.activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return this.activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getFactor() {
        return this.factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getISOCode() {
        return this.iSOCode;
    }

    public void setISOCode(String iSOCode) {
        this.iSOCode = iSOCode;
    }

    public String getLongDescription() {
        return this.longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getMeasure() {
        return this.measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getPrecision() {
        return this.precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}

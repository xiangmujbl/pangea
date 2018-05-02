package com.jnj.pangea.omp.gdm_unit_evol.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmUnitEvolBo extends BaseBo {

    private String uniqueId;
    private String activeFCTERP;
    private String unitId;
    private String startEff;
    private String endEff;
    private String factor;
    private String preference;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("uniqueId", this.uniqueId)
                .toJsonString();
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getActiveFCTERP() {
        return this.activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getUnitId() {
        return this.unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getStartEff() {
        return this.startEff;
    }

    public void setStartEff(String startEff) {
        this.startEff = startEff;
    }

    public String getEndEff() {
        return this.endEff;
    }

    public void setEndEff(String endEff) {
        this.endEff = endEff;
    }

    public String getFactor() {
        return this.factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getPreference() {
        return this.preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

}

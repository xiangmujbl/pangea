package com.jnj.pangea.omp.gdm_bom.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmBomBo extends BaseBo implements Cloneable{

    private String bomId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String comments;
    private String endEff;
    private String locationId;
    private String startEff;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("bomId", this.bomId)
                .toJsonString();
    }

    public String getBomId() {
        return this.bomId;
    }

    public void setBomId(String bomId) {
        this.bomId = bomId;
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

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEndEff() {
        return this.endEff;
    }

    public void setEndEff(String endEff) {
        this.endEff = endEff;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getStartEff() {
        return this.startEff;
    }

    public void setStartEff(String startEff) {
        this.startEff = startEff;
    }

    public Object copy() throws CloneNotSupportedException {
        return super.clone();
    }
}

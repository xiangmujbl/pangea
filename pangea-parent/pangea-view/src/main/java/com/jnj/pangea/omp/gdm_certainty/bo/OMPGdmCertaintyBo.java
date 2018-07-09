package com.jnj.pangea.omp.gdm_certainty.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmCertaintyBo extends BaseBo {

    private String certaintyId;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String label;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("certaintyId", this.certaintyId)
                .toJsonString();
    }

    public String getCertaintyId() {
        return this.certaintyId;
    }

    public void setCertaintyId(String certaintyId) {
        this.certaintyId = certaintyId;
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

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

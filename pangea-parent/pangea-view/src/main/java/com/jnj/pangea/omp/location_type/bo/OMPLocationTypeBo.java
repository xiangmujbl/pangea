package com.jnj.pangea.omp.location_type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPLocationTypeBo extends BaseBo {

    private String locationTypeId;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String label;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("locationTypeId", this.locationTypeId)
                .toJsonString();
    }

    public String getLocationTypeId() {
        return this.locationTypeId;
    }

    public void setLocationTypeId(String locationTypeId) {
        this.locationTypeId = locationTypeId;
    }

    public String getActiveFCTERP() {
        return activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return activeSOPERP;
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

package com.jnj.pangea.omp.location_type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPLocationTypeBo extends BaseBo {

    private String locationTypeId;
    private String activeFctErp;
    private String activeOprErp;
    private String activeSopErp;
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

    public String getActiveFctErp() {
        return this.activeFctErp;
    }

    public void setActiveFctErp(String activeFctErp) {
        this.activeFctErp = activeFctErp;
    }

    public String getActiveOprErp() {
        return this.activeOprErp;
    }

    public void setActiveOprErp(String activeOprErp) {
        this.activeOprErp = activeOprErp;
    }

    public String getActiveSopErp() {
        return this.activeSopErp;
    }

    public void setActiveSopErp(String activeSopErp) {
        this.activeSopErp = activeSopErp;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

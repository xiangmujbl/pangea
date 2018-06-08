package com.jnj.pangea.omp.gdm_location_type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmLocationTypeBo extends BaseBo {

    private String LocationTypeId;
    private String ActiveFCTERP;
    private String ActiveOPRERP;
    private String ActiveSOPERP;
    private String LABEL;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("locationTypeId", this.LocationTypeId)
                .toJsonString();
    }

    public String getLocationTypeId() {
        return this.LocationTypeId;
    }

    public void setLocationTypeId(String locationTypeId) {
        this.LocationTypeId = locationTypeId;
    }

    public String getActiveFCTERP() {
        return ActiveFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        ActiveFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return ActiveOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        ActiveOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return ActiveSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        ActiveSOPERP = activeSOPERP;
    }

    public String getLABEL() {
        return LABEL;
    }

    public void setLABEL(String LABEL) {
        this.LABEL = LABEL;
    }
}

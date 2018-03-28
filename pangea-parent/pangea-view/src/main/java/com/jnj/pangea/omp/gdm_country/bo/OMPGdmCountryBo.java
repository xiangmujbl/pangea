package com.jnj.pangea.omp.gdm_country.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmCountryBo extends BaseBo {

    private String countryID;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String countryDescription;
    private String MRC;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("countryID", this.countryID)
                .toJsonString();
    }

    public String getCountryID() {
        return countryID;
    }

    public void setCountryID(String countryID) {
        this.countryID = countryID;
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

    public String getCountryDescription() {
        return countryDescription;
    }

    public void setCountryDescription(String countryDescription) {
        this.countryDescription = countryDescription;
    }

    public String getMRC() {
        return MRC;
    }

    public void setMRC(String MRC) {
        this.MRC = MRC;
    }
}

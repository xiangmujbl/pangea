package com.jnj.pangea.omp.gdm_location_type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmLocationTypeBo extends BaseBo {

    private String locationTypeId;
    private String activeFprerp;
    private String activeOprerp;
    private String activeSoperp;
    private String label;

    // TODO add keys
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

    public String getActiveFprerp() {
        return this.activeFprerp;
    }

    public void setActiveFprerp(String activeFprerp) {
        this.activeFprerp = activeFprerp;
    }

    public String getActiveOprerp() {
        return this.activeOprerp;
    }

    public void setActiveOprerp(String activeOprerp) {
        this.activeOprerp = activeOprerp;
    }

    public String getActiveSoperp() {
        return this.activeSoperp;
    }

    public void setActiveSoperp(String activeSoperp) {
        this.activeSoperp = activeSoperp;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

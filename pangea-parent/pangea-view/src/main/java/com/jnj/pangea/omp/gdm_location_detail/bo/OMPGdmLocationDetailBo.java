package com.jnj.pangea.omp.gdm_location_detail.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmLocationDetailBo extends BaseBo {

    private String locationDetailId;
    private String activeOprerp;
    private String activeSoperp;
    private String CLASS;
    private String comments;
    private String description;



    private String locationId;
    private String name;
    private String unit;
    private String value;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("locationDetailId", this.locationDetailId)
                .toJsonString();
    }

    public String getLocationDetailId() {
        return this.locationDetailId;
    }

    public void setLocationDetailId(String locationDetailId) {
        this.locationDetailId = locationDetailId;
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

    public String getCLASS() {
        return CLASS;
    }

    public void setCLASS(String CLASS) {
        this.CLASS = CLASS;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
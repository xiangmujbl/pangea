package com.jnj.pangea.omp.gdm_product_location_detail.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductLocationDetailBo extends BaseBo {

    private String productLocationDetailId;
    private String activeOprerp;
    private String activeSoperp;
    private String CLASS;
    private String comments;
    private String description;
    private String name;
    private String productLocationId;
    private String unit;
    private String value;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("productLocationDetailId", this.productLocationDetailId)
                .toJsonString();
    }

    public String getProductLocationDetailId() {
        return this.productLocationDetailId;
    }

    public void setProductLocationDetailId(String productLocationDetailId) {
        this.productLocationDetailId = productLocationDetailId;
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
        return this.CLASS;
    }

    public void setCLASS(String cLASS) {
        this.CLASS = cLASS;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductLocationId() {
        return this.productLocationId;
    }

    public void setProductLocationId(String productLocationId) {
        this.productLocationId = productLocationId;
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

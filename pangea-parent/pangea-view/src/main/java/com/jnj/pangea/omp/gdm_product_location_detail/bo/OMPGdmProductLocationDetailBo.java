package com.jnj.pangea.omp.gdm_product_location_detail.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductLocationDetailBo extends BaseBo {

    private String productLocationDetailId;
    private String activeOPRERP;
    private String activeSOPERP;
    private String CLASS;
    private String comments;
    private String description;
    private String name;
    private String productLocationId;
    private String unit;
    private String value;

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

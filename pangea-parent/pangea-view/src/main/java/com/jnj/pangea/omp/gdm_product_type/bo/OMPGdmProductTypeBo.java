package com.jnj.pangea.omp.gdm_product_type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductTypeBo extends BaseBo {

    private String productTypeId;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String label;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("productTypeId", this.productTypeId)
                .toJsonString();
    }

    public String getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
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
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

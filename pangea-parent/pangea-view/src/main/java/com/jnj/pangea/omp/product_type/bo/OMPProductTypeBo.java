package com.jnj.pangea.omp.product_type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPProductTypeBo extends BaseBo {

    private String productTypeId;
    private String activeFctErp;
    private String activeOprErp;
    private String activeSopErp;
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

    public String getActiveFctErp() {
        return activeFctErp;
    }

    public void setActiveFctErp(String activeFctErp) {
        this.activeFctErp = activeFctErp;
    }

    public String getActiveOprErp() {
        return activeOprErp;
    }

    public void setActiveOprErp(String activeOprErp) {
        this.activeOprErp = activeOprErp;
    }

    public String getActiveSopErp() {
        return activeSopErp;
    }

    public void setActiveSopErp(String activeSopErp) {
        this.activeSopErp = activeSopErp;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

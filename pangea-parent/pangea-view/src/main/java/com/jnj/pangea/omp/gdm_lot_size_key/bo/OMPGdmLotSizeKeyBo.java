package com.jnj.pangea.omp.gdm_lot_size_key.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmLotSizeKeyBo extends BaseBo {

    private String lotSizeKey;
    private String  activeOPRERP;
    private String  activeSOPERP;
    private String  comments;
    private String  description;
    private String  period;
    private String  quantity;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("lotSizeKey", this.lotSizeKey)
                .toJsonString();
    }

    public String getLotSizeKey() {
        return lotSizeKey;
    }

    public void setLotSizeKey(String lotSizeKey) {
        this.lotSizeKey = lotSizeKey;
    }

    public String getactiveOPRERP() {
        return activeOPRERP;
    }

    public void setactiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getactiveSOPERP() {
        return activeSOPERP;
    }

    public void setactiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

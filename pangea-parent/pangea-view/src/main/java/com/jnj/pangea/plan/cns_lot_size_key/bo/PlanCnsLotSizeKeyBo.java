package com.jnj.pangea.plan.cns_lot_size_key.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsLotSizeKeyBo extends BaseBo {

    private String sourceSystem;
    private String localLotSizeKey;
    private String localLotSizeKeyDescription;
    private String period;
    private String quantity;
    private String lotSizeKey;
    private String lotSizeKeyDescription;
    private String comments;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localLotSizeKey",this.localLotSizeKey)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalLotSizeKey() {
        return this.localLotSizeKey;
    }

    public void setLocalLotSizeKey(String localLotSizeKey) {
        this.localLotSizeKey = localLotSizeKey;
    }

    public String getLocalLotSizeKeyDescription() {
        return this.localLotSizeKeyDescription;
    }

    public void setLocalLotSizeKeyDescription(String localLotSizeKeyDescription) {
        this.localLotSizeKeyDescription = localLotSizeKeyDescription;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getLotSizeKey() {
        return this.lotSizeKey;
    }

    public void setLotSizeKey(String lotSizeKey) {
        this.lotSizeKey = lotSizeKey;
    }

    public String getLotSizeKeyDescription() {
        return this.lotSizeKeyDescription;
    }

    public void setLotSizeKeyDescription(String lotSizeKeyDescription) {
        this.lotSizeKeyDescription = lotSizeKeyDescription;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}

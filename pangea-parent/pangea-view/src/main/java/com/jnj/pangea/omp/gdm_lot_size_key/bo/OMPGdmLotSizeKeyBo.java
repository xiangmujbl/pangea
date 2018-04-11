package com.jnj.pangea.omp.gdm_lot_size_key.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmLotSizeKeyBo extends BaseBo {

    private String lotSizeKey;
    private String  activeOprerp;
    private String  activeSoperp;
    private String  comments;
    private String  descRiption;
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

    public String getActiveOprerp() {
        return activeOprerp;
    }

    public void setActiveOprerp(String activeOprerp) {
        this.activeOprerp = activeOprerp;
    }

    public String getActiveSoperp() {
        return activeSoperp;
    }

    public void setActiveSoperp(String activeSoperp) {
        this.activeSoperp = activeSoperp;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescRiption() {
        return descRiption;
    }

    public void setDescRiption(String descRiption) {
        this.descRiption = descRiption;
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

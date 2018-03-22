package com.jnj.pangea.plan.cns_lot_size_key.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class CnsLotSizeKeyBo extends BaseBo{

    private String sourceSystem;
    private String localLotSizeKey;
    private String localLotSizeKeyDescription;
    private String lotSizeKey;
    private String lotSizeKeyDescription;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalLotSizeKey() {
        return localLotSizeKey;
    }

    public void setLocalLotSizeKey(String localLotSizeKey) {
        this.localLotSizeKey = localLotSizeKey;
    }

    public String getLocalLotSizeKeyDescription() {
        return localLotSizeKeyDescription;
    }

    public void setLocalLotSizeKeyDescription(String localLotSizeKeyDescription) {
        this.localLotSizeKeyDescription = localLotSizeKeyDescription;
    }

    public String getLotSizeKey() {
        return lotSizeKey;
    }

    public void setLotSizeKey(String lotSizeKey) {
        this.lotSizeKey = lotSizeKey;
    }

    public String getLotSizeKeyDescription() {
        return lotSizeKeyDescription;
    }

    public void setLotSizeKeyDescription(String lotSizeKeyDescription) {
        this.lotSizeKeyDescription = lotSizeKeyDescription;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localLotSizeKey",this.localLotSizeKey)
                .toJsonString();
    }
}

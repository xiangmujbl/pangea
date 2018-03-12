package com.jnj.pangea.edm.global_base_unit.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMGlobalBaseUnitBo extends BaseBo{
    private String gbu;
    private String gbuName;

    public String getGbu() {
        return gbu;
    }

    public void setGbu(String gbu) {
        this.gbu = gbu;
    }

    public String getGbuName() {
        return gbuName;
    }

    public void setGbuName(String gbuName) {
        this.gbuName = gbuName;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("gbu", this.gbu)
                .toJsonString();
    }
}

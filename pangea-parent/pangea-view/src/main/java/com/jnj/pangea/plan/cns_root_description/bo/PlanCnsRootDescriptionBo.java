package com.jnj.pangea.plan.cns_root_description.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsRootDescriptionBo extends BaseBo {

    private String sourceSystem;
    private String localDpParentCode;
    private String rootDesc;
    private String ovrRootDesc;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localDpParentCode", this.localDpParentCode)
                .add("ovrRootDesc", this.ovrRootDesc)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalDpParentCode() {
        return this.localDpParentCode;
    }

    public void setLocalDpParentCode(String localDpParentCode) {
        this.localDpParentCode = localDpParentCode;
    }

    public String getRootDesc() {
        return this.rootDesc;
    }

    public void setRootDesc(String rootDesc) {
        this.rootDesc = rootDesc;
    }

    public String getOvrRootDesc() {
        return this.ovrRootDesc;
    }

    public void setOvrRootDesc(String ovrRootDesc) {
        this.ovrRootDesc = ovrRootDesc;
    }

}

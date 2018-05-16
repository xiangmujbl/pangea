package com.jnj.pangea.plan.cns_spl_proc_typ.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsSplProcTypBo extends BaseBo {
    private String sourceSystem;
    private String localSplProcType;
    private String localSplProcTypeDesc;
    private String splProcType;
    private String splProcTypeDesc;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalSplProcType() {
        return localSplProcType;
    }

    public void setLocalSplProcType(String localSplProcType) {
        this.localSplProcType = localSplProcType;
    }

    public String getLocalSplProcTypeDesc() {
        return localSplProcTypeDesc;
    }

    public void setLocalSplProcTypeDesc(String localSplProcTypeDesc) {
        this.localSplProcTypeDesc = localSplProcTypeDesc;
    }

    public String getSplProcType() {
        return splProcType;
    }

    public void setSplProcType(String splProcType) {
        this.splProcType = splProcType;
    }

    public String getSplProcTypeDesc() {
        return splProcTypeDesc;
    }

    public void setSplProcTypeDesc(String splProcTypeDesc) {
        this.splProcTypeDesc = splProcTypeDesc;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localSplProcType",this.localSplProcType)
                .add("localSplProcTypeDesc",this.localSplProcTypeDesc)
                .add("splProcType",this.splProcType)
                .add("splProcTypeDesc",this.splProcTypeDesc)
                .toJsonString();
    }

}

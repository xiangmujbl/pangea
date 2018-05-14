package com.jnj.pangea.plan.cns_plng_strat_grp.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsPlngStratGrpBo extends BaseBo{
    private String sourceSystem;
    private String localPlanStratGrp;
    private String localPlanStratGrpDesc;
    private String planStratGrp;
    private String planStratGrpDesc;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPlanStratGrp() {
        return localPlanStratGrp;
    }

    public void setLocalPlanStratGrp(String localPlanStratGrp) {
        this.localPlanStratGrp = localPlanStratGrp;
    }

    public String getLocalPlanStratGrpDesc() {
        return localPlanStratGrpDesc;
    }

    public void setLocalPlanStratGrpDesc(String localPlanStratGrpDesc) {
        this.localPlanStratGrpDesc = localPlanStratGrpDesc;
    }

    public String getPlanStratGrp() {
        return planStratGrp;
    }

    public void setPlanStratGrp(String planStratGrp) {
        this.planStratGrp = planStratGrp;
    }

    public String getPlanStratGrpDesc() {
        return planStratGrpDesc;
    }

    public void setPlanStratGrpDesc(String planStratGrpDesc) {
        this.planStratGrpDesc = planStratGrpDesc;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localPlanStratGrp",this.localPlanStratGrp)
                .add("localPlanStratGrpDesc",this.localPlanStratGrpDesc)
                .toJsonString();
    }
}

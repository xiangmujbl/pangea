package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlngStratGrpEntity extends CommonEntity {

    private String sourceSystem;
    private String localPlanStratGrp;
    private String planStratGrp;

    public PlanCnsPlngStratGrpEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalPlanStratGrp((String) map.get("localPlanStratGrp"));
        setPlanStratGrp((String) map.get("planStratGrp"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalPlanStratGrp() {
        return this.localPlanStratGrp;
    }

    public void setLocalPlanStratGrp(String localPlanStratGrp) {
        this.localPlanStratGrp = localPlanStratGrp;
    }

    public String getPlanStratGrp() {
        return this.planStratGrp;
    }

    public void setPlanStratGrp(String planStratGrp) {
        this.planStratGrp = planStratGrp;
    }

}

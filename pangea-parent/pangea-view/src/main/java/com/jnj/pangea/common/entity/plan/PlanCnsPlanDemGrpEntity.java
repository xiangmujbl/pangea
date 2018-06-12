package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlanDemGrpEntity extends CommonEntity {

    private String demandGroupId;
    private String sourceSystem;
    private String demandGroupDesc;

    public PlanCnsPlanDemGrpEntity(Map<String, Object> map) {
        super(map);

        setDemandGroupId((String) map.get("demandGroupId"));
        setSourceSystem((String) map.get("sourceSystem"));
        setDemandGroupDesc((String) map.get("demandGroupDesc"));
    }

    public String getDemandGroupId() {
        return this.demandGroupId;
    }

    public void setDemandGroupId(String demandGroupId) {
        this.demandGroupId = demandGroupId;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDemandGroupDesc() {
        return this.demandGroupDesc;
    }

    public void setDemandGroupDesc(String demandGroupDesc) {
        this.demandGroupDesc = demandGroupDesc;
    }

}

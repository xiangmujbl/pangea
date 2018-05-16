package com.jnj.omp.common.entity.plan;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsLocTypeEntity extends CommonEntity {

    private String planLocTypeId;
    private String planLocTypeDesc;
    private String planLocTypeComments;

    public PlanCnsLocTypeEntity(Map<String, Object> map) {
        super(map);

        setPlanLocTypeId((String) map.get("planLocTypeId"));
        setPlanLocTypeDesc((String) map.get("planLocTypeDesc"));
        setPlanLocTypeComments((String) map.get("planLocTypeComments"));
    }

    public String getPlanLocTypeId() {
        return this.planLocTypeId;
    }

    public void setPlanLocTypeId(String planLocTypeId) {
        this.planLocTypeId = planLocTypeId;
    }

    public String getPlanLocTypeDesc() {
        return this.planLocTypeDesc;
    }

    public void setPlanLocTypeDesc(String planLocTypeDesc) {
        this.planLocTypeDesc = planLocTypeDesc;
    }

    public String getPlanLocTypeComments() {
        return this.planLocTypeComments;
    }

    public void setPlanLocTypeComments(String planLocTypeComments) {
        this.planLocTypeComments = planLocTypeComments;
    }

}

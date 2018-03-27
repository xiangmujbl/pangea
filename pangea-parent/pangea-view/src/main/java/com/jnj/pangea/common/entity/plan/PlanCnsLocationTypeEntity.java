package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsLocationTypeEntity extends CommonEntity {

    private String planLocTypeId;
    private String planLocTypeDesc;

    public PlanCnsLocationTypeEntity(Map<String, Object> map) {
        super(map);

        setPlanLocTypeId((String) map.get("planLocTypeId"));
        setPlanLocTypeDesc((String) map.get("planLocTypeDesc"));
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

}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlanRegionEntity extends CommonEntity {

    private String planningRegionId;
    private String planningRegionDesc;

    public PlanCnsPlanRegionEntity(Map<String, Object> map) {
        super(map);

        setPlanningRegionId((String) map.get("planningRegionId"));
        setPlanningRegionDesc((String) map.get("planningRegionDesc"));
    }

    public String getPlanningRegionId() {
        return this.planningRegionId;
    }

    public void setPlanningRegionId(String planningRegionId) {
        this.planningRegionId = planningRegionId;
    }

    public String getPlanningRegionDesc() {
        return this.planningRegionDesc;
    }

    public void setPlanningRegionDesc(String planningRegionDesc) {
        this.planningRegionDesc = planningRegionDesc;
    }

}

package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsPlanRegionEntity extends CommonEntity {

    public CnsPlanRegionEntity(Map<String, Object> map) {
        super(map);
        setPlanningRegionDesc((String) map.get("planningRegionDesc"));
        setPlanningRegionID((String) map.get("planningRegionId"));
    }
    private String planningRegionDesc;

    private String planningRegionId;

    public String getPlanningRegionDesc() {
        return planningRegionDesc;
    }

    public void setPlanningRegionDesc(String planningRegionDesc) {
        this.planningRegionDesc = planningRegionDesc;
    }

    public String getPlanningRegionID() {
        return planningRegionId;
    }

    public void setPlanningRegionID(String planningRegionID) {
        this.planningRegionId = planningRegionID;
    }
}

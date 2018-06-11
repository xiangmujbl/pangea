package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsPlanRegionEntity extends CommonEntity {

    public CnsPlanRegionEntity(Map<String, Object> map) {
        super(map);
        setPlanningRegionDesc((String) map.get("planningRegionDesc"));
        setPlanningRegionID((String) map.get("planningRegionID"));
    }
    private String planningRegionDesc;

    private String planningRegionID;

    public String getPlanningRegionDesc() {
        return planningRegionDesc;
    }

    public void setPlanningRegionDesc(String planningRegionDesc) {
        this.planningRegionDesc = planningRegionDesc;
    }

    public String getPlanningRegionID() {
        return planningRegionID;
    }

    public void setPlanningRegionID(String planningRegionID) {
        this.planningRegionID = planningRegionID;
    }
}

package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMCountryInputEntity extends CommonEntity {

    public EDMCountryInputEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setPlanningRegionID((String) map.get("planningRegionId"));
        setLocalCountry((String) map.get("localCountry"));
    }
    private String planningRegionId;

    private String sourceSystem;

    private String localCountry;

    public String getPlanningRegionID() {
        return planningRegionId;
    }

    public void setPlanningRegionID(String planningRegionID) {
        this.planningRegionId = planningRegionID;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalCountry() {
        return localCountry;
    }

    public void setLocalCountry(String localCountry) {
        this.localCountry = localCountry;
    }
}

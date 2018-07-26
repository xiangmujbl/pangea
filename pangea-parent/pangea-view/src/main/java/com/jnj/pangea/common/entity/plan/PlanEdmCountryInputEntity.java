package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanEdmCountryInputEntity extends CommonEntity {

    public PlanEdmCountryInputEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setPlanningRegionID((String) map.get("planningRegionID"));
        setLocalCountry((String) map.get("localCountry"));
        setLocalCurrency((String) map.get("localCurrency"));
    }
    private String planningRegionID;

    private String sourceSystem;

    private String localCountry;

    private String localCurrency;

    public String getPlanningRegionID() {
        return planningRegionID;
    }

    public void setPlanningRegionID(String planningRegionID) {
        this.planningRegionID = planningRegionID;
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

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }
}

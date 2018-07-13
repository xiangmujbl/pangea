package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanEdmCountryInputEntity extends CommonEntity {

    public PlanEdmCountryInputEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setPlanningRegionId((String) map.get("planningRegionId"));
        setLocalCountry((String) map.get("localCountry"));
        setLocalCurrency((String) map.get("localCurrency"));
    }
    private String planningRegionId;

    private String sourceSystem;

    private String localCountry;

    private String localCurrency;

    public String getPlanningRegionId() {
        return planningRegionId;
    }

    public void setPlanningRegionId(String planningRegionId) {
        this.planningRegionId = planningRegionId;
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

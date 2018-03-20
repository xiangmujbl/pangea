package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMCountryEntity extends CommonEntity {

    private String sourceSystem;
    private String localCountry;
    private String countryCode;
    private String countryName;
    private String consumerPlanningRegion;

    public EDMCountryEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setLocalCountry((String) map.get("localCountry"));
        setCountryCode((String) map.get("countryCode"));
        setCountryName((String) map.get("countryName"));
        setConsumerPlanningRegion((String) map.get("consumerPlanningRegion"));
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getConsumerPlanningRegion() {
        return consumerPlanningRegion;
    }

    public void setConsumerPlanningRegion(String consumerPlanningRegion) {
        this.consumerPlanningRegion = consumerPlanningRegion;
    }
}

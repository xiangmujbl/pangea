package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanConsTimeDepXchangeEntity extends CommonEntity {
    private String preference;
    private String unitId;
    private String endEff;
    private String factor;
    private String uniqueId;
    private String startEff;
    private String exchangeRate;

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getEndEff() {
        return endEff;
    }

    public void setEndEff(String endEff) {
        this.endEff = endEff;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getStartEff() {
        return startEff;
    }

    public void setStartEff(String startEff) {
        this.startEff = startEff;
    }

    public PlanConsTimeDepXchangeEntity(Map<String, Object> map) {
        super(map);
        setPreference((String) map.get("preference"));
        setUnitId((String)map.get("unitId"));
        setEndEff((String)map.get("endEff"));
        setFactor((String)map.get("factor"));
        setUniqueId((String)map.get("uniqueId"));
        setStartEff((String)map.get("startEff"));
        setExchangeRate((String)map.get("exchangeRate"));
    }
}

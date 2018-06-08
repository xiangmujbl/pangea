package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanConsTimeDepXchangeEntity extends CommonEntity {

    private String uniqueId;
    private String sourceSystem;
    private String fromCurrency;
    private String effectiveEndDate;
    private String effectiveStartDate;
    private String exchangeRate;
    private String preference;

    public PlanConsTimeDepXchangeEntity(Map<String, Object> map) {
        super(map);

        setUniqueId((String) map.get("uniqueId"));
        setSourceSystem((String) map.get("sourceSystem"));
        setFromCurrency((String) map.get("fromCurrency"));
        setEffectiveEndDate((String) map.get("endEff"));
        setEffectiveStartDate((String) map.get("startEff"));
        setExchangeRate((String) map.get("exchangeRate"));
        setPreference((String) map.get("preference"));
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getFromCurrency() {
        return this.fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getEffectiveEndDate() {
        return this.effectiveEndDate;
    }

    public void setEffectiveEndDate(String effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getEffectiveStartDate() {
        return this.effectiveStartDate;
    }

    public void setEffectiveStartDate(String effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }

    public String getExchangeRate() {
        return this.exchangeRate;
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
}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsFinPlanValEntity extends CommonEntity {

    private String localMaterialNumber;
    private String identifier;
    private String value;
    private String yearMonth;
    private String currency;

    public String getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PlanCnsFinPlanValEntity(Map<String, Object> map) {
        super(map);

        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setIdentifier((String) map.get("identifier"));
        setValue((String) map.get("value"));
        setYearMonth((String) map.get("yearMonth"));
        setCurrency((String) map.get("currency"));
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

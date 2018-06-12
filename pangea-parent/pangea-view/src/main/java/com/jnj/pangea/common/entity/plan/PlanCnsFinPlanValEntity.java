package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsFinPlanValEntity extends CommonEntity {

    private String localMaterialNumber ;
    private String identifier ;
    private String value ;
    private String yearMonth ;
    private String currency ;
    private String country ;

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
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
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PlanCnsFinPlanValEntity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setIdentifier((String) map.get("identifier"));
        setValue((String) map.get("value"));
        setYearMonth((String) map.get("yearMonth"));
        setCountry((String) map.get("country"));
        setCurrency((String) map.get("currency"));
    }

    @Override
    public String toString() {
        return "PlanCnsFinPlanValEntity{" +
                "localMaterialNumber='" + localMaterialNumber + '\'' +
                ", identifier='" + identifier + '\'' +
                ", value='" + value + '\'' +
                ", yearMonth='" + yearMonth + '\'' +
                ", currency='" + currency + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

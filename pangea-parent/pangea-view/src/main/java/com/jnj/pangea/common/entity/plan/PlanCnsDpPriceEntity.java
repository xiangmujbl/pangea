package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsDpPriceEntity extends CommonEntity {

    private String localMaterialNumber;
    private String country;
    private String currency;
    private String fromDate;
    private String toDate;
    private String salesPrice;
    private String sourceSystem;

    public PlanCnsDpPriceEntity(Map<String, Object> map) {
        super(map);
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setCountry((String) map.get("country"));
        setCurrency((String) map.get("currency"));
        setFromDate((String) map.get("fromDate"));
        setToDate((String) map.get("toDate"));
        setSalesPrice((String) map.get("salesPrice"));
        setSourceSystem((String) map.get("sourceSystem"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getCurrency() {
        return this.currency;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getSalesPrice() {
        return this.salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

}

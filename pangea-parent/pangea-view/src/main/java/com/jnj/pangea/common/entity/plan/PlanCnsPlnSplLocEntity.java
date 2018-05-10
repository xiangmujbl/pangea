package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsPlnSplLocEntity extends CommonEntity {

    private String sourceSystem;
    private String vendorOrCustomer;
    private String localNumber;
    private String localCountry;
    private String localCurrency;
    private String localName;
    private String planLocTypeId;
    private String localRegion;
    private String localPlant;

    public PlanCnsPlnSplLocEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setVendorOrCustomer((String) map.get("vendorOrCustomer"));
        setLocalNumber((String) map.get("localNumber"));
        setLocalCountry((String) map.get("localCountry"));
        setLocalCurrency((String) map.get("localCurrency"));
        setLocalName((String) map.get("localName"));
        setPlanLocTypeId((String) map.get("planLocTypeId"));
        setLocalRegion((String) map.get("localRegion"));
        setLocalPlant((String) map.get("localPlant"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getVendorOrCustomer() {
        return vendorOrCustomer;
    }

    public void setVendorOrCustomer(String vendorOrCustomer) {
        this.vendorOrCustomer = vendorOrCustomer;
    }

    public String getLocalCountry() {
        return this.localCountry;
    }

    public void setLocalCountry(String localCountry) {
        this.localCountry = localCountry;
    }

    public String getLocalCurrency() {
        return this.localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public String getLocalNumber() {
        return this.localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
    }

    public String getLocalName() {
        return this.localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getPlanLocTypeId() {
        return this.planLocTypeId;
    }

    public void setPlanLocTypeId(String planLocTypeId) {
        this.planLocTypeId = planLocTypeId;
    }

    public String getLocalRegion() {
        return this.localRegion;
    }

    public void setLocalRegion(String localRegion) {
        this.localRegion = localRegion;
    }

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }
}

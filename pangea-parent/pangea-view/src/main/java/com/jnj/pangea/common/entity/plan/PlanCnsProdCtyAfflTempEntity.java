package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProdCtyAfflTempEntity extends CommonEntity {
    private String sourceSystem;
    private String dpParentCode;
    private String country;
    private String prodClassification;
    private String ovrProdClass;
    private String prodStatus;
    private String ovrProdStat;
    private String dpSegmentation;
    private String dpPlannerId;
    private String rootSize;
    private String countryGrp;
    public PlanCnsProdCtyAfflTempEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setDpParentCode((String) map.get("dpParentCode"));
        setCountry((String) map.get("country"));
        setProdClassification((String) map.get("prodClassification"));
        setOvrProdClass((String) map.get("ovrProdClass"));
        setProdStatus((String) map.get("prodStatus"));
        setOvrProdStat((String) map.get("ovrProdStat"));
        setDpSegmentation((String) map.get("dpSegmentation"));
        setDpPlannerId((String) map.get("dpPlannerId"));
        setRootSize((String) map.get("rootSize"));
        setCountryGrp((String) map.get("countryGrp"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDpParentCode() {
        return dpParentCode;
    }

    public void setDpParentCode(String dpParentCode) {
        this.dpParentCode = dpParentCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProdClassification() {
        return prodClassification;
    }

    public void setProdClassification(String prodClassification) {
        this.prodClassification = prodClassification;
    }

    public String getOvrProdClass() {
        return ovrProdClass;
    }

    public void setOvrProdClass(String ovrProdClass) {
        this.ovrProdClass = ovrProdClass;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getOvrProdStat() {
        return ovrProdStat;
    }

    public void setOvrProdStat(String ovrProdStat) {
        this.ovrProdStat = ovrProdStat;
    }

    public String getDpSegmentation() {
        return dpSegmentation;
    }

    public void setDpSegmentation(String dpSegmentation) {
        this.dpSegmentation = dpSegmentation;
    }

    public String getDpPlannerId() {
        return dpPlannerId;
    }

    public void setDpPlannerId(String dpPlannerId) {
        this.dpPlannerId = dpPlannerId;
    }

    public String getRootSize() {
        return rootSize;
    }

    public void setRootSize(String rootSize) {
        this.rootSize = rootSize;
    }

    public String getCountryGrp() {
        return countryGrp;
    }

    public void setCountryGrp(String countryGrp) {
        this.countryGrp = countryGrp;
    }
}

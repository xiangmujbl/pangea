package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsProdCtyAffEntity extends CommonEntity {

    private String sourceSystem;
    private String dpParentCode;
    private String country;
    private String prodClassification;
    private String prodStatus;
    private String ovrPrdClass;
    private String ovrPrdStat;
    private String dpSegmentation;
    private String dpPlannerId;
    private String rootSize;
    private String countryGrp;

    public CnsProdCtyAffEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setDpParentCode((String) map.get("dpParentCode"));
        setCountry((String) map.get("country"));
        setProdClassification((String) map.get("prodClassification"));
        setProdStatus((String) map.get("prodStatus"));
        setOvrPrdClass((String) map.get("ovrPrdClass"));
        setOvrPrdStat((String) map.get("ovrPrdStat"));
        setDpSegmentation((String) map.get("DPSegmentation"));
        setDpPlannerId((String) map.get("DPPlannerID"));
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

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getOvrPrdClass() {
        return ovrPrdClass;
    }

    public void setOvrPrdClass(String ovrPrdClass) {
        this.ovrPrdClass = ovrPrdClass;
    }

    public String getOvrPrdStat() {
        return ovrPrdStat;
    }

    public void setOvrPrdStat(String ovrPrdStat) {
        this.ovrPrdStat = ovrPrdStat;
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

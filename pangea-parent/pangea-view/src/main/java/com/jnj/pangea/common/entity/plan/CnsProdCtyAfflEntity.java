package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class CnsProdCtyAfflEntity extends CommonEntity {

    private String sourceSystem;
    private String dpParentCode;
    private String country;
    private String productClassification;
    private String productStatus;
    private String dpSegmentation;
    private String dpPlanner;
    private String rootSize;
    private String countryGroup;

    public CnsProdCtyAfflEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("countryGroup"));
        setDpParentCode((String) map.get("dpParentCode"));
        setCountry((String) map.get("country"));
        setProductClassification((String) map.get("productClassification"));
        setProductStatus((String) map.get("productStatus"));
        setDpSegmentation((String) map.get("dpSegmentation"));
        setDpPlanner((String) map.get("dpPlanner"));
        setRootSize((String) map.get("rootSize"));
        setCountryGroup((String) map.get("countryGroup"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryGroup() {
        return countryGroup;
    }

    public void setCountryGroup(String countryGroup) {
        this.countryGroup = countryGroup;
    }

    public String getDpPlanner() {
        return dpPlanner;
    }

    public void setDpPlanner(String dpPlanner) {
        this.dpPlanner = dpPlanner;
    }

    public String getDpSegmentation() {
        return dpSegmentation;
    }

    public void setDpSegmentation(String dpSegmentation) {
        this.dpSegmentation = dpSegmentation;
    }

    public String getProductClassification() {
        return productClassification;
    }

    public void setProductClassification(String productClassification) {
        this.productClassification = productClassification;
    }

    public String getDpParentCode() {
        return dpParentCode;
    }

    public void setDpParentCode(String dpParentCode) {
        this.dpParentCode = dpParentCode;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getRootSize() {
        return rootSize;
    }

    public void setRootSize(String rootSize) {
        this.rootSize = rootSize;
    }
}

package com.jnj.pangea.plan.cns_prod_cty_affl.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsProdCtyAfflBo extends BaseBo {

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

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("dpParentCode",this.dpParentCode)
                .add("country",this.country)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDpParentCode() {
        return this.dpParentCode;
    }

    public void setDpParentCode(String dpParentCode) {
        this.dpParentCode = dpParentCode;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProdClassification() {
        return this.prodClassification;
    }

    public void setProdClassification(String prodClassification) {
        this.prodClassification = prodClassification;
    }

    public String getOvrProdClass() {
        return this.ovrProdClass;
    }

    public void setOvrProdClass(String ovrProdClass) {
        this.ovrProdClass = ovrProdClass;
    }

    public String getProdStatus() {
        return this.prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getOvrProdStat() {
        return this.ovrProdStat;
    }

    public void setOvrProdStat(String ovrProdStat) {
        this.ovrProdStat = ovrProdStat;
    }

    public String getDpSegmentation() {
        return this.dpSegmentation;
    }

    public void setDpSegmentation(String dpSegmentation) {
        this.dpSegmentation = dpSegmentation;
    }

    public String getDpPlannerId() {
        return this.dpPlannerId;
    }

    public void setDpPlannerId(String dpPlannerId) {
        this.dpPlannerId = dpPlannerId;
    }

    public String getRootSize() {
        return this.rootSize;
    }

    public void setRootSize(String rootSize) {
        this.rootSize = rootSize;
    }

    public String getCountryGrp() {
        return this.countryGrp;
    }

    public void setCountryGrp(String countryGrp) {
        this.countryGrp = countryGrp;
    }

    @Override
    public String toString() {
        return "PlanCnsProdCtyAfflBo{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", dpParentCode='" + dpParentCode + '\'' +
                ", country='" + country + '\'' +
                ", prodClassification='" + prodClassification + '\'' +
                ", ovrProdClass='" + ovrProdClass + '\'' +
                ", prodStatus='" + prodStatus + '\'' +
                ", ovrProdStat='" + ovrProdStat + '\'' +
                ", dpSegmentation='" + dpSegmentation + '\'' +
                ", dpPlannerId='" + dpPlannerId + '\'' +
                ", rootSize='" + rootSize + '\'' +
                ", countryGrp='" + countryGrp + '\'' +
                '}';
    }
}

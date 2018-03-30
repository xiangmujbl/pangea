package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMCurrencyV1Entity extends CommonEntity {

    private String localCountry;
    private String sourceSystem;
    private String isoNumeric;
    private String currencyName;
    private String currencyCode;

    public EDMCurrencyV1Entity(Map<String, Object> map) {
        super(map);

        setLocalCountry((String) map.get("localCountry"));
        setSourceSystem((String) map.get("sourceSystem"));
        setIsoNumeric((String) map.get("isoNumeric"));
        setCurrencyName((String) map.get("currencyName"));
        setCurrencyCode((String) map.get("currencyCode"));
    }

    public String getLocalCountry() {
        return this.localCountry;
    }

    public void setLocalCountry(String localCountry) {
        this.localCountry = localCountry;
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getIsoNumeric() {
        return this.isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    public String getCurrencyName() {
        return this.currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}

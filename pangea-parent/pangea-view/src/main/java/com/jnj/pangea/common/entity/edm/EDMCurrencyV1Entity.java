package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMCurrencyV1Entity extends CommonEntity {
    private String localCurrency;
    private String sourceSystem;
    private String isoNumeric;
    private String currencyName;
    private String currencyCode;

    public EDMCurrencyV1Entity(Map<String, Object> map) {
        super(map);

        setLocalCurrency((String) map.get("localCurrency"));
        setSourceSystem((String) map.get("sourceSystem"));
        setIsoNumeric((String) map.get("isoNumeric"));
        setCurrencyName((String) map.get("currencyName"));
        setCurrencyCode((String) map.get("currencyCode"));
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
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

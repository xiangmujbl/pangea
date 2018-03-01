package com.jnj.pangea.view.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;

public class CurrencyBo  extends BaseBo{
    @Override
    public String toString() {
        return "CurrencyBo{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", localCurrency='" + localCurrency + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", isoNumeric='" + isoNumeric + '\'' +
                '}';
    }

    private String sourceSystem;
    private String localCurrency;
    private String currencyCode;
    private String currencyName;
    private String isoNumeric;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalCurrency() {
        return localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getIsoNumeric() {
        return isoNumeric;
    }

    public void setIsoNumeric(String isoNumeric) {
        this.isoNumeric = isoNumeric;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localCurrency", this.localCurrency)
                .toJsonString();
    }
}

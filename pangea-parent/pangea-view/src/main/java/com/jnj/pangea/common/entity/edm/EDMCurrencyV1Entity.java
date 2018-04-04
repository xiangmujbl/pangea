package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMCurrencyV1Entity extends CommonEntity {

    private String localCurrency;
    private String currencyCode;

    public EDMCurrencyV1Entity(Map<String, Object> map) {
        super(map);

        setLocalCurrency((String) map.get("localCurrency"));
        setCurrencyCode((String) map.get("currencyCode"));
    }

    public String getLocalCurrency() {
        return this.localCurrency;
    }

    public void setLocalCurrency(String localCurrency) {
        this.localCurrency = localCurrency;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

}

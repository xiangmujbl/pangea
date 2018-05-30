package com.jnj.pangea.omp.gdm_conversion_storage.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmConversionStorageBo extends BaseBo {

    private String aggregationId;
    private String conversionFactor;
    private String currencyId;
    private String dueDate;
    private String fromDueDate;
    private String value;
    private String unitId;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("aggregationId", this.aggregationId)
                .add("conversionFactor", this.conversionFactor)
                .add("currencyId", this.currencyId)
                .add("dueDate", this.dueDate)
                .add("fromDueDate", this.fromDueDate)
                .toJsonString();
    }

    public String getAggregationId() {
        return this.aggregationId;
    }

    public void setAggregationId(String aggregationId) {
        this.aggregationId = aggregationId;
    }

    public String getCurrencyId() {
        return this.currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getFromDueDate() {
        return this.fromDueDate;
    }

    public void setFromDueDate(String fromDueDate) {
        this.fromDueDate = fromDueDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnitId() {
        return this.unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(String conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

}

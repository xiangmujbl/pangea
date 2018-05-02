package com.jnj.pangea.omp.gdm_pos.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmPosBo extends BaseBo {

    private String aggregationId;
    private String currencyId;
    private String dueDate;
    private String forecastUploadId;
    private String fromDueDate;
    private String unitId;
    private String quantity;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("aggregationId", this.aggregationId)
                .add("unitId", this.unitId)
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

    public String getForecastUploadId() {
        return this.forecastUploadId;
    }

    public void setForecastUploadId(String forecastUploadId) {
        this.forecastUploadId = forecastUploadId;
    }

    public String getFromDueDate() {
        return this.fromDueDate;
    }

    public void setFromDueDate(String fromDueDate) {
        this.fromDueDate = fromDueDate;
    }

    public String getUnitId() {
        return this.unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}

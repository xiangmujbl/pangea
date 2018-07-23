package com.jnj.pangea.omp.gdm_fbp.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmFbpBo extends BaseBo {

    private String fbpId;
    private String countryId;
    private String currencyId;
    private String dueDate;
    private String fromDueDate;
    private String productId;
    private String value;
    private String volume;
    private String aggregationId;




    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("fbpId", this.fbpId)
                .add("aggregationId", this.aggregationId)
                .toJsonString();
    }

    public String getFbpId() {
        return fbpId;
    }
    public String getAggregationId() {
        return aggregationId;
    }

    public void setAggregationId(String aggregationId) {
        this.aggregationId = aggregationId;
    }
    public void setFbpId(String fbpId) {
        this.fbpId = fbpId;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
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

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getVolume() {
        return this.volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "OMPGdmfbpBo{" +
                "fbpId='" + fbpId + '\'' +
                ", countryId='" + countryId + '\'' +
                ", currencyId='" + currencyId + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", fromDueDate='" + fromDueDate + '\'' +
                ", productId='" + productId + '\'' +
                ", value='" + value + '\'' +
                ", volume='" + volume + '\'' +
                ", aggregationId='" + aggregationId + '\'' +
                '}';
    }
}

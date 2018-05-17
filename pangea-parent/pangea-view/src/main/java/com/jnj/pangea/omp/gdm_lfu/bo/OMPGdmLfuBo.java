package com.jnj.pangea.omp.gdm_lfu.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmLfuBo extends BaseBo {

    private String LFUId ;
    private String CountryId ;
    private String CurrencyId ;
    private String DueDate ;
    private String FromDueDate ;
    private String ProductId ;
    private String Value ;
    private String Volume ;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("LFUId", this.LFUId)
                .toJsonString();
    }

    public String getLFUId() {
        return LFUId;
    }

    public void setLFUId(String LFUId) {
        this.LFUId = LFUId;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getCurrencyId() {
        return CurrencyId;
    }

    public void setCurrencyId(String currencyId) {
        CurrencyId = currencyId;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public String getFromDueDate() {
        return FromDueDate;
    }

    public void setFromDueDate(String fromDueDate) {
        FromDueDate = fromDueDate;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    @Override
    public String toString() {
        return "OMPGdmLfuBo{" +
                "LFUId='" + LFUId + '\'' +
                ", CountryId='" + CountryId + '\'' +
                ", CurrencyId='" + CurrencyId + '\'' +
                ", DueDate='" + DueDate + '\'' +
                ", FromDueDate='" + FromDueDate + '\'' +
                ", ProductId='" + ProductId + '\'' +
                ", Value='" + Value + '\'' +
                ", Volume='" + Volume + '\'' +
                '}';
    }
}

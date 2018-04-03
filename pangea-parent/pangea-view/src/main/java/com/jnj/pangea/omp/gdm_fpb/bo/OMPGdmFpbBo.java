package com.jnj.pangea.omp.gdm_fpb.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmFpbBo extends BaseBo {

    private String fpbId;
    private String countryId;
    private String currencyId;
    private String dueDate;
    private String fromDueDate;
    private String productId;
    private String value;
    private String volume;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("fpbId", this.fpbId)
                .toJsonString();
    }

    public String getFpbId() {
        return this.fpbId;
    }

    public void setFpbId(String fpbId) {
        this.fpbId = fpbId;
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

}

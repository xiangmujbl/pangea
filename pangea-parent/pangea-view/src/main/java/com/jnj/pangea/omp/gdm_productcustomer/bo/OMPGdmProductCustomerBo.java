package com.jnj.pangea.omp.gdm_productcustomer.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductCustomerBo extends BaseBo {

    private String customerId;
    private String productId;
    private String productStatus;
    private String moq;
    private String ioq;
    private String locationId;
    private String norm;
    private String leadTime;
    private String roundingThreshold;
    private String stockLevel;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("customerId", this.customerId)
                .add("productId",productId)
                .toJsonString();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getMoq() {
        return moq;
    }

    public void setMoq(String moq) {
        this.moq = moq;
    }

    public String getIoq() {
        return ioq;
    }

    public void setIoq(String ioq) {
        this.ioq = ioq;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getRoundingThreshold() {
        return roundingThreshold;
    }

    public void setRoundingThreshold(String roundingThreshold) {
        this.roundingThreshold = roundingThreshold;
    }

    public String getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(String stockLevel) {
        this.stockLevel = stockLevel;
    }
}

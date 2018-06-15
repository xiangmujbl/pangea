package com.jnj.pangea.omp.gdm_productcustomer.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductCustomerBo extends BaseBo {

    private String productCustomerId;
    private String productStatus;
    private String minStock;
    private String maxStock;
    private String preferredDC;
    private String leadTime;
    private String revenueRecognitionOffset;
    private String stockLevel;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("productCustomerId", this.productCustomerId)
                .toJsonString();
    }

    public String getProductCustomerId() {
        return productCustomerId;
    }

    public void setProductCustomerId(String productCustomerId) {
        this.productCustomerId = productCustomerId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getMinStock() {
        return minStock;
    }

    public void setMinStock(String minStock) {
        this.minStock = minStock;
    }

    public String getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(String maxStock) {
        this.maxStock = maxStock;
    }

    public String getPreferredDC() {
        return preferredDC;
    }

    public void setPreferredDC(String preferredDC) {
        this.preferredDC = preferredDC;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getRevenueRecognitionOffset() {
        return revenueRecognitionOffset;
    }

    public void setRevenueRecognitionOffset(String revenueRecognitionOffset) {
        this.revenueRecognitionOffset = revenueRecognitionOffset;
    }

    public String getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(String stockLevel) {
        this.stockLevel = stockLevel;
    }
}

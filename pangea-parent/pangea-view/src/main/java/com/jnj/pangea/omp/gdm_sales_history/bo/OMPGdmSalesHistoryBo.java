package com.jnj.pangea.omp.gdm_sales_history.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmSalesHistoryBo extends BaseBo {

    private String salesHistoryId;
    private String activeFCTERP;
    private String certaintyId;
    private String conversionFactorXx;
    private String currencyId;
    private String customerId;
    private String demandStreamId;
    private String dueDate;
    private String forecastItemId;
    private String fromDueDate;
    private String locationId;
    private String orderReason;
    private String orderStatus;
    private String orderSubType;
    private String orderType;
    private String productId;
    private String quantity;
    private String salesUnit;
    private String unitId;
    private String validValueXx;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("salesHistoryId", this.salesHistoryId)
                .add("activeFCTERP",this.activeFCTERP)
                .toJsonString();
    }

    public String getSalesHistoryId() {
        return this.salesHistoryId;
    }

    public void setSalesHistoryId(String salesHistoryId) {
        this.salesHistoryId = salesHistoryId;
    }

    public String getActiveFCTERP() {
        return this.activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getCertaintyId() {
        return this.certaintyId;
    }

    public void setCertaintyId(String certaintyId) {
        this.certaintyId = certaintyId;
    }

    public String getConversionFactorXx() {
        return this.conversionFactorXx;
    }

    public void setConversionFactorXx(String conversionFactorXx) {
        this.conversionFactorXx = conversionFactorXx;
    }

    public String getCurrencyId() {
        return this.currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDemandStreamId() {
        return this.demandStreamId;
    }

    public void setDemandStreamId(String demandStreamId) {
        this.demandStreamId = demandStreamId;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getForecastItemId() {
        return this.forecastItemId;
    }

    public void setForecastItemId(String forecastItemId) {
        this.forecastItemId = forecastItemId;
    }

    public String getFromDueDate() {
        return this.fromDueDate;
    }

    public void setFromDueDate(String fromDueDate) {
        this.fromDueDate = fromDueDate;
    }

    public String getLocationId() {
        return this.locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getOrderReason() {
        return this.orderReason;
    }

    public void setOrderReason(String orderReason) {
        this.orderReason = orderReason;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderSubType() {
        return this.orderSubType;
    }

    public void setOrderSubType(String orderSubType) {
        this.orderSubType = orderSubType;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSalesUnit() {
        return this.salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getUnitId() {
        return this.unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getValidValueXx() {
        return this.validValueXx;
    }

    public void setValidValueXx(String validValueXx) {
        this.validValueXx = validValueXx;
    }

}

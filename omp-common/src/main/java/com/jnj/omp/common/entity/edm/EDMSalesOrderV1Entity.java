package com.jnj.omp.common.entity.edm;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EDMSalesOrderV1Entity extends CommonEntity {

    private String sourceSystem;
    private String salesOrderNo;
    private String salesOrderItem;
    private String scheduleLineItem;
    private String localSalesOrg;
    private String localShipToParty;
    private String localOrderCreateDt;
    private String localOrderType;
    private String localPlant;
    private String localMaterialNumber;
    private String localItemCategory;
    private String localSDItemCurrency;
    private String localRequestedDate;
    private String localRejReason;
    private String salesOrderQty;
    private String localNumtoBase;
    private String localDentoBase;
    private String localRoute;

    public EDMSalesOrderV1Entity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setSalesOrderNo((String) map.get("salesOrderNo"));
        setSalesOrderItem((String) map.get("salesOrderItem"));
        setScheduleLineItem((String) map.get("scheduleLineItem"));
        setLocalSalesOrg((String) map.get("localSalesOrg"));
        setLocalShipToParty((String) map.get("localShipToParty"));
        setLocalOrderCreateDt((String) map.get("localOrderCreateDt"));
        setLocalOrderType((String) map.get("localOrderType"));
        setLocalPlant((String) map.get("localPlant"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalItemCategory((String) map.get("localItemCategory"));
        setLocalSDItemCurrency((String) map.get("localSDItemCurrency"));
        setLocalRequestedDate((String) map.get("localRequestedDate"));
        setLocalRejReason((String) map.get("localRejReason"));
        setSalesOrderQty((String) map.get("salesOrderQty"));
        setLocalNumtoBase((String) map.get("localNumtoBase"));
        setLocalDentoBase((String) map.get("localDentoBase"));
        setLocalRoute((String) map.get("localRoute"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getSalesOrderNo() {
        return this.salesOrderNo;
    }

    public void setSalesOrderNo(String salesOrderNo) {
        this.salesOrderNo = salesOrderNo;
    }

    public String getSalesOrderItem() {
        return this.salesOrderItem;
    }

    public void setSalesOrderItem(String salesOrderItem) {
        this.salesOrderItem = salesOrderItem;
    }

    public String getScheduleLineItem() {
        return scheduleLineItem;
    }

    public void setScheduleLineItem(String scheduleLineItem) {
        this.scheduleLineItem = scheduleLineItem;
    }

    public String getLocalSalesOrg() {
        return this.localSalesOrg;
    }

    public void setLocalSalesOrg(String localSalesOrg) {
        this.localSalesOrg = localSalesOrg;
    }

    public String getLocalShipToParty() {
        return this.localShipToParty;
    }

    public void setLocalShipToParty(String localShipToParty) {
        this.localShipToParty = localShipToParty;
    }

    public String getLocalOrderCreateDt() {
        return this.localOrderCreateDt;
    }

    public void setLocalOrderCreateDt(String localOrderCreateDt) {
        this.localOrderCreateDt = localOrderCreateDt;
    }

    public String getLocalOrderType() {
        return this.localOrderType;
    }

    public void setLocalOrderType(String localOrderType) {
        this.localOrderType = localOrderType;
    }

    public String getLocalPlant() {
        return this.localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalItemCategory() {
        return this.localItemCategory;
    }

    public void setLocalItemCategory(String localItemCategory) {
        this.localItemCategory = localItemCategory;
    }

    public String getLocalSDItemCurrency() {
        return localSDItemCurrency;
    }

    public void setLocalSDItemCurrency(String localSDItemCurrency) {
        this.localSDItemCurrency = localSDItemCurrency;
    }

    public String getLocalRequestedDate() {
        return this.localRequestedDate;
    }

    public void setLocalRequestedDate(String localRequestedDate) {
        this.localRequestedDate = localRequestedDate;
    }

    public String getLocalRejReason() {
        return this.localRejReason;
    }

    public void setLocalRejReason(String localRejReason) {
        this.localRejReason = localRejReason;
    }

    public String getSalesOrderQty() {
        return this.salesOrderQty;
    }

    public void setSalesOrderQty(String salesOrderQty) {
        this.salesOrderQty = salesOrderQty;
    }

    public String getLocalNumtoBase() {
        return this.localNumtoBase;
    }

    public void setLocalNumtoBase(String localNumtoBase) {
        this.localNumtoBase = localNumtoBase;
    }

    public String getLocalDentoBase() {
        return this.localDentoBase;
    }

    public void setLocalDentoBase(String localDentoBase) {
        this.localDentoBase = localDentoBase;
    }

    public String getLocalRoute() {
        return this.localRoute;
    }

    public void setLocalRoute(String localRoute) {
        this.localRoute = localRoute;
    }

}

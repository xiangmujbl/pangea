package com.jnj.pangea.omp.gdm_demand.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmDemandBo extends BaseBo {

    private String active;
    private String activeOPRERP;
    private String activeOBDPERP;
    private String activeFCTERP;
    private String batchId;
    private String certaintyId;
    private String customerId;
    private String planningStrategy;
    private String fromDueDate;
    private String dueDate;
    private String locationId;
    private String minRemainingShelfLife;
    private String productId;
    private String demandId;
    private String inventoryLinkGroupId;
    private String quantity;
    private String unitId;
    private String WRK02;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("demandId", demandId)
                .toJsonString();
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveOPRERP() {
        return activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveOBDPERP() {
        return activeOBDPERP;
    }

    public void setActiveOBDPERP(String activeOBDPERP) {
        this.activeOBDPERP = activeOBDPERP;
    }

    public String getActiveFCTERP() {
        return activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getCertaintyId() {
        return certaintyId;
    }

    public void setCertaintyId(String certaintyId) {
        this.certaintyId = certaintyId;
    }

    public String getCustomerId() { return customerId; }

    public  void setCustomerId(String customerId) { this.customerId = customerId; }

    public String getPlanningStrategy() {
        return planningStrategy;
    }

    public void setPlanningStrategy(String planningStrategy) {
        this.planningStrategy = planningStrategy;
    }

    public String getFromDueDate() {
        return fromDueDate;
    }

    public void setFromDueDate(String fromDueDate) {
        this.fromDueDate = fromDueDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getMinRemainingShelfLife() {
        return minRemainingShelfLife;
    }

    public void setMinRemainingShelfLife(String minRemainingShelfLife) {
        this.minRemainingShelfLife = minRemainingShelfLife;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDemandId() {
        return demandId;
    }

    public void setDemandId(String demandId) {
        this.demandId = demandId;
    }

    public String getInventoryLinkGroupId() {
        return inventoryLinkGroupId;
    }

    public void setInventoryLinkGroupId(String inventoryLinkGroupId) {
        this.inventoryLinkGroupId = inventoryLinkGroupId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getWRK02() {
        return WRK02;
    }

    public void setWRK02(String WRK02) {
        this.WRK02 = WRK02;
    }

}

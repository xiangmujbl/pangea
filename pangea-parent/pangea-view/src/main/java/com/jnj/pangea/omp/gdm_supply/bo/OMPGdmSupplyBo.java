package com.jnj.pangea.omp.gdm_supply.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmSupplyBo extends BaseBo {

    private String supplyId;
    private String active;
    private String activeOprerp;
    private String activeSoperp;
    private String fromDate;
    private String label;
    private String locationId;
    private String machineCapacity;
    private String machineTypeId;
    private String maxQuantity;
    private String maxQuantityType;
    private String minQuantity;
    private String minQuantityType;
    private String planLevelId;
    private String preference;
    private String processTypeId;
    private String productId;
    private String purchasingGroup;
    private String purchasingOrganization;
    private String toDate;
    private String transportType;
    private String supplierId;
    private String vendor;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("supplyId", this.supplyId)
                .toJsonString();
    }

    public String getSupplyId() {
        return this.supplyId;
    }

    public void setSupplyId(String supplyId) {
        this.supplyId = supplyId;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveOprerp() {
        return this.activeOprerp;
    }

    public void setActiveOprerp(String activeOprerp) {
        this.activeOprerp = activeOprerp;
    }

    public String getActiveSoperp() {
        return this.activeSoperp;
    }

    public void setActiveSoperp(String activeSoperp) {
        this.activeSoperp = activeSoperp;
    }

    public String getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getMachineCapacity() {
        return machineCapacity;
    }

    public void setMachineCapacity(String machineCapacity) {
        this.machineCapacity = machineCapacity;
    }

    public String getMachineTypeId() {
        return machineTypeId;
    }

    public void setMachineTypeId(String machineTypeId) {
        this.machineTypeId = machineTypeId;
    }

    public String getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(String maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public String getMaxQuantityType() {
        return maxQuantityType;
    }

    public void setMaxQuantityType(String maxQuantityType) {
        this.maxQuantityType = maxQuantityType;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getMinQuantityType() {
        return minQuantityType;
    }

    public void setMinQuantityType(String minQuantityType) {
        this.minQuantityType = minQuantityType;
    }

    public String getPlanLevelId() {
        return planLevelId;
    }

    public void setPlanLevelId(String planLevelId) {
        this.planLevelId = planLevelId;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }

    public String getProcessTypeId() {
        return processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPurchasingGroup() {
        return purchasingGroup;
    }

    public void setPurchasingGroup(String purchasingGroup) {
        this.purchasingGroup = purchasingGroup;
    }

    public String getPurchasingOrganization() {
        return purchasingOrganization;
    }

    public void setPurchasingOrganization(String purchasingOrganization) {
        this.purchasingOrganization = purchasingOrganization;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}

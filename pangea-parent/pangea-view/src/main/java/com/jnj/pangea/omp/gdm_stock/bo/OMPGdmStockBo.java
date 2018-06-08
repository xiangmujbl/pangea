package com.jnj.pangea.omp.gdm_stock.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmStockBo extends BaseBo {

    private String stockId;
    private String active;
    private String activeOPRERP;
    private String activeSOPERP;
    private String batchId;
    private String blockedQuantity;
    private String consignment;
    private String certaintyID;
    private String erpOrderId;
    private String inventoryLinkGroupId;
    private String vendorId;
    private String locationId;
    private String processId;
    private String processTypeId;
    private String productId;
    private String qualityQuantity;
    private String quantity;
    private String receiptDate;
    private String restrictedQuantity;
    private String returnsQuantity;
    private String startDate;
    private String stockType;
    private String transferQuantity;
    private String transitDate;
    private String unrestrictedQuantity;

    //No keys currently in the DOMD so using stockId for the moment
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("stockId", this.stockId)
                .toJsonString();
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
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

    public String getActiveSOPERP() {
        return activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getBlockedQuantity() {
        return blockedQuantity;
    }

    public void setBlockedQuantity(String blockedQuantity) {
        this.blockedQuantity = blockedQuantity;
    }

    public String getConsignment() {
        return consignment;
    }

    public void setConsignment(String consignment) {
        this.consignment = consignment;
    }


    public String getCertaintyID() {
        return certaintyID;
    }

    public void setCertaintyID(String certaintyID) {
        this.certaintyID = certaintyID;
    }

    public String getErpOrderId() {
        return erpOrderId;
    }

    public void setErpOrderId(String erpOrderId) {
        this.erpOrderId = erpOrderId;
    }

    public String getInventoryLinkGroupId() {
        return inventoryLinkGroupId;
    }

    public void setInventoryLinkGroupId(String inventoryLinkGroupId) {
        this.inventoryLinkGroupId = inventoryLinkGroupId;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
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

    public String getQualityQuantity() {
        return qualityQuantity;
    }

    public void setQualityQuantity(String qualityQuantity) {
        this.qualityQuantity = qualityQuantity;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getRestrictedQuantity() {
        return restrictedQuantity;
    }

    public void setRestrictedQuantity(String restrictedQuantity) {
        this.restrictedQuantity = restrictedQuantity;
    }

    public String getReturnsQuantity() {
        return returnsQuantity;
    }

    public void setReturnsQuantity(String returnsQuantity) {
        this.returnsQuantity = returnsQuantity;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getTransferQuantity() {
        return transferQuantity;
    }

    public void setTransferQuantity(String transferQuantity) {
        this.transferQuantity = transferQuantity;
    }

    public String getTransitDate() {
        return transitDate;
    }

    public void setTransitDate(String transitDate) {
        this.transitDate = transitDate;
    }

    public String getUnrestrictedQuantity() {
        return unrestrictedQuantity;
    }

    public void setUnrestrictedQuantity(String unrestrictedQuantity) {
        this.unrestrictedQuantity = unrestrictedQuantity;
    }
}

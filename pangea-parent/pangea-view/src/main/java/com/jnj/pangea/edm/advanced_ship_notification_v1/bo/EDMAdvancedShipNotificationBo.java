package com.jnj.pangea.edm.advanced_ship_notification_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * Created by rtierne2 on 2018/5/10.
 */


public class EDMAdvancedShipNotificationBo extends BaseBo {

    private String srcSysCd;
    private String delvDocId;
    private String receivingPtId;
    private String localDeliveryType;
    private String localDeliveryCatg;
    private String localDeliveryDate;
    private String localCreatedDate;
    private String localBillOfLading;
    private String localExternalId;
    private String actGRDt;
    private String vendorId;
    private String localShippingPlant;
    private String delvLineNbr;
    private String matlNum;
    private String localBatchNo;
    private String localVendorBatchNo;
    private String localReceivingPlant;
    private String baseUnitOfMeasureCd;
    private String actlSkuDelvQty;
    private String localRefDocNum;
    private String locRefDocLineNum;

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getDelvDocId() {
        return delvDocId;
    }

    public void setDelvDocId(String delvDocId) {
        this.delvDocId = delvDocId;
    }

    public String getReceivingPtId() {
        return receivingPtId;
    }

    public void setReceivingPtId(String receivingPtId) {
        this.receivingPtId = receivingPtId;
    }

    public String getLocalDeliveryType() {
        return localDeliveryType;
    }

    public void setLocalDeliveryType(String localDeliveryType) {
        this.localDeliveryType = localDeliveryType;
    }

    public String getLocalDeliveryCatg() {
        return localDeliveryCatg;
    }

    public void setLocalDeliveryCatg(String localDeliveryCatg) {
        this.localDeliveryCatg = localDeliveryCatg;
    }

    public String getLocalDeliveryDate() {
        return localDeliveryDate;
    }

    public void setLocalDeliveryDate(String localDeliveryDate) {
        this.localDeliveryDate = localDeliveryDate;
    }

    public String getLocalCreatedDate() {
        return localCreatedDate;
    }

    public void setLocalCreatedDate(String localCreatedDate) {
        this.localCreatedDate = localCreatedDate;
    }

    public String getLocalBillOfLading() {
        return localBillOfLading;
    }

    public void setLocalBillOfLading(String localBillOfLading) {
        this.localBillOfLading = localBillOfLading;
    }

    public String getLocalExternalId() {
        return localExternalId;
    }

    public void setLocalExternalId(String localExternalId) {
        this.localExternalId = localExternalId;
    }

    public String getActGRDt() {
        return actGRDt;
    }

    public void setActGRDt(String actGRDt) {
        this.actGRDt = actGRDt;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getLocalShippingPlant() {
        return localShippingPlant;
    }

    public void setLocalShippingPlant(String localShippingPlant) {
        this.localShippingPlant = localShippingPlant;
    }

    public String getDelvLineNbr() {
        return delvLineNbr;
    }

    public void setDelvLineNbr(String delvLineNbr) {
        this.delvLineNbr = delvLineNbr;
    }

    public String getMatlNum() {
        return matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getLocalBatchNo() {
        return localBatchNo;
    }

    public void setLocalBatchNo(String localBatchNo) {
        this.localBatchNo = localBatchNo;
    }

    public String getLocalVendorBatchNo() {
        return localVendorBatchNo;
    }

    public void setLocalVendorBatchNo(String localVendorBatchNo) {
        this.localVendorBatchNo = localVendorBatchNo;
    }

    public String getLocalReceivingPlant() {
        return localReceivingPlant;
    }

    public void setLocalReceivingPlant(String localReceivingPlant) {
        this.localReceivingPlant = localReceivingPlant;
    }

    public String getBaseUnitOfMeasureCd() {
        return baseUnitOfMeasureCd;
    }

    public void setBaseUnitOfMeasureCd(String baseUnitOfMeasureCd) {
        this.baseUnitOfMeasureCd = baseUnitOfMeasureCd;
    }

    public String getActlSkuDelvQty() {
        return actlSkuDelvQty;
    }

    public void setActlSkuDelvQty(String actlSkuDelvQty) {
        this.actlSkuDelvQty = actlSkuDelvQty;
    }

    public String getLocalRefDocNum() {
        return localRefDocNum;
    }

    public void setLocalRefDocNum(String localRefDocNum) {
        this.localRefDocNum = localRefDocNum;
    }

    public String getLocRefDocLineNum() {
        return locRefDocLineNum;
    }

    public void setLocRefDocLineNum(String locRefDocLineNum) {
        this.locRefDocLineNum = locRefDocLineNum;
    }

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("delvDocId", this.delvDocId)
                .add("delvLineNbr", this.delvLineNbr)
                .toJsonString();
    }
}

package com.jnj.pangea.edm.advanced_ship_notification_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * Created by rtierne2 on 2018/5/10.
 */


public class EDMAdvancedShipNotificationBo extends BaseBo {

    private String srcSysCd;
    private String delvDocID;
    private String receivingPtID;
    private String localdeliveryType;
    private String localdeliveryCatg;
    private String localdeliveryDate;
    private String localcreatedDate;
    private String localbillOfLading;
    private String localExternalId;
    private String actGRDt;
    private String vendorID;
    private String localShippingPlant;
    private String delvLineNbr;
    private String matlNum;
    private String localbatchNo;
    private String localvendorBatchNo;
    private String localReceivingPlant;
    private String baseUnitOfMeasureCd;
    private String actlSkuDelvQty;
    private String localRefDocNum;
    private String locRefDocLineNum;

    public String getSrcSysCd() { return srcSysCd; }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getDelvDocID() {
        return delvDocID;
    }

    public void setDelvDocID(String delvDocID) {
        this.delvDocID = delvDocID;
    }

    public String getReceivingPtID() {
        return receivingPtID;
    }

    public void setReceivingPtID(String receivingPtID) {
        this.receivingPtID = receivingPtID;
    }

    public String getLocaldeliveryType() {
        return localdeliveryType;
    }

    public void setLocaldeliveryType(String localdeliveryType) {
        this.localdeliveryType = localdeliveryType;
    }

    public String getLocaldeliveryCatg() {
        return localdeliveryCatg;
    }

    public void setLocaldeliveryCatg(String localdeliveryCatg) {
        this.localdeliveryCatg = localdeliveryCatg;
    }

    public String getLocaldeliveryDate() {
        return localdeliveryDate;
    }

    public void setLocaldeliveryDate(String localdeliveryDate) {
        this.localdeliveryDate = localdeliveryDate;
    }

    public String getLocalcreatedDate() {
        return localcreatedDate;
    }

    public void setLocalcreatedDate(String localcreatedDate) {
        this.localcreatedDate = localcreatedDate;
    }

    public String getLocalbillOfLading() {
        return localbillOfLading;
    }

    public void setLocalbillOfLading(String localbillOfLading) {
        this.localbillOfLading = localbillOfLading;
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

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
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

    public String getLocalbatchNo() {
        return localbatchNo;
    }

    public void setLocalbatchNo(String localbatchNo) {
        this.localbatchNo = localbatchNo;
    }

    public String getLocalvendorBatchNo() {
        return localvendorBatchNo;
    }

    public void setLocalvendorBatchNo(String localvendorBatchNo) {
        this.localvendorBatchNo = localvendorBatchNo;
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
                .add("delvDocID", this.delvDocID)
                .add("delvLineNbr", this.delvLineNbr)
                .toJsonString();
    }
}

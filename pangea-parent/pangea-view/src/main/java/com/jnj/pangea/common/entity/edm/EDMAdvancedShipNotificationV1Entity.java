package com.jnj.pangea.common.entity.edm;


import java.util.Map;

/**
 * Created by rtierne2 on 2018/5/10.
 */
public class EDMAdvancedShipNotificationV1Entity {

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
    private String localVendorBatchNo;
    private String localReceivingPlant;
    private String baseUnitOfMeasureCd;
    private String actlSkuDelvQty;
    private String localRefDocNum;
    private String locRefDocLineNum;

    public EDMAdvancedShipNotificationV1Entity(Map<String, Object> map) {
        setSrcSysCd((String) map.get("srcSysCd"));
        setDelvDocID((String) map.get("delvDocID"));
        setReceivingPtID((String) map.get("delvDocID"));
        setLocaldeliveryType((String) map.get("delvDocID"));
        setLocaldeliveryCatg((String) map.get("delvDocID"));
        setLocaldeliveryDate((String) map.get("delvDocID"));
        setLocalcreatedDate((String) map.get("delvDocID"));
        setLocalbillOfLading((String) map.get("delvDocID"));
        setLocalExternalId((String) map.get("delvDocID"));
        setActGRDt((String) map.get("delvDocID"));
        setVendorID((String) map.get("delvDocID"));
        setLocalShippingPlant((String) map.get("delvDocID"));
        setLocalbatchNo((String) map.get("delvDocID"));
        setLocalReceivingPlant((String) map.get("delvDocID"));
        setBaseUnitOfMeasureCd((String) map.get("delvDocID"));
        setActlSkuDelvQty((String) map.get("delvDocID"));
        setLocalRefDocNum((String) map.get("delvDocID"));
        setLocRefDocLineNum((String) map.get("delvDocID"));
        setDelvLineNbr((String) map.get("delvDocID"));
        setLocalVendorBatchNo((String) map.get("localVendorBatchNo"));
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getDelvDocID() {
        return this.delvDocID;
    }

    public void setDelvDocID(String delvDocID) { this.delvDocID = delvDocID; }

    public String getReceivingPtID() { return receivingPtID; }

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

    public String getLocalVendorBatchNo() {
        return localVendorBatchNo;
    }

    public void setLocalVendorBatchNo(String localVendorBatchNo) {
        this.localVendorBatchNo = localVendorBatchNo;
    }

}

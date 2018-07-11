package com.jnj.pangea.common.entity.edm;


import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * Created by rtierne2 on 2018/5/10.
 */
public class EDMAdvancedShipNotificationV1Entity extends CommonEntity {

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

    public EDMAdvancedShipNotificationV1Entity(Map<String, Object> map) {
        super(map);
        setSrcSysCd((String) map.get("srcSysCd"));
        setDelvDocId((String) map.get("delvDocId"));
        setReceivingPtId((String) map.get("receivingPtId"));
        setLocalDeliveryType((String) map.get("localDeliveryType"));
        setLocalDeliveryCatg((String) map.get("localDeliveryCatg"));
        setLocalDeliveryDate((String) map.get("localDeliveryDate"));
        setLocalCreatedDate((String) map.get("localCreatedDate"));
        setLocalBillOfLading((String) map.get("localBillOfLading"));
        setLocalExternalId((String) map.get("localExternalId"));
        setActGRDt((String) map.get("actGRDt"));
        setVendorId((String) map.get("vendorId"));
        setLocalShippingPlant((String) map.get("localShippingPlant"));
        setLocalBatchNo((String) map.get("localBatchNo"));
        setLocalReceivingPlant((String) map.get("localReceivingPlant"));
        setBaseUnitOfMeasureCd((String) map.get("baseUnitOfMeasureCd"));
        setActlSkuDelvQty((String) map.get("actlSkuDelvQty"));
        setLocalRefDocNum((String) map.get("localRefDocNum"));
        setLocRefDocLineNum((String) map.get("locRefDocLineNum"));
        setDelvLineNbr((String) map.get("delvLineNbr"));
        setMatlNum((String) map.get("matlNum"));
        setLocalVendorBatchNo((String) map.get("localVendorBatchNo"));
    }

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
}

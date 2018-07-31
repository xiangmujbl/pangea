package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMOutboundDeliveryLineV1Entity extends CommonEntity {

    private String actlSkuDelvQty;
    private String baseUnitOfMeasureCd;
    private String btchNum;
    private String delvDocId;
    private String delvLineNbr;
    private String matlNum;
    private String shippedQty;
    private String shippingPlntCd;
    private String slsOrdrLineNbr;
    private String slsOrdrNum;
    private String srcSysCd;
    private String vendBtchNum;

    public EDMOutboundDeliveryLineV1Entity(Map<String, Object> map) {
        super(map);
        setSrcSysCd((String)map.get("srcSysCd"));
        setDelvDocId((String)map.get("delvDocId"));
        setDelvLineNbr((String)map.get("delvLineNbr"));
        setActlSkuDelvQty((String)map.get("actlSkuDelvQty"));
        setBaseUnitOfMeasureCd((String)map.get("baseUnitOfMeasureCd"));
        setBtchNum((String)map.get("btchNum"));
        setMatlNum((String)map.get("matlNum"));
        setShippedQty((String)map.get("shippedQty"));
        setShippingPlntCd((String)map.get("shippingPlntCd"));
        setSlsOrdrLineNbr((String)map.get("slsOrdrLineNbr"));
        setSlsOrdrNum((String)map.get("slsOrdrNum"));
        setVendBtchNum((String)map.get("vendBtchNum"));
    }

    public String getActlSkuDelvQty() {
        return actlSkuDelvQty;
    }

    public void setActlSkuDelvQty(String actlSkuDelvQty) {
        this.actlSkuDelvQty = actlSkuDelvQty;
    }

    public String getBaseUnitOfMeasureCd() {
        return baseUnitOfMeasureCd;
    }

    public void setBaseUnitOfMeasureCd(String baseUnitOfMeasureCd) {
        this.baseUnitOfMeasureCd = baseUnitOfMeasureCd;
    }

    public String getBtchNum() {
        return btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getDelvDocId() {
        return delvDocId;
    }

    public void setDelvDocId(String delvDocId) {
        this.delvDocId = delvDocId;
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

    public String getShippedQty() {
        return shippedQty;
    }

    public void setShippedQty(String shippedQty) {
        this.shippedQty = shippedQty;
    }

    public String getShippingPlntCd() {
        return shippingPlntCd;
    }

    public void setShippingPlntCd(String shippingPlntCd) {
        this.shippingPlntCd = shippingPlntCd;
    }

    public String getSlsOrdrLineNbr() {
        return slsOrdrLineNbr;
    }

    public void setSlsOrdrLineNbr(String slsOrdrLineNbr) {
        this.slsOrdrLineNbr = slsOrdrLineNbr;
    }

    public String getSlsOrdrNum() {
        return slsOrdrNum;
    }

    public void setSlsOrdrNum(String slsOrdrNum) {
        this.slsOrdrNum = slsOrdrNum;
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getVendBtchNum() {
        return vendBtchNum;
    }

    public void setVendBtchNum(String vendBtchNum) {
        this.vendBtchNum = vendBtchNum;
    }
}

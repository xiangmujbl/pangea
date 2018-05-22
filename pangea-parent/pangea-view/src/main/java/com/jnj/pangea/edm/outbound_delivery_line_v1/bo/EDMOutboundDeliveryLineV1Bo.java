package com.jnj.pangea.edm.outbound_delivery_line_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMOutboundDeliveryLineV1Bo extends BaseBo {

    private String srcSysCd;
    private String delvDocId;
    private String delvLineNbr;
    private String matlNum;
    private String btchNum;
    private String vendBtchNum;
    private String shippingPlntCd;
    private String baseUnitOfMeasureCd;
    private String shippedQty;
    private String actlSkuDelvQty;
    private String slsOrdrNum;
    private String slsOrdrLineNbr;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("delvDocId", this.delvDocId)
                .add("delvLineNbr", this.delvLineNbr)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getDelvDocId() {
        return this.delvDocId;
    }

    public void setDelvDocId(String delvDocId) {
        this.delvDocId = delvDocId;
    }

    public String getDelvLineNbr() {
        return this.delvLineNbr;
    }

    public void setDelvLineNbr(String delvLineNbr) {
        this.delvLineNbr = delvLineNbr;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getBtchNum() {
        return this.btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getVendBtchNum() {
        return this.vendBtchNum;
    }

    public void setVendBtchNum(String vendBtchNum) {
        this.vendBtchNum = vendBtchNum;
    }

    public String getShippingPlntCd() {
        return this.shippingPlntCd;
    }

    public void setShippingPlntCd(String shippingPlntCd) {
        this.shippingPlntCd = shippingPlntCd;
    }

    public String getBaseUnitOfMeasureCd() {
        return this.baseUnitOfMeasureCd;
    }

    public void setBaseUnitOfMeasureCd(String baseUnitOfMeasureCd) {
        this.baseUnitOfMeasureCd = baseUnitOfMeasureCd;
    }

    public String getShippedQty() {
        return this.shippedQty;
    }

    public void setShippedQty(String shippedQty) {
        this.shippedQty = shippedQty;
    }

    public String getActlSkuDelvQty() {
        return this.actlSkuDelvQty;
    }

    public void setActlSkuDelvQty(String actlSkuDelvQty) {
        this.actlSkuDelvQty = actlSkuDelvQty;
    }

    public String getSlsOrdrNum() {
        return this.slsOrdrNum;
    }

    public void setSlsOrdrNum(String slsOrdrNum) {
        this.slsOrdrNum = slsOrdrNum;
    }

    public String getSlsOrdrLineNbr() {
        return this.slsOrdrLineNbr;
    }

    public void setSlsOrdrLineNbr(String slsOrdrLineNbr) {
        this.slsOrdrLineNbr = slsOrdrLineNbr;
    }

}

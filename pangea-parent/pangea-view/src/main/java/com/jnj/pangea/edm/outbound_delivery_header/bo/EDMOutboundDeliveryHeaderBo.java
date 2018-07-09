package com.jnj.pangea.edm.outbound_delivery_header.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMOutboundDeliveryHeaderBo extends BaseBo {

    private String srcSysCd;
    private String delvDocId;
    private String shippingPtNum;
    private String delvTypeCd;
    private String slsOrdrCarCd;
    private String delvDt;
    private String crtDttm;
    private String soldToCustNum;
    private String shipToCustNum;
    private String billOfLdngNum;
    private String delvNum;
    private String planGiDt;
    private String actlGiDt;
    private String shippingCondCd;
    private String supNum;
    private String plntCd;
    private String localSalesOrg;
    private String hdrOvrStat;
    private String hdrGdsMvtStat;
    private String hdrWMStat;
    private String hdrPickStat;
    private String hdrBillStat;



    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd",this.srcSysCd)
                .add("delvDocId",this.delvDocId)
                .toJsonString();
    }

    public String getHdrOvrStat() {
        return hdrOvrStat;
    }

    public void setHdrOvrStat(String hdrOvrStat) {
        this.hdrOvrStat = hdrOvrStat;
    }

    public String getHdrGdsMvtStat() {
        return hdrGdsMvtStat;
    }

    public void setHdrGdsMvtStat(String hdrGdsMvtStat) {
        this.hdrGdsMvtStat = hdrGdsMvtStat;
    }

    public String getHdrWMStat() {
        return hdrWMStat;
    }

    public void setHdrWMStat(String hdrWMStat) {
        this.hdrWMStat = hdrWMStat;
    }

    public String getHdrPickStat() {
        return hdrPickStat;
    }

    public void setHdrPickStat(String hdrPickStat) {
        this.hdrPickStat = hdrPickStat;
    }

    public String getHdrBillStat() {
        return hdrBillStat;
    }

    public void setHdrBillStat(String hdrBillStat) {
        this.hdrBillStat = hdrBillStat;
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

    public String getShippingPtNum() {
        return shippingPtNum;
    }

    public void setShippingPtNum(String shippingPtNum) {
        this.shippingPtNum = shippingPtNum;
    }

    public String getDelvTypeCd() {
        return delvTypeCd;
    }

    public void setDelvTypeCd(String delvTypeCd) {
        this.delvTypeCd = delvTypeCd;
    }

    public String getSlsOrdrCarCd() {
        return slsOrdrCarCd;
    }

    public void setSlsOrdrCarCd(String slsOrdrCarCd) {
        this.slsOrdrCarCd = slsOrdrCarCd;
    }

    public String getDelvDt() {
        return delvDt;
    }

    public void setDelvDt(String delvDt) {
        this.delvDt = delvDt;
    }

    public String getCrtDttm() {
        return crtDttm;
    }

    public void setCrtDttm(String crtDttm) {
        this.crtDttm = crtDttm;
    }

    public String getSoldToCustNum() {
        return soldToCustNum;
    }

    public void setSoldToCustNum(String soldToCustNum) {
        this.soldToCustNum = soldToCustNum;
    }

    public String getShipToCustNum() {
        return shipToCustNum;
    }

    public void setShipToCustNum(String shipToCustNum) {
        this.shipToCustNum = shipToCustNum;
    }

    public String getBillOfLdngNum() {
        return billOfLdngNum;
    }

    public void setBillOfLdngNum(String billOfLdngNum) {
        this.billOfLdngNum = billOfLdngNum;
    }

    public String getDelvNum() {
        return delvNum;
    }

    public void setDelvNum(String delvNum) {
        this.delvNum = delvNum;
    }

    public String getPlanGiDt() {
        return planGiDt;
    }

    public void setPlanGiDt(String planGiDt) {
        this.planGiDt = planGiDt;
    }

    public String getActlGiDt() {
        return actlGiDt;
    }

    public void setActlGiDt(String actlGiDt) {
        this.actlGiDt = actlGiDt;
    }

    public String getShippingCondCd() {
        return shippingCondCd;
    }

    public void setShippingCondCd(String shippingCondCd) {
        this.shippingCondCd = shippingCondCd;
    }

    public String getSupNum() {
        return supNum;
    }

    public void setSupNum(String supNum) {
        this.supNum = supNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getLocalSalesOrg() {
        return localSalesOrg;
    }

    public void setLocalSalesOrg(String localSalesOrg) {
        this.localSalesOrg = localSalesOrg;
    }
}

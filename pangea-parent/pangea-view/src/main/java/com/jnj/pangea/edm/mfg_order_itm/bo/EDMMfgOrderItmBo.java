package com.jnj.pangea.edm.mfg_order_itm.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgOrderItmBo extends BaseBo {

    private String srcSysCd;
    private String mfgOrdrNum;
    private String lnItmNbr;
    private String mfgPlnndOrdrNum;
    private String matlNum;
    private String scrpQty;
    private String itmQty;
    private String rcvdQty;
    private String prdtnUomCd;
    private String plntCd;
    private String fctrNmrtrMeas;
    private String fctrDnmntrMeas;
    private String goodRcptLdDaysQty;
    private String dlvCmpltInd;
    private String prdntVrsnNum;
    private String btchNum;
    private String delInd;

    public String getBaseUomCd() {
        return baseUomCd;
    }

    public void setBaseUomCd(String baseUomCd) {
        this.baseUomCd = baseUomCd;
    }

    private String baseUomCd;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("mfgOrdrNum", this.mfgOrdrNum)
                .add("lnItmNbr", this.lnItmNbr)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getMfgOrdrNum() {
        return this.mfgOrdrNum;
    }

    public void setMfgOrdrNum(String mfgOrdrNum) {
        this.mfgOrdrNum = mfgOrdrNum;
    }

    public String getLnItmNbr() {
        return this.lnItmNbr;
    }

    public void setLnItmNbr(String lnItmNbr) {
        this.lnItmNbr = lnItmNbr;
    }

    public String getMfgPlnndOrdrNum() {
        return this.mfgPlnndOrdrNum;
    }

    public void setMfgPlnndOrdrNum(String mfgPlnndOrdrNum) {
        this.mfgPlnndOrdrNum = mfgPlnndOrdrNum;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getScrpQty() {
        return this.scrpQty;
    }

    public void setScrpQty(String scrpQty) {
        this.scrpQty = scrpQty;
    }

    public String getItmQty() {
        return this.itmQty;
    }

    public void setItmQty(String itmQty) {
        this.itmQty = itmQty;
    }

    public String getRcvdQty() {
        return this.rcvdQty;
    }

    public void setRcvdQty(String rcvdQty) {
        this.rcvdQty = rcvdQty;
    }

    public String getPrdtnUomCd() {
        return this.prdtnUomCd;
    }

    public void setPrdtnUomCd(String prdtnUomCd) {
        this.prdtnUomCd = prdtnUomCd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getFctrNmrtrMeas() {
        return this.fctrNmrtrMeas;
    }

    public void setFctrNmrtrMeas(String fctrNmrtrMeas) {
        this.fctrNmrtrMeas = fctrNmrtrMeas;
    }

    public String getFctrDnmntrMeas() {
        return this.fctrDnmntrMeas;
    }

    public void setFctrDnmntrMeas(String fctrDnmntrMeas) {
        this.fctrDnmntrMeas = fctrDnmntrMeas;
    }

    public String getGoodRcptLdDaysQty() {
        return this.goodRcptLdDaysQty;
    }

    public void setGoodRcptLdDaysQty(String goodRcptLdDaysQty) {
        this.goodRcptLdDaysQty = goodRcptLdDaysQty;
    }

    public String getDlvCmpltInd() {
        return this.dlvCmpltInd;
    }

    public void setDlvCmpltInd(String dlvCmpltInd) {
        this.dlvCmpltInd = dlvCmpltInd;
    }

    public String getPrdntVrsnNum() {
        return this.prdntVrsnNum;
    }

    public void setPrdntVrsnNum(String prdntVrsnNum) {
        this.prdntVrsnNum = prdntVrsnNum;
    }

    public String getBtchNum() {
        return this.btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getDelInd() {
        return this.delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

}

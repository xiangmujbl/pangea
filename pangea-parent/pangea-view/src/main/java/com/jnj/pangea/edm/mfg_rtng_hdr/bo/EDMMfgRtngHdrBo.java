package com.jnj.pangea.edm.mfg_rtng_hdr.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgRtngHdrBo extends BaseBo {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngGrpCntrNbr;
    private String rtngVrsnCntrNbr;
    private String valFromDt;
    private String chgNum;
    private String delInd;
    private String crtDttm;
    private String chgDttm;
    private String plntCd;
    private String rtngUsgCd;
    private String rtngSttsCd;
    private String rtngUomCd;
    private String fromLtSzQty;
    private String toLtSzQty;
    private String rtngPlnnrGrpCd;
    private String rtngDesc;
    private String rtngPrflCd;
    private String rtgVld_ToDt;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", srcSysCd)
                .add("rtngTypCd", rtngTypCd)
                .add("rtngGrpCd", rtngGrpCd)
                .add("rtngGrpCntrNbr", rtngGrpCntrNbr)
                .add("rtngVrsnCntrNbr", rtngVrsnCntrNbr)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getRtngTypCd() {
        return this.rtngTypCd;
    }

    public void setRtngTypCd(String rtngTypCd) {
        this.rtngTypCd = rtngTypCd;
    }

    public String getRtngGrpCd() {
        return this.rtngGrpCd;
    }

    public void setRtngGrpCd(String rtngGrpCd) {
        this.rtngGrpCd = rtngGrpCd;
    }

    public String getRtngGrpCntrNbr() {
        return this.rtngGrpCntrNbr;
    }

    public void setRtngGrpCntrNbr(String rtngGrpCntrNbr) {
        this.rtngGrpCntrNbr = rtngGrpCntrNbr;
    }

    public String getRtngVrsnCntrNbr() {
        return this.rtngVrsnCntrNbr;
    }

    public void setRtngVrsnCntrNbr(String rtngVrsnCntrNbr) {
        this.rtngVrsnCntrNbr = rtngVrsnCntrNbr;
    }

    public String getValFromDt() {
        return this.valFromDt;
    }

    public void setValFromDt(String valFromDt) {
        this.valFromDt = valFromDt;
    }

    public String getChgNum() {
        return this.chgNum;
    }

    public void setChgNum(String chgNum) {
        this.chgNum = chgNum;
    }

    public String getDelInd() {
        return this.delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getCrtDttm() {
        return this.crtDttm;
    }

    public void setCrtDttm(String crtDttm) {
        this.crtDttm = crtDttm;
    }

    public String getChgDttm() {
        return this.chgDttm;
    }

    public void setChgDttm(String chgDttm) {
        this.chgDttm = chgDttm;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getRtngUsgCd() {
        return this.rtngUsgCd;
    }

    public void setRtngUsgCd(String rtngUsgCd) {
        this.rtngUsgCd = rtngUsgCd;
    }

    public String getRtngSttsCd() {
        return this.rtngSttsCd;
    }

    public void setRtngSttsCd(String rtngSttsCd) {
        this.rtngSttsCd = rtngSttsCd;
    }

    public String getRtngUomCd() {
        return this.rtngUomCd;
    }

    public void setRtngUomCd(String rtngUomCd) {
        this.rtngUomCd = rtngUomCd;
    }

    public String getFromLtSzQty() {
        return this.fromLtSzQty;
    }

    public void setFromLtSzQty(String fromLtSzQty) {
        this.fromLtSzQty = fromLtSzQty;
    }

    public String getToLtSzQty() {
        return this.toLtSzQty;
    }

    public void setToLtSzQty(String toLtSzQty) {
        this.toLtSzQty = toLtSzQty;
    }

    public String getRtngPlnnrGrpCd() {
        return this.rtngPlnnrGrpCd;
    }

    public void setRtngPlnnrGrpCd(String rtngPlnnrGrpCd) {
        this.rtngPlnnrGrpCd = rtngPlnnrGrpCd;
    }

    public String getRtngDesc() {
        return this.rtngDesc;
    }

    public void setRtngDesc(String rtngDesc) {
        this.rtngDesc = rtngDesc;
    }

    public String getRtngPrflCd() {
        return this.rtngPrflCd;
    }

    public void setRtngPrflCd(String rtngPrflCd) {
        this.rtngPrflCd = rtngPrflCd;
    }

    public String getRtgVld_ToDt() {
        return this.rtgVld_ToDt;
    }

    public void setRtgVld_ToDt(String rtgVld_ToDt) {
        this.rtgVld_ToDt = rtgVld_ToDt;
    }

}

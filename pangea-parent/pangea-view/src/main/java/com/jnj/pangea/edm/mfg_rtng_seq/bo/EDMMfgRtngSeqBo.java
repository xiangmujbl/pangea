package com.jnj.pangea.edm.mfg_rtng_seq.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgRtngSeqBo extends BaseBo {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngGrpCntrNbr;
    private String rtngSqncNum;
    private String rtngSqncVrsnCntrNbr;
    private String valFromDt;
    private String chgNum;
    private String delInd;
    private String crtDttm;
    private String chgDttm;
    private String seqCategory;
    private String seqDesc;
    private String fromLtSzQty;
    private String toLtSzQty;
    private String seqRelKeyBranch;
    private String seqRelKeyReturn;
    private String seqStartNode;
    private String seqEndNode;
    private String seqValidToDate;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("rtngTypCd",this.rtngTypCd)
                .add("rtngGrpCd",this.rtngGrpCd)
                .add("rtngGrpCntrNbr",this.rtngGrpCntrNbr)
                .add("rtngSqncNum",this.rtngSqncNum)
                .add("rtngSqncVrsnCntrNbr",this.rtngSqncVrsnCntrNbr)
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

    public String getRtngSqncNum() {
        return this.rtngSqncNum;
    }

    public void setRtngSqncNum(String rtngSqncNum) {
        this.rtngSqncNum = rtngSqncNum;
    }

    public String getRtngSqncVrsnCntrNbr() {
        return this.rtngSqncVrsnCntrNbr;
    }

    public void setRtngSqncVrsnCntrNbr(String rtngSqncVrsnCntrNbr) {
        this.rtngSqncVrsnCntrNbr = rtngSqncVrsnCntrNbr;
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

    public String getSeqCategory() {
        return this.seqCategory;
    }

    public void setSeqCategory(String seqCategory) {
        this.seqCategory = seqCategory;
    }

    public String getSeqDesc() {
        return this.seqDesc;
    }

    public void setSeqDesc(String seqDesc) {
        this.seqDesc = seqDesc;
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

    public String getSeqRelKeyBranch() {
        return this.seqRelKeyBranch;
    }

    public void setSeqRelKeyBranch(String seqRelKeyBranch) {
        this.seqRelKeyBranch = seqRelKeyBranch;
    }

    public String getSeqRelKeyReturn() {
        return this.seqRelKeyReturn;
    }

    public void setSeqRelKeyReturn(String seqRelKeyReturn) {
        this.seqRelKeyReturn = seqRelKeyReturn;
    }

    public String getSeqStartNode() {
        return this.seqStartNode;
    }

    public void setSeqStartNode(String seqStartNode) {
        this.seqStartNode = seqStartNode;
    }

    public String getSeqEndNode() {
        return this.seqEndNode;
    }

    public void setSeqEndNode(String seqEndNode) {
        this.seqEndNode = seqEndNode;
    }

    public String getSeqValidToDate() {
        return this.seqValidToDate;
    }

    public void setSeqValidToDate(String seqValidToDate) {
        this.seqValidToDate = seqValidToDate;
    }

}

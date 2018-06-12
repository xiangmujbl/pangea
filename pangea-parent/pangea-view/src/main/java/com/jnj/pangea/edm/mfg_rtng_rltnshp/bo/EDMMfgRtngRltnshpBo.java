package com.jnj.pangea.edm.mfg_rtng_rltnshp.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgRtngRltnshpBo extends BaseBo {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngGrpCntrNbr;
    private String rtngNdeNum;
    private String rtngScndGrpCd;
    private String rtngScndGrpCntrNbr;
    private String rtngScndNdeNum;
    private String rltnshpTypCd;
    private String rltnshpMxIntrvlCd;
    private String rltnshpVrsnCntrNbr;
    private String valFromDt;
    private String chgNum;
    private String rltnshpIntrvlUomCd;
    private String rltnshpIntrvl;
    private String rltsnhpDrtnInd;
    private String fctryClndrCd;
    private String plntCd;
    private String crtDttm;
    private String chgDttm;
    private String rltnshpValidEnd;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("rtngTypCd",rtngTypCd)
                .add("rtngGrpCd",rtngGrpCd)
                .add("rtngGrpCntrNbr",rtngGrpCntrNbr)
                .add("rtngNdeNum",rtngNdeNum)
                .add("rtngScndGrpCd",rtngScndGrpCd)
                .add("rtngScndGrpCntrNbr",rtngScndGrpCntrNbr)
                .add("rtngScndNdeNum",rtngScndNdeNum)
                .add("rltnshpTypCd",rltnshpTypCd)
                .add("rltnshpMxIntrvlCd",rltnshpMxIntrvlCd)
                .add("rltnshpVrsnCntrNbr",rltnshpVrsnCntrNbr)

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

    public String getRtngNdeNum() {
        return this.rtngNdeNum;
    }

    public void setRtngNdeNum(String rtngNdeNum) {
        this.rtngNdeNum = rtngNdeNum;
    }

    public String getRtngScndGrpCd() {
        return this.rtngScndGrpCd;
    }

    public void setRtngScndGrpCd(String rtngScndGrpCd) {
        this.rtngScndGrpCd = rtngScndGrpCd;
    }

    public String getRtngScndGrpCntrNbr() {
        return this.rtngScndGrpCntrNbr;
    }

    public void setRtngScndGrpCntrNbr(String rtngScndGrpCntrNbr) {
        this.rtngScndGrpCntrNbr = rtngScndGrpCntrNbr;
    }

    public String getRtngScndNdeNum() {
        return this.rtngScndNdeNum;
    }

    public void setRtngScndNdeNum(String rtngScndNdeNum) {
        this.rtngScndNdeNum = rtngScndNdeNum;
    }

    public String getRltnshpTypCd() {
        return this.rltnshpTypCd;
    }

    public void setRltnshpTypCd(String rltnshpTypCd) {
        this.rltnshpTypCd = rltnshpTypCd;
    }

    public String getRltnshpMxIntrvlCd() {
        return this.rltnshpMxIntrvlCd;
    }

    public void setRltnshpMxIntrvlCd(String rltnshpMxIntrvlCd) {
        this.rltnshpMxIntrvlCd = rltnshpMxIntrvlCd;
    }

    public String getRltnshpVrsnCntrNbr() {
        return this.rltnshpVrsnCntrNbr;
    }

    public void setRltnshpVrsnCntrNbr(String rltnshpVrsnCntrNbr) {
        this.rltnshpVrsnCntrNbr = rltnshpVrsnCntrNbr;
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

    public String getRltnshpIntrvlUomCd() {
        return this.rltnshpIntrvlUomCd;
    }

    public void setRltnshpIntrvlUomCd(String rltnshpIntrvlUomCd) {
        this.rltnshpIntrvlUomCd = rltnshpIntrvlUomCd;
    }

    public String getRltnshpIntrvl() {
        return this.rltnshpIntrvl;
    }

    public void setRltnshpIntrvl(String rltnshpIntrvl) {
        this.rltnshpIntrvl = rltnshpIntrvl;
    }

    public String getRltsnhpDrtnInd() {
        return this.rltsnhpDrtnInd;
    }

    public void setRltsnhpDrtnInd(String rltsnhpDrtnInd) {
        this.rltsnhpDrtnInd = rltsnhpDrtnInd;
    }

    public String getFctryClndrCd() {
        return this.fctryClndrCd;
    }

    public void setFctryClndrCd(String fctryClndrCd) {
        this.fctryClndrCd = fctryClndrCd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
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

    public String getRltnshpValidEnd() {
        return this.rltnshpValidEnd;
    }

    public void setRltnshpValidEnd(String rltnshpValidEnd) {
        this.rltnshpValidEnd = rltnshpValidEnd;
    }

    @Override
    public String toString() {
        return "EDMMfgRtngRltnshpBo{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", rtngTypCd='" + rtngTypCd + '\'' +
                ", rtngGrpCd='" + rtngGrpCd + '\'' +
                ", rtngGrpCntrNbr='" + rtngGrpCntrNbr + '\'' +
                ", rtngNdeNum='" + rtngNdeNum + '\'' +
                ", rtngScndGrpCd='" + rtngScndGrpCd + '\'' +
                ", rtngScndGrpCntrNbr='" + rtngScndGrpCntrNbr + '\'' +
                ", rtngScndNdeNum='" + rtngScndNdeNum + '\'' +
                ", rltnshpTypCd='" + rltnshpTypCd + '\'' +
                ", rltnshpMxIntrvlCd='" + rltnshpMxIntrvlCd + '\'' +
                ", rltnshpVrsnCntrNbr='" + rltnshpVrsnCntrNbr + '\'' +
                ", valFromDt='" + valFromDt + '\'' +
                ", chgNum='" + chgNum + '\'' +
                ", rltnshpIntrvlUomCd='" + rltnshpIntrvlUomCd + '\'' +
                ", rltnshpIntrvl='" + rltnshpIntrvl + '\'' +
                ", rltsnhpDrtnInd='" + rltsnhpDrtnInd + '\'' +
                ", fctryClndrCd='" + fctryClndrCd + '\'' +
                ", plntCd='" + plntCd + '\'' +
                ", crtDttm='" + crtDttm + '\'' +
                ", chgDttm='" + chgDttm + '\'' +
                ", rltnshpValidEnd='" + rltnshpValidEnd + '\'' +
                '}';
    }
}

package com.jnj.pangea.edm.matl_mfg_rtng.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * E.2.1.1 EDMRouting-MATL_MFG_RTNG - Curation
 * AEAZ-3268
 */
public class MATLMFGRTNGBo extends BaseBo {

    private String srcSysCd;
    private String matlNum;
    private String plntCd;
    private String rtngTypCd;
    private String rntgGrpCd;
    private String rntgGrpCntrNbr;
    private String rntgAddtnlCntrNbr;
    private String matlRtngVrsnCntrNbr;
    private String valFromDt;
    private String chgNum;
    private String delInd;
    private String crtDttm;
    private String chgDttm;
    private String matlRtngValid_To;
    
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("matlNum", this.matlNum)
                .add("plntCd", this.plntCd)
                .add("rtngTypCd", this.rtngTypCd)
                .add("rntgGrpCd", this.rntgGrpCd)
                .add("rntgGrpCntrNbr", this.rntgGrpCntrNbr)
                .add("rntgAddtnlCntrNbr", this.rntgAddtnlCntrNbr)
                .add("matlRtngVrsnCntrNbr", this.matlRtngVrsnCntrNbr)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getMatlNum() {
        return matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getRtngTypCd() {
        return rtngTypCd;
    }

    public void setRtngTypCd(String rtngTypCd) {
        this.rtngTypCd = rtngTypCd;
    }

    public String getRntgGrpCd() {
        return rntgGrpCd;
    }

    public void setRntgGrpCd(String rntgGrpCd) {
        this.rntgGrpCd = rntgGrpCd;
    }

    public String getRntgGrpCntrNbr() {
        return rntgGrpCntrNbr;
    }

    public void setRntgGrpCntrNbr(String rntgGrpCntrNbr) {
        this.rntgGrpCntrNbr = rntgGrpCntrNbr;
    }

    public String getRntgAddtnlCntrNbr() {
        return rntgAddtnlCntrNbr;
    }

    public void setRntgAddtnlCntrNbr(String rntgAddtnlCntrNbr) {
        this.rntgAddtnlCntrNbr = rntgAddtnlCntrNbr;
    }

    public String getMatlRtngVrsnCntrNbr() {
        return matlRtngVrsnCntrNbr;
    }

    public void setMatlRtngVrsnCntrNbr(String matlRtngVrsnCntrNbr) {
        this.matlRtngVrsnCntrNbr = matlRtngVrsnCntrNbr;
    }

    public String getValFromDt() {
        return valFromDt;
    }

    public void setValFromDt(String valFromDt) {
        this.valFromDt = valFromDt;
    }

    public String getChgNum() {
        return chgNum;
    }

    public void setChgNum(String chgNum) {
        this.chgNum = chgNum;
    }

    public String getDelInd() {
        return delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getCrtDttm() {
        return crtDttm;
    }

    public void setCrtDttm(String crtDttm) {
        this.crtDttm = crtDttm;
    }

    public String getChgDttm() {
        return chgDttm;
    }

    public void setChgDttm(String chgDttm) {
        this.chgDttm = chgDttm;
    }

    public String getMatlRtngValid_To() {
        return matlRtngValid_To;
    }

    public void setMatlRtngValid_To(String matlRtngValid_To) {
        this.matlRtngValid_To = matlRtngValid_To;
    }

    @Override
    public String toString() {
        return "MATL_MFG_RTNGBo{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", matlNum='" + matlNum + '\'' +
                ", plntCd='" + plntCd + '\'' +
                ", rtngTypCd='" + rtngTypCd + '\'' +
                ", rntgGrpCd='" + rntgGrpCd + '\'' +
                ", rntgGrpCntrNbr='" + rntgGrpCntrNbr + '\'' +
                ", rntgAddtnlCntrNbr='" + rntgAddtnlCntrNbr + '\'' +
                ", matlRtngVrsnCntrNbr='" + matlRtngVrsnCntrNbr + '\'' +
                ", valFromDt='" + valFromDt + '\'' +
                ", chgNum='" + chgNum + '\'' +
                ", delInd='" + delInd + '\'' +
                ", crtDttm='" + crtDttm + '\'' +
                ", chgDttm='" + chgDttm + '\'' +
                ", matlRtngValid_To='" + matlRtngValid_To + '\'' +
                '}';
    }
}

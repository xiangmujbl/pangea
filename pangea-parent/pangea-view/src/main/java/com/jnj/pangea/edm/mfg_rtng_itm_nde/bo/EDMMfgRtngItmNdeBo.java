package com.jnj.pangea.edm.mfg_rtng_itm_nde.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgRtngItmNdeBo extends BaseBo {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngGrpCntrNbr;
    private String rtngSqncNum;
    private String rtngNdeNum;
    private String rtngNdeVrsnCntrNbr;
    private String ValidFromDate;
    private String chgNum;
    private String delInd;
    private String CreateDttm;
    private String chgDttm;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", srcSysCd)
                .add("rtngTypCd", rtngTypCd)
                .add("rtngGrpCd", rtngGrpCd)
                .add("rtngGrpCntrNbr", rtngGrpCntrNbr)
                .add("rtngSqncNum", rtngSqncNum)
                .add("rtngNdeNum", rtngNdeNum)
                .add("rtngNdeVrsnCntrNbr", rtngNdeVrsnCntrNbr)
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

    public String getRtngNdeNum() {
        return this.rtngNdeNum;
    }

    public void setRtngNdeNum(String rtngNdeNum) {
        this.rtngNdeNum = rtngNdeNum;
    }

    public String getRtngNdeVrsnCntrNbr() {
        return this.rtngNdeVrsnCntrNbr;
    }

    public void setRtngNdeVrsnCntrNbr(String rtngNdeVrsnCntrNbr) {
        this.rtngNdeVrsnCntrNbr = rtngNdeVrsnCntrNbr;
    }

    public String getValidFromDate() {
        return this.ValidFromDate;
    }

    public void setValidFromDate(String validFromDate) {
        this.ValidFromDate = validFromDate;
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

    public String getCreateDttm() {
        return this.CreateDttm;
    }

    public void setCreateDttm(String createDttm) {
        this.CreateDttm = createDttm;
    }

    public String getChgDttm() {
        return this.chgDttm;
    }

    public void setChgDttm(String chgDttm) {
        this.chgDttm = chgDttm;
    }

}

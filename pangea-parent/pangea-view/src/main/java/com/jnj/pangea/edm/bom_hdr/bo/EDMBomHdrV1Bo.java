package com.jnj.pangea.edm.bom_hdr.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMBomHdrV1Bo extends BaseBo {
    private String srcSysCd;
    private String bomCatCd;
    private String bomNum;
    private String altBomNum;
    private String bomCntrNbr;
    private String bomVldFromDt;
    private String chgNum;
    private String delInd;
    private String prvCntrNbr;
    private String crtDttm;
    private String chgDttm;
    private String bomUomCd;
    private String bomBaseQty;
    private String bomTxt;
    private String bomStsCd;
    private String bomVld_ToDt;
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("bomCatCd", this.bomCatCd)
                .add("bomNum", this.bomNum)
                .add("altBomNum", this.altBomNum)
                .add("bomCntrNbr", this.bomCntrNbr)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getBomCatCd() {
        return bomCatCd;
    }

    public void setBomCatCd(String bomCatCd) {
        this.bomCatCd = bomCatCd;
    }

    public String getBomNum() {
        return bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getAltBomNum() {
        return altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getBomCntrNbr() {
        return bomCntrNbr;
    }

    public void setBomCntrNbr(String bomCntrNbr) {
        this.bomCntrNbr = bomCntrNbr;
    }

    public String getBomVldFromDt() {
        return bomVldFromDt;
    }

    public void setBomVldFromDt(String bomVldFromDt) {
        this.bomVldFromDt = bomVldFromDt;
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

    public String getPrvCntrNbr() {
        return prvCntrNbr;
    }

    public void setPrvCntrNbr(String prvCntrNbr) {
        this.prvCntrNbr = prvCntrNbr;
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

    public String getBomUomCd() {
        return bomUomCd;
    }

    public void setBomUomCd(String bomUomCd) {
        this.bomUomCd = bomUomCd;
    }

    public String getBomBaseQty() {
        return bomBaseQty;
    }

    public void setBomBaseQty(String bomBaseQty) {
        this.bomBaseQty = bomBaseQty;
    }

    public String getBomTxt() {
        return bomTxt;
    }

    public void setBomTxt(String bomTxt) {
        this.bomTxt = bomTxt;
    }

    public String getBomStsCd() {
        return bomStsCd;
    }

    public void setBomStsCd(String bomStsCd) {
        this.bomStsCd = bomStsCd;
    }

    public String getBomVld_ToDt() {
        return bomVld_ToDt;
    }

    public void setBomVld_ToDt(String bomVld_ToDt) {
        this.bomVld_ToDt = bomVld_ToDt;
    }
}

package com.jnj.pangea.edm.bom_item_node.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMBomItemNodeBo extends BaseBo {

    private String srcSysCd;
    private String bomCatCd;
    private String bomNum;
    private String altBomNum;
    private String bomItmNdeNum;
    private String bomItmNdeCntrNbr;
    private String bomItmNdeVldFromDt;
    private String chgNum;
    private String delInd;
    private String crtDttm;
    private String chgDttm;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("bomCatCd",this.bomCatCd)
                .add("bomNum",this.bomNum)
                .add("altBomNum",this.altBomNum)
                .add("bomItmNdeNum",this.bomItmNdeNum)
                .add("bomItmNdeCntrNbr",this.bomItmNdeCntrNbr)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getBomCatCd() {
        return this.bomCatCd;
    }

    public void setBomCatCd(String bomCatCd) {
        this.bomCatCd = bomCatCd;
    }

    public String getBomNum() {
        return this.bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getAltBomNum() {
        return this.altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getBomItmNdeNum() {
        return this.bomItmNdeNum;
    }

    public void setBomItmNdeNum(String bomItmNdeNum) {
        this.bomItmNdeNum = bomItmNdeNum;
    }

    public String getBomItmNdeCntrNbr() {
        return this.bomItmNdeCntrNbr;
    }

    public void setBomItmNdeCntrNbr(String bomItmNdeCntrNbr) {
        this.bomItmNdeCntrNbr = bomItmNdeCntrNbr;
    }

    public String getBomItmNdeVldFromDt() {
        return this.bomItmNdeVldFromDt;
    }

    public void setBomItmNdeVldFromDt(String bomItmNdeVldFromDt) {
        this.bomItmNdeVldFromDt = bomItmNdeVldFromDt;
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

}

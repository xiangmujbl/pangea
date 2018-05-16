package com.jnj.pangea.edm.bom_item.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * E.2.1.4 EDMBOM-BOM_ITEM - curation
 * AEAZ-3271
 */
public class BOMITEMBo extends BaseBo {

    private String  srcSysCd ;
    private String bomCatCd ;
    private String bomNum ;
    private String bomItmNdeNum ;
    private String bomItmCntrNbr ;
    private String bomItmVldFromDt ;
    private String bomChgNum ;
    private String bomDelInd ;
    private String bomPreItmNdeNum ;
    private String bomPreItmCntrNbr ;
    private String crtDttm ;
    private String chgDttm ;
    private String cmpntNum ;
    private String bomItmCatCd ;
    private String bomItmNum ;
    private String cmpntUomCd ;
    private String cmpntQty ;
    private String fxQtyInd ;
    private String cmpntScrapPct ;
    private String bomItmBulkInd ;
    private String leadTimeOffst ;
    private String dstrbtnKeyCd ;
    private String bomItmTxt ;
    private String coProdInd ;
    private String bomItmVldToDt ;
    private String altItmGrpCd ;
    private String altItmPct ;
    private String altItmInd ;
    private String altItmStratCd ;
    private String altItmRankNbr ;
    
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("bomCatCd", this.bomCatCd)
                .add("bomNum", this.bomNum)
                .add("bomItmNdeNum", this.bomItmNdeNum)
                .add("bomItmCntrNbr", this.bomItmCntrNbr)
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

    public String getBomItmNdeNum() {
        return bomItmNdeNum;
    }

    public void setBomItmNdeNum(String bomItmNdeNum) {
        this.bomItmNdeNum = bomItmNdeNum;
    }

    public String getBomItmCntrNbr() {
        return bomItmCntrNbr;
    }

    public void setBomItmCntrNbr(String bomItmCntrNbr) {
        this.bomItmCntrNbr = bomItmCntrNbr;
    }

    public String getBomItmVldFromDt() {
        return bomItmVldFromDt;
    }

    public void setBomItmVldFromDt(String bomItmVldFromDt) {
        this.bomItmVldFromDt = bomItmVldFromDt;
    }

    public String getBomChgNum() {
        return bomChgNum;
    }

    public void setBomChgNum(String bomChgNum) {
        this.bomChgNum = bomChgNum;
    }

    public String getBomDelInd() {
        return bomDelInd;
    }

    public void setBomDelInd(String bomDelInd) {
        this.bomDelInd = bomDelInd;
    }

    public String getBomPreItmNdeNum() {
        return bomPreItmNdeNum;
    }

    public void setBomPreItmNdeNum(String bomPreItmNdeNum) {
        this.bomPreItmNdeNum = bomPreItmNdeNum;
    }

    public String getBomPreItmCntrNbr() {
        return bomPreItmCntrNbr;
    }

    public void setBomPreItmCntrNbr(String bomPreItmCntrNbr) {
        this.bomPreItmCntrNbr = bomPreItmCntrNbr;
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

    public String getCmpntNum() {
        return cmpntNum;
    }

    public void setCmpntNum(String cmpntNum) {
        this.cmpntNum = cmpntNum;
    }

    public String getBomItmCatCd() {
        return bomItmCatCd;
    }

    public void setBomItmCatCd(String bomItmCatCd) {
        this.bomItmCatCd = bomItmCatCd;
    }

    public String getBomItmNum() {
        return bomItmNum;
    }

    public void setBomItmNum(String bomItmNum) {
        this.bomItmNum = bomItmNum;
    }

    public String getCmpntUomCd() {
        return cmpntUomCd;
    }

    public void setCmpntUomCd(String cmpntUomCd) {
        this.cmpntUomCd = cmpntUomCd;
    }

    public String getCmpntQty() {
        return cmpntQty;
    }

    public void setCmpntQty(String cmpntQty) {
        this.cmpntQty = cmpntQty;
    }

    public String getFxQtyInd() {
        return fxQtyInd;
    }

    public void setFxQtyInd(String fxQtyInd) {
        this.fxQtyInd = fxQtyInd;
    }

    public String getCmpntScrapPct() {
        return cmpntScrapPct;
    }

    public void setCmpntScrapPct(String cmpntScrapPct) {
        this.cmpntScrapPct = cmpntScrapPct;
    }

    public String getBomItmBulkInd() {
        return bomItmBulkInd;
    }

    public void setBomItmBulkInd(String bomItmBulkInd) {
        this.bomItmBulkInd = bomItmBulkInd;
    }

    public String getLeadTimeOffst() {
        return leadTimeOffst;
    }

    public void setLeadTimeOffst(String leadTimeOffst) {
        this.leadTimeOffst = leadTimeOffst;
    }

    public String getDstrbtnKeyCd() {
        return dstrbtnKeyCd;
    }

    public void setDstrbtnKeyCd(String dstrbtnKeyCd) {
        this.dstrbtnKeyCd = dstrbtnKeyCd;
    }

    public String getBomItmTxt() {
        return bomItmTxt;
    }

    public void setBomItmTxt(String bomItmTxt) {
        this.bomItmTxt = bomItmTxt;
    }

    public String getCoProdInd() {
        return coProdInd;
    }

    public void setCoProdInd(String coProdInd) {
        this.coProdInd = coProdInd;
    }

    public String getBomItmVldToDt() {
        return bomItmVldToDt;
    }

    public void setBomItmVldToDt(String bomItmVldToDt) {
        this.bomItmVldToDt = bomItmVldToDt;
    }

    public String getAltItmGrpCd() {
        return altItmGrpCd;
    }

    public void setAltItmGrpCd(String altItmGrpCd) {
        this.altItmGrpCd = altItmGrpCd;
    }

    public String getAltItmPct() {
        return altItmPct;
    }

    public void setAltItmPct(String altItmPct) {
        this.altItmPct = altItmPct;
    }

    public String getAltItmInd() {
        return altItmInd;
    }

    public void setAltItmInd(String altItmInd) {
        this.altItmInd = altItmInd;
    }

    public String getAltItmStratCd() {
        return altItmStratCd;
    }

    public void setAltItmStratCd(String altItmStratCd) {
        this.altItmStratCd = altItmStratCd;
    }

    public String getAltItmRankNbr() {
        return altItmRankNbr;
    }

    public void setAltItmRankNbr(String altItmRankNbr) {
        this.altItmRankNbr = altItmRankNbr;
    }

    @Override
    public String toString() {
        return "BOMITEMBo{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", bomCatCd='" + bomCatCd + '\'' +
                ", bomNum='" + bomNum + '\'' +
                ", bomItmNdeNum='" + bomItmNdeNum + '\'' +
                ", bomItmCntrNbr='" + bomItmCntrNbr + '\'' +
                ", bomItmVldFromDt='" + bomItmVldFromDt + '\'' +
                ", bomChgNum='" + bomChgNum + '\'' +
                ", bomDelInd='" + bomDelInd + '\'' +
                ", bomPreItmNdeNum='" + bomPreItmNdeNum + '\'' +
                ", bomPreItmCntrNbr='" + bomPreItmCntrNbr + '\'' +
                ", crtDttm='" + crtDttm + '\'' +
                ", chgDttm='" + chgDttm + '\'' +
                ", cmpntNum='" + cmpntNum + '\'' +
                ", bomItmCatCd='" + bomItmCatCd + '\'' +
                ", bomItmNum='" + bomItmNum + '\'' +
                ", cmpntUomCd='" + cmpntUomCd + '\'' +
                ", cmpntQty='" + cmpntQty + '\'' +
                ", fxQtyInd='" + fxQtyInd + '\'' +
                ", cmpntScrapPct='" + cmpntScrapPct + '\'' +
                ", bomItmBulkInd='" + bomItmBulkInd + '\'' +
                ", leadTimeOffst='" + leadTimeOffst + '\'' +
                ", dstrbtnKeyCd='" + dstrbtnKeyCd + '\'' +
                ", bomItmTxt='" + bomItmTxt + '\'' +
                ", coProdInd='" + coProdInd + '\'' +
                ", bomItmVldToDt='" + bomItmVldToDt + '\'' +
                ", altItmGrpCd='" + altItmGrpCd + '\'' +
                ", altItmPct='" + altItmPct + '\'' +
                ", altItmInd='" + altItmInd + '\'' +
                ", altItmStratCd='" + altItmStratCd + '\'' +
                ", altItmRankNbr='" + altItmRankNbr + '\'' +
                '}';
    }
}

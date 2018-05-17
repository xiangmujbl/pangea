package com.jnj.pangea.edm.mfg_rtng_itm.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgRtngItmBo extends BaseBo {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngItmNum;
    private String rtngItmVersnCntrNbr;
    private String valFromDt;
    private String chgNum;
    private String delInd;
    private String crtDttm;
    private String chgDttm;
    private String rtngSupNdeNum;
    private String operNum;
    private String operCd;
    private String wrkCntrCd;
    private String plntCd;
    private String operDesc;
    private String operUom;
    private String bsQty;
    private String act1Cd;
    private String act1UomCd;
    private String act1Qty;
    private String act2Cd;
    private String act2UomCd;
    private String act2Qty;
    private String act3Cd;
    private String act3UomCd;
    private String act3Qty;
    private String act4Cd;
    private String act4UomCd;
    private String act4Qty;
    private String act5Cd;
    private String act5UomCd;
    private String act5Qty;
    private String act6Cd;
    private String act6UomCd;
    private String act6Qty;
    private String operDurtnQty;
    private String operDurtnUomCd;
    private String minOperDurtnQty;
    private String minOperDurtnUomCd;
    private String phsInd;
    private String rtgItemEndDate;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", srcSysCd)
                .add("rtngTypCd",rtngTypCd)
                .add("rtngGrpCd",rtngGrpCd)
                .add("rtngItmNum",rtngItmNum)
                .add("rtngItmVersnCntrNbr",rtngItmVersnCntrNbr)
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

    public String getRtngItmNum() {
        return this.rtngItmNum;
    }

    public void setRtngItmNum(String rtngItmNum) {
        this.rtngItmNum = rtngItmNum;
    }

    public String getRtngItmVersnCntrNbr() {
        return this.rtngItmVersnCntrNbr;
    }

    public void setRtngItmVersnCntrNbr(String rtngItmVersnCntrNbr) {
        this.rtngItmVersnCntrNbr = rtngItmVersnCntrNbr;
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

    public String getRtngSupNdeNum() {
        return this.rtngSupNdeNum;
    }

    public void setRtngSupNdeNum(String rtngSupNdeNum) {
        this.rtngSupNdeNum = rtngSupNdeNum;
    }

    public String getOperNum() {
        return this.operNum;
    }

    public void setOperNum(String operNum) {
        this.operNum = operNum;
    }

    public String getOperCd() {
        return this.operCd;
    }

    public void setOperCd(String operCd) {
        this.operCd = operCd;
    }

    public String getWrkCntrCd() {
        return this.wrkCntrCd;
    }

    public void setWrkCntrCd(String wrkCntrCd) {
        this.wrkCntrCd = wrkCntrCd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getOperDesc() {
        return this.operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc;
    }

    public String getOperUom() {
        return this.operUom;
    }

    public void setOperUom(String operUom) {
        this.operUom = operUom;
    }

    public String getBsQty() {
        return this.bsQty;
    }

    public void setBsQty(String bsQty) {
        this.bsQty = bsQty;
    }

    public String getAct1Cd() {
        return this.act1Cd;
    }

    public void setAct1Cd(String act1Cd) {
        this.act1Cd = act1Cd;
    }

    public String getAct1UomCd() {
        return this.act1UomCd;
    }

    public void setAct1UomCd(String act1UomCd) {
        this.act1UomCd = act1UomCd;
    }

    public String getAct1Qty() {
        return this.act1Qty;
    }

    public void setAct1Qty(String act1Qty) {
        this.act1Qty = act1Qty;
    }

    public String getAct2Cd() {
        return this.act2Cd;
    }

    public void setAct2Cd(String act2Cd) {
        this.act2Cd = act2Cd;
    }

    public String getAct2UomCd() {
        return this.act2UomCd;
    }

    public void setAct2UomCd(String act2UomCd) {
        this.act2UomCd = act2UomCd;
    }

    public String getAct2Qty() {
        return this.act2Qty;
    }

    public void setAct2Qty(String act2Qty) {
        this.act2Qty = act2Qty;
    }

    public String getAct3Cd() {
        return this.act3Cd;
    }

    public void setAct3Cd(String act3Cd) {
        this.act3Cd = act3Cd;
    }

    public String getAct3UomCd() {
        return this.act3UomCd;
    }

    public void setAct3UomCd(String act3UomCd) {
        this.act3UomCd = act3UomCd;
    }

    public String getAct3Qty() {
        return this.act3Qty;
    }

    public void setAct3Qty(String act3Qty) {
        this.act3Qty = act3Qty;
    }

    public String getAct4Cd() {
        return this.act4Cd;
    }

    public void setAct4Cd(String act4Cd) {
        this.act4Cd = act4Cd;
    }

    public String getAct4UomCd() {
        return this.act4UomCd;
    }

    public void setAct4UomCd(String act4UomCd) {
        this.act4UomCd = act4UomCd;
    }

    public String getAct4Qty() {
        return this.act4Qty;
    }

    public void setAct4Qty(String act4Qty) {
        this.act4Qty = act4Qty;
    }

    public String getAct5Cd() {
        return this.act5Cd;
    }

    public void setAct5Cd(String act5Cd) {
        this.act5Cd = act5Cd;
    }

    public String getAct5UomCd() {
        return this.act5UomCd;
    }

    public void setAct5UomCd(String act5UomCd) {
        this.act5UomCd = act5UomCd;
    }

    public String getAct5Qty() {
        return this.act5Qty;
    }

    public void setAct5Qty(String act5Qty) {
        this.act5Qty = act5Qty;
    }

    public String getAct6Cd() {
        return this.act6Cd;
    }

    public void setAct6Cd(String act6Cd) {
        this.act6Cd = act6Cd;
    }

    public String getAct6UomCd() {
        return this.act6UomCd;
    }

    public void setAct6UomCd(String act6UomCd) {
        this.act6UomCd = act6UomCd;
    }

    public String getAct6Qty() {
        return this.act6Qty;
    }

    public void setAct6Qty(String act6Qty) {
        this.act6Qty = act6Qty;
    }

    public String getOperDurtnQty() {
        return this.operDurtnQty;
    }

    public void setOperDurtnQty(String operDurtnQty) {
        this.operDurtnQty = operDurtnQty;
    }

    public String getOperDurtnUomCd() {
        return this.operDurtnUomCd;
    }

    public void setOperDurtnUomCd(String operDurtnUomCd) {
        this.operDurtnUomCd = operDurtnUomCd;
    }

    public String getMinOperDurtnQty() {
        return this.minOperDurtnQty;
    }

    public void setMinOperDurtnQty(String minOperDurtnQty) {
        this.minOperDurtnQty = minOperDurtnQty;
    }

    public String getMinOperDurtnUomCd() {
        return this.minOperDurtnUomCd;
    }

    public void setMinOperDurtnUomCd(String minOperDurtnUomCd) {
        this.minOperDurtnUomCd = minOperDurtnUomCd;
    }

    public String getPhsInd() {
        return phsInd;
    }

    public void setPhsInd(String phsInd) {
        this.phsInd = phsInd;
    }

    public String getRtgItemEndDate() {
        return this.rtgItemEndDate;
    }

    public void setRtgItemEndDate(String rtgItemEndDate) {
        this.rtgItemEndDate = rtgItemEndDate;
    }

    @Override
    public String toString() {
        return "EDMMfgRtngItmBo{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", rtngTypCd='" + rtngTypCd + '\'' +
                ", rtngGrpCd='" + rtngGrpCd + '\'' +
                ", rtngItmNum='" + rtngItmNum + '\'' +
                ", rtngItmVersnCntrNbr='" + rtngItmVersnCntrNbr + '\'' +
                ", valFromDt='" + valFromDt + '\'' +
                ", chgNum='" + chgNum + '\'' +
                ", delInd='" + delInd + '\'' +
                ", crtDttm='" + crtDttm + '\'' +
                ", chgDttm='" + chgDttm + '\'' +
                ", rtngSupNdeNum='" + rtngSupNdeNum + '\'' +
                ", operNum='" + operNum + '\'' +
                ", operCd='" + operCd + '\'' +
                ", wrkCntrCd='" + wrkCntrCd + '\'' +
                ", plntCd='" + plntCd + '\'' +
                ", operDesc='" + operDesc + '\'' +
                ", operUom='" + operUom + '\'' +
                ", bsQty='" + bsQty + '\'' +
                ", act1Cd='" + act1Cd + '\'' +
                ", act1UomCd='" + act1UomCd + '\'' +
                ", act1Qty='" + act1Qty + '\'' +
                ", act2Cd='" + act2Cd + '\'' +
                ", act2UomCd='" + act2UomCd + '\'' +
                ", act2Qty='" + act2Qty + '\'' +
                ", act3Cd='" + act3Cd + '\'' +
                ", act3UomCd='" + act3UomCd + '\'' +
                ", act3Qty='" + act3Qty + '\'' +
                ", act4Cd='" + act4Cd + '\'' +
                ", act4UomCd='" + act4UomCd + '\'' +
                ", act4Qty='" + act4Qty + '\'' +
                ", act5Cd='" + act5Cd + '\'' +
                ", act5UomCd='" + act5UomCd + '\'' +
                ", act5Qty='" + act5Qty + '\'' +
                ", act6Cd='" + act6Cd + '\'' +
                ", act6UomCd='" + act6UomCd + '\'' +
                ", act6Qty='" + act6Qty + '\'' +
                ", operDurtnQty='" + operDurtnQty + '\'' +
                ", operDurtnUomCd='" + operDurtnUomCd + '\'' +
                ", minOperDurtnQty='" + minOperDurtnQty + '\'' +
                ", minOperDurtnUomCd='" + minOperDurtnUomCd + '\'' +
                ", phsInd='" + phsInd + '\'' +
                ", rtgItemEndDate='" + rtgItemEndDate + '\'' +
                '}';
    }
}

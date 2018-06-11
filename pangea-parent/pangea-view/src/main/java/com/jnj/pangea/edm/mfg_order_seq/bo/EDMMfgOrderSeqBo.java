package com.jnj.pangea.edm.mfg_order_seq.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgOrderSeqBo extends BaseBo {

    private String srcSysCd;
    private String ordrRtngNum;
    private String ordrRtngCtrNum;
    private String rtngTypCd;
    private String rntgGrpCd;
    private String rntgGrpCntrNbr;
    private String rtngSqncNum;
    private String rtngSqncVrsnCntrNbr;
    private String chgNum;
    private String rtngSqncCtgryCd;
    private String strtNdeNum;
    private String endNdeNum;
    private String fromLtSzQty;
    private String toLtSzQty;
    private String brnchOperNum;
    private String retrnOperNum;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd",srcSysCd)
                .add("ordrRtngNum",ordrRtngNum)
                .add("ordrRtngCtrNum",ordrRtngCtrNum)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getOrdrRtngNum() {
        return this.ordrRtngNum;
    }

    public void setOrdrRtngNum(String ordrRtngNum) {
        this.ordrRtngNum = ordrRtngNum;
    }

    public String getOrdrRtngCtrNum() {
        return this.ordrRtngCtrNum;
    }

    public void setOrdrRtngCtrNum(String ordrRtngCtrNum) {
        this.ordrRtngCtrNum = ordrRtngCtrNum;
    }

    public String getRtngTypCd() {
        return this.rtngTypCd;
    }

    public void setRtngTypCd(String rtngTypCd) {
        this.rtngTypCd = rtngTypCd;
    }

    public String getRntgGrpCd() {
        return this.rntgGrpCd;
    }

    public void setRntgGrpCd(String rntgGrpCd) {
        this.rntgGrpCd = rntgGrpCd;
    }

    public String getRntgGrpCntrNbr() {
        return this.rntgGrpCntrNbr;
    }

    public void setRntgGrpCntrNbr(String rntgGrpCntrNbr) {
        this.rntgGrpCntrNbr = rntgGrpCntrNbr;
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

    public String getChgNum() {
        return this.chgNum;
    }

    public void setChgNum(String chgNum) {
        this.chgNum = chgNum;
    }

    public String getRtngSqncCtgryCd() {
        return this.rtngSqncCtgryCd;
    }

    public void setRtngSqncCtgryCd(String rtngSqncCtgryCd) {
        this.rtngSqncCtgryCd = rtngSqncCtgryCd;
    }

    public String getStrtNdeNum() {
        return this.strtNdeNum;
    }

    public void setStrtNdeNum(String strtNdeNum) {
        this.strtNdeNum = strtNdeNum;
    }

    public String getEndNdeNum() {
        return this.endNdeNum;
    }

    public void setEndNdeNum(String endNdeNum) {
        this.endNdeNum = endNdeNum;
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

    public String getBrnchOperNum() {
        return this.brnchOperNum;
    }

    public void setBrnchOperNum(String brnchOperNum) {
        this.brnchOperNum = brnchOperNum;
    }

    public String getRetrnOperNum() {
        return this.retrnOperNum;
    }

    public void setRetrnOperNum(String retrnOperNum) {
        this.retrnOperNum = retrnOperNum;
    }

}

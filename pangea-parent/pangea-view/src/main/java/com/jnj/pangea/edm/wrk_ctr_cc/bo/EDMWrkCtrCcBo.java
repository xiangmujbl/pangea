package com.jnj.pangea.edm.wrk_ctr_cc.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMWrkCtrCcBo extends BaseBo {

    private String srcSysCd;
    private String wrkCtrTypeCd;
    private String wrkCtrNum;
    private String actvTypeSetCd;
    private String vldToDt;
    private String actvTypeNbr;
    private String vldFromDt;
    private String chgDttm;
    private String cntlAreaCd;
    private String ccCd;
    private String actvCd;


    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("wrkCtrTypeCd", this.wrkCtrTypeCd)
                .add("wrkCtrNum", this.wrkCtrNum)
                .add("actvTypeSetCd", this.actvTypeSetCd)
                .add("vldToDt", this.vldToDt)
                .add("actvTypeNbr", this.actvTypeNbr)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getWrkCtrTypeCd() {
        return this.wrkCtrTypeCd;
    }

    public void setWrkCtrTypeCd(String wrkCtrTypeCd) {
        this.wrkCtrTypeCd = wrkCtrTypeCd;
    }

    public String getWrkCtrNum() {
        return this.wrkCtrNum;
    }

    public void setWrkCtrNum(String wrkCtrNum) {
        this.wrkCtrNum = wrkCtrNum;
    }

    public String getActvTypeSetCd() {
        return this.actvTypeSetCd;
    }

    public void setActvTypeSetCd(String actvTypeSetCd) {
        this.actvTypeSetCd = actvTypeSetCd;
    }

    public String getVldToDt() {
        return this.vldToDt;
    }

    public void setVldToDt(String vldToDt) {
        this.vldToDt = vldToDt;
    }

    public String getActvTypeNbr() {
        return this.actvTypeNbr;
    }

    public void setActvTypeNbr(String actvTypeNbr) {
        this.actvTypeNbr = actvTypeNbr;
    }

    public String getVldFromDt() {
        return this.vldFromDt;
    }

    public void setVldFromDt(String vldFromDt) {
        this.vldFromDt = vldFromDt;
    }

    public String getChgDttm() {
        return this.chgDttm;
    }

    public void setChgDttm(String chgDttm) {
        this.chgDttm = chgDttm;
    }

    public String getCntlAreaCd() {
        return this.cntlAreaCd;
    }

    public void setCntlAreaCd(String cntlAreaCd) {
        this.cntlAreaCd = cntlAreaCd;
    }

    public String getCcCd() {
        return this.ccCd;
    }

    public void setCcCd(String ccCd) {
        this.ccCd = ccCd;
    }

    public String getActvCd() {
        return this.actvCd;
    }

    public void setActvCd(String actvCd) {
        this.actvCd = actvCd;
    }

}

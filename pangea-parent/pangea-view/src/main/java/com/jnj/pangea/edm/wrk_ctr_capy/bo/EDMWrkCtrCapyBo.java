package com.jnj.pangea.edm.wrk_ctr_capy.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMWrkCtrCapyBo extends BaseBo {

    private String srcSysCd;
    private String wrkCtrTypeCd;
    private String wrkCtrNum;
    private String capyAllcNbr;
    private String vldFromDt;
    private String vldToDt;
    private String chgDttm;
    private String capyNum;
    private String setupFrmlCd;
    private String runFrmlCd;
    private String teardownFrmlCd;
    private String othFrmlCd;
    private String setupDstnCd;
    private String runDstnCd;
    private String teardownDstnCd;
    private String othDstnCd;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("wrkCtrTypeCd", this.wrkCtrTypeCd)
                .add("wrkCtrNum", this.wrkCtrNum)
                .add("capyAllcNbr", this.capyAllcNbr)
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

    public String getCapyAllcNbr() {
        return this.capyAllcNbr;
    }

    public void setCapyAllcNbr(String capyAllcNbr) {
        this.capyAllcNbr = capyAllcNbr;
    }

    public String getVldFromDt() {
        return this.vldFromDt;
    }

    public void setVldFromDt(String vldFromDt) {
        this.vldFromDt = vldFromDt;
    }

    public String getVldToDt() {
        return this.vldToDt;
    }

    public void setVldToDt(String vldToDt) {
        this.vldToDt = vldToDt;
    }

    public String getChgDttm() {
        return this.chgDttm;
    }

    public void setChgDttm(String chgDttm) {
        this.chgDttm = chgDttm;
    }

    public String getCapyNum() {
        return this.capyNum;
    }

    public void setCapyNum(String capyNum) {
        this.capyNum = capyNum;
    }

    public String getSetupFrmlCd() {
        return this.setupFrmlCd;
    }

    public void setSetupFrmlCd(String setupFrmlCd) {
        this.setupFrmlCd = setupFrmlCd;
    }

    public String getRunFrmlCd() {
        return this.runFrmlCd;
    }

    public void setRunFrmlCd(String runFrmlCd) {
        this.runFrmlCd = runFrmlCd;
    }

    public String getTeardownFrmlCd() {
        return this.teardownFrmlCd;
    }

    public void setTeardownFrmlCd(String teardownFrmlCd) {
        this.teardownFrmlCd = teardownFrmlCd;
    }

    public String getOthFrmlCd() {
        return this.othFrmlCd;
    }

    public void setOthFrmlCd(String othFrmlCd) {
        this.othFrmlCd = othFrmlCd;
    }

    public String getSetupDstnCd() {
        return this.setupDstnCd;
    }

    public void setSetupDstnCd(String setupDstnCd) {
        this.setupDstnCd = setupDstnCd;
    }

    public String getRunDstnCd() {
        return this.runDstnCd;
    }

    public void setRunDstnCd(String runDstnCd) {
        this.runDstnCd = runDstnCd;
    }

    public String getTeardownDstnCd() {
        return this.teardownDstnCd;
    }

    public void setTeardownDstnCd(String teardownDstnCd) {
        this.teardownDstnCd = teardownDstnCd;
    }

    public String getOthDstnCd() {
        return this.othDstnCd;
    }

    public void setOthDstnCd(String othDstnCd) {
        this.othDstnCd = othDstnCd;
    }

}

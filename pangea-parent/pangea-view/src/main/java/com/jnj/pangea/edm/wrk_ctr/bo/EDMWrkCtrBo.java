package com.jnj.pangea.edm.wrk_ctr.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMWrkCtrBo extends BaseBo {

    private String srcSysCd;
    private String wrkCtrTypeCd;
    private String wrkCtrNum;
    private String vldFromDt;
    private String vldToDt;
    private String wrkCtrCd;
    private String plntCd;
    private String wrkCtrCatCd;
    private String delInd;
    private String wrkCtrUsgCd;
    private String wrkCtrLocCd;
    private String respPrsnNum;
    private String wrkCtrActvCd;
    private String lockInd;
    private String schdlngInd;
    private String setupTypeCd;
    private String oprCd;
    private String setupFrmlCd;
    private String runFrmlCd;
    private String teardownFrmlCd;
    private String capyNum;
    private String locGrpCd;
    private String machTypeCd;
    private String plnrGrpCd;
    private String othFrmlCd;
    private String suplAreaCd;
    private String slocCd;
    private String mixingInd;
    private String wrkCtrDesc;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("wrkCtrTypeCd",this.wrkCtrTypeCd)
                .add("wrkCtrNum",this.wrkCtrNum)
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

    public String getWrkCtrCd() {
        return this.wrkCtrCd;
    }

    public void setWrkCtrCd(String wrkCtrCd) {
        this.wrkCtrCd = wrkCtrCd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getWrkCtrCatCd() {
        return this.wrkCtrCatCd;
    }

    public void setWrkCtrCatCd(String wrkCtrCatCd) {
        this.wrkCtrCatCd = wrkCtrCatCd;
    }

    public String getDelInd() {
        return this.delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getWrkCtrUsgCd() {
        return this.wrkCtrUsgCd;
    }

    public void setWrkCtrUsgCd(String wrkCtrUsgCd) {
        this.wrkCtrUsgCd = wrkCtrUsgCd;
    }

    public String getWrkCtrLocCd() {
        return this.wrkCtrLocCd;
    }

    public void setWrkCtrLocCd(String wrkCtrLocCd) {
        this.wrkCtrLocCd = wrkCtrLocCd;
    }

    public String getRespPrsnNum() {
        return this.respPrsnNum;
    }

    public void setRespPrsnNum(String respPrsnNum) {
        this.respPrsnNum = respPrsnNum;
    }

    public String getWrkCtrActvCd() {
        return this.wrkCtrActvCd;
    }

    public void setWrkCtrActvCd(String wrkCtrActvCd) {
        this.wrkCtrActvCd = wrkCtrActvCd;
    }

    public String getLockInd() {
        return this.lockInd;
    }

    public void setLockInd(String lockInd) {
        this.lockInd = lockInd;
    }

    public String getSchdlngInd() {
        return this.schdlngInd;
    }

    public void setSchdlngInd(String schdlngInd) {
        this.schdlngInd = schdlngInd;
    }

    public String getSetupTypeCd() {
        return this.setupTypeCd;
    }

    public void setSetupTypeCd(String setupTypeCd) {
        this.setupTypeCd = setupTypeCd;
    }

    public String getOprCd() {
        return this.oprCd;
    }

    public void setOprCd(String oprCd) {
        this.oprCd = oprCd;
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

    public String getCapyNum() {
        return this.capyNum;
    }

    public void setCapyNum(String capyNum) {
        this.capyNum = capyNum;
    }

    public String getLocGrpCd() {
        return this.locGrpCd;
    }

    public void setLocGrpCd(String locGrpCd) {
        this.locGrpCd = locGrpCd;
    }

    public String getMachTypeCd() {
        return this.machTypeCd;
    }

    public void setMachTypeCd(String machTypeCd) {
        this.machTypeCd = machTypeCd;
    }

    public String getPlnrGrpCd() {
        return this.plnrGrpCd;
    }

    public void setPlnrGrpCd(String plnrGrpCd) {
        this.plnrGrpCd = plnrGrpCd;
    }

    public String getOthFrmlCd() {
        return this.othFrmlCd;
    }

    public void setOthFrmlCd(String othFrmlCd) {
        this.othFrmlCd = othFrmlCd;
    }

    public String getSuplAreaCd() {
        return this.suplAreaCd;
    }

    public void setSuplAreaCd(String suplAreaCd) {
        this.suplAreaCd = suplAreaCd;
    }

    public String getSlocCd() {
        return this.slocCd;
    }

    public void setSlocCd(String slocCd) {
        this.slocCd = slocCd;
    }

    public String getMixingInd() {
        return this.mixingInd;
    }

    public void setMixingInd(String mixingInd) {
        this.mixingInd = mixingInd;
    }

    public String getWrkCtrDesc() {
        return this.wrkCtrDesc;
    }

    public void setWrkCtrDesc(String wrkCtrDesc) {
        this.wrkCtrDesc = wrkCtrDesc;
    }

}

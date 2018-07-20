package com.jnj.pangea.edm.mfg_order.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMfgOrderBo extends BaseBo {


    private String sourceSysCd;
    private String mfgOrdrNum;
    private String mfgOrdrTypCd;
    private String plntCd;
    private String crtdDttm;
    private String chgDttm;
    private String delInd;
    private String strtDt;
    private String strtTm;
    private String endDt;
    private String endTm;
    private String schdStrtDt;
    private String schdStrtTm;
    private String schdEndDt;
    private String schdEndTm;
    private String schRelDt;
    private String actStrtDt;
    private String actStrtTm;
    private String prdtnEndDt;
    private String cnfrmdEndDt;
    private String cnfrmdEndTm;
    private String planRlseDt;
    private String actRlseDt;
    private String rsrvtnNum;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngGrpCntrNum;
    private String bomCatCd;
    private String bomNum;
    private String bomAltNum;
    private String mrpCntrllrCd;
    private String ordrRtngNum;
    private String prdSpvsrCd;
    private String cnfrmdYldQty;
    private String cnfrmdScrpQty;
    private String objectNumber;
    private String mfgOrdrSttsCd;
    private String localSystemStatus;
    private String localStatus1;
    private String internalTimeStamp;
    public String getInternalTimeStamp() {
        return internalTimeStamp;
    }

    public void setInternalTimeStamp(String internalTimeStamp) {
        this.internalTimeStamp = internalTimeStamp;
    }




    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSysCd", this.sourceSysCd)
                .add("mfgOrdrNum", this.mfgOrdrNum)
                .toJsonString();
    }

    public String getSourceSysCd() {
        return this.sourceSysCd;
    }

    public void setSourceSysCd(String sourceSysCd) {
        this.sourceSysCd = sourceSysCd;
    }

    public String getMfgOrdrNum() {
        return this.mfgOrdrNum;
    }

    public void setMfgOrdrNum(String mfgOrdrNum) {
        this.mfgOrdrNum = mfgOrdrNum;
    }

    public String getMfgOrdrTypCd() {
        return this.mfgOrdrTypCd;
    }

    public void setMfgOrdrTypCd(String mfgOrdrTypCd) {
        this.mfgOrdrTypCd = mfgOrdrTypCd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getCrtdDttm() {
        return this.crtdDttm;
    }

    public void setCrtdDttm(String crtdDttm) {
        this.crtdDttm = crtdDttm;
    }

    public String getChgDttm() {
        return this.chgDttm;
    }

    public void setChgDttm(String chgDttm) {
        this.chgDttm = chgDttm;
    }

    public String getDelInd() {
        return this.delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getStrtDt() {
        return this.strtDt;
    }

    public void setStrtDt(String strtDt) {
        this.strtDt = strtDt;
    }

    public String getStrtTm() {
        return this.strtTm;
    }

    public void setStrtTm(String strtTm) {
        this.strtTm = strtTm;
    }

    public String getEndDt() {
        return this.endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public String getEndTm() {
        return this.endTm;
    }

    public void setEndTm(String endTm) {
        this.endTm = endTm;
    }

    public String getSchdStrtDt() {
        return this.schdStrtDt;
    }

    public void setSchdStrtDt(String schdStrtDt) {
        this.schdStrtDt = schdStrtDt;
    }

    public String getSchdStrtTm() {
        return this.schdStrtTm;
    }

    public void setSchdStrtTm(String schdStrtTm) {
        this.schdStrtTm = schdStrtTm;
    }

    public String getSchdEndDt() {
        return this.schdEndDt;
    }

    public void setSchdEndDt(String schdEndDt) {
        this.schdEndDt = schdEndDt;
    }

    public String getSchdEndTm() {
        return this.schdEndTm;
    }

    public void setSchdEndTm(String schdEndTm) {
        this.schdEndTm = schdEndTm;
    }

    public String getSchRelDt() {
        return this.schRelDt;
    }

    public void setSchRelDt(String schRelDt) {
        this.schRelDt = schRelDt;
    }

    public String getActStrtDt() {
        return this.actStrtDt;
    }

    public void setActStrtDt(String actStrtDt) {
        this.actStrtDt = actStrtDt;
    }

    public String getActStrtTm() {
        return this.actStrtTm;
    }

    public void setActStrtTm(String actStrtTm) {
        this.actStrtTm = actStrtTm;
    }

    public String getPrdtnEndDt() {
        return this.prdtnEndDt;
    }

    public void setPrdtnEndDt(String prdtnEndDt) {
        this.prdtnEndDt = prdtnEndDt;
    }

    public String getCnfrmdEndDt() {
        return this.cnfrmdEndDt;
    }

    public void setCnfrmdEndDt(String cnfrmdEndDt) {
        this.cnfrmdEndDt = cnfrmdEndDt;
    }

    public String getCnfrmdEndTm() {
        return this.cnfrmdEndTm;
    }

    public void setCnfrmdEndTm(String cnfrmdEndTm) {
        this.cnfrmdEndTm = cnfrmdEndTm;
    }

    public String getPlanRlseDt() {
        return this.planRlseDt;
    }

    public void setPlanRlseDt(String planRlseDt) {
        this.planRlseDt = planRlseDt;
    }

    public String getActRlseDt() {
        return this.actRlseDt;
    }

    public void setActRlseDt(String actRlseDt) {
        this.actRlseDt = actRlseDt;
    }

    public String getRsrvtnNum() {
        return this.rsrvtnNum;
    }

    public void setRsrvtnNum(String rsrvtnNum) {
        this.rsrvtnNum = rsrvtnNum;
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

    public String getRtngGrpCntrNum() {
        return this.rtngGrpCntrNum;
    }

    public void setRtngGrpCntrNum(String rtngGrpCntrNum) {
        this.rtngGrpCntrNum = rtngGrpCntrNum;
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

    public String getBomAltNum() {
        return this.bomAltNum;
    }

    public void setBomAltNum(String bomAltNum) {
        this.bomAltNum = bomAltNum;
    }

    public String getMrpCntrllrCd() {
        return this.mrpCntrllrCd;
    }

    public void setMrpCntrllrCd(String mrpCntrllrCd) {
        this.mrpCntrllrCd = mrpCntrllrCd;
    }

    public String getOrdrRtngNum() {
        return this.ordrRtngNum;
    }

    public void setOrdrRtngNum(String ordrRtngNum) {
        this.ordrRtngNum = ordrRtngNum;
    }

    public String getPrdSpvsrCd() {
        return this.prdSpvsrCd;
    }

    public void setPrdSpvsrCd(String prdSpvsrCd) {
        this.prdSpvsrCd = prdSpvsrCd;
    }

    public String getCnfrmdYldQty() {
        return this.cnfrmdYldQty;
    }

    public void setCnfrmdYldQty(String cnfrmdYldQty) {
        this.cnfrmdYldQty = cnfrmdYldQty;
    }

    public String getCnfrmdScrpQty() {
        return this.cnfrmdScrpQty;
    }

    public void setCnfrmdScrpQty(String cnfrmdScrpQty) {
        this.cnfrmdScrpQty = cnfrmdScrpQty;
    }

    public String getObjectNumber() {
        return objectNumber;
    }

    public void setObjectNumber(String objectNumber) {
        this.objectNumber = objectNumber;
    }

    public String getMfgOrdrSttsCd() {
        return this.mfgOrdrSttsCd;
    }

    public void setMfgOrdrSttsCd(String mfgOrdrSttsCd) {
        this.mfgOrdrSttsCd = mfgOrdrSttsCd;
    }

    public String getLocalSystemStatus() {
        return this.localSystemStatus;
    }

    public void setLocalSystemStatus(String localSystemStatus) {
        this.localSystemStatus = localSystemStatus;
    }

    public String getLocalStatus1() {
        return this.localStatus1;
    }

    public void setLocalStatus1(String localStatus1) {
        this.localStatus1 = localStatus1;
    }

}

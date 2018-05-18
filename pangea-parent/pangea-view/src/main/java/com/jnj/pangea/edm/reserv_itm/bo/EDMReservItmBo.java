package com.jnj.pangea.edm.reserv_itm.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMReservItmBo extends BaseBo {

    private String sourceSysCd;
    private String rsrvtnNum;
    private String rsrvtnItmNum;
    private String rsrvtnRcrdTypCd;
    private String rsrvtnRqrmntTypCd;
    private String mfgOrdrNum;
    private String bomItmNum;
    private String delInd;
    private String finIssind;
    private String matlNum;
    private String plntCd;
    private String sLocCd;
    private String btchNum;
    private String rqmtDt;
    private String rqmtQty;
    private String debCrdInd;
    private String qtyFxInd;
    private String wthdrnQty;
    private String entryQty;
    private String entryUomCd;
    private String mfgPlanOrdrDocNum;
    private String prNum;
    private String prLineNbr;
    private String slsOrdrDocNum;
    private String slsOrdrLineNbr;
    private String mvmtTyp;
    private String rcvngIssngPlntCd;
    private String rcvngIssngSLocCd;
    private String itmCatCd;
    private String cmpntScrapPct;
    private String leadTimeOffset;
    private String fctrNmrtrMeas;
    private String fctrDnmntrMeas;
    private String lnItmNbr;
    private String purchsOrdrNum;
    private String poLineNbr;
    private String bckflushInd;
    private String coProdInd;

    public String getPurchsOrdrNum() {
        return purchsOrdrNum;
    }

    public void setPurchsOrdrNum(String purchsOrdrNum) {
        this.purchsOrdrNum = purchsOrdrNum;
    }

    public String getPoLineNbr() {
        return poLineNbr;
    }

    public void setPoLineNbr(String poLineNbr) {
        this.poLineNbr = poLineNbr;
    }

    public String getBckflushInd() {
        return bckflushInd;
    }

    public void setBckflushInd(String bckflushInd) {
        this.bckflushInd = bckflushInd;
    }

    public String getCoProdInd() {
        return coProdInd;
    }

    public void setCoProdInd(String coProdInd) {
        this.coProdInd = coProdInd;
    }

    public String getGrProcTime() {
        return grProcTime;
    }

    public void setGrProcTime(String grProcTime) {
        this.grProcTime = grProcTime;
    }

    public String getVndNum() {
        return vndNum;
    }

    public void setVndNum(String vndNum) {
        this.vndNum = vndNum;
    }

    private String grProcTime;
    private String vndNum;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSysCd", this.sourceSysCd)
                .add("rsrvtnNum",rsrvtnNum)
                .add("rsrvtnItmNum",rsrvtnItmNum)
                .add("rsrvtnRcrdTypCd",rsrvtnRcrdTypCd)
                .toJsonString();
    }

    public String getSourceSysCd() {
        return sourceSysCd;
    }

    public void setSourceSysCd(String sourceSysCd) {
        this.sourceSysCd = sourceSysCd;
    }

    public String getRsrvtnNum() {
        return rsrvtnNum;
    }

    public void setRsrvtnNum(String rsrvtnNum) {
        this.rsrvtnNum = rsrvtnNum;
    }

    public String getRsrvtnItmNum() {
        return rsrvtnItmNum;
    }

    public void setRsrvtnItmNum(String rsrvtnItmNum) {
        this.rsrvtnItmNum = rsrvtnItmNum;
    }

    public String getRsrvtnRcrdTypCd() {
        return rsrvtnRcrdTypCd;
    }

    public void setRsrvtnRcrdTypCd(String rsrvtnRcrdTypCd) {
        this.rsrvtnRcrdTypCd = rsrvtnRcrdTypCd;
    }

    public String getRsrvtnRqrmntTypCd() {
        return rsrvtnRqrmntTypCd;
    }

    public void setRsrvtnRqrmntTypCd(String rsrvtnRqrmntTypCd) {
        this.rsrvtnRqrmntTypCd = rsrvtnRqrmntTypCd;
    }

    public String getMfgOrdrNum() {
        return mfgOrdrNum;
    }

    public void setMfgOrdrNum(String mfgOrdrNum) {
        this.mfgOrdrNum = mfgOrdrNum;
    }

    public String getBomItmNum() {
        return bomItmNum;
    }

    public void setBomItmNum(String bomItmNum) {
        this.bomItmNum = bomItmNum;
    }

    public String getDelInd() {
        return delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getFinIssind() {
        return finIssind;
    }

    public void setFinIssind(String finIssind) {
        this.finIssind = finIssind;
    }

    public String getMatlNum() {
        return matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getsLocCd() {
        return sLocCd;
    }

    public void setsLocCd(String sLocCd) {
        this.sLocCd = sLocCd;
    }

    public String getBtchNum() {
        return btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getRqmtDt() {
        return rqmtDt;
    }

    public void setRqmtDt(String rqmtDt) {
        this.rqmtDt = rqmtDt;
    }

    public String getRqmtQty() {
        return rqmtQty;
    }

    public void setRqmtQty(String rqmtQty) {
        this.rqmtQty = rqmtQty;
    }


    public String getDebCrdInd() {
        return debCrdInd;
    }

    public void setDebCrdInd(String debCrdInd) {
        this.debCrdInd = debCrdInd;
    }

    public String getQtyFxInd() {
        return qtyFxInd;
    }

    public void setQtyFxInd(String qtyFxInd) {
        this.qtyFxInd = qtyFxInd;
    }

    public String getWthdrnQty() {
        return wthdrnQty;
    }

    public void setWthdrnQty(String wthdrnQty) {
        this.wthdrnQty = wthdrnQty;
    }

    public String getEntryQty() {
        return entryQty;
    }

    public void setEntryQty(String entryQty) {
        this.entryQty = entryQty;
    }

    public String getEntryUomCd() {
        return entryUomCd;
    }

    public void setEntryUomCd(String entryUomCd) {
        this.entryUomCd = entryUomCd;
    }

    public String getMfgPlanOrdrDocNum() {
        return mfgPlanOrdrDocNum;
    }

    public void setMfgPlanOrdrDocNum(String mfgPlanOrdrDocNum) {
        this.mfgPlanOrdrDocNum = mfgPlanOrdrDocNum;
    }

    public String getPrNum() {
        return prNum;
    }

    public void setPrNum(String prNum) {
        this.prNum = prNum;
    }

    public String getPrLineNbr() {
        return prLineNbr;
    }

    public void setPrLineNbr(String prLineNbr) {
        this.prLineNbr = prLineNbr;
    }

    public String getSlsOrdrDocNum() {
        return slsOrdrDocNum;
    }

    public void setSlsOrdrDocNum(String slsOrdrDocNum) {
        this.slsOrdrDocNum = slsOrdrDocNum;
    }

    public String getSlsOrdrLineNbr() {
        return slsOrdrLineNbr;
    }

    public void setSlsOrdrLineNbr(String slsOrdrLineNbr) {
        this.slsOrdrLineNbr = slsOrdrLineNbr;
    }

    public String getMvmtTyp() {
        return mvmtTyp;
    }

    public void setMvmtTyp(String mvmtTyp) {
        this.mvmtTyp = mvmtTyp;
    }

    public String getRcvngIssngPlntCd() {
        return rcvngIssngPlntCd;
    }

    public void setRcvngIssngPlntCd(String rcvngIssngPlntCd) {
        this.rcvngIssngPlntCd = rcvngIssngPlntCd;
    }

    public String getRcvngIssngSLocCd() {
        return rcvngIssngSLocCd;
    }

    public void setRcvngIssngSLocCd(String rcvngIssngSLocCd) {
        this.rcvngIssngSLocCd = rcvngIssngSLocCd;
    }

    public String getItmCatCd() {
        return itmCatCd;
    }

    public void setItmCatCd(String itmCatCd) {
        this.itmCatCd = itmCatCd;
    }

    public String getCmpntScrapPct() {
        return cmpntScrapPct;
    }

    public void setCmpntScrapPct(String cmpntScrapPct) {
        this.cmpntScrapPct = cmpntScrapPct;
    }

    public String getLeadTimeOffset() {
        return leadTimeOffset;
    }

    public void setLeadTimeOffset(String leadTimeOffset) {
        this.leadTimeOffset = leadTimeOffset;
    }

    public String getFctrNmrtrMeas() {
        return fctrNmrtrMeas;
    }

    public void setFctrNmrtrMeas(String fctrNmrtrMeas) {
        this.fctrNmrtrMeas = fctrNmrtrMeas;
    }

    public String getFctrDnmntrMeas() {
        return fctrDnmntrMeas;
    }

    public void setFctrDnmntrMeas(String fctrDnmntrMeas) {
        this.fctrDnmntrMeas = fctrDnmntrMeas;
    }

    public String getLnItmNbr() {
        return lnItmNbr;
    }

    public void setLnItmNbr(String lnItmNbr) {
        this.lnItmNbr = lnItmNbr;
    }




}

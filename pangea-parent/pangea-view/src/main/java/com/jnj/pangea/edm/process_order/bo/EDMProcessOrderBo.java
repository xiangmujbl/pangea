package com.jnj.pangea.edm.process_order.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMProcessOrderBo extends BaseBo {

    private String sourceSystem;
    private String mfgOrdrNum;
    private String mfgOrdrItemNum;
    private String ordrType;
    private String localOrderCat;
    private String crtdDttm;
    private String chgDttm;
    private String plntCd;
    private String delInd;
    private String localTechnicalCompletion;
    private String localProductionProcessNo;
    private String localLogicalSystem;
    private String plnEndDt;
    private String plnStrtDt;
    private String prdtnSchdEndDt;
    private String prdtnSchdStrtDt;
    private String prdntStrtDt;
    private String prdtnEndDt;
    private String prdtnRlseDt;
    private String prdtnPlanRlseDt;
    private String mfgPlnnr;
    private String mfgSprvsr;
    private String localReservation;
    private String localTotalScrap;
    private String localTargetQty;
    private String localBaseUOM;
    private String localMaterialAFKOPLNBEZ;
    private String localTaskListType;
    private String localGroup;
    private String localApplication;
    private String localGroupCounter;
    private String localUsage;
    private String localMaterialAFKOSTLBEZ;
    private String localSchedulingType;
    private String localConfirmation;
    private String localConfirmationCounter;
    private String localConfirmedQtyIGMNG;
    private String plnndOrdr;
    private String scrpQty;
    private String ordrQty;
    private String rcvdQty;
    private String prdtnUom;
    private String matlNum;
    private String dlvCmpltInd;
    private String prdntVrsnNum;
    private String btchNum;
    private String ordrStts;
    private String fctrNmrtrMeas;
    private String fctrDnmntrMeas;
    private String goodRcptLdDaysQty;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("mfgOrdrNum", this.mfgOrdrNum)
                .add("mfgOrdrItemNum", this.mfgOrdrItemNum)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getMfgOrdrNum() {
        return this.mfgOrdrNum;
    }

    public void setMfgOrdrNum(String mfgOrdrNum) {
        this.mfgOrdrNum = mfgOrdrNum;
    }

    public String getMfgOrdrItemNum() {
        return this.mfgOrdrItemNum;
    }

    public void setMfgOrdrItemNum(String mfgOrdrItemNum) {
        this.mfgOrdrItemNum = mfgOrdrItemNum;
    }

    public String getOrdrType() {
        return this.ordrType;
    }

    public void setOrdrType(String ordrType) {
        this.ordrType = ordrType;
    }

    public String getLocalOrderCat() {
        return this.localOrderCat;
    }

    public void setLocalOrderCat(String localOrderCat) {
        this.localOrderCat = localOrderCat;
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

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getDelInd() {
        return this.delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getLocalTechnicalCompletion() {
        return this.localTechnicalCompletion;
    }

    public void setLocalTechnicalCompletion(String localTechnicalCompletion) {
        this.localTechnicalCompletion = localTechnicalCompletion;
    }

    public String getLocalProductionProcessNo() {
        return this.localProductionProcessNo;
    }

    public void setLocalProductionProcessNo(String localProductionProcessNo) {
        this.localProductionProcessNo = localProductionProcessNo;
    }

    public String getLocalLogicalSystem() {
        return this.localLogicalSystem;
    }

    public void setLocalLogicalSystem(String localLogicalSystem) {
        this.localLogicalSystem = localLogicalSystem;
    }

    public String getPlnEndDt() {
        return this.plnEndDt;
    }

    public void setPlnEndDt(String plnEndDt) {
        this.plnEndDt = plnEndDt;
    }

    public String getPlnStrtDt() {
        return this.plnStrtDt;
    }

    public void setPlnStrtDt(String plnStrtDt) {
        this.plnStrtDt = plnStrtDt;
    }

    public String getPrdtnSchdEndDt() {
        return this.prdtnSchdEndDt;
    }

    public void setPrdtnSchdEndDt(String prdtnSchdEndDt) {
        this.prdtnSchdEndDt = prdtnSchdEndDt;
    }

    public String getPrdtnSchdStrtDt() {
        return this.prdtnSchdStrtDt;
    }

    public void setPrdtnSchdStrtDt(String prdtnSchdStrtDt) {
        this.prdtnSchdStrtDt = prdtnSchdStrtDt;
    }

    public String getPrdntStrtDt() {
        return this.prdntStrtDt;
    }

    public void setPrdntStrtDt(String prdntStrtDt) {
        this.prdntStrtDt = prdntStrtDt;
    }

    public String getPrdtnEndDt() {
        return this.prdtnEndDt;
    }

    public void setPrdtnEndDt(String prdtnEndDt) {
        this.prdtnEndDt = prdtnEndDt;
    }

    public String getPrdtnRlseDt() {
        return this.prdtnRlseDt;
    }

    public void setPrdtnRlseDt(String prdtnRlseDt) {
        this.prdtnRlseDt = prdtnRlseDt;
    }

    public String getPrdtnPlanRlseDt() {
        return this.prdtnPlanRlseDt;
    }

    public void setPrdtnPlanRlseDt(String prdtnPlanRlseDt) {
        this.prdtnPlanRlseDt = prdtnPlanRlseDt;
    }

    public String getMfgPlnnr() {
        return this.mfgPlnnr;
    }

    public void setMfgPlnnr(String mfgPlnnr) {
        this.mfgPlnnr = mfgPlnnr;
    }

    public String getMfgSprvsr() {
        return this.mfgSprvsr;
    }

    public void setMfgSprvsr(String mfgSprvsr) {
        this.mfgSprvsr = mfgSprvsr;
    }

    public String getLocalReservation() {
        return this.localReservation;
    }

    public void setLocalReservation(String localReservation) {
        this.localReservation = localReservation;
    }

    public String getLocalTotalScrap() {
        return this.localTotalScrap;
    }

    public void setLocalTotalScrap(String localTotalScrap) {
        this.localTotalScrap = localTotalScrap;
    }

    public String getLocalTargetQty() {
        return this.localTargetQty;
    }

    public void setLocalTargetQty(String localTargetQty) {
        this.localTargetQty = localTargetQty;
    }

    public String getLocalBaseUOM() {
        return this.localBaseUOM;
    }

    public void setLocalBaseUOM(String localBaseUOM) {
        this.localBaseUOM = localBaseUOM;
    }

    public String getLocalMaterialAFKOPLNBEZ() {
        return this.localMaterialAFKOPLNBEZ;
    }

    public void setLocalMaterialAFKOPLNBEZ(String localMaterialAFKOPLNBEZ) {
        this.localMaterialAFKOPLNBEZ = localMaterialAFKOPLNBEZ;
    }

    public String getLocalTaskListType() {
        return this.localTaskListType;
    }

    public void setLocalTaskListType(String localTaskListType) {
        this.localTaskListType = localTaskListType;
    }

    public String getLocalGroup() {
        return this.localGroup;
    }

    public void setLocalGroup(String localGroup) {
        this.localGroup = localGroup;
    }

    public String getLocalApplication() {
        return this.localApplication;
    }

    public void setLocalApplication(String localApplication) {
        this.localApplication = localApplication;
    }

    public String getLocalGroupCounter() {
        return this.localGroupCounter;
    }

    public void setLocalGroupCounter(String localGroupCounter) {
        this.localGroupCounter = localGroupCounter;
    }

    public String getLocalUsage() {
        return this.localUsage;
    }

    public void setLocalUsage(String localUsage) {
        this.localUsage = localUsage;
    }

    public String getLocalMaterialAFKOSTLBEZ() {
        return this.localMaterialAFKOSTLBEZ;
    }

    public void setLocalMaterialAFKOSTLBEZ(String localMaterialAFKOSTLBEZ) {
        this.localMaterialAFKOSTLBEZ = localMaterialAFKOSTLBEZ;
    }

    public String getLocalSchedulingType() {
        return this.localSchedulingType;
    }

    public void setLocalSchedulingType(String localSchedulingType) {
        this.localSchedulingType = localSchedulingType;
    }

    public String getLocalConfirmation() {
        return this.localConfirmation;
    }

    public void setLocalConfirmation(String localConfirmation) {
        this.localConfirmation = localConfirmation;
    }

    public String getLocalConfirmationCounter() {
        return this.localConfirmationCounter;
    }

    public void setLocalConfirmationCounter(String localConfirmationCounter) {
        this.localConfirmationCounter = localConfirmationCounter;
    }

    public String getLocalConfirmedQtyIGMNG() {
        return this.localConfirmedQtyIGMNG;
    }

    public void setLocalConfirmedQtyIGMNG(String localConfirmedQtyIGMNG) {
        this.localConfirmedQtyIGMNG = localConfirmedQtyIGMNG;
    }

    public String getPlnndOrdr() {
        return this.plnndOrdr;
    }

    public void setPlnndOrdr(String plnndOrdr) {
        this.plnndOrdr = plnndOrdr;
    }

    public String getScrpQty() {
        return this.scrpQty;
    }

    public void setScrpQty(String scrpQty) {
        this.scrpQty = scrpQty;
    }

    public String getOrdrQty() {
        return this.ordrQty;
    }

    public void setOrdrQty(String ordrQty) {
        this.ordrQty = ordrQty;
    }

    public String getRcvdQty() {
        return this.rcvdQty;
    }

    public void setRcvdQty(String rcvdQty) {
        this.rcvdQty = rcvdQty;
    }

    public String getPrdtnUom() {
        return this.prdtnUom;
    }

    public void setPrdtnUom(String prdtnUom) {
        this.prdtnUom = prdtnUom;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getDlvCmpltInd() {
        return this.dlvCmpltInd;
    }

    public void setDlvCmpltInd(String dlvCmpltInd) {
        this.dlvCmpltInd = dlvCmpltInd;
    }

    public String getPrdntVrsnNum() {
        return this.prdntVrsnNum;
    }

    public void setPrdntVrsnNum(String prdntVrsnNum) {
        this.prdntVrsnNum = prdntVrsnNum;
    }

    public String getBtchNum() {
        return this.btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getOrdrStts() {
        return this.ordrStts;
    }

    public void setOrdrStts(String ordrStts) {
        this.ordrStts = ordrStts;
    }

    public String getFctrNmrtrMeas() {
        return this.fctrNmrtrMeas;
    }

    public void setFctrNmrtrMeas(String fctrNmrtrMeas) {
        this.fctrNmrtrMeas = fctrNmrtrMeas;
    }

    public String getFctrDnmntrMeas() {
        return this.fctrDnmntrMeas;
    }

    public void setFctrDnmntrMeas(String fctrDnmntrMeas) {
        this.fctrDnmntrMeas = fctrDnmntrMeas;
    }

    public String getGoodRcptLdDaysQty() {
        return this.goodRcptLdDaysQty;
    }

    public void setGoodRcptLdDaysQty(String goodRcptLdDaysQty) {
        this.goodRcptLdDaysQty = goodRcptLdDaysQty;
    }

}

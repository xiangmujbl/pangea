package com.jnj.pangea.edm.planned_order.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMPlannedOrderBo extends BaseBo {

    private String srcSysCd;
    private String mfgPlanOrdrDocId;
    private String planPlntCd;
    private String plntCd;
    private String matlNum;
    private String uomCd;
    private String prcmtTypeCd;
    private String splPrcmtTypeCd;
    private String prdtnVersNum;
    private String planOrdrTypeCd;
    private String planOrdrQty;
    private String fxScrapQty;
    private String reqQty;
    private String planOrdrStrtDt;
    private String prdtnStrtTm;
    private String planOrdrEndDt;
    private String planOrdrEndTm;
    private String grDaysLeadQty;
    private String firmingInd;
    private String sLocCd;
    private String prdtnVers;
    private String prdtnStrtDt;
    private String prdtnFnshdDt;
    private String mrpCtlId;
    private String plngScnroCd;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("mfgPlanOrdrDocId", this.mfgPlanOrdrDocId)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getMfgPlanOrdrDocId() {
        return this.mfgPlanOrdrDocId;
    }

    public void setMfgPlanOrdrDocId(String mfgPlanOrdrDocId) {
        this.mfgPlanOrdrDocId = mfgPlanOrdrDocId;
    }

    public String getPlanPlntCd() {
        return this.planPlntCd;
    }

    public void setPlanPlntCd(String planPlntCd) {
        this.planPlntCd = planPlntCd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getUomCd() {
        return this.uomCd;
    }

    public void setUomCd(String uomCd) {
        this.uomCd = uomCd;
    }

    public String getPrcmtTypeCd() {
        return this.prcmtTypeCd;
    }

    public void setPrcmtTypeCd(String prcmtTypeCd) {
        this.prcmtTypeCd = prcmtTypeCd;
    }

    public String getSplPrcmtTypeCd() {
        return this.splPrcmtTypeCd;
    }

    public void setSplPrcmtTypeCd(String splPrcmtTypeCd) {
        this.splPrcmtTypeCd = splPrcmtTypeCd;
    }

    public String getPrdtnVersNum() {
        return this.prdtnVersNum;
    }

    public void setPrdtnVersNum(String prdtnVersNum) {
        this.prdtnVersNum = prdtnVersNum;
    }

    public String getPlanOrdrTypeCd() {
        return this.planOrdrTypeCd;
    }

    public void setPlanOrdrTypeCd(String planOrdrTypeCd) {
        this.planOrdrTypeCd = planOrdrTypeCd;
    }

    public String getPlanOrdrQty() {
        return this.planOrdrQty;
    }

    public void setPlanOrdrQty(String planOrdrQty) {
        this.planOrdrQty = planOrdrQty;
    }

    public String getFxScrapQty() {
        return this.fxScrapQty;
    }

    public void setFxScrapQty(String fxScrapQty) {
        this.fxScrapQty = fxScrapQty;
    }

    public String getReqQty() {
        return this.reqQty;
    }

    public void setReqQty(String reqQty) {
        this.reqQty = reqQty;
    }

    public String getPlanOrdrStrtDt() {
        return this.planOrdrStrtDt;
    }

    public void setPlanOrdrStrtDt(String planOrdrStrtDt) {
        this.planOrdrStrtDt = planOrdrStrtDt;
    }

    public String getPrdtnStrtTm() {
        return this.prdtnStrtTm;
    }

    public void setPrdtnStrtTm(String prdtnStrtTm) {
        this.prdtnStrtTm = prdtnStrtTm;
    }

    public String getPlanOrdrEndDt() {
        return this.planOrdrEndDt;
    }

    public void setPlanOrdrEndDt(String planOrdrEndDt) {
        this.planOrdrEndDt = planOrdrEndDt;
    }

    public String getPlanOrdrEndTm() {
        return this.planOrdrEndTm;
    }

    public void setPlanOrdrEndTm(String planOrdrEndTm) {
        this.planOrdrEndTm = planOrdrEndTm;
    }

    public String getGrDaysLeadQty() {
        return this.grDaysLeadQty;
    }

    public void setGrDaysLeadQty(String grDaysLeadQty) {
        this.grDaysLeadQty = grDaysLeadQty;
    }

    public String getFirmingInd() {
        return this.firmingInd;
    }

    public void setFirmingInd(String firmingInd) {
        this.firmingInd = firmingInd;
    }

    public String getSLocCd() {
        return this.sLocCd;
    }

    public void setSLocCd(String sLocCd) {
        this.sLocCd = sLocCd;
    }

    public String getPrdtnVers() {
        return this.prdtnVers;
    }

    public void setPrdtnVers(String prdtnVers) {
        this.prdtnVers = prdtnVers;
    }

    public String getPrdtnStrtDt() {
        return this.prdtnStrtDt;
    }

    public void setPrdtnStrtDt(String prdtnStrtDt) {
        this.prdtnStrtDt = prdtnStrtDt;
    }

    public String getPrdtnFnshdDt() {
        return this.prdtnFnshdDt;
    }

    public void setPrdtnFnshdDt(String prdtnFnshdDt) {
        this.prdtnFnshdDt = prdtnFnshdDt;
    }

    public String getMrpCtlId() {
        return this.mrpCtlId;
    }

    public void setMrpCtlId(String mrpCtlId) {
        this.mrpCtlId = mrpCtlId;
    }

    public String getPlngScnroCd() {
        return this.plngScnroCd;
    }

    public void setPlngScnroCd(String plngScnroCd) {
        this.plngScnroCd = plngScnroCd;
    }

}

package com.jnj.pangea.edm.matl_prod_versn.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMatlProdVersnBo extends BaseBo {

    private String srcSysCd;
    private String matlNum;
    private String plntCd;
    private String prdntVrsnNum;
    private String valFromDt;
    private String valToDt;
    private String altBomNum;
    private String bomUsgCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngGrpCntrNum;
    private String prcrmntTypCd;
    private String spPrcrmntTypCd;
    private String cstLtSzQty;
    private String mfgLineCd;
    private String prdVrsnDesc;
    private String dstrbtnKeyCd;
    private String frmLtSzQty;
    private String toLtSzQty;
    private String sLocCd;
    private String chckInd;
    private String chckDt;
    private String lckInd;
    private String orgBtchInd;
    private String crtDttm;
    private String chgDttm;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", srcSysCd)
                .add("matlNum",matlNum)
                .add("plntCd",plntCd)
                .add("prdntVrsnNum",prdntVrsnNum)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getPrdntVrsnNum() {
        return this.prdntVrsnNum;
    }

    public void setPrdntVrsnNum(String prdntVrsnNum) {
        this.prdntVrsnNum = prdntVrsnNum;
    }

    public String getValFromDt() {
        return this.valFromDt;
    }

    public void setValFromDt(String valFromDt) {
        this.valFromDt = valFromDt;
    }

    public String getValToDt() {
        return this.valToDt;
    }

    public void setValToDt(String valToDt) {
        this.valToDt = valToDt;
    }

    public String getAltBomNum() {
        return this.altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getBomUsgCd() {
        return this.bomUsgCd;
    }

    public void setBomUsgCd(String bomUsgCd) {
        this.bomUsgCd = bomUsgCd;
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

    public String getPrcrmntTypCd() {
        return this.prcrmntTypCd;
    }

    public void setPrcrmntTypCd(String prcrmntTypCd) {
        this.prcrmntTypCd = prcrmntTypCd;
    }

    public String getSpPrcrmntTypCd() {
        return this.spPrcrmntTypCd;
    }

    public void setSpPrcrmntTypCd(String spPrcrmntTypCd) {
        this.spPrcrmntTypCd = spPrcrmntTypCd;
    }

    public String getCstLtSzQty() {
        return this.cstLtSzQty;
    }

    public void setCstLtSzQty(String cstLtSzQty) {
        this.cstLtSzQty = cstLtSzQty;
    }

    public String getMfgLineCd() {
        return this.mfgLineCd;
    }

    public void setMfgLineCd(String mfgLineCd) {
        this.mfgLineCd = mfgLineCd;
    }

    public String getPrdVrsnDesc() {
        return this.prdVrsnDesc;
    }

    public void setPrdVrsnDesc(String prdVrsnDesc) {
        this.prdVrsnDesc = prdVrsnDesc;
    }

    public String getDstrbtnKeyCd() {
        return this.dstrbtnKeyCd;
    }

    public void setDstrbtnKeyCd(String dstrbtnKeyCd) {
        this.dstrbtnKeyCd = dstrbtnKeyCd;
    }

    public String getFrmLtSzQty() {
        return this.frmLtSzQty;
    }

    public void setFrmLtSzQty(String frmLtSzQty) {
        this.frmLtSzQty = frmLtSzQty;
    }

    public String getToLtSzQty() {
        return this.toLtSzQty;
    }

    public void setToLtSzQty(String toLtSzQty) {
        this.toLtSzQty = toLtSzQty;
    }

    public String getSLocCd() {
        return this.sLocCd;
    }

    public void setSLocCd(String sLocCd) {
        this.sLocCd = sLocCd;
    }

    public String getChckInd() {
        return this.chckInd;
    }

    public void setChckInd(String chckInd) {
        this.chckInd = chckInd;
    }

    public String getChckDt() {
        return this.chckDt;
    }

    public void setChckDt(String chckDt) {
        this.chckDt = chckDt;
    }

    public String getLckInd() {
        return this.lckInd;
    }

    public void setLckInd(String lckInd) {
        this.lckInd = lckInd;
    }

    public String getOrgBtchInd() {
        return this.orgBtchInd;
    }

    public void setOrgBtchInd(String orgBtchInd) {
        this.orgBtchInd = orgBtchInd;
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

}

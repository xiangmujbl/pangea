package com.jnj.pangea.edm.inspection_lot.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMInspectionLotBo extends BaseBo {

    private String srcSysCd;
    private String lotNum;
    private String plntCd;
    private String matlId;
    private String baseUom;
    private String lotVerifTypeCd;
    private String lotOrigCd;
    private String localObjectNumber;
    private String stsPrfl;
    private String usgDecInd;
    private String localDateOfLotCreation;
    private String localTimeOfLotCreation;
    private String crtDttm;
    private String chgDttm;
    private String inspStrtDt;
    private String inspStrtTm;
    private String inspEndDt;
    private String inspEndTm;
    private String cstmNum;
    private String vndrNum;
    private String btchNum;
    private String stgLocCd;
    private String mfgOrdrDoc;
    private String poDocNum;
    private String poDocLineNbr;
    private String matlMvmtDocYr;
    private String matlMvmtDocNum;
    private String stckPlntCd;
    private String stckSLocCd;
    private String inspLotQty;
    private String actlLotQty;
    private String toBePostedQty;
    private String qcStsCd;
    private String usgDcsnCd;
    private String qcDcsnDttm;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("lotNum", this.lotNum)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getLotNum() {
        return this.lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getplntCd() {
        return this.plntCd;
    }

    public void setplntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getMatlId() {
        return this.matlId;
    }

    public void setMatlId(String matlId) {
        this.matlId = matlId;
    }

    public String getBaseUom() {
        return this.baseUom;
    }

    public void setBaseUom(String baseUom) {
        this.baseUom = baseUom;
    }

    public String getLotVerifTypeCd() {
        return this.lotVerifTypeCd;
    }

    public void setLotVerifTypeCd(String lotVerifTypeCd) {
        this.lotVerifTypeCd = lotVerifTypeCd;
    }

    public String getLotOrigCd() {
        return this.lotOrigCd;
    }

    public void setLotOrigCd(String lotOrigCd) {
        this.lotOrigCd = lotOrigCd;
    }

    public String getLocalObjectNumber() {
        return this.localObjectNumber;
    }

    public void setLocalObjectNumber(String localObjectNumber) {
        this.localObjectNumber = localObjectNumber;
    }

    public String getStsPrfl() {
        return this.stsPrfl;
    }

    public void setStsPrfl(String stsPrfl) {
        this.stsPrfl = stsPrfl;
    }

    public String getUsgDecInd() {
        return this.usgDecInd;
    }

    public void setUsgDecInd(String usgDecInd) {
        this.usgDecInd = usgDecInd;
    }

    public String getLocalDateOfLotCreation() {
        return this.localDateOfLotCreation;
    }

    public void setLocalDateOfLotCreation(String localDateOfLotCreation) {
        this.localDateOfLotCreation = localDateOfLotCreation;
    }

    public String getLocalTimeOfLotCreation() {
        return this.localTimeOfLotCreation;
    }

    public void setLocalTimeOfLotCreation(String localTimeOfLotCreation) {
        this.localTimeOfLotCreation = localTimeOfLotCreation;
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

    public String getInspStrtDt() {
        return this.inspStrtDt;
    }

    public void setInspStrtDt(String inspStrtDt) {
        this.inspStrtDt = inspStrtDt;
    }

    public String getInspStrtTm() {
        return this.inspStrtTm;
    }

    public void setInspStrtTm(String inspStrtTm) {
        this.inspStrtTm = inspStrtTm;
    }

    public String getInspEndDt() {
        return this.inspEndDt;
    }

    public void setInspEndDt(String inspEndDt) {
        this.inspEndDt = inspEndDt;
    }

    public String getInspEndTm() {
        return this.inspEndTm;
    }

    public void setInspEndTm(String inspEndTm) {
        this.inspEndTm = inspEndTm;
    }

    public String getCstmNum() {
        return this.cstmNum;
    }

    public void setCstmNum(String cstmNum) {
        this.cstmNum = cstmNum;
    }

    public String getVndrNum() {
        return this.vndrNum;
    }

    public void setVndrNum(String vndrNum) {
        this.vndrNum = vndrNum;
    }

    public String getBtchNum() {
        return this.btchNum;
    }

    public void setBtchNum(String btchNum) {
        this.btchNum = btchNum;
    }

    public String getStgLocCd() {
        return this.stgLocCd;
    }

    public void setStgLocCd(String stgLocCd) {
        this.stgLocCd = stgLocCd;
    }

    public String getMfgOrdrDoc() {
        return this.mfgOrdrDoc;
    }

    public void setMfgOrdrDoc(String mfgOrdrDoc) {
        this.mfgOrdrDoc = mfgOrdrDoc;
    }

    public String getPoDocNum() {
        return this.poDocNum;
    }

    public void setPoDocNum(String poDocNum) {
        this.poDocNum = poDocNum;
    }

    public String getPoDocLineNbr() {
        return this.poDocLineNbr;
    }

    public void setPoDocLineNbr(String poDocLineNbr) {
        this.poDocLineNbr = poDocLineNbr;
    }

    public String getMatlMvmtDocYr() {
        return this.matlMvmtDocYr;
    }

    public void setMatlMvmtDocYr(String matlMvmtDocYr) {
        this.matlMvmtDocYr = matlMvmtDocYr;
    }

    public String getMatlMvmtDocNum() {
        return this.matlMvmtDocNum;
    }

    public void setMatlMvmtDocNum(String matlMvmtDocNum) {
        this.matlMvmtDocNum = matlMvmtDocNum;
    }

    public String getStckPlntCd() {
        return this.stckPlntCd;
    }

    public void setStckPlntCd(String stckPlntCd) {
        this.stckPlntCd = stckPlntCd;
    }

    public String getStckSLocCd() {
        return this.stckSLocCd;
    }

    public void setStckSLocCd(String stckSLocCd) {
        this.stckSLocCd = stckSLocCd;
    }

    public String getInspLotQty() {
        return this.inspLotQty;
    }

    public void setInspLotQty(String inspLotQty) {
        this.inspLotQty = inspLotQty;
    }

    public String getActlLotQty() {
        return this.actlLotQty;
    }

    public void setActlLotQty(String actlLotQty) {
        this.actlLotQty = actlLotQty;
    }

    public String getToBePostedQty() {
        return this.toBePostedQty;
    }

    public void setToBePostedQty(String toBePostedQty) {
        this.toBePostedQty = toBePostedQty;
    }

    public String getQcStsCd() {
        return this.qcStsCd;
    }

    public void setQcStsCd(String qcStsCd) {
        this.qcStsCd = qcStsCd;
    }

    public String getUsgDcsnCd() {
        return this.usgDcsnCd;
    }

    public void setUsgDcsnCd(String usgDcsnCd) {
        this.usgDcsnCd = usgDcsnCd;
    }

    public String getQcDcsnDttm() {
        return this.qcDcsnDttm;
    }

    public void setQcDcsnDttm(String qcDcsnDttm) {
        this.qcDcsnDttm = qcDcsnDttm;
    }

}

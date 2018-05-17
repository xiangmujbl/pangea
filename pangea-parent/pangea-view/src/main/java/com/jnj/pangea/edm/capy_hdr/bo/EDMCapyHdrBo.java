package com.jnj.pangea.edm.capy_hdr.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMCapyHdrBo extends BaseBo {

    private String srcSysCd;
    private String capyNum;
    private String capyNbr;
    private String strtTm;
    private String endTm;
    private String fctryCalCd;
    private String capyCatCd;
    private String capyBaseUomCd;
    private String capyNm;
    private String capyUtlzRtPct;
    private String plnrGrpCd;
    private String poolCapyInd;
    private String plntCd;
    private String finiteSchdlngInd;
    private String mltOpsInd;
    private String ovldPct;
    private String ltpExclnInd;
    private String capyUomCd;
    private String indivCapyInd;
    private String availCapyUomCd;
    private String btneckInd;
    private String capyDesc;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", srcSysCd)
                .add("capyNum",capyNum)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getCapyNum() {
        return this.capyNum;
    }

    public void setCapyNum(String capyNum) {
        this.capyNum = capyNum;
    }

    public String getCapyNbr() {
        return this.capyNbr;
    }

    public void setCapyNbr(String capyNbr) {
        this.capyNbr = capyNbr;
    }

    public String getStrtTm() {
        return this.strtTm;
    }

    public void setStrtTm(String strtTm) {
        this.strtTm = strtTm;
    }

    public String getEndTm() {
        return this.endTm;
    }

    public void setEndTm(String endTm) {
        this.endTm = endTm;
    }

    public String getFctryCalCd() {
        return this.fctryCalCd;
    }

    public void setFctryCalCd(String fctryCalCd) {
        this.fctryCalCd = fctryCalCd;
    }

    public String getCapyCatCd() {
        return this.capyCatCd;
    }

    public void setCapyCatCd(String capyCatCd) {
        this.capyCatCd = capyCatCd;
    }

    public String getCapyBaseUomCd() {
        return this.capyBaseUomCd;
    }

    public void setCapyBaseUomCd(String capyBaseUomCd) {
        this.capyBaseUomCd = capyBaseUomCd;
    }

    public String getCapyNm() {
        return this.capyNm;
    }

    public void setCapyNm(String capyNm) {
        this.capyNm = capyNm;
    }

    public String getCapyUtlzRtPct() {
        return this.capyUtlzRtPct;
    }

    public void setCapyUtlzRtPct(String capyUtlzRtPct) {
        this.capyUtlzRtPct = capyUtlzRtPct;
    }

    public String getPlnrGrpCd() {
        return this.plnrGrpCd;
    }

    public void setPlnrGrpCd(String plnrGrpCd) {
        this.plnrGrpCd = plnrGrpCd;
    }

    public String getPoolCapyInd() {
        return this.poolCapyInd;
    }

    public void setPoolCapyInd(String poolCapyInd) {
        this.poolCapyInd = poolCapyInd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getFiniteSchdlngInd() {
        return this.finiteSchdlngInd;
    }

    public void setFiniteSchdlngInd(String finiteSchdlngInd) {
        this.finiteSchdlngInd = finiteSchdlngInd;
    }

    public String getMltOpsInd() {
        return this.mltOpsInd;
    }

    public void setMltOpsInd(String mltOpsInd) {
        this.mltOpsInd = mltOpsInd;
    }

    public String getOvldPct() {
        return this.ovldPct;
    }

    public void setOvldPct(String ovldPct) {
        this.ovldPct = ovldPct;
    }

    public String getLtpExclnInd() {
        return this.ltpExclnInd;
    }

    public void setLtpExclnInd(String ltpExclnInd) {
        this.ltpExclnInd = ltpExclnInd;
    }

    public String getCapyUomCd() {
        return this.capyUomCd;
    }

    public void setCapyUomCd(String capyUomCd) {
        this.capyUomCd = capyUomCd;
    }

    public String getIndivCapyInd() {
        return this.indivCapyInd;
    }

    public void setIndivCapyInd(String indivCapyInd) {
        this.indivCapyInd = indivCapyInd;
    }

    public String getAvailCapyUomCd() {
        return this.availCapyUomCd;
    }

    public void setAvailCapyUomCd(String availCapyUomCd) {
        this.availCapyUomCd = availCapyUomCd;
    }

    public String getBtneckInd() {
        return this.btneckInd;
    }

    public void setBtneckInd(String btneckInd) {
        this.btneckInd = btneckInd;
    }

    public String getCapyDesc() {
        return this.capyDesc;
    }

    public void setCapyDesc(String capyDesc) {
        this.capyDesc = capyDesc;
    }

}

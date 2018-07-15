package com.jnj.pangea.edm.reserv_hdr.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMReservHdrBo extends BaseBo {

    private String sourceSysCd;
    private String rsrvtnNum;
    private String rsrvtnOrigCd;
    private String factCalndrInd;
    private String rsrvtnBsDt;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSysCd", sourceSysCd)
                .add("rsrvtnNum",rsrvtnNum)
                .toJsonString();
    }

    public String getSourceSysCd() {
        return this.sourceSysCd;
    }

    public void setSourceSysCd(String sourceSysCd) {
        this.sourceSysCd = sourceSysCd;
    }

    public String getRsrvtnNum() {
        return this.rsrvtnNum;
    }

    public void setRsrvtnNum(String rsrvtnNum) {
        this.rsrvtnNum = rsrvtnNum;
    }

    public String getRsrvtnOrigCd() {
        return this.rsrvtnOrigCd;
    }

    public void setRsrvtnOrigCd(String rsrvtnOrigCd) {
        this.rsrvtnOrigCd = rsrvtnOrigCd;
    }

    public String getFactCalndrInd() {
        return this.factCalndrInd;
    }

    public void setFactCalndrInd(String factCalndrInd) {
        this.factCalndrInd = factCalndrInd;
    }

    public String getRsrvtnBsDt() {
        return this.rsrvtnBsDt;
    }

    public void setRsrvtnBsDt(String rsrvtnBsDt) {
        this.rsrvtnBsDt = rsrvtnBsDt;
    }

}

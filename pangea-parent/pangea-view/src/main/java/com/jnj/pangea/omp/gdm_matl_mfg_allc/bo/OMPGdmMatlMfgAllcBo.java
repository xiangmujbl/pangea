package com.jnj.pangea.omp.gdm_matl_mfg_allc.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmMatlMfgAllcBo extends BaseBo {


    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String allocNum;
    private String allocCount;
    private String allocValidFrom;
    private String chgNum;
    private String delInd;
    private String rtngGrpCntrNbr;
    private String rtngSqncNum;
    private String rtngNdeNum;
    private String bomCatCd;
    private String bomNum;
    private String altBomNum;
    private String bomItmNdeNum;
    private String plntCd;
    private String allocValidTo;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", this.srcSysCd)
                .add("rtngTypCd", this.rtngTypCd)
                .add("rtngGrpCd", this.rtngGrpCd)
                .add("allocNum", this.allocNum)
                .add("allocCount", this.allocCount)
                .toJsonString();
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getRtngTypCd() {
        return rtngTypCd;
    }

    public void setRtngTypCd(String rtngTypCd) {
        this.rtngTypCd = rtngTypCd;
    }

    public String getRtngGrpCd() {
        return rtngGrpCd;
    }

    public void setRtngGrpCd(String rtngGrpCd) {
        this.rtngGrpCd = rtngGrpCd;
    }

    public String getAllocNum() {
        return allocNum;
    }

    public void setAllocNum(String allocNum) {
        this.allocNum = allocNum;
    }

    public String getAllocCount() {
        return allocCount;
    }

    public void setAllocCount(String allocCount) {
        this.allocCount = allocCount;
    }

    public String getAllocValidFrom() {
        return allocValidFrom;
    }

    public void setAllocValidFrom(String allocValidFrom) {
        this.allocValidFrom = allocValidFrom;
    }

    public String getChgNum() {
        return chgNum;
    }

    public void setChgNum(String chgNum) {
        this.chgNum = chgNum;
    }

    public String getDelInd() {
        return delInd;
    }

    public void setDelInd(String delInd) {
        this.delInd = delInd;
    }

    public String getRtngGrpCntrNbr() {
        return rtngGrpCntrNbr;
    }

    public void setRtngGrpCntrNbr(String rtngGrpCntrNbr) {
        this.rtngGrpCntrNbr = rtngGrpCntrNbr;
    }

    public String getRtngSqncNum() {
        return rtngSqncNum;
    }

    public void setRtngSqncNum(String rtngSqncNum) {
        this.rtngSqncNum = rtngSqncNum;
    }

    public String getRtngNdeNum() {
        return rtngNdeNum;
    }

    public void setRtngNdeNum(String rtngNdeNum) {
        this.rtngNdeNum = rtngNdeNum;
    }

    public String getBomCatCd() {
        return bomCatCd;
    }

    public void setBomCatCd(String bomCatCd) {
        this.bomCatCd = bomCatCd;
    }

    public String getBomNum() {
        return bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getAltBomNum() {
        return altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getBomItmNdeNum() {
        return bomItmNdeNum;
    }

    public void setBomItmNdeNum(String bomItmNdeNum) {
        this.bomItmNdeNum = bomItmNdeNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getAllocValidTo() {
        return allocValidTo;
    }

    public void setAllocValidTo(String allocValidTo) {
        this.allocValidTo = allocValidTo;
    }


}

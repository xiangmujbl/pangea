package com.jnj.pangea.edm.matl_bom.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class EDMMatlBomBo extends BaseBo {

    private String srcSysCd;
    private String matlNum;
    private String plntCd;
    private String bomUsgCd;
    private String bomNum;
    private String altBomNum;
    private String fromLotSizeQty;
    private String toLotSizeQty;
    private String crtDttm;
    private String chgDttm;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("srcSysCd", srcSysCd)
                .add("matlNum",matlNum)
                .add("plntCd",plntCd)
                .add("bomUsgCd",bomUsgCd)
                .add("bomNum",bomNum)
                .add("altBomNum",altBomNum)
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

    public String getBomUsgCd() {
        return this.bomUsgCd;
    }

    public void setBomUsgCd(String bomUsgCd) {
        this.bomUsgCd = bomUsgCd;
    }

    public String getBomNum() {
        return this.bomNum;
    }

    public void setBomNum(String bomNum) {
        this.bomNum = bomNum;
    }

    public String getAltBomNum() {
        return this.altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getFromLotSizeQty() {
        return this.fromLotSizeQty;
    }

    public void setFromLotSizeQty(String fromLotSizeQty) {
        this.fromLotSizeQty = fromLotSizeQty;
    }

    public String getToLotSizeQty() {
        return this.toLotSizeQty;
    }

    public void setToLotSizeQty(String toLotSizeQty) {
        this.toLotSizeQty = toLotSizeQty;
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

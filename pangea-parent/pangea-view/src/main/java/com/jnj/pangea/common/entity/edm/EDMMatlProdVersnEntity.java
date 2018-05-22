package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMatlProdVersnEntity extends CommonEntity {

    private String srcSysCd;
    private String plntCd;
    private String altBomNum;
    private String matlNum;
    private String rtngGrpCntrNum;
    private String rtngGrpCd;
    private String prdntVrsnNum;
    private String dstrbtnKeyCd;
    private String valToDt;
    private String valFromDt;

    public EDMMatlProdVersnEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setPlntCd((String) map.get("plntCd"));
        setAltBomNum((String) map.get("altBomNum"));
        setMatlNum((String) map.get("matlNum"));
        setRtngGrpCntrNum((String) map.get("rtngGrpCntrNum"));
        setRtngGrpCd((String) map.get("rtngGrpCd"));
        setPrdntVrsnNum((String) map.get("prdntVrsnNum"));
        setDstrbtnKeyCd((String) map.get("dstrbtnKeyCd"));
        setValToDt((String) map.get("valToDt"));
        setValFromDt((String) map.get("valFromDt"));
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getAltBomNum() {
        return this.altBomNum;
    }

    public void setAltBomNum(String altBomNum) {
        this.altBomNum = altBomNum;
    }

    public String getMatlNum() {
        return this.matlNum;
    }

    public void setMatlNum(String matlNum) {
        this.matlNum = matlNum;
    }

    public String getRtngGrpCntrNum() {
        return this.rtngGrpCntrNum;
    }

    public void setRtngGrpCntrNum(String rtngGrpCntrNum) {
        this.rtngGrpCntrNum = rtngGrpCntrNum;
    }

    public String getRtngGrpCd() {
        return this.rtngGrpCd;
    }

    public void setRtngGrpCd(String rtngGrpCd) {
        this.rtngGrpCd = rtngGrpCd;
    }

    public String getPrdntVrsnNum() {
        return this.prdntVrsnNum;
    }

    public void setPrdntVrsnNum(String prdntVrsnNum) {
        this.prdntVrsnNum = prdntVrsnNum;
    }

    public String getDstrbtnKeyCd() {
        return this.dstrbtnKeyCd;
    }

    public void setDstrbtnKeyCd(String dstrbtnKeyCd) {
        this.dstrbtnKeyCd = dstrbtnKeyCd;
    }

    public String getValToDt() {
        return this.valToDt;
    }

    public void setValToDt(String valToDt) {
        this.valToDt = valToDt;
    }

    public String getValFromDt() {
        return this.valFromDt;
    }

    public void setValFromDt(String valFromDt) {
        this.valFromDt = valFromDt;
    }

}

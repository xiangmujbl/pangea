package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgOrderEntity extends CommonEntity {

    private String sourceSysCd;
    private String mfgOrdrNum;
    private String rsrvtnNum;
    private String ordrRtngNum;
    private String bomCatCd;
    private String bomNum;
    private String actRlseDt;
    private String srcSysCd;
    private String plntCd;
    private String primaryPlanningCode;
    private String mfgOrdrSttsCd;
    private String rtngSqncNum;

    public EDMMfgOrderEntity(Map<String, Object> map) {
        super(map);

        setSourceSysCd((String) map.get("sourceSysCd"));
        setMfgOrdrNum((String) map.get("mfgOrdrNum"));
        setRsrvtnNum((String) map.get("rsrvtnNum"));
        setOrdrRtngNum((String) map.get("ordrRtngNum"));
        setBomCatCd((String) map.get("bomCatCd"));
        setBomNum((String) map.get("bomNum"));
        setActRlseDt ((String) map.get("actRlseDt"));
        setSrcSysCd((String) map.get("srcSysCd"));
        setPlntCd((String) map.get("plntCd"));
        setPrimaryPlanningCode((String) map.get("primaryPlanningCode"));
        setMfgOrdrSttsCd((String) map.get("mfgOrdrSttsCd"));
        setRtngSqncNum((String) map.get("rtngSqncNum"));
    }

    public String getPrimaryPlanningCode() {
        return primaryPlanningCode;
    }

    public void setPrimaryPlanningCode(String primaryPlanningCode) {
        this.primaryPlanningCode = primaryPlanningCode;
    }

    public String getBomCatCd() {
        return bomCatCd;
    }

    public String getActRlseDt() {
        return actRlseDt;
    }

    public void setActRlseDt(String actRlseDt) {
        this.actRlseDt = actRlseDt;
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

    public String getSourceSysCd() {
        return sourceSysCd;
    }

    public void setSourceSysCd(String sourceSysCd) {
        this.sourceSysCd = sourceSysCd;
    }

    public String getMfgOrdrNum() {
        return this.mfgOrdrNum;
    }

    public void setMfgOrdrNum(String mfgOrdrNum) {
        this.mfgOrdrNum = mfgOrdrNum;
    }

    public String getRsrvtnNum() {
        return this.rsrvtnNum;
    }

    public void setRsrvtnNum(String rsrvtnNum) {
        this.rsrvtnNum = rsrvtnNum;
    }

    public String getOrdrRtngNum() {
        return this.ordrRtngNum;
    }

    public void setOrdrRtngNum(String ordrRtngNum) {
        this.ordrRtngNum = ordrRtngNum;
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }

    public String getMfgOrdrSttsCd() {
        return mfgOrdrSttsCd;
    }

    public void setMfgOrdrSttsCd(String mfgOrdrSttsCd) {
        this.mfgOrdrSttsCd = mfgOrdrSttsCd;
    }

    public String getRtngSqncNum() {
        return rtngSqncNum;
    }

    public void setRtngSqncNum(String rtngSqncNum) {
        this.rtngSqncNum = rtngSqncNum;
    }
}

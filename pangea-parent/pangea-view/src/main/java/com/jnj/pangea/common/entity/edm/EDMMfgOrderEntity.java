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
    public EDMMfgOrderEntity(Map<String, Object> map) {
        super(map);

        setSourceSysCd((String) map.get("sourceSysCd"));
        setMfgOrdrNum((String) map.get("mfgOrdrNum"));
        setRsrvtnNum((String) map.get("rsrvtnNum"));
        setOrdrRtngNum((String) map.get("ordrRtngNum"));
        setBomCatCd((String) map.get("bomCatCd"));
        setBomNum((String) map.get("bomNum"));
        setActRlseDt ((String) map.get("actRlseDt"));


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

}

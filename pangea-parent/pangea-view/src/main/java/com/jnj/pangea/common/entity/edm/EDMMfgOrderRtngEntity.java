package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgOrderRtngEntity extends CommonEntity {

    private String srcSysCd;
    private String ordrRtngNum;
    private String operNum;

    public EDMMfgOrderRtngEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setOrdrRtngNum((String) map.get("ordrRtngNum"));
        setOperNum((String) map.get("operNum"));
    }

    public String getOperNum() {
        return operNum;
    }

    public void setOperNum(String operNum) {
        this.operNum = operNum;
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getOrdrRtngNum() {
        return this.ordrRtngNum;
    }

    public void setOrdrRtngNum(String ordrRtngNum) {
        this.ordrRtngNum = ordrRtngNum;
    }

}

package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgRtngItmEntity extends CommonEntity {

    private String rtngTypCd;
    private String rtngItmNum;
    private String srcSysCd;
    private String rtngGrpCd;
    private String operNum;

    public EDMMfgRtngItmEntity(Map<String, Object> map) {
        super(map);

        setRtngTypCd((String) map.get("rtngTypCd"));
        setRtngItmNum((String) map.get("rtngItmNum"));
        setSrcSysCd((String) map.get("srcSysCd"));
        setRtngGrpCd((String) map.get("rtngGrpCd"));
        setOperNum((String) map.get("operNum"));
    }

    public String getRtngTypCd() {
        return this.rtngTypCd;
    }

    public void setRtngTypCd(String rtngTypCd) {
        this.rtngTypCd = rtngTypCd;
    }

    public String getRtngItmNum() {
        return this.rtngItmNum;
    }

    public void setRtngItmNum(String rtngItmNum) {
        this.rtngItmNum = rtngItmNum;
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getRtngGrpCd() {
        return this.rtngGrpCd;
    }

    public void setRtngGrpCd(String rtngGrpCd) {
        this.rtngGrpCd = rtngGrpCd;
    }

    public String getOperNum() {
        return this.operNum;
    }

    public void setOperNum(String operNum) {
        this.operNum = operNum;
    }

}

package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgOrderRtngEntity extends CommonEntity {

    private String srcSysCd;
    private String ordrRtngNum;
    private String operNum;
    private String operCd;
    private String wrkCntrId;
    private String oprQty;

    @Override
    public String toString() {
        return "EDMMfgOrderRtngEntity{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", ordrRtngNum='" + ordrRtngNum + '\'' +
                ", operNum='" + operNum + '\'' +
                ", operCd='" + operCd + '\'' +
                ", wrkCntrId='" + wrkCntrId + '\'' +
                ", oprQty='" + oprQty + '\'' +
                '}';
    }

    public EDMMfgOrderRtngEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setOrdrRtngNum((String) map.get("ordrRtngNum"));
        setOperNum((String) map.get("operNum"));
        setOperCd((String) map.get("operCd"));
        setWrkCntrId((String) map.get("wrkCntrId"));
        setOprQty((String) map.get("oprQty"));
    }

    public String getOperCd() {
        return operCd;
    }

    public void setOperCd(String operCd) {
        this.operCd = operCd;
    }

    public String getWrkCntrId() {
        return wrkCntrId;
    }

    public void setWrkCntrId(String wrkCntrId) {
        this.wrkCntrId = wrkCntrId;
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

    public String getOprQty() {
        return oprQty;
    }

    public void setOprQty(String oprQty) {
        this.oprQty = oprQty;
    }
}

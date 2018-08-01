package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgOrderSeqEntity extends CommonEntity {

    private String srcSysCd;
    private String ordrRtngNum;
    private String rtngSqncNum;
    private String toLtSzQty;
    private String fromLtSzQty;
    private String rtngSqncCtgryCd;

    public EDMMfgOrderSeqEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setOrdrRtngNum((String) map.get("ordrRtngNum"));
        setRtngSqncNum((String) map.get("rtngSqncNum"));
        setToLtSzQty((String) map.get("toLtSzQty"));
        setFromLtSzQty((String) map.get("fromLtSzQty"));
        setRtngSqncCtgryCd((String) map.get("rtngSqncCtgryCd"));
    }

    public String getRtngSqncCtgryCd() {
        return rtngSqncCtgryCd;
    }

    public void setRtngSqncCtgryCd(String rtngSqncCtgryCd) {
        this.rtngSqncCtgryCd = rtngSqncCtgryCd;
    }

    public String getRtngSqncNum() {
        return rtngSqncNum;
    }

    public void setRtngSqncNum(String rtngSqncNum) {
        this.rtngSqncNum = rtngSqncNum;
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

    public String getToLtSzQty() {
        return toLtSzQty;
    }

    public void setToLtSzQty(String toLtSzQty) {
        this.toLtSzQty = toLtSzQty;
    }

    public String getFromLtSzQty() {
        return fromLtSzQty;
    }

    public void setFromLtSzQty(String fromLtSzQty) {
        this.fromLtSzQty = fromLtSzQty;
    }
}

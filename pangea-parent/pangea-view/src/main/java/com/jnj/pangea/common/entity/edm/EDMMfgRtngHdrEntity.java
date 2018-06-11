package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgRtngHdrEntity extends CommonEntity {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngGrpCd;
    private String rtngGrpCntrNbr;
    private String toLtSzQty;

    public EDMMfgRtngHdrEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setRtngTypCd((String) map.get("rtngTypCd"));
        setRtngGrpCd((String) map.get("rtngGrpCd"));
        setRtngGrpCntrNbr((String) map.get("rtngGrpCntrNbr"));
        setToLtSzQty((String) map.get("toLtSzQty"));
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getRtngTypCd() {
        return this.rtngTypCd;
    }

    public void setRtngTypCd(String rtngTypCd) {
        this.rtngTypCd = rtngTypCd;
    }

    public String getRtngGrpCd() {
        return this.rtngGrpCd;
    }

    public void setRtngGrpCd(String rtngGrpCd) {
        this.rtngGrpCd = rtngGrpCd;
    }

    public String getRtngGrpCntrNbr() {
        return this.rtngGrpCntrNbr;
    }

    public void setRtngGrpCntrNbr(String rtngGrpCntrNbr) {
        this.rtngGrpCntrNbr = rtngGrpCntrNbr;
    }

    public String getToLtSzQty() {
        return this.toLtSzQty;
    }

    public void setToLtSzQty(String toLtSzQty) {
        this.toLtSzQty = toLtSzQty;
    }

}

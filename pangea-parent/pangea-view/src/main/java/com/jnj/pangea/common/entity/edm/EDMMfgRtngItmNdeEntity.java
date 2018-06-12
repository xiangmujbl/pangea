package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgRtngItmNdeEntity extends CommonEntity {

    private String srcSysCd;
    private String rtngTypCd;
    private String rtngNdeNum;
    private String rtngGrpCntrNbr;
    private String rtngGrpCd;

    public EDMMfgRtngItmNdeEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setRtngTypCd((String) map.get("rtngTypCd"));
        setRtngNdeNum((String) map.get("rtngNdeNum"));
        setRtngGrpCntrNbr((String) map.get("rtngGrpCntrNbr"));
        setRtngGrpCd((String) map.get("rtngGrpCd"));
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

    public String getRtngNdeNum() {
        return this.rtngNdeNum;
    }

    public void setRtngNdeNum(String rtngNdeNum) {
        this.rtngNdeNum = rtngNdeNum;
    }

    public String getRtngGrpCntrNbr() {
        return this.rtngGrpCntrNbr;
    }

    public void setRtngGrpCntrNbr(String rtngGrpCntrNbr) {
        this.rtngGrpCntrNbr = rtngGrpCntrNbr;
    }

    public String getRtngGrpCd() {
        return this.rtngGrpCd;
    }

    public void setRtngGrpCd(String rtngGrpCd) {
        this.rtngGrpCd = rtngGrpCd;
    }

    @Override
    public String toString() {
        return "EDMMfgRtngItmNdeEntity{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", rtngTypCd='" + rtngTypCd + '\'' +
                ", rtngNdeNum='" + rtngNdeNum + '\'' +
                ", rtngGrpCntrNbr='" + rtngGrpCntrNbr + '\'' +
                ", rtngGrpCd='" + rtngGrpCd + '\'' +
                '}';
    }
}

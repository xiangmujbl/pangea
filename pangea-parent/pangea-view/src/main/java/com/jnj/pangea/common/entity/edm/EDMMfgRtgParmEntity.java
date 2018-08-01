package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMMfgRtgParmEntity extends CommonEntity {

    private String srcSysCd;
    private String rtgTypeCd;
    private String rtgGrpCd;
    private String rtgNodeNum;
    private String charCd;
    private String charVal;
    private String intrnlPrcsInstrNum;

    @Override
    public String toString() {
        return "EDMMfgRtgParmEntity{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", rtgTypeCd='" + rtgTypeCd + '\'' +
                ", rtgGrpCd='" + rtgGrpCd + '\'' +
                ", rtgNodeNum='" + rtgNodeNum + '\'' +
                ", charCd='" + charCd + '\'' +
                ", charVal='" + charVal + '\'' +
                ", intrnlPrcsInstrNum='" + intrnlPrcsInstrNum + '\'' +
                '}';
    }

    public EDMMfgRtgParmEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setRtgTypeCd((String) map.get("rtgTypeCd"));
        setRtgGrpCd((String) map.get("rtgGrpCd"));
        setRtgNodeNum((String) map.get("rtgNodeNum"));
        setCharCd((String) map.get("charCd"));
        setCharVal((String) map.get("charVal"));
        setIntrnlPrcsInstrNum((String) map.get("intrnlPrcsInstrNum"));
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getRtgTypeCd() {
        return rtgTypeCd;
    }

    public void setRtgTypeCd(String rtgTypeCd) {
        this.rtgTypeCd = rtgTypeCd;
    }

    public String getRtgGrpCd() {
        return rtgGrpCd;
    }

    public void setRtgGrpCd(String rtgGrpCd) {
        this.rtgGrpCd = rtgGrpCd;
    }

    public String getRtgNodeNum() {
        return rtgNodeNum;
    }

    public void setRtgNodeNum(String rtgNodeNum) {
        this.rtgNodeNum = rtgNodeNum;
    }

    public String getCharCd() {
        return charCd;
    }

    public void setCharCd(String charCd) {
        this.charCd = charCd;
    }

    public String getCharVal() {
        return charVal;
    }

    public void setCharVal(String charVal) {
        this.charVal = charVal;
    }

    public String getIntrnlPrcsInstrNum() {
        return intrnlPrcsInstrNum;
    }

    public void setIntrnlPrcsInstrNum(String intrnlPrcsInstrNum) {
        this.intrnlPrcsInstrNum = intrnlPrcsInstrNum;
    }
}

package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMWrkCtrEntity extends CommonEntity {

    private String srcSysCd;
    private String capyNum;
    private String wrkCtrUsgCd;
    private String wrkCtrCd;
    private String wrkCtrTypeCd;
    private String wrkCtrNum;
    private String plntCd;

    public EDMWrkCtrEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setCapyNum((String) map.get("capyNum"));
        setWrkCtrUsgCd((String) map.get("wrkCtrUsgCd"));
        setWrkCtrCd((String) map.get("wrkCtrCd"));
        setWrkCtrTypeCd((String) map.get("wrkCtrTypeCd"));
        setWrkCtrNum((String) map.get("wrkCtrNum"));
        setPlntCd((String) map.get("plntCd"));
    }

    @Override
    public String toString() {
        return "EDMWrkCtrEntity{" +
                "srcSysCd='" + srcSysCd + '\'' +
                ", capyNum='" + capyNum + '\'' +
                ", wrkCtrUsgCd='" + wrkCtrUsgCd + '\'' +
                ", wrkCtrCd='" + wrkCtrCd + '\'' +
                ", wrkCtrTypeCd='" + wrkCtrTypeCd + '\'' +
                ", wrkCtrNum='" + wrkCtrNum + '\'' +
                ", plntCd='" + plntCd + '\'' +
                '}';
    }

    public String getSrcSysCd() {
        return this.srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getCapyNum() {
        return this.capyNum;
    }

    public void setCapyNum(String capyNum) {
        this.capyNum = capyNum;
    }

    public String getWrkCtrUsgCd() {
        return this.wrkCtrUsgCd;
    }

    public void setWrkCtrUsgCd(String wrkCtrUsgCd) {
        this.wrkCtrUsgCd = wrkCtrUsgCd;
    }

    public String getWrkCtrCd() {
        return this.wrkCtrCd;
    }

    public void setWrkCtrCd(String wrkCtrCd) {
        this.wrkCtrCd = wrkCtrCd;
    }

    public String getWrkCtrTypeCd() {
        return this.wrkCtrTypeCd;
    }

    public void setWrkCtrTypeCd(String wrkCtrTypeCd) {
        this.wrkCtrTypeCd = wrkCtrTypeCd;
    }

    public String getWrkCtrNum() {
        return this.wrkCtrNum;
    }

    public void setWrkCtrNum(String wrkCtrNum) {
        this.wrkCtrNum = wrkCtrNum;
    }

    public String getPlntCd() {
        return plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
    }
}

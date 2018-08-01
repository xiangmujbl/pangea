package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMWrkCtrCapyEntity extends CommonEntity {

    private String srcSysCd;
    private String wrkCtrTypeCd;
    private String wrkCtrNum;
    private String capyAllcNbr;
    private String capyNum;

    public EDMWrkCtrCapyEntity(Map<String, Object> map) {
        super(map);

        setSrcSysCd((String) map.get("srcSysCd"));
        setCapyNum((String) map.get("capyNum"));
        setWrkCtrTypeCd((String) map.get("wrkCtrTypeCd"));
        setWrkCtrNum((String) map.get("wrkCtrNum"));
        setCapyAllcNbr((String) map.get("capyAllcNbr"));
    }

    public String getSrcSysCd() {
        return srcSysCd;
    }

    public void setSrcSysCd(String srcSysCd) {
        this.srcSysCd = srcSysCd;
    }

    public String getWrkCtrTypeCd() {
        return wrkCtrTypeCd;
    }

    public void setWrkCtrTypeCd(String wrkCtrTypeCd) {
        this.wrkCtrTypeCd = wrkCtrTypeCd;
    }

    public String getWrkCtrNum() {
        return wrkCtrNum;
    }

    public void setWrkCtrNum(String wrkCtrNum) {
        this.wrkCtrNum = wrkCtrNum;
    }

    public String getCapyAllcNbr() {
        return capyAllcNbr;
    }

    public void setCapyAllcNbr(String capyAllcNbr) {
        this.capyAllcNbr = capyAllcNbr;
    }

    public String getCapyNum() {
        return capyNum;
    }

    public void setCapyNum(String capyNum) {
        this.capyNum = capyNum;
    }
}

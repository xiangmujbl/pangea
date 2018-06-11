package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMCapyHdrEntity extends CommonEntity {

    private String capyDesc;
    private String plntCd;
    private String srcSysCd;
    private String capyNum;
    private String capyCatCd;

    public EDMCapyHdrEntity(Map<String, Object> map) {
        super(map);

        setCapyDesc((String) map.get("capyDesc"));
        setPlntCd((String) map.get("plntCd"));
        setSrcSysCd((String) map.get("srcSysCd"));
        setCapyNum((String) map.get("capyNum"));
        setCapyCatCd((String) map.get("capyCatCd"));
    }

    public String getCapyDesc() {
        return this.capyDesc;
    }

    public void setCapyDesc(String capyDesc) {
        this.capyDesc = capyDesc;
    }

    public String getPlntCd() {
        return this.plntCd;
    }

    public void setPlntCd(String plntCd) {
        this.plntCd = plntCd;
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

    public String getCapyCatCd() {
        return this.capyCatCd;
    }

    public void setCapyCatCd(String capyCatCd) {
        this.capyCatCd = capyCatCd;
    }

}

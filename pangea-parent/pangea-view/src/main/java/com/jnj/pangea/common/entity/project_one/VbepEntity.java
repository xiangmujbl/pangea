package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class VbepEntity extends CommonEntity {

    private String vbeln;
    private String posnr;
    private String etenr;
    private String edatu;
    private String wmeng;
    private String bmeng;

    public VbepEntity(Map<String, Object> map) {
        super(map);
        setVbeln((String) map.get("vbeln"));
        setPosnr((String) map.get("posnr"));
        setEtenr((String) map.get("etenr"));
        setEdatu((String) map.get("edatu"));
        setWmeng((String) map.get("wmeng"));
        setBmeng((String) map.get("bmeng"));

    }

    public String getVbeln() {
        return vbeln;
    }

    public void setVbeln(String vbeln) {
        this.vbeln = vbeln;
    }

    public String getPosnr() {
        return posnr;
    }

    public void setPosnr(String posnr) {
        this.posnr = posnr;
    }

    public String getEtenr() {
        return etenr;
    }

    public void setEtenr(String etenr) {
        this.etenr = etenr;
    }

    public String getEdatu() {
        return edatu;
    }

    public void setEdatu(String edatu) {
        this.edatu = edatu;
    }

    public String getWmeng() {
        return wmeng;
    }

    public void setWmeng(String wmeng) {
        this.wmeng = wmeng;
    }

    public String getBmeng() {
        return bmeng;
    }

    public void setBmeng(String bmeng) {
        this.bmeng = bmeng;
    }
}

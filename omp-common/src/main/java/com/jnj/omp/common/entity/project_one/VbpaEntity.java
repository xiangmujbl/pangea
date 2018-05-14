package com.jnj.omp.common.entity.project_one;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class VbpaEntity extends CommonEntity {

    private String vbeln;
    private String posnr;
    private String parvw;
    private String kunnr;

    public VbpaEntity(Map<String, Object> map) {
        super(map);

        setVbeln((String) map.get("vbeln"));
        setPosnr((String) map.get("posnr"));
        setParvw((String) map.get("parvw"));
        setKunnr((String) map.get("kunnr"));
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

    public String getParvw() {
        return parvw;
    }

    public void setParvw(String parvw) {
        this.parvw = parvw;
    }

    public String getKunnr() {
        return kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }
}

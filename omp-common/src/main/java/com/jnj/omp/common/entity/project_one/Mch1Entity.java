package com.jnj.omp.common.entity.project_one;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class Mch1Entity extends CommonEntity {

    private String matnr;
    private String charg;
    private String vfdat;
    private String hsdat;

    @Override
    public String toString() {
        return "Mch1Entity{" +
                "matnr='" + matnr + '\'' +
                ", charg='" + charg + '\'' +
                ", vfdat='" + vfdat + '\'' +
                ", hsdat='" + hsdat + '\'' +
                '}';
    }

    public Mch1Entity(Map<String, Object> map) {
        super(map);

        setMatnr((String) map.get("matnr"));
        setCharg((String) map.get("charg"));
        setVfdat((String) map.get("vfdat"));
        setHsdat((String) map.get("hsdat"));
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getCharg() {
        return charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getVfdat() {
        return vfdat;
    }

    public void setVfdat(String vfdat) {
        this.vfdat = vfdat;
    }

    public String getHsdat() {
        return hsdat;
    }

    public void setHsdat(String hsdat) {
        this.hsdat = hsdat;
    }
}

package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MaraEntity extends CommonEntity {

    private String matnr;
    private String mtart;
    private String meins;
    private String matkl;
    private String lvorm;
    private String mstae;
    private String spart;
    private String xchpf;
    private String mhdrz;
    private String mhdhb;

    public MaraEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String) map.get("matnr"));
        setMtart((String) map.get("mtart"));
        setMeins((String) map.get("meins"));
        setMatkl((String) map.get("matkl"));
        setLvorm((String) map.get("lvorm"));
        setMstae((String) map.get("mstae"));
        setSpart((String) map.get("spart"));
        setXchpf((String) map.get("xchpf"));
        setMhdrz((String) map.get("mhdrz"));
        setMhdhb((String) map.get("mhdhb"));
    }


    public String getXchpf() {
        return xchpf;
    }

    public void setXchpf(String xchpf) {
        this.xchpf = xchpf;
    }

    public String getMhdrz() {
        return mhdrz;
    }

    public void setMhdrz(String mhdrz) {
        this.mhdrz = mhdrz;
    }

    public String getMhdhb() {
        return mhdhb;
    }

    public void setMhdhb(String mhdhb) {
        this.mhdhb = mhdhb;
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMtart() {
        return mtart;
    }

    public void setMtart(String mtart) {
        this.mtart = mtart;
    }

    public String getMeins() {
        return meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getMatkl() {
        return matkl;
    }

    public void setMatkl(String matkl) {
        this.matkl = matkl;
    }

    public String getLvorm() {
        return lvorm;
    }

    public void setLvorm(String lvorm) {
        this.lvorm = lvorm;
    }

    public String getMstae() {
        return mstae;
    }

    public void setMstae(String mstae) {
        this.mstae = mstae;
    }

    public String getSpart() {
        return spart;
    }

    public void setSpart(String spart) {
        this.spart = spart;
    }
}

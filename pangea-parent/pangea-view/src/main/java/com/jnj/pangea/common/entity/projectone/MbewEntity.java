package com.jnj.pangea.common.entity.projectone;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MbewEntity extends CommonEntity{
    private String matnr;
    private String bwkey;
    private String lvorm;
    private String vprsv;
    private String stprs;
    private String peinh;
    private String verpr;

    public MbewEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String)map.get("matnr"));
        setBwkey((String)map.get("bwkey"));
        setLvorm((String)map.get("lvorm"));
        setVprsv((String)map.get("vprsv"));
        setStprs((String)map.get("stprs"));
        setPeinh((String)map.get("peinh"));
        setVerpr((String)map.get("verpr"));

    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getBwkey() {
        return bwkey;
    }

    public void setBwkey(String bwkey) {
        this.bwkey = bwkey;
    }

    public String getLvorm() {
        return lvorm;
    }

    public void setLvorm(String lvorm) {
        this.lvorm = lvorm;
    }

    public String getVprsv() {
        return vprsv;
    }

    public void setVprsv(String vprsv) {
        this.vprsv = vprsv;
    }

    public String getStprs() {
        return stprs;
    }

    public void setStprs(String stprs) {
        this.stprs = stprs;
    }

    public String getPeinh() {
        return peinh;
    }

    public void setPeinh(String peinh) {
        this.peinh = peinh;
    }

    public String getVerpr() {
        return verpr;
    }

    public void setVerpr(String verpr) {
        this.verpr = verpr;
    }
}

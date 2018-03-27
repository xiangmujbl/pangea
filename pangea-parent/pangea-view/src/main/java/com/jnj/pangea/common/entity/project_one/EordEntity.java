package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EordEntity extends CommonEntity {
    private String matnr;
    private String werks;
    private String zeord;
    private String erdat;
    private String ernam;
    private String vdatu;
    private String bdatu;
    private String lifnr;
    private String flifn;
    private String ebeln;
    private String ebelp;
    private String febel;
    private String reswk;
    private String ematn;
    private String notkz;
    private String ekorg;
    private String vrtyp;
    private String eortp;
    private String autet;

    @Override
    public String toString() {
        return "EordEntity{" +
                "matnr='" + matnr + '\'' +
                ", werks='" + werks + '\'' +
                ", zeord='" + zeord + '\'' +
                ", erdat='" + erdat + '\'' +
                ", ernam='" + ernam + '\'' +
                ", vdatu='" + vdatu + '\'' +
                ", bdatu='" + bdatu + '\'' +
                ", lifnr='" + lifnr + '\'' +
                ", flifn='" + flifn + '\'' +
                ", ebeln='" + ebeln + '\'' +
                ", ebelp='" + ebelp + '\'' +
                ", febel='" + febel + '\'' +
                ", reswk='" + reswk + '\'' +
                ", ematn='" + ematn + '\'' +
                ", notkz='" + notkz + '\'' +
                ", ekorg='" + ekorg + '\'' +
                ", vrtyp='" + vrtyp + '\'' +
                ", eortp='" + eortp + '\'' +
                ", autet='" + autet + '\'' +
                '}';
    }

    public EordEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String)map.get("matnr"));
        setWerks((String)map.get("werks"));
        setZeord((String)map.get("zeord"));
        setErdat((String)map.get("erdat"));
        setErnam((String)map.get("ernam"));
        setVdatu((String)map.get("vdatu"));
        setBdatu((String)map.get("bdatu"));
        setLifnr((String)map.get("lifnr"));
        setFlifn((String)map.get("flifn"));
        setEbeln((String)map.get("ebeln"));
        setEbelp((String)map.get("ebelp"));
        setFebel((String)map.get("febel"));
        setReswk((String)map.get("reswk"));
        setEmatn((String)map.get("ematn"));
        setNotkz((String)map.get("notkz"));
        setEkorg((String)map.get("ekorg"));
        setVrtyp((String)map.get("vrtyp"));
        setEortp((String)map.get("eortp"));
        setAutet((String)map.get("autet"));
    }

    public String getMatnr() {
        return matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getWerks() {
        return werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getZeord() {
        return zeord;
    }

    public void setZeord(String zeord) {
        this.zeord = zeord;
    }



    public String getErdat() {
        return erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }

    public String getErnam() {
        return ernam;
    }

    public void setErnam(String ernam) {
        this.ernam = ernam;
    }

    public String getVdatu() {
        return vdatu;
    }

    public void setVdatu(String vdatu) {
        this.vdatu = vdatu;
    }

    public String getBdatu() {
        return bdatu;
    }

    public void setBdatu(String bdatu) {
        this.bdatu = bdatu;
    }

    public String getLifnr() {
        return lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getFlifn() {
        return flifn;
    }

    public void setFlifn(String flifn) {
        this.flifn = flifn;
    }

    public String getEbeln() {
        return ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getEbelp() {
        return ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public String getFebel() {
        return febel;
    }

    public void setFebel(String febel) {
        this.febel = febel;
    }

    public String getReswk() {
        return reswk;
    }

    public void setReswk(String reswk) {
        this.reswk = reswk;
    }

    public String getEmatn() {
        return ematn;
    }

    public void setEmatn(String ematn) {
        this.ematn = ematn;
    }

    public String getNotkz() {
        return notkz;
    }

    public void setNotkz(String notkz) {
        this.notkz = notkz;
    }

    public String getEkorg() {
        return ekorg;
    }

    public void setEkorg(String ekorg) {
        this.ekorg = ekorg;
    }

    public String getVrtyp() {
        return vrtyp;
    }

    public void setVrtyp(String vrtyp) {
        this.vrtyp = vrtyp;
    }

    public String getEortp() {
        return eortp;
    }

    public void setEortp(String eortp) {
        this.eortp = eortp;
    }

    public String getAutet() {
        return autet;
    }

    public void setAutet(String autet) {
        this.autet = autet;
    }
}

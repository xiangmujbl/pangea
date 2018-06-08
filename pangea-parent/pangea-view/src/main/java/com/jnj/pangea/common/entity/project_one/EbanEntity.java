package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EbanEntity extends CommonEntity {

    private String banfn;
    private String bnfpo;
    private String werks;
    private String matnr;
    private String meins;
    private String bsart;
    private String bstyp;
    private String bsakz;
    private String loekz;
    private String statu;
    private String estkz;
    private String ekgrp;
    private String ernam;
    private String erdat;
    private String txz01;
    private String ematn;
    private String lgort;
    private String bednr;
    private String matkl;
    private String reswk;
    private String menge;
    private String badat;
    private String lfdat;
    private String frgdt;
    private String webaz;
    private String pstyp;
    private String knttp;
    private String wepos;
    private String lifnr;
    private String flief;
    private String ekorg;
    private String vrtyp;
    private String konnr;
    private String ktpnr;
    private String infnr;
    private String zugba;
    private String qunum;
    private String qupos;
    private String dispo;
    private String sernr;
    private String ebeln;
    private String ebelp;
    private String bedat;
    private String bsmng;
    private String ebakz;
    private String rsnum;
    private String sobkz;
    private String fixkz;
    private String bmein;
    private String frgrl;
    private String charg;
    private String umsok;
    private String verid;
    private String adrnr;
    private String adrn2;
    private String kunnr;
    private String emlif;
    private String lblkz;
    private String waers;
    private String gsfrg;
    private String mfrpn;
    private String mfrnr;
    private String emnfr;
    private String plifz;
    private String berid;
    private String memory;
    private String banpr;
    private String blckd;
    private String blckt;
    private String beswk;
    private String reslo;
    private String banfnCs;
    private String bnfpoCs;
    private String itemCs;

    public EbanEntity(Map<String, Object> map) {
        super(map);

        setBanfn((String) map.get("banfn"));
        setBnfpo((String) map.get("bnfpo"));
        setWerks((String) map.get("werks"));
        setMatnr((String) map.get("matnr"));
        setMeins((String) map.get("meins"));
        setBsart((String) map.get("bsart"));
        setBstyp((String) map.get("bstyp"));
        setBsakz((String) map.get("bsakz"));
        setLoekz((String) map.get("loekz"));
        setStatu((String) map.get("statu"));
        setEstkz((String) map.get("estkz"));
        setEkgrp((String) map.get("ekgrp"));
        setErnam((String) map.get("ernam"));
        setErdat((String) map.get("erdat"));
        setTxz01((String) map.get("txz01"));
        setEmatn((String) map.get("ematn"));
        setLgort((String) map.get("lgort"));
        setBednr((String) map.get("bednr"));
        setMatkl((String) map.get("matkl"));
        setReswk((String) map.get("reswk"));
        setMenge((String) map.get("menge"));
        setBadat((String) map.get("badat"));
        setLfdat((String) map.get("lfdat"));
        setFrgdt((String) map.get("frgdt"));
        setWebaz((String) map.get("webaz"));
        setPstyp((String) map.get("pstyp"));
        setKnttp((String) map.get("knttp"));
        setWepos((String) map.get("wepos"));
        setLifnr((String) map.get("lifnr"));
        setFlief((String) map.get("flief"));
        setEkorg((String) map.get("ekorg"));
        setVrtyp((String) map.get("vrtyp"));
        setKonnr((String) map.get("konnr"));
        setKtpnr((String) map.get("ktpnr"));
        setInfnr((String) map.get("infnr"));
        setZugba((String) map.get("zugba"));
        setQunum((String) map.get("qunum"));
        setQupos((String) map.get("qupos"));
        setDispo((String) map.get("dispo"));
        setSernr((String) map.get("sernr"));
        setEbeln((String) map.get("ebeln"));
        setEbelp((String) map.get("ebelp"));
        setBedat((String) map.get("bedat"));
        setBsmng((String) map.get("bsmng"));
        setEbakz((String) map.get("ebakz"));
        setRsnum((String) map.get("rsnum"));
        setSobkz((String) map.get("sobkz"));
        setFixkz((String) map.get("fixkz"));
        setBmein((String) map.get("bmein"));
        setFrgrl((String) map.get("frgrl"));
        setCharg((String) map.get("charg"));
        setUmsok((String) map.get("umsok"));
        setVerid((String) map.get("verid"));
        setAdrnr((String) map.get("adrnr"));
        setAdrn2((String) map.get("adrn2"));
        setKunnr((String) map.get("kunnr"));
        setEmlif((String) map.get("emlif"));
        setLblkz((String) map.get("lblkz"));
        setWaers((String) map.get("waers"));
        setGsfrg((String) map.get("gsfrg"));
        setMfrpn((String) map.get("mfrpn"));
        setMfrnr((String) map.get("mfrnr"));
        setEmnfr((String) map.get("emnfr"));
        setPlifz((String) map.get("plifz"));
        setBerid((String) map.get("berid"));
        setMemory((String) map.get("memory"));
        setBanpr((String) map.get("banpr"));
        setBlckd((String) map.get("blckd"));
        setBlckt((String) map.get("blckt"));
        setBeswk((String) map.get("beswk"));
        setReslo((String) map.get("reslo"));
        setBanfnCs((String) map.get("banfnCs"));
        setBnfpoCs((String) map.get("bnfpoCs"));
        setItemCs((String) map.get("itemCs"));
    }

    public String getBanfn() {
        return this.banfn;
    }

    public void setBanfn(String banfn) {
        this.banfn = banfn;
    }

    public String getBnfpo() {
        return this.bnfpo;
    }

    public void setBnfpo(String bnfpo) {
        this.bnfpo = bnfpo;
    }

    public String getWerks() {
        return this.werks;
    }

    public void setWerks(String werks) {
        this.werks = werks;
    }

    public String getMatnr() {
        return this.matnr;
    }

    public void setMatnr(String matnr) {
        this.matnr = matnr;
    }

    public String getMeins() {
        return this.meins;
    }

    public void setMeins(String meins) {
        this.meins = meins;
    }

    public String getBsart() {
        return this.bsart;
    }

    public void setBsart(String bsart) {
        this.bsart = bsart;
    }

    public String getBstyp() {
        return this.bstyp;
    }

    public void setBstyp(String bstyp) {
        this.bstyp = bstyp;
    }

    public String getBsakz() {
        return this.bsakz;
    }

    public void setBsakz(String bsakz) {
        this.bsakz = bsakz;
    }

    public String getLoekz() {
        return this.loekz;
    }

    public void setLoekz(String loekz) {
        this.loekz = loekz;
    }

    public String getStatu() {
        return this.statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public String getEstkz() {
        return this.estkz;
    }

    public void setEstkz(String estkz) {
        this.estkz = estkz;
    }

    public String getEkgrp() {
        return this.ekgrp;
    }

    public void setEkgrp(String ekgrp) {
        this.ekgrp = ekgrp;
    }

    public String getErnam() {
        return this.ernam;
    }

    public void setErnam(String ernam) {
        this.ernam = ernam;
    }

    public String getErdat() {
        return this.erdat;
    }

    public void setErdat(String erdat) {
        this.erdat = erdat;
    }

    public String getTxz01() {
        return this.txz01;
    }

    public void setTxz01(String txz01) {
        this.txz01 = txz01;
    }

    public String getEmatn() {
        return this.ematn;
    }

    public void setEmatn(String ematn) {
        this.ematn = ematn;
    }

    public String getLgort() {
        return this.lgort;
    }

    public void setLgort(String lgort) {
        this.lgort = lgort;
    }

    public String getBednr() {
        return this.bednr;
    }

    public void setBednr(String bednr) {
        this.bednr = bednr;
    }

    public String getMatkl() {
        return this.matkl;
    }

    public void setMatkl(String matkl) {
        this.matkl = matkl;
    }

    public String getReswk() {
        return this.reswk;
    }

    public void setReswk(String reswk) {
        this.reswk = reswk;
    }

    public String getMenge() {
        return this.menge;
    }

    public void setMenge(String menge) {
        this.menge = menge;
    }

    public String getBadat() {
        return this.badat;
    }

    public void setBadat(String badat) {
        this.badat = badat;
    }

    public String getLfdat() {
        return this.lfdat;
    }

    public void setLfdat(String lfdat) {
        this.lfdat = lfdat;
    }

    public String getFrgdt() {
        return this.frgdt;
    }

    public void setFrgdt(String frgdt) {
        this.frgdt = frgdt;
    }

    public String getWebaz() {
        return this.webaz;
    }

    public void setWebaz(String webaz) {
        this.webaz = webaz;
    }

    public String getPstyp() {
        return this.pstyp;
    }

    public void setPstyp(String pstyp) {
        this.pstyp = pstyp;
    }

    public String getKnttp() {
        return this.knttp;
    }

    public void setKnttp(String knttp) {
        this.knttp = knttp;
    }

    public String getWepos() {
        return this.wepos;
    }

    public void setWepos(String wepos) {
        this.wepos = wepos;
    }

    public String getLifnr() {
        return this.lifnr;
    }

    public void setLifnr(String lifnr) {
        this.lifnr = lifnr;
    }

    public String getFlief() {
        return this.flief;
    }

    public void setFlief(String flief) {
        this.flief = flief;
    }

    public String getEkorg() {
        return this.ekorg;
    }

    public void setEkorg(String ekorg) {
        this.ekorg = ekorg;
    }

    public String getVrtyp() {
        return this.vrtyp;
    }

    public void setVrtyp(String vrtyp) {
        this.vrtyp = vrtyp;
    }

    public String getKonnr() {
        return this.konnr;
    }

    public void setKonnr(String konnr) {
        this.konnr = konnr;
    }

    public String getKtpnr() {
        return this.ktpnr;
    }

    public void setKtpnr(String ktpnr) {
        this.ktpnr = ktpnr;
    }

    public String getInfnr() {
        return this.infnr;
    }

    public void setInfnr(String infnr) {
        this.infnr = infnr;
    }

    public String getZugba() {
        return this.zugba;
    }

    public void setZugba(String zugba) {
        this.zugba = zugba;
    }

    public String getQunum() {
        return this.qunum;
    }

    public void setQunum(String qunum) {
        this.qunum = qunum;
    }

    public String getQupos() {
        return this.qupos;
    }

    public void setQupos(String qupos) {
        this.qupos = qupos;
    }

    public String getDispo() {
        return this.dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getSernr() {
        return this.sernr;
    }

    public void setSernr(String sernr) {
        this.sernr = sernr;
    }

    public String getEbeln() {
        return this.ebeln;
    }

    public void setEbeln(String ebeln) {
        this.ebeln = ebeln;
    }

    public String getEbelp() {
        return this.ebelp;
    }

    public void setEbelp(String ebelp) {
        this.ebelp = ebelp;
    }

    public String getBedat() {
        return this.bedat;
    }

    public void setBedat(String bedat) {
        this.bedat = bedat;
    }

    public String getBsmng() {
        return this.bsmng;
    }

    public void setBsmng(String bsmng) {
        this.bsmng = bsmng;
    }

    public String getEbakz() {
        return this.ebakz;
    }

    public void setEbakz(String ebakz) {
        this.ebakz = ebakz;
    }

    public String getRsnum() {
        return this.rsnum;
    }

    public void setRsnum(String rsnum) {
        this.rsnum = rsnum;
    }

    public String getSobkz() {
        return this.sobkz;
    }

    public void setSobkz(String sobkz) {
        this.sobkz = sobkz;
    }

    public String getFixkz() {
        return this.fixkz;
    }

    public void setFixkz(String fixkz) {
        this.fixkz = fixkz;
    }

    public String getBmein() {
        return this.bmein;
    }

    public void setBmein(String bmein) {
        this.bmein = bmein;
    }

    public String getFrgrl() {
        return this.frgrl;
    }

    public void setFrgrl(String frgrl) {
        this.frgrl = frgrl;
    }

    public String getCharg() {
        return this.charg;
    }

    public void setCharg(String charg) {
        this.charg = charg;
    }

    public String getUmsok() {
        return this.umsok;
    }

    public void setUmsok(String umsok) {
        this.umsok = umsok;
    }

    public String getVerid() {
        return this.verid;
    }

    public void setVerid(String verid) {
        this.verid = verid;
    }

    public String getAdrnr() {
        return this.adrnr;
    }

    public void setAdrnr(String adrnr) {
        this.adrnr = adrnr;
    }

    public String getAdrn2() {
        return this.adrn2;
    }

    public void setAdrn2(String adrn2) {
        this.adrn2 = adrn2;
    }

    public String getKunnr() {
        return this.kunnr;
    }

    public void setKunnr(String kunnr) {
        this.kunnr = kunnr;
    }

    public String getEmlif() {
        return this.emlif;
    }

    public void setEmlif(String emlif) {
        this.emlif = emlif;
    }

    public String getLblkz() {
        return this.lblkz;
    }

    public void setLblkz(String lblkz) {
        this.lblkz = lblkz;
    }

    public String getWaers() {
        return this.waers;
    }

    public void setWaers(String waers) {
        this.waers = waers;
    }

    public String getGsfrg() {
        return this.gsfrg;
    }

    public void setGsfrg(String gsfrg) {
        this.gsfrg = gsfrg;
    }

    public String getMfrpn() {
        return this.mfrpn;
    }

    public void setMfrpn(String mfrpn) {
        this.mfrpn = mfrpn;
    }

    public String getMfrnr() {
        return this.mfrnr;
    }

    public void setMfrnr(String mfrnr) {
        this.mfrnr = mfrnr;
    }

    public String getEmnfr() {
        return this.emnfr;
    }

    public void setEmnfr(String emnfr) {
        this.emnfr = emnfr;
    }

    public String getPlifz() {
        return this.plifz;
    }

    public void setPlifz(String plifz) {
        this.plifz = plifz;
    }

    public String getBerid() {
        return this.berid;
    }

    public void setBerid(String berid) {
        this.berid = berid;
    }

    public String getMemory() {
        return this.memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getBanpr() {
        return this.banpr;
    }

    public void setBanpr(String banpr) {
        this.banpr = banpr;
    }

    public String getBlckd() {
        return this.blckd;
    }

    public void setBlckd(String blckd) {
        this.blckd = blckd;
    }

    public String getBlckt() {
        return this.blckt;
    }

    public void setBlckt(String blckt) {
        this.blckt = blckt;
    }

    public String getBeswk() {
        return this.beswk;
    }

    public void setBeswk(String beswk) {
        this.beswk = beswk;
    }

    public String getReslo() {
        return this.reslo;
    }

    public void setReslo(String reslo) {
        this.reslo = reslo;
    }

    public String getBanfnCs() {
        return this.banfnCs;
    }

    public void setBanfnCs(String banfnCs) {
        this.banfnCs = banfnCs;
    }

    public String getBnfpoCs() {
        return this.bnfpoCs;
    }

    public void setBnfpoCs(String bnfpoCs) {
        this.bnfpoCs = bnfpoCs;
    }

    public String getItemCs() {
        return this.itemCs;
    }

    public void setItemCs(String itemCs) {
        this.itemCs = itemCs;
    }

}

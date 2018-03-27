package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class MarcEntity extends CommonEntity {
    private String matnr;
    private String werks;
    private String mmsta;
    private String beskz;
    private String bstfe;
    private String bstma;
    private String bstmi;
    private String bstrf;
    private String disls;
    private String dismm;
    private String dispo;
    private String dzeit;
    private String eisbe;
    private String eislo;
    private String fevor;
    private String frtme;
    private String insmk;
    private String kausf;
    private String kzkri;
    private String maabc;
    private String mabst;
    private String mtvfp;
    private String plifz;
    private String sbdkz;
    private String shflg;
    private String shzet;
    private String sobsl;
    private String strgr;
    private String vint1;
    private String vint2;
    private String vrmod;
    private String webaz;
    private String xchpf;
    private String fxhor;
    private String basmg;

    public MarcEntity(Map<String, Object> map) {
        super(map);
        setMatnr((String)map.get("matnr"));
        setWerks((String)map.get("werks"));
        setMmsta((String)map.get("mmsta"));
        setBeskz((String)map.get("beskz"));
        setBstfe((String)map.get("bstfe"));
        setBstma((String)map.get("bstma"));
        setBstmi((String)map.get("bstmi"));
        setBstrf((String)map.get("bstrf"));
        setDisls((String)map.get("disls"));
        setDismm((String)map.get("dismm"));
        setDispo((String)map.get("dispo"));
        setDzeit((String)map.get("dzeit"));
        setEisbe((String)map.get("eisbe"));
        setEislo((String)map.get("eislo"));
        setFevor((String)map.get("fevor"));
        setFrtme((String)map.get("frtme"));
        setInsmk((String)map.get("insmk"));
        setKausf((String)map.get("kausf"));
        setKzkri((String)map.get("kzkri"));
        setMaabc((String)map.get("maabc"));
        setMabst((String)map.get("mabst"));
        setMtvfp((String)map.get("mtvfp"));
        setPlifz((String)map.get("plifz"));
        setSbdkz((String)map.get("sbdkz"));
        setShflg((String)map.get("shflg"));
        setShzet((String)map.get("shzet"));
        setSobsl((String)map.get("sobsl"));
        setStrgr((String)map.get("strgr"));
        setVint1((String)map.get("vint1"));
        setVint2((String)map.get("vint2"));
        setVrmod((String)map.get("vrmod"));
        setWebaz((String)map.get("webaz"));
        setXchpf((String)map.get("xchpf"));
        setFxhor((String)map.get("fxhor"));
        setBasmg((String)map.get("basmg"));

    }

    public String getBstfe() {
        return bstfe;
    }

    public void setBstfe(String bstfe) {
        this.bstfe = bstfe;
    }

    public String getBstma() {
        return bstma;
    }

    public void setBstma(String bstma) {
        this.bstma = bstma;
    }

    public String getBstmi() {
        return bstmi;
    }

    public void setBstmi(String bstmi) {
        this.bstmi = bstmi;
    }

    public String getBstrf() {
        return bstrf;
    }

    public void setBstrf(String bstrf) {
        this.bstrf = bstrf;
    }

    public String getDisls() {
        return disls;
    }

    public void setDisls(String disls) {
        this.disls = disls;
    }

    public String getDismm() {
        return dismm;
    }

    public void setDismm(String dismm) {
        this.dismm = dismm;
    }

    public String getDispo() {
        return dispo;
    }

    public void setDispo(String dispo) {
        this.dispo = dispo;
    }

    public String getDzeit() {
        return dzeit;
    }

    public void setDzeit(String dzeit) {
        this.dzeit = dzeit;
    }

    public String getEisbe() {
        return eisbe;
    }

    public void setEisbe(String eisbe) {
        this.eisbe = eisbe;
    }

    public String getEislo() {
        return eislo;
    }

    public void setEislo(String eislo) {
        this.eislo = eislo;
    }

    public String getFevor() {
        return fevor;
    }

    public void setFevor(String fevor) {
        this.fevor = fevor;
    }

    public String getFrtme() {
        return frtme;
    }

    public void setFrtme(String frtme) {
        this.frtme = frtme;
    }

    public String getInsmk() {
        return insmk;
    }

    public void setInsmk(String insmk) {
        this.insmk = insmk;
    }

    public String getKausf() {
        return kausf;
    }

    public void setKausf(String kausf) {
        this.kausf = kausf;
    }

    public String getKzkri() {
        return kzkri;
    }

    public void setKzkri(String kzkri) {
        this.kzkri = kzkri;
    }

    public String getMaabc() {
        return maabc;
    }

    public void setMaabc(String maabc) {
        this.maabc = maabc;
    }

    public String getMabst() {
        return mabst;
    }

    public void setMabst(String mabst) {
        this.mabst = mabst;
    }

    public String getMtvfp() {
        return mtvfp;
    }

    public void setMtvfp(String mtvfp) {
        this.mtvfp = mtvfp;
    }

    public String getPlifz() {
        return plifz;
    }

    public void setPlifz(String plifz) {
        this.plifz = plifz;
    }

    public String getSbdkz() {
        return sbdkz;
    }

    public void setSbdkz(String sbdkz) {
        this.sbdkz = sbdkz;
    }

    public String getShflg() {
        return shflg;
    }

    public void setShflg(String shflg) {
        this.shflg = shflg;
    }

    public String getShzet() {
        return shzet;
    }

    public void setShzet(String shzet) {
        this.shzet = shzet;
    }

    public String getSobsl() {
        return sobsl;
    }

    public void setSobsl(String sobsl) {
        this.sobsl = sobsl;
    }

    public String getStrgr() {
        return strgr;
    }

    public void setStrgr(String strgr) {
        this.strgr = strgr;
    }

    public String getVint1() {
        return vint1;
    }

    public void setVint1(String vint1) {
        this.vint1 = vint1;
    }

    public String getVint2() {
        return vint2;
    }

    public void setVint2(String vint2) {
        this.vint2 = vint2;
    }

    public String getVrmod() {
        return vrmod;
    }

    public void setVrmod(String vrmod) {
        this.vrmod = vrmod;
    }

    public String getWebaz() {
        return webaz;
    }

    public void setWebaz(String webaz) {
        this.webaz = webaz;
    }

    public String getXchpf() {
        return xchpf;
    }

    public void setXchpf(String xchpf) {
        this.xchpf = xchpf;
    }

    public String getFxhor() {
        return fxhor;
    }

    public void setFxhor(String fxhor) {
        this.fxhor = fxhor;
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

    public String getMmsta() {
        return mmsta;
    }

    public void setMmsta(String mmsta) {
        this.mmsta = mmsta;
    }

    public String getBeskz() { return beskz; }

    public void setBeskz(String beskz) { this.beskz = beskz; }

    public String getBasmg() {
        return basmg;
    }

    public void setBasmg(String basmg) {
        this.basmg = basmg;
    }
}

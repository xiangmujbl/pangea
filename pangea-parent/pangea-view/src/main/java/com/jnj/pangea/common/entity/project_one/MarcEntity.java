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
    private String abcin;
    private String abfac;
    private String ahdis;
    private String altsl;
    private String aplal;
    private String apokz;
    private String atpkz;
    private String auftl;
    private String ausdt;
    private String ausme;
    private String ausss;
    private String autru;
    private String awsls;
    private String bearz;
    private String bwesb;
    private String bwscl;
    private String bwtty;
    private String casnr;
    private String ccfix;
    private String compl;
    private String consProcg;
    private String convt;
    private String copam;
    private String cuobj;
    private String cuobv;
    private String diber;
    private String disgr;
    private String dispr;
    private String dplfs;
    private String dplho;
    private String dplpu;
    private String ekgrp;
    private String eprio;
    private String expme;
    private String fabkz;
    private String ffrei;
    private String fhori;
    private String fixls;
    private String fprfm;
    private String fvidk;
    private String fxpru;
    private String giPrTime;
    private String glgmg;
    private String gpmkz;
    private String gpnum;
    private String herbl;
    private String herkl;
    private String herkr;
    private String indus;
    private String itark;
    private String iuidRelevant;
    private String iuidType;
    private String kautb;
    private String kordb;
    private String kzagl;
    private String kzaus;
    private String kzbed;
    private String kzdie;
    private String kzdkz;
    private String kzech;
    private String kzkfk;
    private String kzkup;
    private String kzppv;
    private String kzpro;
    private String kzpsp;
    private String ladgr;
    private String lagpr;
    private String lfgja;
    private String lfmon;
    private String lfrhy;
    private String lgfsb;
    private String lgpro;
    private String lgrad;
    private String lizyk;
    private String loggr;
    private String losfx;
    private String losgr;
    private String ltinc;
    private String lvorm;
    private String lzeih;
    private String mandt;
    private String matgr;
    private String maxls;
    private String maxlz;
    private String maxTroc;
    private String mcrue;
    private String mdach;
    private String megru;
    private String mfrgr;
    private String minbe;
    private String minls;
    private String minTroc;
    private String miskz;
    private String mmstd;
    private String mogru;
    private String mownr;
    private String mpdau;
    private String mrppp;
    private String mtver;
    private String multipleEkgrp;
    private String ncost;
    private String nfmat;
    private String nkmpr;
    private String objid;
    private String ocmpf;
    private String otype;
    private String periv;
    private String perkz;
    private String pfrei;
    private String plnnr;
    private String plnty;
    private String plvar;
    private String prctr;
    private String prefe;
    private String prenc;
    private String prend;
    private String prene;
    private String preng;
    private String preno;
    private String prfrq;
    private String pstat;
    private String qmata;
    private String qmatv;
    private String qssys;
    private String quazt;
    private String qzgtp;
    private String rdprf;
    private String refSchema;
    private String resvp;
    private String rgekz;
    private String rotationDate;
    private String ruezt;
    private String rwpro;
    private String sauft;
    private String schgt;
    private String sernp;
    private String servg;
    private String sfcpf;
    private String sfepr;
    private String shpro;
    private String sobsk;
    private String sproz;
    private String ssqss;
    private String stawn;
    private String stdpd;
    private String steuc;
    private String stlal;
    private String stlan;
    private String takzt;
    private String targetStock;
    private String trame;
    private String tranz;
    private String uchkz;
    private String ucmat;
    private String ueetk;
    private String ueeto;
    private String uidIea;
    private String umlmc;
    private String umrsl;
    private String uneto;
    private String uomgr;
    private String usequ;
    private String vbamg;
    private String vbeaz;
    private String verkz;
    private String vkglg;
    private String vktrw;
    private String vkumc;
    private String vrbdt;
    private String vrbfk;
    private String vrbmt;
    private String vrbwk;
    private String vrvez;
    private String vsorForkDir;
    private String vsorLaneNum;
    private String vsorPalVend;
    private String vsorPkgrp;
    private String vspvb;
    private String vzusl;
    private String wstgh;
    private String wzeit;
    private String xchar;
    private String xmcng;
    private String zdtalancprev;
    private String zdtalancreal;
    private String zindnbnp;

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
        setAbcin((String) map.get("abcin"));
        setAbfac((String) map.get("abfac"));
        setAhdis((String) map.get("ahdis"));
        setAltsl((String) map.get("altsl"));
        setAplal((String) map.get("aplal"));
        setApokz((String) map.get("apokz"));
        setAtpkz((String) map.get("atpkz"));
        setAuftl((String) map.get("auftl"));
        setAusdt((String) map.get("ausdt"));
        setAusme((String) map.get("ausme"));
        setAusss((String) map.get("ausss"));
        setAutru((String) map.get("autru"));
        setAwsls((String) map.get("awsls"));
        setBearz((String) map.get("bearz"));
        setBwesb((String) map.get("bwesb"));
        setBwscl((String) map.get("bwscl"));
        setBwtty((String) map.get("bwtty"));
        setCasnr((String) map.get("casnr"));
        setCcfix((String) map.get("ccfix"));
        setCompl((String) map.get("compl"));
        setConsProcg((String) map.get("consProcg"));
        setConvt((String) map.get("convt"));
        setCopam((String) map.get("copam"));
        setCuobj((String) map.get("cuobj"));
        setCuobv((String) map.get("cuobv"));
        setDiber((String) map.get("diber"));
        setDisgr((String) map.get("disgr"));
        setDispr((String) map.get("dispr"));
        setDplfs((String) map.get("dplfs"));
        setDplho((String) map.get("dplho"));
        setDplpu((String) map.get("dplpu"));
        setEkgrp((String) map.get("ekgrp"));
        setEprio((String) map.get("eprio"));
        setExpme((String) map.get("expme"));
        setFabkz((String) map.get("fabkz"));
        setFfrei((String) map.get("ffrei"));
        setFhori((String) map.get("fhori"));
        setFixls((String) map.get("fixls"));
        setFprfm((String) map.get("fprfm"));
        setFvidk((String) map.get("fvidk"));
        setFxpru((String) map.get("fxpru"));
        setGiPrTime((String) map.get("giPrTime"));
        setGlgmg((String) map.get("glgmg"));
        setGpmkz((String) map.get("gpmkz"));
        setGpnum((String) map.get("gpnum"));
        setHerbl((String) map.get("herbl"));
        setHerkl((String) map.get("herkl"));
        setHerkr((String) map.get("herkr"));
        setIndus((String) map.get("indus"));
        setItark((String) map.get("itark"));
        setIuidRelevant((String) map.get("iuidRelevant"));
        setIuidType((String) map.get("iuidType"));
        setKautb((String) map.get("kautb"));
        setKordb((String) map.get("kordb"));
        setKzagl((String) map.get("kzagl"));
        setKzaus((String) map.get("kzaus"));
        setKzbed((String) map.get("kzbed"));
        setKzdie((String) map.get("kzdie"));
        setKzdkz((String) map.get("kzdkz"));
        setKzech((String) map.get("kzech"));
        setKzkfk((String) map.get("kzkfk"));
        setKzkup((String) map.get("kzkup"));
        setKzppv((String) map.get("kzppv"));
        setKzpro((String) map.get("kzpro"));
        setKzpsp((String) map.get("kzpsp"));
        setLadgr((String) map.get("ladgr"));
        setLagpr((String) map.get("lagpr"));
        setLfgja((String) map.get("lfgja"));
        setLfmon((String) map.get("lfmon"));
        setLfrhy((String) map.get("lfrhy"));
        setLgfsb((String) map.get("lgfsb"));
        setLgpro((String) map.get("lgpro"));
        setLgrad((String) map.get("lgrad"));
        setLizyk((String) map.get("lizyk"));
        setLoggr((String) map.get("loggr"));
        setLosfx((String) map.get("losfx"));
        setLosgr((String) map.get("losgr"));
        setLtinc((String) map.get("ltinc"));
        setLvorm((String) map.get("lvorm"));
        setLzeih((String) map.get("lzeih"));
        setMandt((String) map.get("mandt"));
        setMatgr((String) map.get("matgr"));
        setMaxls((String) map.get("maxls"));
        setMaxlz((String) map.get("maxlz"));
        setMaxTroc((String) map.get("maxTroc"));
        setMcrue((String) map.get("mcrue"));
        setMdach((String) map.get("mdach"));
        setMegru((String) map.get("megru"));
        setMfrgr((String) map.get("mfrgr"));
        setMinbe((String) map.get("minbe"));
        setMinls((String) map.get("minls"));
        setMinTroc((String) map.get("minTroc"));
        setMiskz((String) map.get("miskz"));
        setMmstd((String) map.get("mmstd"));
        setMogru((String) map.get("mogru"));
        setMownr((String) map.get("mownr"));
        setMpdau((String) map.get("mpdau"));
        setMrppp((String) map.get("mrppp"));
        setMtver((String) map.get("mtver"));
        setMultipleEkgrp((String) map.get("multipleEkgrp"));
        setNcost((String) map.get("ncost"));
        setNfmat((String) map.get("nfmat"));
        setNkmpr((String) map.get("nkmpr"));
        setObjid((String) map.get("objid"));
        setOcmpf((String) map.get("ocmpf"));
        setOtype((String) map.get("otype"));
        setPeriv((String) map.get("periv"));
        setPerkz((String) map.get("perkz"));
        setPfrei((String) map.get("pfrei"));
        setPlnnr((String) map.get("plnnr"));
        setPlnty((String) map.get("plnty"));
        setPlvar((String) map.get("plvar"));
        setPrctr((String) map.get("prctr"));
        setPrefe((String) map.get("prefe"));
        setPrenc((String) map.get("prenc"));
        setPrend((String) map.get("prend"));
        setPrene((String) map.get("prene"));
        setPreng((String) map.get("preng"));
        setPreno((String) map.get("preno"));
        setPrfrq((String) map.get("prfrq"));
        setPstat((String) map.get("pstat"));
        setQmata((String) map.get("qmata"));
        setQmatv((String) map.get("qmatv"));
        setQssys((String) map.get("qssys"));
        setQuazt((String) map.get("quazt"));
        setQzgtp((String) map.get("qzgtp"));
        setRdprf((String) map.get("rdprf"));
        setRefSchema((String) map.get("refSchema"));
        setResvp((String) map.get("resvp"));
        setRgekz((String) map.get("rgekz"));
        setRotationDate((String) map.get("rotationDate"));
        setRuezt((String) map.get("ruezt"));
        setRwpro((String) map.get("rwpro"));
        setSauft((String) map.get("sauft"));
        setSchgt((String) map.get("schgt"));
        setSernp((String) map.get("sernp"));
        setServg((String) map.get("servg"));
        setSfcpf((String) map.get("sfcpf"));
        setSfepr((String) map.get("sfepr"));
        setShpro((String) map.get("shpro"));
        setSobsk((String) map.get("sobsk"));
        setSproz((String) map.get("sproz"));
        setSsqss((String) map.get("ssqss"));
        setStawn((String) map.get("stawn"));
        setStdpd((String) map.get("stdpd"));
        setSteuc((String) map.get("steuc"));
        setStlal((String) map.get("stlal"));
        setStlan((String) map.get("stlan"));
        setTakzt((String) map.get("takzt"));
        setTargetStock((String) map.get("targetStock"));
        setTrame((String) map.get("trame"));
        setTranz((String) map.get("tranz"));
        setUchkz((String) map.get("uchkz"));
        setUcmat((String) map.get("ucmat"));
        setUeetk((String) map.get("ueetk"));
        setUeeto((String) map.get("ueeto"));
        setUidIea((String) map.get("uidIea"));
        setUmlmc((String) map.get("umlmc"));
        setUmrsl((String) map.get("umrsl"));
        setUneto((String) map.get("uneto"));
        setUomgr((String) map.get("uomgr"));
        setUsequ((String) map.get("usequ"));
        setVbamg((String) map.get("vbamg"));
        setVbeaz((String) map.get("vbeaz"));
        setVerkz((String) map.get("verkz"));
        setVkglg((String) map.get("vkglg"));
        setVktrw((String) map.get("vktrw"));
        setVkumc((String) map.get("vkumc"));
        setVrbdt((String) map.get("vrbdt"));
        setVrbfk((String) map.get("vrbfk"));
        setVrbmt((String) map.get("vrbmt"));
        setVrbwk((String) map.get("vrbwk"));
        setVrvez((String) map.get("vrvez"));
        setVsorForkDir((String) map.get("vsorForkDir"));
        setVsorLaneNum((String) map.get("vsorLaneNum"));
        setVsorPalVend((String) map.get("vsorPalVend"));
        setVsorPkgrp((String) map.get("vsorPkgrp"));
        setVspvb((String) map.get("vspvb"));
        setVzusl((String) map.get("vzusl"));
        setWstgh((String) map.get("wstgh"));
        setWzeit((String) map.get("wzeit"));
        setXchar((String) map.get("xchar"));
        setXmcng((String) map.get("xmcng"));
        setZdtalancprev((String) map.get("zdtalancprev"));
        setZdtalancreal((String) map.get("zdtalancreal"));
        setZindnbnp((String) map.get("zindnbnp"));

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

    public String getAbcin() {
        return abcin;
    }

    public void setAbcin(String abcin) {
        this.abcin = abcin;
    }

    public String getAbfac() {
        return abfac;
    }

    public void setAbfac(String abfac) {
        this.abfac = abfac;
    }

    public String getAhdis() {
        return ahdis;
    }

    public void setAhdis(String ahdis) {
        this.ahdis = ahdis;
    }

    public String getAltsl() {
        return altsl;
    }

    public void setAltsl(String altsl) {
        this.altsl = altsl;
    }

    public String getAplal() {
        return aplal;
    }

    public void setAplal(String aplal) {
        this.aplal = aplal;
    }

    public String getApokz() {
        return apokz;
    }

    public void setApokz(String apokz) {
        this.apokz = apokz;
    }

    public String getAtpkz() {
        return atpkz;
    }

    public void setAtpkz(String atpkz) {
        this.atpkz = atpkz;
    }

    public String getAuftl() {
        return auftl;
    }

    public void setAuftl(String auftl) {
        this.auftl = auftl;
    }

    public String getAusdt() {
        return ausdt;
    }

    public void setAusdt(String ausdt) {
        this.ausdt = ausdt;
    }

    public String getAusme() {
        return ausme;
    }

    public void setAusme(String ausme) {
        this.ausme = ausme;
    }

    public String getAusss() {
        return ausss;
    }

    public void setAusss(String ausss) {
        this.ausss = ausss;
    }

    public String getAutru() {
        return autru;
    }

    public void setAutru(String autru) {
        this.autru = autru;
    }

    public String getAwsls() {
        return awsls;
    }

    public void setAwsls(String awsls) {
        this.awsls = awsls;
    }

    public String getBearz() {
        return bearz;
    }

    public void setBearz(String bearz) {
        this.bearz = bearz;
    }

    public String getBwesb() {
        return bwesb;
    }

    public void setBwesb(String bwesb) {
        this.bwesb = bwesb;
    }

    public String getBwscl() {
        return bwscl;
    }

    public void setBwscl(String bwscl) {
        this.bwscl = bwscl;
    }

    public String getBwtty() {
        return bwtty;
    }

    public void setBwtty(String bwtty) {
        this.bwtty = bwtty;
    }

    public String getCasnr() {
        return casnr;
    }

    public void setCasnr(String casnr) {
        this.casnr = casnr;
    }

    public String getCcfix() {
        return ccfix;
    }

    public void setCcfix(String ccfix) {
        this.ccfix = ccfix;
    }

    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }

    public String getConsProcg() {
        return consProcg;
    }

    public void setConsProcg(String consProcg) {
        this.consProcg = consProcg;
    }

    public String getConvt() {
        return convt;
    }

    public void setConvt(String convt) {
        this.convt = convt;
    }

    public String getCopam() {
        return copam;
    }

    public void setCopam(String copam) {
        this.copam = copam;
    }

    public String getCuobj() {
        return cuobj;
    }

    public void setCuobj(String cuobj) {
        this.cuobj = cuobj;
    }

    public String getCuobv() {
        return cuobv;
    }

    public void setCuobv(String cuobv) {
        this.cuobv = cuobv;
    }

    public String getDiber() {
        return diber;
    }

    public void setDiber(String diber) {
        this.diber = diber;
    }

    public String getDisgr() {
        return disgr;
    }

    public void setDisgr(String disgr) {
        this.disgr = disgr;
    }

    public String getDispr() {
        return dispr;
    }

    public void setDispr(String dispr) {
        this.dispr = dispr;
    }

    public String getDplfs() {
        return dplfs;
    }

    public void setDplfs(String dplfs) {
        this.dplfs = dplfs;
    }

    public String getDplho() {
        return dplho;
    }

    public void setDplho(String dplho) {
        this.dplho = dplho;
    }

    public String getDplpu() {
        return dplpu;
    }

    public void setDplpu(String dplpu) {
        this.dplpu = dplpu;
    }

    public String getEkgrp() {
        return ekgrp;
    }

    public void setEkgrp(String ekgrp) {
        this.ekgrp = ekgrp;
    }

    public String getEprio() {
        return eprio;
    }

    public void setEprio(String eprio) {
        this.eprio = eprio;
    }

    public String getExpme() {
        return expme;
    }

    public void setExpme(String expme) {
        this.expme = expme;
    }

    public String getFabkz() {
        return fabkz;
    }

    public void setFabkz(String fabkz) {
        this.fabkz = fabkz;
    }

    public String getFfrei() {
        return ffrei;
    }

    public void setFfrei(String ffrei) {
        this.ffrei = ffrei;
    }

    public String getFhori() {
        return fhori;
    }

    public void setFhori(String fhori) {
        this.fhori = fhori;
    }

    public String getFixls() {
        return fixls;
    }

    public void setFixls(String fixls) {
        this.fixls = fixls;
    }

    public String getFprfm() {
        return fprfm;
    }

    public void setFprfm(String fprfm) {
        this.fprfm = fprfm;
    }

    public String getFvidk() {
        return fvidk;
    }

    public void setFvidk(String fvidk) {
        this.fvidk = fvidk;
    }

    public String getFxpru() {
        return fxpru;
    }

    public void setFxpru(String fxpru) {
        this.fxpru = fxpru;
    }

    public String getGiPrTime() {
        return giPrTime;
    }

    public void setGiPrTime(String giPrTime) {
        this.giPrTime = giPrTime;
    }

    public String getGlgmg() {
        return glgmg;
    }

    public void setGlgmg(String glgmg) {
        this.glgmg = glgmg;
    }

    public String getGpmkz() {
        return gpmkz;
    }

    public void setGpmkz(String gpmkz) {
        this.gpmkz = gpmkz;
    }

    public String getGpnum() {
        return gpnum;
    }

    public void setGpnum(String gpnum) {
        this.gpnum = gpnum;
    }

    public String getHerbl() {
        return herbl;
    }

    public void setHerbl(String herbl) {
        this.herbl = herbl;
    }

    public String getHerkl() {
        return herkl;
    }

    public void setHerkl(String herkl) {
        this.herkl = herkl;
    }

    public String getHerkr() {
        return herkr;
    }

    public void setHerkr(String herkr) {
        this.herkr = herkr;
    }

    public String getIndus() {
        return indus;
    }

    public void setIndus(String indus) {
        this.indus = indus;
    }

    public String getItark() {
        return itark;
    }

    public void setItark(String itark) {
        this.itark = itark;
    }

    public String getIuidRelevant() {
        return iuidRelevant;
    }

    public void setIuidRelevant(String iuidRelevant) {
        this.iuidRelevant = iuidRelevant;
    }

    public String getIuidType() {
        return iuidType;
    }

    public void setIuidType(String iuidType) {
        this.iuidType = iuidType;
    }

    public String getKautb() {
        return kautb;
    }

    public void setKautb(String kautb) {
        this.kautb = kautb;
    }

    public String getKordb() {
        return kordb;
    }

    public void setKordb(String kordb) {
        this.kordb = kordb;
    }

    public String getKzagl() {
        return kzagl;
    }

    public void setKzagl(String kzagl) {
        this.kzagl = kzagl;
    }

    public String getKzaus() {
        return kzaus;
    }

    public void setKzaus(String kzaus) {
        this.kzaus = kzaus;
    }

    public String getKzbed() {
        return kzbed;
    }

    public void setKzbed(String kzbed) {
        this.kzbed = kzbed;
    }

    public String getKzdie() {
        return kzdie;
    }

    public void setKzdie(String kzdie) {
        this.kzdie = kzdie;
    }

    public String getKzdkz() {
        return kzdkz;
    }

    public void setKzdkz(String kzdkz) {
        this.kzdkz = kzdkz;
    }

    public String getKzech() {
        return kzech;
    }

    public void setKzech(String kzech) {
        this.kzech = kzech;
    }

    public String getKzkfk() {
        return kzkfk;
    }

    public void setKzkfk(String kzkfk) {
        this.kzkfk = kzkfk;
    }

    public String getKzkup() {
        return kzkup;
    }

    public void setKzkup(String kzkup) {
        this.kzkup = kzkup;
    }

    public String getKzppv() {
        return kzppv;
    }

    public void setKzppv(String kzppv) {
        this.kzppv = kzppv;
    }

    public String getKzpro() {
        return kzpro;
    }

    public void setKzpro(String kzpro) {
        this.kzpro = kzpro;
    }

    public String getKzpsp() {
        return kzpsp;
    }

    public void setKzpsp(String kzpsp) {
        this.kzpsp = kzpsp;
    }

    public String getLadgr() {
        return ladgr;
    }

    public void setLadgr(String ladgr) {
        this.ladgr = ladgr;
    }

    public String getLagpr() {
        return lagpr;
    }

    public void setLagpr(String lagpr) {
        this.lagpr = lagpr;
    }

    public String getLfgja() {
        return lfgja;
    }

    public void setLfgja(String lfgja) {
        this.lfgja = lfgja;
    }

    public String getLfmon() {
        return lfmon;
    }

    public void setLfmon(String lfmon) {
        this.lfmon = lfmon;
    }

    public String getLfrhy() {
        return lfrhy;
    }

    public void setLfrhy(String lfrhy) {
        this.lfrhy = lfrhy;
    }

    public String getLgfsb() {
        return lgfsb;
    }

    public void setLgfsb(String lgfsb) {
        this.lgfsb = lgfsb;
    }

    public String getLgpro() {
        return lgpro;
    }

    public void setLgpro(String lgpro) {
        this.lgpro = lgpro;
    }

    public String getLgrad() {
        return lgrad;
    }

    public void setLgrad(String lgrad) {
        this.lgrad = lgrad;
    }

    public String getLizyk() {
        return lizyk;
    }

    public void setLizyk(String lizyk) {
        this.lizyk = lizyk;
    }

    public String getLoggr() {
        return loggr;
    }

    public void setLoggr(String loggr) {
        this.loggr = loggr;
    }

    public String getLosfx() {
        return losfx;
    }

    public void setLosfx(String losfx) {
        this.losfx = losfx;
    }

    public String getLosgr() {
        return losgr;
    }

    public void setLosgr(String losgr) {
        this.losgr = losgr;
    }

    public String getLtinc() {
        return ltinc;
    }

    public void setLtinc(String ltinc) {
        this.ltinc = ltinc;
    }

    public String getLvorm() {
        return lvorm;
    }

    public void setLvorm(String lvorm) {
        this.lvorm = lvorm;
    }

    public String getLzeih() {
        return lzeih;
    }

    public void setLzeih(String lzeih) {
        this.lzeih = lzeih;
    }

    public String getMandt() {
        return mandt;
    }

    public void setMandt(String mandt) {
        this.mandt = mandt;
    }

    public String getMatgr() {
        return matgr;
    }

    public void setMatgr(String matgr) {
        this.matgr = matgr;
    }

    public String getMaxls() {
        return maxls;
    }

    public void setMaxls(String maxls) {
        this.maxls = maxls;
    }

    public String getMaxlz() {
        return maxlz;
    }

    public void setMaxlz(String maxlz) {
        this.maxlz = maxlz;
    }

    public String getMaxTroc() {
        return maxTroc;
    }

    public void setMaxTroc(String maxTroc) {
        this.maxTroc = maxTroc;
    }

    public String getMcrue() {
        return mcrue;
    }

    public void setMcrue(String mcrue) {
        this.mcrue = mcrue;
    }

    public String getMdach() {
        return mdach;
    }

    public void setMdach(String mdach) {
        this.mdach = mdach;
    }

    public String getMegru() {
        return megru;
    }

    public void setMegru(String megru) {
        this.megru = megru;
    }

    public String getMfrgr() {
        return mfrgr;
    }

    public void setMfrgr(String mfrgr) {
        this.mfrgr = mfrgr;
    }

    public String getMinbe() {
        return minbe;
    }

    public void setMinbe(String minbe) {
        this.minbe = minbe;
    }

    public String getMinls() {
        return minls;
    }

    public void setMinls(String minls) {
        this.minls = minls;
    }

    public String getMinTroc() {
        return minTroc;
    }

    public void setMinTroc(String minTroc) {
        this.minTroc = minTroc;
    }

    public String getMiskz() {
        return miskz;
    }

    public void setMiskz(String miskz) {
        this.miskz = miskz;
    }

    public String getMmstd() {
        return mmstd;
    }

    public void setMmstd(String mmstd) {
        this.mmstd = mmstd;
    }

    public String getMogru() {
        return mogru;
    }

    public void setMogru(String mogru) {
        this.mogru = mogru;
    }

    public String getMownr() {
        return mownr;
    }

    public void setMownr(String mownr) {
        this.mownr = mownr;
    }

    public String getMpdau() {
        return mpdau;
    }

    public void setMpdau(String mpdau) {
        this.mpdau = mpdau;
    }

    public String getMrppp() {
        return mrppp;
    }

    public void setMrppp(String mrppp) {
        this.mrppp = mrppp;
    }

    public String getMtver() {
        return mtver;
    }

    public void setMtver(String mtver) {
        this.mtver = mtver;
    }

    public String getMultipleEkgrp() {
        return multipleEkgrp;
    }

    public void setMultipleEkgrp(String multipleEkgrp) {
        this.multipleEkgrp = multipleEkgrp;
    }

    public String getNcost() {
        return ncost;
    }

    public void setNcost(String ncost) {
        this.ncost = ncost;
    }

    public String getNfmat() {
        return nfmat;
    }

    public void setNfmat(String nfmat) {
        this.nfmat = nfmat;
    }

    public String getNkmpr() {
        return nkmpr;
    }

    public void setNkmpr(String nkmpr) {
        this.nkmpr = nkmpr;
    }

    public String getObjid() {
        return objid;
    }

    public void setObjid(String objid) {
        this.objid = objid;
    }

    public String getOcmpf() {
        return ocmpf;
    }

    public void setOcmpf(String ocmpf) {
        this.ocmpf = ocmpf;
    }

    public String getOtype() {
        return otype;
    }

    public void setOtype(String otype) {
        this.otype = otype;
    }

    public String getPeriv() {
        return periv;
    }

    public void setPeriv(String periv) {
        this.periv = periv;
    }

    public String getPerkz() {
        return perkz;
    }

    public void setPerkz(String perkz) {
        this.perkz = perkz;
    }

    public String getPfrei() {
        return pfrei;
    }

    public void setPfrei(String pfrei) {
        this.pfrei = pfrei;
    }

    public String getPlnnr() {
        return plnnr;
    }

    public void setPlnnr(String plnnr) {
        this.plnnr = plnnr;
    }

    public String getPlnty() {
        return plnty;
    }

    public void setPlnty(String plnty) {
        this.plnty = plnty;
    }

    public String getPlvar() {
        return plvar;
    }

    public void setPlvar(String plvar) {
        this.plvar = plvar;
    }

    public String getPrctr() {
        return prctr;
    }

    public void setPrctr(String prctr) {
        this.prctr = prctr;
    }

    public String getPrefe() {
        return prefe;
    }

    public void setPrefe(String prefe) {
        this.prefe = prefe;
    }

    public String getPrenc() {
        return prenc;
    }

    public void setPrenc(String prenc) {
        this.prenc = prenc;
    }

    public String getPrend() {
        return prend;
    }

    public void setPrend(String prend) {
        this.prend = prend;
    }

    public String getPrene() {
        return prene;
    }

    public void setPrene(String prene) {
        this.prene = prene;
    }

    public String getPreng() {
        return preng;
    }

    public void setPreng(String preng) {
        this.preng = preng;
    }

    public String getPreno() {
        return preno;
    }

    public void setPreno(String preno) {
        this.preno = preno;
    }

    public String getPrfrq() {
        return prfrq;
    }

    public void setPrfrq(String prfrq) {
        this.prfrq = prfrq;
    }

    public String getPstat() {
        return pstat;
    }

    public void setPstat(String pstat) {
        this.pstat = pstat;
    }

    public String getQmata() {
        return qmata;
    }

    public void setQmata(String qmata) {
        this.qmata = qmata;
    }

    public String getQmatv() {
        return qmatv;
    }

    public void setQmatv(String qmatv) {
        this.qmatv = qmatv;
    }

    public String getQssys() {
        return qssys;
    }

    public void setQssys(String qssys) {
        this.qssys = qssys;
    }

    public String getQuazt() {
        return quazt;
    }

    public void setQuazt(String quazt) {
        this.quazt = quazt;
    }

    public String getQzgtp() {
        return qzgtp;
    }

    public void setQzgtp(String qzgtp) {
        this.qzgtp = qzgtp;
    }

    public String getRdprf() {
        return rdprf;
    }

    public void setRdprf(String rdprf) {
        this.rdprf = rdprf;
    }

    public String getRefSchema() {
        return refSchema;
    }

    public void setRefSchema(String refSchema) {
        this.refSchema = refSchema;
    }

    public String getResvp() {
        return resvp;
    }

    public void setResvp(String resvp) {
        this.resvp = resvp;
    }

    public String getRgekz() {
        return rgekz;
    }

    public void setRgekz(String rgekz) {
        this.rgekz = rgekz;
    }

    public String getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(String rotationDate) {
        this.rotationDate = rotationDate;
    }

    public String getRuezt() {
        return ruezt;
    }

    public void setRuezt(String ruezt) {
        this.ruezt = ruezt;
    }

    public String getRwpro() {
        return rwpro;
    }

    public void setRwpro(String rwpro) {
        this.rwpro = rwpro;
    }

    public String getSauft() {
        return sauft;
    }

    public void setSauft(String sauft) {
        this.sauft = sauft;
    }

    public String getSchgt() {
        return schgt;
    }

    public void setSchgt(String schgt) {
        this.schgt = schgt;
    }

    public String getSernp() {
        return sernp;
    }

    public void setSernp(String sernp) {
        this.sernp = sernp;
    }

    public String getServg() {
        return servg;
    }

    public void setServg(String servg) {
        this.servg = servg;
    }

    public String getSfcpf() {
        return sfcpf;
    }

    public void setSfcpf(String sfcpf) {
        this.sfcpf = sfcpf;
    }

    public String getSfepr() {
        return sfepr;
    }

    public void setSfepr(String sfepr) {
        this.sfepr = sfepr;
    }

    public String getShpro() {
        return shpro;
    }

    public void setShpro(String shpro) {
        this.shpro = shpro;
    }

    public String getSobsk() {
        return sobsk;
    }

    public void setSobsk(String sobsk) {
        this.sobsk = sobsk;
    }

    public String getSproz() {
        return sproz;
    }

    public void setSproz(String sproz) {
        this.sproz = sproz;
    }

    public String getSsqss() {
        return ssqss;
    }

    public void setSsqss(String ssqss) {
        this.ssqss = ssqss;
    }

    public String getStawn() {
        return stawn;
    }

    public void setStawn(String stawn) {
        this.stawn = stawn;
    }

    public String getStdpd() {
        return stdpd;
    }

    public void setStdpd(String stdpd) {
        this.stdpd = stdpd;
    }

    public String getSteuc() {
        return steuc;
    }

    public void setSteuc(String steuc) {
        this.steuc = steuc;
    }

    public String getStlal() {
        return stlal;
    }

    public void setStlal(String stlal) {
        this.stlal = stlal;
    }

    public String getStlan() {
        return stlan;
    }

    public void setStlan(String stlan) {
        this.stlan = stlan;
    }

    public String getTakzt() {
        return takzt;
    }

    public void setTakzt(String takzt) {
        this.takzt = takzt;
    }

    public String getTargetStock() {
        return targetStock;
    }

    public void setTargetStock(String targetStock) {
        this.targetStock = targetStock;
    }

    public String getTrame() {
        return trame;
    }

    public void setTrame(String trame) {
        this.trame = trame;
    }

    public String getTranz() {
        return tranz;
    }

    public void setTranz(String tranz) {
        this.tranz = tranz;
    }

    public String getUchkz() {
        return uchkz;
    }

    public void setUchkz(String uchkz) {
        this.uchkz = uchkz;
    }

    public String getUcmat() {
        return ucmat;
    }

    public void setUcmat(String ucmat) {
        this.ucmat = ucmat;
    }

    public String getUeetk() {
        return ueetk;
    }

    public void setUeetk(String ueetk) {
        this.ueetk = ueetk;
    }

    public String getUeeto() {
        return ueeto;
    }

    public void setUeeto(String ueeto) {
        this.ueeto = ueeto;
    }

    public String getUidIea() {
        return uidIea;
    }

    public void setUidIea(String uidIea) {
        this.uidIea = uidIea;
    }

    public String getUmlmc() {
        return umlmc;
    }

    public void setUmlmc(String umlmc) {
        this.umlmc = umlmc;
    }

    public String getUmrsl() {
        return umrsl;
    }

    public void setUmrsl(String umrsl) {
        this.umrsl = umrsl;
    }

    public String getUneto() {
        return uneto;
    }

    public void setUneto(String uneto) {
        this.uneto = uneto;
    }

    public String getUomgr() {
        return uomgr;
    }

    public void setUomgr(String uomgr) {
        this.uomgr = uomgr;
    }

    public String getUsequ() {
        return usequ;
    }

    public void setUsequ(String usequ) {
        this.usequ = usequ;
    }

    public String getVbamg() {
        return vbamg;
    }

    public void setVbamg(String vbamg) {
        this.vbamg = vbamg;
    }

    public String getVbeaz() {
        return vbeaz;
    }

    public void setVbeaz(String vbeaz) {
        this.vbeaz = vbeaz;
    }

    public String getVerkz() {
        return verkz;
    }

    public void setVerkz(String verkz) {
        this.verkz = verkz;
    }

    public String getVkglg() {
        return vkglg;
    }

    public void setVkglg(String vkglg) {
        this.vkglg = vkglg;
    }

    public String getVktrw() {
        return vktrw;
    }

    public void setVktrw(String vktrw) {
        this.vktrw = vktrw;
    }

    public String getVkumc() {
        return vkumc;
    }

    public void setVkumc(String vkumc) {
        this.vkumc = vkumc;
    }

    public String getVrbdt() {
        return vrbdt;
    }

    public void setVrbdt(String vrbdt) {
        this.vrbdt = vrbdt;
    }

    public String getVrbfk() {
        return vrbfk;
    }

    public void setVrbfk(String vrbfk) {
        this.vrbfk = vrbfk;
    }

    public String getVrbmt() {
        return vrbmt;
    }

    public void setVrbmt(String vrbmt) {
        this.vrbmt = vrbmt;
    }

    public String getVrbwk() {
        return vrbwk;
    }

    public void setVrbwk(String vrbwk) {
        this.vrbwk = vrbwk;
    }

    public String getVrvez() {
        return vrvez;
    }

    public void setVrvez(String vrvez) {
        this.vrvez = vrvez;
    }

    public String getVsorForkDir() {
        return vsorForkDir;
    }

    public void setVsorForkDir(String vsorForkDir) {
        this.vsorForkDir = vsorForkDir;
    }

    public String getVsorLaneNum() {
        return vsorLaneNum;
    }

    public void setVsorLaneNum(String vsorLaneNum) {
        this.vsorLaneNum = vsorLaneNum;
    }

    public String getVsorPalVend() {
        return vsorPalVend;
    }

    public void setVsorPalVend(String vsorPalVend) {
        this.vsorPalVend = vsorPalVend;
    }

    public String getVsorPkgrp() {
        return vsorPkgrp;
    }

    public void setVsorPkgrp(String vsorPkgrp) {
        this.vsorPkgrp = vsorPkgrp;
    }

    public String getVspvb() {
        return vspvb;
    }

    public void setVspvb(String vspvb) {
        this.vspvb = vspvb;
    }

    public String getVzusl() {
        return vzusl;
    }

    public void setVzusl(String vzusl) {
        this.vzusl = vzusl;
    }

    public String getWstgh() {
        return wstgh;
    }

    public void setWstgh(String wstgh) {
        this.wstgh = wstgh;
    }

    public String getWzeit() {
        return wzeit;
    }

    public void setWzeit(String wzeit) {
        this.wzeit = wzeit;
    }

    public String getXchar() {
        return xchar;
    }

    public void setXchar(String xchar) {
        this.xchar = xchar;
    }

    public String getXmcng() {
        return xmcng;
    }

    public void setXmcng(String xmcng) {
        this.xmcng = xmcng;
    }

    public String getZdtalancprev() {
        return zdtalancprev;
    }

    public void setZdtalancprev(String zdtalancprev) {
        this.zdtalancprev = zdtalancprev;
    }

    public String getZdtalancreal() {
        return zdtalancreal;
    }

    public void setZdtalancreal(String zdtalancreal) {
        this.zdtalancreal = zdtalancreal;
    }

    public String getZindnbnp() {
        return zindnbnp;
    }

    public void setZindnbnp(String zindnbnp) {
        this.zindnbnp = zindnbnp;
    }
}

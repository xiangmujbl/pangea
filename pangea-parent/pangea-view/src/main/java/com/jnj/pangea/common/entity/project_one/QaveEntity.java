package com.jnj.pangea.common.entity.project_one;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * Created by SLiu170 on 2018/5/14.
 */
public class QaveEntity extends CommonEntity {
    private String dbewertung;
    private String kzart;
    private String ltextkz;
    private String mandant;
    private String prueflos;
    private String qkennzahl;
    private String stafo;
    private String teillos;
    private String vaedatum;
    private String vaename;
    private String vauswahlmg;
    private String vbewertung;
    private String vcode;
    private String vcodegrp;
    private String vdatum;
    private String versionam;
    private String versioncd;
    private String vezeitaen;
    private String vezeiterf;
    private String vfolgeakti;
    private String vkatart;
    private String vname;
    private String vorglfnr;
    private String vwerks;
    private String zaehler;

    public QaveEntity(Map<String, Object> map) {
        super(map);
        setVaename((String) map.get("vaename"));
        setDbewertung((String) map.get("dbewertung"));
        setKzart((String) map.get("kzart"));
        setLtextkz((String) map.get("ltextkz"));
        setMandant((String) map.get("mandant"));
        setPrueflos((String) map.get("prueflos"));
        setQkennzahl((String) map.get("qkennzahl"));
        setStafo((String) map.get("stafo"));
        setTeillos((String) map.get("teillos"));
        setVaedatum((String) map.get("vaedatum"));
        setVauswahlmg((String) map.get("vauswahlmg"));
        setVbewertung((String) map.get("vbewertung"));
        setVcode((String) map.get("vcode"));
        setVcodegrp((String) map.get("vcodegrp"));
        setVdatum((String) map.get("vdatum"));
        setVersionam((String) map.get("versionam"));
        setVersioncd((String) map.get("versioncd"));
        setVezeitaen((String) map.get("vezeitaen"));
        setVezeiterf((String) map.get("vezeiterf"));
        setVfolgeakti((String) map.get("vfolgeakti"));
        setVkatart((String) map.get("vkatart"));
        setVname((String) map.get("vname"));
        setVorglfnr((String) map.get("vorglfnr"));
        setVwerks((String) map.get("vwerks"));
        setZaehler((String) map.get("zaehler"));
    }

    public String getDbewertung() {
        return dbewertung;
    }

    public void setDbewertung(String dbewertung) {
        this.dbewertung = dbewertung;
    }

    public String getKzart() {
        return kzart;
    }

    public void setKzart(String kzart) {
        this.kzart = kzart;
    }

    public String getLtextkz() {
        return ltextkz;
    }

    public void setLtextkz(String ltextkz) {
        this.ltextkz = ltextkz;
    }

    public String getMandant() {
        return mandant;
    }

    public void setMandant(String mandant) {
        this.mandant = mandant;
    }

    public String getPrueflos() {
        return prueflos;
    }

    public void setPrueflos(String prueflos) {
        this.prueflos = prueflos;
    }

    public String getQkennzahl() {
        return qkennzahl;
    }

    public void setQkennzahl(String qkennzahl) {
        this.qkennzahl = qkennzahl;
    }

    public String getStafo() {
        return stafo;
    }

    public void setStafo(String stafo) {
        this.stafo = stafo;
    }

    public String getTeillos() {
        return teillos;
    }

    public void setTeillos(String teillos) {
        this.teillos = teillos;
    }

    public String getVaedatum() {
        return vaedatum;
    }

    public void setVaedatum(String vaedatum) {
        this.vaedatum = vaedatum;
    }

    public String getVaename() {
        return vaename;
    }

    public void setVaename(String vaename) {
        this.vaename = vaename;
    }

    public String getVauswahlmg() {
        return vauswahlmg;
    }

    public void setVauswahlmg(String vauswahlmg) {
        this.vauswahlmg = vauswahlmg;
    }

    public String getVbewertung() {
        return vbewertung;
    }

    public void setVbewertung(String vbewertung) {
        this.vbewertung = vbewertung;
    }

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getVcodegrp() {
        return vcodegrp;
    }

    public void setVcodegrp(String vcodegrp) {
        this.vcodegrp = vcodegrp;
    }

    public String getVdatum() {
        return vdatum;
    }

    public void setVdatum(String vdatum) {
        this.vdatum = vdatum;
    }

    public String getVersionam() {
        return versionam;
    }

    public void setVersionam(String versionam) {
        this.versionam = versionam;
    }

    public String getVersioncd() {
        return versioncd;
    }

    public void setVersioncd(String versioncd) {
        this.versioncd = versioncd;
    }

    public String getVezeitaen() {
        return vezeitaen;
    }

    public void setVezeitaen(String vezeitaen) {
        this.vezeitaen = vezeitaen;
    }

    public String getVezeiterf() {
        return vezeiterf;
    }

    public void setVezeiterf(String vezeiterf) {
        this.vezeiterf = vezeiterf;
    }

    public String getVfolgeakti() {
        return vfolgeakti;
    }

    public void setVfolgeakti(String vfolgeakti) {
        this.vfolgeakti = vfolgeakti;
    }

    public String getVkatart() {
        return vkatart;
    }

    public void setVkatart(String vkatart) {
        this.vkatart = vkatart;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVorglfnr() {
        return vorglfnr;
    }

    public void setVorglfnr(String vorglfnr) {
        this.vorglfnr = vorglfnr;
    }

    public String getVwerks() {
        return vwerks;
    }

    public void setVwerks(String vwerks) {
        this.vwerks = vwerks;
    }

    public String getZaehler() {
        return zaehler;
    }

    public void setZaehler(String zaehler) {
        this.zaehler = zaehler;
    }

    @Override
    public String toString() {
        return "QaveEntity{" +
                "dbewertung='" + dbewertung + '\'' +
                ", kzart='" + kzart + '\'' +
                ", ltextkz='" + ltextkz + '\'' +
                ", mandant='" + mandant + '\'' +
                ", prueflos='" + prueflos + '\'' +
                ", qkennzahl='" + qkennzahl + '\'' +
                ", stafo='" + stafo + '\'' +
                ", teillos='" + teillos + '\'' +
                ", vaedatum='" + vaedatum + '\'' +
                ", vaename='" + vaename + '\'' +
                ", vauswahlmg='" + vauswahlmg + '\'' +
                ", vbewertung='" + vbewertung + '\'' +
                ", vcode='" + vcode + '\'' +
                ", vcodegrp='" + vcodegrp + '\'' +
                ", vdatum='" + vdatum + '\'' +
                ", versionam='" + versionam + '\'' +
                ", versioncd='" + versioncd + '\'' +
                ", vezeitaen='" + vezeitaen + '\'' +
                ", vezeiterf='" + vezeiterf + '\'' +
                ", vfolgeakti='" + vfolgeakti + '\'' +
                ", vkatart='" + vkatart + '\'' +
                ", vname='" + vname + '\'' +
                ", vorglfnr='" + vorglfnr + '\'' +
                ", vwerks='" + vwerks + '\'' +
                ", zaehler='" + zaehler + '\'' +
                '}';
    }
}

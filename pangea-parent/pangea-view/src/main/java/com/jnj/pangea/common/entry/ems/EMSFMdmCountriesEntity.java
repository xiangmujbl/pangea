package com.jnj.pangea.common.entry.ems;

public class EMSFMdmCountriesEntity {
    private String zSourceSystem;
    private String mdmCode;
    private String zEntCodeIso3166Alpha2;
    private String mdmName;
    public String getzSourceSystem() {
        return zSourceSystem;
    }

    public void setzSourceSystem(String zSourceSystem) {
        this.zSourceSystem = zSourceSystem;
    }

    public String getMdmCode() {
        return mdmCode;
    }

    public void setMdmCode(String mdmCode) {
        this.mdmCode = mdmCode;
    }

    public String getzEntCodeIso3166Alpha2() {
        return zEntCodeIso3166Alpha2;
    }

    public void setzEntCodeIso3166Alpha2(String zEntCodeIso3166Alpha2) {
        this.zEntCodeIso3166Alpha2 = zEntCodeIso3166Alpha2;
    }

    public String getMdmName() {
        return mdmName;
    }

    public void setMdmName(String mdmName) {
        this.mdmName = mdmName;
    }


}

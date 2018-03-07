package com.jnj.pangea.common.entry.ems;

public class EMSFMdmCurrenciesEntity {
    private String zSourceSystem;
    private String zCode;
    private String zEntCodeIso4217Alpha;
    private String zName;
    private String zIso4217Numeric;

    public String getzSourceSystem() {
        return zSourceSystem;
    }

    public void setzSourceSystem(String zSourceSystem) {
        this.zSourceSystem = zSourceSystem;
    }

    public String getzCode() {
        return zCode;
    }

    public void setzCode(String zCode) {
        this.zCode = zCode;
    }

    public String getzEntCodeIso4217Alpha() {
        return zEntCodeIso4217Alpha;
    }

    public void setzEntCodeIso4217Alpha(String zEntCodeIso4217Alpha) {
        this.zEntCodeIso4217Alpha = zEntCodeIso4217Alpha;
    }

    public String getzName() {
        return zName;
    }

    public void setzName(String zName) {
        this.zName = zName;
    }

    public String getzIso4217Numeric() {
        return zIso4217Numeric;
    }

    public void setzIso4217Numeric(String zIso4217Numeric) {
        this.zIso4217Numeric = zIso4217Numeric;
    }
}

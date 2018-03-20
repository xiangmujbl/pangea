package com.jnj.pangea.common.entity.ems;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

//public class EMSFMdmCurrenciesEntity{
 public class EMSFMdmCurrenciesEntity extends CommonEntity {
    private String zSourceSystem;
    private String zCode;
    private String zEntCodeIso4217Alpha;
    private String zName;
    private String zIso4217Numeric;

    public EMSFMdmCurrenciesEntity(Map<String, Object> map) {
        super(map);
        setzSourceSystem((String) map.get("zSourceSystem"));
        setzCode((String) map.get("zCode"));
        setzEntCodeIso4217Alpha((String) map.get("zEntCodeIso4217Alpha"));
        setzName((String) map.get("zName"));
        setzIso4217Numeric((String) map.get("zIso4217Numeric"));
    }

    @Override
    public String toString() {
        return "EMSFMdmCurrenciesEntity{" +
                "zSourceSystem='" + zSourceSystem + '\'' +
                ", zCode='" + zCode + '\'' +
                ", zEntCodeIso4217Alpha='" + zEntCodeIso4217Alpha + '\'' +
                ", zName='" + zName + '\'' +
                ", zIso4217Numeric='" + zIso4217Numeric + '\'' +
                '}';
    }

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

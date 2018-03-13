package com.jnj.pangea.common.entity.ems;

public class EMSFMdmUnitsEntity {
    private String zSourceSystem;
    private String mdmSapCode;
    private String mdmName;
    private String zEnterpriseCode;
    private String zUnitsDimension;
    private String mdmIsoCode;

    public String getzSourceSystem() {
        return zSourceSystem;
    }

    public void setzSourceSystem(String zSourceSystem) {
        this.zSourceSystem = zSourceSystem;
    }

    public String getMdmSapCode() {
        return mdmSapCode;
    }

    public void setMdmSapCode(String mdmSapCode) {
        this.mdmSapCode = mdmSapCode;
    }

    public String getMdmName() {
        return mdmName;
    }

    public void setMdmName(String mdmName) {
        this.mdmName = mdmName;
    }

    public String getzEnterpriseCode() {
        return zEnterpriseCode;
    }

    public void setzEnterpriseCode(String zEnterpriseCode) {
        this.zEnterpriseCode = zEnterpriseCode;
    }

    public String getzUnitsDimension() {
        return zUnitsDimension;
    }

    public void setzUnitsDimension(String zUnitsDimension) {
        this.zUnitsDimension = zUnitsDimension;
    }

    public String getMdmIsoCode() {
        return mdmIsoCode;
    }

    public void setMdmIsoCode(String mdmIsoCode) {
        this.mdmIsoCode = mdmIsoCode;
    }
}

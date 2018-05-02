package com.jnj.pangea.edm.material.auom.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * Created by XZhan290 on 2018/2/27.
 */
public class EDMMaterialAuomBo extends BaseBo {

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("localMaterialNumber", this.localMaterialNumber)
                .add("localAuom", this.localAuom)
                .toJsonString();
    }

    private String sourceSystem;
    private String localMaterialNumber;
    private String localAuom;
    private String materialNumber;
    private String localNumerator;
    private String localDenominator;
    private String auom;
    private String numerator;
    private String denominator;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getLocalAuom() {
        return localAuom;
    }

    public void setLocalAuom(String localAuom) {
        this.localAuom = localAuom;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getLocalNumerator() {
        return localNumerator;
    }

    public void setLocalNumerator(String localNumerator) {
        this.localNumerator = localNumerator;
    }

    public String getLocalDenominator() {
        return localDenominator;
    }

    public void setLocalDenominator(String localDenominator) {
        this.localDenominator = localDenominator;
    }

    public String getAuom() {
        return auom;
    }

    public void setAuom(String auom) {
        this.auom = auom;
    }

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    public String getDenominator() {
        return denominator;
    }

    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }
}
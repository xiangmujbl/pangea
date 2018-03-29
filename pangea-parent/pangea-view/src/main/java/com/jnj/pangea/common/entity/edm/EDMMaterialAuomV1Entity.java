package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

/**
 * Created by XZhan290 on 2018/3/5.
 */
public class EDMMaterialAuomV1Entity extends CommonEntity {

        private String localNumerator;
        private String materialNumber;
        private String sourceSystem;
        private String localDenominator;
        private String localAuom;
        private String auom;
        private String denominator;
        private String localMaterialNumber;
        private String numerator;

    public EDMMaterialAuomV1Entity(Map<String, Object> map) {
        super(map);
        setLocalNumerator((String)map.get("localNumerator"));
        setMaterialNumber((String)map.get("materialNumber"));
        setSourceSystem((String)map.get("sourceSystem"));
        setLocalDenominator((String)map.get("localDenominator"));
        setLocalAuom((String)map.get("localAuom"));
        setAuom((String)map.get("auom"));
        setDenominator((String)map.get("denominator"));
        setLocalMaterialNumber((String)map.get("localMaterialNumber"));
        setNumerator((String)map.get("numerator"));

    }

    public String getLocalNumerator() {
        return localNumerator;
    }

    public void setLocalNumerator(String localNumerator) {
        this.localNumerator = localNumerator;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getLocalDenominator() {
        return localDenominator;
    }

    public void setLocalDenominator(String localDenominator) {
        this.localDenominator = localDenominator;
    }

    public String getLocalAuom() {
        return localAuom;
    }

    public void setLocalAuom(String localAuom) {
        this.localAuom = localAuom;
    }

    public String getAuom() {
        return auom;
    }

    public void setAuom(String auom) {
        this.auom = auom;
    }

    public String getDenominator() {
        return denominator;
    }

    public void setDenominator(String denominator) {
        this.denominator = denominator;
    }

    public String getLocalMaterialNumber() {
        return localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getNumerator() {
        return numerator;
    }

    public void setNumerator(String numerator) {
        this.numerator = numerator;
    }

    @Override
    public String toString() {
        return "EDMMaterialAuomV1Entity{" +
                "localNumerator='" + localNumerator + '\'' +
                ", materialNumber='" + materialNumber + '\'' +
                ", sourceSystem='" + sourceSystem + '\'' +
                ", localDenominator='" + localDenominator + '\'' +
                ", localAuom='" + localAuom + '\'' +
                ", auom='" + auom + '\'' +
                ", denominator='" + denominator + '\'' +
                ", localMaterialNumber='" + localMaterialNumber + '\'' +
                ", numerator='" + numerator + '\'' +
                '}';
    }
}

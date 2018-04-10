package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProdLocAttribEntity extends CommonEntity {

    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String schdAttrbName1;
    private String schAttrbDesc1;
    private String schdAttrbName2;
    private String schAttrbDesc2;
    private String schdAttrbName3;
    private String schAttrbDesc3;
    private String supplyGroup;
    private String minShelfLife;
    private String minMinShelfLife;

    public PlanCnsProdLocAttribEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalPlant((String) map.get("localPlant"));
        setSchdAttrbName1((String) map.get("schdAttrbName1"));
        setSchAttrbDesc1((String) map.get("schAttrbDesc1"));
        setSchdAttrbName2((String) map.get("schdAttrbName2"));
        setSchAttrbDesc2((String) map.get("schAttrbDesc2"));
        setSchdAttrbName3((String) map.get("schdAttrbName3"));
        setSchAttrbDesc3((String) map.get("schAttrbDesc3"));
        setSupplyGroup((String) map.get("supplyGroup"));
        setMinShelfLife((String) map.get("minShelfLife"));
        setMinMinShelfLife((String) map.get("minMinShelfLife"));
    }

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

    public String getLocalPlant() {
        return localPlant;
    }

    public void setLocalPlant(String localPlant) {
        this.localPlant = localPlant;
    }

    public String getSchdAttrbName1() {
        return schdAttrbName1;
    }

    public void setSchdAttrbName1(String schdAttrbName1) {
        this.schdAttrbName1 = schdAttrbName1;
    }

    public String getSchAttrbDesc1() {
        return schAttrbDesc1;
    }

    public void setSchAttrbDesc1(String schAttrbDesc1) {
        this.schAttrbDesc1 = schAttrbDesc1;
    }

    public String getSchdAttrbName2() {
        return schdAttrbName2;
    }

    public void setSchdAttrbName2(String schdAttrbName2) {
        this.schdAttrbName2 = schdAttrbName2;
    }

    public String getSchAttrbDesc2() {
        return schAttrbDesc2;
    }

    public void setSchAttrbDesc2(String schAttrbDesc2) {
        this.schAttrbDesc2 = schAttrbDesc2;
    }

    public String getSchdAttrbName3() {
        return schdAttrbName3;
    }

    public void setSchdAttrbName3(String schdAttrbName3) {
        this.schdAttrbName3 = schdAttrbName3;
    }

    public String getSchAttrbDesc3() {
        return schAttrbDesc3;
    }

    public void setSchAttrbDesc3(String schAttrbDesc3) {
        this.schAttrbDesc3 = schAttrbDesc3;
    }

    public String getSupplyGroup() {
        return supplyGroup;
    }

    public void setSupplyGroup(String supplyGroup) {
        this.supplyGroup = supplyGroup;
    }

    public String getMinShelfLife() {
        return minShelfLife;
    }

    public void setMinShelfLife(String minShelfLife) {
        this.minShelfLife = minShelfLife;
    }

    public String getMinMinShelfLife() {
        return minMinShelfLife;
    }

    public void setMinMinShelfLife(String minMinShelfLife) {
        this.minMinShelfLife = minMinShelfLife;
    }
}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProdLocAttribEntity extends CommonEntity {

    private String sourceSystem;
    private String localMaterialNumber;
    private String localPlant;
    private String SchdAttrbName1;
    private String SchAttrbDesc1;
    private String SchdAttrbName2;
    private String SchAttrbDesc2;
    private String SchdAttrbName3;
    private String SchAttrbDesc3;
    private String SupplyGroup;
    private String minShelfLife;
    private String minMinshelfLife;

    public PlanCnsProdLocAttribEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setLocalPlant((String) map.get("localPlant"));
        setSchdAttrbName1((String) map.get("SchdAttrbName1"));
        setSchAttrbDesc1((String) map.get("SchAttrbDesc1"));
        setSchdAttrbName2((String) map.get("SchdAttrbName2"));
        setSchAttrbDesc2((String) map.get("SchAttrbDesc2"));
        setSchdAttrbName3((String) map.get("SchdAttrbName3"));
        setSchAttrbDesc3((String) map.get("SchAttrbDesc3"));
        setSupplyGroup((String) map.get("SupplyGroup"));
        setMinShelfLife((String) map.get("minShelfLife"));
        setMinMinshelfLife((String) map.get("minMinshelfLife"));
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
        return SchdAttrbName1;
    }

    public void setSchdAttrbName1(String schdAttrbName1) {
        SchdAttrbName1 = schdAttrbName1;
    }

    public String getSchAttrbDesc1() {
        return SchAttrbDesc1;
    }

    public void setSchAttrbDesc1(String schAttrbDesc1) {
        SchAttrbDesc1 = schAttrbDesc1;
    }

    public String getSchdAttrbName2() {
        return SchdAttrbName2;
    }

    public void setSchdAttrbName2(String schdAttrbName2) {
        SchdAttrbName2 = schdAttrbName2;
    }

    public String getSchAttrbDesc2() {
        return SchAttrbDesc2;
    }

    public void setSchAttrbDesc2(String schAttrbDesc2) {
        SchAttrbDesc2 = schAttrbDesc2;
    }

    public String getSchdAttrbName3() {
        return SchdAttrbName3;
    }

    public void setSchdAttrbName3(String schdAttrbName3) {
        SchdAttrbName3 = schdAttrbName3;
    }

    public String getSchAttrbDesc3() {
        return SchAttrbDesc3;
    }

    public void setSchAttrbDesc3(String schAttrbDesc3) {
        SchAttrbDesc3 = schAttrbDesc3;
    }

    public String getSupplyGroup() {
        return SupplyGroup;
    }

    public void setSupplyGroup(String supplyGroup) {
        SupplyGroup = supplyGroup;
    }

    public String getMinShelfLife() {
        return minShelfLife;
    }

    public void setMinShelfLife(String minShelfLife) {
        this.minShelfLife = minShelfLife;
    }

    public String getMinMinshelfLife() {
        return minMinshelfLife;
    }

    public void setMinMinshelfLife(String minMinshelfLife) {
        this.minMinshelfLife = minMinshelfLife;
    }
}

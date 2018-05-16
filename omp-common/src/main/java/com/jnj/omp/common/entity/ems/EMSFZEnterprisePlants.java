package com.jnj.omp.common.entity.ems;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class EMSFZEnterprisePlants extends CommonEntity {

    private String zPlantSourceSystem;
    private String zPlant;
    private String zEntPlantNumber;
    private String zSite;
    private String zEntPlantType;
    private String zRegion;

    public EMSFZEnterprisePlants(Map<String, Object> map) {
        super(map);
        setzPlantSourceSystem((String) map.get("zPlantSourceSystem"));
        setzPlant((String) map.get("zPlant"));
        setzEntPlantNumber((String) map.get("zEntPlantNumber"));
        setzSite((String) map.get("zSite"));
        setzEntPlantType((String) map.get("zEntPlantType"));
        setzRegion((String) map.get("zRegion"));
    }

    public String getzPlantSourceSystem() {
        return zPlantSourceSystem;
    }

    public void setzPlantSourceSystem(String zPlantSourceSystem) {
        this.zPlantSourceSystem = zPlantSourceSystem;
    }

    public String getzPlant() {
        return zPlant;
    }

    public void setzPlant(String zPlant) {
        this.zPlant = zPlant;
    }

    public String getzEntPlantNumber() {
        return zEntPlantNumber;
    }

    public void setzEntPlantNumber(String zEntPlantNumber) {
        this.zEntPlantNumber = zEntPlantNumber;
    }

    public String getzSite() {
        return zSite;
    }

    public void setzSite(String zSite) {
        this.zSite = zSite;
    }

    public String getzEntPlantType() {
        return zEntPlantType;
    }

    public void setzEntPlantType(String zEntPlantType) {
        this.zEntPlantType = zEntPlantType;
    }

    public String getzRegion() {
        return zRegion;
    }

    public void setzRegion(String zRegion) {
        this.zRegion = zRegion;
    }
}

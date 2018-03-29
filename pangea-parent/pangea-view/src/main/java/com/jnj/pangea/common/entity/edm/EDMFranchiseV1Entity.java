package com.jnj.pangea.common.entity.edm;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EDMFranchiseV1Entity extends CommonEntity {

    private String franchise;
    private String franchiseDescription;

    public EDMFranchiseV1Entity(Map<String, Object> map) {
        super(map);

        setFranchise((String) map.get("franchise"));
        setFranchiseDescription((String) map.get("franchiseDescription"));
    }

    public String getFranchise() {
        return this.franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getFranchiseDescription() {
        return this.franchiseDescription;
    }

    public void setFranchiseDescription(String franchiseDescription) {
        this.franchiseDescription = franchiseDescription;
    }

}

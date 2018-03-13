package com.jnj.pangea.common.entity.ngems;

import com.jnj.pangea.common.CommonEntity;

public class FranchiseEntity extends CommonEntity {
    private String franchise;
    private String franchiseDescription;

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public String getFranchiseDescription() {
        return franchiseDescription;
    }

    public void setFranchiseDescription(String franchiseDescription) {
        this.franchiseDescription = franchiseDescription;
    }
}

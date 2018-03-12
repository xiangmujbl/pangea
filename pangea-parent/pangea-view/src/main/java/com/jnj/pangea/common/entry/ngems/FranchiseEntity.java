package com.jnj.pangea.common.entry.ngems;

import com.jnj.pangea.common.CommonEntry;

public class FranchiseEntity extends CommonEntry{
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

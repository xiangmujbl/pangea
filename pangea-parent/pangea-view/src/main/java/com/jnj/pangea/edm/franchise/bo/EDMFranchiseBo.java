package com.jnj.pangea.edm.franchise.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

import java.util.Base64;

public class EDMFranchiseBo extends BaseBo {
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

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("franchise", this.franchise)
                .toJsonString();
    }
}

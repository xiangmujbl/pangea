package com.jnj.pangea.edm.franchise_v1.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

/**
 * EDMFranchise- rework - Curation
 * AEAZ-8299
 */
public class EDMFranchiseV1Bo extends BaseBo {

    private String  franchise ;
    private String franchiseDescription ;

    
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("franchise", this.franchise)
                .toJsonString();
    }

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
    public String toString() {
        return "EDMFranchiseBo{" +
                "franchise='" + franchise + '\'' +
                ", franchiseDescription='" + franchiseDescription + '\'' +
                '}';
    }
}

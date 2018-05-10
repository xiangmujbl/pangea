package com.jnj.pangea.omp.gdm_cluster.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmClusterBo extends BaseBo {

    private String clusterId;
    private String  activeFCTERP;
    private String  clusterDescription;
    private String  clusterNr;
    private String  countryId;
    private String  subFranchise;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("clusterId", this.clusterId)
                .toJsonString();
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

    public String getActiveFCTERP() {
        return activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getClusterDescription() {
        return clusterDescription;
    }

    public void setClusterDescription(String clusterDescription) {
        this.clusterDescription = clusterDescription;
    }

    public String getClusterNr() {
        return clusterNr;
    }

    public void setClusterNr(String clusterNr) {
        this.clusterNr = clusterNr;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getSubFranchise() {
        return subFranchise;
    }

    public void setSubFranchise(String subFranchise) {
        this.subFranchise = subFranchise;
    }
}

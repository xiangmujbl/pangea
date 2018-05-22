package com.jnj.pangea.omp.gdm_subcluster.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmSubClusterBo extends BaseBo {

    private String subClusterId;
    private String description;
    private String clusterId;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("subClusterId", this.subClusterId)
                .toJsonString();
    }

    public String getSubClusterId() {
        return subClusterId;
    }

    public void setSubClusterId(String subClusterId) {
        this.subClusterId = subClusterId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClusterId() {
        return clusterId;
    }

    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
    }

}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsClustersEntity extends CommonEntity {

    private String sourceSystem;
    private String countryId;
    private String cluster;
    private String subCluster;
    private String clusterDesc;
    private String subClusterDesc;

    public PlanCnsClustersEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setCountryId((String) map.get("countryId"));
        setCluster((String) map.get("cluster"));
        setSubCluster((String) map.get("subCluster"));
        setClusterDesc((String) map.get("clusterDesc"));
        setSubClusterDesc((String) map.get("subClusterDesc"));
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCluster() {
        return this.cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getSubCluster() {
        return this.subCluster;
    }

    public void setSubCluster(String subCluster) {
        this.subCluster = subCluster;
    }

    public String getClusterDesc() {
        return this.clusterDesc;
    }

    public void setClusterDesc(String clusterDesc) {
        this.clusterDesc = clusterDesc;
    }

    public String getSubClusterDesc() {
        return this.subClusterDesc;
    }

    public void setSubClusterDesc(String subClusterDesc) {
        this.subClusterDesc = subClusterDesc;
    }

}

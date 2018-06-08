package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsOrdRejEntity extends CommonEntity {

    private String salesOrg;
    private String rejCd;

    public PlanCnsOrdRejEntity(Map<String, Object> map) {
        super(map);

        setSalesOrg((String) map.get("salesOrg"));
        setRejCd((String) map.get("rejCd"));
    }

    public String getSalesOrg() {
        return this.salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getRejCd() {
        return this.rejCd;
    }

    public void setRejCd(String rejCd) {
        this.rejCd = rejCd;
    }

}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsCertDeterEntity extends CommonEntity {

    private String certaintyKey;
    private String salesOrg;
    private String orderType;
    private String itemCategory;

    public PlanCnsCertDeterEntity(Map<String, Object> map) {
        super(map);

        setCertaintyKey((String) map.get("certaintyKey"));
        setSalesOrg((String) map.get("salesOrg"));
        setOrderType((String) map.get("orderType"));
        setItemCategory((String) map.get("itemCategory"));
    }

    public String getCertaintyKey() {
        return this.certaintyKey;
    }

    public void setCertaintyKey(String certaintyKey) {
        this.certaintyKey = certaintyKey;
    }

    public String getSalesOrg() {
        return this.salesOrg;
    }

    public void setSalesOrg(String salesOrg) {
        this.salesOrg = salesOrg;
    }

    public String getOrderType() {
        return this.orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getItemCategory() {
        return this.itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

}

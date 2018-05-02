package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsDpPosEntity extends CommonEntity {

    private String localMaterial;
    private String customer;
    private String yearMonth;
    private String quantity;

    public PlanCnsDpPosEntity(Map<String, Object> map) {
        super(map);

        setLocalMaterial((String) map.get("localMaterial"));
        setCustomer((String) map.get("customer"));
        setYearMonth((String) map.get("yearMonth"));
        setQuantity((String) map.get("quantity"));
    }

    public String getLocalMaterial() {
        return this.localMaterial;
    }

    public void setLocalMaterial(String localMaterial) {
        this.localMaterial = localMaterial;
    }

    public String getCustomer() {
        return this.customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getYearMonth() {
        return this.yearMonth;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}

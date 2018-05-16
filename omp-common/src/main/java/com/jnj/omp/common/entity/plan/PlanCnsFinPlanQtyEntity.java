package com.jnj.omp.common.entity.plan;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsFinPlanQtyEntity extends CommonEntity {

    private String localMaterialNumber;
    private String identifier;
    private String country;
    private String currency;
    private String unitId;
    private String quantity;

    public PlanCnsFinPlanQtyEntity(Map<String, Object> map) {
        super(map);

        setLocalMaterialNumber((String) map.get("localMaterialNumber"));
        setIdentifier((String) map.get("identifier"));
        setCountry((String) map.get("country"));
        setCurrency((String) map.get("currency"));
        setUnitId((String) map.get("unitId"));
        setQuantity((String) map.get("quantity"));
    }

    public String getLocalMaterialNumber() {
        return this.localMaterialNumber;
    }

    public void setLocalMaterialNumber(String localMaterialNumber) {
        this.localMaterialNumber = localMaterialNumber;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUnitId() {
        return this.unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}

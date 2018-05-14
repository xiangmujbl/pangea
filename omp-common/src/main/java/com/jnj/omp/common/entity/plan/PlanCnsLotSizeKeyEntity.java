package com.jnj.omp.common.entity.plan;

import com.jnj.omp.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsLotSizeKeyEntity extends CommonEntity {

    private String lotSizeKey;
    private String comments;
    private String lotSizeKeyDescription;
    private String period;
    private String quantity;

    public PlanCnsLotSizeKeyEntity(Map<String, Object> map) {
        super(map);

        setLotSizeKey((String) map.get("lotSizeKey"));
        setComments((String) map.get("comments"));
        setLotSizeKeyDescription((String) map.get("lotSizeKeyDescription"));
        setPeriod((String) map.get("period"));
        setQuantity((String) map.get("quantity"));
    }

    public String getLotSizeKey() {
        return this.lotSizeKey;
    }

    public void setLotSizeKey(String lotSizeKey) {
        this.lotSizeKey = lotSizeKey;
    }

    public String getComments() {
        return this.comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLotSizeKeyDescription() {
        return this.lotSizeKeyDescription;
    }

    public void setLotSizeKeyDescription(String lotSizeKeyDescription) {
        this.lotSizeKeyDescription = lotSizeKeyDescription;
    }

    public String getPeriod() {
        return this.period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getQuantity() {
        return this.quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}

package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProductCustomerEntity extends CommonEntity {

    private String sourceSystem;
    private String productId;
    private String demandGroup;
    private String productStatus;
    private String minStock;
    private String maxStock;
    private String stockLevel;
    private String uom;
    private String preferredDC;
    private String leadTime;
    private String revenueRecognitionOffset;

    public PlanCnsProductCustomerEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setProductId((String) map.get("productId"));
        setDemandGroup((String) map.get("demandGroup"));
        setProductStatus((String) map.get("productStatus"));
        setMinStock((String) map.get("minStock"));
        setMaxStock((String) map.get("maxStock"));
        setStockLevel((String) map.get("stockLevel"));
        setUom((String) map.get("uom"));
        setPreferredDC((String) map.get("preferredDC"));
        setLeadTime((String) map.get("leadTime"));
        setRevenueRecognitionOffset((String) map.get("revenueRecognitionOffset"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDemandGroup() {
        return demandGroup;
    }

    public void setDemandGroup(String demandGroup) {
        this.demandGroup = demandGroup;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getMinStock() {
        return minStock;
    }

    public void setMinStock(String minStock) {
        this.minStock = minStock;
    }

    public String getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(String maxStock) {
        this.maxStock = maxStock;
    }

    public String getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(String stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

    public String getPreferredDC() {
        return preferredDC;
    }

    public void setPreferredDC(String preferredDC) {
        this.preferredDC = preferredDC;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getRevenueRecognitionOffset() {
        return revenueRecognitionOffset;
    }

    public void setRevenueRecognitionOffset(String revenueRecognitionOffset) {
        this.revenueRecognitionOffset = revenueRecognitionOffset;
    }

    @Override
    public String toString() {
        return "PlanCnsProductCustomerEntity{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", productId='" + productId + '\'' +
                ", demandGroup='" + demandGroup + '\'' +
                ", productStatus='" + productStatus + '\'' +
                ", minStock='" + minStock + '\'' +
                ", maxStock='" + maxStock + '\'' +
                ", stockLevel='" + stockLevel + '\'' +
                ", uom='" + uom + '\'' +
                ", preferredDC='" + preferredDC + '\'' +
                ", leadTime='" + leadTime + '\'' +
                ", revenueRecognitionOffset='" + revenueRecognitionOffset + '\'' +
                '}';
    }
}

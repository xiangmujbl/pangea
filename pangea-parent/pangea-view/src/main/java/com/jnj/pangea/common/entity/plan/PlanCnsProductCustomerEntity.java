package com.jnj.pangea.common.entity.plan;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class PlanCnsProductCustomerEntity extends CommonEntity {

    private String sourceSystem;
    private String customerId;
    private String productId;
    private String productStatus;
    private String moq;
    private String ioq;
    private String stockLevel;
    private String uom;
    private String locationId;
    private String norm;
    private String leadTime;
    private String roundingThreshold;

    public PlanCnsProductCustomerEntity(Map<String, Object> map) {
        super(map);
        setSourceSystem((String) map.get("sourceSystem"));
        setCustomerId((String) map.get("customerId"));
        setProductId((String) map.get("productId"));
        setProductStatus((String) map.get("productStatus"));
        setMoq((String) map.get("moq"));
        setIoq((String) map.get("ioq"));
        setStockLevel((String) map.get("stockLevel"));
        setUom((String) map.get("uom"));
        setLocationId((String) map.get("locationId"));
        setNorm((String) map.get("norm"));
        setLeadTime((String) map.get("leadTime"));
        setRoundingThreshold((String) map.get("roundingThreshold"));
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getMoq() {
        return moq;
    }

    public void setMoq(String moq) {
        this.moq = moq;
    }

    public String getIoq() {
        return ioq;
    }

    public void setIoq(String ioq) {
        this.ioq = ioq;
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getNorm() {
        return norm;
    }

    public void setNorm(String norm) {
        this.norm = norm;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getRoundingThreshold() {
        return roundingThreshold;
    }

    public void setRoundingThreshold(String roundingThreshold) {
        this.roundingThreshold = roundingThreshold;
    }

    @Override
    public String toString() {
        return "PlanCnsProductCustomerEntity{" +
                "sourceSystem='" + sourceSystem + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", productStatus='" + productStatus + '\'' +
                ", moq='" + moq + '\'' +
                ", ioq='" + ioq + '\'' +
                ", stockLevel='" + stockLevel + '\'' +
                ", uom='" + uom + '\'' +
                ", locationId='" + locationId + '\'' +
                ", norm='" + norm + '\'' +
                ", leadTime='" + leadTime + '\'' +
                ", roundingThreshold='" + roundingThreshold + '\'' +
                '}';
    }
}

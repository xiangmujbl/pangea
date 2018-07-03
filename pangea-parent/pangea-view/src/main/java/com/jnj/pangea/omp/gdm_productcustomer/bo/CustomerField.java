package com.jnj.pangea.omp.gdm_productcustomer.bo;

public class CustomerField {
    private double moq = 0;
    private double ioq = 0;
    private double stockLevel = 0;
    private String LeadTime = "";
    private String Norm = "";
    private double RoundingThreshold = 0;
    private double RoundingThresholdCount = 0;
    private String locationId;
    private boolean locationIdFlag=true;
    private boolean productStatusFlag = false;

    public double getMoq() {
        return moq;
    }

    public void setMoq(double moq) {
        this.moq = moq;
    }

    public double getIoq() {
        return ioq;
    }

    public void setIoq(double ioq) {
        this.ioq = ioq;
    }

    public double getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(double stockLevel) {
        this.stockLevel = stockLevel;
    }

    public String getLeadTime() {
        return LeadTime;
    }

    public void setLeadTime(String leadTime) {
        LeadTime = leadTime;
    }

    public String getNorm() {
        return Norm;
    }

    public void setNorm(String norm) {
        Norm = norm;
    }

    public double getRoundingThreshold() {
        return RoundingThreshold;
    }

    public void setRoundingThreshold(double roundingThreshold) {
        RoundingThreshold = roundingThreshold;
    }

    public double getRoundingThresholdCount() {
        return RoundingThresholdCount;
    }

    public void setRoundingThresholdCount(double roundingThresholdCount) {
        RoundingThresholdCount = roundingThresholdCount;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public boolean isLocationIdFlag() {
        return locationIdFlag;
    }

    public void setLocationIdFlag(boolean locationIdFlag) {
        this.locationIdFlag = locationIdFlag;
    }

    public boolean isProductStatusFlag() {
        return productStatusFlag;
    }

    public void setProductStatusFlag(boolean productStatusFlag) {
        this.productStatusFlag = productStatusFlag;
    }
}

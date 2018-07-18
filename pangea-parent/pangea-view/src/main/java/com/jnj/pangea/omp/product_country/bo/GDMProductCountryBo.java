package com.jnj.pangea.omp.product_country.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class GDMProductCountryBo extends BaseBo {
    private String uniqueId;
    private String activeFCTERP;
    private String countryGroup;
    private String countryId;
    private String DPPlannerID;
    private String DPSegmentation;
    private String productClassification;
    private String productId;
    private String productStatus;
    private String rootSize;
    private String segmentation;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("uniqueId", this.uniqueId)
                .toJsonString();
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getActiveFCTERP() {
        return activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getCountryGroup() {
        return countryGroup;
    }

    public void setCountryGroup(String countryGroup) {
        this.countryGroup = countryGroup;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getDPPlannerID() {
        return DPPlannerID;
    }

    public void setDPPlannerID(String DPPlannerID) {
        this.DPPlannerID = DPPlannerID;
    }

    public String getDPSegmentation() {
        return DPSegmentation;
    }

    public void setDPSegmentation(String DPSegmentation) {
        this.DPSegmentation = DPSegmentation;
    }

    public String getProductClassification() {
        return productClassification;
    }

    public void setProductClassification(String productClassification) {
        this.productClassification = productClassification;
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

    public String getRootSize() {
        return rootSize;
    }

    public void setRootSize(String rootSize) {
        this.rootSize = rootSize;
    }

    public String getSegmentation() {
        return segmentation;
    }

    public void setSegmentation(String segmentation) {
        this.segmentation = segmentation;
    }
}

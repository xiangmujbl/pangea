package com.jnj.pangea.omp.gdm_product.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmProductBo extends BaseBo {

    private String productId;
    private String active;
    private String activeFCTERP;
    private String activeOPRERP;
    private String activeSOPERP;
    private String color;
    private String description;
    private String planningHierarchy1;
    private String planningHierarchy1Desc;
    private String planningHierarchy2;
    private String planningHierarchy2Desc;
    private String planningHierarchy3;
    private String planningHierarchy3Desc;
    private String planningHierarchy4;
    private String planningHierarchy4Desc;
    private String planningHierarchy5;
    private String planningHierarchy5Desc;
    private String planningHierarchy6;
    private String planningHierarchy6Desc;
    private String planningHierarchy7;
    private String planningHierarchy7Desc;
    private String shortDescription;
    private String sourceLocationId;
    private String subFranchise;
    private String technology;
    private String unitId;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("productId", this.productId)
                .toJsonString();
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getActiveFCTERP() {
        return activeFCTERP;
    }

    public void setActiveFCTERP(String activeFCTERP) {
        this.activeFCTERP = activeFCTERP;
    }

    public String getActiveOPRERP() {
        return activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlanningHierarchy1() {
        return this.planningHierarchy1;
    }

    public void setPlanningHierarchy1(String planningHierarchy1) {
        this.planningHierarchy1 = planningHierarchy1;
    }

    public String getPlanningHierarchy1Desc() {
        return this.planningHierarchy1Desc;
    }

    public void setPlanningHierarchy1Desc(String planningHierarchy1Desc) {
        this.planningHierarchy1Desc = planningHierarchy1Desc;
    }

    public String getPlanningHierarchy2() {
        return this.planningHierarchy2;
    }

    public void setPlanningHierarchy2(String planningHierarchy2) {
        this.planningHierarchy2 = planningHierarchy2;
    }

    public String getPlanningHierarchy2Desc() {
        return this.planningHierarchy2Desc;
    }

    public void setPlanningHierarchy2Desc(String planningHierarchy2Desc) {
        this.planningHierarchy2Desc = planningHierarchy2Desc;
    }

    public String getPlanningHierarchy3() {
        return this.planningHierarchy3;
    }

    public void setPlanningHierarchy3(String planningHierarchy3) {
        this.planningHierarchy3 = planningHierarchy3;
    }

    public String getPlanningHierarchy3Desc() {
        return this.planningHierarchy3Desc;
    }

    public void setPlanningHierarchy3Desc(String planningHierarchy3Desc) {
        this.planningHierarchy3Desc = planningHierarchy3Desc;
    }

    public String getPlanningHierarchy4() {
        return this.planningHierarchy4;
    }

    public void setPlanningHierarchy4(String planningHierarchy4) {
        this.planningHierarchy4 = planningHierarchy4;
    }

    public String getPlanningHierarchy4Desc() {
        return this.planningHierarchy4Desc;
    }

    public void setPlanningHierarchy4Desc(String planningHierarchy4Desc) {
        this.planningHierarchy4Desc = planningHierarchy4Desc;
    }

    public String getPlanningHierarchy5() {
        return this.planningHierarchy5;
    }

    public void setPlanningHierarchy5(String planningHierarchy5) {
        this.planningHierarchy5 = planningHierarchy5;
    }

    public String getPlanningHierarchy5Desc() {
        return this.planningHierarchy5Desc;
    }

    public void setPlanningHierarchy5Desc(String planningHierarchy5Desc) {
        this.planningHierarchy5Desc = planningHierarchy5Desc;
    }

    public String getPlanningHierarchy6() {
        return this.planningHierarchy6;
    }

    public void setPlanningHierarchy6(String planningHierarchy6) {
        this.planningHierarchy6 = planningHierarchy6;
    }

    public String getPlanningHierarchy6Desc() {
        return this.planningHierarchy6Desc;
    }

    public void setPlanningHierarchy6Desc(String planningHierarchy6Desc) {
        this.planningHierarchy6Desc = planningHierarchy6Desc;
    }

    public String getPlanningHierarchy7() {
        return this.planningHierarchy7;
    }

    public void setPlanningHierarchy7(String planningHierarchy7) {
        this.planningHierarchy7 = planningHierarchy7;
    }

    public String getPlanningHierarchy7Desc() {
        return this.planningHierarchy7Desc;
    }

    public void setPlanningHierarchy7Desc(String planningHierarchy7Desc) {
        this.planningHierarchy7Desc = planningHierarchy7Desc;
    }

    public String getShortDescription() {
        return this.shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSourceLocationId() {
        return this.sourceLocationId;
    }

    public void setSourceLocationId(String sourceLocationId) {
        this.sourceLocationId = sourceLocationId;
    }

    public String getSubFranchise() {
        return this.subFranchise;
    }

    public void setSubFranchise(String subFranchise) {
        this.subFranchise = subFranchise;
    }

    public String getTechnology() {
        return this.technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getUnitId() {
        return this.unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

}

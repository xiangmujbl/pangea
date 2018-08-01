package com.jnj.pangea.omp.gdm_step_resouce.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPGdmStepResourceProcessBo extends BaseBo {

    private String stepResourceId;
    private String machineId;
    private String minQuantity;
    private String operationId;
    private String resourceId;
    private String active;
    private String activeOPRERP;
    private String activeSOPERP;
    private String quantity;
    private String stepResourceType;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance().makeJsonObject("stepResourceId",stepResourceId).toJsonString();
    }

    public String getStepResourceId() {
        return stepResourceId;
    }

    public void setStepResourceId(String stepResourceId) {
        this.stepResourceId = stepResourceId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(String minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStepResourceType() {
        return stepResourceType;
    }

    public void setStepResourceType(String stepResourceType) {
        this.stepResourceType = stepResourceType;
    }
}

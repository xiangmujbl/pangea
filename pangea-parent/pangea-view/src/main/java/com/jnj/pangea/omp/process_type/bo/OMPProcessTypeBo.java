package com.jnj.pangea.omp.process_type.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class OMPProcessTypeBo extends BaseBo {

    private String processTypeId;
    private String activeOPRERP;
    private String activeSOPERP;
    private String label;

    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("processTypeId", this.processTypeId)
                .toJsonString();
    }

    public String getProcessTypeId() {
        return this.processTypeId;
    }

    public void setProcessTypeId(String processTypeId) {
        this.processTypeId = processTypeId;
    }

    public String getActiveOPRERP() {
        return this.activeOPRERP;
    }

    public void setActiveOPRERP(String activeOPRERP) {
        this.activeOPRERP = activeOPRERP;
    }

    public String getActiveSOPERP() {
        return this.activeSOPERP;
    }

    public void setActiveSOPERP(String activeSOPERP) {
        this.activeSOPERP = activeSOPERP;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

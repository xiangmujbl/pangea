package com.jnj.pangea.plan.cns_dem_grp_asgn.bo;

import com.jnj.adf.client.api.remote.RawDataHelper;
import com.jnj.pangea.common.BaseBo;

public class PlanCnsDemGrpAsgnBo extends BaseBo {

    private String sourceSystem;
    private String customerShipTo;
    private String subFranchise;
    private String group;
    private String fromDate;
    private String toDate;

    // TODO add keys
    @Override
    public String getKey() {
        return RawDataHelper.getInstance()
                .makeJsonObject("sourceSystem", this.sourceSystem)
                .add("customerShipTo", this.customerShipTo)
                .add("subFranchise", this.subFranchise)
                .toJsonString();
    }

    public String getSourceSystem() {
        return this.sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getCustomerShipTo() {
        return this.customerShipTo;
    }

    public void setCustomerShipTo(String customerShipTo) {
        this.customerShipTo = customerShipTo;
    }

    public String getSubFranchise() {
        return this.subFranchise;
    }

    public void setSubFranchise(String subFranchise) {
        this.subFranchise = subFranchise;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

}

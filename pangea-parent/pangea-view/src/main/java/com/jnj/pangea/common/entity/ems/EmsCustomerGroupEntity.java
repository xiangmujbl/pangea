package com.jnj.pangea.common.entity.ems;

import com.jnj.pangea.common.entity.CommonEntity;

import java.util.Map;

public class EmsCustomerGroupEntity extends CommonEntity {

    private String sourceSystem;
    private String customerShipTo;
    private String subFranchise;
    private String planningCustomerGroup;
    private String fromDate;
    private String toDate;

    public EmsCustomerGroupEntity(Map<String, Object> map) {
        super(map);

        setSourceSystem((String) map.get("sourceSystem"));
        setCustomerShipTo((String) map.get("customerShipTo"));
        setSubFranchise((String) map.get("subFranchise"));
        setPlanningCustomerGroup((String) map.get("planningCustomerGroup"));
        setFromDate((String) map.get("fromDate"));
        setToDate((String) map.get("toDate"));
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

    public String getPlanningCustomerGroup() {
        return this.planningCustomerGroup;
    }

    public void setPlanningCustomerGroup(String planningCustomerGroup) {
        this.planningCustomerGroup = planningCustomerGroup;
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

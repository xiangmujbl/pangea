package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import java.util.List;


public class PlanCnsPlnSplLocDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_SPL_PLN_LOC = "/plan/cns_spl_pln_loc";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCALNUMBER = "localNumber";
    public static final String VENDORORCUSTOMER = "vendorOrCustomer";
    public static final String LOCAL_PLANT = "localPlant";

    private static PlanCnsPlnSplLocDaoImpl instance;

    public static PlanCnsPlnSplLocDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlnSplLocDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlnSplLocEntity getEntityWithSourceSystemAndLocalNumber(String sourceSystem, String localNumber) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem).and(LOCALNUMBER).is(localNumber).toQueryString();
        return queryForObject(PLAN_CNS_SPL_PLN_LOC, queryString, PlanCnsPlnSplLocEntity.class);
    }

    public PlanCnsPlnSplLocEntity getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(String sourceSystem, String localNumber, String vendorOrCustomer) {
        if(!(sourceSystem.isEmpty() && localNumber.isEmpty() && vendorOrCustomer.isEmpty()) && sourceSystem != null && localNumber != null && vendorOrCustomer != null) {
            String queryString = QueryHelper.buildCriteria(LOCALNUMBER).is(localNumber)
                    .and(VENDORORCUSTOMER).is(vendorOrCustomer)
                    .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(PLAN_CNS_SPL_PLN_LOC, queryString, PlanCnsPlnSplLocEntity.class);
        }
        return null;
    }


    public List<PlanCnsPlnSplLocEntity> getEntityListWithSourceSystemLocalNumberAndVendorOrCustomer(String sourceSystem, String localNumber, String vendorOrCustomer) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localNumber) && StringUtils.isNotEmpty(vendorOrCustomer)) {
            String queryString = QueryHelper.buildCriteria(LOCALNUMBER).is(localNumber)
                    .and(VENDORORCUSTOMER).is(vendorOrCustomer)
                    .and(SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForList(PLAN_CNS_SPL_PLN_LOC, queryString, PlanCnsPlnSplLocEntity.class);
        }
        return null;
    }

    public List<PlanCnsPlnSplLocEntity> getEntitiesWithSourceSystemAndLocalPlantNumber (String sourceSystem, String localPlantNumber) {
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                .and(LOCALNUMBER).is(localPlantNumber).toQueryString();
        return queryForList(PLAN_CNS_SPL_PLN_LOC,queryString,PlanCnsPlnSplLocEntity.class);
    }
}
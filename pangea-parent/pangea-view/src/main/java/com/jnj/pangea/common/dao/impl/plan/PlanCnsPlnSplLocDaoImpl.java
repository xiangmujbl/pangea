package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsPlnSplLocEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import java.util.List;


public class PlanCnsPlnSplLocDaoImpl extends CommonDaoImpl {
    private static PlanCnsPlnSplLocDaoImpl instance;

    public static PlanCnsPlnSplLocDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsPlnSplLocDaoImpl();
        }
        return instance;
    }

    public PlanCnsPlnSplLocEntity getEntityWithSourceSystemAndLocalNumber(String sourceSystem, String localNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SPL_PLN_LOC.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.PLAN_CNS_SPL_PLN_LOC.LOCALNUMBER).is(localNumber).toQueryString();
        return queryForObject(IConstant.REGION.PLAN_CNS_SPL_PLN_LOC, queryString, PlanCnsPlnSplLocEntity.class);
    }

    public PlanCnsPlnSplLocEntity getEntityWithSourceSystemLocalNumberAndVendorOrCustomer(String sourceSystem, String localNumber, String vendorOrCustomer) {
        if(!(sourceSystem.isEmpty() && localNumber.isEmpty() && vendorOrCustomer.isEmpty()) && sourceSystem != null && localNumber != null && vendorOrCustomer != null) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SPL_PLN_LOC.LOCALNUMBER).is(localNumber)
                    .and(IConstant.PLAN_CNS_SPL_PLN_LOC.VENDORORCUSTOMER).is(vendorOrCustomer)
                    .and(IConstant.PLAN_CNS_SPL_PLN_LOC.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForObject(IConstant.REGION.CNS_SPL_PLN_LOC, queryString, PlanCnsPlnSplLocEntity.class);
        }
        return null;
    }


    public List<PlanCnsPlnSplLocEntity> getEntityListWithSourceSystemLocalNumberAndVendorOrCustomer(String sourceSystem, String localNumber, String vendorOrCustomer) {
        if (StringUtils.isNotEmpty(sourceSystem) && StringUtils.isNotEmpty(localNumber) && StringUtils.isNotEmpty(vendorOrCustomer)) {
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SPL_PLN_LOC.LOCALNUMBER).is(localNumber)
                    .and(IConstant.PLAN_CNS_SPL_PLN_LOC.VENDORORCUSTOMER).is(vendorOrCustomer)
                    .and(IConstant.PLAN_CNS_SPL_PLN_LOC.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForList(IConstant.REGION.CNS_SPL_PLN_LOC, queryString, PlanCnsPlnSplLocEntity.class);
        }
        return null;
    }

    public List<PlanCnsPlnSplLocEntity> getEntitiesWithSourceSystemAndLocalPlantNumber (String sourceSystem, String localPlantNumber) {
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_SPL_PLN_LOC.SOURCE_SYSTEM).is(sourceSystem)
                .and(IConstant.PLAN_CNS_SPL_PLN_LOC.LOCALNUMBER).is(localPlantNumber).toQueryString();
        return queryForList(IConstant.REGION.PLAN_CNS_SPL_PLN_LOC,queryString,PlanCnsPlnSplLocEntity.class);
    }
}
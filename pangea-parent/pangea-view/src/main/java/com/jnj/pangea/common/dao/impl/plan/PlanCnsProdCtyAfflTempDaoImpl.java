package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProdCtyAfflTempEntity;
import org.apache.commons.lang.StringUtils;

public class PlanCnsProdCtyAfflTempDaoImpl extends CommonDaoImpl {

    private static PlanCnsProdCtyAfflTempDaoImpl instance;

    public static PlanCnsProdCtyAfflTempDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdCtyAfflTempDaoImpl();
        }
        return instance;
    }


    public PlanCnsProdCtyAfflTempEntity queryplanCnsProdCtyAfflTemp(String country, String dpParentCode, String sourceSystem) {
        if(StringUtils.isNotEmpty(country)&&StringUtils.isNotEmpty(dpParentCode)&&StringUtils.isNotEmpty(sourceSystem)){
            String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_PROD_CTY_AFFL_TEMP.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.PLAN_CNS_PROD_CTY_AFFL_TEMP.COUNTRY).is(country).and(IConstant.PLAN_CNS_PROD_CTY_AFFL_TEMP.DP_PARENT_CODE).is(dpParentCode).toQueryString();
            return queryForObject(IConstant.REGION.PLAN_CNS_PROD_CTY_AFFL, queryString, PlanCnsProdCtyAfflTempEntity.class);

        }
        return null;
    }
}

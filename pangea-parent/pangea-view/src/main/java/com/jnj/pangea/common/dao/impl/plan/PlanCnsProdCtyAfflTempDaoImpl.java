package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanCnsMaterialPlanStatusEntity;
import com.jnj.pangea.common.entity.plan.PlanCnsProdCtyAfflTempEntity;
import org.apache.commons.lang.StringUtils;

public class PlanCnsProdCtyAfflTempDaoImpl extends CommonDaoImpl {

    public static final String PLAN_CNS_PROD_CTY_AFFL = "/plan/cns_prod_cty_affl";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String COUNTRY = "country";
    public static final String DP_PARENT_CODE="dpParentCode";

    private static PlanCnsProdCtyAfflTempDaoImpl instance;

    public static PlanCnsProdCtyAfflTempDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsProdCtyAfflTempDaoImpl();
        }
        return instance;
    }


    public PlanCnsProdCtyAfflTempEntity queryplanCnsProdCtyAfflTemp(String country, String dpParentCode, String sourceSystem) {
        if(StringUtils.isNotEmpty(country)&&StringUtils.isNotEmpty(dpParentCode)&&StringUtils.isNotEmpty(sourceSystem)){
            String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem)
                    .and(COUNTRY).is(country).and(DP_PARENT_CODE).is(dpParentCode).toQueryString();
            return queryForObject(PLAN_CNS_PROD_CTY_AFFL, queryString, PlanCnsProdCtyAfflTempEntity.class);

        }
        return null;
    }
}

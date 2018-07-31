package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanEdmCountryInputEntity;
import org.apache.commons.lang.StringUtils;

public class PlanCnsCountryInputDaoImpl extends CommonDaoImpl {

    public static final String PLAN_EDM_COUNTRY_INPUT = "/plan/edm_country_input";

    public static final String SOURCE_SYSTEM = "sourceSystem";
    public static final String LOCAL_COUNTRY = "localCountry";

    private static PlanCnsCountryInputDaoImpl instance;

    public static PlanCnsCountryInputDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanCnsCountryInputDaoImpl();
        }
        return instance;
    }

    public String getConsumerPlanningRegion(String sourceSystem, String localCountry) {
        if(StringUtils.isEmpty(sourceSystem)||StringUtils.isEmpty(localCountry)){
            return null;
        }
        String queryString = QueryHelper.buildCriteria(SOURCE_SYSTEM).is(sourceSystem).and(LOCAL_COUNTRY).is(localCountry).toQueryString();
        PlanEdmCountryInputEntity cnsCountryInputEntity = queryForObject(PLAN_EDM_COUNTRY_INPUT, queryString, PlanEdmCountryInputEntity.class);
        if(cnsCountryInputEntity!=null && StringUtils.isNotBlank(cnsCountryInputEntity.getPlanningRegionID())){
            return cnsCountryInputEntity.getPlanningRegionID();
        }

        return null;
    }
}

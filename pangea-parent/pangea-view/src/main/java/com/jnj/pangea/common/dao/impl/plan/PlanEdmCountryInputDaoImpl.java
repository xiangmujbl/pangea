package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanEdmCountryInputEntity;
import org.eclipse.jetty.util.StringUtil;

public class PlanEdmCountryInputDaoImpl extends CommonDaoImpl {

    public static final String PLAN_EDM_COUNTRY_INPUT = "/plan/edm_country_input";

    public static final String SOURCE_SYSTEM= "sourceSystem";
    public static final String LOCAL_COUNTRY ="localCountry" ;
    public static final String LOCAL_CURRENCY="localCurrency";

    private static PlanEdmCountryInputDaoImpl instance;

    public static PlanEdmCountryInputDaoImpl getInstance() {
        if (instance == null) {
            instance = new PlanEdmCountryInputDaoImpl();
        }
        return instance;
    }

    public PlanEdmCountryInputEntity getEntityWithFormNameAndLocalCountry(String sourceSystem, String localCountry) {
        if(StringUtil.isNotBlank(sourceSystem) && StringUtil.isNotBlank(localCountry)){
            String queryString = QueryHelper.buildCriteria(
                    SOURCE_SYSTEM).is(sourceSystem)
                    .and(LOCAL_COUNTRY).is(localCountry)
                    .toQueryString();
            return queryForObject(PLAN_EDM_COUNTRY_INPUT, queryString, PlanEdmCountryInputEntity.class);
        }else{
            return null;
        }
    }
}

package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.PlanEdmCountryInputEntity;
import org.eclipse.jetty.util.StringUtil;

public class PlanEdmCountryInputDaoImpl extends CommonDaoImpl {

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
                    IConstant.PLAN_EDM_COUNTRY_INPUT.SOURCE_SYSTEM).is(sourceSystem)
                    .and(IConstant.PLAN_EDM_COUNTRY_INPUT.LOCAL_COUNTRY).is(localCountry)
                    .toQueryString();
            return queryForObject(IConstant.REGION.PLAN_EDM_COUNTRY_INPUT, queryString, PlanEdmCountryInputEntity.class);
        }else{
            return null;
        }
    }
}

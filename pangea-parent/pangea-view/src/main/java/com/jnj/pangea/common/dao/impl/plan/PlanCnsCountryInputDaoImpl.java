package com.jnj.pangea.common.dao.impl.plan;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.plan.EDMCountryInputEntity;
import org.apache.commons.lang.StringUtils;

public class PlanCnsCountryInputDaoImpl extends CommonDaoImpl {
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
        String queryString = QueryHelper.buildCriteria(IConstant.PLAN_CNS_COUNTRY_INPUT.SOURCE_SYSTEM).is(sourceSystem).and(IConstant.PLAN_CNS_COUNTRY_INPUT.LOCAL_COUNTRY).is(localCountry).toQueryString();
        EDMCountryInputEntity cnsCountryInputEntity = queryForObject(IConstant.REGION.PLAN_EDM_COUNTRY_INPUT, queryString, EDMCountryInputEntity.class);
        if(cnsCountryInputEntity!=null && StringUtils.isNotBlank(cnsCountryInputEntity.getPlanningRegionID())){
            return cnsCountryInputEntity.getPlanningRegionID();
        }

        return null;
    }
}

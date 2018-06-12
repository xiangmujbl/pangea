package com.jnj.pangea.common.dao.impl.edm;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.edm.EDMSourceSystemV1Entity;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import java.util.List;

public class EDMSourceSystemV1DaoImpl extends CommonDaoImpl {

    private static EDMSourceSystemV1DaoImpl instance;

    public static EDMSourceSystemV1DaoImpl getInstance() {
        if (instance == null) {
            instance = new EDMSourceSystemV1DaoImpl();
        }
        return instance;
    }

    public String getSourceSystemWithLocalSourceSystem(String localSourceSystem) {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(localSourceSystem).toQueryString();

        EDMSourceSystemV1Entity sourceSystems = queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
        if (null != sourceSystems) {
            return sourceSystems.getSourceSystem();
        }
        return "";
    }

    public EDMSourceSystemV1Entity getSourceSystemWithProjectOne() {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();

        return queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
    }

    public List<EDMSourceSystemV1Entity> getListWithSourceSystemWithProjectOne() {

        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(IConstant.VALUE.PROJECT_ONE).toQueryString();
        return queryForList(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
    }

    public EDMSourceSystemV1Entity getEntityWithLocalSourceSystem(String localSourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(localSourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
    }

    public EDMSourceSystemV1Entity getEntityWithSourceSystem(String sourceSystem) {
        if ("".equals(sourceSystem)) {
            return null;
        }
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
    }

    public List<EDMSourceSystemV1Entity> getEntityListWithSourceSystem(String sourceSystem) {
        if (StringUtils.isNotBlank(sourceSystem)) {
            String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
            return queryForList(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
        }
        return null;
    }

    public EDMSourceSystemV1Entity getEntityWithLocalSourceSystemAndSourceSystem(String localSourceSystem, String sourceSystem) {
        String queryString = QueryHelper.buildCriteria(IConstant.EDM_SOURCE_SYSTEM_V1.LOCAL_SOURCE_SYSTEM).is(localSourceSystem)
                .and(IConstant.EDM_SOURCE_SYSTEM_V1.SOURCE_SYSTEM).is(sourceSystem).toQueryString();
        return queryForObject(IConstant.REGION.EDM_SOURCE_SYSTEM_V1, queryString, EDMSourceSystemV1Entity.class);
    }
}

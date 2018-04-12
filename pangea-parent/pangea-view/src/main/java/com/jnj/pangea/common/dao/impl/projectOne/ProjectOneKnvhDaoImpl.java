package com.jnj.pangea.common.dao.impl.projectOne;

import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.projectOne.ProjectOneKnvhEntity;

public class ProjectOneKnvhDaoImpl extends CommonDaoImpl {

    private static ProjectOneKnvhDaoImpl instance;

    public static ProjectOneKnvhDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneKnvhDaoImpl();
        }
        return instance;
    }

    public ProjectOneKnvhEntity getEntityWithConditions(String kunnr, String vkorg, String datbi) {
        if (null != kunnr && !"".equals(kunnr) && null!=vkorg && !"".equals(vkorg) && null!=datbi && !"".equals(datbi)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_KNVH.KUNNR).is(kunnr)
                    .and(IConstant.PROJECT_ONE_KNVH.VKORG).is(vkorg)
                    .and(IConstant.PROJECT_ONE_KNVH.DATBI).greaterThanEqual(datbi).toQueryString();
            return queryForObject(IConstant.REGION.PROJECT_ONE_KNVH, queryString, ProjectOneKnvhEntity.class);
        }
        return null;
    }

}

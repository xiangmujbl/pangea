package com.jnj.omp.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.project_one.KnvhEntity;

public class ProjectOneKnvhDaoImpl extends CommonDaoImpl {

    private static ProjectOneKnvhDaoImpl instance;

    public static ProjectOneKnvhDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneKnvhDaoImpl();
        }
        return instance;
    }

    public KnvhEntity getEntityWithConditions(String kunnr, String vkorg, String datbi) {
        if (null != kunnr && !"".equals(kunnr) && null!=vkorg && !"".equals(vkorg) && null!=datbi && !"".equals(datbi)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_KNVH.KUNNR).is(kunnr)
                    .and(IConstant.PROJECT_ONE_KNVH.VKORG).is(vkorg)
                    .and(IConstant.PROJECT_ONE_KNVH.DATBI).greaterThanEqual(datbi).toQueryString();
            return queryForObject(IConstant.REGION.PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }

    public KnvhEntity getEntityWithKunnrAndVkorg(String kunnr, String vkorg) {
        if (null != kunnr && !"".equals(kunnr) && null!=vkorg && !"".equals(vkorg)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_KNVH.KUNNR).is(kunnr)
                    .and(IConstant.PROJECT_ONE_KNVH.VKORG).is(vkorg).toQueryString();
            return queryForObject(IConstant.REGION.PROJECT_ONE_KNVH, queryString, KnvhEntity.class);
        }
        return null;
    }
}

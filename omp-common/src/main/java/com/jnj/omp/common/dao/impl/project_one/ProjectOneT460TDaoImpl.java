package com.jnj.omp.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.omp.common.IConstant;
import com.jnj.omp.common.dao.impl.CommonDaoImpl;
import com.jnj.omp.common.entity.project_one.T460TEntity;

import static com.jnj.omp.common.service.ICommonService.commonDao;

public class ProjectOneT460TDaoImpl extends CommonDaoImpl {
    private static ProjectOneT460TDaoImpl instance;

    public static ProjectOneT460TDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneT460TDaoImpl();
        }
        return instance;
    }

    public T460TEntity getEntityWithSobslAndSpras(String sobsl, String spras) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_T460T.SOBSL).is(sobsl).and(IConstant.PROJECT_ONE_T460T.SPRAS).is(spras).toQueryString();
        return commonDao.queryForObject(IConstant.REGION.PROJECT_ONE_T460T,queryString,T460TEntity.class);
    }
}

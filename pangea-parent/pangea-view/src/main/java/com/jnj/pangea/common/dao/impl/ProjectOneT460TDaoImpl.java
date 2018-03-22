package com.jnj.pangea.common.dao.impl;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.entity.projectone.T460TEntity;

import java.util.List;

import static com.jnj.pangea.common.service.ICommonService.commonDao;

public class ProjectOneT460TDaoImpl extends CommonDaoImpl{
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

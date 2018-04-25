package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.JestEntity;

import java.util.List;

public class ProjectOneJestDaoImpl extends CommonDaoImpl {

    private static ProjectOneJestDaoImpl instance;

    public static ProjectOneJestDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneJestDaoImpl();
        }
        return instance;
    }

    public List<JestEntity> getEntityWithObjnr(String objnr) {

        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_JEST.OBJNR).is(IConstant.VALUE.OR + objnr)
                .and(IConstant.PROJECT_ONE_JEST.STAT).startsWith(IConstant.VALUE.I)
                .and(IConstant.PROJECT_ONE_JEST.INACT).is(IConstant.VALUE.X).not().toQueryString();

        return queryForList(IConstant.REGION.PROJECT_ONE_JEST, queryString, JestEntity.class);
    }
}

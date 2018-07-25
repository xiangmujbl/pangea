package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.Utils;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.JestEntity;

import java.util.List;

public class ProjectOneJestDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_JEST = "/project_one/jest";

    public static final String OBJNR = "objnr";
    public static final String STAT = "stat";
    public static final String INACT = "inact";

    private static ProjectOneJestDaoImpl instance;

    public static ProjectOneJestDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneJestDaoImpl();
        }
        return instance;
    }

    public List<JestEntity> getEntityWithObjnr(String objnr) {

        String queryString = QueryHelper.buildCriteria(OBJNR).is(Utils.OR + objnr)
                .and(STAT).startsWith(Utils.I)
                .and(INACT).is(Utils.X).not().toQueryString();

        return queryForList(PROJECT_ONE_JEST, queryString, JestEntity.class);
    }
    public List<JestEntity> getEntityByObjnr(String objnr) {
        String queryString = QueryHelper.buildCriteria(OBJNR).is(objnr)
                .and(STAT).startsWith(Utils.I)
                .and(INACT).is(Utils.X).not().toQueryString();
        return queryForList(PROJECT_ONE_JEST, queryString, JestEntity.class);
    }
    public List<JestEntity> getEntityByObjnrAndStat(String objnr) {
        String queryString = QueryHelper.buildCriteria(OBJNR).is(objnr)
                .and(INACT).is(Utils.X).not().toQueryString();
        return queryForList(PROJECT_ONE_JEST, queryString, JestEntity.class);
    }
}

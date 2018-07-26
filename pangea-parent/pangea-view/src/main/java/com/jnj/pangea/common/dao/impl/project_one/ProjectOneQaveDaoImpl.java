package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.QaveEntity;

/**
 * Created by SLiu170 on 2018/5/15.
 */
public class ProjectOneQaveDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_QAVE = "/project_one/qave";

    public static final String PRUEFLOS = "prueflos";

    private static ProjectOneQaveDaoImpl instance;

    public static ProjectOneQaveDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneQaveDaoImpl();
        }
        return instance;
    }
    public QaveEntity getQaveEntity(String prueflos) {
        String queryString = QueryHelper.buildCriteria(PRUEFLOS).is(prueflos).toQueryString();
        return queryForObject(PROJECT_ONE_QAVE, queryString, QaveEntity.class);
    }
}

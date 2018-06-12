package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.QaveEntity;

/**
 * Created by SLiu170 on 2018/5/15.
 */
public class ProjcetOneQaveDaoImpl  extends CommonDaoImpl {

    private static ProjcetOneQaveDaoImpl instance;

    public static ProjcetOneQaveDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjcetOneQaveDaoImpl();
        }
        return instance;
    }
    public QaveEntity getQaveEntity(String prueflos) {
        String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_Qave.PRUEFLOS).is(prueflos).toQueryString();
        return queryForObject(IConstant.REGION.PROJECT_ONE_Qave, queryString, QaveEntity.class);
    }
}

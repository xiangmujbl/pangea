package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.Utils;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.Tj02tEntity;

import java.util.List;

public class ProjectOneTj02tDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_TJ02T = "/project_one/tj02t";

    public static final String SPRAS = "spras";
    public static final String ISTAT = "istat";


    private static ProjectOneTj02tDaoImpl instance;

    public static ProjectOneTj02tDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOneTj02tDaoImpl();
        }
        return instance;
    }

    public List<Tj02tEntity> getEntityWithStat(String stat) {

        String queryString = QueryHelper.buildCriteria(SPRAS).is(Utils.EN)
                .and(ISTAT).is(stat).toQueryString();
        return queryForList(PROJECT_ONE_TJ02T, queryString  , Tj02tEntity.class);
    }
}

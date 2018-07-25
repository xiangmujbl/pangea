package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.pangea.common.RegionsConstant;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.Utils;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.TmabctEntity;

public class ProjectOneTmabctDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_TMABCT = "/project_one/tmabct";

    public static final String MAABC = "maabc";
    public static final String SPARS = "spars";

    private static  ProjectOneTmabctDaoImpl instance;

    public static ProjectOneTmabctDaoImpl getInstance(){
        if (instance == null) {
            instance = new ProjectOneTmabctDaoImpl();
        }
        return instance;
    }

    public TmabctEntity getEntityWithMaabc(String maabc){
        String name1QueryString = QueryHelper.buildCriteria(MAABC).is(maabc)
                .and(SPARS).is(Utils.EN).toQueryString();
        return queryForObject(PROJECT_ONE_TMABCT, name1QueryString, TmabctEntity.class);
    }
}

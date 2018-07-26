package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;

 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlpoEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOnePlpoDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_PLPO = "/project_one/plpo";
    public static final String PROJECT_ONE_PLPO_CLONE = "/project_one/plpo_clone";

    public static final String PLNTY = "plnty";
    public static final String PLNNR = "plnnr";
    public static final String PLNKN = "plnkn";
    public static final String ZAEHL = "zaehl";

    private static ProjectOnePlpoDaoImpl instance;

    public static ProjectOnePlpoDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlpoDaoImpl();
        }
        return instance;
    }

    public List<PlpoEntity> getEntityWithPlntyAndPlnnrAndPlnkn(String plnty, String plnnr, String plnkn) {
        if(StringUtils.isNotBlank(plnty)&&StringUtils.isNotBlank(plnnr)&&StringUtils.isNotBlank(plnkn)){
            String queryString = QueryHelper.buildCriteria(PLNTY).is(plnty)
                    .and(PLNNR).is(plnnr)
                    .and(PLNKN).is(plnkn)
                    .toQueryString();
            return queryForList(PROJECT_ONE_PLPO_CLONE, queryString, PlpoEntity.class,ZAEHL);
        }
        return null;
    }
}

package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.RegionsConstant;
 import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlflEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOnePlflDaoImpl extends CommonDaoImpl {

    public static final String PROJECT_ONE_PLFL = "/project_one/plfl";
    public static final String PROJECT_ONE_PLFL_CLONE = "/project_one/plfl_clone";

    public static final String PLNNR = "plnnr";
    public static final String PLNAL = "plnal";
    public static final String PLNFL = "plnfl";
    public static final String ZAEHL = "zaehl";

    private static ProjectOnePlflDaoImpl instance;

    public static ProjectOnePlflDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlflDaoImpl();
        }
        return instance;
    }

    public List<PlflEntity> getEntityWithPlnnrAndPlnalAndPlnfl(String plnnr,String plnal,String plnfl){
        if (StringUtils.isNotBlank(plnnr) && StringUtils.isNotBlank(plnal) && StringUtils.isNotBlank(plnfl)){
            String queryString = QueryHelper.buildCriteria(PLNNR).is(plnnr).
                    and(PLNAL).is(plnal).
                    and(PLNFL).is(plnfl).toQueryString();
            //return queryForList(PROJECT_ONE_PLFL_CLONE, queryString, PlflEntity.class,ZAEHL);
            return queryForList(PROJECT_ONE_PLFL_CLONE, queryString, PlflEntity.class);
        }
        return null;
    }
}

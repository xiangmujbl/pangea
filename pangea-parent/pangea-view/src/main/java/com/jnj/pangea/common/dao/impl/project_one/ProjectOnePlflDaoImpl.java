package com.jnj.pangea.common.dao.impl.project_one;

import com.jnj.adf.client.api.query.QueryHelper;
import com.jnj.adf.grid.utils.LogUtil;
import com.jnj.pangea.common.IConstant;
import com.jnj.pangea.common.dao.impl.CommonDaoImpl;
import com.jnj.pangea.common.entity.project_one.PlflEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ProjectOnePlflDaoImpl extends CommonDaoImpl {
    private static ProjectOnePlflDaoImpl instance;

    public static ProjectOnePlflDaoImpl getInstance() {
        if (instance == null) {
            instance = new ProjectOnePlflDaoImpl();
        }
        return instance;
    }

    public List<PlflEntity> getEntityWithPlnnrAndPlnalAndPlnfl(String plnnr,String plnal,String plnfl){
        if (StringUtils.isNotBlank(plnnr) && StringUtils.isNotBlank(plnal) && StringUtils.isNotBlank(plnfl)){
            String queryString = QueryHelper.buildCriteria(IConstant.PROJECT_ONE_PLFL.PLNNR).is(plnnr).
                    and(IConstant.PROJECT_ONE_PLFL.PLNAL).is(plnal).
                    and(IConstant.PROJECT_ONE_PLFL.PLNFL).is(plnfl).toQueryString();
            //return queryForList(IConstant.REGION.PROJECT_ONE_PLFL_CLONE, queryString, PlflEntity.class,IConstant.PROJECT_ONE_PLFL.ZAEHL);
            return queryForList(IConstant.REGION.PROJECT_ONE_PLFL_CLONE, queryString, PlflEntity.class);
        }
        return null;
    }
}
